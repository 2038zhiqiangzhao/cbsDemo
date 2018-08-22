package com.people.cbsadmin.api.action;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.people.cbsadmin.api.BaseController;
import com.people.cbsadmin.api.ResultCode;
import com.people.cbsadmin.business.dao.UserMapper;
import com.people.cbsadmin.business.read.CommunityActivityReadManage;
import com.people.cbsadmin.business.read.CommunityActivityReadOrderManage;
import com.people.cbsadmin.business.write.CommunityActivityOrderWriterManager;
import com.people.cbsadmin.business.write.CommunityActivityWriterManager;
import com.people.cbsadmin.model.constant.AllConstant;
import com.people.cbsadmin.model.po.CommunityActivity;
import com.people.cbsadmin.model.po.CommunityActivityOrder;
import com.people.cbsadmin.model.po.User;
import com.people.cbsadmin.model.vo.CommunityActivityOrderVo;
import com.people.cbsadmin.model.vo.CommunityActivityVo;
import com.people.cbsadmin.model.vo.CommunitySecret;
import com.people.cbsadmin.model.vo.OrderCodeVo;
import com.people.cbsadmin.model.vo.QRCodeVO;
import com.people.cbsadmin.model.vo.ReportSignVo;
import com.people2000.common.base.bean.BeanUtils;
import com.people2000.common.encrypt.MD5Support;
import com.people2000.user.client.utils.SessionContainer;
import com.people2000.user.model.dto.UserCache;

/**
 * @author zhiqiang_zhao
 * 2018年5月10日
 * 
 */
@Controller
@RequestMapping({ "/communityActivity" })
public class CommunityActivityController extends BaseController {
	@Resource
	private CommunityActivityReadManage communityActivityReadManage;
	@Resource
	private CommunityActivityWriterManager communityActivityWriterManager;
	@Resource
	private CommunityActivityReadOrderManage communityActivityReadOrderManage;
	@Resource
	private CommunityActivityOrderWriterManager communityActivityOrderWriterManager;
	@Resource
	private UserMapper userMapper;
	
	/**
	 * 查询活动列表
	 * 
	 * 
	 */
	@RequestMapping({ "/queryActivityList" })
	@ResponseBody
	private Object queryActivityList() {
		return success(communityActivityReadManage.queryActivityListAll());
	}

	/**
	 * 获取活动详情页
	 * 
	 * @return
	 */
	@RequestMapping({ "/queryActivityListDetails" })
	@ResponseBody
	private Object queryActivityListDetails(
			@RequestBody CommunityActivity mCommunityActivity) {
		if (mCommunityActivity.getId() == null) {
			return fail("活动编号不能为空");
		}
		return success(communityActivityReadManage
				.queryActivityListDetails(mCommunityActivity.getId()));
	}

