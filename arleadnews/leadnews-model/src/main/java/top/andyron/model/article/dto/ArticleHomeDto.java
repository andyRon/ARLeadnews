package top.andyron.model.article.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author andyron
 **/
@Data
public class ArticleHomeDto {
    @ApiModelProperty(value = "最大时间")
    Date maxBehotTime;
    @ApiModelProperty(value = "最小时间")
    Date minBehotTime;
    @ApiModelProperty(value = "分页size")
    Integer size;
    @ApiModelProperty(value = "频道ID")
    String tag;
}
