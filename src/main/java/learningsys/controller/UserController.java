package learningsys.controller;


import learningsys.model.Users;
import learningsys.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UsersService usersService;


    @RequestMapping("/confirm")
    public Map confirm(@RequestParam("username") String username,@RequestParam("password") String password){
        Map map=new HashMap();
        Users users=usersService.Confirm(username,password);
        map.put("data",users);
        if(users==null){
            map.put("status",201);
        }else {
            map.put("status",200);
        }
        return map;
    }

    @RequestMapping("/register")
    public Map register(@RequestParam("username")String username,
                        @RequestParam("password")String password,
                        @RequestParam("email")String email,
                        @RequestParam("role")byte role){
        Map map=new HashMap();
        try{
            usersService.Register(username,password,email,role);
            map.put("message", "注册成功");
            map.put("status", 200);
        }catch (Exception e){
            map.put("message", "注册失败");
            map.put("status", 201);
        }
        return map;

    }

}
