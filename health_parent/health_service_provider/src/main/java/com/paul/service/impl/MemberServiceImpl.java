package com.paul.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.paul.dao.MemberDao;
import com.paul.pojo.MapData;
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

    @Override
    public List<MapData> findAddressAndCount() {
        ArrayList<MapData> list = new ArrayList<>();

        /*{name: '北京',value: 3},
                        {name: '天津',value:19},
                        {name: '上海',value:14},
                        {name: '重庆',value:1},
                        {name: '河北',value:2},
                        {name: '河南',value:5},
                        {name: '云南',value:10},*/

        //去重复的查询
        List<String> allAddress = memberDao.findAllAddress();
        //
        if(allAddress == null || allAddress.size() <= 0) return null;

        for (String address : allAddress) {
            //去除查询出来为null的地址
            if(address == null || "null".equals(address) || address.length() <= 0) continue;
            //根据地址查询对应会员数量
            Integer count = memberDao.findCountByAddress(address);
            //封装成对象
            MapData mapData = new MapData(address,count);
            //添加进集合
            list.add(mapData);
        }

        return list;
    }
}
