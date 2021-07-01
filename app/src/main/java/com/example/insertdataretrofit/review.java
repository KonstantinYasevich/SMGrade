package com.example.insertdataretrofit;

import androidx.appcompat.app.AppCompatActivity;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Paint;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class review extends AppCompatActivity {
    MediaRecorder mediaRecorder;
    MediaPlayer mediaPlayer;
    String fileName, date_time, ftpfileName;
    Date date_timeD;
    Spinner spinner;
    DBHelper dbHelper;
    List<String> ws1;
    String path;
    Context mcontext = this;
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        dbHelper = new DBHelper(this);
        setContentView(R.layout.activity_review);
        EditText editText = (EditText)findViewById(R.id.editText1);
        TextView mTimer = (TextView)findViewById(R.id.myImageViewText);
        String WS = getIntent().getStringExtra("WS");
        spinner = (Spinner)findViewById(R.id.User);

        Button ins1  = (Button) findViewById(R.id.ButtonOK);
        ImageButton rec  = (ImageButton) findViewById(R.id.imageButton4);
        Runnable r1 = new ParseJsonUSER(mcontext);
        Thread t1 = new Thread(r1);
        t1.start();
        try {
            t1.join();

        } catch (InterruptedException e) {
            System.out.println("бля");
        }
        SpinnerData(WS);
       // path = this.getDir("audio", Context.MODE_PRIVATE).getPath();
        path = review.this.getExternalFilesDir(null).getAbsolutePath().toString() + "/grade/";
        File folder = new File(path);
        if(!folder.exists()) {
            System.out.println("dasdasdasd");
            folder.mkdirs();
            Log.i("IO", ""+folder.exists());
            System.out.println("dasdasdasd123123");
        }
        else {
            System.out.println(folder.getAbsolutePath().toString());
        }    System.out.println(folder.getAbsolutePath().toString() + "123");
        new CountDownTimer(120000, 1000) {

            public void onTick(long millisUntilFinished) {
                mTimer.setText("Осталось: "
                        + millisUntilFinished / 1000);
            }

            public void onFinish() {
                finish();
            }
        }
                .start();
        View.OnClickListener oclins = new View.OnClickListener() {
            public void onClick(View v) {
                System.out.println(editText.getText().toString().trim());
                String UserName = spinner.getSelectedItem().toString().trim();
                String text = editText.getText().toString();

                SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                date_timeD = new Date();
                date_time = formater.format(date_timeD);
                fileName = path + date_time + WS + "text.txt";
                ftpfileName = date_time + "text.txt";

                System.out.println(fileName);
                File tempFile = new File(fileName);
               try {
                    System.out.println("asdasdewqeqwvbnvbnhjgjghj");
                File outFile = new File(fileName);
                if (outFile.exists()) {
                    System.out.println("удаляем файл");
                    outFile.delete(); }


                FileOutputStream fos = new FileOutputStream(tempFile);;
                fos.write(text.getBytes());
                fos.close(); }
                catch (IOException e ){System.out.println("fffffffffffffffffffffffff   ");

                }
               Runnable ftpr = new ftpConnection("ftp.h102555691.nichost.ru", "h102555691_ftp", "W1UEzlUyYr", fileName, ftpfileName);
               Thread ftpt = new Thread(ftpr);
               ftpt.start();
               Runnable insrestxt = new insResultText(WS, "http://h102555691.nichost.ru/text/" + ftpfileName, UserName );
               Thread insrestxtt = new Thread(insrestxt);
               insrestxtt.start();
                finish();
            }
        };

        View.OnClickListener oclrec = new View.OnClickListener() {
            public void onClick(View v) {
                SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                date_timeD = new Date();
                date_time = formater.format(date_timeD);
                fileName = path + date_time + WS + "record.3gpp";
                ftpfileName = date_time + "record.3gpp";
                String UserName = spinner.getSelectedItem().toString().trim();
                System.out.println(fileName);
                AlertDialog dialog = new AlertDialog.Builder(review.this)
                        .setTitle("Идет запись")
                        .setMessage("Осталось:")
                        .setPositiveButton("отправить", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            recordStop();
                                Runnable ftpr = new ftpConnection("ftp.h102555691.nichost.ru", "h102555691_ftp", "W1UEzlUyYr", fileName, ftpfileName);
                                Thread ftpt = new Thread(ftpr);
                                ftpt.start();
                                Runnable insrestxt = new insResultAudio(WS, "http://h102555691.nichost.ru/text/" + ftpfileName, UserName );
                                Thread insrestxtt = new Thread(insrestxt);
                                insrestxtt.start();
                                finish();

                            }
                        })
                        .setNegativeButton("отмена", null)
                        .create();
                dialog.setOnShowListener(new DialogInterface.OnShowListener() {
                    private static final int AUTO_DISMISS_MILLIS = 60000;
                    @Override
                    public void onShow(final DialogInterface dialog) {
                        final Button defaultButton = ((AlertDialog) dialog).getButton(AlertDialog.BUTTON_NEGATIVE);

                        final CharSequence negativeButtonText = defaultButton.getText();
                        new CountDownTimer(AUTO_DISMISS_MILLIS, 100) {
                            Integer s = 60;
                            @Override

                            public void onTick(long millisUntilFinished) {
                                if (s==60){
                                    recordStart();
                                    s=s-1;
                                }
                                ((AlertDialog) dialog).setMessage("Осталось: " + (millisUntilFinished/1000));
                            }
                            @Override
                            public void onFinish() {
                                recordStop();
                                Runnable ftpr = new ftpConnection("ftp.h102555691.nichost.ru", "h102555691_ftp", "W1UEzlUyYr", fileName, ftpfileName);
                                Thread ftpt = new Thread(ftpr);
                                ftpt.start();
                                Runnable insrestxt = new insResultAudio(WS, "http://h102555691.nichost.ru/text/" + ftpfileName, UserName );
                                Thread insrestxtt = new Thread(insrestxt);
                                insrestxtt.start();
                                if (((AlertDialog) dialog).isShowing()) {
                                    dialog.dismiss();
                                }
                                finish();
                            }
                        }.start();
                    }
                });
                dialog.show();


            }
        };
        rec.setOnClickListener(oclrec);
        ins1.setOnClickListener(oclins);
        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if (hasFocus) {
                    ins1.setVisibility(View.VISIBLE);

                }
            }
        });

    }
    private void SpinnerData(String WS) {
        System.out.println("ew");
        ws1 = dbHelper.getUSERfromWS(WS);
        ws1.add(0,"");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, ws1);
        spinner.setAdapter(dataAdapter);
        System.out.println("ew1");
        System.out.println(WS);

    }
    public void recordStart() {
        try {
            releaseRecorder();

            File outFile = new File(fileName);
            if (outFile.exists()) {
                System.out.println("удаляем файл");
                outFile.delete(); }
            System.out.println("создаем файл");
            mediaRecorder = new MediaRecorder();
            mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
            mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
            mediaRecorder.setOutputFile(fileName);
            mediaRecorder.prepare();
            mediaRecorder.start();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void recordStop() {
        //if (mediaRecorder != null) {
            System.out.println("стопим файл");
            mediaRecorder.stop();
        //}
    }
    private void releaseRecorder() {
        if (mediaRecorder != null) {
            mediaRecorder.release();
            mediaRecorder = null;
        }
    }

    private void releasePlayer() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }

    }
    /*public static void ftpConn(String hostAddress, String log, String password, String fileName) throws FileNotFoundException {
        FTPClient fClient = new FTPClient();
        FileInputStream fInput = new FileInputStream(fileName);
        String fs = "Yes.txt";
        try {
            fClient.connect(hostAddress);
            fClient.enterLocalPassiveMode();
            fClient.login(log, password);
            fClient.storeFile(fs, fInput);
            fClient.logout();
            fClient.disconnect();
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }*/

    @Override
    protected void onDestroy() {
        super.onDestroy();
        releasePlayer();
        releaseRecorder();
    }
}