package learningsys.controller;


import learningsys.entity.Users;
import learningsys.service.UsersService;
import learningsys.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UsersService usersService;

    @RequestMapping("/confirm")
    public ResponseUtil confirm(@RequestParam("username") String username, @RequestParam("password") String password, HttpSession session) {
        Users users = usersService.Confirm(username, password);
        if (users == null) {
            return ResponseUtil.error("用户名或密码错误");
        } else {
            session.setAttribute("userid", users.getId());
            return ResponseUtil.success().put("user", users);
        }
    }

    @RequestMapping("/register")
    public ResponseUtil register(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        @RequestParam("email") String email,
                        @RequestParam("role") byte role) {
        try {
            usersService.Register(username, password, email, role);
            return ResponseUtil.success("注册成功");
        } catch (Exception e) {
            return ResponseUtil.error("注册失败");
        }
    }

}
