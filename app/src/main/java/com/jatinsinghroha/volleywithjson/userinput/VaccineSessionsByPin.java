package com.jatinsinghroha.volleywithjson.userinput;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.DateValidatorPointForward;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.jatinsinghroha.volleywithjson.databinding.ActivityVaccineSessionsByPinBinding;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

import androidx.appcompat.app.AppCompatActivity;

public class VaccineSessionsByPin extends AppCompatActivity {

    private ActivityVaccineSessionsByPinBinding bind;
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = ActivityVaccineSessionsByPinBinding.inflate(getLayoutInflater());
        setContentView(bind.getRoot());

        setOnClicks();

        bind.chooseDateTF.getEditText().setText(dateFormat.format(System.currentTimeMillis()));
    }

    private void setOnClicks() {

        bind.fetchSlotsButton.setOnClickListener(v -> {
            if (checkConditions(bind.pinCodeTextField.getEditText().getText().toString(),
                    bind.chooseDateTF.getEditText().getText().toString()))
            {
                Toast.makeText(VaccineSessionsByPin.this, "Valid PinCode & Date - Call the API", Toast.LENGTH_LONG).show();
                callAPI(bind.pinCodeTextField.getEditText().getText().toString(), bind.chooseDateTF.getEditText().getText().toString());
            } else {
                Toast.makeText(VaccineSessionsByPin.this, "Please enter valid pincode.", Toast.LENGTH_LONG).show();
            }
        });

        bind.chooseDateTF.setOnClickListener(v -> {
            showDateChooser();
        });

        bind.chooseDateTF.getEditText().setOnClickListener(v -> {
            showDateChooser();
        });

    }

    private void showDateChooser() {

        //Initializing DatePicker Builder
        MaterialDatePicker.Builder datePickerBuilder = MaterialDatePicker.Builder.datePicker();

        //SettingTitleText
        datePickerBuilder.setTitleText("Select Date");

        //Setting Default Selection
        datePickerBuilder.setSelection(MaterialDatePicker.todayInUtcMilliseconds());

        //Preparing Constraints
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        //Setting Last Month
        calendar.set(Calendar.MONTH, Calendar.DECEMBER);

        //BuildingConstraints
        CalendarConstraints.Builder constraints = new CalendarConstraints.Builder().setValidator(DateValidatorPointForward.now());

        //Setting Start and End
        constraints.setStart(MaterialDatePicker.todayInUtcMilliseconds());
        constraints.setEnd(calendar.getTimeInMillis());

        //Setting Constraints
        datePickerBuilder.setCalendarConstraints(constraints.build());

        //Building DatePicker
        MaterialDatePicker datePicker = datePickerBuilder.build();

        //Setting onClick
        datePicker.addOnPositiveButtonClickListener(selection -> {

            bind.chooseDateTF.getEditText().setText(dateFormat.format(selection));
        });

        //Showing DatePicker
        datePicker.show(this.getSupportFragmentManager(), "Date Picker");

    }

    // "124001" -> True.
    // "abc123" -> Crash, control will go to Catch block

    private boolean checkConditions(String pin, String date) {
        return isValidPinCode(pin) && isValidDate(date);
    }

    private boolean isValidPinCode(String pin) {
        try {
            Integer.parseInt(pin);
            return pin.length() == 6;
        }
        catch (Exception e) {
            return false;
        }
    }

    private boolean isValidDate(String date) {
        return date.length() == 10;
    }

    private void callAPI(String pin, String date) {

        String apiURL = "https://cdn-api.co-vin.in/api/v2/appointment/sessions/public/calendarByPin?pincode="+pin+"&date="+date;

        Log.e("vaccineAPI", "API URL = "+apiURL);

        /**
         * 1. API URl
         * 2. Method.GET
         * 3. null
         * 4. Response.Listener
         * 5. Response.ErrorListener
         */

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        JsonObjectRequest getVaccines = new JsonObjectRequest(Request.Method.GET, apiURL, null, response -> {

            Log.e("vaccineAPI", "Response = "+response.toString());

        },
                error -> {

                });

        requestQueue.add(getVaccines);
    }


    /**
     * private fun initDateChooser() {
     *         val today = MaterialDatePicker.todayInUtcMilliseconds()
     *         val calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"))
     *         calendar.timeInMillis = today
     *         calendar[Calendar.MONTH] = Calendar.DECEMBER
     *         val decThisYear = calendar.timeInMillis
     *         val constraintsBuilder = CalendarConstraints.Builder()
     *                 .setValidator(DateValidatorPointForward.now())
     *                 .setStart(MaterialDatePicker.todayInUtcMilliseconds())
     *                 .setEnd(decThisYear)
     *         val datePicker = MaterialDatePicker.Builder.datePicker()
     *                 .setTitleText("Select date")
     *                 .setCalendarConstraints(constraintsBuilder.build())
     *                 .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
     *                 .build()
     *
     *         Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
     *         calendar.set(Calendar.MONTH, Calendar.DECEMBER);
     *
     *         datePicker.show(childFragmentManager, "SelectDate")
     *         datePicker.addOnPositiveButtonClickListener {
     *             val simpleDateFormat = SimpleDateFormat("dd-MM-yyyy")
     *             val dateString = simpleDateFormat.format(datePicker.selection)
     *             chooseDateTF.editText?.setText(dateString)
     *         }
     *     }
     */
}