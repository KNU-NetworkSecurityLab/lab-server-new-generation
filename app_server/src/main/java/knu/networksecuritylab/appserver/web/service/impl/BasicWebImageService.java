package knu.networksecuritylab.appserver.web.service.impl;

import knu.networksecuritylab.appserver.app.exception.file.impl.ImageNotFoundException;
import knu.networksecuritylab.appserver.web.entity.WebImage;
import knu.networksecuritylab.appserver.web.repository.WebImageRepository;
import knu.networksecuritylab.appserver.web.service.WebImageService;
import knu.networksecuritylab.appserver.web.service.file.WebImageFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BasicWebImageService implements WebImageService {

    private final WebImageRepository webImageRepository;
    private final WebImageFileService fileService;

    @Override
    public byte[] webImages(Long imageId) {
        WebImage image = webImageRepository.findById(imageId).orElseThrow(ImageNotFoundException::new);
        return fileService.imageConvertToBytes(image);
    }
}
