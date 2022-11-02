package com.fsecure.monitor.config;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class WebSites {
    private List<Endpoint> endpoints;
}
