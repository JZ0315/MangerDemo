package it.jiazhi.web;

import it.jiazhi.pojo.User;
import it.jiazhi.userservice.UserService;
import it.jiazhi.userservice.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 查询的servlet,实现查询的信息显示到list.jsp
 */
@WebServlet("/selectServlet")
public class SelectServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //为了解决post  获取中文的乱码(客户端--->服务端)
        request.setCharacterEncoding("utf-8");
        //为了解决浏览器出现中文乱码(服务端--->客户端)
        response.setContentType("text/html;charset=utf-8");
        //调用业务层来实现数据的交互,创建一个list集合存储
        UserService selectService = new UserServiceImpl();
        List<User> userList =selectService.finddate();
        //获取userlist集合之后,让其值设置
        request.setAttribute("Userlist",userList);
        //请求转发
        request.getRequestDispatcher("/list.jsp").forward(request,response);
        return;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
