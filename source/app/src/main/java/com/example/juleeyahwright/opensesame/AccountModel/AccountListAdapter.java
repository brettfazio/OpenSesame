package com.example.juleeyahwright.opensesame.AccountModel;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.juleeyahwright.opensesame.R;

import java.util.ArrayList;

public class AccountListAdapter extends RecyclerView.Adapter<AccountListAdapter.AccountViewHolder>{
    private ArrayList<AccountListItem> reportArrayList;

    public static class AccountViewHolder extends RecyclerView.ViewHolder {
        public TextView mReportTitle;
        public TextView mReportLocation;
        public TextView mReportDistance;
        public TextView mReportDescription;

        public AccountViewHolder(View itemView) {
            super(itemView);
            mReportTitle = itemView.findViewById(R.id.report_name);
            mReportLocation = itemView.findViewById(R.id.report_location);
            mReportDistance = itemView.findViewById(R.id.report_distance);
            mReportDescription = itemView.findViewById(R.id.report_description);
        }
    }

    public AccountListAdapter(ArrayList<AccountListItem> reportArray) {
        this.reportArrayList = reportArray;
    }

    @NonNull
    @Override
    public AccountViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_account_list_item,
                parent, false);
        AccountViewHolder vh = new AccountViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull AccountViewHolder holder, int position) {
        AccountListItem currItem = reportArrayList.get(position);
        holder.mReportTitle.setText(currItem.getReportName());
        holder.mReportLocation.setText("Location: " + currItem.getReportLocation());
        holder.mReportDistance.setText("Distance: " + currItem.getReportDistance());
        holder.mReportDescription.setText("Description: " + currItem.getReportDescription());
    }

    @Override
    public int getItemCount() {
        return reportArrayList.size();
    }
}
