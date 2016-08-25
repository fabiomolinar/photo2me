<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;

use App\Http\Requests;
use Lang;
use Storage;
use File;
use App\Festa;
use App\Foto;

class FotoController extends Controller
{
    public function receberFoto(Request $request){
      //Verificar se o request tem uma língua selecionado. Se não tiver, usar o inglês.
      $lingua = 'en';
      $idUsuarioFesta = 0;
      if ($request->has('lingua')) {
        $lingua = $request->input('lingua');
      }
      if ($request->has('idUsuarioFesta')) {
        $idUsuarioFesta = $request->input('idUsuarioFesta');
      }

      //Verificar se o request está em ordem
      if ($request->hasFile('imagem') && $request->has('apelido')){
        //Verificar se a festa existe
        $festa = Festa::where('apelido',$request->input('apelido'))->first();
        if ($festa) {
          //Verificar se o arquivo é uma imagem
          $imagem = $request->file('imagem');
          $extensao = $imagem->getClientOriginalExtension();
          //Verificar se o arquivo é de fato uma imagem
          if (strpos(image_type_to_mime_type(exif_imagetype($imagem)),'image') !== false) {
            //Salvar foto no storage
            $nomeImagem = $idUsuarioFesta . '_' . date("Y-m-d H-i") . '.' . $extensao;
            $caminho = $festa->id . '/' . $nomeImagem;
            Storage::disk('fotos')->put($caminho, File::get($imagem));
            //Adicionando registro da foto ao BD
            $foto = new Foto;
            $foto->user_id = $festa->user_id;
            $foto->festa_id = $festa->id;
            $foto->nome_fotografo = $idUsuarioFesta;
            $foto->nome_arquivo_original = $imagem->getClientOriginalName();
            $foto->nome_arquivo_novo = $nomeImagem;
            $foto->save();
            return response()->json([
              'mensagem' => Lang::get('messages.sucesso',[],$lingua)
            ]);
          }
        } else {
          return response()->json([
            'mensagem' => Lang::get('messages.evento-nao-encontrado',[],$lingua)
          ], 404);
        }
      } else {
        return response()->json([
          'mensagem' => Lang::get('messages.faltando-campos',[],$lingua)
        ], 400);
      }
    }
}
