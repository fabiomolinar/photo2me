package com.photo2me.photo2me;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Environment;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.util.Log;

import org.joda.time.DateTimeZone;
import org.joda.time.LocalDateTime;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;

public class ManagerService extends Service {
  private static final String TAG = "Photo2Me/" + ManagerService.class.getName();
  public static final String FOTO_PATH = "com.photo2me.photo2me.path para foto";
  public static final String FOTO_ID_USUARIO_FESTA = "com.photo2me.photo2me.id_usuario_festa";
  public static final String FOTO_FESTA_ID = "com.photo2me.photo2me.id da festa";
  private Boolean estaRodando;
  private List<File> listaFotos = new ArrayList<File>();
  private Locale locale;
  private Context contexto;

  @Override
  public void onCreate(){
    Log.d(ManagerService.class.getName(),"Serviço criado");
    contexto = getApplicationContext();
    estaRodando = false;
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
      locale = getResources().getConfiguration().getLocales().get(0);
    } else {
      locale = getResources().getConfiguration().locale;
    }
  }
  
  @Override
  public int onStartCommand(Intent intent, int flags, int startId){
    if (!estaRodando){
      estaRodando = true;
      //Iniciando novo thread pois não queremos que esse serviço seja executado no thread da aplicação
      new Thread(new Runnable(){
        @Override
        public void run(){
          while(true){
            //Pegar preferencias do usuario
            SharedPreferences appPreferencias = PreferenceManager.getDefaultSharedPreferences(contexto);
            Boolean usarWifi = appPreferencias.getBoolean(Preferencias.APP_UPLOAD_SO_WIFI,true);
            Log.d(TAG,"usar wifi: " + usarWifi.toString());
            try{
              //Fazer trabalho aqui
              //Verificar se tem wifi
              ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
              NetworkInfo infoWifi = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
              Log.d(ManagerService.class.getName(),"wifi está conectado: "+ infoWifi.isConnected());
              if (infoWifi.isConnected() || (!usarWifi)){
                //Fazer lista de onde pode ter fotos
                //Chamar a função com o parametro "removerSubPastas" se estiver usando método recursivo
                List<File> listaCaminhos = criarListaCaminhos(false);
                //Criar lista de arquivos que gostaríamos de enviar para o servidor
                criarListaArquivos(listaCaminhos);
                //Ordenar a lista por ordem cronológica
                Log.d(TAG,"lista fotos: " + listaFotos.toString());
                //Criar lista com os períodos de captura de foto
                List<Festa> listaFestas = Festa.find(Festa.class,"finalizada = 0");
                List<Comparador> listaComparador = new ArrayList<Comparador>();
                for (Festa _festa : listaFestas){
                  listaComparador.add(new Comparador(_festa.getDataInicio(),_festa.getDataFim(),_festa.getTimezone(),
                          locale,_festa.getApelido(),_festa.getIdFestaUsuario()));
                }
                //Verificar quais fotos estão dentro dos períodos de captura
                for (File _file : listaFotos){
                  for (Comparador _comparador : listaComparador){
                    if (_comparador.entreInicioEFim(_file.lastModified())){
                      //Enviar para o FotoSenderIntent
                      LocalDateTime data = new LocalDateTime(_file.lastModified(), DateTimeZone.forID("America/Sao_Paulo"));
                      Log.d(TAG,"Foto sendo enviada ao ServiceIntent: " + _file.getName() + "; lastModified: " + data.toString());
                      Intent serviceIntent = new Intent(ManagerService.this,FotoSenderService.class);
                      serviceIntent.putExtra(ManagerService.FOTO_FESTA_ID,_comparador.apelido);
                      serviceIntent.putExtra(ManagerService.FOTO_ID_USUARIO_FESTA,_comparador.idFestaUsuario);
                      serviceIntent.putExtra(ManagerService.FOTO_PATH,_file.getAbsolutePath());
                      startService(serviceIntent);
                    }
                  }
                }
              }
            } catch (Exception e){
              Log.d(ManagerService.class.getName(),e.getMessage());
              e.printStackTrace();
            }
            try {
              int minutosProximaTentativa = 1;
              Thread.sleep(minutosProximaTentativa * 1000);
            } catch (InterruptedException e){
              e.printStackTrace();
            }
          }
        }
      }).start();
    }
    //Retorno para que o serviço seja recriado caso seja destruído
    return Service.START_STICKY;
  }

  private List<File> criarListaCaminhos(Boolean removerSubPastas) {
    List<File> lista = new ArrayList<File>();
    //Lista de pastas onde podem ter fotos. Adicionar se necessário.
    //Android tem três tipos de memórias. Interna, externa e removível
    lista.add(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM));
    lista.add(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES));
    lista.add(new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).toString() + File.separator + "100ANDRO"));
    lista.add(new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).toString() + File.separator + "Camera"));
    lista.add(new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).toString() + File.separator + "Pictures"));
    lista.add(new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString() + File.separator + "100ANDRO"));
    lista.add(new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString() + File.separator + "Camera"));
    lista.add(new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString() + File.separator + "Pictures"));
    //Removendo items repetidos
    Set<File> hash = new HashSet<>();
    hash.addAll(lista);
    lista.clear();
    lista.addAll(hash);
    //Removendo itens que talvez não existam
    Iterator<File> iterator = lista.iterator();
    while (iterator.hasNext()){
      File next = iterator.next();
      if (!next.isDirectory() || !next.exists()){
        iterator.remove();
      }
    }
    if (removerSubPastas){
      //Removendo itens que sejam sub-pastas de alguma pasta
      iterator = lista.iterator();
      while (iterator.hasNext()){
        File next = iterator.next();
        for (int i = 0; i < lista.size(); i++){
          if (lista.get(i).toString() != ""){
            if (next.toString().startsWith(lista.get(i).toString())){
              //se for igual, fazer nada, pois quando é igual, a comparação acima retorna true
              if (!(next.toString() == lista.get(i).toString())){
                iterator.remove();
                break;
              }
            }
          }
        }
      }
    }
    return  lista;
  }
  private void criarListaArquivos(List<File> listaPastas){
    //Essa função não faz a busca por fotos de forma profunda
    //Apenas verifica nas pastas listadas se existem fotos
    listaFotos.clear();
    for (int i = 0; i < listaPastas.size(); i++){
      if (listaPastas.get(i).listFiles() != null){
        for (File arquivo : listaPastas.get(i).listFiles()){
          if (arquivo.isFile()){
            //Verificar se a extensão é a extensão de fotos
            if (arquivo.toString().endsWith(".jpg") ||
                    arquivo.toString().endsWith(".jpeg") ||
                    arquivo.toString().endsWith(".JPEG") ||
                    arquivo.toString().endsWith(".JPG")){
              listaFotos.add(arquivo);
            }
          }
        }
      }
    }
  }
  private void criarListaArquivosRecursivo(List<File> listaPastas) {
    //Essa função usa uma outra função que faz a busca por fotos de forma recursiva e em profundidade nas pastas
    //procurando por fotos
    listaFotos.clear();
    for (int i = 0; i < listaPastas.size(); i++){
      listarEmDiretorio(listaPastas.get(i));
    }
  }
  private void listarEmDiretorio(File diretorio){
    //
    File[] lista = diretorio.listFiles();
    if (lista != null){
      for (File arquivo : lista){
        if (arquivo.isFile()){
          //Ver se a extensão é uma extensão de imagem
          if (arquivo.toString().endsWith(".jpg") ||
                  arquivo.toString().endsWith(".jpeg") ||
                  arquivo.toString().endsWith(".JPEG") ||
                  arquivo.toString().endsWith(".JPG")){
            listaFotos.add(arquivo);
          }
        } else if (arquivo.isDirectory()){
          listarEmDiretorio(arquivo);
        }
      }
    }
  }

  public Boolean getEstaRodando(){
    if (estaRodando){
      return true;
    } else {
      return false;
    }
  }

  @Override
  public IBinder onBind(Intent arg0){
    return null;
  }
  
  @Override
  public void onDestroy(){
    Log.d(ManagerService.class.getName(),"Serviço destruido");
    estaRodando = false;
  }
}