package it.jiazhi.web;

import it.jiazhi.pojo.User;
import it.jiazhi.userservice.UserService;
import it.jiazhi.userservice.impl.UserServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * web层调用业务层实现添加用户的功能
 */
@WebServlet("/addServlet")
public class addServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //为了解决post  获取中文的乱码(客户端--->服务端)
        request.setCharacterEncoding("utf-8");
        //为了解决浏览器出现中文乱码(服务端--->客户端)
        response.setContentType("it/jiazhi/text/html;charset=utf-8");
        User user = new User();
        //1. 添加第一步需要调用业务层去执行操作
        try {
            //2. 需要将前台输入的数据封装起来到user类中
            BeanUtils.populate(user, request.getParameterMap());
        } catch (Exception e) {
            e.printStackTrace();
        }
        UserService userService = new UserServiceImpl();
        //3. 通过封装传到业务层
        userService.addDateToDB(user);
        //重定向
//        response.sendRedirect(request.getContextPath()+"/SelectServlet");
        response.sendRedirect(request.getContextPath()+"/selectServlet");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
