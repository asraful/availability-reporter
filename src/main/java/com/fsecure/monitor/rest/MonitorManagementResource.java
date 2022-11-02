package com.fsecure.monitor.rest;

import com.fsecure.monitor.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MonitorManagementResource {

    @Autowired
    ReportService service;

    @GetMapping("/")
    public String redirectToLandingPage() {
        return "redirect:/dashboard";
    }

    @GetMapping("/dashboard")
    public String dashboard() {
        return "dashboard";
    }

    @GetMapping("/report/all")
    String showAll(Model model) {
        model.addAttribute("matrixData", service.getMatrixData());
        return "report";
    }
}
