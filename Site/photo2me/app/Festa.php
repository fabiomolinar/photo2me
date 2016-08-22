<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class Festa extends Model
{
    //Definindo qual a tabela a qual esse modelo se refere
    protected $table = 'festas';
    //Definindo qual a primary key
    protected $primaryKey = 'id';

    protected $fillable = [
      'apelido',
      'user_id',
      'data_inicio',
      'data_fim',
      'timezone',
      'num_convidados',
      'pasta',
      'stat_aberta'
    ];

}
