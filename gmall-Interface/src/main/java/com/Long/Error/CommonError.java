package com.Long.Error;

   /**
      * @autor Jack Chao
      * @version
      * @ClassName : CommonError
      * @date : 2022/2/8 10:24
      * @description: 通用错误接口，处理返回错误信息，避免返回500等恶心的错误码
      */

public interface CommonError {
    int getErrorCode();  //错误码
    String getErrorMsg();  //错误信息
    CommonError setErrorMsg(String errMsg); //自定义错误信息
}
