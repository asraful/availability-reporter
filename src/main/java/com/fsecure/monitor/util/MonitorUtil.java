package com.fsecure.monitor.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fsecure.monitor.config.WebSites;
import com.fsecure.monitor.service.MonitorServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class MonitorUtil {

    private static final Logger logger = LoggerFactory.getLogger(MonitorServiceImpl.class);

    public static WebSites getConfiguration() {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        try {
            return mapper.readValue(new File(Constants.CONFIG_FILE), WebSites.class);
        } catch (Exception e) {
            logger.error("Configuration error {}", e.getStackTrace());
        }
        return null;
    }

    public static String contentMatcher(String content, String contentFromTag) {
        if (contentFromTag.contains(content))
            return Constants.CONTENT_MATCHED;
        else
            return Constants.CONTENT_NOT_MATCHED;
    }

    public static String getLogTimeDate() {
        SimpleDateFormat formatDate = new SimpleDateFormat(Constants.LOG_DATE_FORMAT);
        formatDate.setTimeZone(TimeZone.getDefault());
        return formatDate.format(new Date());
    }
}
