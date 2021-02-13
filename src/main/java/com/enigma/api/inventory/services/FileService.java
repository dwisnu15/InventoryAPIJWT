package com.enigma.api.inventory.services;

import com.enigma.api.inventory.entities.AbstractEntity;
import com.enigma.api.inventory.entities.Item;
import org.apache.tika.mime.MimeTypeException;
import org.apache.tomcat.jni.Buffer;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface FileService {

    String upload(String folder, BufferedInputStream inputStream) throws IOException, MimeTypeException;
    void download(String folder, String filename, OutputStream outputStream) throws IOException;
}
