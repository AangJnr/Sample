package io.nothing.sample;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jaredrummler.materialspinner.MaterialSpinner;

import java.util.ArrayList;
import java.util.List;

import io.nothing.sample.adapter.FlightScheduleRecyclerViewAdapter;
import io.nothing.sample.pojo.FlightSchedule;

/**
 * Created by aangjnr on 04/11/2017.
 */

public class FlightScheduleListActivity extends BaseActivity {

    //Views

    List<FlightSchedule> flightScheduleList;

    RecyclerView mRecycler;
    Button continueButton;
    TextView customToolbarTextView;

    MaterialSpinner departureDateSpinner;
    EditText bookingReferenceNo;

    RelativeLayout placeHolder;


    FlightScheduleRecyclerViewAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_flight_schedule_list);
        mRecycler = (RecyclerView) findViewById(R.id.recycler_view);

        customToolbarTextView = setCustomToolbarTitle("Flight Schedule List");
        findViewById(R.id.menu).setVisibility(View.GONE);


        setBackListener();
        setupAndInitializeViews();


        flightScheduleList = new ArrayList<>();
        flightScheduleList.add(new FlightSchedule("Abuja", "12:40 GMT, 21", "Mon - Sun", "ABJ", "Accra", "3:00 GMT", "Mon - Sun", "ACC"));
        flightScheduleList.add(new FlightSchedule("Abuja", "1:00 GMT, 21", "Mon - Sun", "ABJ", "Accra", "8:00 GMT", "Mon - Sun", "ACC"));
        flightScheduleList.add(new FlightSchedule("Abuja", "2:00 GMT, 21", "Mon - Sun", "ABJ", "Accra", "9:00 GMT", "Mon - Sun", "ACC"));
        flightScheduleList.add(new FlightSchedule("Abuja", "3:00 GMT, 21", "Mon - Sun", "ABJ", "Accra", "12:00 GMT", "Mon - Sun", "ACC"));
        flightScheduleList.add(new FlightSchedule("Abuja", "4:00 GMT, 21", "Mon - Sun", "ABJ", "Accra", "13:01 GMT", "Mon - Sun", "ACC"));
        flightScheduleList.add(new FlightSchedule("Abuja", "5:00 GMT, 21", "Mon - Sun", "ABJ", "Accra", "14:00 GMT", "Mon - Sun", "ACC"));
        flightScheduleList.add(new FlightSchedule("Abuja", "6:00 GMT, 21", "Mon - Sun", "ABJ", "Accra", "15:30 GMT", "Mon - Sun", "ACC"));
        flightScheduleList.add(new FlightSchedule("Abuja", "7:00 GMT, 21", "Mon - Sun", "ABJ", "Accra", "17:20 GMT", "Mon - Sun", "ACC"));


        if (flightScheduleList != null)
            if (flightScheduleList.size() == 0) {

                placeHolder.setVisibility(View.VISIBLE);


            } else {

                if (placeHolder.getVisibility() == View.VISIBLE)
                    placeHolder.setVisibility(View.GONE);

                mAdapter = new FlightScheduleRecyclerViewAdapter(this, flightScheduleList);
                mRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
                mAdapter.setHasStableIds(true);
                mRecycler.setAdapter(mAdapter);
                startFadeInAnimation(mRecycler);


                mAdapter.setOnItemClickListener(new FlightScheduleRecyclerViewAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {


                        startActivity(new Intent(FlightScheduleListActivity.this, FlightSummaryActivity.class));

                    }
                });

            }


    }


    void setupAndInitializeViews() {


        placeHolder = (RelativeLayout) findViewById(R.id.placeHolder);
        departureDateSpinner = (MaterialSpinner) findViewById(R.id.departure_date_spinner);

        bookingReferenceNo = (EditText) findViewById(R.id.reference_no_edittext);


        continueButton = (Button) findViewById(R.id.button);


    }


    private void startFadeInAnimation(View v) {

        v.animate()
                .alpha(1)
                .setInterpolator(new OvershootInterpolator(1.f))
                .setDuration(500)
                .start();

        v.setVisibility(View.VISIBLE);
    }

}
