package com.example.insertdataretrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.MediaRouteButton;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class GRADE extends AppCompatActivity {
    String grade;
    String fileName, date_time;
    Date date_timeD;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_g_r_a_d_e);
        String WS = getIntent().getStringExtra("WS");
        TextView WST = (TextView)findViewById(R.id.textView);
        WST.setText(WS);
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


        //System.out.println(Login.);
        ImageButton bt1  = (ImageButton) findViewById(R.id.imageButton);
        ImageButton bt2  = (ImageButton) findViewById(R.id.imageButton2);
        ImageButton bt3  = (ImageButton) findViewById(R.id.imageButton3);
        View.OnClickListener oclbt1 = new View.OnClickListener() {
                    public void onClick(View v) {

                grade = "1";
                Runnable r1 = new insResult(WS, grade);
                Thread t1 = new Thread(r1);
                t1.start();
                try {

                    t1.join();}
                catch (InterruptedException e){}
                        AlertDialog dialog = new AlertDialog.Builder(GRADE.this)
                                .setTitle("Оставить отзыв?")
                                .setMessage("Если вы хотите оставить отзыв нажмите Да")
                                .setPositiveButton("ДА", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent intent = new Intent(GRADE.this, review.class);
                                        intent.putExtra("WS", WS);
                                        System.out.println(WS);
                                        startActivity(intent);
                                    }
                                })
                                .setNegativeButton("НЕТ", null)
                                .create();
                        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
                            private static final int AUTO_DISMISS_MILLIS = 10000;
                            @Override
                            public void onShow(final DialogInterface dialog) {
                                final Button defaultButton = ((AlertDialog) dialog).getButton(AlertDialog.BUTTON_NEGATIVE);
                                final CharSequence negativeButtonText = defaultButton.getText();
                                new CountDownTimer(AUTO_DISMISS_MILLIS, 100) {
                                    @Override
                                    public void onTick(long millisUntilFinished) {
                                        defaultButton.setText(String.format(
                                                Locale.getDefault(), "%s (%d)",
                                                negativeButtonText,
                                                TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) + 1 //add one so it never displays zero
                                        ));
                                    }
                                    @Override
                                    public void onFinish() {
                                        if (((AlertDialog) dialog).isShowing()) {
                                            dialog.dismiss();
                                        }
                                    }
                                }.start();
                            }
                        });
                        dialog.show();


            }

        };
        View.OnClickListener oclbt2 = new View.OnClickListener() {
            public void onClick(View v) {

                grade = "2";
                Runnable r1 = new insResult(WS, grade);
                Thread t1 = new Thread(r1);
                t1.start();
                try {

                    t1.join();}
                catch (InterruptedException e){}
                AlertDialog dialog = new AlertDialog.Builder(GRADE.this)
                        .setTitle("Оставить отзыв?")
                        .setMessage("Если вы хотите оставить отзыв нажмите Да")
                        .setPositiveButton("ДА", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(GRADE.this, review.class);
                                intent.putExtra("WS", WS);
                                System.out.println(WS);
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton("НЕТ", null)
                        .create();
                dialog.setOnShowListener(new DialogInterface.OnShowListener() {
                    private static final int AUTO_DISMISS_MILLIS = 10000;
                    @Override
                    public void onShow(final DialogInterface dialog) {
                        final Button defaultButton = ((AlertDialog) dialog).getButton(AlertDialog.BUTTON_NEGATIVE);
                        final CharSequence negativeButtonText = defaultButton.getText();
                        new CountDownTimer(AUTO_DISMISS_MILLIS, 100) {
                            @Override
                            public void onTick(long millisUntilFinished) {
                                defaultButton.setText(String.format(
                                        Locale.getDefault(), "%s (%d)",
                                        negativeButtonText,
                                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) + 1 //add one so it never displays zero
                                ));
                            }
                            @Override
                            public void onFinish() {
                                if (((AlertDialog) dialog).isShowing()) {
                                    dialog.dismiss();
                                }
                            }
                        }.start();
                    }
                });
                dialog.show();
            }

        };
        View.OnClickListener oclbt3 = new View.OnClickListener() {
            public void onClick(View v) {

                grade = "3";
                Runnable r1 = new insResult(WS, grade);
                Thread t1 = new Thread(r1);
                t1.start();
                try {

                    t1.join();}
                catch (InterruptedException e){}
                AlertDialog dialog = new AlertDialog.Builder(GRADE.this)
                        .setTitle("Оставить отзыв?")
                        .setMessage("Если вы хотите оставить отзыв нажмите Да")
                        .setPositiveButton("ДА", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(GRADE.this, review.class);
                                intent.putExtra("WS", WS);
                                System.out.println(WS);
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton("НЕТ", null)
                        .create();
                dialog.setOnShowListener(new DialogInterface.OnShowListener() {
                    private static final int AUTO_DISMISS_MILLIS = 10000;
                    @Override
                    public void onShow(final DialogInterface dialog) {
                        final Button defaultButton = ((AlertDialog) dialog).getButton(AlertDialog.BUTTON_NEGATIVE);
                        final CharSequence negativeButtonText = defaultButton.getText();
                        new CountDownTimer(AUTO_DISMISS_MILLIS, 100) {
                            @Override
                            public void onTick(long millisUntilFinished) {
                                defaultButton.setText(String.format(
                                        Locale.getDefault(), "%s (%d)",
                                        negativeButtonText,
                                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) + 1 //add one so it never displays zero
                                ));
                            }
                            @Override
                            public void onFinish() {
                                if (((AlertDialog) dialog).isShowing()) {
                                    dialog.dismiss();
                                }
                            }
                        }.start();
                    }
                });
                dialog.show();
            }

        };

        bt1.setOnClickListener(oclbt1);
        bt2.setOnClickListener(oclbt2);
        bt3.setOnClickListener(oclbt3);



    }

    }
