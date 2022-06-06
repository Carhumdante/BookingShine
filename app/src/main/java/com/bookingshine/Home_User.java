package com.bookingshine;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class  Home_User extends Fragment {
    ImageView imageView;
    SwipeRefreshLayout swipeRefreshLayout;
    RecyclerView recyclerView;
    RVUserSchedule adapter;
    DAOUserSchedule dao;
    boolean isLoading=false;
    String key = null;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_user, container, false);
        swipeRefreshLayout = view.findViewById(R.id.swip);
        recyclerView = view.findViewById(R.id.rv);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(manager);
        adapter = new RVUserSchedule(getActivity());
        recyclerView.setAdapter(adapter);
        dao = new DAOUserSchedule();
        loadData();
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                LinearLayoutManager linearLayoutManager = (LinearLayoutManager)recyclerView.getLayoutManager();
                int totalitem = linearLayoutManager.getItemCount();
                int lastVisible = linearLayoutManager.findLastCompletelyVisibleItemPosition();
                if(totalitem<lastVisible+3){
                    if (!isLoading){
                        isLoading=true;
                        loadData();
                    }
                }
            }
        });
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

    private void loadData() {
        swipeRefreshLayout.setRefreshing(true);
        dao.get(key).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<ScheduleUser> SUS = new ArrayList<>();
                for(DataSnapshot data : snapshot.getChildren()){
                    ScheduleUser SU = data.getValue(ScheduleUser.class);
                    SU.setKey(data.getKey());
                    SUS.add(SU);
                    key = data.getKey();
                }
                adapter.setItems(SUS);
                adapter.notifyDataSetChanged();
                isLoading = false;
                swipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

}