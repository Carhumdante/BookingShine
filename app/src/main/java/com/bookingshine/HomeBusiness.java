package com.bookingshine;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.annotations.Nullable;

import java.util.ArrayList;


public class HomeBusiness extends Fragment {
    // creating variables for our list view.
    private ListView coursesLV;

    // creating a new array list.
    //ArrayList<String> coursesArrayList;

    // creating a variable for database reference.
    DatabaseReference reference;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_business, container, false);

        Button profile = view.findViewById(R.id.btnCancel);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_homeBusiness_to_reasonCancel);
            }
        });

        Button cancel = view.findViewById(R.id.btnProfile);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_homeBusiness_to_profile_Bussines);
            }
        });

        Button reschedule = view.findViewById(R.id.btnReschedule);
        reschedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_homeBusiness_to_reasonreschedule_user);
            }
        });

        // initializing variables for listviews.
        //coursesLV = view.findViewById(R.id.listDates);

        // initializing our array list
        //coursesArrayList = new ArrayList<String>();

        // calling a method to get data from
        // Firebase and set data to list view
        //*initializeListView();

        return view;
    }


    /*private void initializeListView() {
        // creating a new array adapter for our list view.
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_dropdown_item_1line, coursesArrayList);

        // below line is used for getting reference
        // of our Firebase Database.
        FirebaseDatabase db =FirebaseDatabase.getInstance();
        reference = db.getReference(BusinessUsers.class.getSimpleName());
        // in below line we are calling method for add child event
        // listener to get the child of our database.
        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                // this method is called when new child is added to
                // our data base and after adding new child
                // we are adding that item inside our array list and
                // notifying our adapter that the data in adapter is changed.
                coursesArrayList.add(snapshot.getValue(String.class));
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                // this method is called when the new child is added.
                // when the new child is added to our list we will be
                // notifying our adapter that data has changed.
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                // below method is called when we remove a child from our database.
                // inside this method we are removing the child from our array list
                // by comparing with it's value.
                // after removing the data we are notifying our adapter that the
                // data has been changed.
                coursesArrayList.remove(snapshot.getValue(String.class));
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                // this method is called when we move our
                // child in our database.
                // in our code we are note moving any child.
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // this method is called when we get any
                // error from Firebase with error.
            }
        });
        // below line is used for setting
        // an adapter to our list view.
        coursesLV.setAdapter(adapter);
    }*/
}