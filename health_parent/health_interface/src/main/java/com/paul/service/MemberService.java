package com.paul.service;

import com.paul.pojo.Member;

import java.util.List;

public interface MemberService {
    Member findByTelephone(String telephone);

    void add(Member m);

    List<Integer> findMemberCountByMonth(List<String> list);

    List<Member> findAll();
}




