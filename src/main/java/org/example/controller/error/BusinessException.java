package org.example.controller.error;

public class BusinessException extends Exception implements CommonError {

    private CommonError commonError;

    /**
     * @param commonError:
     * @return null
     * @description 直接接收EmBusinessError的传参用于构造业务异常
     */
    //直接接受Em的传参用于构造业务异常
    public BusinessException(CommonError commonError) {
        super();
        this.commonError = commonError;
    }

    /**
     * @param commonError：
     * @param errMsg :错误信息
     * @return null
     * @description 接收自定义errMsg的方式构造业务异常
     */
//    接收自定义errMsg的方式构造业务异常
    public BusinessException(CommonError commonError,String errMsg) {
        super();
        this.commonError = commonError;
        this.commonError.setErMsg(errMsg);
    }

    @Override
    public int getErrCode() {

        return this.commonError.getErrCode();
    }

    @Override
    public String getErrMsg() {
        return this.commonError.getErrMsg();
    }

    @Override
    //接口就是为了方便改，再重写添加就好了，解耦合
    public CommonError setErMsg(String errMsg) {
        this.commonError.setErMsg(errMsg);
        return this;
    }
}
