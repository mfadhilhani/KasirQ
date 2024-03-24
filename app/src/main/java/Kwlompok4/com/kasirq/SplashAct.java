package Kwlompok4.com.kasirq;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Handler;


public class SplashAct extends AppCompatActivity {

    Animation app_splash;
    ImageView app_logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //load animation
        app_splash = AnimationUtils.loadAnimation(this, R.anim.app_splash);

        //load element
        app_logo = findViewById(R.id.app_logo);

        //jalankan animasi
        app_logo.startAnimation(app_splash);


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
        }, 1500);
    }
}