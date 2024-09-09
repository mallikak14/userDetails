package userdetails.activemq;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.jms.Queue;
import userdetails.entities.User;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/consume")
public class Consumer {
    // private static final Logger logger = LoggerFactory.getLogger(Consumer.class);

	// @JmsListener(destination = "userdetails-queue")
	// public void consumeMessage(String message) {
	// 	logger.info("Message received from activemq queue---"+message);
	// }
    @Autowired
    JmsTemplate jmsTemplate;
    @Autowired
    Queue queue;

    @GetMapping("/getData")
    public User consumeUser() {
        User user1=null;
        try{
            ObjectMapper mapper=new ObjectMapper();
            String jsonMessage=(String)jmsTemplate.receiveAndConvert(queue);
            user1=mapper.readValue(jsonMessage,User.class);
        }
        catch (Exception e) {
			e.printStackTrace();
		}
        return user1;
    }
    
    
}
