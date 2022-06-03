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

public class LoginUserFragment extends Fragment {
    private FirebaseAuth mAuth;
    private EditText editTextEmailU;
    private EditText getEditTextPasswordU;
    private Button LogUser1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login_user, container, false);

        mAuth = FirebaseAuth.getInstance();
        editTextEmailU = view.findViewById(R.id.editTextEmailUL);
        getEditTextPasswordU = view.findViewById(R.id.editTextPasswordUL);
        LogUser1 = view.findViewById(R.id.LogUser1);
        LogUser1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = editTextEmailU.getText().toString().trim();
                String password = getEditTextPasswordU.getText().toString().trim();

                mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Navigation.findNavController(view).navigate(R.id.action_loginUserFragment_to_home_User);
                        }
                        else {
                            Log.d("Tag","Email or password incorrect", task.getException());
                            Toast.makeText(getActivity(),"Email or password incorrect.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        Button RegUser1 = view.findViewById(R.id.RegUser1);
        RegUser1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_loginUserFragment_to_nuevoUserFragment);
            }
        });
        return view;
    }

}