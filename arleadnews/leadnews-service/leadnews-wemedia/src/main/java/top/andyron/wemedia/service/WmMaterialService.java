package top.andyron.wemedia.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;
import top.andyron.model.common.dtos.ResponseResult;
import top.andyron.model.wemedia.dtos.WmMaterialDto;
import top.andyron.model.wemedia.pojos.WmMaterial;

public interface WmMaterialService extends IService<WmMaterial> {

    /**
     * 图片上传
     * @param multipartFile
     * @return
     */
    public ResponseResult uploadPicture(MultipartFile multipartFile);

    /**
     * 素材列表查询
     * @param dto
     * @return
     */
    public ResponseResult findList( WmMaterialDto dto);


}
