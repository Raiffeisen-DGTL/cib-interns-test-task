package cib.interns.test.task.core.service;

import cib.interns.test.task.database.entity.Socks;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-10-26T10:30:01+0500",
    comments = "version: 1.4.0.Final, compiler: IncrementalProcessingEnvironment from kotlin-annotation-processing-gradle-1.5.21.jar, environment: Java 15.0.2 (Oracle Corporation)"
)
@Component
public class SocksServiceMapperImpl implements SocksServiceMapper {

    @Override
    public cib.interns.test.task.core.service.Socks transform(Socks obj) {
        if ( obj == null ) {
            return null;
        }

        String color = null;
        int cottonPart = 0;
        long quantity = 0L;

        color = obj.getColor();
        if ( obj.getCottonPart() != null ) {
            cottonPart = obj.getCottonPart();
        }
        if ( obj.getQuantity() != null ) {
            quantity = obj.getQuantity();
        }

        cib.interns.test.task.core.service.Socks socks = new cib.interns.test.task.core.service.Socks( color, cottonPart, quantity );

        return socks;
    }

    @Override
    public Socks transform(cib.interns.test.task.core.service.Socks obj) {
        if ( obj == null ) {
            return null;
        }

        Socks socks = new Socks();

        socks.setColor( obj.getColor() );
        socks.setCottonPart( obj.getCottonPart() );
        socks.setQuantity( obj.getQuantity() );

        return socks;
    }
}
