package inhatc.spring.shop.controller;

import inhatc.spring.shop.dto.UserDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class HelloController {

    @GetMapping("/")
    public String index() {

        return "index";
    }

    //    @GetMapping("/")
//    public UserDto hello() {
//        UserDto userDto = UserDto.builder().name("홍길동").age(20).build();
//        userDto.setAge(20);
//        userDto.setName("홍길동");
//        System.out.println("userDto : " + userDto);
//        return userDto;
//    }
}
