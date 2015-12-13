package hakatonperiodismo.periodismo.com.periodismo.SharedPreferences;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by debbie on 11-12-15.
 */
public class SharedPreferencesClass {


    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    String id_periodista;
    public void Guardar_Shared(Context context,String id_periodista)
    {

        prefs=context.getSharedPreferences("periodista", Context.MODE_PRIVATE);
        editor= prefs.edit();
        editor.putString("id_periodista", id_periodista);
        editor.commit();

    }




    public String   Consultar_si_hay_registro(Context context)
    {
        prefs =context.getSharedPreferences("periodista", Context.MODE_PRIVATE);
        editor = prefs.edit();
        id_periodista = prefs.getString("id_periodista",null);
        if(id_periodista != null) {
            return id_periodista;
        }
        else
        {
            return "";
        }

    }


    public void  Borrar(Context context)
    {
        prefs =context.getSharedPreferences("periodista", Context.MODE_PRIVATE);
        editor = prefs.edit();
        editor.clear();
        editor.commit();

    }

}
