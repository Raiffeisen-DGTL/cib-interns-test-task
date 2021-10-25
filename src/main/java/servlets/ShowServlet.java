package servlets;

import socks.SocksService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ShowServlet extends HttpServlet {
    private final SocksService socksService;

    public ShowServlet(SocksService socksService){
        this.socksService=socksService;
    }
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().print(socksService);
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
