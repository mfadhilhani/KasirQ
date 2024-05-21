package Kwlompok4.com.kasirq.Profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import Kwlompok4.com.kasirq.MainActivity;
import Kwlompok4.com.kasirq.R;

public class EditProfile extends AppCompatActivity {


    LinearLayout btn_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_edit_profile);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });

        btn_back = findViewById(R.id.btn_back);

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goprofil = new Intent(EditProfile.this, MainActivity.class);
                // Mengirimkan flag bahwa navigasi kembali dari EditProfile ke Profile
                goprofil.putExtra("target_fragment", "profile");
                startActivity(goprofil);
            }
        });
    }
}