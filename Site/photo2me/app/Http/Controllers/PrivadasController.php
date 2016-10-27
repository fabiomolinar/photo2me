<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use Lang;

use App\Http\Requests;

class PrivadasController extends Controller
{
  public function painel(){
    return view('privadas.painel');
  }
}
