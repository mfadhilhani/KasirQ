package Kwlompok4.com.kasirq.Profile;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import Kwlompok4.com.kasirq.Profile.EditProfile;
import Kwlompok4.com.kasirq.Login;
import Kwlompok4.com.kasirq.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Profile#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Profile extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private DatabaseReference database;
    private TextView usernameprofile, passwordprofile;

    LinearLayout btn_edit;

    public Profile() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Profile.
     */
    // TODO: Rename and change types and number of parameters
    public static Profile newInstance(String param1, String param2) {
        Profile fragment = new Profile();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @SuppressLint("WrongViewCast")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        usernameprofile = view.findViewById(R.id.usernameprofile);
        passwordprofile = view.findViewById(R.id.passwordprofile);

        // Mengambil username dari SharedPreferences
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("login", getActivity().MODE_PRIVATE);
        String usernameText = sharedPreferences.getString("username", "");

        // Referensi ke database
        database = FirebaseDatabase.getInstance().getReference("Users").child(usernameText);

        // Mengambil data dari database
        database.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    String username = snapshot.child("username").getValue(String.class);
                    String password = snapshot.child("password").getValue(String.class);

                    usernameprofile.setText(username);
                    passwordprofile.setText(password);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle possible errors.
            }
        });

        btn_edit = view.findViewById(R.id.btn_edit);
        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Mengambil data dari TextView
                String currentUsername = usernameprofile.getText().toString();
                String currentPassword = passwordprofile.getText().toString();

                // Mengirim data ke EditProfile melalui Intent
                Intent btn_edit = new Intent(getActivity(), EditProfile.class);
                btn_edit.putExtra("username", currentUsername);
                btn_edit.putExtra("password", currentPassword);
                startActivity(btn_edit);
            }
        });


        LinearLayout btn_edit = view.findViewById(R.id.btn_edit);
        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Code for OpenPageFromFragment
                Intent btn_edit = new Intent(getActivity(), EditProfile.class);
                startActivity(btn_edit);
                //End Code for OpenPageFromFragment

                //Code for OpenFragmentFromFragment
//                getActivity().getSupportFragmentManager().beginTransaction()
//                        .replace(R.id.frame_layout, new FprofileFragment())
//                        .addToBackStack(null)
//                        .commit();
                //End Code for OpenFragmentFromFragment
            }
        });

        Button btn_logout = view.findViewById(R.id.btn_logout);
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Code for OpenPageFromFragment
                Intent btn_logout = new Intent(getActivity(), Login.class);
                startActivity(btn_logout);
                //End Code for OpenPageFromFragment

                //Code for OpenFragmentFromFragment
//                getActivity().getSupportFragmentManager().beginTransaction()
//                        .replace(R.id.frame_layout, new FprofileFragment())
//                        .addToBackStack(null)
//                        .commit();
                //End Code for OpenFragmentFromFragment
            }
        });

        return view;
    }
}
