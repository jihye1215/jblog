package com.poscoict.jblog.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poscoict.jblog.repository.PostRepository;
import com.poscoict.jblog.vo.PostVo;

@Service
public class PostService {
	@Autowired
	private PostRepository postRepository;
	
	public Boolean insert(PostVo vo) {
		return postRepository.insertNew(vo);
	}
	
	public PostVo selectOne() {
		return postRepository.selectOne();
	}
	
	public Map<String, Object> selectAll() {
		HashMap<String, Object> map = new HashMap<>();
		List<PostVo> plist = null;
		plist = postRepository.selectAll();
		map.put("plist", plist);
		return map;
	}
}
