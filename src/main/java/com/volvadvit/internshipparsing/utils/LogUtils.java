package com.volvadvit.internshipparsing.utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * @author Vadim Volkov
 * volvadvit@gmail.com
 */

public class LogUtils {

    private static Logger logger = Logger.getLogger("error_log");

    private static final String filePath = "logs";
    private static final String fileName = "log";

    public static void info(String message) {
        setUpFileHandler();
        logger.info(message);
    }

    private static void setUpFileHandler() {
        FileHandler fileHandler;
        String path = getPath();
        try {
            fileHandler = new FileHandler(path);
            logger.addHandler(fileHandler);
            fileHandler.setFormatter(new SimpleFormatter());
        } catch (IOException e) {
            System.err.println("FAILED TO SAVE LOG. " + e.getMessage());
        }
    }

    private static String getPath() {
        // make dir if not exists
        File mainDir = new File(System.getProperty("user.dir"), filePath);
        if (!mainDir.exists()) {
            mainDir.mkdir();
        }
        File dateDir = new File(mainDir, getCurrentDate());
        if (!dateDir.exists()) {
            dateDir.mkdir();
        }
        return dateDir.getAbsolutePath() + "/" + fileName;
    }

    private static String getCurrentDate() {
        return new SimpleDateFormat("ddMMyy").format(new Date());
    }
}