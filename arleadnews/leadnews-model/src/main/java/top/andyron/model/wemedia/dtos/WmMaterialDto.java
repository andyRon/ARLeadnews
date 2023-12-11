package top.andyron.model.wemedia.dtos;

import lombok.Data;
import top.andyron.model.common.dtos.PageRequestDto;

@Data
public class WmMaterialDto extends PageRequestDto {

    /**
     * 1 收藏
     * 0 未收藏
     */
    private Short isCollection;
}
