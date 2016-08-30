<?php

use Illuminate\Database\Schema\Blueprint;
use Illuminate\Database\Migrations\Migration;

class AddNameToFestasTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::table('festas', function (Blueprint $table) {
            $table->string('nome_festa')->after('apelido');
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::table('festas', function (Blueprint $table) {
            $table->dropColumn('nome_festa');
        });
    }
}
