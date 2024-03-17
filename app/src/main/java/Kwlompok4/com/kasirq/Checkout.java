package Kwlompok4.com.kasirq;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Checkout extends AppCompatActivity {

    EditText namaPelanggan;
    Button btn_pesanSekarang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        namaPelanggan = findViewById(R.id.namaPelanggan);
        btn_pesanSekarang = findViewById(R.id.btn_pesanSekarang);

        btn_pesanSekarang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gostruk = new Intent(Checkout.this, Struk.class);
                gostruk.putExtra("namaPelanggan", namaPelanggan.getText().toString());
                startActivity(gostruk);
            }
        });
    }
}