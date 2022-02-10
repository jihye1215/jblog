package com.poscoict.jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poscoict.jblog.repository.BlogRepository;
import com.poscoict.jblog.vo.BlogVo;

@Service
public class BlogService {
	@Autowired
	private BlogRepository blogRepository;
	
	public void insertjoin(String id) {
		blogRepository.insertjoin(id);
	}
	
	public BlogVo select(String id) {
		return blogRepository.select(id);
	}
	
	public Boolean update(BlogVo vo) {
		return blogRepository.update(vo);
	}

}
