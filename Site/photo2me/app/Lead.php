<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class Lead extends Model
{
  //Definindo qual a tabela a qual esse modelo se refere
  protected $table = 'leads';
  //Definindo qual a primary key
  protected $primaryKey = 'id';

  protected $fillable = [
    'nome',
    'email',
    'telefone',
    'meio_primeiro_contato',
    'e_usuario',
    'deu_opt_out'
  ];
}
