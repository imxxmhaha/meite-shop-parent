package com.mayikt.member.dao;

import com.mayikt.member.MemberApplication;
import com.mayikt.member.entity.UserEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @author xxm
 * @create 2019-06-10 0:22
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MemberApplication.class)
public class UserDaoTest {
    @Autowired
    private UserDao userDao;

    @Test
    public void test(){
        UserEntity userEntity = new UserEntity();
        userEntity.setUserId(123L);
        userEntity.setMobile("17600297244");
        userEntity.setEmail("416660553@qq.com");
        userEntity.setPassword("1214564as6d4");
        userEntity.setSex('1');
        userEntity.setIsAvalible('1');
        Integer insert = userDao.insert(userEntity);
        System.out.println(insert);
    }
}