	/**
	 * 根据活动编号创建活动订单
	 * 
	 * @param mCommunityActivity
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping({ "/createActivityOrder" })
	@ResponseBody
	private Object createActivityOrder(
			@RequestBody CommunityActivityOrderVo mCommunityActivityOrderVo) {
		if (mCommunityActivityOrderVo.getOrderSource() == null) {// 默认订单来源
			mCommunityActivityOrderVo
					.setOrderSource(AllConstant.OrderSource.APP);
		}else{
			mCommunityActivityOrderVo
			.setOrderSource(mCommunityActivityOrderVo.getOrderSource());
		}
		// 获取会员相关信息
		UserCache userCache = SessionContainer.getSession();
		if (null == userCache) {
			return fail(ResultCode.ERR_NOT_LOGIN);
		}
		//获取用户id
		Long userId = userCache.getId();
		// 校验参数
		Map<String, Object> map = (Map) checkCommunityActivityOrderVo(mCommunityActivityOrderVo);
		String code = (String) map.get("code");
		if (Integer.parseInt(code) != 0){//0代表校验成功，否则校验失败
			return map;
		}	
		//对应规则：一个用户在同一个活动id中只能预订一次，所以先判断当店用户id和活动id下是否有数据，如果有就不能再预订了
		mCommunityActivityOrderVo.setCreateUserId(userId);
		int quryUserIdAndActivityId = communityActivityReadOrderManage.quryUserIdAndActivityId(mCommunityActivityOrderVo);
		if(quryUserIdAndActivityId==0){
			CommunityActivityOrder communityActivityOrder = communityActivityReadOrderManage.
					initCreateActivityOrder(mCommunityActivityOrderVo);
			communityActivityOrder.setCreateUserId(userId);
			communityActivityOrderWriterManager.createActivityOrderWithTx(communityActivityOrder);
			return success(new OrderCodeVo(communityActivityOrder.getOrderCode()));
		}else{
			
			return fail("不能重复预订");
			
			
		}
			
		

	}

	/**
	 * 校验参数值
	 * 
	 * @param vo
	 * @return
	 */
	private Map checkCommunityActivityOrderVo(CommunityActivityOrderVo mCommunityActivityOrderVo) {
		// 活动ID不能为空
		if (mCommunityActivityOrderVo.getCommunityActivityId() == null){
			return fail(ResultCode.ERR_ACTIVTY_ID);
		}
		//判断活动名称不能为空
		if(mCommunityActivityOrderVo.getActivityName()==null){
			return fail(ResultCode.ERR_ACTIVTY_NAME);
		}
		//判断活动开始时间不能为空
		if(mCommunityActivityOrderVo.getActivityTime()==null){
			return fail(ResultCode.ERR_ACTIVTY_TIME);
		}
		
		//判断预订人姓名不能为空
		if(mCommunityActivityOrderVo.getReserveName()==null){
			return fail(ResultCode.ERR_ACTIVTY_RESERVE_NAME);
		}
		//判断预订人手机号码不能为空
		if(mCommunityActivityOrderVo.getReservePhone()==null){
			return fail(ResultCode.ERR_ACTIVTY_RESERVE_PHONE);
		}
		//判断支付方式不能为空
		if(mCommunityActivityOrderVo.getPayWay()==null){
			return fail(ResultCode.ERR_PAY_WAY);
		}
		//判断封面图不能为空
		if(mCommunityActivityOrderVo.getSurfacePicture()==null){
		  return fail(ResultCode.ERR_PAY_WAY);
		}
		
		return success(ResultCode.SUCCESS);
	}
	/**
	 * 查询活动订单
	 * 
	 * @param mCommunityActivity
	 * @return
	 */
	@RequestMapping({ "/queryActivityOrder" })
	@ResponseBody
	private Object queryActivityOrder(
			@RequestBody CommunityActivityOrderVo mCommunityActivityOrderVo) {
		// 获取会员相关信息
		UserCache userCache = SessionContainer.getSession();
		if (null == userCache) {
			return fail(ResultCode.ERR_NOT_LOGIN);
		}
		Long id = userCache.getId();
		mCommunityActivityOrderVo.setCreateUserId(id);
		return success(communityActivityReadOrderManage
				.queryActivityOrderInfo(mCommunityActivityOrderVo.getCreateUserId()));
	}
	/**
	 * 根据订单编号获取订单详情
	 * 
	 * @param mCommunityActivity
	 * @return
	 */
	@RequestMapping({ "/findCommnityOrderDetail" })
	@ResponseBody
	private Object findCommnityOrderDetail(
			@RequestBody CommunityActivityOrderVo mCommunityActivityOrderVo) {
		if (mCommunityActivityOrderVo.getOrderCode() == null) {
			return fail("订单编号不能为空");
		}
		// 获取会员相关信息
		UserCache userCache = SessionContainer.getSession();
		if (null == userCache) {
			return fail(ResultCode.ERR_NOT_LOGIN);
		}
		return success(communityActivityReadOrderManage
				.queryCommnityOrderDetail(mCommunityActivityOrderVo.getOrderCode()));
	}
	/**
	 * 生成活动二维码
	 * 
	 */
	@SuppressWarnings("unused")
	@RequestMapping({ "/creatActivityQRCode" })
	@ResponseBody
	public Object CreatActivityQRCode(@RequestBody CommunityActivityOrderVo mCommunityActivityOrderVo){
		if (mCommunityActivityOrderVo.getCommunityActivityId() == null) {
			return fail("活动编号不能为空");
		}
		// 获取会员相关信息
		UserCache userCache = SessionContainer.getSession();
		if (null == userCache) {
			return fail(ResultCode.ERR_NOT_LOGIN);
		}
		Long userId= userCache.getId();
		mCommunityActivityOrderVo.setCreateUserId(userId);
		CommunityActivityOrder creatQRCode = communityActivityOrderWriterManager.creatQRCode(mCommunityActivityOrderVo);
		//如果是免费活动可以返回二维码
		if(creatQRCode.getTotalAmount().compareTo(BigDecimal.ZERO)!=0){
			  //判断订单支付状态，未支付不返回二维码
			if(creatQRCode.getPayState().equals(AllConstant.OrderPayState.ORDER_PAY_STATE_NO)){
				return fail(ResultCode.EERO_ER);
			}
		}
	   
		if(creatQRCode!=null){
			//获取订单编号
			String orderCode = creatQRCode.getOrderCode();
			QRCodeVO rocode=new QRCodeVO();
			creatQRCode.setQrUrl(ResultCode.QR_URL+"{"+"orderCode"+":"+orderCode+",type:"+"3"+"}");
			rocode.setQrUrl(ResultCode.QR_URL+"{"+"orderCode"+":"+orderCode+",type:"+"3"+"}");
			CommunityActivityOrder cao = new CommunityActivityOrder();
			BeanUtils.copyProperties(creatQRCode, cao);
			communityActivityOrderWriterManager.updateById(cao);
			return success(rocode);
		}else{
			return fail(ResultCode.EERO_REEO);
		}
		
	}
	
