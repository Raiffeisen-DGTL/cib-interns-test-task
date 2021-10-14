package ru.danilarassokhin.raiffeisensocks.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerService {

    private Logger logger;

    private static LoggerService INSTANCE;

    private LoggerService() {
        logger = LoggerFactory.getLogger("LoggerService");
    }

    public static LoggerService getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new LoggerService();
        }
        return INSTANCE;
    }

    public static void info(String message) {
        getInstance().getLogger().info(message);
    }

    public static void info(Object message) {
        getInstance().getLogger().info(message.toString());
    }

    public static void warn(String message) {
        getInstance().getLogger().warn(message);
    }

    public static void warn(Object message) {
        getInstance().getLogger().warn(message.toString());
    }

    public static void error(String message) {
        getInstance().getLogger().error(message);
    }

    public static void error(Object message) {
        getInstance().getLogger().error(message.toString());
    }

    public Logger getLogger() {
        return logger;
    }
}
