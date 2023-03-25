package com.murad.operationsservice.service;

import lombok.RequiredArgsConstructor;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.*;


@Service
@RequiredArgsConstructor
public class FileService {

    @Value("${ftp.server.host}")
    private String host;

    @Value("${ftp.server.port}")
    private int port;

    @Value("${ftp.server.username}")
    private String username;

    @Value("${ftp.server.password}")
    private String password;

    @Value("${ftp.server.directory}")
    private String directory;

    private final FTPClient ftpClient;

    public boolean uploadFile(String localFilePath, String remoteFileName) {
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(localFilePath);
            return ftpClient.storeFile(directory + "/" + remoteFileName, inputStream);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public boolean downloadFile(String remoteFileName, String localFilePath) {
        OutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(localFilePath);
            return ftpClient.retrieveFile(directory + "/" + remoteFileName, outputStream);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public boolean deleteFile(String remoteFileName) {
        try {
            return ftpClient.deleteFile(directory + "/" + remoteFileName);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @PostConstruct
    public void connectToFtp() {
        try {
            ftpClient.connect(host, port);
            ftpClient.login(username, password);
            ftpClient.changeWorkingDirectory(directory);
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            ftpClient.enterLocalPassiveMode();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @PreDestroy
    public void disconnectFromFtp() {
        try {
            ftpClient.logout();
            ftpClient.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
