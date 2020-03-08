package com.example.juleeyahwright.opensesame.ReportDetail;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.juleeyahwright.opensesame.Message.MessageReference;
import com.example.juleeyahwright.opensesame.R;

public class ReportDetailAdapter extends RecyclerView.Adapter<ReportDetailAdapter.ReportDetailViewHolder> {

    private MessageReference[] mDataset;

    public static class ReportDetailViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;
        public ReportDetailViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.reportDetailRecyclerTextView);
        }
    }

    public ReportDetailAdapter(MessageReference[] myDataset) {
        this.mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ReportDetailAdapter.ReportDetailViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.report_detail_activity_item, parent, false);

        ReportDetailViewHolder vh = new ReportDetailViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ReportDetailViewHolder holder, int position) {
        holder.textView.setText(mDataset[position].getContents());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.length;
    }

}
