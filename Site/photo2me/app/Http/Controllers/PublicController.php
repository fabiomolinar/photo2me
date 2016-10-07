<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use Lang;

use App\Http\Requests;

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
}
