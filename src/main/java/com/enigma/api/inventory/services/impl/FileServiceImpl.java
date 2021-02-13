package com.enigma.api.inventory.services.impl;

import com.enigma.api.inventory.entities.AbstractEntity;
import com.enigma.api.inventory.services.FileService;
import org.apache.tika.config.TikaConfig;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.mime.MediaType;
import org.apache.tika.mime.MimeType;
import org.apache.tika.mime.MimeTypeException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {

    @Value("${application.data.dir}")
    private String dataDir;

    @Override
    public String upload(String folder, BufferedInputStream inputStream) throws IOException {

        //configuring tika
        TikaConfig config = TikaConfig.getDefaultConfig();
        Metadata metadata = new Metadata();
        MediaType mediaType = config.getMimeRepository().detect(inputStream, metadata);
        MimeType mimeType;
        try {
            mimeType = config.getMimeRepository().forName(mediaType.toString());
        } catch (MimeTypeException e) {
            throw new IOException();
        }
        String extension = mimeType.getExtension();
        File dir = new File(dataDir, folder);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        String filename = UUID.randomUUID().toString() + extension;
        File file = new File(dir, filename);
        try (OutputStream out = new FileOutputStream(file)) {
            int length;
            byte[] data = new byte[8192];
            while ((length = inputStream.read(data)) > -1) {
                out.write(data, 0, length);
            }
        }
        return filename;
    }

    @Override
    public void download(String folder, String filename, OutputStream out) throws IOException {
        File dir = new File(dataDir, folder);
        File file = new File(dir, filename);

        try (InputStream in = new FileInputStream(file)) {
            int length;
            byte[] data = new byte[8192];
            while ((length = in.read(data)) > -1) {
                out.write(data, 0, length);
            }
        }
    }
}
