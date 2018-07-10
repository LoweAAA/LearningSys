package learningsys.controller;


import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import learningsys.entity.Users;
import learningsys.model.GetRegister;
import learningsys.model.GetUser;
import learningsys.model.ReturnUser;
import learningsys.service.UsersService;
import learningsys.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserController {

    @Autowired
    private UsersService usersService;

    @ApiOperation(value = "用户登录")
    @RequestMapping(value = "/confirm", method = RequestMethod.POST)
    @ResponseBody
    public ResponseUtil confirm(@ApiParam(value = "用户名和密码") @RequestBody GetUser rowUsers, HttpSession session) {
        Users users = usersService.Confirm(rowUsers.getUsername(), rowUsers.getPassword());
        if (users == null) {
            return ResponseUtil.error("用户名或密码错误");
        } else {
            ReturnUser returnUser = new ReturnUser();
            returnUser.setUserid(users.getId());
            returnUser.setUsername(users.getUsername());
            returnUser.setSchool(users.getSchool());
            returnUser.setPower(users.getRole());
            returnUser.setNickname(users.getNickname());
            returnUser.setIdentity(users.getRole() == 0 ? "学生" : "老师");
            returnUser.setHeadlogo(users.getHeadlogo());
            session.setAttribute("userid", users.getId());
            return ResponseUtil.success("登录成功").put("data", returnUser);
        }
    }

    @ApiOperation(value = "用户注册")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public ResponseUtil register(@ApiParam(value = "用户名、密码、email、角色（0学生 1老师）") @RequestBody GetRegister rowUsers) {
        try {
            usersService.Register(rowUsers.getUsername(), rowUsers.getPassword(), rowUsers.getEmail(), rowUsers.getRole());
            return ResponseUtil.success("注册成功");
        } catch (Exception e) {
            return ResponseUtil.error("注册失败");
        }
    }

}
