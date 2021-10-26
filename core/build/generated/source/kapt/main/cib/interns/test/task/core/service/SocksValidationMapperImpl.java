package cib.interns.test.task.core.service;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-10-26T11:21:37+0500",
    comments = "version: 1.4.0.Final, compiler: IncrementalProcessingEnvironment from kotlin-annotation-processing-gradle-1.5.21.jar, environment: Java 15.0.2 (Oracle Corporation)"
)
@Component
public class SocksValidationMapperImpl implements SocksValidationMapper {

    @Override
    public SocksValidationDto transform(Socks obj) {
        if ( obj == null ) {
            return null;
        }

        String color = null;
        int cottonPart = 0;
        long quantity = 0L;

        color = obj.getColor();
        cottonPart = obj.getCottonPart();
        quantity = obj.getQuantity();

        SocksValidationDto socksValidationDto = new SocksValidationDto( color, cottonPart, quantity );

        return socksValidationDto;
    }

    @Override
    public SocksOutValidationDto transformOut(Socks obj) {
        if ( obj == null ) {
            return null;
        }

        String color = null;
        int cottonPart = 0;
        long quantity = 0L;

        color = obj.getColor();
        cottonPart = obj.getCottonPart();
        quantity = obj.getQuantity();

        SocksOutValidationDto socksOutValidationDto = new SocksOutValidationDto( color, cottonPart, quantity );

        return socksOutValidationDto;
    }
}
