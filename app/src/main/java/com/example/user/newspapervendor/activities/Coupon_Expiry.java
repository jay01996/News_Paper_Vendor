package com.example.user.newspapervendor.activities;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.user.newspapervendor.Fragments.CouponFragment;
import com.example.user.newspapervendor.Fragments.ExpiredFragment;
import com.example.user.newspapervendor.Fragments.ExpiringThisMonthFragment;
import com.example.user.newspapervendor.Fragments.PendingFragment;
import com.example.user.newspapervendor.Fragments.TodayFragment;
import com.example.user.newspapervendor.Fragments.TransferredFragment;

import java.util.ArrayList;
import java.util.List;

public class Coupon_Expiry extends AppCompatActivity {
    private ViewPager viewPager;
    private TabLayout tabs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coupon__expiry);
        viewPager = findViewById(R.id.viewpager_coupon);
        setupViewPager(viewPager);
        // Set Tabs inside Toolbar
        tabs = findViewById(R.id.tablayout_coupon);
        tabs.setupWithViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        Bank_Transfer_States.Adapter adapter = new Bank_Transfer_States.Adapter(getSupportFragmentManager());
        adapter.addFragment(new CouponFragment(), "Coupon");
        adapter.addFragment(new ExpiringThisMonthFragment(), "Expiring Soon");
        adapter.addFragment(new ExpiredFragment(), "Expired");
        viewPager.setAdapter(adapter);
    }

    static class Adapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public Adapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

}
