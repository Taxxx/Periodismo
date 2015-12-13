package hakatonperiodismo.periodismo.com.periodismo.app;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.Timer;
import java.util.TimerTask;

import hakatonperiodismo.periodismo.com.periodismo.R;
import hakatonperiodismo.periodismo.com.periodismo.SharedPreferences.SharedPreferencesClass;

public class SplashScreen extends  Activity{


        Timer timer;
        String token;

       SharedPreferencesClass spc= new SharedPreferencesClass();
@Override
protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);



        TimerTask timerTask = new TimerTask() {
public void run() {

        token = spc.Consultar_si_hay_registro(SplashScreen.this);

        if (!token.equals("")) {
        goToActivity(MenuPrincipal.class);
        timer.cancel();
        } else

        {
        goToActivity(Login.class);
        timer.cancel();

        }


        }
        };
        timer = new Timer();
        timer.scheduleAtFixedRate(timerTask, 1500, 1500);
        }

private void goToActivity(Class<? extends Activity> activityClass)
        {
        Intent newActivity = new Intent(this, activityClass);
        startActivity(newActivity);
        finish();
        }





        }