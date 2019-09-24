package it.jiazhi.web;

import it.jiazhi.pojo.LimitPage;
import it.jiazhi.pojo.User;
import it.jiazhi.userservice.UserService;
import it.jiazhi.userservice.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/limitByIdPageServlet")
public class LimitByIdPageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //为了解决post  获取中文的乱码(客户端--->服务端)
        request.setCharacterEncoding("utf-8");
        //为了解决浏览器出现中文乱码(服务端--->客户端)
        response.setContentType("text/html;charset=utf-8");
        //1 获取参数 : 当前页码 + 每页显示条数
        int currentPage=1;  //默认值当前页
        int rows = 10; //默认值 默认一页显示的条数
        try {
            //获取请求行中的当前页的元素
            currentPage = Integer.parseInt(request.getParameter("currentPage"));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        try {
            //获取每页收到的数据
            rows = Integer.parseInt(request.getParameter("rows"));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        //1.2获取条件参数:姓名,籍贯,邮箱,  目的是:模糊查询
        Map<String, String[]> conditionMap = request.getParameterMap();
        //1.3 为了完成搜索条件内容的回显
        request.setAttribute("conditionMap", conditionMap);
        //2 调用业务层,查询分页显示数据
        UserService userService = new UserServiceImpl();
        LimitPage<User> limitPage = userService.findLimitPage(currentPage,rows,conditionMap);
        //请求转发,传到listPage.jsp内
        request.setAttribute("limitPage",limitPage );
        request.getRequestDispatcher("/listPage.jsp").forward(request,response );
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
