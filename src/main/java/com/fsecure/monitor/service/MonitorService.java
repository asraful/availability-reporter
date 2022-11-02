package com.fsecure.monitor.service;

import com.fsecure.monitor.config.Endpoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public interface MonitorService {

    void observer(Endpoint sites) throws IOException;

    void monitor();
}
