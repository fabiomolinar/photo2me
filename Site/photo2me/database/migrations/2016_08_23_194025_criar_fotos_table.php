<?php

use Illuminate\Database\Schema\Blueprint;
use Illuminate\Database\Migrations\Migration;

class CriarFotosTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('fotos', function (Blueprint $table) {
            $table->increments('id');
            $table->integer('user_id')->unsigned();
            $table->integer('festa_id')->unsigned();
            $table->integer('ordem_id')->unsigned();
            $table->string('nome_fotografo');
            $table->string('nome_arquivo_original');
            $table->string('nome_arquivo_novo');
            $table->float('qualidade');
            $table->boolean('pedida');
            $table->boolean('gratuita');
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
        Schema::drop('fotos');
    }
}
