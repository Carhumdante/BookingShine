package com.bookingshine;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class CRUDBookingUser extends DialogFragment {
    Button Schedule;
    EditText Name, Lname, Email, datazo;
    private FirebaseFirestore mFirestore;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_c_r_u_d_booking_user, container, false);
        mFirestore = FirebaseFirestore.getInstance();
        Name = view.findViewById(R.id.editTextNameCRUD);
        Lname = view.findViewById(R.id.editTextLNCRUD);
        Email = view.findViewById(R.id.editTextEmailCRUD);
        datazo = view.findViewById(R.id.editTextDateCRUD);
        Schedule = view.findViewById(R.id.btnProfile);

        Schedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameS = Name.getText().toString().trim();
                String LameS = Lname.getText().toString().trim();
                String emailS = Email.getText().toString().trim();
                String dateS = datazo.getText().toString().trim();

                if(nameS.isEmpty() && LameS.isEmpty()&&emailS.isEmpty()&&dateS.isEmpty()){
                    Toast.makeText(getContext(),"Is necesary to fill the fields",Toast.LENGTH_SHORT).show();
                }
                else{
                    postSchedule(nameS, LameS, emailS, dateS);
                }
            }
        });

        return view;
    }
    private void postSchedule(String nameS, String LameS, String emailS, String dateS){
        Map<String, Object>map = new HashMap<>();
        map.put("Name", nameS);
        map.put("LastName", LameS);
        map.put("Email", emailS);
        map.put("SDate", dateS);

        mFirestore.collection("schdules").add(map).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Toast.makeText(getContext(), "Scheduling Sucsessfull!", Toast.LENGTH_SHORT).show();
                getDialog().dismiss();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getContext(), "Scheduling Error!", Toast.LENGTH_SHORT).show();

            }
        });
    }
}