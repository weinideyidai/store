package com.fei.store.common.mq;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class GoodsLister {

    @RabbitListener(bindings = @QueueBinding(
        value = @Queue(name = "queue_direct_1",declare = "true"),
        exchange = @Exchange(name = "exchange_direct",type = "direct",durable = "true"),
            key = {"delete"}
    ))
    public void listenInsertOrUpdate(Long id, Channel channel, Message message){
        if (id==null){
            return;
        }
        System.out.println(id);
        try {
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
