package com.fitness.aiservice.service;


import com.fitness.aiservice.model.Activity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class ActivityMessageListener {


    //mesage being consumed from the queue

    @RabbitListener(queues = "activity.queue")
    public void processActivty(Activity activity){
        log.info("Received activity for Processing: {}", activity.getId());
    }
}
