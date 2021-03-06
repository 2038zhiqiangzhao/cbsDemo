package com.people.cbsadmin.business.read.impl;
import com.people2000.common.base.bean.BeanUtils;
import com.people2000.common.base.date.DateUtil;
import com.people2000.common.base.page.PageResult;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.people.cbsadmin.business.dao.CommunityActivityMapper;
import com.people.cbsadmin.business.dao.CommunityActivityOrderMapper;
import com.people.cbsadmin.business.dao.UserMapper;
import com.people.cbsadmin.business.dao.ext.CommunityActivityMapperExt;
import com.people.cbsadmin.business.dao.ext.CommunityActivityOrderMapperExt;
import com.people.cbsadmin.business.read.CommunityActivityReadManage;
import com.people.cbsadmin.business.write.CommunityActivityWriterManager;
import com.people.cbsadmin.model.dto.CommunityActivityDto;
import com.people.cbsadmin.model.dto.CommunityActivityTwoDto;
import com.people.cbsadmin.model.po.CommunityActivity;
import com.people.cbsadmin.model.po.CommunityActivityOrder;
import com.people.cbsadmin.model.po.User;
import com.people.cbsadmin.model.vo.CommunityActivityVo;
import com.people.cbsadmin.model.vo.CommunitySecret;
import com.people.cbsadmin.model.vo.UserVo;


