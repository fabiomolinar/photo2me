<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class Foto extends Model
{
  //Definindo qual a tabela a qual esse modelo se refere
  protected $table = 'fotos';
  //Definindo qual a primary key
  protected $primaryKey = 'id';

  protected $fillable = [
    'user_id',
    'festa_id',
    'ordem_id',
    'nome_fotografo',
    'nome_arquivo_original',
    'nome_arquivo_novo',
    'qualidade',
    'pedida',
    'gratuita'
  ];
}
