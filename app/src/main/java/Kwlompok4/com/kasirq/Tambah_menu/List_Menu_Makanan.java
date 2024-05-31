package Kwlompok4.com.kasirq.Tambah_menu;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import Kwlompok4.com.kasirq.R;

public class List_Menu_Makanan extends AppCompatActivity {

    private static final int REQUEST_PERMISSION_WRITE = 1001;
    FloatingActionButton floatingActionButton, floatingActionButtonDownload;
    RecyclerView list_makanan;
    DatabaseReference database;
    private MenuAdapter adapter;
    private ArrayList<MenuEntitas> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_menu_makanan);

        floatingActionButton = findViewById(R.id.floatingActionButton);
        floatingActionButtonDownload = findViewById(R.id.floatingActionButtonDownload);
        list_makanan = findViewById(R.id.list_makanan);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent add = new Intent(List_Menu_Makanan.this, input_menu_makanan.class);
                startActivity(add);
            }
        });

        floatingActionButtonDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkPermission()) {
                    downloadData();
                } else {
                    requestPermission();
                }
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

    private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(List_Menu_Makanan.this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        return result == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_PERMISSION_WRITE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_PERMISSION_WRITE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                downloadData();
            } else {
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void downloadData() {
        // Create an Excel file and write data
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    HSSFWorkbook workbook = new HSSFWorkbook();
                    HSSFSheet sheet = workbook.createSheet("Menu Data");

                    HSSFRow row = sheet.createRow(0);
                    row.createCell(0).setCellValue("Menu Name");
                    row.createCell(1).setCellValue("Menu Cost");
                    row.createCell(2).setCellValue("Menu Price");
                    row.createCell(3).setCellValue("Menu Variant");
                    row.createCell(4).setCellValue("Image URL");

                    for (int i = 0; i < arrayList.size(); i++) {
                        MenuEntitas menu = arrayList.get(i);
                        row = sheet.createRow(i + 1);
                        row.createCell(0).setCellValue(menu.getMenuName());
                        row.createCell(1).setCellValue(menu.getMenuCost());
                        row.createCell(2).setCellValue(menu.getMenuPrice());
                        row.createCell(3).setCellValue(menu.getMenuVariant());
                        row.createCell(4).setCellValue(menu.getImageUrl());
                    }

                    // Get the directory for the app's private files
                    File file = new File(getExternalFilesDir(null), "MenuData.xls");
                    FileOutputStream os = new FileOutputStream(file);
                    workbook.write(os);
                    workbook.close();
                    os.close();

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(List_Menu_Makanan.this, "Data downloaded to " + file.getAbsolutePath(), Toast.LENGTH_LONG).show();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(List_Menu_Makanan.this, "Failed to download data", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        }).start();
    }

}
