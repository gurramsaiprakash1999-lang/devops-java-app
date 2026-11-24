package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.net.InetAddress;
import java.time.LocalDateTime;

@SpringBootApplication
@RestController
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @GetMapping("/")
    public String index() {
        String hostname = "Unknown";
        try {
            hostname = InetAddress.getLocalHost().getHostName();
        } catch (Exception e) {
            hostname = "Container Host";
        }

        return "<html><head><style>" +
               "body { background-color: #0f172a; color: #f8fafc; font-family: sans-serif; text-align: center; padding-top: 50px; }" +
               ".card { background-color: #1e293b; display: inline-block; padding: 40px; border-radius: 12px; border: 1px solid #38bdf8; }" +
               "h1 { color: #38bdf8; }" +
               ".badge { background: #22c55e; color: #000; padding: 6px 12px; border-radius: 20px; font-weight: bold; }" +
               "</style></head><body>" +
               "<div class='card'>" +
               "<h1>🚀 Java Enterprise Web Portal</h1>" +
               "<p><span class='badge'>STATUS: LIVE PRODUCTION</span></p>" +
               "<p><strong>Engine:</strong> Spring Boot 3 on Java 21</p>" +
               "<p><strong>Pod Hostname:</strong> " + hostname + "</p>" +
               "<p><strong>Server Time:</strong> " + LocalDateTime.now() + " UTC</p>" +
               "</div></body></html>";
    }
}
