package Kwlompok4.com.kasirq;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import androidx.appcompat.app.AppCompatActivity;

public class Struk extends AppCompatActivity {

    private TextView dateTextView;
    private TextView timeTextView;
    private SimpleDateFormat dateFormat;
    private SimpleDateFormat timeFormat;

    Button btn_kembali;

     LinearLayout btn_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_struk);

        dateTextView = findViewById(R.id.date_text_view);
        timeTextView = findViewById(R.id.time_text_view);
        dateFormat = new SimpleDateFormat("EEEE, dd MMMM yyyy", Locale.getDefault());
        timeFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());

        updateTime();

        btn_back = findViewById(R.id.btn_back);

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goDashbord = new Intent(Struk.this, Checkout.class);
                startActivity(goDashbord);
            }
        });

        btn_kembali = findViewById(R.id.btn_kembali);

        btn_kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goDashbord = new Intent(Struk.this, MainActivity.class);
                startActivity(goDashbord);
            }
        });

    }

    private void updateTime() {
        Calendar calendar = Calendar.getInstance();
        String dateString = dateFormat.format(calendar.getTime());
        String timeString = timeFormat.format(calendar.getTime());
        dateTextView.setText(dateString);
        timeTextView.setText(timeString);

        // Update time every second
        dateTextView.postDelayed(this::updateTime, 1000);
    }
}
