package com.raif.service;

import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletRequest;

@Service
public class SockService {

    // params check for api/socks/income and /api/socks/outcome requests
    public void IncomeAndOutcomeParamsCheck(HttpServletRequest request) throws Exception {

            int cottonPart = Integer.parseInt(request.getParameter("cottonPart"));
            if(cottonPart > 100 || cottonPart < 0){
                throw new Exception();
            }

            int quantity = Integer.parseInt(request.getParameter("quantity"));
    }

    // params check for /api/socks requests
    public void ParamsCheck(HttpServletRequest request) throws Exception{

        int cottonPart = Integer.parseInt(request.getParameter("cottonPart"));

        String operation = request.getParameter("operation");

        if(!(operation.equals("moreThan") || operation.equals("lessThan") || operation.equals("equal"))){
            throw new Exception();
        }
    }
}
