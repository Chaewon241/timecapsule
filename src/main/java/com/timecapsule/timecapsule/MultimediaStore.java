package com.timecapsule.timecapsule;

import com.timecapsule.timecapsule.domain.Multimedia;
import com.timecapsule.timecapsule.domain.TimeCapsule;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class MultimediaStore {
    @Value("${file.dir}")
    private String fileDir;

    public String getFullPath(String filename) {
        return fileDir + filename;
    }

    public List<Multimedia> storeFiles(TimeCapsule timeCapsule, List<MultipartFile> multipartFiles) throws IOException {
        List<Multimedia> storeFileResult = new ArrayList<>();
        for (MultipartFile multipartFile : multipartFiles) {
            if (!multipartFile.isEmpty()) {
                storeFileResult.add(storeFile(timeCapsule, multipartFile));
            }
        }
        return storeFileResult;
    }

    public Multimedia storeFile(TimeCapsule timeCapsule, MultipartFile multipartFile) throws IOException { // multipartFile을 받아 UploadFile을 반환
        if (multipartFile.isEmpty()) {
            return null;
        }

        String originalFilename = multipartFile.getOriginalFilename();
        String storeFileName = createStoreFileName(originalFilename);
        multipartFile.transferTo(new File(getFullPath(storeFileName)));

        return new Multimedia(timeCapsule, originalFilename, storeFileName);
    }

    private String createStoreFileName(String originalFilename) {
        String ext = extractExt(originalFilename);
        String uuid = UUID.randomUUID().toString();
        return uuid + "." + ext;
    }

    private String extractExt(String originalFilename) {
        int pos = originalFilename.lastIndexOf(".");
        return originalFilename.substring(pos + 1);
    }
}
