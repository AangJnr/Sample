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

public class CheckInActivity extends BaseActivity {

    //Views

    Button continueButton;


    TextView customToolbarTextView;

    MaterialSpinner departureDateSpinner;
    EditText bookingReferenceNo;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_check_in);

        customToolbarTextView = setCustomToolbarTitle("Check-In");
        findViewById(R.id.menu).setVisibility(View.GONE);


        setBackListener();
        setupAndInitializeViews();


    }


    void setupAndInitializeViews() {


        departureDateSpinner = (MaterialSpinner) findViewById(R.id.departure_date_spinner);

        bookingReferenceNo = (EditText) findViewById(R.id.reference_no_edittext);


        continueButton = (Button) findViewById(R.id.button);


    }


}
