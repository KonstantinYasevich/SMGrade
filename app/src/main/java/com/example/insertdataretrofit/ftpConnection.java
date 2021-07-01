package com.example.insertdataretrofit;

import org.apache.commons.net.ftp.FTPClient;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ftpConnection implements Runnable {
    String hostAddress, log, password, fileName, ftpfileName;
    public ftpConnection(String mhostAddress, String mlog, String mpassword, String mfileName, String mftpfileName){
        hostAddress = mhostAddress;
        log = mlog;
        password = mpassword;
        fileName = mfileName;
        ftpfileName = mftpfileName;
    }
    public void run ()  {
        FTPClient fClient = new FTPClient();
        try {
        FileInputStream fInput = new FileInputStream(fileName);
        String fs = "h102555691.nichost.ru/docs/text/" + ftpfileName;
        try {
            fClient.connect(hostAddress);
            fClient.enterLocalPassiveMode();
            fClient.setFileType(fClient.BINARY_FILE_TYPE);
            fClient.login(log, password);
            fClient.storeFile(fs, fInput);
            fClient.logout();
            fClient.disconnect();
        } catch (IOException ex) {
            System.err.println(ex);
        }} catch (FileNotFoundException e){};
    }
}
