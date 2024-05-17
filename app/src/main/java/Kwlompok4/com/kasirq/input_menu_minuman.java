package Kwlompok4.com.kasirq;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class input_menu_minuman extends AppCompatActivity {

    private ImageView pic_foto, btn_add_foto;
    private ImageButton imageButtonAddVariantq;
    private EditText editTextMenuName, editTextMenuPrice, editTextMenuCost, editTextMenuVariant;
    private ActivityResultLauncher<Intent> galleryLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_menu_minuman);


        pic_foto = findViewById(R.id.pic_foto);
        editTextMenuName = findViewById(R.id.editTextMenuName);
        editTextMenuPrice = findViewById(R.id.editTextMenuPrice);
        editTextMenuCost = findViewById(R.id.editTextMenuCost);
        editTextMenuVariant = findViewById(R.id.editTextMenuVariant);
        btn_add_foto = findViewById(R.id.btn_add_foto);
        imageButtonAddVariantq = findViewById(R.id.imageButtonAddVariantq);


        galleryLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if (result.getResultCode() == RESULT_OK) {
                Intent data = result.getData();
                if (data != null) {
                    Uri imageUri = data.getData();
                    try {
                        InputStream imageStream = getContentResolver().openInputStream(imageUri);
                        Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                        pic_foto.setImageBitmap(selectedImage);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                        Toast.makeText(input_menu_minuman.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            } else {
                Toast.makeText(input_menu_minuman.this, "Tidak Ada Gambar", Toast.LENGTH_SHORT).show();
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

                // Jika salah satu isian kosong
                Toast.makeText(input_menu_minuman.this, "Harap Lengkapi Semua Isian", Toast.LENGTH_SHORT).show();
            } else if (pic_foto.getDrawable() == null) {
                // Jika gambar belum dipilih
                Toast.makeText(input_menu_minuman.this, "Harap Pilih Gambar", Toast.LENGTH_SHORT).show();
            } else {
                AlertDialog.Builder builder = new AlertDialog.Builder(input_menu_minuman.this);
                builder.setMessage("Nama Menu : " + editTextMenuName.getText().toString() + "\n" +
                        "Modal : " + editTextMenuPrice.getText().toString() + "\n" +
                        "Harga Jual : " + editTextMenuCost.getText().toString() + "\n" +
                        "Varian : " + editTextMenuVariant.getText().toString());
                builder.setTitle("Input Menu Makanan");
                builder.setCancelable(false);
                builder.setPositiveButton("Ok", (dialog, id) -> {
                    dialog.cancel();
                    editTextMenuName.setText("");
                    editTextMenuPrice.setText("");
                    editTextMenuCost.setText("");
                    editTextMenuVariant.setText("");
                    pic_foto.setImageDrawable(null);
                    editTextMenuName.requestFocus();

                    // Switch to List_Menu_Makanan activity
                    Intent intent = new Intent(input_menu_minuman.this, List_Menu_Makanan.class);
                    startActivity(intent);
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });



    }
}