package top.andyron.wemedia.controller.v1;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.andyron.model.common.dtos.ResponseResult;
import top.andyron.model.wemedia.dtos.WmNewsDto;
import top.andyron.model.wemedia.dtos.WmNewsPageReqDto;
import top.andyron.wemedia.service.WmNewsService;

@Api(value = "自媒体文章管理", tags = {"自媒体文章管理"})
@RestController
@RequestMapping("/api/v1/news")
public class WmNewsController {

    @Autowired
    private WmNewsService wmNewsService;

    @ApiOperation(value = "查询自媒体文章")
    @PostMapping("/list")
    public ResponseResult findList(@RequestBody WmNewsPageReqDto dto){
        return wmNewsService.findList(dto);
    }

    @ApiOperation(value = "发布自媒体文章")
    @PostMapping("/submit")
    public ResponseResult submitNews(@RequestBody WmNewsDto dto){
        return wmNewsService.submitNews(dto);
    }
}
