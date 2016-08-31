package com.photo2me.photo2me;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class FestaDbAdapter{
    private static final String DATABASE_NAME = "festa.db";
    private static final int DATABASE_VERSION = 1;

    //Tabelas
    public static final String FESTA_TABLE = "festa";

    //Colunas
    public static final String FESTA_ID = "_id";
    public static final String FESTA_APELIDO = "apelido";
    public static final String FESTA_DATA_INICIO = "data_inicio";
    public static final String FESTA_DATA_FIM = "data_fim";
    //SQLite não aceita booleans, então a saída é usar integers, com o 0 sendo o false e o 1 sendo o true
    public static final String FESTA_ATIVA = "ativa";

    private String[] todasColunas = {FESTA_ID,FESTA_APELIDO,FESTA_DATA_INICIO,FESTA_DATA_FIM,FESTA_ATIVA};

    //String que cria a base de dados
    public static final String CRIAR_DATABASE = "create table " + FESTA_TABLE + " ("
            + FESTA_ID + " integer primary key autoincrement, "
            + FESTA_APELIDO + " text not null, "
            + FESTA_DATA_INICIO + " text not null, "
            + FESTA_DATA_FIM + " text not null,  "
            + FESTA_ATIVA + " integer not null);";

    private SQLiteDatabase sqlDB;
    private Context context;
    private FestaDbHelper festaDbHelper;

    public FestaDbAdapter(Context context){
        this.context = context;
    }
    
    //Métodos para manipular o banco de dados
    public FestaDbAdapter open() throws android.database.SQLException{
        festaDbHelper = new FestaDbHelper(context);
        sqlDB = festaDbHelper.getWritableDatabase();
        return this;
    }

    public void close(){
        festaDbHelper.close();
    }

    private static class FestaDbHelper extends SQLiteOpenHelper{
        FestaDbHelper(Context context){
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db){
            //Criando a tabela
            db.execSQL(CRIAR_DATABASE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
            Log.w(FestaDbHelper.class.getName(),"Upgrading db from version " + oldVersion + " to "
                    + newVersion + ", which will destroy all old data.");
            //Dropping the table
            db.execSQL("DROP TABLE IF EXISTS " + FESTA_TABLE);
            onCreate(db);
        }

    }
}