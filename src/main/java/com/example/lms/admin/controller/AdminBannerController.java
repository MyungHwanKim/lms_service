package com.example.lms.admin.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import com.example.lms.banner.dto.BannerDto;
import com.example.lms.banner.model.BannerInput;
import com.example.lms.banner.model.BannerParam;
import com.example.lms.banner.service.BannerService;
import com.example.lms.course.controller.BaseController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
public class AdminBannerController extends BaseController {

	private final BannerService bannerService;
	
	@GetMapping("/admin/banner/list.do")
	public String list(Model model, BannerParam bannerParam) {
		
		bannerParam.init();
		List<BannerDto> bannerList = bannerService.list(bannerParam);
		
		long totalCount = 0;
		if (!CollectionUtils.isEmpty(bannerList)) {
			totalCount = bannerList.get(0).getTotalCount();
		}
		
		String queryString = bannerParam.getQueryString();
		String pageHtml  = getPaperHtml(totalCount, 
										bannerParam.getPageSize(), 
										bannerParam.getPageIndex(), 
										queryString);
		
		model.addAttribute("list", bannerList);
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("pager", pageHtml);
		
		return "admin/banner/list";
	}
	
	@GetMapping(value = {"/admin/banner/add.do", "/admin/banner/edit.do"})
	public String add(Model model, 
						HttpServletRequest request, 
						BannerInput bannerInput) 
	{
		
		boolean editMode = request.getRequestURI().contains("/edit.do");
		BannerDto detail = new BannerDto();
		
		if (editMode) {
			long id = bannerInput.getId();
			
			BannerDto existBanner = bannerService.getById(id);
			
			if (existBanner == null) {
				model.addAttribute("message", "배너 정보가 존재하지 않습니다.");
				return "common/error";
			}
			detail = existBanner;
		}
		
		model.addAttribute("editMode", editMode);
		model.addAttribute("detail", detail);
		return "admin/banner/add";
	}
	
	
	private String[] getNewSaveFile(String baseLocalPath, 
									String baseUrlPath, 
									String originalFilename) 
	{
		
		LocalDate now = LocalDate.now();
		
		String[] dirs = {	
			String.format("%s/%d/", baseLocalPath, now.getYear()),
			String.format("%s/%d/%02d/", baseLocalPath, 
							now.getYear(), now.getMonthValue()),
			String.format("%s/%d/%02d/%02d/", baseLocalPath, 
							now.getYear(), now.getMonthValue(), now.getDayOfMonth())};
		
		String urlDir = String.format("%s/%d/%02d/%02d/", baseUrlPath, 
							now.getYear(), now.getMonthValue(), now.getDayOfMonth());
		
		for(String dir : dirs) {
			File file = new File(dir);
			if (!file.isDirectory()) {
				file.mkdir();
			}
		}
		
		String fileExtension = "";
		if (originalFilename != null) {
			int dotPos = originalFilename.lastIndexOf(".");
			if (dotPos > -1) {
				fileExtension = originalFilename.substring(dotPos + 1);
			}
		}
		
		String uuid = UUID.randomUUID().toString().replace("-", "");
		String newFilename = String.format("%s%s", dirs[2], uuid);
		String newUrlFilename = String.format("%s%s", urlDir, uuid);
		if (fileExtension.length() > 0) {
			newFilename += "." + fileExtension;
			newUrlFilename += "." + fileExtension;
		}
		
		String[] returnFilename = {newFilename, newUrlFilename};
		
		return returnFilename;
	}
	
	@PostMapping(value = {"/admin/banner/add.do", "/admin/banner/edit.do"})
	public String addSubmit(Model model, 
							HttpServletRequest request, 
							MultipartFile file,
							BannerInput bannerInput) 
	{
		
		String saveFilename = "";
		String urlFilename = "";
		
		if (file != null) {
			String originalFilename = file.getOriginalFilename();		
			String baseLocalPath = "C:/dev/LMS_Project/lms/files";
			String baseUrlPath = "/files";
			
			String[] arrFilename = getNewSaveFile(baseLocalPath, baseUrlPath, originalFilename);
			
			saveFilename = arrFilename[0];
			urlFilename = arrFilename[1];
			
			try {
				File newFile = new File(saveFilename);
				FileCopyUtils.copy(file.getInputStream(), new FileOutputStream(newFile));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		bannerInput.setFileDirectory(urlFilename);

		
		boolean editMode = request.getRequestURI().contains("/edit.do");
		BannerDto detail = new BannerDto();
		
		if (editMode) {
			long id = bannerInput.getId();
			
			BannerDto existBanner = bannerService.getById(id);
			
			if (existBanner == null) {
				model.addAttribute("message", "배너 정보가 존재하지 않습니다.");
				return "common/error";
			}
			boolean result = bannerService.set(bannerInput);
		} else {
			boolean result = bannerService.add(bannerInput);
		}
		
		return "redirect:/admin/banner/list.do";
	}

	@PostMapping("/admin/banner/delete.do")
	public String del(Model model, 
							HttpServletRequest request, 
							BannerInput bannerInput) 
	{
		
		boolean result = bannerService.del(bannerInput.getIdList());
		
		return "redirect:/admin/banner/list.do";
	}
}
