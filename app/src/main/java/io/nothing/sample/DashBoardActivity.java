package io.nothing.sample;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

/**
 * Created by aangjnr on 02/11/2017.
 */

public class DashBoardActivity extends BaseActivity {


    TextView customToolbarTextView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        customToolbarTextView = setCustomToolbarTitle("My Dashboard");
        findViewById(R.id.back).setVisibility(View.GONE);


        setClickListeners();


    }


    void setClickListeners() {


        findViewById(R.id.book_flight).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(DashBoardActivity.this, BookFlightActivity.class));


            }
        });


        findViewById(R.id.check_in).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(DashBoardActivity.this, CheckInActivity.class));


            }
        });


        findViewById(R.id.flight_schedule).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(DashBoardActivity.this, FlightScheduleActivity.class));


            }
        });

        findViewById(R.id.search_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(DashBoardActivity.this, FlightScheduleActivity.class));


            }
        });

        findViewById(R.id.flight_status).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(DashBoardActivity.this, FlightStatusActivity.class));


            }
        });


        findViewById(R.id.manage_booking).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(DashBoardActivity.this, ManageBooking2Activity.class));


            }
        });
    }


}
