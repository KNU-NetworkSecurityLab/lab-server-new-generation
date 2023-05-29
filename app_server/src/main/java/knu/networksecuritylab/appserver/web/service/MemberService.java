package knu.networksecuritylab.appserver.web.service;

import knu.networksecuritylab.appserver.web.entity.Member;
import org.springframework.stereotype.Service;

import java.util.List;

public interface MemberService {
    List<Member> memberList();
}
