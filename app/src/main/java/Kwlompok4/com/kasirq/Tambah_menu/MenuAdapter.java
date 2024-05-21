package Kwlompok4.com.kasirq.Tambah_menu;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.List;

import Kwlompok4.com.kasirq.R;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MyViewHolder> {
    private List<MenuEntitas> mlist;
    private Context context;
    private DatabaseReference database = FirebaseDatabase.getInstance().getReference("Menu");

    public MenuAdapter(List<MenuEntitas> mlist, Context context) {
        this.mlist = mlist;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_makanan, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final MenuEntitas item = mlist.get(position);
        holder.NamaMakanan.setText("Nama Menu : " + item.getMenuName());
        holder.Modal.setText("Modal : " + item.getMenuPrice());
        holder.Harga_jual.setText("Harga Jual : " + item.getMenuCost());
        holder.Varian.setText("Varian : " + item.getMenuVariant());
        Glide.with(context).load(item.getImageUrl()).into(holder.menuImage);

        holder.btnHapus.setOnClickListener(view -> {
            // Hapus gambar dari Firebase Storage
            StorageReference imageRef = FirebaseStorage.getInstance().getReferenceFromUrl(item.getImageUrl());
            imageRef.delete().addOnSuccessListener(aVoid -> {
                // Hapus data dari Firebase Database setelah gambar dihapus
                database.child(item.getMenuName()).setValue(null).addOnSuccessListener(aVoid1 -> {
                    // Beritahu pengguna bahwa item telah dihapus
                    Toast.makeText(context, "Menu dan gambarnya berhasil dihapus", Toast.LENGTH_SHORT).show();
                }).addOnFailureListener(e -> {
                    Toast.makeText(context, "Gagal menghapus menu dari database: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                });
            }).addOnFailureListener(e -> {
                Toast.makeText(context, "Gagal menghapus gambar dari penyimpanan: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            });
        });

        holder.btnEdit.setOnClickListener(view -> {
            String MenuName = item.getMenuName();
            String MenuCost = item.getMenuCost();
            String MenuPrice = item.getMenuPrice();
            String MenuVariant = item.getMenuVariant();
            String ImageUrl = item.getImageUrl();

            Intent edit = new Intent(context, EditDataMenu.class);
            edit.putExtra("MenuName", MenuName);
            edit.putExtra("MenuCost", MenuCost);
            edit.putExtra("MenuPrice", MenuPrice);
            edit.putExtra("MenuVariant", MenuVariant);
            edit.putExtra("ImageUrl", ImageUrl);

            context.startActivity(edit);
        });
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView NamaMakanan, Modal, Harga_jual, Varian;
        private ImageView menuImage;
        private Button btnHapus, btnEdit;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            NamaMakanan = itemView.findViewById(R.id.NamaMakanan);
            Modal = itemView.findViewById(R.id.Modal);
            Harga_jual = itemView.findViewById(R.id.Harga_jual);
            Varian = itemView.findViewById(R.id.Varian);
            menuImage = itemView.findViewById(R.id.pic_foto);
            btnHapus = itemView.findViewById(R.id.btnHapus);
            btnEdit = itemView.findViewById(R.id.btnEdit);
        }
    }
}
