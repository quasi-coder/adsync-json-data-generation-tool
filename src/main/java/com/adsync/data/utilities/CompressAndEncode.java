package com.adsync.data.utilities;

import org.apache.commons.io.FileUtils;
import java.io.*;
import java.util.Base64;
import java.util.zip.DeflaterOutputStream;

public class CompressAndEncode {

    private static void compressFile(File raw, File compressed)
            throws IOException {
        InputStream in = new FileInputStream(raw);
        OutputStream out =
                new DeflaterOutputStream(new FileOutputStream(compressed));
        shovelInToOut(in, out);
        in.close();
        out.close();
    }

    private static void shovelInToOut(InputStream in, OutputStream out)
            throws IOException {
        byte[] buffer = new byte[1000];
        int len;
        while ((len = in.read(buffer)) > 0) {
            out.write(buffer, 0, len);
        }
    }

    private static byte[] fileToString(File file) {
        byte[] content = null;
        try {
            content = FileUtils.readFileToByteArray(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }

    private static void JsonEncode(File compressedFile, File encodedCompressedFile) throws IOException{
        byte[] encodedBytes = Base64.getEncoder().encode(fileToString(compressedFile));
        try (FileOutputStream stream = new FileOutputStream(encodedCompressedFile)) {
            stream.write(encodedBytes);
        }
    }

    private static void createEncodedFiles() throws IOException{

        File jsonDir = new File(System.getProperty("user.dir") + "/data/json");

        if(jsonDir.exists()){
        for (File fileEntry : jsonDir.listFiles()) {
            if (fileEntry.isDirectory()) {
                for (File file: fileEntry.listFiles()) {
                    File deflatedDir = new File(System.getProperty("user.dir") + "/data/deflated/"+fileEntry.getName(),file.getName().replace("json", "dfl"));
                    if (!deflatedDir.getParentFile().exists()) {
                        deflatedDir.getParentFile().mkdirs();
                    }
                    File deflatedFile = new File(deflatedDir.getParentFile(),deflatedDir.getName());
                    compressFile(file, deflatedFile);
                    File encodedDir = new File(System.getProperty("user.dir") + "/data/encoded/"+fileEntry.getName(),deflatedFile.getName().replace("dfl", "txt"));
                    if (!encodedDir.getParentFile().exists()) {
                        encodedDir.getParentFile().mkdirs();
                    }
                    File encodedFile = new File(encodedDir.getParentFile(), encodedDir.getName());
                    JsonEncode(deflatedFile, encodedFile);
                }
            }
        }
        }else{
            throw new RuntimeException("Json Directory not found");
        }
    }

    public static void main(String[] args) throws IOException {
        long st = System.currentTimeMillis();
        createEncodedFiles();
        long et = System.currentTimeMillis();
        System.out.println("Time taken in seconds to create encoded Files from json:"+ (et-st)/1000);
        System.out.println("Encoded Files are created under following directory: " +System.getProperty("user.dir") + "/data/encoded");
    }
}


