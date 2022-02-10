package org.example.controller.controllerService;

import com.alibaba.druid.util.StringUtils;
import org.example.controller.error.BusinessException;
import org.example.controller.error.EmBusinessError;
import org.example.controller.response.CommonReturnType;
import org.example.controller.viewobject.UserVO;
import org.example.service.UserService;
import org.example.service.model.UserModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

@RestController
@RequestMapping("/user")
//@CrossOrigin(allowCredentials = "true",allowedHeaders = "*")//解决跨域问题与ajax不匹配localhost
//允许跨域传输所有的header参数，将用于使用token放入header域，做session共享的跨域请求
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private HttpServletRequest httpServletRequest;

    /**
     * @param telphone:电话号码
     * @return CommonReturnType: 通用返回类型
     * @description 用户获取opt短信
     */
    @PostMapping(value = "/getotp", consumes = CONTENT_TYPE_FORMED)
    @ResponseBody
    public CommonReturnType getOtp(@RequestParam(name = "telphone")String telphone) {
        //生成随机otp验证码，一次性验证码
        Random random = new Random();
        int randomCode = random.nextInt(99999);
        randomCode += 10000;
        String otpCode = String.valueOf(randomCode);// 将 int 变量  转换成字符串
        //将OTP验证码与对应用户的手机号进行关联，使用Http session，分布式应用中使用redis
        httpServletRequest.getSession().setAttribute(telphone,otpCode);
        //将OTP验证码通过短信通道发送给用户，省略
        System.out.println("telphone = "+telphone+"::otpCode = "+otpCode);
        return CommonReturnType.create(null);
    }

    /**
     * @param telphone:手机号
     * @param otpCode:验证码
     * @param name:用户名
     * @param age:用户年龄
     * @param gender:用户性别
     * @param password:密码
     * @return CommonReturnType
     * @description 用户注册
     */
    @Transactional
//    根据你的配置，设置是否自动开启事务（要失败就都失败）
//    自动提交事务或者遇到异常自动回滚
    @PostMapping(value = "/register", consumes = CONTENT_TYPE_FORMED)
    @ResponseBody
    public CommonReturnType register(@RequestParam(name = "telphone") String telphone,
                                     @RequestParam(name = "otpCode") String otpCode,
                                     @RequestParam(name = "name") String name,
                                     @RequestParam(name = "age") Integer age,
                                     @RequestParam(name = "gender") Integer gender,
                                     @RequestParam(name = "password") String password) throws BusinessException, UnsupportedEncodingException, NoSuchAlgorithmException {
        //验证手机号和对应的otp验证码一致
        String inSessionOtpCode = (String) this.httpServletRequest.getSession().getAttribute(telphone);
        if (!StringUtils.equals(otpCode, inSessionOtpCode)) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, "验证码输入有误");
        }
        //用户注册流程
        UserModel userModel = new UserModel();
        userModel.setName(name);
        userModel.setAge(age);
        userModel.setGender(new Byte(String.valueOf(gender.intValue())));
        userModel.setTelphone(telphone);
        userModel.setRegisterMode("byPhone");
        userModel.setEncrptPassword(this.encodeByMD5(password));
        userService.register(userModel);
        return CommonReturnType.create(null);
    }

    /**
     * @param telphone :手机号
     * @param password :密码
     * @return CommonReturnType
     * @description 用户登录
     */
    @PostMapping(value = "/login", consumes = CONTENT_TYPE_FORMED)
    @ResponseBody
    public CommonReturnType login(@RequestParam(name = "telphone")String telphone,
                                  @RequestParam(name = "password") String password) throws BusinessException, UnsupportedEncodingException, NoSuchAlgorithmException {
        //入参校验
        if (StringUtils.isEmpty(telphone)||StringUtils.isEmpty(password)) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }
        //调用登录服务
        UserModel userModel = userService.validateLogin(telphone,this.encodeByMD5(password));
        //将登录凭证加入到用户登录成功的session内
        this.httpServletRequest.getSession().setAttribute("IS_LOGIN",true);
        this.httpServletRequest.getSession().setAttribute("LOGIN_USER",userModel);
        return CommonReturnType.create(null);
    }

    /**
     * @param str :用户密码
     * @return String :加密密码
     * @description 为用户密码加密
     */
    private String encodeByMD5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        // 确定计算方法
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        BASE64Encoder base64Encoder = new BASE64Encoder();
        // 加密字符串
        String newStr = base64Encoder.encode(md5.digest(str.getBytes("utf-8")));
        return newStr;
    }

    /**
     * @param id :用户id
     * @return CommonReturnType
     * @description 根据id查用户
     */
    @GetMapping("/get")
    @ResponseBody
    public CommonReturnType getUser(@RequestParam(name = "id")Integer id) throws BusinessException{
        //通过调用service服务来获取对应对象的id并返回给前端
        UserModel userModel = userService.getUserById(id);
        if (userModel == null) {
            throw new BusinessException(EmBusinessError.USER_NOT_EXIST);
        }
        //引用userVO来处理不必要信息
        UserVO userVO = convertFromModel(userModel);
        return CommonReturnType.create(userVO);
    }

    /**
     * @param userModel：用户模型
     * @return UserVO：用户view object
     * @description 将userModel转换为UserVO
     */
    private UserVO convertFromModel(UserModel userModel) {
        if (userModel == null) {
            return null;
        }
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(userModel,userVO);
        //BeanUtils.copyProperties方法进行对象之间属性的赋值，
        // 避免通过get、set方法一个一个属性的赋值
        return userVO;
    }


}
