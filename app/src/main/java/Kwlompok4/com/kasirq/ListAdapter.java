package Kwlompok4.com.kasirq;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class ListAdapter extends ArrayAdapter<List> {

   @SuppressLint("ResourceType")
   public ListAdapter(Context context, ArrayList<List> listArrayList){

       super(context, R.id.menu,listArrayList);
   }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

       List list = getItem(position);

       if (convertView == null) {

           convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_checkout, parent, false);

       }

        ImageView ImageView = convertView.findViewById(R.id.add);
        ImageView ImageView2 = convertView.findViewById(R.id.min);
        TextView Menu = convertView.findViewById(R.id.menu);
        TextView Varian = convertView.findViewById(R.id.varian);
        TextView Jumlah = convertView.findViewById(R.id.jumlah);
        TextView Rp = convertView.findViewById(R.id.rp);
        TextView Harga = convertView.findViewById(R.id.harga);

        ImageView.setImageResource(list.add);
        ImageView2.setImageResource(list.min);
        Menu.setText(list.menu);
        Menu.setText(list.varian);
        Menu.setText(list.jumlah);
        Menu.setText(list.rp);
        Menu.setText(list.harga);


        return super.getView(position, convertView, parent);
    }
}
