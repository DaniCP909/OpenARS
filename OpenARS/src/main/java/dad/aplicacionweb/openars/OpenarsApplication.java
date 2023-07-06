package dad.aplicacionweb.openars;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import dad.aplicacionweb.openars.controllers.OpenARSLog;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

@EnableCaching
@SpringBootApplication
public class OpenarsApplication {

	private static final Log LOG = LogFactory.getLog(OpenarsApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(OpenarsApplication.class, args);
	}

	@Bean
	public Queue infoCommentsQueue() {
		return new Queue("notifications", false);
	}

	@Bean
	public MessageConverter converter() {
		return new Jackson2JsonMessageConverter();
	}

	@Bean
	public RabbitTemplate jsonRabbitTemplate(ConnectionFactory connectionFactory) {
		RabbitTemplate jsonRabbitTemplate = new RabbitTemplate(connectionFactory);
		jsonRabbitTemplate.setMessageConverter(converter());
		return jsonRabbitTemplate;
	}


	@Primary
	@Bean
	public CacheManager defaultCacheManager(){
		return new ConcurrentMapCacheManager();
	}

	@Bean
	public CacheManager resourcesCacheManager() {
		LOG.info("Activating cache resources...");
		return new ConcurrentMapCacheManager("resources");
	}

	@Bean
	public CacheManager commentCacheManager(){
		LOG.info("Activating cache comments");
		return new ConcurrentMapCacheManager("comments");
	}

	@Bean
	public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter(mapper);
		return converter;
	}

}
