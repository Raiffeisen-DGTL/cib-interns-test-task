package com.dragulaxis.raiffeisentest;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "Socks", value = "/socks")
public class SocksServlet extends HttpServlet {
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
            //выгребаем параметры из http запроса
            String color = request.getParameter("color");
            if (color.equals(null)) response.sendError(400);
            String operation = request.getParameter("operation");
            byte cottonPart = Byte.parseByte(request.getParameter("cottonPart"));

            session.beginTransaction();

            //составляем hql запрос
            String hqlRequest = "FROM Socks s Where color = '" + color + "' and cottonPart";
            switch (operation) {
                case ("moreThan"):
                    hqlRequest += " > ";
                    break;
                case ("lessThan"):
                    hqlRequest += " < ";
                    break;
                case ("equal"):
                    hqlRequest += " = ";
                    break;
                default:
                    break;
            }
            hqlRequest += cottonPart;

            //посылаем запрос в бд и получаем список нужных носков
            List<Socks> allSocks = session.createQuery(hqlRequest).getResultList();
            System.out.println(allSocks);

            //печать носков в консоль
            session.getTransaction().commit();

        } catch (Exception e) {
            //если на этапе проверки ввода данных что-то пошло не так
            response.sendError(400);
            e.getStackTrace();
        } finally {
            session.close();
        }
    }
}