package knu.networksecuritylab.appserver.controller.book.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TagListDto {

    private List<String> tagNameList;
}
