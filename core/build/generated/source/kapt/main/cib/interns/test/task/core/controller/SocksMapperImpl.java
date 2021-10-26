package cib.interns.test.task.core.controller;

import cib.interns.test.task.core.service.Socks;
import cib.interns.test.task.api.SocksIncomeRequest;
import cib.interns.test.task.api.SocksResponse;
import cib.interns.test.task.core.service.Socks;
import javax.annotation.processing.Generated;

import cib.interns.test.task.core.service.Socks;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-10-26T10:02:52+0500",
    comments = "version: 1.4.0.Final, compiler: IncrementalProcessingEnvironment from kotlin-annotation-processing-gradle-1.5.21.jar, environment: Java 15.0.2 (Oracle Corporation)"
)
@Component
public class SocksMapperImpl implements SocksMapper {

    @Override
    public Socks transform(SocksIncomeRequest obj) {
        if ( obj == null ) {
            return null;
        }

        String color = null;
        int cottonPart = 0;
        long quantity = 0L;

        color = obj.getColor();
        cottonPart = obj.getCottonPart();
        quantity = obj.getQuantity();

        Socks socks = new Socks( color, cottonPart, quantity );

        return socks;
    }

    @Override
    public SocksResponse transform(Socks obj) {
        if ( obj == null ) {
            return null;
        }

        String color = null;
        byte cottonPart = 0;
        long quantity = 0L;

        color = obj.getColor();
        cottonPart = (byte) obj.getCottonPart();
        quantity = obj.getQuantity();

        SocksResponse socksResponse = new SocksResponse( color, cottonPart, quantity );

        return socksResponse;
    }
}
