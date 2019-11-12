package com.paul.service;

import com.paul.pojo.Member;

public interface MemberService {
    Member findByTelephone(String telephone);

    void add(Member m);
}


