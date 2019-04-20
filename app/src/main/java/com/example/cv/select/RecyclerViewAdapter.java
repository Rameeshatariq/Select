package com.example.cv.select;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    Context mContext;
    List<CompParticipantsInfo> mData;

    public RecyclerViewAdapter(Context mContext, List<CompParticipantsInfo> mData){
        this.mContext=mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View v;
       v= LayoutInflater.from(mContext).inflate(R.layout.item_completedparticipants,parent,false);
       MyViewHolder viewHolder = new MyViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView textView = (TextView) v.findViewById(R.id.tvcompparticontact);
                Context context=v.getContext();
                String ContactNo= textView.getText().toString();
                Toast.makeText(mContext, "Contact No:" +textView.getText().toString(), Toast.LENGTH_SHORT).show();
                Intent intent= new Intent(v.getContext(), ShowIndividualPartiData.class);
                intent.putExtra("ContactNo", ContactNo);
                context.startActivity(intent);
            }
        });

        holder.tvcomppartiname.setText(mData.get(position).getParticipantName());
        holder.tvcompparticontact.setText(mData.get(position).getParticipantContact());

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView tvcomppartiname;
        private TextView tvcompparticontact;

        public MyViewHolder(View itemView){
            super(itemView);

            tvcomppartiname=(TextView) itemView.findViewById(R.id.tvcomppartiname);
            tvcompparticontact=(TextView) itemView.findViewById(R.id.tvcompparticontact);



        }
    }
}
