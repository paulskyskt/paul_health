package com.paul.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.paul.dao.MemberDao;
import com.paul.pojo.Member;
import com.paul.service.MemberService;
import com.paul.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service(interfaceClass = MemberService.class)
@Transactional
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberDao memberDao;

    @Override
    public Member findByTelephone(String telephone) {
        return memberDao.findByTelephone(telephone);
    }

    @Override
    public void add(Member member) {
        if ( member.getPassword() != null ) {
            String md5 = MD5Utils.md5(member.getPassword());
            member.setPassword(md5);
            memberDao.add(member);
        }
    }

    @Override
    public List<Integer> findMemberCountByMonth(List<String> month) {

        ArrayList<Integer> arrayList = new ArrayList<>();
        //加上日期
        for (String m : month) {
            m = m + ".31";
            Integer count = memberDao.findMemberCountBeforeDate(m);
            arrayList.add(count);
        }
        return arrayList;
    }

    @Override
    public List<Member> findAll() {
        return memberDao.findAll();
    }
}
