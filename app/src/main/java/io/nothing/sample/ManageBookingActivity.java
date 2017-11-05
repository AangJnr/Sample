package io.nothing.sample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by aangjnr on 04/11/2017.
 */

public class ManageBookingActivity extends BaseActivity {

    //Views

    Button shareStatusButton;

    TextView customToolbarTextView;

    TextView date;
    TextView destination;
    TextView departure;
    TextView flightNo;
    TextView departureTerminalNo;
    TextView departureGateNo;
    TextView departureTime;
    TextView arrivalTime;
    TextView arrivalTerminalNo;
    TextView status;


    int year = 0;
    int monthOfYear = 0;
    int dayOfMonth = 0;


    SimpleDateFormat simpleDateFormat;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_manage_booking);

        customToolbarTextView = setCustomToolbarTitle("Manage Booking");
        findViewById(R.id.menu).setVisibility(View.GONE);


        simpleDateFormat = new SimpleDateFormat("EEEE, dd MMM yyyy", Locale.US);


        setBackListener();
        setupAndInitializeViews();


    }


    void setupAndInitializeViews() {


        Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        monthOfYear = c.get(Calendar.MONTH);
        dayOfMonth = c.get(Calendar.DAY_OF_MONTH);


        date = (TextView) findViewById(R.id.currentDate);

        departure = (TextView) findViewById(R.id.departure);
        departureTerminalNo = (TextView) findViewById(R.id.departureTerminalNo);
        departureTime = (TextView) findViewById(R.id.departureTime);
        departureGateNo = (TextView) findViewById(R.id.departureGateNo);

        destination = (TextView) findViewById(R.id.destination);
        arrivalTerminalNo = (TextView) findViewById(R.id.arrivalTerminalNo);
        arrivalTime = (TextView) findViewById(R.id.arrivalTime);
        status = (TextView) findViewById(R.id.status);
        flightNo = (TextView) findViewById(R.id.flightNo);

        date.setText(simpleDateFormat.format(c.getTime()));
        shareStatusButton = (Button) findViewById(R.id.button);


    }


}
