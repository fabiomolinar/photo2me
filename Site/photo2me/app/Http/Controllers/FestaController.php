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
          $idUsuario = $festa->num_convidados;
          $festa->num_convidados = $festa->num_convidados + 1;
          $festa->save();
          $idUsuarioFesta = $festa->id . '-' . $idUsuario;
          return response()->json([
            //O nome das variáveis abaixo não devem ser modificadas sem razão,
            //visto que o applicativo para android usa elas!!
            'mensagem' => Lang::get('messages.evento-encontrado',[],$lingua),
            'dataInicio' => $festa->data_inicio,
            'dataFim' => $festa->data_fim,
            'apelido' => $festa->apelido,
            'nomeFesta' => $festa->nome_festa,
            'timezone' => $festa->timezone,
            'idUsuarioFesta' => $idUsuarioFesta
          ]);
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
