package hakatonperiodismo.periodismo.com.periodismo.async;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import hakatonperiodismo.periodismo.com.periodismo.R;
import hakatonperiodismo.periodismo.com.periodismo.SharedPreferences.SharedPreferencesClass;
import hakatonperiodismo.periodismo.com.periodismo.app.Inicial;
import hakatonperiodismo.periodismo.com.periodismo.app.Login;
import hakatonperiodismo.periodismo.com.periodismo.app.MenuPrincipal;
import hakatonperiodismo.periodismo.com.periodismo.web.VariablesUrl;


public class OperacionesLogin extends AsyncTask<String, String, String>{
	private ProgressDialog DialogLogin;
	protected Activity activity;
	protected String email;
	protected String password;

	VariablesUrl var= new VariablesUrl();
	String link =var.function_login();
	String servidor;	
	String resultado;
	Intent intent;
	String toast;
    String token;
	SharedPreferencesClass spc=  new SharedPreferencesClass();
	
	public OperacionesLogin(Activity act, String email, String password)
	{
        super();
        this.activity=act;
        this.email=email;
        this.password=password;

	}

	protected void onPreExecute()
	{	super.onPreExecute();
		DialogLogin = new ProgressDialog(this.activity);
		DialogLogin.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
		DialogLogin.setMessage(activity.getString(R.string.enviando_datos));
		DialogLogin.show();	
	}
	
	protected String doInBackground(String... args) 
	{		
		

		try
		{
			DefaultHttpClient httpClient =new DefaultHttpClient();
			HttpPost httpPost= new HttpPost(link);		
			String json=devuelve_cadena_json();
			 System.out.println("************ "+json.toString());

			StringEntity stringEntity=  new StringEntity(json, "UTF-8");
			httpPost.setEntity(stringEntity);
			httpPost.setHeader("Content-type","application/json;");
			HttpResponse httpResponse= httpClient.execute(httpPost);		
			HttpEntity httpEntity=httpResponse.getEntity();
			resultado=convertStreamToString(httpEntity.getContent());
			JSONObject jsonObject=new JSONObject(resultado);
			System.out.println("$$$$$$$$$$$$$$$$ "+resultado.toString());
            token=jsonObject.getString("token");

            if(resultado.equals(token==null))
            {

                servidor="no";
            }
            else
            {
            spc.Guardar_Shared(activity, token);
            servidor="si";
            }


		}
		catch (NullPointerException e) 
		{
			servidor="problemas_de_conexion";
		} catch (JSONException e) {
			Log.e("log_tag", "Error parsing data "+e.toString());
			servidor="problemas_de_conexion";
			e.printStackTrace();
		} catch (Exception e) {
			servidor="problemas_de_conexion";
			e.printStackTrace();
		}
		
		return null;
	}
	protected void onPostExecute(String file_url)
	{



        if(servidor.equals("si")) {
            toast = activity.getString(R.string.datos_correctos);
            intent = new Intent(activity, MenuPrincipal.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            activity.finish();
            activity.startActivity(intent);
        }
        else
        {
            toast = activity.getString(R.string.datos_incorrectos);
            intent = new Intent(activity, Login.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            activity.finish();
            activity.startActivity(intent);
        }




		Toast anuncio=Toast.makeText(activity,toast,Toast.LENGTH_LONG);
		anuncio.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
		anuncio.show();
		DialogLogin.dismiss();
	}
	
	public String devuelve_cadena_json() throws Exception	
	{
		JSONObject jsonObject =  new JSONObject();					
		jsonObject.put("username",email);
		jsonObject.put("password",password);
		return jsonObject.toString();
	}

    private static String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
		
	
  }	

	
	