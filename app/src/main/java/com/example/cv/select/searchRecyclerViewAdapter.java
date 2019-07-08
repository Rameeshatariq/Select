package com.example.cv.select;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class searchRecyclerViewAdapter extends RecyclerView.Adapter<searchRecyclerViewAdapter.MyViewHolder> {

    Context mContext;
    List<CompParticipantsInfo> mData;

    public searchRecyclerViewAdapter(Context mContext, List<CompParticipantsInfo> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public searchRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(mContext).inflate(R.layout.item_inprogressparticipants, parent, false);
        searchRecyclerViewAdapter.MyViewHolder viewHolder = new searchRecyclerViewAdapter.MyViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull searchRecyclerViewAdapter.MyViewHolder holder, int position) {
        //holder.itemView.setOnClickListener(new View.OnClickListener() {
        //    @Override
        //   public void onClick(View v) {
        final TextView textView = (TextView) holder.tvcompparticontact;
        final Context context = holder.patient_detail.getContext();
        Button detail = (Button) holder.patient_detail;
        detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String ContactNo = textView.getText().toString();
                //Toast.makeText(mContext, "Contact No:" +textView.getText().toString(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(v.getContext(), ShowIndividualPartiData.class);
                intent.putExtra("ContactNo", ContactNo);
                context.startActivity(intent);
            }
        });
        Button tools = (Button) holder.tools;
        tools.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String ContactNo = textView.getText().toString();
                //  Toast.makeText(mContext, "Contact No:" +textView.getText().toString(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(v.getContext(), Tools.class);
                intent.putExtra("ContactNo", ContactNo);
                context.startActivity(intent);
            }
        });
        //  }
        //   });

        holder.tvcomppartiname.setText(mData.get(position).getParticipantName());
        holder.tvcompparticontact.setText(mData.get(position).getParticipantContact());
        holder.tvcomppartienroll.setText(mData.get(position).getParticipantEnroll());
        TextView enroll = (TextView) holder.tvcomppartienroll;
        if (enroll.getText().toString().equals("0")) {
            tools.setClickable(false);
        }


    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView tvcomppartiname;
        private TextView tvcompparticontact;
        private TextView tvcomppartienroll;
        private Button patient_detail, tools;

        public MyViewHolder(View itemView) {
            super(itemView);

            tvcomppartiname = (TextView) itemView.findViewById(R.id.tvinprogpartiname);
            tvcompparticontact = (TextView) itemView.findViewById(R.id.tvinprogparticontact);
            patient_detail = (Button) itemView.findViewById(R.id.patientDetail);
            tools = (Button) itemView.findViewById(R.id.tools);
            tvcomppartienroll = (TextView) itemView.findViewById(R.id.tvinprogpartienroll);

        }

    }
}
