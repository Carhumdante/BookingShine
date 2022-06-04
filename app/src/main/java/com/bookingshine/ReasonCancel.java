package com.bookingshine;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ReasonCancel#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ReasonCancel extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ReasonCancel() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ReasonCancel.
     */
    // TODO: Rename and change types and number of parameters
    public static ReasonCancel newInstance(String param1, String param2) {
        ReasonCancel fragment = new ReasonCancel();
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
        View view = inflater.inflate(R.layout.fragment_reason_cancel, container, false);
        Button home = view.findViewById(R.id.btnSendCancel);

        final EditText complaint_input = view.findViewById(R.id.inputComplaints);
        final EditText description_input = view.findViewById(R.id.inputDescription);
        Button btn = view.findViewById(R.id.btnSendCancel);
        DAOBusinessCancel dao = new DAOBusinessCancel();
        btn.setOnClickListener(v->
        {
            BusinessCancel cancel = new BusinessCancel(complaint_input.getText().toString(), description_input.getText().toString());
            dao.add(cancel).addOnSuccessListener(suc ->
            {
                Toast.makeText(getActivity(), getString(R.string.msgToastSuccess), Toast.LENGTH_SHORT).show();
                home.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Navigation.findNavController(view).navigate(R.id.action_reasonCancel_to_homeBusiness);
                    }
                });
            }).addOnFailureListener(er ->
            {
                Toast.makeText(getActivity(), "" + er.getMessage(), Toast.LENGTH_SHORT).show();
            });
        });

        return view;
    }
}