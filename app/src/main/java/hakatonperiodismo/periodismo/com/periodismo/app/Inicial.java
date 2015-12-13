package hakatonperiodismo.periodismo.com.periodismo.app;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import hakatonperiodismo.periodismo.com.periodismo.R;
import hakatonperiodismo.periodismo.com.periodismo.SharedPreferences.SharedPreferencesClass;
import hakatonperiodismo.periodismo.com.periodismo.async.OperacionesDescargaNoticias;


public class Inicial extends Activity {

    Button altas;
    SharedPreferencesClass spc= new SharedPreferencesClass();
    Intent intent;
    String token;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicial);

token= spc.Consultar_si_hay_registro(Inicial.this);
        if(spc.Consultar_si_hay_registro(Inicial.this).equals(""))
        {
            intent= new Intent(Inicial.this, Login.class);
            startActivity(intent);
            finish();
        }
        else
        {



            //        nombre=(EditText)this.findViewById(R.id.txt_nombre);
            altas=(Button) this.findViewById(R.id.btn_altas);
            altas.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new OperacionesDescargaNoticias(Inicial.this, token).execute();
                }
            });



        }




    }




}
