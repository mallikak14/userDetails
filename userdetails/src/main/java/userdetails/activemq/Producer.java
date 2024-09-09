package userdetails.activemq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.jms.Queue;
import userdetails.entities.User;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("/produce")
public class Producer {
    @Autowired
    JmsTemplate jmsTemplate;
    @Autowired
    Queue queue;

    @PostMapping("/userData")
    public User sendMessage(@RequestBody User user1){
        try{
            ObjectMapper object= new ObjectMapper();
            String userAsJson = object.writeValueAsString(user1);
            jmsTemplate.convertAndSend(queue,userAsJson);
        }
        catch (Exception e) {
			e.printStackTrace();
		}
        return user1;

    }
}
