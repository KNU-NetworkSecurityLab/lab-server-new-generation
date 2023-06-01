package knu.networksecuritylab.appserver.web.service;

import knu.networksecuritylab.appserver.web.entity.Member;
import knu.networksecuritylab.appserver.web.entity.dto.MemberRequestDto;

import java.util.List;

public interface MemberService {
    List<Member> getAllMembers();

    Long addMember(MemberRequestDto memberRequestDto);
}
