package io.nothing.sample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by aangjnr on 04/11/2017.
 */

public class ManageBooking2Activity extends BaseActivity {

    //Views

    TextView customToolbarTextView;
    EditText nameEdittext;
    EditText bookingReferenceEdittext;

    Button getTripInformationButton;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_manage_booking_2);

        customToolbarTextView = setCustomToolbarTitle("Manage Booking");
        findViewById(R.id.menu).setVisibility(View.GONE);

        getTripInformationButton = (Button) findViewById(R.id.button);

        nameEdittext = (EditText) findViewById(R.id.nameEditText);
        bookingReferenceEdittext = (EditText) findViewById(R.id.referenceNoEdittext);


        getTripInformationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });

        setBackListener();

    }


}
