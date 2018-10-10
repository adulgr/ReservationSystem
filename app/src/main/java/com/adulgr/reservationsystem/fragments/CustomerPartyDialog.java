package com.adulgr.reservationsystem.fragments;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AlertDialog.Builder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import com.adulgr.reservationsystem.R;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

public class CustomerPartyDialog extends DialogFragment {

  private Calendar calendar;
  private EditText dateSelect;
  private Spinner jumperSelect;
  private Spinner tableSelect;
  private Spinner chairSelect;


  public CustomerPartyDialog(){
    // Intentionally left blank.
  }

  @NonNull
  @Override
  public Dialog onCreateDialog(Bundle savedInstanceState) {
    LayoutInflater inflater = getActivity().getLayoutInflater();
    View view = inflater.inflate(R.layout.fragment_customer_party, null);
//    Dialog dialog = new Dialog(getActivity());
    final Calendar calendar = Calendar.getInstance(TimeZone.getDefault());

    dateSelect = view.findViewById(R.id.date);
    final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
      @Override
      public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        updateLabel();
      }
    };

    dateSelect.setOnFocusChangeListener(new OnFocusChangeListener() {
      @Override
      public void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus) {
          new DatePickerDialog(getContext(), date, calendar.get(Calendar.YEAR),
              calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
        }
      }
    });

    setJumperSelect(view);
    setTableSelect(view);
    setChairSelect(view);

    AlertDialog.Builder bob = new Builder(getActivity());
    bob.setTitle("Select Date & Equipment")
        .setView(view)
        .setPositiveButton("Book",
        new OnClickListener() {
          @Override
          public void onClick(DialogInterface dialog, int which) {

          }
        })
        .setNeutralButton("Back", new OnClickListener() {
          @Override
          public void onClick(DialogInterface dialog, int which) {
            DialogFragment fragment = new CustomerInfoDialog();
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragment.show(fragmentTransaction, "Info");
          }
        })
        .setNegativeButton("Cancel", new OnClickListener() {
          @Override
          public void onClick(DialogInterface dialog, int which) {
            CustomerPartyDialog.this.getDialog().cancel();
          }
        });
    return bob.create();
  }

  private void updateLabel(){
    String dateFormat = "MM/dd/yyyy";
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat, Locale.US);

    dateSelect.setText(simpleDateFormat.format(calendar.getTime()));
  }

  private void setJumperSelect(View view){
    ArrayAdapter<CharSequence> jumperAdapter = ArrayAdapter.createFromResource(
        getActivity(),
        R.array.jumper_spinner,
        android.R.layout.simple_spinner_item);
    jumperSelect = view.findViewById(R.id.jumper_spinner);
    jumperSelect.setAdapter(jumperAdapter);
  }
  private void setTableSelect(View view) {
    ArrayAdapter<CharSequence> tableAdapter = ArrayAdapter.createFromResource(
        getActivity(),
        R.array.tables_spinner,
        android.R.layout.simple_spinner_item);
    tableSelect = view.findViewById(R.id.table_spinner);
    tableSelect.setAdapter(tableAdapter);
  }
  private void setChairSelect(View view) {
    ArrayAdapter<CharSequence> chairAdapter = ArrayAdapter.createFromResource(
        getActivity(),
        R.array.chair_spinner,
        android.R.layout.simple_spinner_item);
    chairSelect = view.findViewById(R.id.chair_spinner);
    chairSelect.setAdapter(chairAdapter);
  }

}
