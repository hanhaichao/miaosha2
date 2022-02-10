package com.Long.Error;


   /**
      * @autor Jack Chao
      * @version
      * @ClassName : BusinessException
      * @date : 2022/2/8 10:24
      * @description: 包装器模式业务异常类实现
      */

public class BusinessException extends Exception implements CommonError {
    //内部强关联一个CommonError
    private CommonError commonError;

    //直接接收commonError构造业务异常
    public BusinessException(CommonError commonError){
        super();
        this.commonError =commonError;
    }

    //接受自定义的errMsg的方式构造业务异常
    public BusinessException(CommonError commonError, String errMsg){
        super();
        this.commonError =commonError;
        this.commonError.setErrorMsg(errMsg);
    }
    @Override
    public int getErrorCode() {
        return this.commonError.getErrorCode();
    }

    @Override
    public String getErrorMsg() {
        return this.commonError.getErrorMsg();
    }

    @Override
    public CommonError setErrorMsg(String errMsg) {
        this.commonError.setErrorMsg(errMsg);
        return this;
    }
}
