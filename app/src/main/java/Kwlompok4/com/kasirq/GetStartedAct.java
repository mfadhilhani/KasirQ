package Kwlompok4.com.kasirq;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

public class GetStartedAct extends AppCompatActivity {

    Animation ttb,btt;
    Button btn_login,btn_new_account_create;

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_started);

        //load animation
        ttb = AnimationUtils.loadAnimation(this, R.anim.ttb);
        btt = AnimationUtils.loadAnimation(this, R.anim.btt);

        btn_login = findViewById(R.id.btn_login);
        btn_new_account_create = findViewById(R.id.btn_new_account_create);
        textView = findViewById(R.id.textView);

        //jalankan animasi
        textView.startAnimation(ttb);
        btn_login.startAnimation(btt);
        btn_new_account_create.startAnimation(btt);

        btn_login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent gologin = new Intent(GetStartedAct.this, Login.class);
                    startActivity(gologin);
                }
        });

        btn_new_account_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goregister = new Intent(GetStartedAct.this, Register.class);
                startActivity(goregister);
            }
        });
    }
}