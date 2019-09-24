package it.jiazhi.userservice;

import it.jiazhi.pojo.LimitPage;
import it.jiazhi.pojo.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    /**
     * 添加用户
     * @param user
     */
    void addDateToDB(User user);

    /**
     * 查询用户,显示在list.jsp页面上
     * @return
     */
    List<User> finddate();
    /**
     * 登录的业务层
     */
    User Loginuser(User user);

    /**
     * 查到对应的id值来进行信息的回显,业务层
     * @return
     */
    User queryuserId(String id);

    /**
     * 修改用户信息的业务层
     * @param user
     * @return
     */
    void updateUser(User user);

    /**
     * 通过id来删除用户
     * @param id
     */
    void delUserById(String id);

    /**
     * 批量删除数据
     * @param ids
     */
    void DelUserByIds(String[] ids);

    LimitPage<User> findLimitPage(int currentPage, int rows, Map<String, String[]> conditionMap);
}
