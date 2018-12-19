package com.example.user.newspapervendor.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.easing.linear.Linear;
import com.example.user.newspapervendor.Bill_Status;
import com.example.user.newspapervendor.activities.Bill_Details;
import com.example.user.newspapervendor.activities.R;

import java.util.List;

public class BillStatusAdapter extends RecyclerView.Adapter<BillStatusAdapter.BillStatusViewHolder> {
    private Context mCtx;
    private List<Bill_Status> billStatuses;

    public BillStatusAdapter(Context mCtx, List<Bill_Status> billStatuses) {
        this.mCtx = mCtx;
        this.billStatuses = billStatuses;
    }

    @NonNull
    @Override
    public BillStatusViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.layout_bill_status, null);
        return new BillStatusViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final BillStatusViewHolder billStatusViewHolder, int i) {


        Bill_Status bill_status = billStatuses.get(i);
        // billStatusViewHolder.cust_name.setText("raja");
/*        Log.e("Tag",bill_sta)*/

      //  try {
            billStatusViewHolder.cust_name.setText(bill_status.getCust_name());
            billStatusViewHolder.mobile.setText(bill_status.getMobile());
            billStatusViewHolder.cash_collected.setText("12"+ "% Collected");
            billStatusViewHolder.cash_pending.setText("Rs." + "123" + " collection pending");
           // billStatusViewHolder.cash_collected.setText(String.valueOf(bill_status.getCash_collected()) + "% Collected");
           // billStatusViewHolder.progressBar.setProgress(bill_status.getCash_collected());
           // billStatusViewHolder.cash_pending.setText("Rs." + String.valueOf(bill_status.getPending_cash()) + " collection pending");
            billStatusViewHolder.progressBar.getProgressDrawable().setColorFilter(Color.BLUE, android.graphics.PorterDuff.Mode.SRC_IN);
//        } catch (Exception e){
//            Toast.makeText(mCtx, e.getMessage(), Toast.LENGTH_SHORT).show();
//        }
        billStatusViewHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //   Toast.makeText(mCtx, "You are about to move next msgs", Toast.LENGTH_SHORT).show();
                mCtx.startActivity(new Intent(mCtx, Bill_Details.class));
            }
        });

        billStatusViewHolder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toast.makeText(mCtx, "You are about to move next Screen", Toast.LENGTH_SHORT).show();
                mCtx.startActivity(new Intent(mCtx, Bill_Details.class));
            }
        });
    }


    @Override
    public int getItemCount() {
        return billStatuses.size();
    }

    public class BillStatusViewHolder extends RecyclerView.ViewHolder {
        private TextView cust_name, mobile, cash_collected, cash_pending;
        private LinearLayout linearLayout;
        private ImageView imageView;
        private ProgressBar progressBar;

        public BillStatusViewHolder(@NonNull final View itemView) {
            super(itemView);
            cust_name = itemView.findViewById(R.id.bill_status_customer_name);
            mobile = itemView.findViewById(R.id.bill_status_mobile_number);
            cash_collected = itemView.findViewById(R.id.bill_status_cash_collected);
            cash_pending = itemView.findViewById(R.id.bill_status_pending_cash);
            imageView = itemView.findViewById(R.id.iv_bill_status_right);
            linearLayout = itemView.findViewById(R.id.linearlayout_billStatus);
            progressBar = itemView.findViewById(R.id.progress_bar_bill);
            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }
}
