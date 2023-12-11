package top.andyron.wemedia.controller.v1;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.andyron.model.common.dtos.ResponseResult;
import top.andyron.model.wemedia.dtos.WmLoginDto;
import top.andyron.wemedia.service.WmUserService;

/**
 * @author andyron
 **/
@Api(value = "登录", tags = {"登录"})
@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private WmUserService wmUserService;

    @PostMapping("/in")
    public ResponseResult login(@RequestBody WmLoginDto dto){
        return wmUserService.login(dto);
    }
}
