package com.photo2me.photo2me;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class FotoDbAdapter{
  private static final String DATABASE_NAME = "foto.db";
  private static final int DATABASE_VERSION = 1;
  
  //Tabelas
  public static final String FOTO_TABLE = "foto";
  
  //Colunas
  public static final String FOTO_ID = "_id";
  public static final String FOTO_NOME = "nome";
  public static final String FOTO_DATA_TIRADA = "data_tirada";
  //FOTO_COMUNICADA significa que foi enviado ao servidor a informação que a foto existe
  //SQLite não aceita booleans, então a saída é usar integers, com o 0 sendo o false e o 1 sendo o true
  public static final String FOTO_COMUNICADA = "comunicada";
  //FOTO_ENVIADA significa que a foto foi enviada ao servidor
  public static final String FOTO_ENVIADA = "enviada";
  
  private String[] todasColunas = {FOTO_ID,FOTO_NOME,FOTO_DATA_TIRADA,FOTO_COMUNICADA,FOTO_ENVIADA};
  
  //String que cria a base de dados
  public static final String CRIAR_DATABASE = "create table " + FOTO_TABLE + " (" 
    + FOTO_ID + " integer primary key autoincrement, "
    + FOTO_NOME + " text not null, "
    + FOTO_DATA_TIRADA + " text not null, "
    + FOTO_COMUNICADA + " integer not null,  "
    + FOTO_ENVIADA + " integer not null);";
  
  private SQLiteDatabase sqlDB;
  private Context context;
  private FotoDbHelper fotoDbHelper;
  
  public FotoDbAdapter(Context context){
    this.context = context;
  }
  
  public FotoDbAdapter open() throws android.database.SQLException{
    fotoDbHelper = new FotoDbHelper(context);
    sqlDB = fotoDbHelper.getWritableDatabase();
    return this;
  }
  
  public void close(){
    fotoDbHelper.close();
  }
  
  private static class FotoDbHelper extends SQLiteOpenHelper{
    FotoDbHelper(Context context){
      super(context, DATABASE_NAME, null, DATABASE_VERSION);      
    }
    
    @Override
    public void onCreate(SQLiteDatabase db){
      //Criando a tabela
      db.execSQL(CRIAR_DATABASE);
    }
    
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
      Log.w(FotoDbHelper.class.getName(),"Upgrading db from version " + oldVersion + " to "
              + newVersion + ", which will destroy all old data.");
      //Dropping the table
      db.execSQL("DROP TABLE IF EXISTS " + FOTO_TABLE);
      onCreate(db);
    }
    
  }
}