package com.example.feign.web;



import com.example.feign.config.PatternProperties;
import com.example.feign.service.UserService;
import com.example.feign.pojo.User;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@RestController
@RequestMapping("/user")
@RefreshScope
public class UserController {

         @Autowired
         private UserService userService;

         @Value("${pattern.dateformate}")
         private String dateformate;

         @Autowired
         private PatternProperties properties;

         @GetMapping("prop")
         public PatternProperties properties(){
             return properties;
         }

        @GetMapping("now")
        public String now(){
            System.out.println(dateformate);
            return LocalDateTime.now().format(DateTimeFormatter.ofPattern(properties.getDateformate()));
    //        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(dateformate));
    //        return dateformate;
        }

        /**
         * 路径： /user/110
         *
         * @param id 用户id
         * @return 用户
         */
        @GetMapping("/{id}")
        public User queryById(@PathVariable("id") Long id,@RequestHeader(value = "Truth", required = false) String truth) {
            System.out.println("truth: " + truth);
            return userService.queryById(id);
        }


}
