package hakatonperiodismo.periodismo.com.periodismo.db_model;

import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;

import hakatonperiodismo.periodismo.com.periodismo.objetos.Usuario;


public class Controlador extends Client {

    public Controlador(Context context, String dbName) {
        super(context, dbName);
    }

    public void setDatabaseListener(DatabaseCallback dbListener){
        this.setDatabaseListener(dbListener);
    }
    //////Objetos********************************************************

    private ArrayList<Usuario> usuario=new ArrayList<Usuario>();

    /////Funciones*****************************************************



    public Integer select_login_usuario(String usuario_s, String password_s){
        Cursor cursor = this.mostrar_usuario_login(usuario_s, password_s);
        Integer contador=0;

        for(cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext()){
            usuario.add(new Usuario(
                    cursor.getString(cursor.getColumnIndex(Table.Usuarios.ID_USUARIO)),
                    cursor.getString(cursor.getColumnIndex(Table.Usuarios.USUARIO)),
                    cursor.getString(cursor.getColumnIndex(Table.Usuarios.PASSWORD))

                    ));

            contador=contador+1;
        }
        return contador;
    }




}