	/**
	 * 扫描管理员授权码
	 * 
	 * @return
	 */
	@RequestMapping({ "/queryIsActivityCode" })
	@ResponseBody
	private Object queryIsActivityOderCode(@RequestBody CommunityActivityVo communityActivityVo) {
		try{
			if (communityActivityVo.getId() == null) {
				return fail(ResultCode.ERR_NOT_COMMUNITYID);
			}
			if (communityActivityVo.getSecret() == null) {
				return fail(ResultCode.ERR_NOT_COMMUNSERY);
			}
	      // 获取会员相关信息
			UserCache userCache = SessionContainer.getSession();
			if (null == userCache) {
				return fail(ResultCode.ERR_NOT_LOGIN);
			}
			communityActivityVo.setUserId(userCache.getId());
			//如果秘钥=0说明不是管理员
			if(communityActivityVo.getSecret().equals("0")){
				return success(communityActivityWriterManager.updateCommunitySecret(communityActivityVo,false));
			}else{//和数据库中的数据做匹配防止秘钥篡改
				return success(communityActivityWriterManager.updateCommunitySecret(communityActivityVo,true));
			}
		}catch(Exception e){
			e.printStackTrace();
			return fail(e);
		}
		
	}
	/**
	 * 为组织者授权
	 * 
	 * @return
	 */
	@RequestMapping({ "/queryIsActivityCodeAdmin" })
	@ResponseBody
	private Object queryIsActivityCodeAdmin(@RequestBody CommunitySecret communitySecret) {
		try{
			if (communitySecret.getSecret() == null) {
				return fail(ResultCode.ERR_NOT_COMMUNSERY);
			}
			if (communitySecret.getCommunityId() == null) {
				return fail(ResultCode.ERR_NOT_COMMUNITYID);
			}
			// 获取会员相关信息
			UserCache userCache = SessionContainer.getSession();
			if (null == userCache) {
				return fail(ResultCode.ERR_NOT_LOGIN);
			}
			communitySecret.setUserId(userCache.getId());
			return success(communityActivityReadManage.queryIsActivityCodeAdmin(communitySecret));
			
		}catch(Exception e){
			e.printStackTrace();
			return fail(e);
		}
		
	}
	/**
	 * 判断当前活动是否是管理员活动
	 * 
	 * @return
	 */
	@RequestMapping({ "/judgeActivityCodeAdmin" })
	@ResponseBody
	private Object judgeActivityCodeAdmin(@RequestBody CommunityActivityVo communityActivityVo) {
		try{
			if (communityActivityVo.getId() == null) {
				return fail(ResultCode.ERR_NOT_COMMUNITYID);
			}
			// 获取会员相关信息
				UserCache userCache = SessionContainer.getSession();
				if (null == userCache) {
					return fail(ResultCode.ERR_NOT_LOGIN);
				}
			String md5authCode = AllConstant.QRURL+"{id="+communityActivityVo.getId()+",secret="+  
					MD5Support.MD5(communityActivityVo.getId()+""+userCache.getId())+",type=4"+"}";
			
			
			CommunityActivity queryActivity = communityActivityReadManage.queryActivity(communityActivityVo.getId());
			if(queryActivity!=null){
				String authCode = queryActivity.getAuthCode();
				if(md5authCode.equals(authCode)){
					return success(0);
				}
				return success(-1);
			}
			return success(-1);
			
		}catch(Exception e){
			e.printStackTrace();
			return fail(e);
		}
		
		
	}
	
	
	/**
	 * 扫描活动码判断是否为该活动管理员
	 * @param oderCode
	 */
	@RequestMapping({ "/scanCommunityCode" })
	@ResponseBody
	public Object scanCommunityCode(@RequestBody CommunityActivityOrder communityActivityOrder){
		try{
			if(communityActivityOrder.getOrderCode()==null){
				return fail(ResultCode.ERR_NOT_ODERCODE);
			}
			//判断次活动码是否为管理员活动码
			//活动id去查询活动表中是否有值有值就是该活动管理员
			// 获取会员相关信息
			UserCache userCache = SessionContainer.getSession();
			if (null == userCache) {
				return fail(ResultCode.ERR_NOT_LOGIN);
			}
			
			Object queryCommnityOrderAdmin = communityActivityReadOrderManage.queryCommnityOrderAdmin(communityActivityOrder.getOrderCode());
			if(!queryCommnityOrderAdmin.equals(-1)){
				return success(1);
			}
			return success(0);
		}catch(Exception e){
			e.printStackTrace();
			return fail(e);
		}
		
	}
	
	
	/**
	 * 判断活动码是否已经签到
	 * @param oderCode
	 */
	@RequestMapping({ "/isSignCommunityCode" })
	@ResponseBody
	public Object isSignCommunityCode(@RequestBody CommunityActivityOrderVo communityActivityOrderVo){
		try{
			if(communityActivityOrderVo.getOrderCode()==null){
				return fail(ResultCode.ERR_NOT_ODERCODE);
			}
			// 获取会员相关信息
			UserCache userCache = SessionContainer.getSession();
			if (null == userCache) {
				return fail(ResultCode.ERR_NOT_LOGIN);
			}
		   Object signCommunityCode = communityActivityReadOrderManage.isSignCommunityCode(communityActivityOrderVo.getOrderCode());
		
		return success(signCommunityCode);
			
		}catch(Exception e){
			e.printStackTrace();
			return fail(e);
		}
		
	}
	