@Service("communityActivityReadManage")
public class CommunityActivityReadManageImpl implements CommunityActivityReadManage {
	@Autowired
	private CommunityActivityMapper mCommunityActivityMapper;
	@Autowired
	private CommunityActivityWriterManager communityActivityWriterManager;
	@Autowired
	private CommunityActivityMapperExt mCommunityActivityMapperExt;
	@Autowired
	private CommunityActivityOrderMapper mCommunityActivityOrderMapper;
	@Autowired
	private CommunityActivityOrderMapperExt mCommunityActivityOrderMapperExt;
	@Autowired
	private UserMapper userMapper;
	@Override
	public PageResult<CommunityActivityDto> queryActivityList(CommunityActivityDto communityActivityDto) {
		try {
			PageResult<CommunityActivityDto> page = new PageResult<CommunityActivityDto>();
			 Integer totalSize = mCommunityActivityMapperExt.countOrderMain(communityActivityDto);
			if (totalSize > 0) {
				page.setTotal(totalSize);
				List<CommunityActivityDto> dataList = mCommunityActivityMapperExt.selectAll(communityActivityDto);
				page.setListObj(dataList);
			}				
			return page;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
   
   /**
    * 获取详情页所有数据
    * 
    */
	@Override
	public List<CommunityActivityVo> queryActivityListDetails(Long id) {
		List<CommunityActivityVo> communityActivityVo = new ArrayList<CommunityActivityVo>();
		List<CommunityActivity>  community = mCommunityActivityMapper.selectByPrimaryKey(id);
	    List<CommunityActivityOrder> selectUserList = mCommunityActivityOrderMapperExt.selectUserList(id);
	    ArrayList<UserVo> uservo=new ArrayList<UserVo>();
	  for (CommunityActivityOrder muserOder : selectUserList) {
		  UserVo userE=new UserVo();
		  Long createUserId = muserOder.getCreateUserId();
		   //通过用户id获取用户信息
		   User muser = userMapper.selectByPrimaryKey(createUserId);
		   userE.setIdentityCardName(muser.getIdentityCardName());//设置用户真实姓名
		   userE.setHeadPicUrl(muser.getHeadPicUrl());
		   uservo.add(userE);
		}
		for (CommunityActivity co : community) {
			CommunityActivityVo dto = new CommunityActivityVo();
			BeanUtils.copyProperties(co, dto);
			dto.setUserVo(uservo);
			communityActivityVo.add(dto);
		}

		return communityActivityVo;
		
	}

	@Override
	public List<CommunityActivityOrder> createActivityOrder(Long id) {
		return null;
	}

	@Override
	public PageResult<CommunityActivity> queryCommunityActivityPage(
			CommunityActivityDto querys) {
		PageResult<CommunityActivity> page = new PageResult<CommunityActivity>();
		int totalSize = mCommunityActivityMapperExt.countOrderMain(querys);
		if (totalSize > 0) {
			page.setTotal(totalSize);
			List<CommunityActivity> dataList = mCommunityActivityMapperExt
					.queryOrderMainPage(querys);
			page.setListObj(dataList);
		}
		return page;
	}

	@Override
	public Object deleteCommunityClass() {
		return null;
	}

	@Override
	public CommunityActivityTwoDto queryCommunityActivityById(Long id) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm");
		CommunityActivity mcommunityActivity = mCommunityActivityMapper.selectByPrimaryKeyOnly(id);
		CommunityActivityTwoDto record = new CommunityActivityTwoDto();
			String formatDateTime = DateUtil.formatDateTime(new Date());
			Date date = DateUtil.getDate(formatDateTime,"yyyy-MM-dd HH:mm:ss");
			record.setUpdateTime(date.toString());
			record.setId(id);
			Date activityTime = mcommunityActivity.getActivityTime();
			//将date转化成字符串并分散设置进去 2018-08-25 05:30
			String formatDateTimeTwo = simpleDateFormat.format(activityTime);
			String splitDate = SplitString(formatDateTimeTwo,true);
			String splitTime = SplitString(formatDateTimeTwo,false);
			record.setActivityTime(splitDate);
			record.setActivityTimeStartTime(splitTime);
			
			Date activityEndTime = mcommunityActivity.getActivityEndTime();
			String formatDateactivityEndTime = DateUtil.formatDateTimeTwo(activityEndTime);
			String splitTimeEndTime = SplitString(formatDateactivityEndTime,false);
			record.setActivityEndTime(splitTimeEndTime);
			

			Date applyEndTime = mcommunityActivity.getApplyEndTime();
			String formatapplyEndTime = simpleDateFormat.format(applyEndTime);
			String splitDateapplyEndTime = SplitString(formatapplyEndTime,true);
			String splitTimeapplyEndTime = SplitString(formatapplyEndTime,false);
			record.setApplyEndTime(splitDateapplyEndTime);
			record.setApplyEndTimeStartTime(splitTimeapplyEndTime);
			
			
			
			
			record.setActivityName(mcommunityActivity.getActivityName());
			record.setActivityPlace(mcommunityActivity.getActivityPlace());
			record.setIsTimePublish(mcommunityActivity.getIsTimePublish());
			if(mcommunityActivity.getCreateUserName()!=null&&!mcommunityActivity.getCreateUserName().equals("")){
				record.setCreateUserName(mcommunityActivity.getCreateUserName());
			}
			if(mcommunityActivity.getCreateUserPhone()!=null&&!mcommunityActivity.getCreateUserPhone().equals("")){
				record.setCreateUserPhone(mcommunityActivity.getCreateUserPhone());
			}
			
			record.setIsAvailable(mcommunityActivity.getIsAvailable());
			record.setRegistrationFee(mcommunityActivity.getRegistrationFee());
			record.setRichTextArea(mcommunityActivity.getRichTextArea());
			record.setSurfacePicture(mcommunityActivity.getSurfacePicture());
			if(mcommunityActivity.getTimeInterval()!=null&&!mcommunityActivity.getTimeInterval().equals("")){
				Date timeInterval = mcommunityActivity.getTimeInterval();
				String formattimeInterval = DateUtil.formatDateTimeTwo(timeInterval);
				String splitDateInterval = SplitString(formattimeInterval,true);
				String aplittimeInterval = SplitString(formattimeInterval,false);
				record.setTimeInterval(splitDateInterval);
				record.setTimeIntervalStartTime(aplittimeInterval);
			}
		return  record;
	}

	/**截取字符串年月日和时分
	 * 
	 * @param formatDateTime 2018-08-11 05:30
	 * @param getDay 传入参数true代表返回年月日 false代表返回时分
	 * @return
	 */
    public String SplitString(String formatDateTime,boolean getDay){
    	String[] split = formatDateTime.split(" ");
    	if(getDay){
    		return  split[0];
    	}else {
    		return split[1];
    	}
		
		
		
    }

	@Override
	public CommunityActivity queryActivity(Long id) {
		return mCommunityActivityMapper.selectByPrimaryKeyOnly(id);
	}

	@Override
	public List<CommunityActivityVo> queryActivityListAll() {
		return mCommunityActivityMapperExt.queryActivityListAll();
	}

	@Override
	public Object queryIsActivityCodeAdmin(CommunitySecret communitySecret) {
        return communityActivityWriterManager.updateAuthCode(communitySecret);
		}
	}

	
	

	

	


