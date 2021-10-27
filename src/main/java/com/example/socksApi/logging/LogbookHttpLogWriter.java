package com.example.socksApi.logging;

import org.springframework.stereotype.Component;
import org.zalando.logbook.Correlation;
import org.zalando.logbook.HttpLogWriter;
import org.zalando.logbook.Precorrelation;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class LogbookHttpLogWriter implements HttpLogWriter {

    private final static Logger LOG = Logger.getLogger(LogbookHttpLogWriter.class.getCanonicalName());

    @Override
    public boolean isActive() {
        return true;
    }

    @Override
    public void write(Precorrelation precorrelation, String request) throws IOException {
        LOG.log(Level.INFO, request);
    }

    @Override
    public void write(Correlation correlation, String response) throws IOException {
        LOG.log(Level.INFO, response);
    }

}
