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

Route::get('/', function () {
    return view('publicas.home');
});

//API
Route::post('dadosFesta',['as' => 'dadosFesta', 'uses' => 'FestaController@dadosFesta']);
Route::post('receberFoto',['as' => 'receberFoto', 'uses' => 'FotoController@receberFoto']);

//TESTES
Route::get('enviar', function () {
    return view('APITest.enviar');
});
