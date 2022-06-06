package com.bookingshine;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class signinbussines extends Fragment {

    private FirebaseAuth mAuth;
    private EditText editTextPasswordU;
    private EditText editTextEmailU;
    private TextView RegUser1;

    @Override
    public void onCreate(Bundle savedInstanceState) {super.onCreate(savedInstanceState);}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_signinbussines, container, false);
        mAuth = FirebaseAuth.getInstance();
        editTextEmailU = view.findViewById(R.id.username_input);
        editTextPasswordU = view.findViewById(R.id.password_input);

        RegUser1 = view.findViewById(R.id.signinbutton);
        RegUser1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = editTextEmailU.getText().toString().trim();
                String password = editTextPasswordU.getText().toString().trim();

                mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(getActivity(),new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(getActivity(),"Business created successfully", Toast.LENGTH_SHORT).show();
                            Navigation.findNavController(view).navigate(R.id.action_signinbussines2_to_loginEmpFragment);
                        }
                        else {
                            Log.d("Tag","Error creating user", task.getException());
                            Toast.makeText(getActivity(),"Error creating user", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        return view;
    }
}