package knu.networksecuritylab.appserver.app.exception.file;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public abstract class FileException extends RuntimeException {

    private final FileErrorCode fileErrorCode;
}
