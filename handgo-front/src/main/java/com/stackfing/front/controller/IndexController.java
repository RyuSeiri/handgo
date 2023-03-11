//package com.stackfing.front.controller;
//
//import com.stackfing.common.utils.OrderUtil;
//import com.stackfing.front.pojo.Index;
//import com.stackfing.front.vo.*;
//import com.stackfing.pojo.Category;
//import com.stackfing.pojo.KillProduct;
//import com.stackfing.pojo.Notice;
//import com.stackfing.pojo.Product;
//import com.stackfing.service.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.ModelMap;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * @Author: fing
// * @Description:
// * @Date: 下午1:46 18-1-26
// */
//
//@Controller
//public class IndexController {
//
//	@Autowired
//	private KillProductService killProductService;
//
//	@Autowired
//	private CategoryService categoryService;
//
//	@Autowired
//	private NoticeService noticeService;
//
//	@Autowired
//	private ProductService productService;
//
//	@Autowired
//	private FrontCommendService frontCommendService;
//
//	@GetMapping("")
//	public String index(ModelMap modelMap, HttpServletRequest request) {
////		request.getSession().setAttribute("username","admin");
//		modelMap.put("killList", getKillProductVo());
//		modelMap.put("categorys", categoryService.findAllParent());
//		modelMap.put("Notices", noticeService.getNoticeList());
////		modelMap.put("commendList", getFrontCommendVoList());
//		modelMap.put("ttt", "123");
//		System.out.println(OrderUtil.newOrder());
//		return "home/home2";
//	}
//
//	private List<KillProductVo> getKillProductVo() {
//		List<KillProduct> list = killProductService.productList();
//		List<KillProductVo> volist = new ArrayList<>();
//		for (KillProduct killProduct : list) {
//			Product product = productService.getProductById(killProduct.getId());
//			KillProductVo killProductVo = new KillProductVo();
//			killProductVo.setId(product.getId());
//			killProductVo.setPhoto(product.getPhotos());
//			killProductVo.setPrice(product.getCurrentPrice());
//			killProductVo.setName(product.getName());
//			volist.add(killProductVo);
//		}
//		return volist;
//	}
//
//	private List<FrontCommendVo> getFrontCommendVoList() {
//		List<Category> categoryList = frontCommendService.getFrontCategory();
//		List<FrontCommendVo> list = new ArrayList<>();
//		for (Category c : categoryList) {
//			FrontCommendVo frontCommendVo = new FrontCommendVo();
//			frontCommendVo.setTitle(c.getName());
//			frontCommendVo.setSubtitle("abc");
//			List frontCategoryVoList = new ArrayList();
//			List<Category> categories = frontCommendService.subCommendCategory(c.getId());
//			for (Category t : categories) {
//				FrontCategoryVo vo = new FrontCategoryVo();
//				vo.setName(t.getName());
//				vo.setId(t.getId());
//				frontCategoryVoList.add(vo);
//			}
//			frontCommendVo.setCategoryList(frontCategoryVoList);
//			List frontProductVolist = new ArrayList();
//			List<Product> products = frontCommendService.starProduct(c.getId());
//			for (Product product : products) {
//				FrontProductVo vo = new FrontProductVo();
//				vo.setId(product.getId());
//				vo.setPhoto(product.getPhotos());
//				vo.setTitle(product.getName());
//				vo.setPrice(product.getCurrentPrice());
//				frontProductVolist.add(vo);
//			}
//
//			frontCommendVo.setStarProduct(frontProductVolist);
//			list.add(frontCommendVo);
//		}
//
//		return list;
//	}
//
//	@GetMapping("/success")
//	public String success() {
//		return "home/success";
//	}
//
//	@GetMapping("/notice")
//	@ResponseBody
//	public List<Notice> notice() {
//		return noticeService.getNoticeList();
//	}
//
//	@GetMapping("/home")
//	public String home(HttpServletRequest request) {
//		return "person/index";
//	}
//
//
//
//	@GetMapping("/collection")
//	public String collection() {
//		return "person/collection";
//	}
//
//	@GetMapping("/v1/category/{id}")
//	@ResponseBody
//	public CategoryVo getSubCategory(@PathVariable Long id) {
//		List<Category> subCategoryById = categoryService.findAllSubCategoryById(id);
//		CategoryVo vo = new CategoryVo();
//		vo.setName(categoryService.findById(id).getName());
//		vo.setSubCategories(subCategoryById);
//		return vo;
//	}
//}
