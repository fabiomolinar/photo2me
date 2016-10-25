<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use Lang;

use App\Http\Requests;
use App\Http\Requests\ContatoRequest;
use App\Lead;
use Mail;

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
    //Caso contrário, salvar dados de contato, enviar email e retornar um json de sucesso
    if ($request->has('email')){
      $lead = Lead::where('email',$request->input('email'))->first();
      if (!$lead){
        $novoLead = new Lead;
        $novoLead->nome = $request->input('nome');
        $novoLead->email = $request->input('email');
        $novoLead->meio_primeiro_contato = "formulario contato do site";
        $novoLead->save();
        $lead = $novoLead;
      }
    }
    $mensagem = $request->input('mensagem');
    /*
    Mail::send('emails.contatoFormSite', ['lead' => $lead, 'mensagem' => $mensagem], function($m){
      $m->from('admin@photo2me.com','Photo2Me');
      $m->to('photo2me.kontakt@gmail.com','Contato Photo2Me')->subject('Contato através formulário do site');
    });
    */
    return response()->json([
      'status' => 'sucesso'
    ], 200);
  }
}
