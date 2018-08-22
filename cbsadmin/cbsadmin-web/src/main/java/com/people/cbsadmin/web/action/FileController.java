package com.people.cbsadmin.web.action;

import java.io.File;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import jcifs.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.people.cbsadmin.web.BaseAction;
import com.people2000.common.file.FileUploader;
import com.people2000.common.file.ImageUtils;
import com.people2000.common.file.OSSImageUploadUtil;

/**
 * 
 * 图片上传
 * <p style="display:none">
 * modifyRecord
 * </p>
 * <p style="display:none">
 * version:V1.0,author:dusai,date:2017年4月13日 下午6:32:54,content:
 * </p>
 * 
 * @author dusai
 * @date 2017年4月13日 下午6:32:54
 * @since
 * @version
 */
@Controller
@RequestMapping({ "/file" })
public class FileController extends BaseAction {

//	@Resource
//	private OSSImageUploadUtil fileUploader;
	@Resource
	private FileUploader fileUploader;

	@Autowired
	private HttpServletRequest request;
//
//	/**
//	 * @throws Exception
//	 * @描述: 阿里云OSS图片上传
//	 * @作者: dusai
//	 * @时间: 2018年1月10日 下午1:56:09
//	 */
//	@RequestMapping(value = "/savePic", method = RequestMethod.POST)
//	@ResponseBody
//	public Object savePic(@RequestParam("file") MultipartFile file) {
//		if (file.getSize() > 10 * 1024 * 1024) {
//			return fail("上传图片大小不能超过10M！");
//		}
//		File uploadFile = null;
//		try {
//			// 文件保存路径
//			String filePath = request.getSession().getServletContext()
//					.getRealPath("/")
//					+ UUID.randomUUID().toString() + file.getOriginalFilename();
//			// 转存文件
//			uploadFile = new File(filePath);
//			if (uploadFile.exists()) {
//				uploadFile.delete();
//			}
//			file.transferTo(uploadFile);
//
//			// 大于1M，对图片进行压缩
//			if (uploadFile.length() / 1024l > 1024l) {
//				ImageUtils.optimize(uploadFile, uploadFile, 0.1f);
//			}
//
//			String picUrl = fileUploader.upload(uploadFile);
//
//			uploadFile.delete();
//			return success(picUrl);
//		} catch (Exception e) {
//			uploadFile.delete();
//			return fail("图片上传失败");
//		}
//	}

	 @RequestMapping(value = "/savePic", method = RequestMethod.POST)
	 @ResponseBody
	 public Object savePic(@RequestParam("file") MultipartFile file) {
	 File tempFile = new File("tempfile");
	 File uploadFile = new File(UUID.randomUUID().toString()
	 + file.getOriginalFilename());
	 try {
	 // 保存
	 file.transferTo(tempFile);
	
	 // 大于1M，对图片进行压缩
	 if (tempFile.length() / 1024l > 1024l) {
	 ImageUtils.optimize(tempFile, uploadFile, 0.1f);
	 } else {// 否则上传原图
	 uploadFile = tempFile;
	 }
	 String url = fileUploader.upload(uploadFile);
	
	 uploadFile.delete();
	
	 return success(url);
	 } catch (Exception e) {
//	 LogUtils.getLogger(FileController.class).error("保存图片失败");
	 return fail("保存图片失败");
	 }
	 }

	/**
	 * 
	 * 对字节数组字符串进行Base64解码并生成图片
	 * 
	 * @Title: GenerateImage
	 * @date 2015年12月6日 下午10:05:00
	 * @modifier
	 * @modifydate
	 * @param imgStr
	 * @return
	 */
	@SuppressWarnings({ "static-access", "unused" })
	private byte[] GenerateImage(String imgStr) {
		if (imgStr == null) {// 图像数据为空
			return null;
		}
		int indexOf = imgStr.indexOf(";base64,");
		imgStr = imgStr.substring(indexOf + ";base64,".length());
		Base64 base64 = new Base64();
		return base64.decode(imgStr);

	}

}
