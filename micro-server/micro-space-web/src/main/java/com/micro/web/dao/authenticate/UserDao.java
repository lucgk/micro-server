package com.micro.web.dao.authenticate;

import com.micro.web.dao.MyBatisCrudRepository;
import com.micro.web.entity.authenticate.User;

public interface UserDao extends MyBatisCrudRepository {

    User queryUserByAccount(String account);
}
