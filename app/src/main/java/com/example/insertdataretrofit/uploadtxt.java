package com.example.insertdataretrofit;

import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class uploadtxt  implements Runnable {
    String pathToOurFile;

    public uploadtxt(String path){
        pathToOurFile=path;


    }

        public void run() {
            HttpURLConnection connection = null;
            DataOutputStream outputStream = null;
            DataInputStream inputStream = null;

            String urlServer = "http://h102555691.nichost.ru/uploadtxt.php";
            //String urlServer = "";
            String lineEnd = "\r\n";
            Log.d("testo",pathToOurFile);
            String twoHyphens = "--";
            String boundary =  "*****";

            int bytesRead, bytesAvailable, bufferSize;
            byte[] buffer;
            int maxBufferSize = 1*1024*1024;

            try
            {
                FileInputStream fileInputStream = new FileInputStream(new File(pathToOurFile) );

                URL url = new URL(urlServer);
                connection = (HttpURLConnection) url.openConnection();

                // Allow Inputs &amp; Outputs.
                connection.setDoInput(true);
                connection.setDoOutput(true);
                connection.setUseCaches(false);

                // Set HTTP method to POST.
                connection.setRequestMethod("POST");

                connection.setRequestProperty("Connection", "Keep-Alive");
                connection.setRequestProperty("Content-Type", "multipart/form-data;boundary="+boundary);

                outputStream = new DataOutputStream( connection.getOutputStream() );
                outputStream.writeBytes(twoHyphens + boundary + lineEnd);
                outputStream.writeBytes("Content-Disposition: form-data; name=\"gruppa\"" + lineEnd);
                outputStream.writeBytes(lineEnd);
                outputStream.writeBytes(twoHyphens + boundary + lineEnd);
                outputStream.writeBytes("Content-Disposition: form-data; name=\"uploadedfile\";filename=\"" + pathToOurFile +"\";gruppa=\""+"\";" + lineEnd);
                outputStream.writeBytes(lineEnd);//  эта часть работает

                Log.d("piz", pathToOurFile);
                bytesAvailable = fileInputStream.available();
                bufferSize = Math.min(bytesAvailable, maxBufferSize);
                buffer = new byte[bufferSize];

                // Read file
                bytesRead = fileInputStream.read(buffer, 0, bufferSize);


                while (bytesRead > 0)
                {
                    outputStream.write(buffer, 0, bufferSize);
                    bytesAvailable = fileInputStream.available();
                    bufferSize = Math.min(bytesAvailable, maxBufferSize);
                    bytesRead = fileInputStream.read(buffer, 0, bufferSize);
                }

                outputStream.writeBytes(lineEnd);
                outputStream.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);

                // Responses from the server (code and message)
                int serverResponseCode = connection.getResponseCode();
                String serverResponseMessage = connection.getResponseMessage();
                InputStream inputStream1 = connection.getInputStream();
                String dataResponse = "";
                try {
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream,"UTF-8");
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                    String inputLine = "";
                    while((inputLine = bufferedReader.readLine()) != null){
                        dataResponse += inputLine;
                    }
                    bufferedReader.close();
                    inputStream.close();
                }
                catch (NullPointerException e){}


                Log.d("server"+serverResponseCode,serverResponseMessage + dataResponse );

                fileInputStream.close();
                outputStream.flush();
                outputStream.close();
                Log.d("d", "ok");
            }
            catch (Exception ex)
            {
                //Exception handling
                ex.printStackTrace();
            }

        }

}
