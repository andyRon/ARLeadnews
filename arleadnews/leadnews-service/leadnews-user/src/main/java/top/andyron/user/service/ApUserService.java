package top.andyron.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.andyron.model.common.dtos.ResponseResult;
import top.andyron.model.user.dto.LoginDto;
import top.andyron.model.user.pojos.ApUser;

/**
 * @author andyron
 **/
public interface ApUserService extends IService<ApUser> {
    /**
     * app端登录功能
     * @param dto
     * @return
     */
    public ResponseResult login(LoginDto dto);
}
