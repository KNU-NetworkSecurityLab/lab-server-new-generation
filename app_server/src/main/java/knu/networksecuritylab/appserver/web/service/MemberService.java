package knu.networksecuritylab.appserver.web.service;

import knu.networksecuritylab.appserver.web.entity.Member;
import knu.networksecuritylab.appserver.web.entity.dto.MemberRequestDto;

import java.util.List;

public interface MemberService {
    List<Member> getAllMembers();
    List<Member> studentList();
    Member getProfessor();
    Long addMember(MemberRequestDto memberRequestDto);
}
