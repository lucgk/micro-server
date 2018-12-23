/*
package com.micro.web.service.authenticate;

import com.micro.web.dao.authenticate.UserDao;
import com.micro.web.entity.authenticate.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//@Service
//@Transactional
public class UserService implements UserDetailsService {

    @Autowired
    private UserDao  userDao;

    public UserDetails loadUserByUsername(String account) throws UsernameNotFoundException {
        User user = userDao.queryUserByAccount(account);
        if (user == null) {
            throw new UsernameNotFoundException("用户名不对");
        }
        return user;
    }
}
*/
