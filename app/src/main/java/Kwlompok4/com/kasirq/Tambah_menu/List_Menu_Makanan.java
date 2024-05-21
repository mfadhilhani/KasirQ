package Kwlompok4.com.kasirq.Tambah_menu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import Kwlompok4.com.kasirq.R;

public class List_Menu_Makanan extends AppCompatActivity {

    FloatingActionButton floatingActionButton;
    RecyclerView list_makanan;
    DatabaseReference database;
    private MenuAdapter adapter;  // Use your own adapter class
    private ArrayList<MenuEntitas> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_menu_makanan);

        floatingActionButton = findViewById(R.id.floatingActionButton);
        list_makanan = findViewById(R.id.list_makanan);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent add = new Intent(List_Menu_Makanan.this, input_menu_makanan.class);  // Corrected intent class reference
                startActivity(add);
            }
        });

        database = FirebaseDatabase.getInstance().getReference("Menu");
        list_makanan.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        list_makanan.setLayoutManager(layoutManager);
        list_makanan.setItemAnimator(new DefaultItemAnimator());
        tampilData();
    }

    private void tampilData() {
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                arrayList = new ArrayList<>();
                for (DataSnapshot item : snapshot.getChildren()) {
                    MenuEntitas Makan = new MenuEntitas();
                    Makan.setMenuName(item.child("menuName").getValue().toString());
                    Makan.setMenuCost(item.child("menuCost").getValue().toString());
                    Makan.setMenuPrice(item.child("menuPrice").getValue().toString());
                    Makan.setMenuVariant(item.child("menuVariant").getValue().toString());
                    Makan.setImageUrl(item.child("imageUrl").getValue().toString());
                    arrayList.add(Makan);
                }
                adapter = new MenuAdapter(arrayList, List_Menu_Makanan.this);
                list_makanan.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

    }
}
