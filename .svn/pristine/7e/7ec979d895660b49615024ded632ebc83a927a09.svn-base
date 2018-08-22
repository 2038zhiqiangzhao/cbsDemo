package com.people2000.platform.web.action;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.baidu.ueditor.ConfigManager;
import com.baidu.ueditor.define.BaseState;
import com.baidu.ueditor.define.State;
import com.people2000.common.file.ImageUtils;
import com.people2000.common.file.OSSImageUploadUtil;
import com.people2000.common.log.LogUtils;

@Controller
@RequestMapping("plugins/ueditor/jsp")
public class UEditorAction {

	private ConfigManager configManager = null;

	@Resource
	private OSSImageUploadUtil fileUploader;

	@SuppressWarnings("deprecation")
	@ResponseBody
	@RequestMapping("/upload")
	public String upload(HttpServletRequest request) {
		String action = request.getParameter("action");
		configManager = ConfigManager.getInstance(request.getRealPath("/"),
				request.getContextPath(), request.getRequestURI());
		if ("config".equals(action)) {
			return configManager.getAllConfig().toString();
		} else if ("uploadimage".equals(action)) {
			return doUpload(request, configManager.getConfig(1)).toJSONString();
		} else {
			return new BaseState(false, 101).toJSONString();
		}
	}

	private State doUpload(HttpServletRequest request, Map<String, Object> conf) {
		try {
			MultipartResolver resolver = new CommonsMultipartResolver(request
					.getSession().getServletContext());
			MultipartHttpServletRequest murequest = resolver
					.resolveMultipart(request);
			Map<String, MultipartFile> files = murequest.getFileMap();// 得到文件map对象

			for (MultipartFile file : files.values()) {
				String originalFilename = file.getOriginalFilename(); // 原来的文件名

				// 文件保存路径
				String filePath = request.getSession().getServletContext()
						.getRealPath("/")
						+ UUID.randomUUID().toString() + originalFilename;
				// 转存文件
				File uploadFile = new File(filePath);
				if (uploadFile.exists()) {
					uploadFile.delete();
				}

				file.transferTo(uploadFile);

				// 大于1M，对图片进行压缩
				if (uploadFile.length() / 1024l > 1024l) {
					ImageUtils.optimize(uploadFile, uploadFile, 0.1f);
				}

				String picUrl = fileUploader.upload(uploadFile);

				State state = new BaseState(true, 0);
				state.putInfo("url", picUrl);

				// 删除临时文件
				uploadFile.delete();
				return state;

			}

		} catch (Exception e) {
			LogUtils.getLogger(this.getClass()).error(
					e.getClass().getName() + " : " + e.getMessage(), e);
			return new BaseState(false, 999);
		}
		return null;
	}

	private static boolean validType(String type, String[] allowTypes) {
		@SuppressWarnings("rawtypes")
		List list = Arrays.asList(allowTypes);
		return list.contains(type);
	}

}
