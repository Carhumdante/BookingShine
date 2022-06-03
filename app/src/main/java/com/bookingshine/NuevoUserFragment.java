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
    private EditText editTextEmailU ;
    private EditText editTextPasswordU;
    private Button RegUser1;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mAuth = FirebaseAuth.getInstance();
        View view = inflater.inflate(R.layout.fragment_nuevo_user, container, false);
        editTextEmailU = view.findViewById(R.id.editTextEmailU);
        editTextPasswordU = view.findViewById(R.id.editTextPasswordU);
        RegUser1 = view.findViewById(R.id.RegisterUser1);

        RegUser1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = editTextEmailU.getText().toString().trim();
                String pass = editTextPasswordU.getText().toString().trim();

                mAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(getActivity(),"Register Successfull!", Toast.LENGTH_SHORT).show();
                            Navigation.findNavController(view).navigate(R.id.action_nuevoUserFragment_to_loginUserFragment);
                        }
                        else {
                            Log.d("Tag","Register failed!",task.getException());
                            Toast.makeText(getActivity(),"Register failed!", Toast.LENGTH_SHORT).show();

                        }
                    }
                });
            }
        });
        return view;
    }
}