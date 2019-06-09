package com.mayikt.weixin.mp.handler;


import com.mayikt.api.member.service.MemberService;
import com.mayikt.common.base.BaseResponse;
import com.mayikt.common.constants.Constants;
import com.mayikt.common.core.utils.RedisUtil;
import com.mayikt.common.core.utils.RediskeyUtils;
import com.mayikt.common.core.utils.RegexUtils;
import com.mayikt.member.entity.UserEntity;
import com.mayikt.weixin.mp.builder.TextBuilder;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;

import static me.chanjar.weixin.common.api.WxConsts.XmlMsgType;

/**
 * @author Binary Wang(https://github.com/binarywang)
 */
@Component
@Slf4j
public class MsgHandler extends AbstractHandler {

    /**
     * 发送验证码消息
     */
    @Value("${mayikt.weixin.registration.code.message}")
    private String registrationCodeMessage;
    /**
     * 默认回复消息
     */
    @Value("${mayikt.weixin.default.registration.code.message}")
    private String defaultRegistrationCodeMessage;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private MemberService memberServiceFeign;

    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context, WxMpService weixinService,
                                    WxSessionManager sessionManager) {

        if (!wxMessage.getMsgType().equals(XmlMsgType.EVENT)) {
            // TODO 可以选择将消息保存到本地
        }

        // 当用户输入关键词如“你好”，“客服”等，并且有客服在线时，把消息转发给在线客服
        try {
            if (StringUtils.startsWithAny(wxMessage.getContent(), "你好", "客服")
                    && weixinService.getKefuService().kfOnlineList().getKfOnlineList().size() > 0) {
                return WxMpXmlOutMessage.TRANSFER_CUSTOMER_SERVICE().fromUser(wxMessage.getToUser())
                        .toUser(wxMessage.getFromUser()).build();
            }
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
        // 1. 获取微信客户端发送的消息
        String fromContent = wxMessage.getContent();
        log.info("fromContent:" + fromContent);
        // 2.使用正则表达式验证消息是否为手机号码格式
        if (RegexUtils.checkMobile(fromContent)){
            // 2.1根据手机号码调用会员服务接口查询用户信息是否存在
            BaseResponse<UserEntity> response = memberServiceFeign.existMobile(fromContent);
            if(Constants.HTTP_RES_CODE_200.equals(response.getCode())){
                return new TextBuilder().build("该手机号:"+fromContent+"已经存在", wxMessage, weixinService);
            }

            if(!Constants.HTTP_RES_CODE_EXISTMOBILE_202.equals(response.getCode())){
                return new TextBuilder().build(response.getMsg(), wxMessage, weixinService);
            }
            // 3.如果是手机号码格式的话,随机生成4位数字验证码
            int registCode = getRegistCode();
            // 4.将验证码存放在Redis中
            String weixinCodeKey = RediskeyUtils.getWeixinCode(fromContent);
            redisUtil.setString(weixinCodeKey, registCode + "", Constants.WEIXINCODE_TIMEOUT);
            String content = String.format(registrationCodeMessage, registCode);
            return new TextBuilder().build(content, wxMessage, weixinService);
        }
            // TODO 组装回复消息
            // String content = "收到信息内容：" + JsonUtils.toJson(wxMessage);
        return new TextBuilder().build(defaultRegistrationCodeMessage, wxMessage, weixinService);

    }

    // 获取注册码
    private int getRegistCode(){
        int registCode = (int)(Math.random()*9000 +1000);
        return registCode;
    }

}
