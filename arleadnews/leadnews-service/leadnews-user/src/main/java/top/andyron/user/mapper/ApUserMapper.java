package top.andyron.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.andyron.model.user.pojos.ApUser;

/**
 * @author andyron
 **/
@Mapper
public interface ApUserMapper extends BaseMapper<ApUser> {
}
