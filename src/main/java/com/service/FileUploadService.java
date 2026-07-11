package com.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class FileUploadService {

    private final Cloudinary cloudinary;

    public String uploadFile(MultipartFile file) throws IOException {
        try {
            log.info("Uploading file to cloudinary... {}", file);

            // Upload the file to Cloudinary
            Map uploadResult = cloudinary.uploader().upload(file.getBytes(),
                    ObjectUtils.asMap(
                            "public_id", "swapkart_item_" + UUID.randomUUID().toString(), // Gives it a unique name
                            "folder", "swapkart_uploads" // Organizes them nicely in your Cloudinary account
                    ));

            // Return the secure (HTTPS) URL provided by Cloudinary
            return uploadResult.get("secure_url").toString();

        } catch (IOException e) {
            log.error("Failed to upload image to Cloudinary", e);
            throw new IOException("Failed to upload image");
        }
    }
}