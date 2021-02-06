package com.example.mvvmex04.views.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.example.mvvmex04.R;

public class MainActivity extends FragmentActivity {

    private ViewPager2 viewPager;
    private FragmentStateAdapter pagerAdapter;
    private int pageCount = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pager);
//        pageCount = 4;
        viewPager = findViewById(R.id.vp_main_pager);
        pagerAdapter = new MainPagerAdapter(this);
//        viewPager.setPageTransformer(new ZoomOutPageTransformer());
//        viewPager.setPageTransformer(new DepthPageTransformer());
        viewPager.setAdapter(pagerAdapter);
    }

    @Override
    public void onBackPressed() {
        if (viewPager.getCurrentItem() == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed();
        } else {
            // Otherwise, select the previous step.
            viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
        }
    }

    private class MainPagerAdapter extends FragmentStateAdapter {
        public MainPagerAdapter(FragmentActivity fa) {
            super(fa);
        }

        @Override
        public Fragment createFragment(int position) {
            return new MainPagerFragment(position);
        }

        @Override
        public int getItemCount() {
            return pageCount;
        }
    }
}