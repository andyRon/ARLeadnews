package top.andyron.wemedia.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.andyron.model.wemedia.pojos.WmUser;

@Mapper
public interface WmUserMapper extends BaseMapper<WmUser> {
}
