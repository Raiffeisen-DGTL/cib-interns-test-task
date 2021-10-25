package servlets;
import socks.SocksService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SocksServlet extends HttpServlet{
    private final SocksService socksService;

    public SocksServlet(SocksService socksService){
        this.socksService=socksService;
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String color=req.getParameter("color");
        String operation=req.getParameter("operation");
        Long cottonPart=Long.parseLong(req.getParameter("cottonPart"));

        //пустые параметры
        if(color==null||operation==null||cottonPart==null){
            resp.setContentType("text/html;charset=utf-8");
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        //содержание хлопка
        if(cottonPart<0 || cottonPart>100){
            resp.setContentType("text/html;charset=utf-8");
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        //Корректная операция
        if(!operation.equals("moreThan")&& !operation.equals("lessThan")&& !operation.equals("equal")){
            resp.setContentType("text/html;charset=utf-8");
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        //отобразить кол-во нужных носков
        resp.setContentType("text/html;charset=utf-8");
        resp.getWriter().print(socksService.get(color,operation,cottonPart));
        resp.setStatus(HttpServletResponse.SC_OK);
    }
}
