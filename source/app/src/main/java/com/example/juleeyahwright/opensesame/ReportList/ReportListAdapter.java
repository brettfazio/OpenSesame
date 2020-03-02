package com.example.juleeyahwright.opensesame.ReportList;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.juleeyahwright.opensesame.R;

public class ReportListAdapter extends RecyclerView.Adapter<ReportListAdapter.ReportViewHolder> {

    public static class ReportViewHolder extends RecyclerView.ViewHolder {
        public TextView mReportTitle;
        public TextView mReportCategory;
        public TextView mReportDistance;

        public ReportViewHolder(View itemView) {
            super(itemView);
            mReportTitle = itemView.findViewById(R.id.report_name);
            mReportCategory = itemView.findViewById(R.id.report_category);
            mReportDistance= itemView.findViewById(R.id.report_distance);
        }
    }

    // TODO
    public ReportListAdapter(String s1, String s2, String s3) {

    }

    @NonNull
    @Override
    public ReportViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.report_list_item,
                parent, false);
        ReportViewHolder vh = new ReportViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ReportViewHolder holder, int position) {
        holder.mReportTitle.setText("Report Name");
        holder.mReportCategory.setText("Category: Some category here");
        holder.mReportDistance.setText("Distance: Some distance here");
    }

    @Override
    public int getItemCount() {
        return 1;
    }
}
