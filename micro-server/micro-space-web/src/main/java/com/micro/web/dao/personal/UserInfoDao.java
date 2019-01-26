package com.micro.web.dao.personal;

import com.micro.web.common.utils.QueryByPage;
import com.micro.web.entity.personal.UserInfo;
import com.micro.web.entity.personal.UserInfoQuery;

import java.util.List;

public interface UserInfoDao {

    /**
     * 新增
     * @param userInfo
     */
    void insertUserInfo(UserInfo userInfo);

    /**
     * 修改
     * @param userInfo
     */
    void updateUserInfoById(UserInfo userInfo);

    /**
     * 分页获取用户信息
     * @param queryByPage
     * @return
     */
    List<UserInfo> pageQuery(UserInfoQuery queryByPage);

    long queryTotalCount(UserInfoQuery queryByPage);

    /**
     * 根据id获取用户信息
     * @param userInfo
     * @return
     */
    UserInfo queryUserInfoById(UserInfo userInfo);
}
