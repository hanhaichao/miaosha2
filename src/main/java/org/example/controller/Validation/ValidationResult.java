package org.example.controller.Validation;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class ValidationResult {
    /**
     * 校验结果是否有错
     */
    private boolean hasErrors = false;

    /**
     * 存放错误信息的map
     */
    private Map<String, String> errorMsgMap = new HashMap<>();

    /**
     * @description 实现通用的通过格式化字符串信息获取错误结果的msg方法
     */
    public String getErrMsg() {
//        可用来把字符串数组拼接成整条字符串，错误信息通过逗号组装起来
        return StringUtils.join(errorMsgMap.values().toArray(), ",");
    }

    public boolean isHasErrors() {

        return hasErrors;
    }

    public void setHasErrors(boolean hasErrors) {

        this.hasErrors = hasErrors;
    }

    public Map<String, String> getErrorMsgMap() {

        return errorMsgMap;
    }

    public void setErrorMsgMap(Map<String, String> errorMsgMap) {

        this.errorMsgMap = errorMsgMap;
    }
}
