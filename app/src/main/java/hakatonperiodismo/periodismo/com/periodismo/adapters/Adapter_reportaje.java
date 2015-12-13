package hakatonperiodismo.periodismo.com.periodismo.adapters;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

import hakatonperiodismo.periodismo.com.periodismo.objetos.Reportaje;
import hakatonperiodismo.periodismo.com.periodismo.R;

public class Adapter_reportaje extends ArrayAdapter<Reportaje> {
	private Context contexto;
	private ArrayList<Reportaje> reportaje;
    private ArrayList<Reportaje> reportaje_auxiliar;




	private String url;
	/*Funciones fun=new Funciones();
	VariablesUrl var= new VariablesUrl();

	Visitas_controlador vc;*/

    public Adapter_reportaje(Context context, int textViewResourceId, ArrayList<Reportaje> reportaje) {
        super(context, textViewResourceId, reportaje);
        this.contexto = context;
        this.reportaje =reportaje;


        this.reportaje_auxiliar = new ArrayList<Reportaje>();
        this.reportaje_auxiliar.addAll(reportaje);

    }
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Holders.Holder_listado_noticias holder;
		if (convertView == null) {
			LayoutInflater vi = (LayoutInflater)contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = vi.inflate(R.layout.list_reportaje,parent, false);
			
			holder = new Holders.Holder_listado_noticias();
            holder.lbl_titulo=(TextView)convertView.findViewById(R.id.lbl_titulo);
			holder.lbl_tags = (TextView) convertView.findViewById(R.id.lbl_tags);
            holder.lbl_fecha = (TextView) convertView.findViewById(R.id.lbl_fecha);

         	convertView.setTag(holder);
		} else {
			holder = (Holders.Holder_listado_noticias) convertView.getTag();
		}
		Reportaje muestra =reportaje.get(position);
		final Reportaje solo_un_item=muestra;
		holder.lbl_titulo.setText(Html.fromHtml("<strong>"+contexto.getString(R.string.titulo_noticia)+"</strong>"+muestra.getTitulo()));
        holder.lbl_tags.setText(Html.fromHtml("<strong>"+contexto.getString(R.string.tags)+"</strong>"+muestra.getTags()));
        holder.lbl_fecha.setText(Html.fromHtml("<strong>"+contexto.getString(R.string.fecha)+"</strong>"+muestra.getFecha()));

		return convertView;	
	}


    // Filter Class
    public void filter(String charText) {

        reportaje.clear();
        if (charText.length() == 0) {
            reportaje.addAll(reportaje_auxiliar);
        } else {
            for (Reportaje Wd : reportaje_auxiliar) {

                if(Wd.getTitulo().toLowerCase(Locale.getDefault())
                        .contains(charText)
                        ||
                        Wd.getTags().toLowerCase(Locale.getDefault())
                                .contains(charText)
                     )

                {
                    reportaje.add(Wd);
                }
            }
        }
        notifyDataSetChanged();

    }

}
