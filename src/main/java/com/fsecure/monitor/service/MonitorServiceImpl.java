package com.fsecure.monitor.service;

import com.fsecure.monitor.config.Endpoint;
import com.fsecure.monitor.config.WebSites;
import com.fsecure.monitor.util.Constants;
import com.fsecure.monitor.util.LogWriter;
import com.fsecure.monitor.util.MonitorUtil;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;


@Component
public class MonitorServiceImpl implements MonitorService {

    @Autowired
    LogWriter logWriter;

    private static final Logger logger = LoggerFactory.getLogger(MonitorServiceImpl.class);

    @Override
    public void observer(Endpoint endpoint) throws IOException {
        double responseTime = -1;
        String contentMatcherValue = null;
        try {
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();

            Connection.Response response = Jsoup
                    .connect(endpoint.getUrl())
                    .ignoreHttpErrors(true)
                    .userAgent("Mozilla")
                    .method(Connection.Method.GET)
                    .followRedirects(false)
                    .execute();

            int responseCode = response.statusCode();
            String responseMessage = response.statusMessage();


            stopWatch.stop();


            responseTime = stopWatch.getLastTaskInfo().getTimeSeconds();
            if (responseCode != 0) {
                Document document = response.parse();
                contentMatcherValue = MonitorUtil.contentMatcher(endpoint.getContent(),
                        document.getElementsByTag(Constants.TITLE_TAG).text());
                logWriter.write(new Matrix(MonitorUtil.getLogTimeDate(), endpoint.getUrl(), contentMatcherValue,
                        responseTime, responseCode, responseMessage));
            } else {
                logWriter.write(new Matrix(MonitorUtil.getLogTimeDate(), endpoint.getUrl(), Constants.CONTENT_NOT_AVAILABLE,
                        responseTime, responseCode, responseMessage));
            }

        } catch (UnknownHostException e) {
            logger.error("Observer error, {}", e.getMessage());
            logWriter.write(new Matrix(MonitorUtil.getLogTimeDate(), endpoint.getUrl(), Constants.UNKNOWN_HOST, responseTime, 400, e.getMessage()));
        } catch (Exception e) {
            logger.error("Observer error {}", e.getMessage());
        }

    }

    @Override
    @Scheduled(fixedDelayString = "${monitor.call.period}")
    public void monitor() {
        WebSites webSites = MonitorUtil.getConfiguration();
            try {
                for (Endpoint endpoint : webSites.getEndpoints()) {
                    observer(endpoint);
                }
            } catch (Exception e) {
                logger.error("Monitor error {}", e.getStackTrace());
            }
    }

}
