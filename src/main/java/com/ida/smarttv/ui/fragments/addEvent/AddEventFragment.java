package com.ida.smarttv.ui.fragments.addEvent;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.ida.smarttv.R;
import com.ida.smarttv.data.remote.model.CreateEventResponse;
import com.ida.smarttv.utils.AppConstants;
import com.ida.smarttv.utils.Prefs;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import timber.log.Timber;

public class AddEventFragment extends Fragment {

    private AddEventViewModel mViewModel;

    TextInputLayout mInputDateTime, mInputLocation, mInputEventName;
    TextInputEditText mDateTimeEditText;
    Button mCreateEvent;
    NavController navController;

    public static AddEventFragment newInstance() {
        return new AddEventFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.add_event_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mInputDateTime = view.findViewById(R.id.input_date_time);
        mDateTimeEditText = view.findViewById(R.id.edit_date_time);
        mInputEventName = view.findViewById(R.id.input_event_name);
        mInputLocation = view.findViewById(R.id.input_event_location);
        mCreateEvent = view.findViewById(R.id.btn_create_event);

        mDateTimeEditText.setInputType(InputType.TYPE_NULL);
        navController = Navigation.findNavController(view);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(AddEventViewModel.class);

        mDateTimeEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateTimeDialog(mDateTimeEditText);
            }
        });


        mCreateEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String eventName = mInputEventName.getEditText().getText().toString();
                String eventLocation = mInputLocation.getEditText().getText().toString();
                String date = mInputDateTime.getEditText().getText().toString();
                final String creatorName = Prefs.getPreferences(getContext(), AppConstants.USER_NAME);
                String userFullName = Prefs.getPreferences(getContext(), AppConstants.USER_FULL_NAME);


                mViewModel.createEvent(eventName, eventLocation, date, userFullName, creatorName).observe(getViewLifecycleOwner(), new Observer<CreateEventResponse>() {
                    @Override
                    public void onChanged(CreateEventResponse createEventResponse) {

                        if (createEventResponse != null) {

                            if (createEventResponse.getStatus().equalsIgnoreCase("200")) {
                                Timber.d("success in creating event ");
                                navController.navigate(R.id.action_addEventFragment_to_eventsFragment);
                            }

                        }
                    }
                });


            }
        });
    }


    private void showDateTimeDialog(final TextInputEditText date_time_in) {
        final Calendar calendar = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        calendar.set(Calendar.MINUTE, minute);

                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy-MM-dd HH:mm");

                        date_time_in.setText(simpleDateFormat.format(calendar.getTime()));
                    }
                };

                new TimePickerDialog(getActivity(), timeSetListener, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), false).show();
            }
        };
        new DatePickerDialog(getActivity(), dateSetListener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();

    }


}
