package com.mayikt.common.base;

public enum ExceptionCodeEum {
	/**
	 * 枚举
	 */
	SYSTEM_EXCEPTION("00001","系统繁忙，请稍后再试"),
	BUISNESS_EXCEPTION("00002","业务异常"),
	MY_NUMBALANCE_PHONE_FORMAT_EXCEPTION("00003","请检查号码是否有误"),
	MY_NUMBALANCE_PHONE_NULL_EXCEPTION("00004","请前往'基本资料--账户信息'绑定手机号码"),
	MY_FEEDBACK_EXCEPTION("00005","内容含有敏感词，请修改后再提交"),
	MY_FEEDBACK_NULL_EXCEPTION("00006","参数userid和feedbacktext不能为空"),
	MY_GOLD_EXCEPTION("00007","参数userId和regionId不能为空！"),
	MY_MEMBERINFO_EXCEPTION("00008","参数userId不能为空!"),
	MY_OUTWEB_EXCEPTION("00009","pl_type不能为空！"),
	MY_SHARE_EXCEPTION("00010","新增注册邀请日志失败！"),
	HOMEPAGE_SEARCH_AREA_EXCEPTION("00011","区域编码不能为空!"),
	HOMEPAGE_SEARCH_MM_EXCEPTION("00012","MM应用搜索结果转换错误!"),
	MY_COLLECT_OPT_EXCEPTION("00013","操作类型 optype 不能为空！"),
	MY_COLLECT_INFO_EXCEPTION("00014","收藏信息 collection 不能为空！"),
	MY_MEMBERRIGHT_EXCEPTION("00015","参数areacode不能都为空!"),
	HOMEPAGE_SEARCH_WORD_EXCEPTION("00016","应用搜索特殊字符错误!"),
	LOGIN_EXCEPTION("00017","登录异常"),
	HOMEPAGE_ADV_GETADVINFO_EXCEPTION("00018","获取轮播广告异常"),
	HOMEPAGE_TOSEARCHRESULTREQ_EXCEPTION("00019","查询词汇异常"),
	HOMEPAGE_TOSEARCHRESULTPAGE_EXCEPTION("00020","去搜索结果页面异常"),
	MY_TOCHANGEPWD_EXCEPTION("00021","去统一认证修改密码异常"),
	URLENCODER_EXCEPTION("00022","URLEncoder异常"),
	SYSTEM_GETFRIENDLINKED_EXCEPTION("00023","获取友情链接异常"),
	SYSTEM_GETPORTALNOTICE_EXCEPTION("00024","查询系统公告异常"),
	SYSTEM_SETFEEDBACKINFO_EXCEPTION("00025","设置意见反馈异常"),
	MY_ENTERMYHOMEPAGE_EXCEPTION("20000","进入我的页面异常"),
	HOMEPAGE_APPPAGE_EXCEPTION("20001","跳转AppPage中间页面异常"),
	PORTAL_RES_PAGE_EXCEPTION("20002","跳转PortalResPageController页面异常"),
	COLUMN_EXCEPTION("20003","跳转ColumnController异常"),
	BIZPLAT_SSO_EXCEPTION("20004","跳转BizPlatSsoController异常"),
	DAS_EVENT_REDIRECT_EXCEPTION("20005","跳转DasEventRedirectController异常"),
	OUTPLAT_SSO_EXCEPTION("20006","跳转OutPlatSsoController异常"),
	POWER_REDIRECT_EXCEPTION("20007","跳转PowerRedirectController异常"),
	COLLECT_REPEAT_EXCEPTION("20008","重复收藏或取消收藏"),
	
	
	
	// ============================智能温度计错误异常码==================================
	HUMITURE_DEVICE_ALARM_NECESSARY_PARAMS_NULL("30001","温湿度报警通知必填参数不能为空"),
	HUMITURE_DEVICE_HISTROY_NECESSARY_PARAMS_NULL("30002","温湿度历史数据必填参数不能为空"),
	HUMITURE_DEVICE_DATA_DEVICE_EXCEPTIONL("30003","数据异常或错误"),
	HUMITURE_OFFLINE_CYCLE_DEVICE_EXCEPTION("30004","离线时间设置接口异常"),
	HUMITURE_DEVICE_IS_OPEN_EXCEPTION("30005","该设备已限制绑定 "),
	HUMITURE_ACTIVATE_DEVICE_EXCEPTION("30006","新建设备接口异常 "),
	HUMITURE_DEVICE_EXCEPTION("30007","设备业务返回结果失败"),
	HUMITURE_DEVICE_DETAIL_EXCEPTION("30008","获取设备详情接口异常"),
	HUMITURE_DEVICE_RULE_EXCEPTION("30009","获取设备规则接口异常"),
	HUMITURE_DEVICE_RULE_UPDATE_EXCEPTION("30010","修改设备规则接口异常"),
	HUMITURE_DEVICE_OWER_EXCEPTIONL("30011","非设备主人"),
	HUMITURE_DEVICE_IS_NOT_EXEIT_EXCEPTIONL("30012","此设备不存在"),
	HUMITURE_DEVICE_DATA_ROPY_EXCEPTIONL("30013","请勿重复绑定"),
	HUMITURE_DEVICE_GET_SETTING_DATA_EXCEPTIONL("30014","获取设备设置数据异常"),
	HUMITURE_DEVICE_SET_FIRE_SWITCH_EXCEPTIONL("30015","超高温/疑似火灾报警开关设置异常"),
	HUMITURE_DEVICE_OPEN_SWITCH_EXCEPTIONL("30016","开放/关闭设备开关设置异常"),
	HUMITURE_DEVICE_QUICK_SETTING_EXCEPTIONL("30017","操作过于频繁"),
	
	;
	
	private String code;
	private String msg;
	
	public String getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}

	private ExceptionCodeEum(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}

}
