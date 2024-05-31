package Kwlompok4.com.kasirq;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {

    TextView btn_new_account;
    Button btn_login;

    EditText username, password;
    DatabaseReference database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btn_new_account = findViewById(R.id.btn_new_account);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);

        btn_new_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goregister = new Intent(Login.this, Register.class);
                startActivity(goregister);
            }
        });

        btn_login = findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Mengambil nilai dari EditText
                String usernameText = username.getText().toString();
                String passwordText = password.getText().toString();

                database = FirebaseDatabase.getInstance().getReference("Users");

                if (usernameText.isEmpty() || passwordText.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Username atau Password tidak boleh kosong", Toast.LENGTH_SHORT).show();
                } else {
                    database.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.child(usernameText).exists()) {
                                String savedPassword = snapshot.child(usernameText).child("password").getValue(String.class);
                                if (savedPassword != null && savedPassword.equals(passwordText)) {
                                    Toast.makeText(getApplicationContext(), "Login Berhasil", Toast.LENGTH_SHORT).show();

                                    // Simpan username ke SharedPreferences
                                    SharedPreferences sharedPreferences = getSharedPreferences("login", MODE_PRIVATE);
                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                    editor.putString("username", usernameText);
                                    editor.apply();

                                    Intent masuk = new Intent(Login.this, MainActivity.class);
                                    startActivity(masuk);

                                    // Membersihkan EditText
                                    username.setText("");
                                    password.setText("");
                                } else {
                                    Toast.makeText(getApplicationContext(), "Password Salah", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(getApplicationContext(), "Username tidak terdaftar", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Toast.makeText(getApplicationContext(), "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

    }
}
