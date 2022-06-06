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
    private EditText emailUR;
    private EditText passwordUR;
    private Button RegUser2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_nuevo_user, container, false);
        mAuth = FirebaseAuth.getInstance();
        emailUR = view.findViewById(R.id.EmailUR);
        passwordUR = view.findViewById(R.id.PassUR);

        RegUser2 = view.findViewById(R.id.RegUR);
        RegUser2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailUR1 = emailUR.getText().toString().trim();
                String passwordUR1 = passwordUR.getText().toString().trim();

                mAuth.createUserWithEmailAndPassword(emailUR1, passwordUR1).addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(getActivity(),"User created successfully", Toast.LENGTH_SHORT).show();
                            Navigation.findNavController(view).navigate(R.id.action_nuevoUserFragment_to_loginUserFragment);
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