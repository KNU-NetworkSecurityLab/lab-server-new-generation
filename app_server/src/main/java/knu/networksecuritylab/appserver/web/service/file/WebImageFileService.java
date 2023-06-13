package knu.networksecuritylab.appserver.web.service.file;

import knu.networksecuritylab.appserver.app.exception.file.impl.FileStorageException;
import knu.networksecuritylab.appserver.app.exception.file.impl.ImageNotReadable;
import knu.networksecuritylab.appserver.app.exception.file.impl.InvalidFileExtensionException;
import knu.networksecuritylab.appserver.web.entity.WebImage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Slf4j
@Service
public class WebImageFileService implements WebFileService {

    private Path fileLocation;

    @Value("${image.uploadPath}")
    private final String STORAGE_PATH = "C:\\Users\\Administrator\\lab-service\\lab_service_image";

    @PostConstruct
    private void init() {
        this.fileLocation = Paths.get(STORAGE_PATH).toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.fileLocation);
        } catch (IOException e) {
            throw new FileStorageException();
        }
    }

    @Override
    public WebImage multipartFileStoreAndConvertToImage(MultipartFile multipartFile)
            throws IOException {
        String storedFileName = multipartFileStore(multipartFile);
        return convertToImage(multipartFile, storedFileName);
    }

    private static WebImage convertToImage(MultipartFile multipartFile, String fileName) {
        long fileSize = multipartFile.getSize();
        return WebImage.builder()
                .imageName(fileName)
                .imageSize(fileSize)
                .build();
    }

    private String multipartFileStore(MultipartFile multipartFile) throws IOException {
        String extension = getFileExtension(multipartFile);
        String uuid = UUID.randomUUID().toString();
        String fileName = uuid + extension;
        File file = new File(fileLocation + File.separator + fileName);
        multipartFile.transferTo(file);

        file.setWritable(true);
        file.setReadable(true);
        return fileName;
    }

    private String getFileExtension(MultipartFile multipartFile) {
        String contentType = multipartFile.getContentType();
        if (contentType.contains("image/jpeg")) {
            return ".jpg";
        } else if (contentType.contains("image/png")) {
            return ".png";
        }

        throw new InvalidFileExtensionException();
    }

    @Override
    public byte[] imageConvertToBytes(WebImage webImage) {
        try {
            FileInputStream fileInputStream =
                    new FileInputStream(STORAGE_PATH + File.separator + webImage.getImageName());
            return fileInputStream.readAllBytes();
        } catch (IOException e) {
            throw new ImageNotReadable();
        }
    }

    @Override
    public void removeImages(String imageName) {
        try {
            Files.delete(Paths.get(STORAGE_PATH + File.separator + imageName));
        } catch (IOException ignored) {
        }
    }
}
