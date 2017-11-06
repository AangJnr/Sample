package io.nothing.sample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.jaredrummler.materialspinner.MaterialSpinner;

/**
 * Created by aangjnr on 04/11/2017.
 */

public class FlightPassengerInfoActivity extends BaseActivity {

    //Views

    Button proceedButton;


    TextView customToolbarTextView;

    EditText nameEdittext;
    EditText emailEdittext;

    MaterialSpinner titleSpinner;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_flight_passenger_info);

        customToolbarTextView = setCustomToolbarTitle("Passenger Information");
        findViewById(R.id.menu).setVisibility(View.GONE);


        setBackListener();
        setupAndInitializeViews();


    }


    void setupAndInitializeViews() {


        titleSpinner = (MaterialSpinner) findViewById(R.id.departure_date_spinner);
        titleSpinner.setItems("Mr.", "Mrs.", "Miss", "Dr.");

        nameEdittext = (EditText) findViewById(R.id.nameEditText);
        emailEdittext = (EditText) findViewById(R.id.emailEdittext);


        proceedButton = (Button) findViewById(R.id.button);


    }


}
