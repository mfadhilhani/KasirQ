package Kwlompok4.com.kasirq;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Handler;


public class SplashAct extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.white));
    time();

    }
// Pindah halaman Splash Screen
    private void time(){
        new Timer().schedule(new TimerTask(){
           @Override
           public  void run(){
               Intent  gogetstarted = new Intent(SplashAct.this,GetStartedAct.class);
               startActivity(gogetstarted);
               finish();
           }
        }, 1000);
    }
}