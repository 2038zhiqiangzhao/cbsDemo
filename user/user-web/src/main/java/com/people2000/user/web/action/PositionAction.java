package com.people2000.user.web.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.people2000.common.log.LogUtils;
import com.people2000.user.business.write.manage.PositionWriteManage;
import com.people2000.user.model.dto.PositionDTO;
import com.people2000.user.model.vo.PositionVO;
import com.people2000.user.web.BaseAction;

@Controller
@RequestMapping(value = "/position")
public class PositionAction extends BaseAction {

	@Resource(name = "positionWriteManage")
	private PositionWriteManage positionWriteManage;

	private static final Logger logger = LogUtils
			.getLogger(PositionAction.class);

	/**
	 * 查询岗位信息列表action分页
	 * 
	 * @param positionDTO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/queryPositionList", consumes = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public Object queryPositionList(@RequestBody PositionVO positionVO)
			throws Exception {
		LogUtils.getLogger(PositionAction.class).debug("岗位列表查询action被调用");
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			// 调用service中的查询方法
			map = positionWriteManage.queryPositionListF(positionVO);
		} catch (Exception e) {
			logger.info(e.getMessage());
			return fail("查询岗位信息失败!");
		}
		List<PositionDTO> list = (List<PositionDTO>) map.get("list");
		return success(list, Integer.parseInt(map.get("total").toString()));
	}

	/**
	 * 根据岗位名称查询
	 */
	@RequestMapping(value = "/queryPositionByName", consumes = "application/json", method = RequestMethod.POST)
	@ResponseBody
	public Object queryPositionByName(@RequestBody PositionVO positionVO)
			throws Exception {
		LogUtils.getLogger(PositionAction.class).debug("岗位列表查询action被调用");
		// 调用service中的查询方法
		List<PositionDTO> list = positionWriteManage
				.queryPositionByName(positionVO);
		return success(list);
	}

	/**
	 * 新增岗位信息
	 * 
	 * @param positionVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/addPosition")
	@ResponseBody
	public Object addPosition(@RequestBody PositionVO positionVO)
			throws Exception {
		LogUtils.getLogger(PositionAction.class).debug("新增岗位action被调用");
		this.positionWriteManage.savePositionWithTx(positionVO);
		return success("");
	}

	/**
	 * 修改岗位信息
	 * 
	 * @param positionVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/updatePosition")
	@ResponseBody
	public Object updatePosition(@RequestBody PositionVO positionVO)
			throws Exception {
		LogUtils.getLogger(PositionAction.class).debug("修改岗位action被调用");
		this.positionWriteManage.updatePositionWithTx(positionVO);
		return success("");
	}

	/**
	 * 删除岗位信息
	 * 
	 * @param positionVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/deletePosition")
	@ResponseBody
	public Object deletePosition(@RequestBody PositionVO positionVO)
			throws Exception {
		LogUtils.getLogger(PositionAction.class).debug("修改岗位action被调用");
		this.positionWriteManage.deletePositionWithTx(positionVO);
		return success("");
	}

}
