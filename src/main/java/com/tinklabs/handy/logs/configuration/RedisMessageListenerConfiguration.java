package com.tinklabs.handy.logs.configuration;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

import com.tinklabs.handy.logs.listener.IRedisMessageListener;

@Configuration
public class RedisMessageListenerConfiguration implements ApplicationContextAware {

	private static final Logger log = LoggerFactory.getLogger(RedisMessageListenerConfiguration.class);

	ApplicationContext applicationContext;

	@Bean
	public RedisMessageListenerContainer rmContainer(RedisConnectionFactory connectionFactory) {
		// 初始化监听器容器并设置redis的连接工厂
		RedisMessageListenerContainer container = new RedisMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);

		// 初始化系统全部的监听器
		initListeners(container);

		return container;
	}

	/**
	 * @description: 描述并初始化系统全部的监听器
	 * @company: tinklabs
	 * @author: pengtao
	 * @date: 2019 2019年3月22日 下午4:33:26
	 * @param container
	 */
	private void initListeners(RedisMessageListenerContainer container) {
		log.info("begin to init RedisMessageListener");
		// 查询实现了指定监听器，并且注册到spring窗口的beans
		Map<String, IRedisMessageListener> listeners = applicationContext.getBeansOfType(IRedisMessageListener.class);
		// 如果查找到有结果，则分别执行初始化
		if (listeners != null && !listeners.isEmpty()) {
			// 转换上下文到bean工厂
			ConfigurableApplicationContext configurableContext = (ConfigurableApplicationContext) applicationContext;
			BeanDefinitionRegistry beanDefinitionRegistry = (DefaultListableBeanFactory) configurableContext
					.getBeanFactory();

			log.info("Readed RedisMessageListeners of " + listeners.size());

			Iterator<Entry<String, IRedisMessageListener>> itor = listeners.entrySet().iterator();
			while (itor.hasNext()) {
				Entry<String, IRedisMessageListener> entry = itor.next();
				// 取得监听的队列名并组装监听适配器的beanName
				String topic = entry.getValue().getTopic();
				String listenerAdapterBeanName = "messageListenerAdapter_" + topic;
				log.info("init New MessageListeners of bean : " + entry.getKey() + ", topic: " + topic);

				// 生成一个bean定义构建器
				BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder
						.genericBeanDefinition(MessageListenerAdapter.class);
				// 设置参数：监听器实际的beanName与监听要执行的方法名
				beanDefinitionBuilder.addConstructorArgReference(entry.getKey());
				// 使用默认消费方法名定义在接口里，所以此处的方法名可以不需要设置
//				beanDefinitionBuilder.addConstructorArgValue("handleMessage");

				// 生成bean定义并注册
				BeanDefinition bd = beanDefinitionBuilder.getBeanDefinition();
				beanDefinitionRegistry.registerBeanDefinition(listenerAdapterBeanName, bd);

				// 从上下文中取出刚注册的bean，与要监听的队列名一起绑定到窗口
				container.addMessageListener(
						applicationContext.getBean(listenerAdapterBeanName, MessageListenerAdapter.class),
						new PatternTopic(topic));

			}
		}

	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

}
