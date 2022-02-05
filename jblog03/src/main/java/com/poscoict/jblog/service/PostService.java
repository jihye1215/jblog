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
	
	public PostVo selectOne(String id) {
		return postRepository.selectOne(id);
	}
	
	public Map<String, Object> selectAll(String id) {
		HashMap<String, Object> map = new HashMap<>();
		List<PostVo> plist = null;
		plist = postRepository.selectAll(id);
		map.put("plist", plist);
		return map;
	}
	
	public PostVo selectPost(Long no) {
		return postRepository.selectPost(no);
	}
	
	public Map<String, Object> selectCategoryno(Long no) {
		HashMap<String, Object> map = new HashMap<>();
		List<PostVo> plist = null;
		plist = postRepository.selectCategoryno(no);
		map.put("plist", plist);
		return map;
	}
	
	public PostVo selectCategoryPost(Long no) {
		return postRepository.selectCategoryPost(no);
	}
}
