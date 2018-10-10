package com.adulgr.reservationsystem.fragments;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import com.adulgr.reservationsystem.R;

public class CustomerInfoDialog extends DialogFragment {

  public CustomerInfoDialog() {
    // Intentionally left blank.
  }

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @NonNull
  @Override
  public Dialog onCreateDialog(Bundle savedInstanceState) {
    LayoutInflater inflater = getActivity().getLayoutInflater();
    View view = inflater.inflate(R.layout.fragment_customer_info, null);
    Dialog dialog = new Dialog(getActivity());

    final EditText firstName = dialog.findViewById(R.id.first_name);
    final EditText lastName = dialog.findViewById(R.id.last_name);
    final EditText phoneNumber = dialog.findViewById(R.id.phone);
    EditText email = dialog.findViewById(R.id.email);
    final EditText address = dialog.findViewById(R.id.address);


    AlertDialog.Builder bob = new AlertDialog.Builder(getActivity());
    bob.setTitle("Book Now")
        .setView(view)
        .setPositiveButton("Next", new OnClickListener() {
      @Override
      public void onClick(DialogInterface dialog, int which) {
//        if (firstName.getText().toString().equals("")) {
//          firstName.setError("Enter first name!");
//          return;
//        }
//        if (lastName.getText().toString().equals("")) {
//          lastName.setError("Enter last name!");
//          return;
//        }
//        if (phoneNumber.getText().toString().equals("")) {
//          phoneNumber.setError("Enter phone number!");
//        }
//        if (address.getText().toString().equals("")) {
//          address.setError("We need an address!");
//        }
        DialogFragment fragment = new CustomerPartyDialog();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.addToBackStack(null);
        fragment.show(fragmentTransaction, "Date");

      }
    })
        .setNegativeButton("Cancel", new OnClickListener() {
          @Override
          public void onClick(DialogInterface dialog, int which) {
            CustomerInfoDialog.this.getDialog().cancel();
          }
        });
    return bob.create();
  }

  //  @Nullable
//  @Override
//  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
//      @Nullable Bundle savedInstanceState) {
//    View view = inflater.inflate(R.layout.fragment_customer_info, container, false);
//
//    return view;
//  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    // Used to set title on toolbar.
//    getActivity().setTitle("Book Now");
  }
}
