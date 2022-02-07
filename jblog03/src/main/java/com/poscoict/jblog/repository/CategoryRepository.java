package com.poscoict.jblog.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.poscoict.jblog.vo.CategoryVo;

@Repository
public class CategoryRepository {
	@Autowired
	private SqlSession sqlSession;
	
	public boolean insertjoin(String id) {
		Map<Object, Object> map = new HashMap<>();
		map.put("name", "미분류");
		map.put("description", "기본 카테고리");
		map.put("blogId", id);
		int count = sqlSession.insert("category.insertjoin", map);
		return count == 1;
	}

	public List<CategoryVo> select(String id) {
		return sqlSession.selectList("category.select", id);
	}
	
	public boolean delete(Long no) {
		int count = sqlSession.delete("category.delete", no);
		return count == 1;
	}
	
	public boolean insert(CategoryVo vo) {
		int count = sqlSession.insert("category.insertjoin", vo);
		return count == 1;
	}

}
