package top.andyron.wemedia.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import top.andyron.file.service.FileStorageService;
import top.andyron.model.common.dtos.PageResponseResult;
import top.andyron.model.common.dtos.ResponseResult;
import top.andyron.model.common.enums.AppHttpCodeEnum;
import top.andyron.model.wemedia.dtos.WmMaterialDto;
import top.andyron.model.wemedia.pojos.WmMaterial;
import top.andyron.utils.thread.WmThreadLocalUtil;
import top.andyron.wemedia.mapper.WmMaterialMapper;
import top.andyron.wemedia.service.WmMaterialService;

import java.io.IOException;
import java.util.Date;
import java.util.UUID;

/**
 * @author andyron
 **/
@Slf4j
@Service
@Transactional
public class WmMaterialServiceImpl extends ServiceImpl<WmMaterialMapper, WmMaterial> implements WmMaterialService {

    @Autowired
    private FileStorageService fileStorageService;
    /**
     * 图片上传
     *
     * @param multipartFile
     * @return
     */
    @Override
    public ResponseResult uploadPicture(MultipartFile multipartFile) {
        // 1 检查参数
        if (multipartFile == null || multipartFile.getSize() == 0) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        // 2 上传图片到minio中
        String fileName = UUID.randomUUID().toString().replace("-", "");
        String originalFilename = multipartFile.getOriginalFilename();
        String postfix = originalFilename.substring(originalFilename.lastIndexOf("."));
        String fileId = null;
        try {
            fileId = fileStorageService.uploadImgFile("", fileName + postfix, multipartFile.getInputStream());
            log.info("上传图片到Minio中，fileId：{}", fileId);
        } catch (IOException e) {
            e.printStackTrace();
            log.error("WmMaterialServiceImpl-上传图片失败");
        }
        // 3 保存到数据库中
        WmMaterial wmMaterial = new WmMaterial();
        wmMaterial.setUserId(WmThreadLocalUtil.getUser().getId());
        wmMaterial.setUrl(fileId);
        wmMaterial.setType((short) 0);
        wmMaterial.setIsCollection((short) 0);
        wmMaterial.setCreatedTime(new Date());
        save(wmMaterial);
        // 4 返回结果
        return ResponseResult.okResult(wmMaterial);
    }

    /**
     * 素材列表查询
     *
     * @param dto
     * @return
     */
    @Override
    public ResponseResult findList(WmMaterialDto dto) {
        // 1 检查参数
        dto.checkParam();
        // 2 分页查询
        IPage page = new Page(dto.getPage(), dto.getSize());
        LambdaQueryWrapper<WmMaterial> wrapper = new LambdaQueryWrapper<>();
        if (dto.getIsCollection() != null && dto.getIsCollection() == 1) {
            wrapper.eq(WmMaterial::getIsCollection, dto.getIsCollection());
        }
        // 按照用户查询
        wrapper.eq(WmMaterial::getUserId, WmThreadLocalUtil.getUser().getId());
        wrapper.orderByDesc(WmMaterial::getCreatedTime);

        page = page(page, wrapper);

        // 3 返回结果
        ResponseResult responseResult = new PageResponseResult(dto.getPage(), dto.getSize(), (int) page.getTotal());
        responseResult.setData(page.getRecords());
        return responseResult;
    }
}
