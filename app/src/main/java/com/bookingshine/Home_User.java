package com.bookingshine;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class  Home_User extends Fragment {
    ImageView imageView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_user, container, false);
        ImageView calendar = view.findViewById(R.id.calendarCRUD);
        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CRUDBookingUser dialog = new CRUDBookingUser();
                dialog.show(getFragmentManager(),"Dialog call");
            }
        });
        ImageView profile = view.findViewById(R.id.profile);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_home_User_to_perfilUserFragment2);
            }
        });
        ImageView suggestion = view.findViewById(R.id.suggestion);
        suggestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_home_User_to_complaintsandsugestions);
            }
        });

        ImageView search1 = view.findViewById(R.id.search);
        search1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_home_User_to_search2);
            }
        });
        return view;
    }

}