package com.paul.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.paul.constant.MessageConstant;
import com.paul.dao.MemberDao;
import com.paul.dao.OrderDao;
import com.paul.dao.OrderSettingDao;
import com.paul.entity.Result;
import com.paul.pojo.Order;
import com.paul.pojo.OrderSetting;
import com.paul.service.OrderService;
import com.paul.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import com.paul.pojo.Member;
import java.util.Date;
import java.util.List;
import java.util.Map;
/**
 * @author Think
 */
@Service(interfaceClass = OrderService.class)
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private MemberDao memberDao;

    @Autowired
    private OrderSettingDao orderSettingDao;

    @Override
    public Result order(Map map) throws Exception {
        /*  idCard: "500103199602230019"
            name: "123123"
            orderDate: "2019-11-11"
            setmealId: "12"
            sex: "1"
            telephone: "15823192046"
            validateCode: "2342"*/
        //判断 选择的日期 是否提前设置 （健壮性！） findCountByOrderDate orderDate
        String date_str = (String) map.get("orderDate");
        Date date = DateUtils.parseString2Date(date_str);
        OrderSetting orderSetting = orderSettingDao.findByOrderDate(date);
        if(orderSetting==null){
            return new Result(false,MessageConstant.SELECTED_DATE_CANNOT_ORDER);
        }
        //判断 选择的日期 预约人数满没有
        if(orderSetting.getReservations() >= orderSetting.getNumber()){
            return new Result(false,MessageConstant.ORDER_FULL);
        }

        //判断 是不是会员 通过手机号 判断
        String telephone = (String) map.get("telephone");
        Member member = memberDao.findByTelephone(telephone);

        //判断 是否重复预约 同一个用户 同一天 同一个套餐
        if(member !=null){
            Integer memberId = orderSetting.getId();
            Integer setmealId = Integer.parseInt((String) map.get("setmealId"));
            Order order = new Order(memberId, date, null, null, setmealId);
            List<Order> orderList = orderDao.findByCondition(order);
           if(orderList !=null && orderList.size() > 0){
               return new Result(false,MessageConstant.HAS_ORDERED);
           }
        }

        //可以预约，设置预约人数加一
        orderSetting.setReservations(orderSetting.getReservations()+1);
        orderSettingDao.editReservationsByOrderDate(orderSetting);

        if(member == null){
            //添加为会员
            member = new Member();
            member.setName((String)map.get("name"));
            member.setPhoneNumber(telephone);
            member.setIdCard((String)map.get("idCard"));
            member.setSex((String)map.get("sex"));
            member.setRegTime(new Date());
            memberDao.add(member);
        }

        //保存预约信息到预约表
        Order order=new Order(member.getId(),date,(String)map.get("orderType"),Order.ORDERSTATUS_NO,Integer.parseInt((String)map.get("setmealId")));

        orderDao.add(order);

        return new Result(true,MessageConstant.ORDER_SUCCESS,order.getId());

    }

    @Override
    public Map findById(Integer id) {
        return orderDao.findById4Detai(id);
    }
}
