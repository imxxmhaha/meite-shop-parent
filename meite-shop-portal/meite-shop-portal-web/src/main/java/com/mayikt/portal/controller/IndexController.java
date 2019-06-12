package com.mayikt.portal.controller;

import com.mayikt.common.view.ViewUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

	/**
	 * 跳转到首页
	 * 
	 * @return
	 */
	@RequestMapping(value={"/","/index","/index.html"})
	//@RequestMapping(value={"/","/index"})
	public String index() {
		return ViewUtils.PAGE_INDEX_VIEW;
	}


}