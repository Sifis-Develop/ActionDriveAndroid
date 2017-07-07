package com.google.android.gms.location.sample.locationupdates;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.NumberPicker;

public class SettingsDialog extends DialogFragment {

    public static final String PREFS_SETTINGS = "PrefsSettings";
    public static final String PREF_SAVE_RATE = "PrefSaveRate";

    private View myLayout;

    //private OnSettingsDialogInteractionListener mListener;

    public static SettingsDialog newInstance() {

        return new SettingsDialog();
    }

    public SettingsDialog() { }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final ViewGroup rootView = null;
        Activity rootActivity = getActivity();

        LayoutInflater myLayoutInflater = rootActivity.getLayoutInflater();
        myLayout = myLayoutInflater.inflate(R.layout.dialog_settings, rootView, false);

        final NumberPicker numberPicker = (NumberPicker)myLayout.findViewById(R.id.numberPicker);
        numberPicker.setMaxValue(180);
        numberPicker.setMinValue(1);

        final SharedPreferences settings = getActivity().getSharedPreferences(PREFS_SETTINGS, 0);

        Log.d("integer----->",String.valueOf(settings.getInt(PREF_SAVE_RATE, 30)));
        numberPicker.setValue(settings.getInt(PREF_SAVE_RATE, 30));

        AlertDialog.Builder builder = new AlertDialog.Builder( rootActivity );
        builder.setView( myLayout )
                .setTitle( "Settings" )
                .setNegativeButton("Done", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int id) {

                        SharedPreferences.Editor editor = settings.edit();
                        editor.putInt(PREF_SAVE_RATE, numberPicker.getValue());
                        editor.commit();

                        //mListener.updateViewsOnChangeSettings();
                        dismiss();
                    }
                });
        return builder.create();
    }

//    @Override
//    public void onAttach(Activity activity) {
//        super.onAttach(activity);
//        try {
//            mListener = (OnSettingsDialogInteractionListener) activity;
//        } catch (ClassCastException e) {
//            throw new ClassCastException(activity.toString()
//                    + " must implement OnSettingsDialogInteractionListener");
//        }
//    }
//
//    @Override
//    public void onDetach() {
//        super.onDetach();
//        mListener = null;
//    }

//    public interface OnSettingsDialogInteractionListener {
//
//        void updateViewsOnChangeSettings();
//        void checkForNewVersion();
//    }
}
