package servlets;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import socks.Socks;
import socks.SocksService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class OutcomeServlet extends HttpServlet {
    private final SocksService socksService;

    public OutcomeServlet(SocksService socksService){
        this.socksService=socksService;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //просматриваем json
        JSONParser parser=new JSONParser();
        JSONObject jsonObject;
        try {
            jsonObject=(JSONObject)parser.parse(getBody(req));
        }catch (Exception e){
            throw new RuntimeException();
        }
        Socks socks=new Socks(jsonObject);

        //пустые параметры
        if(socks.getColor()==null||socks.getCottonPart()==null||
                socks.getQuantity()==null){
            resp.setContentType("text/html;charset=utf-8");
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        //содержание хлопка
        if(socks.getCottonPart()<0 || socks.getCottonPart()>100){
            resp.setContentType("text/html;charset=utf-8");
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        //Отрицательное число
        if(socks.getQuantity()<=0){
            resp.setContentType("text/html;charset=utf-8");
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        if(socksService.delSocks(socks)){
            resp.setContentType("text/html;charset=utf-8");
            resp.setStatus(HttpServletResponse.SC_OK);
        }else{
            resp.setContentType("text/html;charset=utf-8");
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
    //сам не знаю как объект json обработать в строку нашел на сайте
    // https://question-it.com/questions/192029/poluchenie-dannyh-iz-vhodjaschego-json-v-servlete-java
    public static String getBody(HttpServletRequest request)  {

        String body = null;
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;

        try {
            InputStream inputStream = request.getInputStream();
            if (inputStream != null) {
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                char[] charBuffer = new char[128];
                int bytesRead = -1;
                while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                    stringBuilder.append(charBuffer, 0, bytesRead);
                }
            } else {
                stringBuilder.append("");
            }
        } catch (IOException ex) {
            // throw ex;
            return "";
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException ex) {

                }
            }
        }

        body = stringBuilder.toString();
        return body;
    }
}

