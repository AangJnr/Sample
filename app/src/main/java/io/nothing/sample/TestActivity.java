package io.nothing.sample;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.github.dkharrat.nexusdialog.FormController;
import com.github.dkharrat.nexusdialog.FormFragment;
import com.github.dkharrat.nexusdialog.controllers.CheckBoxController;
import com.github.dkharrat.nexusdialog.controllers.DatePickerController;
import com.github.dkharrat.nexusdialog.controllers.EditTextController;
import com.github.dkharrat.nexusdialog.controllers.FormSectionController;
import com.github.dkharrat.nexusdialog.controllers.SelectionController;
import com.github.dkharrat.nexusdialog.controllers.TimePickerController;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.nothing.sample.utility.Constants;

/**
 * Created by aangjnr on 16/10/2017.
 */

public class TestActivity extends FragmentActivity {


    Button submitButton;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(getResources().getString(R.string.app_name));


        // In order to use our custom activity, we inflate the form fragment instead of extending the
        // activity to FormActivity which comes with its own view;

        FragmentManager fm = getSupportFragmentManager();
        final MyFormFragment formFragment;


        formFragment = new MyFormFragment();
        fm.beginTransaction()
                .add(R.id.mainLayout, formFragment, formFragment.getClass().getSimpleName())
                .commit();


        //Button which gets all questions, validates the input, set their respective answers
        // and sends the data to the server


        submitButton = (Button) findViewById(R.id.submit);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                formFragment.validate();
            }
        });


    }


    public static class MyFormFragment extends FormFragment {


        List<Question> questions;
        FormSectionController formSectionController;


        @Override
        public void initForm(FormController controller) {

            Context context = getContext();
            // Add new Questions with data model of Question.class
            // The Question data model takes 2 or more parameters based on the type value

            formSectionController = new FormSectionController(getContext(), "Personal Info");


            questions = new ArrayList<>();

            questions.add(new Question(Constants.TYPE_TEXT, "fname", "What is your first name?", null));

            questions.add(new Question(Constants.TYPE_TEXT, "age", "What is your age?", null));

            questions.add(new Question(Constants.TYPE_SELECTABLE, "gender", "What is your gender?",
                    Arrays.asList("Male", "Female")));

            questions.add(new Question(Constants.TYPE_TEXT, "location", "Where do you live?", null));

            questions.add(new Question(Constants.TYPE_SELECTABLE, "make", "Are you human?",
                    Arrays.asList("Yes", "No")));

            questions.add(new Question(Constants.TYPE_CHECKBOX, "bestGames", "Please select your favorites games",
                    Arrays.asList("FIFA 16", "NFS Smackdown", "TEKKEN 5", "Nova 3")));

            questions.add(new Question(Constants.TYPE_TIMEPICKER, "time", "Please select time", null));

            questions.add(new Question(Constants.TYPE_DATEPICKER, "date", "When were you born", null));


            for (Question q : questions) {

                switch (q.getType()) {

                    case Constants.TYPE_TEXT:
                        formSectionController.addElement(new EditTextController(context, q.getTag(), q.getLabel()));
                        break;

                    case Constants.TYPE_SELECTABLE:
                        formSectionController.addElement(new SelectionController(context, q.getTag(), q.getLabel(), true, "Select", (q.getValues() != null) ? q.getValues() : null, true));
                        break;

                    case Constants.TYPE_CHECKBOX:
                        formSectionController.addElement(new CheckBoxController(context, q.getTag(), q.getLabel(), true, (q.getValues() != null) ? q.getValues() : null, true));
                        break;

                    case Constants.TYPE_TIMEPICKER:
                        formSectionController.addElement(new TimePickerController(context, q.getTag(), q.getLabel()));
                        break;

                    case Constants.TYPE_DATEPICKER:
                        formSectionController.addElement(new DatePickerController(context, q.getTag(), q.getLabel()));
                        break;


                }

            }

            // End for loop


            controller.addSection(formSectionController);


        }


        public boolean validate() {
            getFormController().resetValidationErrors();
            if (getFormController().isValidInput()) {

                //Send data to server here after getting JSON string

                Toast.makeText(getContext(), getAllAnswersInJSON(), Toast.LENGTH_LONG).show();

            } else {

                // Whoaaaaaaa! There were some invalid inputs
                getFormController().showValidationErrors();

            }
            return true;
        }


        // This method iterates through the questions list, append their respective answers and
        // parses the list into a JSON string

        String getAllAnswersInJSON() {

            for (Question q : questions) {
                q.setAnswer(getModel().getValue(q.getTag()));
            }

            return new Gson().toJson(questions);
        }


    }
}
