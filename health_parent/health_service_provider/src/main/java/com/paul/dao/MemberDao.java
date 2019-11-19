package com.paul.dao;

import com.paul.pojo.Member;

import java.util.List;

public interface MemberDao {
    Member findByTelephone(String telephone);

    void add(Member member);

    Integer findMemberCountBeforeDate(String m);

    List<Member> findAll();

    List<String> findAllAddress();

    Integer findCountByAddress(String address);

    Integer findMemberCountByDate(String today);

    Integer findMemberTotalCount();

    Integer findMemberCountAfterDate(String thisWeekMonday);
}







