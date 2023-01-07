package com.hadi.ecommerce.service;

import com.hadi.ecommerce.FileStorageProperties;
import com.hadi.ecommerce.exception.FileNotFoundException;
import com.hadi.ecommerce.exception.FileStorageException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class FileStorageService {
    private final Path fileStorageLocation;

    @Autowired
    public FileStorageService(FileStorageProperties prop) {
        this.fileStorageLocation = Paths.get(prop.getUploadDir())
                .toAbsolutePath()
                .normalize();

        try {
            Files.createDirectories(this.fileStorageLocation);
        }catch (IOException e) {
            throw new FileStorageException("fail make directory images", e);
        }
    }

    public String storeFile(MultipartFile file) {
        try {
            String fileNameExtension = StringUtils.getFilenameExtension(StringUtils.cleanPath(file.getOriginalFilename()));
            String fileName = UUID.randomUUID().toString() + "." + fileNameExtension;
            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            return fileName;
        }catch (IOException e) {
            throw new FileStorageException("fail to save file", e);
        }
    }

    public Resource loadFileAsResource(String fileName) {
        try{
            Path filePath = this.fileStorageLocation.resolve(fileName);
            UrlResource resource = new UrlResource(filePath.toUri());
            if(!resource.exists()) {
                throw new FileNotFoundException("file not found");
            }
            return resource;

        }catch (MalformedURLException e){
            throw new FileNotFoundException("file not found.");
        }
    }

}
