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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link signinbussines#newInstance} factory method to
 * create an instance of this fragment.
 */
public class signinbussines extends Fragment {

    private FirebaseAuth mAuth;
    private EditText editTextPasswordU;
    private EditText editTextEmailU;
    private Button RegUser1;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public signinbussines() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment signinbussines.
     */
    // TODO: Rename and change types and number of parameters
    public static signinbussines newInstance(String param1, String param2) {
        signinbussines fragment = new signinbussines();
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



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_signinbussines, container, false);

        TextView regisEmp = view.findViewById(R.id.signinbutton);
        regisEmp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_signinbussines2_to_homeBusiness);
            }
        });
        TextView recoverPass = view.findViewById(R.id.passresetbuss);
        recoverPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_signinbussines2_to_ressetpassword_bussines);
            }
        });

        mAuth = FirebaseAuth.getInstance();
        editTextEmailU = view.findViewById(R.id.username_input);
        editTextPasswordU = view.findViewById(R.id.password_input);
        RegUser1 = view.findViewById(R.id.signinbutton);

        RegUser1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = editTextEmailU.getText().toString().trim();
                String password = editTextPasswordU.getText().toString().trim();

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