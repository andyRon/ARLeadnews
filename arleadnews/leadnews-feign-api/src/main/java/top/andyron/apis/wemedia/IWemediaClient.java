package top.andyron.apis.wemedia;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import top.andyron.model.common.dtos.ResponseResult;
import top.andyron.model.wemedia.pojos.WmUser;

@FeignClient("leadnews-wemedia")
public interface IWemediaClient {
    @GetMapping("/api/v1/user/findByName/{name}")
    WmUser findWmUserByName(@PathVariable("name") String name);

    @PostMapping("/api/v1/wm_user/save")
    ResponseResult saveWmUser(@RequestBody WmUser wmUser);

    /**
     * 查询所有频道
     * @return
     */
    @GetMapping("/api/v1/channel/list")
    public ResponseResult getChannels();
}
