package com.people2000.user.api.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.people2000.user.model.vo.SampleVO;

/**
 * 
 * 样例action
 * <p style="display:none">
 * modifyRecord
 * </p>
 * <p style="display:none">
 * version:V1.0,author:admin,date:2017年3月28日 下午2:44:04,content:
 * </p>
 * 
 * @author admin
 * @date 2017年3月28日 下午2:44:04
 * @since
 * @version
 */
@Controller("userAction")
@RequestMapping(value = "/user")
public class SampleAction {

	private static Logger log = LoggerFactory.getLogger(SampleAction.class);

	@RequestMapping(value = "/model", params = "method=add", consumes = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public SampleVO add(@RequestBody SampleVO user) {
		// json 字符串
		log.debug("add user:{}", user);
		if (user == null) {
			log.error("user is null");
		}
		ModelAndView mv = new ModelAndView();
		mv.addObject("user", user);
		mv.setViewName("user");
		mv.addObject("method", "add");
		return user;
	}

	@RequestMapping(value = "/model", params = "method=insert", consumes = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public SampleVO insert(@RequestBody SampleVO user) {// json 字符串
		log.debug("add user:{}", user);
		if (user == null) {
			log.error("user is null");
		}
		return user;
	}

	@RequestMapping(value = "/model", params = "method=modify")
	@ResponseBody
	public SampleVO modify(@RequestBody SampleVO user) {
		log.debug("modify");
		return user;
	}

	@RequestMapping(value = "/model", params = "method=delete")
	@ResponseBody
	public String delete(@RequestParam long id) {
		return "ok";
	}

	@RequestMapping(value = "/model", params = "method=list")
	public ModelAndView list() {
		log.debug("list");
		List<SampleVO> list = new ArrayList<SampleVO>();// userService.listSample();
		ModelAndView mv = new ModelAndView();
		mv.addObject("list", list);
		mv.setViewName("user");
		mv.addObject("method", "list");
		return mv;
	}

	@RequestMapping(value = "/index")
	public String index() {
		return "index";
	}

	@RequestMapping(value = "/user")
	public String index2(HttpServletRequest request, HttpSession session,
			HttpServletResponse response) {
		return "user";
	}

	@RequestMapping("/*/index3")
	public String index3(Map<String, Object> map) {
		return "index";
	}

	@RequestMapping(value = "/index4", params = "param1=value1")
	public String index4(Map<String, Object> map) {
		return "index";
	}

	@RequestMapping(value = "/index5", params = "!param1=value1")
	public String index5(Map<String, Object> map) {
		return "index";
	}

	@RequestMapping(value = "/index6", headers = "content-type=text/html")
	public String index6(Map<String, Object> map) {
		return "index";
	}

	@RequestMapping(value = "/index7/{blogId}", method = RequestMethod.POST)
	public String index7(@PathVariable int blogId) {
		return "index";
	}

	@RequestMapping("/index8")
	public String index8(@RequestParam int id,
			@RequestParam("name") String username) {
		return "index";
	}

	@RequestMapping("/index9")
	public ModelAndView index9(@RequestParam int id,
			@RequestParam("name") String username) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("index");
		mv.addObject("id", id);
		mv.addObject("name", username);
		return mv;
	}

	@RequestMapping(value = "index10", method = RequestMethod.GET)
	@ResponseBody
	public SampleVO index10() {
		SampleVO user = new SampleVO();
		user.setId(100L);
		user.setFirstName("firstName");
		user.setLastName("lastName");
		return user;
	}

	@RequestMapping(value = "index11")
	public String index11(@RequestBody SampleVO user) {
		return "index";
	}

}
