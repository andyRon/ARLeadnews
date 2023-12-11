package top.andyron.wemedia.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.andyron.model.common.dtos.ResponseResult;
import top.andyron.model.wemedia.dtos.WmLoginDto;
import top.andyron.model.wemedia.pojos.WmUser;

public interface WmUserService extends IService<WmUser> {

    /**
     * 自媒体端登录
     * @param dto
     * @return
     */
    public ResponseResult login(WmLoginDto dto);

}
