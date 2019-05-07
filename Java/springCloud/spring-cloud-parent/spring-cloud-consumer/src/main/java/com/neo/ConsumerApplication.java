package com.neo;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableHystrixDashboard
@EnableCircuitBreaker
public class ConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConsumerApplication.class, args);
	}

	@Bean
	@LoadBalanced
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}

	@Bean
	public Queue helloQueue() {
		return new Queue("hello");
	}

	/** 对象队列
	 * @return
	 */
	@Bean
	public Queue orderQueue() {
		return new Queue("order");
	}


	/**交换机下队列1
	 * @return
	 */
	@Bean
	public Queue queueMessage() {
		return new Queue("topic.message");
	}

	/**交换机下队列2
	 * @return
	 */
	@Bean
	public Queue queueMessage1() {
		return new Queue("topic.message1");
	}

	/**定义交换机名字为 exchange
	 * @return
	 */
	@Bean
	TopicExchange exchange() {
		return new TopicExchange("exchange");
	}

	/**
	 * @param queueMessage 注入queueMessage(上面两个bean)
	 * @param exchange 注入exchange(上面两个bean)
	 * @return 返回一个绑定关系
	 */
	@Bean
	Binding bindingExchangeMessage(Queue queueMessage, TopicExchange exchange) {
		// 发送目的交换机为"exchange"的且符合"topic.message"的都会发送到Bean为queueMessage的队列，也就是name为topic.message
		return BindingBuilder.bind(queueMessage).to(exchange).with("topic.message");
	}

	@Bean
	Binding bindingExchangeMessages(Queue queueMessage1, TopicExchange exchange) {
		return BindingBuilder.bind(queueMessage1).to(exchange).with("topic.#");
	}
}
