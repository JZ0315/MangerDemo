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
 * 登录的servlet,实现查询信息之前的登录
 */
@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //为了解决post  获取中文的乱码(客户端--->服务端)
        request.setCharacterEncoding("utf-8");
        //为了解决浏览器出现中文乱码(服务端--->客户端)
        response.setContentType("text/html;charset=utf-8");
        //获取请求数据中的参数
        String verifycode = null;
        verifycode = request.getParameter("verifycode");
        /*因为在验证码的servlet中已经设置了session,所以验证码可以通过获取session对象的值和前端请求过来的验证码进行对比
        判断验证码的真确与否
        */
        String str_code = (String) request.getSession().getAttribute("str_code");
        if(verifycode==null||!str_code.equalsIgnoreCase(verifycode)){
            //设置请求参数的值error,为验证码错误!!!
            request.setAttribute("error", "验证码错误!!!");
            //请求转发(如果是服务器内部的资源文件,用请求转发,如果有外部资源或者内部服务器资源用重定向)
            request.getRequestDispatcher("/login.jsp").forward(request,response);
            return;
        }
        //1.1使用完验证码需要把验证码的session清除掉,避免出现恶意的绕过验证码的技术
        request.getSession().removeAttribute("str_code");
        /*
            如果验证码正确,直接进行下面的用户名以及密码的验证
        */
        //1 首先获取到前端输入的用户名以及密码
        //1.1 创建user对象
        User user = new User();
        //1.2 封装user对象的值
        try {
            //2 最好将用户名及密码封装到user对象中
            BeanUtils.populate(user,request.getParameterMap());
            //1.2.1 将异常放到最大化
        } catch (Exception e) {
            e.printStackTrace();
        }
        //3 创建serviceImpl对象,利用多态,调用业务层查数据
        UserService userService = new UserServiceImpl();
        User loginUser = userService.Loginuser(user);
        //3.1 如果loginUser不为null
        if(loginUser!=null){
            //3.2表示有用户及密码,登录成功,设置一个session记录登录的用户
            request.getSession().setAttribute("loginUser", loginUser);
            //3.3请求转发
            request.getRequestDispatcher("/index.jsp").forward(request,response);
//            response.sendRedirect("/index.jsp");
            System.out.println("Hello");

            return;
        }else{
            //4 登录失败给出提示信息
            request.setAttribute("error","帐号或密码错误!!!");
            request.getRequestDispatcher("/login.jsp").forward(request,response);
            return;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
