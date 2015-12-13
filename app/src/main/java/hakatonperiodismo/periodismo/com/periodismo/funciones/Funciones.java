package hakatonperiodismo.periodismo.com.periodismo.funciones;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by debbie on 23-07-15.
 */
public class Funciones {

    /////////////////función para ver si un mail es válido o no
    public static boolean isEmailValid(String email) {
        boolean isValid = false;
        String expression = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        CharSequence inputStr = email;
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches()) {
            isValid = true;
        }
        return isValid;
    }

    ///////////////función para verificar si está prendido el wifi o el gprs
    public static boolean verificaConexion(Context ctx) {
        boolean bConectado = false;
        ConnectivityManager connec = (ConnectivityManager) ctx
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] redes = connec.getAllNetworkInfo();
        for (int i = 0; i < 2; i++) {
            // ¿Tenemos conexión? ponemos a true
            if (redes[i].getState() == NetworkInfo.State.CONNECTED) {
                bConectado = true;
            }
        }
        return bConectado;
    }
    ///////////////////funcion para convertir un string en double
    public Double convertir_de_string_a_double(String cadena) {
        Double cadena_a_convertir = Double.parseDouble(cadena);
        return cadena_a_convertir;
    }
    ///////////////////funcion para convertir un double a string
    public String convertir_de_double_a_string(Double dato_decimal) {
        String decimal_a_convertir=Double.toString(dato_decimal);
        return decimal_a_convertir;
    }

    public void cargar_llamada(String telefono,Context context) {
        Intent i;
        i = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + telefono));
        context.startActivity(i);
    }

    public void mandar_mail(String correo,Context context) {
        Intent i;
        i = new Intent(Intent.ACTION_SEND);
        i.setType("message/rfc822");
        i.putExtra(Intent.EXTRA_EMAIL, new String[] { correo });
        i.putExtra(Intent.EXTRA_SUBJECT, "");
        i.putExtra(Intent.EXTRA_TEXT, "");
        try {
            context.startActivity(Intent.createChooser(i, "Mandar correo mediante:"));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(context, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
        }
    }


    public void mandar_sms_whatsapp(String numero,Context context) {
        Uri uri = Uri.parse("smsto:" + numero);
        Intent i;
        i = new Intent(Intent.ACTION_SENDTO, uri);
        i.setPackage("com.whatsapp");
        try {
            context.startActivity(Intent.createChooser(i, ""));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(context,	"Debe instalar la aplicación", Toast.LENGTH_SHORT).show();
        }
    }


    public int get_width(Activity act)
    {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        act.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int width = displaymetrics.widthPixels;
        return  width;
    }

    public int get_height(Activity  act)
    {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        act.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int height = displaymetrics.heightPixels;
        return  height;
    }

    String caso;
    Integer alto, ancho;
    Double ratio;
    Integer real_ancho;
    public String get_density(Activity act)
    {
        DisplayMetrics dm = new DisplayMetrics();
        real_ancho=(int) Math.ceil(dm.widthPixels * (dm.densityDpi / 160.0));;

        alto=get_height(act);
        ancho=get_width(act);

        Double d_alto=(Double.parseDouble(alto.toString())/1);
        Double d_ancho=(Double.parseDouble(ancho.toString())/1);


        DisplayMetrics displaymetrics = new DisplayMetrics();
        float density= act.getResources().getDisplayMetrics().density;

        Double densidad_alto=d_alto/density;
        Double densidad_ancho=d_ancho/density;

        System.out.println("***************************************");
        System.out.println("ancho_dp: " +densidad_ancho+" alto_dp "+densidad_alto);
        System.out.println("***************************************");

        if(densidad_ancho>0&&densidad_ancho<500)
        {
            caso="sw400dp";
        }
        else if(densidad_ancho>=500&&densidad_ancho<600)
        {
            caso="normal";
        }

        else if(densidad_ancho>=600&&densidad_ancho<720)
        {
            caso="sw600dp";
        }
        else if(densidad_ancho>=720&&densidad_ancho<1000)
        {
            caso="sw720dp";
        }
        else if(densidad_ancho>=1000)
        {
            caso="sw1000dp";
        }
        else
        {

        }
/*    if(density==0.75)
    {
    caso="ldpi";
    }
    else if(density==1.0)
    {
        caso="mdpi";
    }
    else if(density==1.5)
    {
        caso="hdpi";
    }
    else if(density==2.0)
    {
        caso="xhdpi";
    }
    else if(density==3.0)
    {
        caso="xxhdpi";
    }
    else if(density==4.0)
    {
        caso="xxxhdpi";
    }*/
      /*
	     DisplayMetrics metrics = new DisplayMetrics();
	     act.getWindowManager().getDefaultDisplay().getMetrics(metrics);
	      switch(metrics.densityDpi)
	        {
	        case DisplayMetrics.DENSITY_LOW:
	        	caso="ldpi";
	            break;

	        case DisplayMetrics.DENSITY_MEDIUM:
	            caso="mdpi";
	            break;

	        case DisplayMetrics.DENSITY_HIGH:
	            caso="hdpi";
	            break;

	        case DisplayMetrics.DENSITY_XHIGH:
	        	caso="xhdpi";
	            break;

	        case DisplayMetrics.DENSITY_XXHIGH:
	        	caso="xxhdpi";
	            break;

	        case DisplayMetrics.DENSITY_XXXHIGH:
	        	caso="xxxhdpi";
	            break;
	        }*/
        return caso;
    }

    public int ifElseTesting(String a){
        //return null;
        return (a== null)? 0: a.length();
    }


    Bitmap bitmap;
    public void  whatsapp(Activity act, View v)
    {
        bitmap = takeScreenshot(v);
        saveBitmap(bitmap);

    }

    public Bitmap takeScreenshot(View view) {
        view.getRootView();
        view.setDrawingCacheEnabled(true);
        return view.getDrawingCache();
    }



    public void saveBitmap(Bitmap bitmap) {

        File sdCard = Environment.getExternalStorageDirectory();
        File directory = new File(sdCard.getAbsolutePath() + "/VISMED");
        if (!directory.isDirectory())
            directory.mkdirs();
        String foto = Environment.getExternalStorageDirectory()+ "/VISMED/visita" + ".jpg";
        File fot = new File(foto);

        FileOutputStream fos;
        try {

            if (fot != null) {
                fos = new FileOutputStream(fot);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
                fos.flush();
                fos.close();
            }
        } catch (FileNotFoundException e) {
            Log.e("GREC", e.getMessage(), e);
        } catch (IOException e) {
            Log.e("GREC", e.getMessage(), e);
        }
    }

}
