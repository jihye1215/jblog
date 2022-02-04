package com.poscoict.jblog.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poscoict.jblog.repository.CategoryRepository;
import com.poscoict.jblog.vo.CategoryVo;

@Service
public class CategoryService {
	@Autowired
	private CategoryRepository categoryRepository;
	
	public void insertjoin(String id) {
		categoryRepository.insertjoin(id);
	}
	
	public Map<String, Object> select(String id) {
		HashMap<String, Object> map = new HashMap<>();
		List<CategoryVo> clist = null;
		clist = categoryRepository.select(id);
		map.put("clist", clist);
		System.out.println("====================" + clist);
		return map;
	}
	
	public int cnt(CategoryVo vo) {
		return categoryRepository.cnt(vo);
	}
	
	public boolean delete(Long no) {
		return categoryRepository.delete(no);
	}
	
	public boolean insert(CategoryVo vo) {
		return categoryRepository.insert(vo);
	}
}
