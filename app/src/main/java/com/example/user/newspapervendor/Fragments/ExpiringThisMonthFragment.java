package com.example.user.newspapervendor.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.newspapervendor.activities.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ExpiringThisMonthFragment extends Fragment {


    public ExpiringThisMonthFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_expiring_this_month, container, false);
    }

}
