package top.andyron.wemedia.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.andyron.model.wemedia.pojos.WmNewsMaterial;

import java.util.List;

/**
 * @author andyron
 **/
@Mapper
public interface WmNewsMaterialMapper extends BaseMapper<WmNewsMaterial> {
    /**
     * 批量保存文章素材关系
     * @param materialIds
     * @param newsId
     * @param type
     */
    void saveRelations(@Param("materialIds")List<Integer> materialIds, @Param("newsId") Integer newsId, @Param("type") Short type);
}
