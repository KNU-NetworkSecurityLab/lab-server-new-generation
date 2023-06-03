package knu.networksecuritylab.appserver.web.service.file;

import knu.networksecuritylab.appserver.web.entity.WebImage;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ThesisFileService {

    WebImage multipartFileStoreAndConvertToImage(MultipartFile multipartFile)
            throws IOException;

    byte[] imageConvertToBytes(WebImage webImage);

    void removeImages(String imageName);
}
