package com.rmgx.dialoglibrary;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.gurpreet.dialogmanager.AnimUtils;
import com.gurpreet.dialogmanager.MicroInteraction;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void showDialog(View view) {
        new MicroInteraction.Builder(this)
                .headerImage(R.drawable.ic_success_green)
                .title(getString(R.string.title))
                .content(getString(R.string.content))
                .cancelable(false, false)
                .animation(AnimUtils.AnimDown)
                .positiveButton("OKAY", new MicroInteraction.onPositiveListener() {
                    @Override
                    public void onPositive(Dialog dialog) {
                        dialog.cancel();
                    }
                })
                .negativeButton("CANCEL", new MicroInteraction.onNegativeListener() {
                    @Override
                    public void onNegative(Dialog dialog) {
                        dialog.cancel();
                    }
                })
                .show();
    }
}
