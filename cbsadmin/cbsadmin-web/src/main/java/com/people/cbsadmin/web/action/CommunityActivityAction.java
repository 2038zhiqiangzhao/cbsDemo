package com.people.cbsadmin.web.action;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.people.cbsadmin.business.read.CommunityActivityAdminReadManage;
import com.people.cbsadmin.business.read.CommunityActivityReadManage;
import com.people.cbsadmin.business.read.CommunityActivityReadOrderManage;
import com.people.cbsadmin.business.write.CommunityActivityOrderWriterManager;
import com.people.cbsadmin.business.write.CommunityActivityWriterManager;
import com.people.cbsadmin.model.dto.CommunityActivityAdminDto;
import com.people.cbsadmin.model.dto.CommunityActivityDto;
import com.people.cbsadmin.model.dto.CommunityActivityTwoDto;
import com.people.cbsadmin.model.po.CommunityActivity;
import com.people.cbsadmin.model.po.CommunityActivityAdmin;
import com.people.cbsadmin.model.po.CommunityActivityOrder;
import com.people.cbsadmin.model.po.CommunityMiddleAdmin;
import com.people.cbsadmin.model.vo.CommunityActivityOrderVo;
import com.people.cbsadmin.model.vo.CommunityActivityVo;
import com.people.cbsadmin.web.BaseAction;
import com.people2000.common.exception.BusinessException;



@Controller
@RequestMapping({ "/communityActivity" })
public class CommunityActivityAction extends BaseAction {
	@Resource
	private CommunityActivityReadManage communityActivityReadManage;
	@Resource
	private CommunityActivityWriterManager communityActivityWriterManager;
	@Resource
	private CommunityActivityOrderWriterManager communityActivityOrderWriterManager;
	@Resource
	private CommunityActivityReadOrderManage communityActivityReadOrderManage;
	@Resource
	private CommunityActivityAdminReadManage communityActivityAdminReadManage;
	
	/**查询列表
	 * 
	 * @return
	 */
	@RequestMapping({ "/queryActivityList" })
	@ResponseBody
	public Object QueryActivityList(@RequestBody CommunityActivityDto communityActivityDto){
		return success(communityActivityReadManage.queryActivityList(communityActivityDto));
		
	}
	
	/**删除
	 * 
	 * @return
	 */
	@RequestMapping({ "/deleteCommunityClass" })
	@ResponseBody
	public Object deleteCommunityClass(@RequestBody CommunityActivityDto communityActivityDto){
		try {
			if(communityActivityDto.getIds()!=null && communityActivityDto.getIds().size()>0){
				List<Long> ids = communityActivityDto.getIds();
				for (Long id : ids) {
					communityActivityDto.setId(id);
					communityActivityDto.setIsDeleted(1);
					communityActivityWriterManager.updateCommunityClass(communityActivityDto);
				}
			}else{
				return fail("ID不能为空");
			}
			
		} catch (Exception e) {
			return fail(e.getMessage());
		}
		return success(0);
		
	}
	/**社区活动表单提交数据
	 * 
	 * @param communityActivityDto
	 * @return 
	 */
	@RequestMapping({ "/addcommunityActivity" })
	@ResponseBody
	public Object completSubmit(@RequestBody CommunityActivityTwoDto communityActivityDto){
		try {
			communityActivityWriterManager.addCommunityActivity(communityActivityDto);
			
			
		} catch (Exception e) {
			return fail(e.getMessage());
		}
		
		return success(0);
	}
	
	/**
	 * 
	 * <p>
	 * 查询详情（）
	 * </p>
	 * 
	 * 
	 */
	@RequestMapping({ "/editcommunityActivityById" })
	@ResponseBody
	private Object editcommunityActivityById(@RequestBody CommunityActivity  communityActivity) {
		return success(communityActivityReadManage.queryCommunityActivityById(communityActivity.getId()));
	}
	/**
	 * 
	 * <p>
	 *  更新编辑内容
	 * </p>
	 * 
	 */
	@RequestMapping({ "/updatecommunityById" })
	@ResponseBody
	private Object updatecommunityById(@RequestBody CommunityActivityTwoDto communityActivityDto) {
		try {
			//不是定时发布就不要定时发布时间了
			if(communityActivityDto.getIsTimePublish()==0){
				communityActivityDto.setTimeInterval(null);
				communityActivityDto.setTimeIntervalStartTime(null);
				
			}
			communityActivityWriterManager.updateProduct(communityActivityDto);
		} catch (Exception e) {
			return fail(e.getMessage());
		}
		return success(0);
	}
	/**
	 * 发布或下架
	 * 
	 * @param communityVo
	 * @return
	 */

	@RequestMapping({ "/editCommunityClass" })
	@ResponseBody
	public Object editCommunityClass(@RequestBody CommunityActivityVo communityVo) {
		try {
			communityActivityWriterManager.updateCommunityClass(communityVo);
		} catch (BusinessException e) {
			return fail(e.getMessage());
		}
		return success(0);
	}
	
	/**查询活动订单列表
	 * 
	 * @param communityVo
	 * @return
	 */
	

	@RequestMapping({ "/queryActivityListOder" })
	@ResponseBody
	public Object queryActivityListOder(@RequestBody CommunityActivityOrderVo communityActivityOrderVo) {
		try {
			return success(communityActivityReadOrderManage.quryListOder(communityActivityOrderVo));
		} catch (Exception e) {
			return fail(e.getMessage());
		}
	}
	
	/**查询活动订单详情
	 * 
	 * @param communityVo
	 * @return
	 */
	

	@RequestMapping({ "/queryActivityListDetailsOder" })
	@ResponseBody
	public Object queryActivityListDetailsOder(@RequestBody CommunityActivityOrder communityActivityOrder) {
		try {
			return success(communityActivityReadOrderManage.quryListOderDetails(communityActivityOrder));
		} catch (Exception e) {
			return fail(e.getMessage());
		}
	}
	/**获取当前活动下的管理员
	 * 
	 * @param communityActivity
	 * @return
	 */
	@RequestMapping({ "/adminMumber" })
	@ResponseBody
	public Object queryAdminMumber(@RequestBody CommunityActivityAdminDto communityActivityAdminDto){
		try {
			List<CommunityActivityAdmin> list=new ArrayList<CommunityActivityAdmin>();
			list.clear();
			List<CommunityMiddleAdmin> queryAdminMiddle = communityActivityAdminReadManage.queryAdminMiddle(communityActivityAdminDto.getActivityId());
			for (CommunityMiddleAdmin communityMiddleAdmin : queryAdminMiddle) {
				CommunityActivityAdmin communityActivityAdmin=new CommunityActivityAdmin();
				communityActivityAdmin.setId(communityMiddleAdmin.getAdminId());
				CommunityActivityAdmin queryAdminnumber = communityActivityAdminReadManage.queryAdminnumber(communityActivityAdmin);
				if(queryAdminnumber!=null){
					
						list.add(queryAdminnumber);	
						
					
					
				}
			}
			return success(list);
		} catch (Exception e) {
			return fail(e.getMessage());
		}
	}

}