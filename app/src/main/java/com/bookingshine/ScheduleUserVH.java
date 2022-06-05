package com.bookingshine;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ScheduleUserVH extends RecyclerView.ViewHolder {
    public TextView txt_name, txt_LastName, txt_email, txt_date, txt_option;
    public ScheduleUserVH(@NonNull View itemView) {
        super(itemView);
        txt_name = itemView.findViewById(R.id.txt_name);
        txt_LastName = itemView.findViewById(R.id.txt_lastname);
        txt_email = itemView.findViewById(R.id.txt_email);
        txt_date = itemView.findViewById(R.id.txt_date);
        txt_option = itemView.findViewById(R.id.txt_option);
    }
}
