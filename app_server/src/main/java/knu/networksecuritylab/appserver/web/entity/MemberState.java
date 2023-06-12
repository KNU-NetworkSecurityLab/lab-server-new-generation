package knu.networksecuritylab.appserver.web.entity;

import lombok.Getter;

@Getter
public enum MemberState {

    PROFESSOR("지도교수"),
    STUDENT_READER("랩장"),
    STUDENT_NORMAL("부원"),
    STUDENT_RETIRED("졸업생"),
    STUDENT_LEAVE("휴학생");

    private final String displayName;

    MemberState(String displayValue) {
        this.displayName = displayValue;
    }
}
