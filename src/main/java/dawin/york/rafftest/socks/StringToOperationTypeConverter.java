package dawin.york.rafftest.socks;

import dawin.york.rafftest.socks.tos.OperationType;
import org.springframework.core.convert.converter.Converter;

import java.util.Collections;
import java.util.Map;

import static java.util.Map.entry;

public class StringToOperationTypeConverter implements Converter<String, OperationType> {

    public final Map<String, OperationType> map = Map.ofEntries(
            entry("moreThan", OperationType.MORE_THAN),
            entry("lessThan", OperationType.LESS_THAN),
            entry("equal", OperationType.EQUAL)
    );

    @Override
    public OperationType convert(String source) {
        return map.get(source);
    }
}
