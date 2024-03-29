package knu.networksecuritylab.appserver.app.service.book.impl;

import knu.networksecuritylab.appserver.app.controller.book.dto.BookInfoResponseDto;
import knu.networksecuritylab.appserver.app.controller.book.dto.BookListResponseDto;
import knu.networksecuritylab.appserver.app.controller.book.dto.BookRegisterRequestDto;
import knu.networksecuritylab.appserver.app.entity.book.Book;
import knu.networksecuritylab.appserver.app.entity.book.BookTag;
import knu.networksecuritylab.appserver.app.entity.book.Image;
import knu.networksecuritylab.appserver.app.entity.book.Tag;
import knu.networksecuritylab.appserver.app.exception.book.impl.BookDuplicateException;
import knu.networksecuritylab.appserver.app.exception.book.impl.BookNotFoundException;
import knu.networksecuritylab.appserver.app.repository.book.BookRepository;
import knu.networksecuritylab.appserver.app.service.book.BookService;
import knu.networksecuritylab.appserver.app.service.book.ImageService;
import knu.networksecuritylab.appserver.app.service.book.TagService;
import knu.networksecuritylab.appserver.app.service.file.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BasicBookService implements BookService {

    private final BookRepository bookRepository;
    private final TagService tagService;
    private final FileService fileService;
    private final ImageService imageService;

    @Override
    @Transactional
    public Long registerBook(
            final List<MultipartFile> files,
            final BookRegisterRequestDto bookRegisterRequestDto) throws IOException {
        Book book = checkDuplicateBook(bookRegisterRequestDto);

        setBookImages(files, book);
        setBookTags(bookRegisterRequestDto.getBookTagList(), book);

        bookRepository.save(book); // cascade 설정으로 image도 모두 저장
        return book.getId();
    }

    private Book checkDuplicateBook(final BookRegisterRequestDto bookRegisterRequestDto) {
        bookRepository.findByBookName(bookRegisterRequestDto.getBookName())
                .ifPresent(book -> {
                    if (book.getBookAuthor().equals(bookRegisterRequestDto.getBookAuthor())) {
                        throw new BookDuplicateException();
                    }
                });

        return Book.from(bookRegisterRequestDto);
    }

    private void setBookImages(List<MultipartFile> files, Book book) throws IOException {
        List<Image> images = fileService.multipartFilesStoreAndConvertToImages(files);
        for (Image image : images) {
            image.setBook(book);
        }
    }

    private void setBookTags(List<String> bookTagList, Book book) {
        List<Tag> tags = tagService.tagArrangement(bookTagList);
        for (Tag tag : tags) {
            book.addTag(tag);
        }
    }

    @Override
    public List<BookListResponseDto> bookList() {
        List<BookListResponseDto> bookList = new ArrayList<>();
        bookRepository.findBookRandomList(PageRequest.of(0, 10))
                .forEach(book -> {
                    List<BookTag> bookTags = book.getBookTags();
                    List<String> tagList = tagService.bookTagsToTagNameList(bookTags);
                    bookList.add(book.toBookListDto(tagList));

                });
        return bookList;
    }

    @Override
    public BookInfoResponseDto bookInfo(final Long bookId) {
        Book book = bookRepository.findByIdWithBookTags(bookId)
                .orElseThrow(BookNotFoundException::new);

        List<Long> imageList = bookImagesToImageIdList(book.getImages());
        List<String> tagList = tagService.bookTagsToTagNameList(book.getBookTags());

        return book.toBookInfoDto(tagList, imageList);
    }

    private List<Long> bookImagesToImageIdList(final List<Image> images) {
        List<Long> imageList = new ArrayList<>();
        for (Image image : images) {
            imageList.add(image.getId());
        }
        return imageList;
    }

    @Override
    public List<BookListResponseDto> bookSearch(final String keyword) {
        List<BookListResponseDto> bookList = new ArrayList<>();
        bookRepository.searchBookByName(keyword)
                .forEach(book -> {
                    List<BookTag> bookTags = book.getBookTags();
                    List<String> tagList = tagService.bookTagsToTagNameList(bookTags);
                    bookList.add(book.toBookListDto(tagList));
                });
        return bookList;
    }

    @Override
    @Transactional
    public void removeBook(Long bookId) {
        Book book = bookRepository.findById(bookId).orElseThrow(BookNotFoundException::new);
        List<String> imageNameList = imageService.imagesToImageNameList(book.getImages());
        fileService.removeImages(imageNameList);
        bookRepository.delete(book);
    }
}
