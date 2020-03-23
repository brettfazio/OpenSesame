package com.example.juleeyahwright.opensesame.AccountModel;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.juleeyahwright.opensesame.R;
import com.example.juleeyahwright.opensesame.ReportEditInfo.ReportEditInfoActivity;

import java.util.ArrayList;

public class AccountListAdapter extends RecyclerView.Adapter<AccountListAdapter.AccountViewHolder>{
    private static ArrayList<AccountListItem> reportArrayList;

    public AccountListAdapter(ArrayList<AccountListItem> reportArray) {
        this.reportArrayList = reportArray;
    }

    public static class AccountViewHolder extends RecyclerView.ViewHolder {
        public TextView mReportTitle;
        public TextView mReportLocation;
        public TextView mReportDistance;
        public TextView mReportDescription;
        public Button editReportButton;
        public Button deleteReportButton;
        public AccountListAdapter acl;

        public AccountViewHolder(View itemView) {
            super(itemView);
            mReportTitle = itemView.findViewById(R.id.report_name);
            mReportLocation = itemView.findViewById(R.id.report_location);
            mReportDistance = itemView.findViewById(R.id.report_distance);
            mReportDescription = itemView.findViewById(R.id.report_description);
            editReportButton = itemView.findViewById(R.id.edit_button);
            deleteReportButton = itemView.findViewById(R.id.delete_button);
            acl = new AccountListAdapter(reportArrayList);

        }

        public void onBindViewHolder(final AccountViewHolder accountViewHolder){
            accountViewHolder.editReportButton.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), ReportEditInfoActivity.class);
                    intent.putExtra("title", mReportTitle.getText());
                    intent.putExtra("location", mReportLocation.getText());
                    intent.putExtra("description", mReportDescription.getText());
                    v.getContext().startActivity(intent);
                }
            });

            accountViewHolder.editReportButton.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    reportArrayList.remove(v);
                    acl.notifyItemRemoved(accountViewHolder.getAdapterPosition());
                    acl.notifyItemRangeChanged(accountViewHolder.getAdapterPosition(), reportArrayList.size());
                }
            });
        }
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
