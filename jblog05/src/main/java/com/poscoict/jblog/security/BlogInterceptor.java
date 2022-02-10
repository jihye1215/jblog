package com.poscoict.jblog.security;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.poscoict.jblog.service.BlogService;
import com.poscoict.jblog.vo.BlogVo;



public class BlogInterceptor extends HandlerInterceptorAdapter {
	@Autowired
	private BlogService blogService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		ServletContext servletContext = request.getServletContext();
		BlogVo blogvo = (BlogVo)request.getServletContext().getAttribute("blogvo");
		
		if(blogvo == null) {
			servletContext.setAttribute("blogvo", blogService.select(blogvo.getUserId()));
		}
		return true;
	}

}
