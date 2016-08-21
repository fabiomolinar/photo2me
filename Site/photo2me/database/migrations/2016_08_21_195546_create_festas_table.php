<?php

use Illuminate\Database\Schema\Blueprint;
use Illuminate\Database\Migrations\Migration;

class CreateFestaTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
      Schema::create('festas', function (Blueprint $table) {
          $table->increments('id');
          $table->string('apelido');
          $table->integer('user_id')->unsigned();
          $table->dateTimeTz('data_inicio');
          $table->dateTimeTz('data_fim');
          $table->integer('num_convidados');
          $table->integer('num_fotos_tiradas');
          $table->integer('num_fotos_coletadas');
          $table->string('pasta');
          $table->boolean('stat_aberta');
          $table->boolean('stat_fim_festa');
          $table->boolean('stat_cancelada');
          $table->boolean('stat_paga');
          $table->boolean('stat_amostra_baixada');
          $table->boolean('stat_coletada');
          $table->boolean('stat_arquivada');
          $table->boolean('stat_deletada');
          $table->timestamps();
      });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::drop('festas');
    }
}
