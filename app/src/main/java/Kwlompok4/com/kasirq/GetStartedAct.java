package Kwlompok4.com.kasirq;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GetStartedAct extends AppCompatActivity {

    Button btn_login,btn_new_account_create;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_started);

        btn_login = findViewById(R.id.btn_login);
        btn_new_account_create = findViewById(R.id.btn_new_account_create);

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