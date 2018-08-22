package com.people.cbsadmin.api.action;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.people.cbsadmin.api.BaseController;
import com.people.cbsadmin.business.read.BaseDicReadManage;
import com.people.cbsadmin.model.vo.BaseDicQueryVo;


/**
 * 
 * 字典
 * <p style="display:none">modifyRecord</p>
 * <p style="display:none">version:V1.0,author:dusai,date:2017年8月3日 下午2:37:23,content: </p>
 * @author dusai
 * @date 2017年8月3日 下午2:37:23
 * @since
 * @version
 */
@Controller
@RequestMapping(value = "/dict")
public class DictController extends BaseController {

	@Resource
	private BaseDicReadManage baseDicReadManage;

	/**
	 * 
	 * <p>查询所有字典数据</p> 
	 * @author dusai
	 * @date 2017年8月3日 下午3:24:42
	 * @return
	 * @see
	 */
	@RequestMapping(value="/findAllDictData")
	@ResponseBody
	public Object findAllDictData() {
		BaseDicQueryVo querys = new BaseDicQueryVo();
		return success(baseDicReadManage.queryBaseDicList(querys));
	}
	
	/**
	 * 
	 * <p>根据key查询字典数据</p> 
	 * @author dusai
	 * @date 2017年8月3日 下午3:24:52
	 * @param querys
	 * @return
	 * @see
	 */
	@RequestMapping(value="/findDictByKey")
	@ResponseBody
	public Object findDictByKey(@RequestBody BaseDicQueryVo querys) {
		if (querys.getDicKey() == null) {
			return fail("dickey is null");
		}
		return success(baseDicReadManage.queryBaseDicByDicKey(querys.getDicKey()));
	}
	
	/**
	 * 
	 * <p>根据父级key查询子级字典数据</p> 
	 * @author dusai
	 * @date 2017年8月3日 下午3:24:52
	 * @param querys
	 * @return
	 * @see
	 */
	@RequestMapping(value="/findDictByParentKey")
	@ResponseBody
	public Object findDictByParentKey(@RequestBody BaseDicQueryVo querys) {
		if (querys.getDicPkey() == null) {
			return fail("dicPkey is null");
		}
		return success(baseDicReadManage.queryBaseDicByDicPKey(querys.getDicPkey()));
	}
}
