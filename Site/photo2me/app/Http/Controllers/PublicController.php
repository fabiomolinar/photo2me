<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use Lang;

use App\Http\Requests;
use App\Http\Requests\ContatoRequest;

class PublicController extends Controller
{
  public function home(){
    $vantagens = array(
      array(
        'icone' => 'checkmark',
        'titulo' => Lang::get('messages.vantagem1-titulo'),
        'descricao' => Lang::get('messages.vantagem1-descricao')
      ),
      array(
        'icone' => 'shop',
        'titulo' => Lang::get('messages.vantagem2-titulo'),
        'descricao' => Lang::get('messages.vantagem2-descricao')
      ),
      array(
        'icone' => 'protect',
        'titulo' => Lang::get('messages.vantagem3-titulo'),
        'descricao' => Lang::get('messages.vantagem3-descricao')
      )
    );
    return view('publicas.home')->with('vantagens',$vantagens);
  }

  public function contato(ContatoRequest $request){
    /*
    *   Como estou usando a classe ContatoRequest e passando o request,
    *   o request está sendo validado automatimente. Se ele não passar
    *   a validação, o laravel retorna automaticamente um JSON junto
    *   com as mensagens de erro.
    */
    //Caso contrário, retornar um json de sucesso
    return response()->json([
      'status' => 'sucesso'
    ], 200);
  }
}
