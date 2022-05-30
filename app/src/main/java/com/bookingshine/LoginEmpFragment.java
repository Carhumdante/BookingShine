package com.bookingshine;

import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LoginEmpFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginEmpFragment extends Fragment {

    private FirebaseAuth mAuth;
    private EditText editTextEmailU;
    private EditText getEditTextPasswordU;
    private Button loginuser1;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public LoginEmpFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LoginEmpFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LoginEmpFragment newInstance(String param1, String param2) {
        LoginEmpFragment fragment = new LoginEmpFragment();
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
        View view = inflater.inflate(R.layout.fragment_login_emp, container, false);
        Button login = view.findViewById(R.id.BLoginEmp);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_loginEmpFragment_to_homeBusiness);
            }
        });
        Button register = view.findViewById(R.id.BRegisterEmp);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_loginEmpFragment_to_signinbussines2);
            }
        });

        mAuth = FirebaseAuth.getInstance();
        editTextEmailU = view.findViewById(R.id.usernameInput);
        getEditTextPasswordU = view.findViewById(R.id.passwordInput);
        loginuser1 = view.findViewById(R.id.BLoginEmp);
        loginuser1.setOnClickListener(new View.OnClickListener() {
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

        return view;
    }
}