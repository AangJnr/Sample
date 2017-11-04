package io.nothing.sample.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import io.nothing.sample.R;
import io.nothing.sample.pojo.AppIntro;

/**
 * Created by aangjnr on 04/09/2017.
 */

public class AppIntroViewPagerAdapter extends PagerAdapter {

    Context context;
    List<AppIntro> appIntroList;
    LayoutInflater layoutInflater;


    public AppIntroViewPagerAdapter(Context c, List<AppIntro> appIntroList) {
        this.context = c;
        this.appIntroList = appIntroList;
        this.layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }


    @Override
    public int getCount() {
        return appIntroList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);


    }


    @Override
    public Object instantiateItem(ViewGroup container, final int position) {

        View view = this.layoutInflater.inflate(R.layout.app_intro_pager_item, container, false);

        AppIntro introItem = appIntroList.get(position);


        TextView headertext = (TextView) view.findViewById(R.id.header_text);
        TextView subText = (TextView) view.findViewById(R.id.sub_text);
        ImageView image = (ImageView) view.findViewById(R.id.iv1);


        headertext.setText(introItem.getHeaderText());
        subText.setText(introItem.getSubText());
        image.setImageResource(introItem.getImage());


        container.addView(view);
        return view;
    }


}
