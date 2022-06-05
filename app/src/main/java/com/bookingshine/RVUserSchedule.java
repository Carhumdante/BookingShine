package com.bookingshine;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;

public class RVUserSchedule extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    ArrayList<ScheduleUser> list = new ArrayList<>();
    public RVUserSchedule(Context ctx){
        this.context = ctx;
    }
    public  void setItems(ArrayList<ScheduleUser>SU){
        list.addAll(SU);
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_item,parent,false);
        return new ScheduleUserVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ScheduleUserVH vh1 = (ScheduleUserVH) holder;
        ScheduleUser SU = list.get(position);
        vh1.txt_name.setText(SU.getNameU());
        vh1.txt_LastName.setText(SU.getLNameU());
        vh1.txt_email.setText(SU.getEmailU());
        vh1.txt_date.setText(SU.getDateU());
        vh1.txt_option.setOnClickListener(v ->
        {
                PopupMenu popupMenu = new PopupMenu(context,vh1.txt_option);
                popupMenu.inflate(R.menu.options_menu);
                popupMenu.setOnMenuItemClickListener(item ->
                {
                        switch (item.getItemId()){
                            case R.id.menu_edit:
                                Intent intent = new Intent(context,CRUDBookingUser.class);
                                intent.putExtra("EDIT", SU);
                                context.startActivity(intent);
                                break;
                            case R.id.menu_remove:
                                DAOUserSchedule dao = new DAOUserSchedule();
                                dao.remove(SU.getKey()).addOnSuccessListener(suc -> {
                                    Toast.makeText(context, "Sucsessfull removed!",Toast.LENGTH_SHORT).show();
                                    notifyItemRemoved(position);
                                }).addOnFailureListener(er->
                                {
                                    Toast.makeText(context, "Failed remove!",Toast.LENGTH_SHORT).show();

                                });
                                break;
                        }
                        return false;
                });
                popupMenu.show();
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
