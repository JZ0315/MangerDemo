package it.jiazhi.dao.Impl;

import it.jiazhi.pojo.User;
import it.jiazhi.utils.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class UserdaoImpl implements it.jiazhi.dao.Userdao {
    JdbcTemplate jt = new JdbcTemplate(JDBCUtils.getDataSource()); //切记获取jdbcutils中的连接池对象,不然获取不到对象

    /**
     * 数据层添加用户信息
     *
     * @param user
     */
    @Override
    public void addDateToDB(User user) {
        String sql = "insert into user(id, name, gender, age, address, qq, email, username, password) values(null, ?, ?, ?, ?, ?, ?, ?, ?)";
        Object[] params = {user.getName(), user.getGender(), user.getAge(), user.getAddress(), user.getQq(), user.getEmail(), user.getUsername(), user.getPassword()};
        jt.update(sql, params);
    }

    /**
     * 数据层查询所有用户的信息,返回一个UserList集合给业务层
     *
     * @return
     */
    @Override
    public List<User> finddate() {
        String sql = "select * from user";
        return jt.query(sql, new BeanPropertyRowMapper<User>(User.class));
    }

    /**
     * 登录用户密码的查询
     * 登录界面的业务层,查询前端请求的数据是否有
     *
     * @param user
     * @return
     */
    @Override
    public User queryUser(User user) {
        String sql = "select * from user where username=? and password=?";
        Object[] params = {user.getUsername(), user.getPassword()};
        return jt.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), params);
    }

    /**
     * 修改用户信息的数据层
     *
     * @param user
     * @return
     */
    @Override
    public void updateUser(User user) {
        //修改用户的sql语句
        String sql = "update user set name=?,gender=?,age=?,address=?,qq=?,email=?,username=?,password=? where id =?";
        Object[] params = {user.getName(), user.getGender(), user.getAge(), user.getAddress(), user.getQq(), user.getEmail(), user.getUsername(), user.getPassword(), user.getId()};
        jt.update(sql, params);

    }

    /**
     * 查询id,给予页面的回显
     */
    @Override
    public User queryuserid(String id) {
        String sql = "select * from user where id=?";
        return jt.queryForObject(sql,new BeanPropertyRowMapper<User>(User.class),id);
    }

    @Override
    public void delUserById(String id) {
        String sql = "delete from user where id=?";
        jt.update(sql,id);
    }
    /**
     * 批量删除数据
     * @param id
     */
    @Override
    public void DelUserByIds(String id) {
        //定义一个sql语句
        String sql = "delete from user where id = ?";
        //获取连接池执行删除功能
        jt.update(sql,id);
    }

    /**
     * 查询总记录数
     * @param conditionMap
     * @return
     */
    @Override
    public int findLimitPageCount(Map<String, String[]> conditionMap) {
    String sql = "select count(*) from user where 1=1";
    //keyset的语法  为了将map集合中的所有键存储到set集合中 ,因为map集合中没有迭代的功能
        Set<String> keySet = conditionMap.keySet();
        //准备参数的容器
        List<Object> paramlist = new ArrayList<Object>();
        //遍历所有的map集合的key值
        for (String key : keySet) {
            if ("currentPage".equals(key)||"rows".equals(key)){
                continue; //跳过本次循环,继续执行下一次循环
            }
            String value = conditionMap.get(key)[0];
            if(value!=null && !"".equals(value)){
                sql+="and"+key+"like ?";
                paramlist.add("%"+value+"%");
            }
        }
        Object[] params = paramlist.toArray();
        return jt.queryForObject(sql, Integer.class,params);
    }

    /**
     *查询分页显示的数据
     * @param start
     * @param rows
     * @param conditionMap
     * @return
     */
    @Override
    public List<User> findLimitPage(int start, int rows, Map<String, String[]> conditionMap) {
        String sql = "select * from user where 1=1";
        // 增加条件
        Set<String> keySet = conditionMap.keySet();
        // 准备参数的容器
        List<Object> paramList = new ArrayList<Object>();
        for (String key : keySet) {
            if("currentPage".equals(key) || "rows".equals(key)) {
                continue; // 跳过本次循环,继续执行下一次循环
            }
            String value = conditionMap.get(key)[0];
            if(value!=null && !"".equals(value)) {
                sql += " and " + key +  " like ?";
                paramList.add("%" + value + "%");
            }
        }
        sql +=" limit ?, ?";
        paramList.add(start);
        paramList.add(rows);
        Object[] paramArr = paramList.toArray();
        return jt.query(sql,new BeanPropertyRowMapper<User>(User.class),paramArr);
    }
}
