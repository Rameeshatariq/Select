package com.example.cv.select;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class InProgRecyclerViewAdapter extends RecyclerView.Adapter<InProgRecyclerViewAdapter.MyViewHolder> {

    Context mContext;
    List<CompParticipantsInfo> mData;

    public InProgRecyclerViewAdapter(Context mContext, List<CompParticipantsInfo> mData){
        this.mContext=mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public InProgRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        v= LayoutInflater.from(mContext).inflate(R.layout.item_inprogressparticipants,parent,false);
        InProgRecyclerViewAdapter.MyViewHolder viewHolder = new InProgRecyclerViewAdapter.MyViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull InProgRecyclerViewAdapter.MyViewHolder holder, int position) {
        holder.tvinprogpartiname.setText(mData.get(position).getParticipantName());
        holder.tvinprogparticontact.setText(mData.get(position).getParticipantContact());

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView tvinprogpartiname;
        private TextView tvinprogparticontact;

        public MyViewHolder(View itemView){
            super(itemView);

            tvinprogpartiname=(TextView) itemView.findViewById(R.id.tvinprogpartiname);
            tvinprogparticontact=(TextView) itemView.findViewById(R.id.tvinprogparticontact);

        }
    }
}
