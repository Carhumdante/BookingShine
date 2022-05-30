package com.bookingshine;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link reasonreschedule_user#newInstance} factory method to
 * create an instance of this fragment.
 */
public class reasonreschedule_user extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public reasonreschedule_user() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment reasonreschedule_user.
     */
    // TODO: Rename and change types and number of parameters
    public static reasonreschedule_user newInstance(String param1, String param2) {
        reasonreschedule_user fragment = new reasonreschedule_user();
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
        View view = inflater.inflate(R.layout.fragment_reasonreschedule_user, container, false);

        final EditText complaint_input = view.findViewById(R.id.InputTextComplaints);
        final EditText description_input = view.findViewById(R.id.InputTextDescription);
        Button btn = view.findViewById(R.id.signinbutton);
        DAOBusinessReschedule dao = new DAOBusinessReschedule();
        btn.setOnClickListener(v->
        {
            BusinessReschedule resch = new BusinessReschedule(complaint_input.getText().toString(), description_input.getText().toString());
            dao.add(resch).addOnSuccessListener(suc ->
            {
                Toast.makeText(getActivity(), getString(R.string.msgToastSuccess), Toast.LENGTH_SHORT).show();
            }).addOnFailureListener(er ->
            {
                Toast.makeText(getActivity(), "" + er.getMessage(), Toast.LENGTH_SHORT).show();
            });
        });

        return view;
    }
}