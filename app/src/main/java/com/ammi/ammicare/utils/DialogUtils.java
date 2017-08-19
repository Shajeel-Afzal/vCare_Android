package com.ammi.ammicare.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

public class DialogUtils {

    public static void showInternetNotAvailableDialog(Context context, DialogInterface.OnClickListener okClickListner) {
        new AlertDialog.Builder(context).setTitle("Connectivity Problem")
                .setMessage("Sorry you are not connected to the internet. Please try again later.")
                .setPositiveButton("OK", okClickListner)
                .show();
    }

    public static void showInternetNotAvailableDialog(Context context) {
        new AlertDialog.Builder(context).setTitle("Connectivity Problem")
                .setMessage("Sorry you are not connected to the internet. Please try again later.")
                .setPositiveButton("OK", null)
                .show();
    }

    public static void showInternetNotAvailableDialog(Context context, String message) {
        new AlertDialog.Builder(context).setTitle("Connectivity Problem")
                .setMessage(message)
                .setPositiveButton("OK", null).show();
    }

    public static void showOkDialog(Context context, String dialogTitle, String messageToShow) {
        new AlertDialog.Builder(context).setTitle(dialogTitle)
                .setMessage(messageToShow)
                .setPositiveButton("OK", null).show();
    }

    public static void showOkDialog(Context context, String dialogTitle, String messageToShow, DialogInterface.OnClickListener listner) {
        new AlertDialog.Builder(context).setTitle(dialogTitle)
                .setMessage(messageToShow)
                .setPositiveButton("OK", listner).show();
    }

    public static void showOkCancelDialog(Context context, String dialogTitle, String messageToShow,
                                          DialogInterface.OnClickListener okClickListner,
                                          DialogInterface.OnClickListener canceClickListner) {
        new AlertDialog.Builder(context).setTitle(dialogTitle)
                .setMessage(messageToShow)
                .setNegativeButton("CANCEL", canceClickListner)
                .setPositiveButton("OK", okClickListner).show();

    }

    public static void showOkCancelDialog(Context context, String dialogTitle, String messageToShow,
                                          String postiveActionButtonTitle, String negativeActionButtonTitle,
                                          DialogInterface.OnClickListener okClickListner,
                                          DialogInterface.OnClickListener canceClickListner) {
        new AlertDialog.Builder(context).setTitle(dialogTitle)
                .setMessage(messageToShow)
                .setNegativeButton(negativeActionButtonTitle, canceClickListner)
                .setPositiveButton(postiveActionButtonTitle, okClickListner).show();

    }

    public static void showOkCancelDialog(Context context, String dialogTitle, String messageToShow,
                                          DialogInterface.OnClickListener okClickListner,
                                          DialogInterface.OnClickListener canceClickListner,
                                          String positiveButtonMessage,
                                          String negativeButtonMessage) {
        new AlertDialog.Builder(context).setTitle(dialogTitle)
                .setMessage(messageToShow)
                .setNegativeButton(negativeButtonMessage, canceClickListner)
                .setPositiveButton(positiveButtonMessage, okClickListner).show();

    }

//    public static void showOkDialog(MyOkDialog.OnOkDialogCompleteListener listner,
//                                    FragmentManager fm, String dialogTitle, String messageToShow) {
//        FragmentTransaction ft3 = fm.beginTransaction();
//        Fragment prev3 = (Fragment) fm
//                .findFragmentByTag(Consts.Extras.OK_DIALOG_TAG);
//        if (prev3 != null) {
//            ft3.remove(prev3);
//        }
//        // Create and show the dialog.
//        MyOkDialog dialog = MyOkDialog.newInstance(listner, dialogTitle,
//                messageToShow, 0);
//        dialog.setCancelable(false);
//        ft3.add(dialog, Consts.Extras.OK_DIALOG_TAG).commit();
//    }
//
//    public static void showOkDialog(FragmentManager fm, String dialogTitle,
//                                    String messageToShow) {
//        // We want to remove any currently showing
//        // dialog, so make our own transaction and take care of that here.
//        FragmentTransaction ft3 = fm.beginTransaction();
//        Fragment prev3 = (Fragment) fm
//                .findFragmentByTag(Consts.Extras.OK_DIALOG_TAG);
//        if (prev3 != null) {
//            ft3.remove(prev3);
//        }
//        // Create and show the dialog.
//        MyOkDialog dialog = MyOkDialog.newInstance(null, dialogTitle,
//                messageToShow, 0);
//        dialog.setCancelable(false);
//        ft3.add(dialog, Consts.Extras.OK_DIALOG_TAG).commit();
//    }
//
//    public static void showOkCancelDialog(
//            MyOkCancelDialog.OnOkCancelDialogCompleteListener okCancerlListner,
//            FragmentManager fm, String dialogTitle, String messageToShow,
//            String positiveButtonTitle, String cancelButtonTitle, int DIALOG_KEY) {
//        // We want to remove any currently showing
//        // dialog, so make our own transaction and take care of that here.
//        FragmentTransaction ft3 = fm.beginTransaction();
//        Fragment prev3 = (Fragment) fm
//                .findFragmentByTag(Consts.Extras.OK_CANCEL_DIALOG_TAG);
//
//        if (prev3 != null) {
//            ft3.remove(prev3);
//        }
//        // Create and show the dialog.
//        ft3.add(MyOkCancelDialog.newInstance(okCancerlListner, dialogTitle,
//                messageToShow, positiveButtonTitle, cancelButtonTitle,
//                DIALOG_KEY), Consts.Extras.OK_CANCEL_DIALOG_TAG).commit();
//    }


}


