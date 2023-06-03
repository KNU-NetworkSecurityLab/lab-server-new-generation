package knu.networksecuritylab.appserver.web.service.file;

import knu.networksecuritylab.appserver.web.entity.ThesisImage;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ThesisFileService {

    ThesisImage multipartFileStoreAndConvertToImage(MultipartFile multipartFile)
            throws IOException;

    byte[] imageConvertToBytes(ThesisImage thesisImage);

    void removeImages(String imageName);
}
