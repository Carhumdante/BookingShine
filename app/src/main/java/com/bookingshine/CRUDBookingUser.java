package com.bookingshine;

import android.content.Intent;
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

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class CRUDBookingUser extends DialogFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_c_r_u_d_booking_user, container, false);
        final EditText NameUS = view.findViewById(R.id.editTextNameCRUD);
        final EditText LNameUS = view.findViewById(R.id.editTextLNCRUD);
        final EditText EmailUS = view.findViewById(R.id.editTextEmailCRUD);
        final EditText DateUS = view.findViewById(R.id.editTextDateCRUD);

        Button btn = view.findViewById(R.id.btnScheduleU);
        DAOUserSchedule dao = new DAOUserSchedule();
        ScheduleUser SU_edit = (ScheduleUser)getActivity().getIntent().getSerializableExtra("EDIT");
        if(SU_edit !=null){
            NameUS.setText(SU_edit.getNameU());
            LNameUS.setText(SU_edit.getLNameU());
            EmailUS.setText(SU_edit.getEmailU());
            DateUS.setText(SU_edit.getDateU());
        }
        btn.setOnClickListener(v -> {
                ScheduleUser SU = new ScheduleUser(NameUS.getText().toString(), LNameUS.getText().toString(), EmailUS.getText().toString(), DateUS.getText().toString());
                if(SU_edit==null) {
                    dao.add(SU).addOnSuccessListener(suc ->
                    {
                            Toast.makeText(getContext(), "Scheduling Sucsessfull!", Toast.LENGTH_SHORT).show();
                    }).addOnFailureListener(er ->
                        {
                            Toast.makeText(getContext(), "Scheduling Error!!!", Toast.LENGTH_SHORT).show();
                    });
                }
                else{
                    HashMap<String,Object>hashMap=new HashMap<>();
                    hashMap.put("Name",NameUS.getText().toString());
                    hashMap.put("Last Name",LNameUS.getText().toString());
                    hashMap.put("Email",EmailUS.getText().toString());
                    hashMap.put("Date",DateUS.getText().toString());
                    dao.update(SU_edit.getKey(),hashMap).addOnSuccessListener(suc ->
                    {
                        Toast.makeText(getContext(), "Scheduling Sucsessfull!", Toast.LENGTH_SHORT).show();
                        getActivity().finish();
                    }).addOnFailureListener(er ->
                    {
                        Toast.makeText(getContext(), "Scheduling Error!!!", Toast.LENGTH_SHORT).show();
                    });
                }
        });
        return view;
    }

}