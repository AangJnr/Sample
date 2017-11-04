package io.nothing.sample;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import io.nothing.sample.adapter.AppIntroViewPagerAdapter;
import io.nothing.sample.pojo.AppIntro;

/**
 * Created by aangjnr on 04/11/2017.
 */

public class AppIntroActivity extends BaseActivity implements ViewPager.OnPageChangeListener {


    ViewPager viewPager;
    AppIntroViewPagerAdapter mAdapter;
    List<AppIntro> introSlideItems = new ArrayList<>();
    int dotsCount;
    ImageView[] dots;
    TextView next;
    TextView skip;
    TextView done;
    private LinearLayout pager_indicator;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow(); // in Activity's onCreate() for instance
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        setContentView(R.layout.activity_app_intro);


        next = (TextView) findViewById(R.id.next);
        skip = (TextView) findViewById(R.id.skip);

        done = (TextView) findViewById(R.id.done);
        done.setAlpha(0f);
        done.setVisibility(View.GONE);


        introSlideItems.add(new AppIntro("Join flyer program", "Get rewarded when you build up mileage by booking flights on the go.", R.drawable.walkthrough_img_1));
        introSlideItems.add(new AppIntro("Seamless payments", "Pay with visa or mobile money right from your phone with ease.", R.drawable.walkthrough_img_2));
        introSlideItems.add(new AppIntro("Search and book flights", "Easily browse and book flights that suits your schedule.", R.drawable.walkthrough_img_3));


        viewPager = (ViewPager) findViewById(R.id.viewPager);
        pager_indicator = (LinearLayout) findViewById(R.id.viewPagerCountDots);

        mAdapter = new AppIntroViewPagerAdapter(this, introSlideItems);

        viewPager.setPadding(64, 0, 64, 64);
        viewPager.setClipToPadding(false);
        viewPager.setOffscreenPageLimit(2);
        viewPager.setPageMargin(32);

        viewPager.setAdapter(mAdapter);
        viewPager.setCurrentItem(0);

        viewPager.addOnPageChangeListener(this);
        setUiPageViewController();


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                viewPager.setCurrentItem(viewPager.getCurrentItem() + 1, true);

            }
        });


        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                viewPager.setCurrentItem(introSlideItems.size() - 1, true);

            }
        });


        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(AppIntroActivity.this, DashBoardActivity.class));
                supportFinishAfterTransition();
            }
        });


    }


    private void setUiPageViewController() {

        dotsCount = mAdapter.getCount();
        dots = new ImageView[dotsCount];

        for (int i = 0; i < dotsCount; i++) {
            dots[i] = new ImageView(this);
            dots[i].setImageDrawable(ContextCompat.getDrawable(this, R.drawable.non_selected_item));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );

            params.setMargins(4, 0, 4, 0);

            pager_indicator.addView(dots[i], params);
        }

        dots[0].setImageDrawable(ContextCompat.getDrawable(this, R.drawable.selected_item));

    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {


        for (int i = 0; i < dotsCount; i++) {
            dots[i].setImageDrawable(ContextCompat.getDrawable(this, R.drawable.non_selected_item));
        }

        dots[position].setImageDrawable(ContextCompat.getDrawable(this, R.drawable.selected_item));


        if (viewPager.getCurrentItem() == introSlideItems.size() - 1) {

            skip.animate().alpha(0f).start();
            skip.setVisibility(View.GONE);

            next.animate().alpha(0f).start();
            next.setVisibility(View.GONE);

            done.animate().alpha(1f).start();
            done.setVisibility(View.VISIBLE);


        } else {
            skip.animate().alpha(1f).start();
            skip.setVisibility(View.VISIBLE);

            next.animate().alpha(1f).start();
            next.setVisibility(View.VISIBLE);


            done.animate().alpha(0f).start();
            done.setVisibility(View.GONE);


        }


    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


}
