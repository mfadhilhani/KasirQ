package Kwlompok4.com.kasirq.Tambah_menu;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;

import Kwlompok4.com.kasirq.R;

public class EditDataMenu extends AppCompatActivity {

    private ImageView pic_foto, btn_add_foto;
    private ImageButton imageButtonAddVariantq;
    private EditText editTextMenuName, editTextMenuPrice, editTextMenuCost, editTextMenuVariant;
    private ActivityResultLauncher<Intent> galleryLauncher;
    private Uri imageUri;
    private String MenuName, MenuPrice, MenuCost, MenuVariant, ImageUrl;
    private DatabaseReference database;
    private StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_data_menu);

        pic_foto = findViewById(R.id.pic_foto);
        editTextMenuName = findViewById(R.id.editTextMenuName);
        editTextMenuPrice = findViewById(R.id.editTextMenuPrice);
        editTextMenuCost = findViewById(R.id.editTextMenuCost);
        editTextMenuVariant = findViewById(R.id.editTextMenuVariant);
        btn_add_foto = findViewById(R.id.btn_add_foto);
        imageButtonAddVariantq = findViewById(R.id.imageButtonAddVariantq);

        database = FirebaseDatabase.getInstance().getReference("Menu");
        storageReference = FirebaseStorage.getInstance().getReference("MenuImages");

        galleryLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if (result.getResultCode() == RESULT_OK) {
                Intent data = result.getData();
                if (data != null) {
                    imageUri = data.getData();
                    try {
                        InputStream imageStream = getContentResolver().openInputStream(imageUri);
                        Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                        pic_foto.setImageBitmap(selectedImage);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                        Toast.makeText(EditDataMenu.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            } else {
                Toast.makeText(EditDataMenu.this, "Tidak Ada Gambar", Toast.LENGTH_SHORT).show();
            }
        });

        btn_add_foto.setOnClickListener(view -> {
            Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
            galleryLauncher.launch(galleryIntent);
        });

        if (getIntent().hasExtra("MenuName") && getIntent().hasExtra("MenuPrice") &&
                getIntent().hasExtra("MenuCost") && getIntent().hasExtra("MenuVariant") &&
                getIntent().hasExtra("ImageUrl")) {
            MenuName = getIntent().getStringExtra("MenuName");
            MenuPrice = getIntent().getStringExtra("MenuPrice");
            MenuCost = getIntent().getStringExtra("MenuCost");
            MenuVariant = getIntent().getStringExtra("MenuVariant");
            ImageUrl = getIntent().getStringExtra("ImageUrl");

            editTextMenuName.setText(MenuName);
            editTextMenuPrice.setText(MenuPrice);
            editTextMenuCost.setText(MenuCost);
            editTextMenuVariant.setText(MenuVariant);
            Glide.with(this).load(ImageUrl).into(pic_foto); // Load image from URL
        }

        imageButtonAddVariantq.setOnClickListener(view -> {
            String MenuNameBaru = editTextMenuName.getText().toString();
            String MenuPriceBaru = editTextMenuPrice.getText().toString();
            String MenuCostBaru = editTextMenuCost.getText().toString();
            String MenuVariantBaru = editTextMenuVariant.getText().toString();

            if (MenuNameBaru.isEmpty() || MenuPriceBaru.isEmpty() ||
                    MenuCostBaru.isEmpty() || MenuVariantBaru.isEmpty()) {
                Toast.makeText(EditDataMenu.this, "Semua field harus diisi", Toast.LENGTH_SHORT).show();
                return;
            }

            if (imageUri != null) {
                StorageReference fileReference = storageReference.child(MenuNameBaru + "." + getFileExtension(imageUri));
                fileReference.putFile(imageUri)
                        .addOnSuccessListener(taskSnapshot -> fileReference.getDownloadUrl().addOnSuccessListener(uri -> {
                            ImageUrl = uri.toString();
                            updateMenu(MenuNameBaru, MenuPriceBaru, MenuCostBaru, MenuVariantBaru, ImageUrl);
                        }))
                        .addOnFailureListener(e -> Toast.makeText(EditDataMenu.this, e.getMessage(), Toast.LENGTH_SHORT).show());
            } else {
                updateMenu(MenuNameBaru, MenuPriceBaru, MenuCostBaru, MenuVariantBaru, ImageUrl);
            }
        });
    }

    private void updateMenu(String menuName, String menuPrice, String menuCost, String menuVariant, String imageUrl) {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("menuName", menuName);
        hashMap.put("menuPrice", menuPrice);
        hashMap.put("menuCost", menuCost);
        hashMap.put("menuVariant", menuVariant);
        hashMap.put("imageUrl", imageUrl);

        database.child(MenuName).updateChildren(hashMap).addOnSuccessListener(o -> {
            Toast.makeText(getApplicationContext(), "Update Berhasil", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getApplicationContext(), List_Menu_Makanan.class));
            finish();
        });
    }

    private String getFileExtension(Uri uri) {
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }
}
