package io.nothing.sample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.percent.PercentRelativeLayout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.jaredrummler.materialspinner.MaterialSpinner;
import com.tsongkha.spinnerdatepicker.DatePicker;
import com.tsongkha.spinnerdatepicker.DatePickerDialog;
import com.tsongkha.spinnerdatepicker.SpinnerDatePickerDialogBuilder;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

/**
 * Created by aangjnr on 04/11/2017.
 */

public class FlightScheduleActivity extends BaseActivity {

    //Views
    TextView customToolbarTextView;

    MaterialSpinner trip_type_spinner;
    MaterialSpinner cabin_class_spinner;


    PercentRelativeLayout departureLayout;
    PercentRelativeLayout returnLayout;


    TextView departure_day;
    TextView departure_month_year;

    TextView return_day;
    TextView return_month_year;

    EditText tripSourceEdittext;
    EditText tripDestinationEdittext;


    //Strings
    String tripType;
    String cabinClass;
    String noOfTeenagers;
    String noOfChildren;
    String noOfInfants;

    String departureDay;
    String departureMonth;

    String returnDay;
    String returnMonth;


    int year = 0;
    int monthOfYear = 0;
    int dayOfMonth = 0;


    SimpleDateFormat simpleDateFormatDay;
    SimpleDateFormat simpleDateFormatMonthYear;


    Button searchButton;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_flight_schedule);

        customToolbarTextView = setCustomToolbarTitle("Flight Schedules");
        findViewById(R.id.menu).setVisibility(View.GONE);


        simpleDateFormatDay = new SimpleDateFormat("dd", Locale.US);
        simpleDateFormatMonthYear = new SimpleDateFormat("MMMM yyyy", Locale.US);


        setBackListener();
        setupAndInitializeViews();


    }


    void setupAndInitializeViews() {

        Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        monthOfYear = c.get(Calendar.MONTH);
        dayOfMonth = c.get(Calendar.DAY_OF_MONTH);


        List<String> values = new ArrayList<>();
        for (int i = 0; i <= 20; i++) {

            values.add(i + "");

        }


        searchButton = (Button) findViewById(R.id.button);

        tripSourceEdittext = (EditText) findViewById(R.id.source_edittext);

        tripDestinationEdittext = (EditText) findViewById(R.id.destination_edittext);

        trip_type_spinner = (MaterialSpinner) findViewById(R.id.trip_type_Spinner);
        trip_type_spinner.setItems(new LinkedList<>(Arrays.asList("Round Trip", "One Way")));
        cabin_class_spinner = (MaterialSpinner) findViewById(R.id.cabin_class_Spinner);
        cabin_class_spinner.setItems(new LinkedList<>(Arrays.asList("Economy", "Business")));

        departureLayout = (PercentRelativeLayout) findViewById(R.id.departure_layout);
        returnLayout = (PercentRelativeLayout) findViewById(R.id.return_date_layout);

        departure_day = (TextView) findViewById(R.id.departure_day);
        departure_month_year = (TextView) findViewById(R.id.departure_month);

        return_day = (TextView) findViewById(R.id.return_day);
        return_month_year = (TextView) findViewById(R.id.return_month);


        //Clicked on departure, You can get the int values of day, month and year
        departure_day.setText(simpleDateFormatDay.format(c.getTime()));
        departure_month_year.setText(simpleDateFormatMonthYear.format(c.getTime()));


        //Clicked on return, You can get the int values of day, month and year
        return_day.setText(simpleDateFormatDay.format(c.getTime()));
        return_month_year.setText(simpleDateFormatMonthYear.format(c.getTime()));


        departureLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showDate(true, R.style.DatePickerSpinner);
            }
        });


        returnLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showDate(false, R.style.DatePickerSpinner);
            }
        });


    }


    void showDate(final boolean isDepartureDate, int spinnerTheme) {


        Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        monthOfYear = c.get(Calendar.MONTH);
        dayOfMonth = c.get(Calendar.DAY_OF_MONTH);


        new SpinnerDatePickerDialogBuilder()
                .context(FlightScheduleActivity.this)
                .callback(new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int _year, int _monthOfYear, int _dayOfMonth) {


                        year = _year;
                        monthOfYear = _monthOfYear;
                        dayOfMonth = _dayOfMonth;

                        Calendar c = Calendar.getInstance();
                        c.set(year, monthOfYear, dayOfMonth);


                        if (isDepartureDate) {
                            //Clicked on departure, You can get the int values of day, month and year
                            departure_day.setText(simpleDateFormatDay.format(c.getTime()));
                            departure_month_year.setText(simpleDateFormatMonthYear.format(c.getTime()));


                        } else {
                            //Clicked on return, You can get the int values of day, month and year
                            return_day.setText(simpleDateFormatDay.format(c.getTime()));
                            return_month_year.setText(simpleDateFormatMonthYear.format(c.getTime()));


                        }


                    }
                })
                .spinnerTheme(spinnerTheme)
                .year(year)
                .monthOfYear(monthOfYear)
                .dayOfMonth(dayOfMonth)
                .build()
                .show();
    }

}
