package informatics.uk.ac.ed.track.esm.fragments;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.os.Bundle;
import android.widget.DatePicker;

import informatics.uk.ac.ed.track.R;

public class DatePickerFragment extends DialogFragment
        implements DatePickerDialog.OnDateSetListener {

    public static final String ARG_PICKER_ID = "pickerId";
    public static final String ARG_YEAR = "year";
    public static final String ARG_MONTH = "month";
    public static final String ARG_DAY = "day";
    public static final String ARG_MIN_DATE = "minDate";

    private int pickerId;
    private DatePickerDialogListener callbackListener;

    public interface DatePickerDialogListener {
        void OnDateSet(int pickerId, DatePicker view, int year, int monthOfYear, int dayOfMonth);
    }

    @Override @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);
        Bundle args = getArguments();

        int year, month, day;
        long minDate;

        year = args.getInt(ARG_YEAR);
        month = args.getInt(ARG_MONTH);
        day = args.getInt(ARG_DAY);
        minDate = args.getLong(ARG_MIN_DATE);

        pickerId = args.getInt(ARG_PICKER_ID);

        // Create a new instance of DatePickerDialog and return it
        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                this, year, month, day);

        if (minDate != 0L) {
            datePickerDialog.getDatePicker().setMinDate(minDate);
        }

        return datePickerDialog;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            callbackListener = (DatePickerDialogListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement DatePickerDialogListener");
        }
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        callbackListener.OnDateSet(this.pickerId, view, year, monthOfYear, dayOfMonth);
    }
}