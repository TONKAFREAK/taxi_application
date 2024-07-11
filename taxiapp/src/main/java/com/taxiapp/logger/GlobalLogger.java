package com.taxiapp.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GlobalLogger {

    public static Logger getLogger(Class<?> clazz) {
        return LoggerFactory.getLogger(clazz);
    }
}
