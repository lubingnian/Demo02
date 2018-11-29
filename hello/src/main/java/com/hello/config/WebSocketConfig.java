package com.hello.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * 配置WebSocket AbstractWebSocketMessageBrokerConfigurer（spring boot2.0以后过期划线）可改为
 * implements WebSocketMessageBrokerConfigurer
 */
@Configuration
// 注解开启使用STOMP协议来传输基于代理(message
// broker)的消息,这时控制器支持使用@MessageMapping,就像使用@RequestMapping一样
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {// 注册STOMP协议的节点(endpoint),并映射指定的url
		// 注册一个STOMP的endpoint,并指定使用SockJS协议
		registry.addEndpoint("/endpointAric").withSockJS();
		// 注册名为"endpointChat"的endpoint
		registry.addEndpoint("/endpointChat").withSockJS();
	}

	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {// 配置消息代理(Message Broker)
		// 广播式应配置一个/topic消息代理
//      registry.enableSimpleBroker("/topic");

		// 点对点式应配置/queue和/topic消息代理
		registry.enableSimpleBroker("/queue", "/topic");
	}
}