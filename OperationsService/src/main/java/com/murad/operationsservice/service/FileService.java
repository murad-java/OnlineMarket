package com.murad.operationsservice.service;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.murad.operationsservice.entity.ProductImageEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Slf4j
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

    public boolean uploadFile(MultipartFile file) {
        InputStream inputStream = null;
        try {
            inputStream = file.getInputStream();
            if (!ftpClient.isAvailable() || !ftpClient.isConnected()) connectToFtp();
            return ftpClient.storeFile(directory + "/" + file.getOriginalFilename(), inputStream);
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

    public byte[] convertInputStreamToByteArray(InputStream inputStream)  {
        try {
            byte[] bytes = IOUtils.toByteArray(inputStream);
            return bytes;
        }catch (IOException e){
            log.error(e.getMessage());
            return new byte[0];
        }
    }

    public byte[] downloadFile(String path) {
        InputStream inputStream = null;
        try {
            connectToFtp();
            inputStream = ftpClient.retrieveFileStream(path);
            return convertInputStreamToByteArray(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
            //close();
            return null;
        } finally {

            close();
        }
    }

    private void close(){
        if (ftpClient != null && (ftpClient.isConnected()||ftpClient.isAvailable())) {
            try {
                ftpClient.completePendingCommand();
                ftpClient.logout();
                ftpClient.disconnect();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public boolean deleteFileByName(String remoteFileName) {
        try {
            if (!ftpClient.isAvailable() || !ftpClient.isConnected()) connectToFtp();
            return ftpClient.deleteFile(directory + "/" + remoteFileName);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteFileByPath(String filePath) {
        try {
            if (!ftpClient.isAvailable() || !ftpClient.isConnected()) connectToFtp();
            return ftpClient.deleteFile(filePath);
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
            ftpClient.setDefaultTimeout(60000);
            ftpClient.setConnectTimeout(30000);
            ftpClient.setSoTimeout(30000);
            //ftpClient.reinitialize();
            ftpClient.enterLocalActiveMode();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @PreDestroy
    public void disconnectFromFtp() {

            if (ftpClient != null) {
                try {
                    ftpClient.completePendingCommand();
                    ftpClient.logout();
                    ftpClient.disconnect();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

    }

    public List<byte[]> downloadFile(List<String> images) {
        List<byte[]> bytes = images.stream().map(this::downloadFile).collect(Collectors.toList());
        return bytes;
    }
}
