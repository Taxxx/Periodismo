package hakatonperiodismo.periodismo.com.periodismo.app;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import hakatonperiodismo.periodismo.com.periodismo.R;

public class MenuPrincipal extends Activity {

    LinearLayout fila_arriba_izquierda;
    LinearLayout fila_arriba_derecha;
    LinearLayout fila_abajo_derecha;
    LinearLayout fila_abajo_izquierda;

    Intent intent ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);


        fila_arriba_derecha=(LinearLayout)this.findViewById(R.id.fila_arriba_derecha);
        fila_arriba_derecha.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                intent=new Intent(MenuPrincipal.this, ListadoNoticias.class);
                startActivity(intent);


            }
        });


        fila_arriba_izquierda=(LinearLayout)this.findViewById(R.id.fila_arriba_izquierda);
        fila_arriba_izquierda.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                intent=new Intent(MenuPrincipal.this, Noticia.class);
                startActivity(intent);


            }
        });


        fila_abajo_izquierda=(LinearLayout)this.findViewById(R.id.fila_abajo_izquierda);
        fila_abajo_izquierda.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                intent=new Intent(MenuPrincipal.this, Inicial.class);
                startActivity(intent);


            }
        });


    }




}
