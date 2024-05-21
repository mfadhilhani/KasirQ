package Kwlompok4.com.kasirq.Tambah_menu;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.webkit.MimeTypeMap;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import java.io.FileNotFoundException;
import java.io.InputStream;
import Kwlompok4.com.kasirq.R;

public class input_menu_makanan extends AppCompatActivity {
    private ImageView pic_foto, btn_add_foto;
    private ImageButton imageButtonAddVariantq;
    private EditText editTextMenuName, editTextMenuPrice, editTextMenuCost, editTextMenuVariant;
    private ActivityResultLauncher<Intent> galleryLauncher;
    private Uri imageUri;
    private DatabaseReference database;
    private StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_menu_makanan);

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
                        Toast.makeText(input_menu_makanan.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            } else {
                Toast.makeText(input_menu_makanan.this, "Tidak Ada Gambar", Toast.LENGTH_SHORT).show();
            }
        });

        btn_add_foto.setOnClickListener(view -> {
            Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
            galleryLauncher.launch(galleryIntent);
        });

        imageButtonAddVariantq.setOnClickListener(view -> {
            if (editTextMenuName.getText().toString().isEmpty() ||
                    editTextMenuPrice.getText().toString().isEmpty() ||
                    editTextMenuCost.getText().toString().isEmpty() ||
                    editTextMenuVariant.getText().toString().isEmpty()) {
                Toast.makeText(input_menu_makanan.this, "Semua field harus diisi", Toast.LENGTH_SHORT).show();
            } else {
                String menuName = editTextMenuName.getText().toString();
                String menuPrice = editTextMenuPrice.getText().toString();
                String menuCost = editTextMenuCost.getText().toString();
                String menuVariant = editTextMenuVariant.getText().toString();

                StorageReference fileReference = storageReference.child(menuName + "." + getFileExtension(imageUri));
                fileReference.putFile(imageUri)
                        .addOnSuccessListener(taskSnapshot -> fileReference.getDownloadUrl().addOnSuccessListener(uri -> {
                            String imageUrl = uri.toString();
                            Menu menu = new Menu(menuName, menuPrice, menuCost, menuVariant, imageUrl);
                            database.child(menuName).setValue(menu)
                                    .addOnCompleteListener(task -> {
                                        if (task.isSuccessful()) {
                                            Toast.makeText(input_menu_makanan.this, "Menu Berhasil Disimpan", Toast.LENGTH_SHORT).show();
                                            clearForm();
                                            // Pindah ke halaman List_Menu_Makanan
                                            Intent intent = new Intent(input_menu_makanan.this, List_Menu_Makanan.class);
                                            startActivity(intent);
                                            finish();
                                        } else {
                                            Toast.makeText(input_menu_makanan.this, "Gagal Menyimpan Menu", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                        }))
                        .addOnFailureListener(e -> Toast.makeText(input_menu_makanan.this, e.getMessage(), Toast.LENGTH_SHORT).show());
            }
        });
    }

    private String getFileExtension(Uri uri) {
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }

    private void clearForm() {
        editTextMenuName.setText("");
        editTextMenuPrice.setText("");
        editTextMenuCost.setText("");
        editTextMenuVariant.setText("");
        pic_foto.setImageResource(R.drawable.icon_nopic);
        editTextMenuName.requestFocus();
        imageUri = null;
    }
}
