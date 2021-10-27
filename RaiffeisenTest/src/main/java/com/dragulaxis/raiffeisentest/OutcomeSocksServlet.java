package com.dragulaxis.raiffeisentest;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "OutcomeSocksServlet", value = "/socks/outcome")
public class OutcomeSocksServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        SessionFactory factory;
        Session session = null;

        try {
            factory = new Configuration()
                    .configure("hibernate.cfg.xml")
                    .addAnnotatedClass(Socks.class)
                    .buildSessionFactory();
            session = factory.getCurrentSession();
        } catch (Exception e) {
            response.sendError(500);
            e.getStackTrace();
        }

        try {
            String color = request.getParameter("color");
            if (color.equals(null)) response.sendError(400);
            byte cottonPart = Byte.parseByte(request.getParameter("cottonPart"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            if (quantity <= 0) response.sendError(400);
            Socks socks = new Socks(color, cottonPart, quantity);

            session.beginTransaction();

            List<Socks> allSocks = session.createQuery("from Socks").getResultList();
            if (allSocks.contains(socks)) {
                int id = 0;
                for (Socks s: allSocks) {
                    if (s.equals(socks)) id = s.getId();
                }
                Socks s = session.get(Socks.class, id);
                s.setQuantity(s.getQuantity() - socks.getQuantity());
            } else {
                response.sendError(400);
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            response.sendError(400);
            e.getStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        doGet(req, resp);
    }
}
