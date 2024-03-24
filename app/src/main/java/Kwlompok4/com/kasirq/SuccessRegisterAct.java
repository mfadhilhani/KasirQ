package Kwlompok4.com.kasirq;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class SuccessRegisterAct extends AppCompatActivity {

    Button btn_login_success;
    Animation app_splash,btt,ttb;
    ImageView icon_success;
    TextView app_subtitle,app_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success_register);
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.white));
        btn_login_success = findViewById(R.id.btn_login_success);

        icon_success = findViewById(R.id.icon_success);
        app_subtitle = findViewById(R.id.app_subtitle);
        app_title = findViewById(R.id.app_title);

        //load animation
        app_splash = AnimationUtils.loadAnimation(this, R.anim.app_splash);
        btt = AnimationUtils.loadAnimation(this, R.anim.btt);
        ttb = AnimationUtils.loadAnimation(this, R.anim.ttb);

        //jalanakan animasi
        btn_login_success.startAnimation(btt);
        icon_success.startAnimation(app_splash);
        app_title.startAnimation(ttb);
        app_subtitle.startAnimation(ttb);


        btn_login_success.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gologinsuccess = new Intent(SuccessRegisterAct.this, Login.class);
                startActivity(gologinsuccess);
            }
        });
    }
}