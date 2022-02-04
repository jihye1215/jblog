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
	
	public List<PostVo> selectAll() {
		return sqlSession.selectList("post.selectAll");
	}
	
	public PostVo selectOne() {
		return sqlSession.selectOne("post.selectOne");
	}

}
