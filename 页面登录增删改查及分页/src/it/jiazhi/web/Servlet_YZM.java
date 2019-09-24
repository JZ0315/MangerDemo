package it.jiazhi.web;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/**
 * 绘制验证码
 */
@WebServlet("/servlet_YZM")
public class Servlet_YZM extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 /*       //为了解决post  获取中文的乱码(客户端--->服务端)
        request.setCharacterEncoding("utf-8");
        //为了解决浏览器出现中文乱码(服务端--->客户端)
        response.setContentType("it.jiazhi.text/html;charset=utf-8");*/
        //要想出来图片,那么需要去定义一个画布
        int width = 110;
        int height = 40;
        // 1 生成画布对象 类似与 带画笔的画板
        BufferedImage buff = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        // 4 从画布上获取画笔
        Graphics g = buff.getGraphics();
        // 3 填充背景色 为 白色
        g.setColor(Color.white);
        g.fillRect(0, 0, width, height);
        // 5 绘制边框
        g.setColor(Color.red);
        //减1是因为他边框会有一个像素,所以需要给边框的像素减去1才可以显示在规定的画布上面
        g.drawRect(0, 0, width - 1, height - 1);
        //定义数据  绘制4个随机字符
        String str = "QWERTYUIOPASDFGHJKLZXCVBNMqwertyuiopasdfghjklzxcvbnm0123456789";
        Random r = new Random();
        // 7.1 字符串拼接准备
        String str_code = "";
        // 6.3 绘制4个
        for (int i = 0; i < 4; i++) {
            //6.3.2 调整字体
            g.setFont(new Font("圆幼",Font.BOLD,23));
            //6.3.3 改变字体颜色
            g.setColor(new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255)));
            //整数拼接字符串转化成字符串
            String string = str.charAt(r.nextInt(str.length()))+"";
            g.drawString(string,5+i*25,25);
            str_code+=string;
        }
        // 7 将生成的验证码输出到控制台
        System.out.println(str_code);
        request.getSession().setAttribute("str_code",str_code );
        // 8 绘制干扰线
        for (int i = 0; i <10; i++) {
            //给不同的线弄不同的颜色
            g.setColor(new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255)));
            //化没有规律的线条
            g.drawLine(r.nextInt(width),r.nextInt(height),r.nextInt(width),r.nextInt(height));
        }
        //展示到浏览器
        ImageIO.write(buff,"jpg",response.getOutputStream());
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