	/**
	 * 获取签到列表
	 * @param oderCode
	 */
	@RequestMapping({ "/querySignList" })
	@ResponseBody
	public Object isSignList(@RequestBody CommunityActivityOrder communityActivityOrder){
		try{
			if(communityActivityOrder.getCommunityActivityId()==null){
				return fail(ResultCode.ERR_NOT_COMMUNITYID);
			}
			// 获取会员相关信息
			UserCache userCache = SessionContainer.getSession();
			if (null == userCache) {
				return fail(ResultCode.ERR_NOT_LOGIN);
			}
			List<CommunityActivityOrder> quryListSign = communityActivityReadOrderManage.quryListSign(communityActivityOrder);
		     List<ReportSignVo> list=new ArrayList<ReportSignVo>();
			if(quryListSign!=null){
				
				for (CommunityActivityOrder communityActivityOrderVo2 : quryListSign) {
					User selectByPrimaryKey = userMapper.selectByPrimaryKey(communityActivityOrderVo2.getCreateUserId());
					ReportSignVo reportSignVo=new ReportSignVo();
					reportSignVo.setReportTime(communityActivityOrderVo2.getCreateTime());
					reportSignVo.setName(communityActivityOrderVo2.getReserveName());
					reportSignVo.setSignTime(communityActivityOrderVo2.getSignTime());
					if(selectByPrimaryKey.getHeadPicUrl()!=null){
						reportSignVo.setHeadImageUrl(selectByPrimaryKey.getHeadPicUrl());
					}
					list.add(reportSignVo);
				}
			  
			  return success(list);
		  }
		  return success(-1);
			
		}catch(Exception e){
			e.printStackTrace();
			return fail(e);
		}
		
	}
	
	/**
	 * 获取报名列表
	 * @param oderCode
	 */
	@RequestMapping({ "/queryReportList" })
	@ResponseBody
	public Object isReportList(@RequestBody CommunityActivityOrder communityActivityOrder){
		try{
			if(communityActivityOrder.getCommunityActivityId()==null){
				return fail(ResultCode.ERR_NOT_COMMUNITYID);
			}
			// 获取会员相关信息
			UserCache userCache = SessionContainer.getSession();
			if (null == userCache) {
				return fail(ResultCode.ERR_NOT_LOGIN);
			}
			List<CommunityActivityOrder> quryListSign = communityActivityReadOrderManage.quryListReport(communityActivityOrder);
			
			List<ReportSignVo> list=new ArrayList<ReportSignVo>();
			if(quryListSign!=null){
				
				for (CommunityActivityOrder communityActivityOrderVo2 : quryListSign) {
					User selectByPrimaryKey = userMapper.selectByPrimaryKey(communityActivityOrderVo2.getCreateUserId());
					ReportSignVo reportSignVo=new ReportSignVo();
					reportSignVo.setReportTime(communityActivityOrderVo2.getCreateTime());
					reportSignVo.setName(communityActivityOrderVo2.getReserveName());
					reportSignVo.setSignTime(communityActivityOrderVo2.getSignTime());
					if(selectByPrimaryKey.getHeadPicUrl()!=null){
						reportSignVo.setHeadImageUrl(selectByPrimaryKey.getHeadPicUrl());
					}
					
					list.add(reportSignVo);
				}
			  
			  return success(list);
		  }
		  return success(-1);
			
		}catch(Exception e){
			e.printStackTrace();
			return fail(e);
		}
		
	}
}
