package hello.servlet.basic.request;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

@WebServlet(name = "requestParamServlet", urlPatterns = "/request-param")
public class RequestParamServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("전체 파라미터 조회 - start");
        req.getParameterNames().asIterator()
                        .forEachRemaining(paramName -> System.out.println(paramName + " = " + req.getParameter(paramName)));
        System.out.println("전체 파라미터 조회 - end");
        System.out.println();

        System.out.println("단일 파라미터 조회");
        String username = req.getParameter("username");
        String age = req.getParameter("age");
        System.out.println("username = " + username + ", age = " + age);
        System.out.println();

        System.out.println("이름이 같은 복수 파라미터 조회");
        String[] usernames = req.getParameterValues("username");
        for (String name : usernames) {
            System.out.println(name);
        }
        System.out.println();

        resp.getWriter().write("success");



        resp.setContentType("text/html");
        resp.setCharacterEncoding("utf-8");
        PrintWriter writer = resp.getWriter();
        writer.println("<html>");
        writer.println("<body>");
        writer.println(" <div>안녕?</div>");
        writer.println("</body>");
        writer.println("</html>");
    }
}
