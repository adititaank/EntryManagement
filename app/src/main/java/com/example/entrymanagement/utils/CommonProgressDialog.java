package com.example.entrymanagement.utils;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;

import com.example.entrymanagement.R;


public class CommonProgressDialog {
    public static Dialog LoadingSpinner(Context mContext){
        Dialog pd = new Dialog(mContext, android.R.style.Theme_DeviceDefault_Dialog);
        View view = LayoutInflater.from(mContext).inflate(R.layout.progress_dialog_layout, null);
        pd.requestWindowFeature(Window.FEATURE_NO_TITLE);
        pd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        pd.setCancelable(false);
        pd.setContentView(view);
        return pd;}
}
