package com.example.cv.select;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;

public class InProgRecyclerViewAdapter extends RecyclerView.Adapter<InProgRecyclerViewAdapter.MyViewHolder> {

    Context mContext;
    List<CompParticipantsInfo> mData;
    Cursor mCursor;

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
    public void onBindViewHolder(@NonNull final InProgRecyclerViewAdapter.MyViewHolder holder, final int position) {
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        TextView textView = (TextView) v.findViewById(R.id.tvinprogparticontact);
                        Context context=v.getContext();
                        String ContactNo= textView.getText().toString();
                        Toast.makeText(mContext, "Contact No:" +textView.getText().toString(), Toast.LENGTH_SHORT).show();
                        Intent intent= new Intent(v.getContext(), ShowIndividualPartiData.class);
                        intent.putExtra("ContactNo", ContactNo);
                        context.startActivity(intent);
                    }
                });
       /* if(!mCursor.moveToPosition(position)){
            return;
        }*/
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
        private View.OnClickListener onItemClickListener;
        private DatabaseHelperRP databaseHelperRP;

        public void setItemClickListener(View.OnClickListener clickListener) {
            onItemClickListener = clickListener;
        }

        public MyViewHolder(View itemView){
            super(itemView);
            itemView.setTag(this);
            itemView.setOnClickListener(onItemClickListener);



            tvinprogpartiname=(TextView) itemView.findViewById(R.id.tvinprogpartiname);
            tvinprogparticontact=(TextView) itemView.findViewById(R.id.tvinprogparticontact);

        }
    }
}
