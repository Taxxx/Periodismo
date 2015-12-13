package hakatonperiodismo.periodismo.com.periodismo.app;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

import hakatonperiodismo.periodismo.com.periodismo.R;
import hakatonperiodismo.periodismo.com.periodismo.adapters.Adapter_reportaje;
import hakatonperiodismo.periodismo.com.periodismo.objetos.Reportaje;

public class ListadoNoticias extends Activity  {

    ListView lista_reportaje;
    Adapter_reportaje adapter;
    ArrayList<Reportaje> reportaje= new ArrayList<Reportaje>();
    EditText buscador;
    ImageView volver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_noticias);



        reportaje.add( new Reportaje("1","Titulo noticia 1","c","d","e","f","g","h","i"));
        reportaje.add( new Reportaje("2","Titulo noticia 2","c","d","e","f","g","h","i"));
        reportaje.add( new Reportaje("3","Titulo noticia 3","c","d","e","f","g","h","i"));
        reportaje.add( new Reportaje("4","Titulo noticia 4","c","d","e","f","g","h","i"));
        reportaje.add( new Reportaje("5","Titulo noticia 5","c","d","e","f","g","h","i"));
        reportaje.add( new Reportaje("11","Titulo noticia 6","c","d","e","f","g","h","i"));



        lista_reportaje=(ListView)this.findViewById(R.id.lv_noticias);

        volver=(ImageView)this.findViewById(R.id.btn_volver);
        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });

        llenar_listado();
    }
    public void llenar_listado()
    {

        buscador= (EditText) findViewById(R.id.txt_buscador);
        adapter=new Adapter_reportaje(this,R.layout.list_reportaje,reportaje);
        lista_reportaje.setAdapter(adapter);

        lista_reportaje.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String id_reportaje = ((Reportaje) parent.getItemAtPosition(position)).getId();
                Intent resultIntent = new Intent(ListadoNoticias.this,Noticia.class );
                resultIntent.putExtra("id_reportaje",id_reportaje);
                setResult(Activity.RESULT_OK, resultIntent);
                finish();

            }
        });


        buscador.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {

                String text = buscador.getText().toString().toLowerCase(Locale.getDefault());
                adapter.filter(text);
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                          int arg3) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub
            }
        });


    }




}