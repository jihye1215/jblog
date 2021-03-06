package com.poscoict.jblog.controller;

import java.util.Map;
import java.util.Optional;

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
@RequestMapping("/{id:(?!assets)(?!images).*}")
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
	
	@RequestMapping({"", "/{pathNo1}", "/{pathNo1}/{pathNo2}"})
	public String blogmain(@PathVariable("id") String id, @PathVariable("pathNo1") Optional<Long> pathNo1, @PathVariable("pathNo2") Optional<Long> pathNo2, UserVo userVo, BlogVo blogVo, CategoryVo categoryVo, PostVo postvo, Model model) {
		Long categoryNo = null;
		Long postNo = null;
		
		if(pathNo2.isPresent()) {
			categoryNo = pathNo1.get();
			postNo = pathNo2.get();
		} else if(pathNo1.isPresent()) {
			categoryNo = pathNo1.get();
		}
		
		servletContext.setAttribute("blogvo", blogService.select(id));
		Map<String, Object> map1 = categoryService.select(id);
		model.addAttribute("map1", map1);
		Map<String, Object> map2 = null;
		if(categoryNo == null && postNo == null) {
			map2 = postService.selectAll(id);
		} else {
			map2 = postService.selectCategoryno(categoryNo);
		}	
		model.addAttribute("map2", map2);
		
		if(categoryNo == null && postNo == null) {
			servletContext.setAttribute("postvo", postService.selectOne(id));
		} else if(postNo == null){
			servletContext.setAttribute("postvo", postService.selectCategoryPost(categoryNo));
		} else {
			servletContext.setAttribute("postvo", postService.selectPost(postNo));
		}
		
		return "blog/blog-main";
	}
	
	@RequestMapping("/admin/basic")
	public String adminbasic(@AuthUser UserVo authUser, @PathVariable("id") String id, UserVo userVo, BlogVo blogVo, CategoryVo categoryVo, Model model) {
		servletContext.setAttribute("blogvo", blogService.select(id));
		if(authUser == null || !id.equals(authUser.getId())) {
			return "redirect:/{id}";
		}
		Map<String, Object> map = categoryService.select(id);
		model.addAttribute("map", map);
		return "blog/blog-admin-basic";
	}
	
	@RequestMapping(value = "/basic/update", method = RequestMethod.POST)
	public String update(@AuthUser UserVo authUser, @RequestParam(value = "logo1") MultipartFile multipartFile, BlogVo blogVo, @PathVariable("id") String id) {
		if(authUser == null || !id.equals(authUser.getId())) {
			return "redirect:/{id}";
		}
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
		if(authUser == null || !id.equals(authUser.getId())) {
			return "redirect:/{id}";
		}
		servletContext.setAttribute("blogvo", blogService.select(id));
		Map<String, Object> map = categoryService.select(id);
		model.addAttribute("map", map);
		return "blog/blog-admin-category";
	}
	
	@RequestMapping("/category/delete/{no}")
	public String delete(@AuthUser UserVo authUser, @PathVariable("id") String id, @PathVariable("no") Long no, Model model) {
		if(authUser == null || !id.equals(authUser.getId())) {
			return "redirect:/{id}";
		}
		model.addAttribute("no", no);
		categoryService.delete(no);
		return "redirect:/{id}/admin/category";
	}

	@RequestMapping(value = "/category/insert", method = RequestMethod.POST)
	public String insert(@AuthUser UserVo authUser, @PathVariable("id") String id, CategoryVo categoryVo) {
		if(authUser == null || !id.equals(authUser.getId())) {
			return "redirect:/{id}";
		}
		categoryService.insert(categoryVo);
		return "redirect:/{id}";
	}
	
	@RequestMapping("/admin/write")
	public String adminwrite(@AuthUser UserVo authUser, @PathVariable("id") String id, UserVo userVo, BlogVo blogVo, CategoryVo categoryVo, Model model) {
		if(authUser == null || !id.equals(authUser.getId())) {
			return "redirect:/{id}";
		}
		servletContext.setAttribute("blogvo", blogService.select(id));
		Map<String, Object> map = categoryService.select(id);
		model.addAttribute("map", map);
		return "blog/blog-admin-write";
	}
	
	@RequestMapping(value = "/post/write", method = RequestMethod.POST)
	public String insertpost(@AuthUser UserVo authUser, @PathVariable("id") String id, PostVo postVo) {
		if(authUser == null || !id.equals(authUser.getId())) {
			return "redirect:/{id}";
		}
		postService.insert(postVo);
		return "redirect:/{id}";
	}
	
//	@RequestMapping("post/{no}")
//	public String blogpost(@PathVariable("id") String id, @PathVariable("no") Long no, UserVo userVo, BlogVo blogVo, CategoryVo categoryVo, PostVo postvo, Model model) {
//		servletContext.setAttribute("blogvo", blogService.select(id));
//		Map<String, Object> map1 = categoryService.select(id);
//		model.addAttribute("map1", map1);
//		Map<String, Object> map2 = postService.selectAll(id);
//		model.addAttribute("map2", map2);
//		servletContext.setAttribute("postvo", postService.selectPost(no));
//		return "blog/blog-main";
//	}
//	
//	@RequestMapping("category/{categoryno}")
//	public String blogcategory(@PathVariable("id") String id, @PathVariable("categoryno") Long categoryno, UserVo userVo, BlogVo blogVo, CategoryVo categoryVo, PostVo postvo, Model model) {
//		servletContext.setAttribute("blogvo", blogService.select(id));
//		Map<String, Object> map1 = categoryService.select(id);
//		model.addAttribute("map1", map1);
//		Map<String, Object> map2 = postService.selectCategoryno(categoryno);
//		model.addAttribute("map2", map2);
//		servletContext.setAttribute("postvo", postService.selectCategoryPost(categoryno));
//		return "blog/blog-main";
//	}
//	
//	@RequestMapping("category/{categoryno}/post/{no}")
//	public String blogcategorypost(@PathVariable("id") String id, @PathVariable("no") Long no, UserVo userVo, @PathVariable(value = "categoryno", required = false) Long categoryno, BlogVo blogVo, CategoryVo categoryVo, PostVo postvo, Model model) {
//		servletContext.setAttribute("blogvo", blogService.select(id));
//		Map<String, Object> map1 = categoryService.select(id);
//		model.addAttribute("map1", map1);
//		Map<String, Object> map2 = null;
//		if(categoryno == null) {
//			map2 = postService.selectAll(id);
//		} else if(categoryno != null) {
//			map2 = postService.selectCategoryno(categoryno);
//		}
//		model.addAttribute("map2", map2);
//		servletContext.setAttribute("postvo", postService.selectPost(no));
//		return "blog/blog-main";
//	}
}
