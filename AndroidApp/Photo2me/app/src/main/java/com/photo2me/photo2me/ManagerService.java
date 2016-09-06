package com.photo2me.photo2me;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.os.IBinder;
import android.util.Log;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ManagerService extends Service {
  private Boolean estaRodando;

  @Override
  public void onCreate(){
    Log.d(ManagerService.class.getName(),"Serviço criado");
    estaRodando = false;
  }
  
  @Override
  public int onStartCommand(Intent intent, int flags, int startId){
    if (!estaRodando){
      estaRodando = true;
      Log.d(ManagerService.class.getName(),"Serviço iniciado");
      //Iniciando novo thread pois não queremos que esse serviço seja executado no thread da aplicação
      new Thread(new Runnable(){
        @Override
        public void run(){
          while(true){
            try{
              Log.d(ManagerService.class.getName(),"Serviço rodando e funcionando");
              //Fazer trabalho aqui
              //Verificar se tem wifi
              ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
              NetworkInfo infoWifi = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
              Log.d(ManagerService.class.getName(),"wifi está conectado: "+ infoWifi.isConnected());
              if (!infoWifi.isConnected()){
                //Fazer lista de onde pode ter fotos
                List<File> listaCaminhos = criarListaCaminhos();
                //Fazer lista de arquivos que gostaríamos de enviar para o servidor
                List<File> listaArquivos = criarListaArquivos();
                //Iniciando envio das fotos
              }
              Thread.sleep(5000);
            } catch (Exception e){
              Log.d(ManagerService.class.getName(),e.getMessage());
              e.printStackTrace();
            }
          }
        }
      }).start();
    }
    //Retorno para que o serviço seja recriado caso seja destruído
    return Service.START_STICKY;
  }

  private List<File> criarListaCaminhos() {
    List<File> lista = new ArrayList<File>();
    //Lista de pastas onde podem ter fotos. Adicionar se necessário.
    lista.add(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM));
    lista.add(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES));
    //Removendo items repetidos
    Set<File> hash = new HashSet<>();
    hash.addAll(lista);
    lista.clear();
    lista.addAll(hash);
    Log.d("asd",lista.toString());
    return  lista;
  }
  private List<File> criarListaArquivos() {
    List<File> lista = new ArrayList<File>();

    return lista;
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