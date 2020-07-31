package com.example.controller;

import java.net.URISyntaxException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.service.PushConfirmService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class PushConfirmController {

	private final PushConfirmService lineMessagingService;

    PushConfirmController(PushConfirmService lineMessagingService) {
        this.lineMessagingService = lineMessagingService;
    }

    @GetMapping()
    public String defaultPage() {
        return "";
    }

    @GetMapping("api/alarm/burnables")
    public void pushBurnablesAlarm() {
        try {
            lineMessagingService.pushBurnablesAlarm();
        } catch (URISyntaxException e) {
            log.error("", e);
        }
    }
	
}
