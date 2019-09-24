package it.jiazhi.web;

import it.jiazhi.userservice.UserService;
import it.jiazhi.userservice.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/batchRemoveServlet")
public class BatchRemoveServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //为了解决post  获取中文的乱码(客户端--->服务端)
        request.setCharacterEncoding("utf-8");
        //为了解决浏览器出现中文乱码(服务端--->客户端)
        response.setContentType("text/html;charset=utf-8");
        //获取前端发送的数据请求
        String[] ids = request.getParameterValues("ids");
        //调用业务层来删除数据
       UserService _ids =new UserServiceImpl();
       _ids.DelUserByIds(ids);
       //删除数据之后请求转发,回到list.jsp页面
       request.getRequestDispatcher(request.getContextPath()+"/selectServlet").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
