package com.del.androidclasses.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import java.io.InputStream;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.ClipData;

public class DebugActivity extends Activity {
	String[] exceptionType = {
		"StringIndexOutOfBoundsException",
		"IndexOutOfBoundsException",
		"ArithmeticException",
		"NumberFormatException",
		"ActivityNotFoundException"
	};
	String[] errMessage= {
		"Invalid string operation\n",
		"Invalid list operation\n",
		"Invalid arithmetical operation\n",
		"Invalid toNumber block operation\n",
		"Invalid intent operation"
	};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		Intent intent = getIntent();
		String errMsg = "";
		String madeErrMsg = "";
		if (intent != null) {
			errMsg = intent.getStringExtra("error");
			String[] spilt = errMsg.split("\n");
			//errMsg = spilt[0];
			try {
				for (int j = 0; j < exceptionType.length; j++) {
					if (spilt[0].contains(exceptionType[j])) {
						madeErrMsg = errMessage[j];
						int addIndex = spilt[0].indexOf(exceptionType[j]) + exceptionType[j].length();
						madeErrMsg += spilt[0].substring(addIndex, spilt[0].length());
						break;
					}
				}
				if (madeErrMsg.isEmpty()) madeErrMsg = errMsg;
			} catch (Exception e) {}
		}
		final String error = madeErrMsg;
        AlertDialog.Builder bld = new AlertDialog.Builder(this);
		bld.setTitle("An error occured");
		bld.setMessage(madeErrMsg);
		bld.setPositiveButton("Copy Error", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE); 
					ClipData clip = ClipData.newPlainText("Copy", error);
					clipboard.setPrimaryClip(clip);
					finish();
				}
			});
		bld.setNeutralButton("End Application", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					finish();
				}
			});
		bld.create().show();
    }
}