package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.linecorp.bot.model.action.MessageAction;
import com.linecorp.bot.model.event.Event;
import com.linecorp.bot.model.event.JoinEvent;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.model.message.template.ConfirmTemplate;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;

@SpringBootApplication
@LineMessageHandler
public class MuscleAlarmApplication {

	public static void main(String[] args) {
		SpringApplication.run(MuscleAlarmApplication.class, args);
	}

	@EventMapping
	public TextMessage handleTextMessageEvent(MessageEvent<TextMessageContent> event) {
		System.out.println("event: " + event);
		ConfirmTemplate confirmTemplate = new ConfirmTemplate(
                "筋トレしたかい?",
                new MessageAction("やったね", "＼(^_^)／"),
                new MessageAction("まだ…", ";つД｀)")
        );
		return new TextMessage(event.getMessage().getText());
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
