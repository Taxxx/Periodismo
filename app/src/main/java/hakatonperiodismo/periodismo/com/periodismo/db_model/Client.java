package hakatonperiodismo.periodismo.com.periodismo.db_model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;


public class Client extends Helper {
private SQLiteDatabase db;	
	protected Client(Context context, String dbName) {
		super(context, dbName);
		this.CreateDatabase();
	}	
	protected void OpenDb(){
		db = this.getWritableDatabase();
	}	
	
	/*
	USUARIO
	 */


	protected Cursor mostrar_usuario_login(String usuario, String password){
		OpenDb();
		String[] selectionArgs = new String[]{usuario, password};
		return db.query(Table.Usuarios.TABLE_NAME, null, Table.Usuarios.USUARIO + " = ? AND " +Table.Usuarios.PASSWORD + " = ? " , selectionArgs, null, null, null);
	}




}