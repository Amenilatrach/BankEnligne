package tn.esprit.ourbank.Configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import tn.esprit.ourbank.DAO.Entities.ChatWebSocketHandler;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
@Configuration
@EnableWebSocket
public class WsConfig implements WebSocketConfigurer {

	 private final static String CHAT_ENDPOINT = "/chat";

	    @Override
	    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {
	        webSocketHandlerRegistry.addHandler(getChatWebSocketHandler(), CHAT_ENDPOINT)
	                .setAllowedOrigins("*");
	    }

	    @Bean
	    public WebSocketHandler getChatWebSocketHandler(){
	        return new ChatWebSocketHandler();
	    }
}
