package org.example.service;

import org.example.controller.error.BusinessException;
import org.example.service.model.UserModel;
import org.springframework.dao.DuplicateKeyException;

public interface UserService {
    /**
     * @param id：用户id
     * @return UserModel:用户模型
     * @description 根据用户id查用户模型
     */
    UserModel getUserById(Integer id);

    /**
     * @param userModel:用户模型
     * @return null
     * @description 注册用户
     */
    void register(UserModel userModel) throws BusinessException, DuplicateKeyException;
         //真正处理的是UserModel对象
    /**
     * @param telphone:电话号
     * @param encrptPassword: 加密密码
     * @return UserModel:用户模型
     * @description 用户校验
     */
    UserModel validateLogin(String telphone , String encrptPassword)throws BusinessException;
}
