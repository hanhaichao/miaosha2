package org.example.controller.controllerService;

import org.example.controller.error.BusinessException;
import org.example.controller.error.EmBusinessError;
import org.example.controller.response.CommonReturnType;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class BaseController {

    /**
     * 后端要消费的前端的数据类型
     */
    public static final String CONTENT_TYPE_FORMED="application/x-www-form-urlencoded";

    /**
     * @param request
     * @param ex
     * @return Object
     * @description 定义exception handler解决未被controller层吸收的exception
     */
    @ExceptionHandler(Exception.class)//处理全局异常
    @ResponseStatus(HttpStatus.OK)  //获取Http状态
    @ResponseBody
    public Object handlerException(HttpServletRequest request, Exception ex) {
        Map<String,Object> responseData = new HashMap<>();
        if (ex instanceof BusinessException) {
//            它的具体作用是测试左边的对象是否是右边类或者该类的子类创建的实例对象，是，则返回true，否则返回false。
            BusinessException businessException = (BusinessException)ex;
            responseData.put("errCode",businessException.getErrCode());
            responseData.put("errMsg",businessException.getErrMsg());
            //如果有错误就用hashmap接收，如果没有就未知错误
        }else {
            responseData.put("errCode", EmBusinessError.UNKNOWN_ERROR.getErrCode());
            responseData.put("errMsg", EmBusinessError.UNKNOWN_ERROR.getErrMsg());
        }
        return CommonReturnType.create(responseData,"fail");
    }
}
