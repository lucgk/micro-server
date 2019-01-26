package com.micro.web.service.personal;

import com.micro.web.common.utils.QueryByPage;
import com.micro.web.dao.personal.UserInfoDao;
import com.micro.web.entity.personal.UserInfo;
import com.micro.web.entity.personal.UserInfoQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserInfoService {

    @Autowired
    private UserInfoDao userInfoDao;

    /**
     * 保存用户信息
     * @param userInfo
     */
    public void saveUserInfo(UserInfo userInfo) {
        if(userInfo.getId()<=0){//新增
            userInfoDao.insertUserInfo(userInfo);
        }else {//修改
            userInfoDao.updateUserInfoById(userInfo);
        }
    }

    /**
     * 分页获取用户信息
     * @param userInfoQuery
     * @return
     */
    public List<UserInfo> pageQuery(UserInfoQuery userInfoQuery) {

        return  userInfoDao.pageQuery(userInfoQuery);
    }

    public long queryTotalCount(UserInfoQuery userInfoQuery) {
        return  userInfoDao.queryTotalCount(userInfoQuery);
    }

    /**
     * 根据id获取用户信息
     * @param id
     * @return
     */
    public UserInfo queryUserInfoById(int id) {
        UserInfo userInfo = new UserInfo();
        userInfo.setId(id);
        return  userInfoDao.queryUserInfoById(userInfo);
    }
}
