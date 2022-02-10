package com.Long.ViewObject;

import lombok.Data;

import java.io.Serializable;
   /**
      * @autor Jack Chao
      * @version
      * @ClassName : UserVo
      * @date : 2022/2/8 10:25
      *@description: 安全性考虑，前端获得的数据仅有这些
      */

@Data
public class UserVo implements Serializable {
    private Integer id;
    private String name;
    private Byte gender;
    private Integer age;
}
