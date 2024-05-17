package Kwlompok4.com.kasirq;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationBarView;

import Kwlompok4.com.kasirq.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {


    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
//        EdgeToEdge.enable(this);
        setContentView(binding.getRoot());
        replaceFragment(new Dashboard());


        // Cek apakah ada flag yang dikirimkan dari EditProfile
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("target_fragment")) {
            String targetFragment = intent.getStringExtra("target_fragment");
            // Navigasi ke Profile jika flag dikirimkan dari EditProfile
            if (targetFragment.equals("profile")) {
                replaceFragment(new Profile());
            }
        }



        binding.navMenu.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int menuItemId=item.getItemId();

                if(menuItemId==R.id.home){
                    replaceFragment(new Dashboard());
                    return true;
                }else if(menuItemId==R.id.plus){
                    replaceFragment(new TambahMenu());
                    return true;
                }else if(menuItemId==R.id.profile){
                    replaceFragment(new Profile());
                    return true;
                }
                return true;
            }
        });



    }

    private  void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction= fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout,fragment);
        fragmentTransaction.commit();
    }
}