package it.jiazhi.dao;

import it.jiazhi.pojo.User;

import java.util.List;
import java.util.Map;

public interface Userdao {
    void addDateToDB(User user);

    /**
     * 查询所有数据为list.jsp
     * @return
     */
    List<User> finddate();

    User queryUser(User user);

    void updateUser(User user);

    User queryuserid(String id);

    void delUserById(String id);
    /**
     * 批量删除数据
     * @param id
     */
    void DelUserByIds(String id);

    int findLimitPageCount(Map<String, String[]> conditionMap);

    List<User> findLimitPage(int start, int rows, Map<String, String[]> conditionMap);
}
