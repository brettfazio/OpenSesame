package com.example.juleeyahwright.opensesame.ReportList;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.juleeyahwright.opensesame.R;
import java.util.ArrayList;

public class ReportListAdapter extends RecyclerView.Adapter<ReportListAdapter.ReportViewHolder> {
    private ArrayList<ReportListItem> reportArrayList;

    public static class ReportViewHolder extends RecyclerView.ViewHolder {
        public TextView mReportTitle;
        public TextView mReportLocation;
        public TextView mReportDistance;
        public TextView mReportDescription;

        public ReportViewHolder(View itemView) {
            super(itemView);
            mReportTitle = itemView.findViewById(R.id.report_name);
            mReportLocation = itemView.findViewById(R.id.report_location);
            mReportDistance = itemView.findViewById(R.id.report_distance);
            mReportDescription = itemView.findViewById(R.id.report_description);
        }
    }

    public ReportListAdapter(ArrayList<ReportListItem> itemArray) {
        this.reportArrayList = itemArray;
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
        ReportListItem currItem = reportArrayList.get(position);
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
