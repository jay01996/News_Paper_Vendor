package com.example.user.newspapervendor.Adapters;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.newspapervendor.BillDetail;
import com.example.user.newspapervendor.Bill_Status;
import com.example.user.newspapervendor.activities.R;
import com.google.firebase.database.core.Context;

import java.util.Calendar;
import java.util.List;

import static com.example.user.newspapervendor.BillDetail.BILL_COMPLETED;
import static com.example.user.newspapervendor.BillDetail.BILL_GENERATE;
import static com.example.user.newspapervendor.BillDetail.BILL_PENDING;

public class BillDetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private android.content.Context mCtx;
    private List<BillDetail> billDetailList;
    private String amt;
    private AlertDialog.Builder builder;
    private LayoutInflater layoutInflater;
    private View customView;
    private AlertDialog alertDialog;

    public BillDetailAdapter(android.content.Context mCtx, List<BillDetail> billDetailList) {
        this.mCtx = mCtx;
        this.billDetailList = billDetailList;
    }

    @Override
    public int getItemViewType(int position) {
        BillDetail billDetail = billDetailList.get(position);
        if (billDetail != null) {
            return billDetail.getType();
        }
        return 0;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view;
        switch (viewType) {
            case BILL_COMPLETED:
                view = LayoutInflater.from(mCtx).inflate(R.layout.layout_bill_detail, viewGroup, false);
                return new BillCompletedViewHolder(view);
            case BILL_PENDING:
                view = LayoutInflater.from(mCtx).inflate(R.layout.layout_pending_bill_detail, viewGroup, false);
                return new BillPendingViewHolder(view);
            case BILL_GENERATE:
                view = LayoutInflater.from(mCtx).inflate(R.layout.layout_generate_bill, viewGroup, false);
                return new BillGenerateViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, int i) {
        BillDetail billDetail = billDetailList.get(i);

        Calendar cal = Calendar.getInstance();
//        String[] monthName = {"January", "February", "March", "April", "May", "June", "July",
//                "August", "September", "October", "November",
//                "December"};
        int day = cal.get(Calendar.DAY_OF_MONTH);
        // String month = monthName[cal.get(Calendar.MONTH)];
        final int month = cal.get(Calendar.MONTH);
        int year = cal.get(Calendar.YEAR);
        StringBuilder date = new StringBuilder(day + "/" + (month + 1) + "/" + year);

        switch (billDetail.getType()) {
            case BILL_COMPLETED:
                ((BillCompletedViewHolder) holder).cust_name.setText(billDetail.getName());
                ((BillCompletedViewHolder) holder).papers.setText(billDetail.getPapers());
                ((BillCompletedViewHolder) holder).cash_collected.setText("Cash Amount Collected Rs. " + String.valueOf(billDetail.getCash_collected()) + " On " + date);
                ((BillCompletedViewHolder) holder).cash_advanced.setText("Advanced Payment Rs. " + String.valueOf(billDetail.getCash_advance()));
                //   Attach Popup menu On ImageView
                ((BillCompletedViewHolder) holder).iv_Option_menu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        PopupMenu popupMenu = new PopupMenu(mCtx, ((BillCompletedViewHolder) holder).iv_Option_menu);
                        popupMenu.inflate(R.menu.bill_collection_menu);
                        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                            @Override
                            public boolean onMenuItemClick(MenuItem item) {
                                //handling click Event
                                switch (item.getItemId()) {
                                    case R.id.menu_call:
                                        //   Intent callIntent = new Intent(Intent.ACTION_CALL);
                                        //   callIntent.setData(Uri.parse("tel:9125514509"));
                                        if (ActivityCompat.checkSelfPermission(mCtx, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                                            Toast.makeText(mCtx, "Permission Not Granted", Toast.LENGTH_SHORT).show();
                                            return false;
                                        }
                                        //mCtx.startActivity(callIntent);
                                        else {
                                            mCtx.startActivity(new Intent(Intent.ACTION_CALL).setData(Uri.parse("tel:9125514509")));
                                        }

                                        break;
                                    case R.id.menu_history:
                                        Toast.makeText(mCtx, "History Clicked", Toast.LENGTH_SHORT).show();
                                        break;
                                    case R.id.menu_report_problem:
                                        builder = new AlertDialog.Builder(mCtx);
                                        layoutInflater = LayoutInflater.from(mCtx);
                                        //this is custom dialog
                                        //custom_popup_dialog contains text view only
                                        customView = layoutInflater.inflate(R.layout.layout_report_problem, null);
                                        EditText editText = customView.findViewById(R.id.edt_write_report_problem);
                                        Button btn_send = customView.findViewById(R.id.btn_send_report_problem);
                                        Button btn_cancel = customView.findViewById(R.id.btn_cancel_report_problem);
                                        TextView cust_name = customView.findViewById(R.id.tv_cust_name_report_problem);
                                        TextView cust_mobile = customView.findViewById(R.id.tv_mobile_report_problem);
                                        CheckBox bill_collection = customView.findViewById(R.id.cb_bill_collection_report_problem);
                                        CheckBox pending_amount = customView.findViewById(R.id.cb_pending_amount_report_problem);
                                        CheckBox send_bill = customView.findViewById(R.id.cb_send_bill__report_problem);
                                        CheckBox collection_problem = customView.findViewById(R.id.cb_collection_report_problem);
                                        amt = editText.getText().toString();
                                        builder.setView(customView);
                                        final AlertDialog alertDialog = builder.create();
                                        alertDialog.show();

                                        btn_cancel.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {

                                                alertDialog.dismiss();
                                            }
                                        });
                                        btn_send.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                Toast.makeText(mCtx, amt, Toast.LENGTH_SHORT).show();
                                            }
                                        });
                                        break;
                                    case R.id.menu_delete_customer:
                                        builder = new AlertDialog.Builder(mCtx);
                                        layoutInflater = LayoutInflater.from(mCtx);
                                        //this is custom dialog
                                        //custom_popup_dialog contains textview only
                                        customView = layoutInflater.inflate(R.layout.layout_delete_customer, null);
                                        Button btn_delete = customView.findViewById(R.id.btn_delete_customer);
                                        Button button_cancel = customView.findViewById(R.id.btn_cancel_delete_customer);


                                        builder.setView(customView);
                                        final AlertDialog alertDialog1 = builder.create();
                                        alertDialog1.show();

                                        button_cancel.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                alertDialog1.dismiss();
                                            }
                                        });
                                        btn_delete.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                Toast.makeText(mCtx, "Record has been Deleted Permanently", Toast.LENGTH_SHORT).show();
                                            }
                                        });
                                        break;
                                }
                                return false;
                            }
                        });
                        popupMenu.show();
                    }
                });
                ((BillCompletedViewHolder) holder).btn_edit_Collection.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Attach Your Popup
                        showPopup(v);
                    }
                });
                break;
            case BILL_GENERATE:

                ((BillGenerateViewHolder) holder).bg_name.setText(billDetail.getName());
                ((BillGenerateViewHolder) holder).bg_papers.setText(billDetail.getPapers());
                ((BillGenerateViewHolder) holder).bg_mobile.setText(billDetail.getMobile());
                ((BillGenerateViewHolder) holder).bg_estimated_bill.setText("Rs." + billDetail.getEstimate_bill());
                ((BillGenerateViewHolder) holder).bg_send.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String amount = ((BillGenerateViewHolder) holder).bg_amount.getText().toString();
                        Toast.makeText(mCtx, "you Enter " + amount, Toast.LENGTH_SHORT).show();
                    }
                });

                ((BillGenerateViewHolder) holder).bg_bill_generate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(mCtx, "generate your bill", Toast.LENGTH_SHORT).show();
                    }
                });
                ((BillGenerateViewHolder) holder).bg_detail.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        builder = new AlertDialog.Builder(mCtx);
                        final LayoutInflater layoutInflater = LayoutInflater.from(mCtx);
                        //this is custom dialog
                        //custom_popup_dialog contains textview only
                        customView = layoutInflater.inflate(R.layout.detail_last_collection, null);
                        TextView tv_name = customView.findViewById(R.id.tv_dlc_customer_name);
                        TextView tv_mobile = customView.findViewById(R.id.tv_dlc_mobile);
                        TextView tv_total_bill = customView.findViewById(R.id.tv_dlc_total_bill_amt);
                        TextView tv_paper = customView.findViewById(R.id.tv_dlc_paper_name);
                        TextView tv_paper_qty = customView.findViewById(R.id.tv_dlc_paper_qty);
                        TextView tv_paper_price = customView.findViewById(R.id.tv_dlc_paper_price);
                        Button btn_close = customView.findViewById(R.id.btn_dlc_close);
                        builder.setView(customView);
                        final AlertDialog alertDialog = builder.create();
                        alertDialog.show();

                        btn_close.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                alertDialog.dismiss();
                            }
                        });
                    }
                });
                ((BillGenerateViewHolder) holder).bg_menu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        PopupMenu popupMenu = new PopupMenu(mCtx, ((BillGenerateViewHolder) holder).bg_menu);
                        popupMenu.inflate(R.menu.bill_collection_menu);
                        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                            @Override
                            public boolean onMenuItemClick(MenuItem item) {
                                //handling click Event
                                switch (item.getItemId()) {
                                    case R.id.menu_call:
                                        //   Intent callIntent = new Intent(Intent.ACTION_CALL);
                                        //   callIntent.setData(Uri.parse("tel:9125514509"));
                                        if (ActivityCompat.checkSelfPermission(mCtx, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                                            Toast.makeText(mCtx, "Permission Not Granted", Toast.LENGTH_SHORT).show();
                                            return false;
                                        }
                                        //mCtx.startActivity(callIntent);
                                        else {
                                            mCtx.startActivity(new Intent(Intent.ACTION_CALL).setData(Uri.parse("tel:9125514509")));
                                        }

                                        break;
                                    case R.id.menu_history:
                                        Toast.makeText(mCtx, "History Clicked", Toast.LENGTH_SHORT).show();
                                        break;
                                    case R.id.menu_report_problem:
                                        builder = new AlertDialog.Builder(mCtx);
                                        layoutInflater = LayoutInflater.from(mCtx);
                                        //this is custom dialog
                                        //custom_popup_dialog contains text view only
                                        customView = layoutInflater.inflate(R.layout.layout_report_problem, null);
                                        EditText editText = customView.findViewById(R.id.edt_write_report_problem);
                                        Button btn_send = customView.findViewById(R.id.btn_send_report_problem);
                                        Button btn_cancel = customView.findViewById(R.id.btn_cancel_report_problem);
                                        TextView cust_name = customView.findViewById(R.id.tv_cust_name_report_problem);
                                        TextView cust_mobile = customView.findViewById(R.id.tv_mobile_report_problem);
                                        CheckBox bill_collection = customView.findViewById(R.id.cb_bill_collection_report_problem);
                                        CheckBox pending_amount = customView.findViewById(R.id.cb_pending_amount_report_problem);
                                        CheckBox send_bill = customView.findViewById(R.id.cb_send_bill__report_problem);
                                        CheckBox collection_problem = customView.findViewById(R.id.cb_collection_report_problem);
                                        amt = editText.getText().toString();
                                        builder.setView(customView);
                                        final AlertDialog alertDialog = builder.create();
                                        alertDialog.show();

                                        btn_cancel.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {

                                                alertDialog.dismiss();
                                            }
                                        });
                                        btn_send.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                Toast.makeText(mCtx, amt, Toast.LENGTH_SHORT).show();
                                            }
                                        });
                                        break;
                                    case R.id.menu_delete_customer:
                                        builder = new AlertDialog.Builder(mCtx);
                                        layoutInflater = LayoutInflater.from(mCtx);
                                        //this is custom dialog
                                        //custom_popup_dialog contains textview only
                                        customView = layoutInflater.inflate(R.layout.layout_delete_customer, null);
                                        Button btn_delete = customView.findViewById(R.id.btn_delete_customer);
                                        Button button_cancel = customView.findViewById(R.id.btn_cancel_delete_customer);


                                        builder.setView(customView);
                                        final AlertDialog alertDialog1 = builder.create();
                                        alertDialog1.show();

                                        button_cancel.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                alertDialog1.dismiss();
                                            }
                                        });
                                        btn_delete.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                Toast.makeText(mCtx, "Record has been Deleted Permanently", Toast.LENGTH_SHORT).show();
                                            }
                                        });
                                        break;
                                }
                                return false;
                            }
                        });
                        popupMenu.show();
                    }
                });

                break;

            case BILL_PENDING:

                ((BillPendingViewHolder) holder).name.setText(billDetail.getName());
                ((BillPendingViewHolder) holder).bp_papers.setText(billDetail.getPapers());
                ((BillPendingViewHolder) holder).bp_last_month_name.setText(billDetail.getLast_month_name());
                ((BillPendingViewHolder) holder).bp_last_month_bill.setText(billDetail.getLast_month_bill());
                ((BillPendingViewHolder) holder).pending_amt.setText(billDetail.getCollection_pending_amt());
                ((BillPendingViewHolder) holder).bp_amount.setHint("0.0");

                ((BillPendingViewHolder) holder).bp_menu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        PopupMenu popupMenu = new PopupMenu(mCtx, ((BillPendingViewHolder) holder).bp_menu);
                        popupMenu.inflate(R.menu.bill_collection_menu);
                        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                            @Override
                            public boolean onMenuItemClick(MenuItem item) {
                                //handling click Event
                                switch (item.getItemId()) {
                                    case R.id.menu_call:
                                        //   Intent callIntent = new Intent(Intent.ACTION_CALL);
                                        //   callIntent.setData(Uri.parse("tel:9125514509"));
                                        if (ActivityCompat.checkSelfPermission(mCtx, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                                            Toast.makeText(mCtx, "Permission Not Granted", Toast.LENGTH_SHORT).show();
                                            return false;
                                        }
                                        //mCtx.startActivity(callIntent);
                                        else {
                                            mCtx.startActivity(new Intent(Intent.ACTION_CALL).setData(Uri.parse("tel:9125514509")));
                                        }

                                        break;
                                    case R.id.menu_history:
                                        Toast.makeText(mCtx, "History Clicked", Toast.LENGTH_SHORT).show();
                                        break;
                                    case R.id.menu_report_problem:
                                        builder = new AlertDialog.Builder(mCtx);
                                        layoutInflater = LayoutInflater.from(mCtx);
                                        //this is custom dialog
                                        //custom_popup_dialog contains text view only
                                        customView = layoutInflater.inflate(R.layout.layout_report_problem, null);
                                        EditText editText = customView.findViewById(R.id.edt_write_report_problem);
                                        Button btn_send = customView.findViewById(R.id.btn_send_report_problem);
                                        Button btn_cancel = customView.findViewById(R.id.btn_cancel_report_problem);
                                        TextView cust_name = customView.findViewById(R.id.tv_cust_name_report_problem);
                                        TextView cust_mobile = customView.findViewById(R.id.tv_mobile_report_problem);
                                        CheckBox bill_collection = customView.findViewById(R.id.cb_bill_collection_report_problem);
                                        CheckBox pending_amount = customView.findViewById(R.id.cb_pending_amount_report_problem);
                                        CheckBox send_bill = customView.findViewById(R.id.cb_send_bill__report_problem);
                                        CheckBox collection_problem = customView.findViewById(R.id.cb_collection_report_problem);
                                        amt = editText.getText().toString();
                                        builder.setView(customView);
                                        final AlertDialog alertDialog = builder.create();
                                        alertDialog.show();

                                        btn_cancel.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {

                                                alertDialog.dismiss();
                                            }
                                        });
                                        btn_send.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                Toast.makeText(mCtx, amt, Toast.LENGTH_SHORT).show();
                                            }
                                        });
                                        break;
                                    case R.id.menu_delete_customer:
                                        builder = new AlertDialog.Builder(mCtx);
                                        layoutInflater = LayoutInflater.from(mCtx);
                                        //this is custom dialog
                                        //custom_popup_dialog contains textview only
                                        customView = layoutInflater.inflate(R.layout.layout_delete_customer, null);
                                        Button btn_delete = customView.findViewById(R.id.btn_delete_customer);
                                        Button button_cancel = customView.findViewById(R.id.btn_cancel_delete_customer);


                                        builder.setView(customView);
                                        final AlertDialog alertDialog1 = builder.create();
                                        alertDialog1.show();

                                        button_cancel.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                alertDialog1.dismiss();
                                            }
                                        });
                                        btn_delete.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                Toast.makeText(mCtx, "Record has been Deleted Permanently", Toast.LENGTH_SHORT).show();
                                            }
                                        });
                                        break;
                                }
                                return false;
                            }
                        });
                        popupMenu.show();
                    }
                });

                ((BillPendingViewHolder) holder).bp_last_month_detail.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        builder = new AlertDialog.Builder(mCtx);
                        final LayoutInflater layoutInflater = LayoutInflater.from(mCtx);
                        //this is custom dialog
                        //custom_popup_dialog contains textview only
                        customView = layoutInflater.inflate(R.layout.detail_last_collection, null);
                        TextView tv_name = customView.findViewById(R.id.tv_dlc_customer_name);
                        TextView tv_mobile = customView.findViewById(R.id.tv_dlc_mobile);
                        TextView tv_total_bill = customView.findViewById(R.id.tv_dlc_total_bill_amt);
                        TextView tv_paper = customView.findViewById(R.id.tv_dlc_paper_name);
                        TextView tv_paper_qty = customView.findViewById(R.id.tv_dlc_paper_qty);
                        TextView tv_paper_price = customView.findViewById(R.id.tv_dlc_paper_price);
                        Button btn_close = customView.findViewById(R.id.btn_dlc_close);
                        builder.setView(customView);
                        final AlertDialog alertDialog = builder.create();
                        alertDialog.show();

                        btn_close.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(mCtx, amt, Toast.LENGTH_SHORT).show();
                                alertDialog.dismiss();
                            }
                        });
                    }
                });

                ((BillPendingViewHolder) holder).bp_last_month_edit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        builder = new AlertDialog.Builder(mCtx);
                        final LayoutInflater layoutInflater = LayoutInflater.from(mCtx);
                        //this is custom dialog
                        //custom_popup_dialog contains textview only
                        customView = layoutInflater.inflate(R.layout.edit_last_collection, null);
                        EditText editText = customView.findViewById(R.id.edt_modified_last_amt);
                        Button btn = customView.findViewById(R.id.btn_save_modified_last_amt);
                        amt = editText.getText().toString();
                        builder.setView(customView);
                        final AlertDialog alertDialog = builder.create();
                        alertDialog.show();

                        btn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(mCtx, amt, Toast.LENGTH_SHORT).show();
                                alertDialog.dismiss();
                            }
                        });
                    }
                });

                ((BillPendingViewHolder) holder).bp_pending_amt_detail.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        builder = new AlertDialog.Builder(mCtx);
                        final LayoutInflater layoutInflater = LayoutInflater.from(mCtx);
                        //this is custom dialog
                        //custom_popup_dialog contains textview only
                        customView = layoutInflater.inflate(R.layout.custom_layout_detail_pending_amt, null);
                        TextView tv_name = customView.findViewById(R.id.tv_dpa_customer_name);
                        TextView tv_mobile = customView.findViewById(R.id.tv_dpa_mobile);
                        TextView tv_total_pending_amt = customView.findViewById(R.id.tv_dpa_total_pending_amt);
                        TextView tv_last_month_name = customView.findViewById(R.id.tv_dpa_last_month_name);
                        TextView tv_last_month_bill = customView.findViewById(R.id.tv_dpa_last_month_bill);
                        TextView tv_pending_amt = customView.findViewById(R.id.tv_dpa_pending_amt);
                        Button btn_close = customView.findViewById(R.id.btn_dpa_close);
                        builder.setView(customView);
                        final AlertDialog alertDialog = builder.create();
                        alertDialog.show();

                        btn_close.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(mCtx, amt, Toast.LENGTH_SHORT).show();
                                alertDialog.dismiss();
                            }
                        });
                    }
                });

                ((BillPendingViewHolder) holder).bp_save_amt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String amount = ((BillPendingViewHolder) holder).bp_amount.getText().toString();
                        Toast.makeText(mCtx, "" + amount, Toast.LENGTH_SHORT).show();
                    }
                });
                break;
        }
    }

    private void showPopup(View v) {
        builder = new AlertDialog.Builder(mCtx);
        final LayoutInflater layoutInflater = LayoutInflater.from(mCtx);

        //this is custom dialog
        //custom_popup_dialog contains textview only
        customView = layoutInflater.inflate(R.layout.custom_popup_edit_collection, null);
        EditText modified_amt = customView.findViewById(R.id.edt_modified_amt);
        Button btn = customView.findViewById(R.id.btn_save_modified_amt);
        amt = modified_amt.getText().toString();
        builder.setView(customView);
        alertDialog = builder.create();
        alertDialog.show();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mCtx, amt, Toast.LENGTH_SHORT).show();
                alertDialog.dismiss();
            }
        });


    }


    @Override
    public int getItemCount() {
        return billDetailList.size();
    }

    //Here all Component are define regarding Bill Completed layout

    private class BillCompletedViewHolder extends RecyclerView.ViewHolder {
        private TextView cust_name, papers, cash_collected, cash_advanced;
        private ImageView iv_Option_menu;
        private Button btn_edit_Collection;

        private BillCompletedViewHolder(View view) {
            super(view);
            cust_name = itemView.findViewById(R.id.bill_Detail_customer_name);
            papers = itemView.findViewById(R.id.bill_Detail_papers);
            cash_collected = itemView.findViewById(R.id.bill_Detail_cash_collected);
            cash_advanced = itemView.findViewById(R.id.bill_Detail_cash_advanced);
            iv_Option_menu = itemView.findViewById(R.id.bill_Detail_iv_option_menu);
            btn_edit_Collection = itemView.findViewById(R.id.bill_Detail_Edit_Collection);

        }
    }


    //Here all Component are define regarding Bill Pending layout

    private class BillPendingViewHolder extends RecyclerView.ViewHolder {
        private TextView name, bp_papers, bp_last_month_name, bp_last_month_bill, pending_amt;
        private ImageView bp_menu;
        private Button bp_last_month_detail, bp_last_month_edit, bp_pending_amt_detail, bp_save_amt;
        private EditText bp_amount;

        private BillPendingViewHolder(View view) {
            super(view);
            //getting id's of bill pending amount
            name = itemView.findViewById(R.id.bill_pending_customer_name);
            bp_papers = itemView.findViewById(R.id.bill_pending_papers);
            bp_last_month_name = itemView.findViewById(R.id.bill_pending_last_month_name);
            bp_last_month_bill = itemView.findViewById(R.id.bill_pending_last_month_bill);
            pending_amt = itemView.findViewById(R.id.bill_pending_amount);
            bp_menu = itemView.findViewById(R.id.bill_pending_iv_option_menu);
            bp_last_month_detail = itemView.findViewById(R.id.bill_pending_btn_last_month_detail);
            bp_last_month_edit = itemView.findViewById(R.id.bill_pending_btn_last_month_edit);
            bp_pending_amt_detail = itemView.findViewById(R.id.bill_pending_btn_pending_amt_detail);
            bp_save_amt = itemView.findViewById(R.id.bill_pending_btn_save_amt);
            bp_amount = itemView.findViewById(R.id.bill_pending_edt_amount);
        }
    }


    //Here all Component are define regarding Bill generate layout

    private class BillGenerateViewHolder extends RecyclerView.ViewHolder {
        private TextView bg_name, bg_papers, bg_mobile, bg_estimated_bill;
        private ImageView bg_menu;
        private Button bg_send, bg_bill_generate, bg_detail;
        private EditText bg_amount;

        private BillGenerateViewHolder(View view) {
            super(view);
            bg_name = itemView.findViewById(R.id.bill_generate_customer_name);
            bg_papers = itemView.findViewById(R.id.bill_generate_papers);
            bg_mobile = itemView.findViewById(R.id.bill_generate_c_mobile);
            bg_estimated_bill = itemView.findViewById(R.id.bill_generate_estimated_bill);
            bg_menu = itemView.findViewById(R.id.bill_generate_iv_option_menu);
            bg_send = itemView.findViewById(R.id.bill_generate_btn_send);
            bg_bill_generate = itemView.findViewById(R.id.btn_bill_generate);
            bg_detail = itemView.findViewById(R.id.bill_generate_btn_detail);
            bg_amount = itemView.findViewById(R.id.bill_generate_edt_amount);
        }
    }
}
