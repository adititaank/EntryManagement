package com.example.entrymanagement.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.entrymanagement.R;
import com.example.entrymanagement.models.User;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CheckOutAdapter extends RecyclerView.Adapter<CheckOutAdapter.CheckOutAdapterViewHolder>{

    List<User> checkedInList;
    private OnClickListener listener;
    private Context context;
    public interface OnClickListener{
        void onClick(User user);
    }

    public CheckOutAdapter(Context context, List<User> userList, OnClickListener listener) {
        this.context = context;
        this.checkedInList = userList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CheckOutAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.checkout_item, parent, false);
        return new CheckOutAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CheckOutAdapterViewHolder holder, int position) {
        User user = checkedInList.get(position);
        holder.visitorPhone.setText(user.getVisitorPhone());
        holder.hostName.setText(user.getHostName());
        holder.visitorName.setText(user.getVisitorName());
    }

    @Override
    public int getItemCount() {
        if(checkedInList == null){
        return 0;}
        return checkedInList.size();
    }

    class CheckOutAdapterViewHolder extends RecyclerView.ViewHolder {
        TextView visitorName;
        TextView hostName;
        TextView visitorPhone;
        Button checkOut;

        public CheckOutAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            visitorName = itemView.findViewById(R.id.visitor_name);
            hostName = itemView.findViewById(R.id.host_name);
            visitorPhone = itemView.findViewById(R.id.phone);
            checkOut = itemView.findViewById(R.id.check_out_button);

            checkOut.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onClick(checkedInList.get(getAdapterPosition()));
                }
            });
        }
    }
}
