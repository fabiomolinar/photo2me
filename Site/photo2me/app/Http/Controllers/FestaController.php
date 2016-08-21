<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;

use App\Http\Requests;
use Lang;

class FestaController extends Controller
{
    public function dadosFesta(Request $request){
      //Verificar se o request tem uma língua selecionado. Se não tiver, usar o inglês.
      $lingua = 'en';
      $id = $request->input('id');

      if ($request->has('lingua')) {
        $lingua = $request->input('lingua');
      }
      //Dados falsos para teste
      $dadosFesta = array(
        'id' => '1',
        'comeco' => '2016-10-12',
        'fim' => '2016-10-13'
      );
      if ($id == $dadosFesta['id']) {
        return response()->json([
          'status' => '200',
          'message' => Lang::get('messages.evento-encontrado',[],$lingua)
        ]);
      }
      return response()->json([
        'status' => '400',
        'evento-nao-encontrado' => Lang::get('messages.evento-nao-encontrado')
      ], 400);
    }
}
