package com.ermias.a20180101_ermiasabdi_nycschools;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NYCSchoolAdapter extends RecyclerView.Adapter<NYCSchoolAdapter.ViewHolderNYCSchool> {

    private List<String> data;
    private Context mContext;
    public NYCSchoolAdapter(Context context, List<String> data){
        this.data = data;
        mContext = context;
    }

    @NonNull
    @Override
    public ViewHolderNYCSchool onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.list_item, parent,false);
        ViewHolderNYCSchool viewHolderNYCSchool = new ViewHolderNYCSchool(view);
        return viewHolderNYCSchool;
    }

    @Override
    public void onBindViewHolder(NYCSchoolAdapter.ViewHolderNYCSchool holder, int position) {
        String data1 = data.get(position);
        holder.textView.setText(data1);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolderNYCSchool extends RecyclerView.ViewHolder {

        public TextView textView;
        public ViewHolderNYCSchool(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.data_textview);
        }
    }
}
