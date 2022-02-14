package com.poscoict.jblog.repository;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.poscoict.jblog.vo.BlogVo;

@Repository
public class BlogRepository {
	@Autowired
	private SqlSession sqlSession;

	public boolean insertjoin(String id) {
		Map<Object, Object> map = new HashMap<>();
		map.put("title",  id + "님의 블로그");
		map.put("logo", "/images/logo.jpg");
		map.put("userId", id);
		int count = sqlSession.insert("blog.insertjoin", map);
		return count == 1;
	}

	public BlogVo select(String id) {
		return sqlSession.selectOne("blog.select", id);
		
	}
	
	public boolean update(BlogVo vo) {
		int count = sqlSession.update("blog.update", vo);
		return count == 1;
	}

}
