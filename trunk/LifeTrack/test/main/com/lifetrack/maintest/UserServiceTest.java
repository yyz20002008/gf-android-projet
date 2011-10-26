package com.lifetrack.maintest;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/applicationContext.xml" })
public class UserServiceTest extends AbstractTransactionalJUnit4SpringContextTests {  
      
    @Autowired  
    private UserService userService;  
  
    @Test  
    public void testSave() {  
        User user = new User();  
        user.setPassword("1234");  
        user.setUsername("张三3");  
        //userService.save(user);  
    }  
  
    @Test  
    public void testExits() {  
        UserService userService = new UserService();  
        User user = new User();  
        user.setPassword("1234");  
        user.setUsername("张三");  
        //userService.exits(user.getUsername());  
          
    }  
  
} 