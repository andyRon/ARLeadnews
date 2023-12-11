package top.andyron.wemedia.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.andyron.model.common.dtos.ResponseResult;
import top.andyron.model.wemedia.pojos.WmChannel;

public interface WmChannelService extends IService<WmChannel> {

    /**
     * 查询所有频道
     * @return
     */
    public ResponseResult findAll();


}
