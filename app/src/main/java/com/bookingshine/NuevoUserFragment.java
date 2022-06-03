package com.bookingshine;

import android.os.Bundle;
import android.util.Log;
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

public class NuevoUserFragment extends Fragment {

    private FirebaseAuth mAuth;
    private EditText username_input;
    private EditText password_input;
    private Button RegUser1;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters

    public NuevoUserFragment() {
        // Required empty public constructor
    }

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
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mAuth = FirebaseAuth.getInstance();
        View view = inflater.inflate(R.layout.fragment_nuevo_user, container, false);
        username_input = view.findViewById(R.id.editTextEmailU);
        password_input = view.findViewById(R.id.editTextPasswordU);
        RegUser1 = view.findViewById(R.id.RegUser1);

        RegUser1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = username_input.getText().toString().trim();
                String password = password_input.getText().toString().trim();

                mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(getActivity(),"User created sucsesfully", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Log.d("Tag","Error creating user", task.getException());
                            Toast.makeText(getActivity(),"Error creating user", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                Navigation.findNavController(view).navigate(R.id.action_nuevoUserFragment_to_loginUserFragment);
            }
        });
        return view;
    }
}