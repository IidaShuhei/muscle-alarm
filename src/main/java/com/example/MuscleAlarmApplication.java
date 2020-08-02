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

	public static void main(String[] args) {
		SpringApplication.run(MuscleAlarmApplication.class, args);
	}

	@EventMapping
	public Dto handleTextMessageEvent(Form form) {
		String  url  = "https://api.gnavi.co.jp/RestSearchAPI/v3/?keyid=0fee8fd48d4f09b6d91da827ab0ddbd7&category_s=RSFST08011&freeword=" + form.getStation();
		return restTemplate.getForObject(url, Dto.class, form.getStation());
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
