package ru.stqa.pft.generators;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.core.util.StatusPrinter;
import org.slf4j.LoggerFactory;

import java.io.File;

public class LogBackCheck {
    public static void main(String[] args) {
        // assume SLF4J is bound to logback in the current environment
        System.out.println(new File(".").getAbsolutePath());
        LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
        // print logback's internal status
        StatusPrinter.print(lc);
    }
}
