package top.andyron.wemedia.controller.v1;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import top.andyron.model.common.dtos.ResponseResult;
import top.andyron.model.wemedia.dtos.WmMaterialDto;
import top.andyron.wemedia.service.WmMaterialService;

/**
 * @author andyron
 **/
@Api(value = "自媒体素材管理", tags = {"自媒体素材管理"})
@RestController
@RequestMapping("/api/v1/material")
public class WeMaterialController {

    @Autowired
    private WmMaterialService wmMaterialService;

    @ApiOperation(value = "上传图片")
    @PostMapping("/upload_picture")
    public ResponseResult uploadPicture(MultipartFile multipartFile) {
        return wmMaterialService.uploadPicture(multipartFile);
    }

    @ApiOperation(value = "素材列表")
    @PostMapping("/list")
    public ResponseResult list(@RequestBody WmMaterialDto dto) {
        return wmMaterialService.findList(dto);
    }
}
