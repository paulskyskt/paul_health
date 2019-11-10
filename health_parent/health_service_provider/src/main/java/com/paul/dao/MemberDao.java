package com.paul.dao;

import com.paul.pojo.Member;

public interface MemberDao {
    Member findByTelephone(String telephone);

    void add(Member member);
}


