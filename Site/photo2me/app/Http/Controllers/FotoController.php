<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;

use App\Http\Requests;
use Lang;
use App\Festa;

class FotoController extends Controller
{
    public function receberFoto(Request $request){
      //Verificar se o request tem uma língua selecionado. Se não tiver, usar o inglês.
      $lingua = 'en';
      if ($request->has('lingua')) {
        $lingua = $request->input('lingua');
      }
      dd([
        file_exists($request->file('imagem')),
        $request->file('imagem')->getPathName(),
        Input::file('imagem')
      ]);

      if ($request->hasFile('imagem') && $request->has('apelido')){
        //Verificar se a festa existe
        $festa = Festa::where('apelido',$request->input('apelido'))->first();
        if ($festa) {
          //Verificar se o arquivo é uma imagem
          $imagem = $request->file('imagem');
          if (strpos(image_type_to_mime_type(exif_imagetype($imagem)),'image') !== false) {
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
