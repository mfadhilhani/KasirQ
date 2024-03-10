package Kwlompok4.com.kasirq;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SuccessRegisterAct extends AppCompatActivity {

    Button btn_login_success;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success_register);
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.white));
        btn_login_success = findViewById(R.id.btn_login_success);


        btn_login_success.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gologinsuccess = new Intent(SuccessRegisterAct.this, Login.class);
                startActivity(gologinsuccess);
            }
        });
    }
}