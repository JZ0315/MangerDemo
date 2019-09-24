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

@WebServlet("/findByIdServlet")
public class FindByIdServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //为了解决post  获取中文的乱码(客户端--->服务端)
        request.setCharacterEncoding("utf-8");
        //为了解决浏览器出现中文乱码(服务端--->客户端)
        response.setContentType("text/html;charset=utf-8");
        //1 修改数据需要修改界面出现个用户信息的回显
        String id = request.getParameter("id");
        //1.1实现用户回显需要通过一个唯一值确定是哪个用户
        UserService userService = new UserServiceImpl();
        User showUser = userService.queryuserId(id);
        //获取到id值的人把他设置一个值放到请求中
       request.setAttribute("showUser",showUser);
        //重定向
        request.getRequestDispatcher(request.getContextPath()+"/update.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
