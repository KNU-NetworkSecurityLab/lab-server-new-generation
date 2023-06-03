package knu.networksecuritylab.appserver.web.service.impl;

import knu.networksecuritylab.appserver.app.entity.book.Image;
import knu.networksecuritylab.appserver.app.exception.file.impl.ImageNotFoundException;
import knu.networksecuritylab.appserver.app.repository.book.ImageRepository;
import knu.networksecuritylab.appserver.app.service.file.FileService;
import knu.networksecuritylab.appserver.web.entity.ThesisImage;
import knu.networksecuritylab.appserver.web.repository.ThesisImageRepository;
import knu.networksecuritylab.appserver.web.service.ThesisImageService;
import knu.networksecuritylab.appserver.web.service.file.ThesisImageFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BasicThesisImageService implements ThesisImageService {

    private final ThesisImageRepository thesisImageRepository;
    private final ThesisImageFileService fileService;

    @Override
    public byte[] thesisImages(Long imageId) {
        ThesisImage image = thesisImageRepository.findById(imageId).orElseThrow(ImageNotFoundException::new);
        return fileService.imageConvertToBytes(image);
    }
}
