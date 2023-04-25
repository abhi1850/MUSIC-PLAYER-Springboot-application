package com.example.musicplayer.helper;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Component
public class SongUploadHelper {
    public final String UPLOAD_DIR = "D:\\abhis\\Downloads\\6th SEM\\OOADJ_LAB\\Project\\FINAL_IMPLEMENTATION\\Music_Player\\src\\main\\resources\\static\\Uploads";

    public boolean uploadFile(MultipartFile file)
    {
        boolean f = false;
        try {
            Files.copy(file.getInputStream(), Paths.get(UPLOAD_DIR + File.separator+file.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
            f = true;
        }catch(Exception e){
            e.printStackTrace();
        }

        return f;
    }
}
