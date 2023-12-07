package top.andyron.common.exception;

import top.andyron.model.common.enums.AppHttpCodeEnum;

/**
 * 自定义异常
 * @author andyron
 **/
public class CustomException extends RuntimeException {
    private AppHttpCodeEnum appHttpCodeEnum;

    public CustomException(AppHttpCodeEnum appHttpCodeEnum) {
        this.appHttpCodeEnum = appHttpCodeEnum;
    }

    public AppHttpCodeEnum getAppHttpCodeEnum() {
        return appHttpCodeEnum;
    }
}
