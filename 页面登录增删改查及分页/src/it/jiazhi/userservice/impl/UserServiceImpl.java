package it.jiazhi.userservice.impl;

import it.jiazhi.dao.Impl.UserdaoImpl;
import it.jiazhi.dao.Userdao;
import it.jiazhi.pojo.LimitPage;
import it.jiazhi.pojo.User;
import it.jiazhi.userservice.UserService;
import java.util.List;
import java.util.Map;

public class UserServiceImpl implements UserService {
    Userdao userdao = new UserdaoImpl();
    /**
     * 添加用户
     * @param user
     */
    @Override
    public void addDateToDB(User user) {
        /*将封装好的User对象传递给数据层
        然后数据层调用数据库来存储数据*/
        userdao.addDateToDB(user);
    }
    /**
     * 查询所有用户的信息,显示在list.jsp页面上
     **/
    public List<User> finddate() {
        Userdao user = new UserdaoImpl();
        List<User> userlist = null;
        try {
            userlist = user.finddate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userlist;
    }

    /**
     * 登录界面的业务层,查询前端请求的数据是否有
     * @param user
     * @return
     */
    @Override
    public User Loginuser(User user) {
        User LoginUser = null;
        /*业务层这一块需要做个异常处理
        * 一旦出现帐号密码错误,mysql会查出来null值
        * 这时候如果没有异常处理,那么程序不会执行了,会直接在前端报错
        * 这样你程序的健壮性不是很好
        * */
        try {
            LoginUser = userdao.queryUser(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return LoginUser;
    }
    /**
     * 查到对应的id值来进行信息的回显,业务层
     * @return
     */
    @Override
    public User queryuserId(String id) {
        User user = userdao.queryuserid(id);
        return user;
    }

    /**
     * 修改用户数据
     * @param user
     */
    @Override
    public void updateUser(User user) {
        try {
            //调用数据层来执行
            userdao.updateUser(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 通过id来删除用户
     * @param id
     */
    @Override
    public void delUserById(String id) {
        try {
            userdao.delUserById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 批量删除数据
     * @param ids
     */
    @Override
    public void DelUserByIds(String[] ids) {
        //拿到传的数据,遍历每一个数据,逐个删除
        //做个异常处理,遇到null值继续执行
        try {
            for (int i = 0; i < ids.length; i++) {
                userdao.DelUserByIds(ids[i]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public LimitPage<User> findLimitPage(int currentPage, int rows, Map<String, String[]> conditionMap) {
        //1查询总记录数:select count(*) from 表名
        int allPagecount = userdao.findLimitPageCount(conditionMap);
        //2计算总页数
        int allPage = (allPagecount+rows-1)/rows;
        // 3 查询分页显示的数据: select * from 表名 limit 开始索引, 每页显示条数
        int start = (currentPage-1)*rows;
       List<User> list =  userdao.findLimitPage(start,rows,conditionMap);
        LimitPage<User> userLimitPage = new LimitPage<>();
        userLimitPage.setCurrentPage(currentPage);
        userLimitPage.setRows(rows);
        userLimitPage.setAllPage(allPage);
        userLimitPage.setAllPagecount(allPagecount);
        userLimitPage.setPageDate(list);
        return userLimitPage;
    }
}
