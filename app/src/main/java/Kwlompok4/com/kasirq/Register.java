package Kwlompok4.com.kasirq;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Register extends AppCompatActivity {

    LinearLayout btn_back;
    Button btn_continue;
    EditText password,username;
    DatabaseReference database;

//    String USERNAME_KEY = "usernamekey";
//    String username_key = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        password = findViewById(R.id.password);
        username = findViewById(R.id.username);
        btn_back = findViewById(R.id.btn_back);
        btn_continue = findViewById(R.id.btn_continue);

        //menyimpan data ke database
        database = FirebaseDatabase.getInstance().getReferenceFromUrl("https://kasirq-fc9f9-default-rtdb.firebaseio.com/");



        // tombol kembali
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goback = new Intent(Register.this, Login.class);
                startActivity(goback);
            }
        });


        // tombol continue
        btn_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Mengambil nilai dari EditText
                String usernameText = username.getText().toString();
                String passwordText = password.getText().toString();

                if (usernameText.isEmpty() || passwordText.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Ada Data Yang Masih Kosong!!", Toast.LENGTH_SHORT).show();
                } else {
                    // Mendapatkan referensi ke "Users" di Firebase Database
                    database = FirebaseDatabase.getInstance().getReference("Users");

                    // Menyimpan data pengguna baru ke Firebase Database
                    database.child(usernameText).child("username").setValue(usernameText);
                    database.child(usernameText).child("password").setValue(passwordText);

                    Toast.makeText(getApplicationContext(), "Register Berhasil", Toast.LENGTH_SHORT).show();

                    // Pindah halaman
                    Intent gocontinue = new Intent(Register.this, SuccessRegisterAct.class);
                    startActivity(gocontinue);
                }
            }
        });


    }



}