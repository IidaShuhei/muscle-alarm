package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

import com.example.dto.Dto;
import com.example.form.Form;
import com.linecorp.bot.model.event.Event;
import com.linecorp.bot.model.event.JoinEvent;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;

@SpringBootApplication
@LineMessageHandler
public class MuscleAlarmApplication {
	
	@Autowired
	RestTemplate restTemplate;
	
	private static final String getUrl = "https://api.gnavi.co.jp/RestSearchAPI/v3/?keyid=4044f5ab8816f1d84ae60d4aed15bf4b&category_s=RSFST08008&freeword=";

	public static void main(String[] args) {
		SpringApplication.run(MuscleAlarmApplication.class, args);
	}

	@EventMapping
	public Dto handleTextMessageEvent(Form form) {
		return (Dto) restTemplate.getForObject(getUrl + form.getStation(), Dto.class).getRest();
	}

	@EventMapping
	public void handleDefaultMessageEvent(Event event) {
		System.out.println("event: " + event);
	}
	
	@EventMapping
	public TextMessage joinMessageEvent(JoinEvent event) {
		return new TextMessage(event.getReplyToken());
	}

}
