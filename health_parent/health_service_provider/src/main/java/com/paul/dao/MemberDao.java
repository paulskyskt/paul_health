package com.paul.dao;

import com.paul.pojo.Member;

import java.util.List;

public interface MemberDao {
    Member findByTelephone(String telephone);

    void add(Member member);

    Integer findMemberCountBeforeDate(String m);


}



