package knu.networksecuritylab.appserver.app.service.file;

import knu.networksecuritylab.appserver.app.entity.book.Image;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface FileService {

    List<Image> multipartFilesStoreAndConvertToImages(List<MultipartFile> multipartFiles)
            throws IOException;

    byte[] imageConvertToBytes(Image image);

    void removeImages(List<String> imageNameList);
}
