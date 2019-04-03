package com.fc.mq.rabbitmq;

import java.awt.List;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmListener;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.ReturnListener;

public class RabbitMqTest {

	static class Producer{
		public void product() throws IOException, TimeoutException, InterruptedException{
			ConnectionFactory connectionFactory =  new ConnectionFactory();
			connectionFactory.setHost("127.0.0.1");
			connectionFactory.setPort(5672);
			connectionFactory.setVirtualHost("/");
			Connection connection = connectionFactory.newConnection();
			Channel channel = connection.createChannel();
			//确认送达
			channel.confirmSelect();
			
			channel.addConfirmListener(new ConfirmListener() {
				//no ack 失败
				@Override
				public void handleNack(long deliveryTag, boolean multiple) throws IOException {
					System.out.println("handleNack deliveryTag:"+deliveryTag);
				}
				
				//成功
				@Override
				public void handleAck(long deliveryTag, boolean multiple) throws IOException {
					System.out.println("handleAck deliveryTag:"+deliveryTag);
				}
			});
			
			//处理不可路由的消息(没找到exchange,或者无法路由到队列)
			channel.addReturnListener(new ReturnListener() {
				@Override
				public void handleReturn(int replyCode, String replyText, String exchange, String routingKey,
						BasicProperties properties, byte[] body) throws IOException {
					System.out.println("handleReturn:"+replyText);
				}
			});
			
			for (int i = 0; i < 3000; i++) {
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				//deliveryMode 2 持久化
				BasicProperties basicProperties = new BasicProperties().builder()
							.deliveryMode(2).contentEncoding("UTF-8").expiration("5000").build();
				String msg = "i pingjing "+i;
				channel.basicPublish("exchange_1", "fc.rooting.key", basicProperties, msg.getBytes());
				
				channel.basicPublish("exchange_2", "log.rooting.key", basicProperties, msg.getBytes());
				
				channel.basicPublish("exchange_1", "log2.rooting.key", true , basicProperties, msg.getBytes());
			}
			
			channel.close();
			connection.close();
			while(true){
				Thread.sleep(1000L);
			}
		}
	}
	
	static class MsgConsumer{
		public void consume() throws IOException, TimeoutException{
			ConnectionFactory connectionFactory =  new ConnectionFactory();
			connectionFactory.setHost("127.0.0.1");
			connectionFactory.setPort(5672);
			connectionFactory.setVirtualHost("/");
			Connection connection = connectionFactory.newConnection();
			Channel channel = connection.createChannel();
//			//exclusive 独占  队列至于一个消费者
//			//autodelete 
//			channel.exchangeDeclare("exchange_1", "direct");
//			channel.queueBind("fc-queue-1", "exchange_1", "fc.rooting.key");
//			channel.queueBind("fc-queue-1", "exchange_1", "fc.rooting.key");
			
			DeliverCallback deliverCallback = (consumerTag, delivery) -> {
			    String message = new String(delivery.getBody(), "UTF-8");
			    Envelope envelope = delivery.getEnvelope();
			    long tag = envelope.getDeliveryTag();
			    String routingKey = envelope.getRoutingKey();
			    String exchange  = envelope.getExchange();
			    System.out.println(" [x] Received: '" + message + "tag :"+tag+"' routingKey:"+routingKey+",exchange:"+exchange);
			    channel.basicAck(tag, false);
			};
			//限流
			//channel.basicQos(0, 20, false);
			//限流的时候必须要求autoAck必须false
			channel.basicConsume("fc-queue-1",false, deliverCallback,consumerTag -> { });
			channel.basicConsume("fc-queue-2",false, deliverCallback,consumerTag -> { });
			
		}
	}
	
	public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {

		MsgConsumer consumer = new RabbitMqTest.MsgConsumer();
		consumer.consume();
		
		Producer producer = new RabbitMqTest.Producer();
		producer.product();
	}
}
