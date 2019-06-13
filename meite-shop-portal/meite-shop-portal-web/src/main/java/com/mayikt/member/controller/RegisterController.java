package com.mayikt.member.controller;

import com.alibaba.fastjson.JSONObject;
import com.mayikt.api.member.service.MemberRegisterService;
import com.mayikt.common.base.BaseResponse;
import com.mayikt.common.base.CommonCode;
import com.mayikt.common.core.utils.MiteBeanUtils;
import com.mayikt.common.view.ViewUtils;
import com.mayikt.member.input.dto.UserInpDTO;
import com.mayikt.member.vo.RegisterVo;
import com.mayikt.web.base.BaseWebController;
import com.mayikt.web.utils.RandomValidateCodeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotBlank;

/**
 * @version V1.0
 * @description: 注册请求
 * @author: 97后互联网架构师-余胜军
 * @contact: QQ644064779、微信yushengjun644 www.mayikt.com
 * @date: 2019年1月3日 下午3:03:17
 * @Copyright 该项目“基于SpringCloud2.x构建微服务电商项目”由每特教育|蚂蚁课堂版权所有，未经过允许的情况下，
 * 私自分享视频和源码属于违法行为。
 */
@Controller
@Slf4j
public class RegisterController extends BaseWebController {
    /**
     * 跳转到登陆页面页面
     */
    private static final String MB_LOGIN_FTL = "member/login";

    @Autowired
    private MemberRegisterService memberRegisterServiceFeign;

    /**
     * 跳转到注册页面
     *
     * @return
     */
    @GetMapping("/register")
    public String getRegister() {
        return ViewUtils.PAGE_REGISTER_VIEW;
    }

    /**
     * 跳转到注册页面
     *
     * @return
     */
    @PostMapping("/register")
    public String postRegister(@ModelAttribute("registerVo") @Validated RegisterVo registerVo,
                               BindingResult bindingResult, HttpSession httpSession, Model model) {

        // 1.接受表单参数(验证码) 创建对象接受vo do dto
        if (bindingResult.hasErrors()) {
            // 如果参数由错误的话  获取第一个错误\
            String errorMsg = bindingResult.getFieldError().getDefaultMessage();
            setErrorMsg(model,errorMsg);
            log.error(errorMsg);
            return ViewUtils.PAGE_REGISTER_VIEW;
        }

        // 2.判断图形验证码是否正确
        String graphicCode = registerVo.getGraphicCode();
        Boolean flag = RandomValidateCodeUtil.checkVerify(graphicCode, httpSession);
        if(!flag){
            setErrorMsg(model,"图形验证码不正确");
            log.error("图形验证码不正确");
            return ViewUtils.PAGE_REGISTER_VIEW;
        }


        // 3.调用会员服务接口实现注册 将前端提交的vo 转换dto
        UserInpDTO userInpDTO = MiteBeanUtils.E2T(registerVo, UserInpDTO.class);
        try {
            BaseResponse<JSONObject> response = memberRegisterServiceFeign.register(userInpDTO, registerVo.getRegistCode());
            if(!isSuccess(response)){
                setErrorMsg(model,response.getMsg());
                log.error(response.getMsg());
                return ViewUtils.PAGE_REGISTER_VIEW;
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Feign调用 memberRegisterServiceFeign.register  异常");
            setErrorMsg(model,CommonCode.SERVER_ERROR.message());
            return ViewUtils.PAGE_REGISTER_VIEW;
        }
        // 注册成功,跳转到登陆页面
        return MB_LOGIN_FTL;
    }

}
