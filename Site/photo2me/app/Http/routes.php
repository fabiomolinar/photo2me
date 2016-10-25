<?php

/*
|--------------------------------------------------------------------------
| Application Routes
|--------------------------------------------------------------------------
|
| Here is where you can register all of the routes for an application.
| It's a breeze. Simply tell Laravel the URIs it should respond to
| and give it the controller to call when that URI is requested.
|
*/

//Rotas públicas
Route::group([],function(){
  Route::get('/', ['uses' => 'PublicController@home', 'as' => 'home']);
  Route::get('/como-funciona',function(){return view('publicas.comoFunciona');})->name('comoFunciona');
  Route::get('/contato',function(){return view('publicas.contato');})->name('contato');
  Route::get('/cadastrar',function(){return view('publicas.cadastrar');})->name('cadastrar');
  Route::get('/entrar',function(){return view('publicas.entrar');})->name('entrar');
  Route::get('/esqueci-senha',function(){return view('publicas.esqueceuSenha');})->name('esqueceuSenha');
  Route::post('/contato', ['uses' => 'PublicController@contato', 'as' => 'postContato']);
});

//API
Route::post('dadosFesta',['as' => 'dadosFesta', 'uses' => 'FestaController@dadosFesta']);
Route::post('receberFoto',['as' => 'receberFoto', 'uses' => 'FotoController@receberFoto']);

//TESTES
Route::get('enviar', function () {
    return view('APITest.enviar');
});
