package hakatonperiodismo.periodismo.com.periodismo.app;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import hakatonperiodismo.periodismo.com.periodismo.R;
import hakatonperiodismo.periodismo.com.periodismo.async.OperacionesLogin;
import hakatonperiodismo.periodismo.com.periodismo.db_model.Controlador;
import hakatonperiodismo.periodismo.com.periodismo.funciones.Funciones;

public class Login extends Activity {

    EditText usuario,  contrasenhia;
    Button login;
    Funciones fun= new Funciones();
    public static Activity first;

    Controlador controlador;
    Integer contador;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        controlador= new Controlador(Login.this, "periodismo.sqlite");
        usuario=(EditText)this.findViewById(R.id.txt_usuario);
        contrasenhia=(EditText)this.findViewById(R.id.txt_contrasenhia);
        login=(Button)this.findViewById(R.id.btn_login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


             /*   Intent i = new Intent(Login.this,MenuPrincipal.class);
                finish();
                startActivity(i);*/

               new OperacionesLogin(Login.this, usuario.getText().toString(), contrasenhia.getText().toString()).execute();

                /*contador=controlador.select_login_usuario(usuario.getText().toString(), contrasenhia.getText().toString());
                if(contador==1)
                {
                  Toast.makeText(Login.this, getString(R.string.datos_correctos),Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(Login.this,Inicial.class);
                    finish();
                    startActivity(i);


                }
                else
                {
                    Toast.makeText(Login.this, getString(R.string.datos_incorrectos),Toast.LENGTH_SHORT).show();
                }


             /*  if (!fun.verificaConexion(Login.this)) {
                    Toast toast = Toast.makeText(getApplicationContext(), R.string.necesita_conexion, Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
                    toast.show();
                    startActivity(new Intent(android.provider.Settings.ACTION_WIFI_SETTINGS));
                } else {

                    new OperacionesLogin(Login.this, usuario.getText().toString(), contrasenhia.getText().toString()).execute();
                }*/



            }
        });



    }



}
