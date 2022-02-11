package com.poscoict.jblog.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.poscoict.jblog.vo.PostVo;

@Repository
public class PostRepository {
	@Autowired 
	private SqlSession sqlSession;
	
	public boolean insertNew(PostVo vo) {
		int count = sqlSession.insert("post.insertNew", vo);
		return count == 1;
	}
	
	public List<PostVo> selectAll(String id) {
		return sqlSession.selectList("post.selectAll", id);
	}
	
	public PostVo selectOne(String id) {
		return sqlSession.selectOne("post.selectOne", id);
	}

	public PostVo selectPost(Long no) {
		return sqlSession.selectOne("post.selectpost", no);
	}
	
	public List<PostVo> selectCategoryno(Long no) {
		return sqlSession.selectList("post.selectcategory", no);
	}
	
	public PostVo selectCategoryPost(Long no) {
		return sqlSession.selectOne("post.selectcategorypost", no);
	}
}
