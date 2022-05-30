package com.bookingshine;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NuevoUserFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NuevoUserFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public NuevoUserFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NuevoUserFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NuevoUserFragment newInstance(String param1, String param2) {
        NuevoUserFragment fragment = new NuevoUserFragment();
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
    private String user = "";
    private String email = "";
    private String pass = "";

    FirebaseAuth mAuth;
    DatabaseReference mDatabase;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_nuevo_user, container, false);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        EditText editTextUsernameU = view.findViewById(R.id.editTextUsernameU);
        EditText editTextEmailU = view.findViewById(R.id.editTextEmailU);
        EditText editTextPasswordU = view.findViewById(R.id.editTextPasswordU);
        Button regisUser = view.findViewById(R.id.homeuser1);
        regisUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user = editTextUsernameU.getText().toString();
                email = editTextEmailU.getText().toString();
                pass = editTextPasswordU.getText().toString();

                if (!user.isEmpty() && !email.isEmpty() && !pass.isEmpty()){
                    if (pass.length() >= 6){
                        registerUser();
                        Navigation.findNavController(view).navigate(R.id.action_nuevoUserFragment_to_home_User2);
                    }
                    else {
                        Toast.makeText(getActivity(), "Password need 6 characters", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(getActivity(), "We need to fill the fields", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }

    private void registerUser (){
        mAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){

                    Map<String, Object> map = new HashMap<>();
                    map.put("user", user);
                    map.put("email", email);
                    map.put("pass", pass);

                    String id = mAuth.getCurrentUser().getUid();
                    mDatabase.child("Users").child(id).setValue(map);
                }
                else {
                    Toast.makeText(getActivity(), "Is not possible to register this user", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}