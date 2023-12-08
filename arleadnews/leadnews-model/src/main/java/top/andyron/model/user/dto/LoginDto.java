package top.andyron.model.user.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.checkerframework.checker.units.qual.A;

/**
 * @author andyron
 **/
@Data
public class LoginDto {
    @ApiModelProperty(value = "手机", required = true)
    private String phone;
    @ApiModelProperty(value = "密码", required = true)
    private String password;
}
