package main;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import servlets.*;
import socks.*;



public class Main {
    public static void main(String[] args) throws Exception {
        SocksService socksService=new SocksService();

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(new ShowServlet(socksService)),"/show");
        context.addServlet(new ServletHolder(new IncomeServlet(socksService)), "/api/socks/income");
        context.addServlet(new ServletHolder(new OutcomeServlet(socksService)), "/api/socks/outcome");
        context.addServlet(new ServletHolder(new SocksServlet(socksService)), "/api/socks");

        Server server = new Server(8080);
        server.setHandler(context);

        server.start();
        server.join();
    }
}
