package com.gurpreet.dialogmanager;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


/**
 * Created by Gurpreet on 13-05-2017.
 */

public class MicroInteraction {

    public MicroInteraction(final Builder builder) {

        final Dialog dialog = new Dialog(builder.context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        View view = LayoutInflater.from(builder.context).inflate(R.layout.microinteraction, null);
        dialog.setContentView(view);

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = builder.context.getResources().getDimensionPixelSize(R.dimen.micro_width);
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.CENTER;
        dialog.getWindow().setAttributes(lp);

        // animation
        if (builder.animType == AnimUtils.AnimDown) {
            dialog.getWindow().getAttributes().windowAnimations = R.style.AnimDown;

        } else if (builder.animType == AnimUtils.AnimFadeInOut) {
            dialog.getWindow().getAttributes().windowAnimations = R.style.AnimFadeInOut;

        } else if (builder.animType == AnimUtils.AnimZoomInOut) {
            dialog.getWindow().getAttributes().windowAnimations = R.style.AnimZoomInOut;
        }

        /*
        findviewById
        */
        ImageView headerImage = (ImageView) view.findViewById(R.id.ivMicroImage);
        headerImage.setImageResource(builder.headerImageResource);

        LinearLayout headerLayout = (LinearLayout) view.findViewById(R.id.llMicroHeader);
        //  If color is not set dynamically, don't override the color
        if (builder.headerBgColor > 0) {
            headerLayout.setBackgroundColor(builder.headerBgColor);
        }

        TextView txtTitle = (TextView) view.findViewById(R.id.tvMicroTitle);
        TextView txtContent = (TextView) view.findViewById(R.id.tvMicroDescription);

        Button btnPositive = (Button) view.findViewById(R.id.buttonMicroPositive);
        Button btnNegative = (Button) view.findViewById(R.id.buttonMicroNegative);

        /*
        apply customization to dialog
        */
        //  If color is not set dynamically, don't override the color
        if (builder.buttonPositiveTextColor > 0) {
            btnPositive.setTextColor(builder.buttonPositiveTextColor);
        }
        //  If color is not set dynamically, don't override the color
        if (builder.buttonNegativeTextColor > 0) {
            btnNegative.setTextColor(builder.buttonNegativeTextColor);
        }


        if (builder.headerImageResource != 0) {
            headerImage.setImageResource(builder.headerImageResource);
            headerLayout.setVisibility(View.VISIBLE);
        } else {
            headerLayout.setVisibility(View.GONE);
        }

        if (TextUtils.isEmpty(builder.title)) {
            txtTitle.setVisibility(View.GONE);
        } else {
            txtTitle.setVisibility(View.VISIBLE);
            txtTitle.setText(builder.title);
        }

        if (TextUtils.isEmpty(builder.content)) {
            txtContent.setVisibility(View.GONE);
        } else {
            txtContent.setVisibility(View.VISIBLE);
            txtContent.setText(builder.content);
        }

        if (TextUtils.isEmpty(builder.positiveText)) {
            btnPositive.setVisibility(View.GONE);
        } else {
            btnPositive.setVisibility(View.VISIBLE);
            btnPositive.setText(builder.positiveText);
            btnPositive.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    builder.onPositiveListener.onPositive(dialog);
                }
            });
        }

        if (TextUtils.isEmpty(builder.negativeText)) {
            btnNegative.setVisibility(View.GONE);
        } else {
            btnNegative.setVisibility(View.VISIBLE);
            btnNegative.setText(builder.negativeText);
            btnNegative.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    builder.onNegativeListener.onNegative(dialog);
                }
            });
        }

        if (!TextUtils.isEmpty(builder.typefaceTitle)) {
            Typeface ttf = Typeface.createFromAsset(builder.context.getAssets(), builder.typefaceTitle);
            txtTitle.setTypeface(ttf);
        }

        if (!TextUtils.isEmpty(builder.typefaceContent)) {
            Typeface ttf = Typeface.createFromAsset(builder.context.getAssets(), builder.typefaceContent);
            txtContent.setTypeface(ttf);
        }

        if (!TextUtils.isEmpty(builder.typefacePositiveButton)) {
            Typeface ttf = Typeface.createFromAsset(builder.context.getAssets(), builder.typefacePositiveButton);
            btnPositive.setTypeface(ttf);
        }

        if (!TextUtils.isEmpty(builder.typefaceNegativeButton)) {
            Typeface ttf = Typeface.createFromAsset(builder.context.getAssets(), builder.typefaceNegativeButton);
            btnNegative.setTypeface(ttf);
        }

        dialog.setCancelable(builder.isCancelable);
        dialog.setCanceledOnTouchOutside(builder.isCancelableTouchOutside);

        dialog.show();
    }


    public static class Builder {

        // default values
        private Context context;
        private int headerImageResource = 0;
        private String title = "Title";
        private String content = "Content Description";

        private boolean isCancelable = true;
        private boolean isCancelableTouchOutside = false;

        private String positiveText = "";
        private onPositiveListener onPositiveListener = new onPositiveListener() {
            @Override
            public void onPositive(Dialog droidDialog) {

            }
        };

        private String negativeText = "";
        private onNegativeListener onNegativeListener = new onNegativeListener() {
            @Override
            public void onNegative(Dialog droidDialog) {

            }
        };

        private String typefaceTitle = "";
        private String typefaceContent = "";
        private String typefacePositiveButton = "";
        private String typefaceNegativeButton = "";

        private int animType = 0;

        private int headerBgColor = 0;
        private int buttonPositiveTextColor = 0;
        private int buttonNegativeTextColor = 0;

        private boolean isDivider = false;

        public Builder(Context context) {
            this.context = context;
        }

        public MicroInteraction show() {
            return new MicroInteraction(this);
        }

        /*
        dialog topbar icon
        */
        public Builder headerImage(int headerImage) {
            this.headerImageResource = headerImage;
            return this;
        }

        /*
        dialog title
        */
        public Builder title(String title) {
            this.title = title;
            return this;
        }

        /*
        dialog content message
        */
        public Builder content(String content) {
            this.content = content;
            return this;
        }

        /*
        dialog cancelable flag
        */
        public Builder cancelable(boolean isCancelable, boolean isCancelableTouchOutside) {
            this.isCancelable = isCancelable;
            this.isCancelableTouchOutside = isCancelableTouchOutside;
            return this;
        }

        /*
        dialog positive button and click event handler
        */
        public Builder positiveButton(String positiveText, onPositiveListener onPositiveListener) {
            this.positiveText = positiveText;
            this.onPositiveListener = onPositiveListener;
            return this;
        }

        /*
        dialog negative button and click event handler
        */
        public Builder negativeButton(String negativeText, onNegativeListener onNegativeListener) {
            this.negativeText = negativeText;
            this.onNegativeListener = onNegativeListener;
            return this;
        }


        /*
        dialog custom typeface, applied to title
        -- put your .ttf file in assets/fonts directory
        -- pass font file name with extension in String type
        */
        public Builder typefaceTitle(String typefaceTitle) {
            this.typefaceTitle = typefaceTitle;
            return this;
        }

        /*
       dialog custom typeface, applied to content message
       -- put your .ttf/.otf file in assets/fonts directory
       -- pass font file name with extension in String type
       */
        public Builder typefaceContent(String typefaceContent) {
            this.typefaceContent = typefaceContent;
            return this;
        }

        /*
        dialog custom typeface, applied to positive Button
        -- put your .ttf file in assets/fonts directory
        -- pass font file name with extension in String type
        */
        public Builder typefacePositiveButton(String typefacePositiveButton) {
            this.typefacePositiveButton = typefacePositiveButton;
            return this;
        }

        /*
       dialog custom typeface, applied to negative button
       -- put your .ttf/.otf file in assets/fonts directory
       -- pass font file name with extension in String type
       */
        public Builder typefaceNegativeButton(String typefaceNegativeButton) {
            this.typefaceNegativeButton = typefaceNegativeButton;
            return this;
        }

        /*
        dialog appear and disappear animation
        */
        public Builder animation(int animType) {
            this.animType = animType;
            return this;
        }

        /*
        dialog color
        -- headerBgColor : dialog topbar background color
        -- buttonPositiveTextColor : dialog positive button text color
        -- buttonNegativeTextColor : dialog negative button text color
        */
        public Builder color(int headerBgColor, int buttonPositiveTextColor, int buttonNegativeTextColor) {
            this.headerBgColor = headerBgColor;
            this.buttonPositiveTextColor = buttonPositiveTextColor;
            this.buttonNegativeTextColor = buttonNegativeTextColor;
            return this;
        }


    }

    public interface onPositiveListener {
        void onPositive(Dialog dialog);
    }

    public interface onNegativeListener {
        void onNegative(Dialog dialog);
    }

}