<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;

use App\Http\Requests;
use Lang;
use App\Festa;

class FestaController extends Controller
{
    public function dadosFesta(Request $request){
      //Verificar se o request tem uma língua selecionado. Se não tiver, usar o inglês.
      $lingua = 'en';
      if ($request->has('lingua')) {
        $lingua = $request->input('lingua');
      }

      if ($request->has('apelido')) {
        $festa = Festa::where('apelido',$request->input('apelido'))->first();
        if ($festa) {
          return response()->json([
            'status' => '200',
            'message' => Lang::get('messages.evento-encontrado',[],$lingua),
            'data_inicio' => $festa->data_inicio,
            'data_fim' => $festa->data_fim,
            'apelido' => $festa->apelido
          ]);
        } else {
          return response()->json([
            'status' => '404',
            'message' => Lang::get('messages.evento-nao-encontrado')
          ], 404);
        }
      } else {
        return response()->json([
          'status' => '400',
          'message' => Lang::get('messages.faltando-campos')
        ], 400);
      }
    }
}
