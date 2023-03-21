package knu.networksecuritylab.appserver.exception.file.impl;

import knu.networksecuritylab.appserver.exception.file.FileErrorCode;
import knu.networksecuritylab.appserver.exception.file.FileException;

public class InvalidFileExtensionException extends FileException {

    public InvalidFileExtensionException() {
        super(FileErrorCode.INVALID_FILE_EXTENSION);
    }
}
