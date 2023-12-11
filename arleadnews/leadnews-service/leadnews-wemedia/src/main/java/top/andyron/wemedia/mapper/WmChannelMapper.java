package top.andyron.wemedia.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.andyron.model.wemedia.pojos.WmChannel;

/**
 * @author andyron
 **/
@Mapper
public interface WmChannelMapper extends BaseMapper<WmChannel> {
}