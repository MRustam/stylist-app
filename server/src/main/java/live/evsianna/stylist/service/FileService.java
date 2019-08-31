package live.evsianna.stylist.service;

import live.evsianna.stylist.exception.FileNotFoundException;
import live.evsianna.stylist.exception.FileStorageException;
import live.evsianna.stylist.model.dto.UploadFileResponse;
import live.evsianna.stylist.properties.FileStorageProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Rustam Mamedov
 */

@Service
@RequiredArgsConstructor
public class FileService {

    private final FileStorageProperties fileProperties;

    private Path filePath;

    @PostConstruct
    public void init() {
        filePath = Paths.get(fileProperties.getUploadDir()).toAbsolutePath().normalize();
        try {
            Files.createDirectories(filePath);
        } catch (Exception e) {
            throw new FileStorageException("Could't create the Directory.", e);
        }
    }

    public List<UploadFileResponse> uploadFiles(final MultipartFile[] files) {
        return Arrays.stream(files)
                .map(this::uploadFile)
                .collect(Collectors.toList());
    }

    private UploadFileResponse uploadFile(final MultipartFile file) {
        final String fileName = storeFile(file);

        final String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/file/download-file/")
                .path(fileName)
                .toUriString();

        return UploadFileResponse.builder()
                .fileName(fileName)
                .fileDownloadUri(fileDownloadUri)
                .fileType(file.getContentType()).size(file.getSize())
                .build();
    }

    private String storeFile(MultipartFile file) {
        final String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            if(fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }
            final Path targetLocation = filePath.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return fileName;
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    public Resource loadFileAsResource(String fileName) {
        try {
            final Path path = filePath.resolve(fileName).normalize();
            final Resource resource = new UrlResource(path.toUri());
            if(resource.exists()) {
                return resource;
            } else {
                throw new FileNotFoundException("File not found " + fileName);
            }
        } catch (MalformedURLException ex) {
            throw new FileNotFoundException("File not found " + fileName, ex);
        }
    }
}
