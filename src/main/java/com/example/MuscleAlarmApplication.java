package com.example;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

import com.example.dto.Dto;
import com.example.dto.Station;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
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
	public TextMessage handleTextMessageEvent(MessageEvent<TextMessageContent> event) {
		List<Station> dtoList = restTemplate.getForObject(getUrl + event.getMessage().getText(), Dto.class).getRest();
		String str = "";
		for(Station station : dtoList) {
			str += station.getName();
		}
		return new TextMessage(str);
	}

}
