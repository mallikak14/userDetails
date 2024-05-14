package userdetails.schedulers;

import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.stereotype.Component;

import userdetails.entities.User;
import userdetails.service.UserService;
import userdetails.service.UserServiceImplementation;

@Component
public class DynamicScheduling implements SchedulingConfigurer {

	
	@Autowired
    UserService uservice;
	
	 private boolean limitReached = false;

	 private int nextExecutionTimeMilliSeconds = 0;
	 
	 
	 @Override
	 public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
		 taskRegistrar.addTriggerTask(() -> {
			 System.out.print("Dynamic schedule is up with time =" + nextExecutionTimeMilliSeconds + "\n");
			 List<User> userList = uservice.getAllUsers();
			 if (userList.isEmpty())
				 limitReached = true;
		 }, triggerContext -> {
			 Calendar nextExecutionTime = new GregorianCalendar();
			 Date lastActualExecutionTime = triggerContext.lastActualExecutionTime();
			 nextExecutionTime.setTime(lastActualExecutionTime != null ? lastActualExecutionTime : new Date());
			 nextExecutionTime.add(Calendar.MILLISECOND, getNewExecutionTime());
			 return nextExecutionTime.toInstant();
		 });
	 }
 	 private int getNewExecutionTime() {
		 if (limitReached)
			 nextExecutionTimeMilliSeconds = 10000;
		 else
			 nextExecutionTimeMilliSeconds = 5000; 
		 return nextExecutionTimeMilliSeconds;
	 }
 }