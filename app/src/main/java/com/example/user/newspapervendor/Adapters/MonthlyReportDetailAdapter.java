package com.example.user.newspapervendor.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.user.newspapervendor.MonthlyReportDetail;
import com.example.user.newspapervendor.activities.R;

import java.util.List;

public class MonthlyReportDetailAdapter extends RecyclerView.Adapter<MonthlyReportDetailAdapter.MonthlyReportViewHolder> {
    private Context mCtx;
    private List<MonthlyReportDetail> mList;

    public MonthlyReportDetailAdapter() {
    }

    public MonthlyReportDetailAdapter(Context mCtx, List<MonthlyReportDetail> mList) {
        this.mCtx = mCtx;
        this.mList = mList;
    }

    @NonNull
    @Override
    public MonthlyReportViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.layout_monthly_report_detail, null);
        return new MonthlyReportViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MonthlyReportViewHolder viewHolder, int i) {
        MonthlyReportDetail monthlyReportDetail = mList.get(i);
        viewHolder.name.setText(monthlyReportDetail.getName());
        viewHolder.bill.setText(monthlyReportDetail.getBill());
        viewHolder.collection.setText(monthlyReportDetail.getCollection());
        viewHolder.papers.setText(monthlyReportDetail.getPapers());

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class MonthlyReportViewHolder extends RecyclerView.ViewHolder {
        TextView name, papers, bill, collection;

        public MonthlyReportViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.monthly_detail_c_name);
            papers = itemView.findViewById(R.id.monthly_detail_papers);
            bill = itemView.findViewById(R.id.monthly_detail_bill);
            collection = itemView.findViewById(R.id.monthly_detail_collection);
        }
    }
}
