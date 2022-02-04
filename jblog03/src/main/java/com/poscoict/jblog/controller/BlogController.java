package com.poscoict.jblog.controller;

import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.poscoict.jblog.security.AuthUser;
import com.poscoict.jblog.service.BlogService;
import com.poscoict.jblog.service.CategoryService;
import com.poscoict.jblog.service.FileUploadService;
import com.poscoict.jblog.service.PostService;
import com.poscoict.jblog.vo.BlogVo;
import com.poscoict.jblog.vo.CategoryVo;
import com.poscoict.jblog.vo.PostVo;
import com.poscoict.jblog.vo.UserVo;

@Controller
@RequestMapping("/{id}")
public class BlogController {
	@Autowired
	private ServletContext servletContext;
	
	@Autowired 
	private BlogService blogService;
	
	@Autowired
	private FileUploadService fileUploadService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private PostService postService;
	
	@RequestMapping("")
	public String blogmain(@AuthUser UserVo authUser, @PathVariable("id") String id, UserVo userVo, BlogVo blogVo, CategoryVo categoryVo, PostVo postvo, Model model) {
		servletContext.setAttribute("blogvo", blogService.select(id));
		Map<String, Object> map1 = categoryService.select(id);
		model.addAttribute("map1", map1);
		Map<String, Object> map2 = postService.selectAll();
		model.addAttribute("map2", map2);
		servletContext.setAttribute("postvo", postService.selectOne());
		return "blog/blog-main";
	}
	
	@RequestMapping("/admin/basic")
	public String adminbasic(@AuthUser UserVo authUser, @PathVariable("id") String id, UserVo userVo, BlogVo blogVo, CategoryVo categoryVo, Model model) {
		servletContext.setAttribute("blogvo", blogService.select(id));
		Map<String, Object> map = categoryService.select(id);
		model.addAttribute("map", map);
		return "blog/blog-admin-basic";
	}
	
	@RequestMapping(value = "/basic/update", method = RequestMethod.POST)
	public String update(@RequestParam(value = "logo1") MultipartFile multipartFile, BlogVo blogVo, @PathVariable("id") String id) {
		String url = fileUploadService.restore(multipartFile);
		blogVo.setLogo(url);
		blogService.update(blogVo);
		if(blogService.update(blogVo)) {
			servletContext.setAttribute("blogvo", blogService.select(id));
		}
		return "redirect:/{id}";
	}
	
	@RequestMapping("/admin/category")
	public String admincategory(@AuthUser UserVo authUser, @PathVariable("id") String id, UserVo userVo, BlogVo blogVo, CategoryVo categoryVo, Model model) {
		servletContext.setAttribute("blogvo", blogService.select(id));
		Map<String, Object> map = categoryService.select(id);
		model.addAttribute("map", map);
		int cnt = categoryService.cnt(categoryVo);
		model.addAttribute("cnt", cnt);
		return "blog/blog-admin-category";
	}
	
	@RequestMapping("/category/delete/{no}")
	public String delete(@PathVariable("no") Long no, Model model) {
		model.addAttribute("no", no);
		categoryService.delete(no);
		return "redirect:/{id}/admin/category";
	}

	@RequestMapping(value = "/category/insert", method = RequestMethod.POST)
	public String insert(CategoryVo categoryVo) {
		categoryService.insert(categoryVo);
		return "redirect:/{id}";
	}
	
	@RequestMapping("/admin/write")
	public String adminwrite(@AuthUser UserVo authUser, @PathVariable("id") String id, UserVo userVo, BlogVo blogVo, CategoryVo categoryVo, Model model) {
		servletContext.setAttribute("blogvo", blogService.select(id));
		Map<String, Object> map = categoryService.select(id);
		model.addAttribute("map", map);
		int cnt = categoryService.cnt(categoryVo);
		model.addAttribute("cnt", cnt);
		return "blog/blog-admin-write";
	}
	
	@RequestMapping(value = "/post/write", method = RequestMethod.POST)
	public String insertpost(PostVo postVo) {
		postService.insert(postVo);
		return "redirect:/{id}";
	}
	
}
