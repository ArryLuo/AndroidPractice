package lyl.coordinatoreps;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;


public class AppBarLayoutActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private ViewPager mViewPager;
    private TabLayout mTabLayout;

    private CollapsingToolbarLayout mCollapsingToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_content);

        initToolbar();

        mCollapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_collaps_toolbar);
        mCollapsingToolbar.setTitle("SmallCheric");
        mCollapsingToolbar.setExpandedTitleColor(Color.WHITE);

        initViewpager();
    }

    private void initToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle("AppbarLayout");
        mToolbar.setNavigationIcon(R.mipmap.back);
    }

    private void initViewpager() {
        FragmentManager fm = getSupportFragmentManager();
        mTabLayout = (TabLayout) findViewById(R.id.appbar_tab_layout);
        mTabLayout.addTab(mTabLayout.newTab().setText("Tab1"));
        mTabLayout.addTab(mTabLayout.newTab().setText("Tab2"));
        mTabLayout.addTab(mTabLayout.newTab().setText("Tab3"));
        mViewPager = (ViewPager) findViewById(R.id.appbar_viewpager);
        mViewPager.setAdapter(new MyCusAdapter(fm));
        mTabLayout.setupWithViewPager(mViewPager);
    }

    public class MyCusAdapter extends FragmentPagerAdapter {

        private String tabTitles[] = new String[]{"tab1", "tab2", "tab3"};

        public MyCusAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return new AppbarFragment();
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabTitles[position];
        }

    }
}
