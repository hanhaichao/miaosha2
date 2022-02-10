package org.example.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.example.controller.Validation.ValidationResult;
import org.example.controller.Validation.ValidatorImpl;
import org.example.controller.error.BusinessException;
import org.example.controller.error.EmBusinessError;
import org.example.entity.User;
import org.example.entity.UserPassword;
import org.example.mapper.UserMapper;
import org.example.mapper.UserPasswordMapper;
import org.example.service.UserService;
import org.example.service.model.UserModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserPasswordMapper userPasswordMapper;

    @Autowired
    private ValidatorImpl validator;

    /**
     * @param id：用户id
     * @return UserModel:用户模型
     * @description 根据用户id查用户模型
     */
    @Override
    public UserModel getUserById(Integer id) {
        User user = userMapper.selectByPrimaryKey(id);
        if (user == null) {
            return null;
        }
        UserPassword userPassword = userPasswordMapper.selectByUserId(user.getId());
        return convertFromEntity(user, userPassword);
    }

    /**
     * @param userModel:用户模型
     * @return null
     * @description 注册用户
     */
    @Override
    @Transactional
    public void register(UserModel userModel) throws BusinessException, DuplicateKeyException {
        if (userModel == null) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }
        ValidationResult result = validator.validate(userModel);
        if (result.isHasErrors()) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, result.getErrMsg());
        }
 
        User user = convertFromUserModel(userModel);
        //使用insertSelective方法时，如果待插入字段值为null，则不插入该字段，使用数据库的默认值
        userMapper.insertSelective(user);
        userModel.setId(user.getId());
        UserPassword userPassword = convertPasswordFromModel(userModel);
        userPasswordMapper.insertSelective(userPassword);
        return;
    }

    /**
     * @param telphone:电话号
     * @param encrptPassword: 加密密码
     * @return UserModel:用户模型
     * @description 用户登录校验
     */
    @Override
    public UserModel validateLogin(String telphone, String encrptPassword) throws BusinessException{
        //通过用户手机号获取用户信息
        User user = userMapper.selectByTelphone(telphone);
        if (user == null) {
            throw new BusinessException(EmBusinessError.USER_LOGIN_FAIL);
        }
        UserPassword userPassword = userPasswordMapper.selectByUserId(user.getId());
        UserModel userModel = convertFromEntity(user,userPassword);
        //将数据库中的密码与输入的密码进行比对
        if (!StringUtils.equals(encrptPassword,userModel.getEncrptPassword())) {
            throw new BusinessException(EmBusinessError.USER_LOGIN_FAIL);
        }
        return userModel;
    }

    /**
     * @param userModel:用户模型
     * @return User：用户对象
     * @description 将userModel转换为User
     */
    private User convertFromUserModel(UserModel userModel) {
        if (userModel == null) {
            return null;
        }
        User user = new User();
        BeanUtils.copyProperties(userModel, user);
        return user;
    }

    /**
     * @param userModel:用户模型
     * @return UserPassword：用户密码对象
     * @description 将userModel转换为UserPassword
     */
    private UserPassword convertPasswordFromModel(UserModel userModel) {
        if (userModel == null) {
            return null;
        }
        UserPassword userPassword = new UserPassword();
        userPassword.setEncrptPassword(userModel.getEncrptPassword());
        userPassword.setUserId(userModel.getId());
        return userPassword;
    }

    /**
     * @param user: 用户对象
     * @param userPassword: 用户密码对象
     * @return UserModel: 用户模型
     * @description 将user和userPassword转换为UserModel
     */
    private UserModel convertFromEntity(User user, UserPassword userPassword) {
        if (user == null) {
            return null;
        }
        UserModel userModel = new UserModel();
        BeanUtils.copyProperties(user, userModel);
        if (userPassword != null) {
            userModel.setEncrptPassword(userPassword.getEncrptPassword());
        }
        return userModel;
    }
}
