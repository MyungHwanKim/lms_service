package com.example.lms.banner.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.example.lms.banner.domain.Banner;
import com.example.lms.banner.dto.BannerDto;
import com.example.lms.banner.mapper.BannerMapper;
import com.example.lms.banner.model.BannerInput;
import com.example.lms.banner.model.BannerParam;
import com.example.lms.banner.repository.BannerRepository;
import com.example.lms.banner.service.BannerService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BannerServiceImpl implements BannerService {

	private final BannerRepository bannerRepository;
	private final BannerMapper bannerMapper;
	
	@Override
	public boolean add(BannerInput bannerInput) {
			
		Banner banner = Banner.builder()
								.bannerName(bannerInput.getBannerName())
								.url(bannerInput.getUrl())
								.alterText(bannerInput.getAlterText())
								.open(bannerInput.getOpen())
								.fileDirectory(bannerInput.getFileDirectory())
								.sortValue(bannerInput.getSortValue())
								.openYn(true)
								.createAt(LocalDateTime.now())
								.build();
		
		bannerRepository.save(banner);
		
		return true;
	}
	
	@Override
	public boolean set(BannerInput bannerInput) {
		
		Optional<Banner> optionalBanner = bannerRepository.findById(bannerInput.getId());
		if (!optionalBanner.isPresent()) {
			return false;
		}
		
		Banner banner = optionalBanner.get();
		
		banner.setBannerName(bannerInput.getBannerName());
		banner.setUrl(bannerInput.getUrl());
		banner.setAlterText(bannerInput.getAlterText());
		banner.setOpen(bannerInput.getOpen());
		banner.setFileDirectory(bannerInput.getFileDirectory());
		banner.setSortValue(bannerInput.getSortValue());
		banner.setOpenYn(bannerInput.isOpenYn());
		banner.setCreateAt(banner.getCreateAt());
		banner.setUpdateAt(LocalDateTime.now());
		bannerRepository.save(banner);
		
		return true;
		}

	@Override
	public List<BannerDto> list(BannerParam bannerParam) {

		long totalCount = bannerMapper.selectListCount(bannerParam);
		List<BannerDto> list = bannerMapper.selectList(bannerParam);
		if (!CollectionUtils.isEmpty(list)) {
			int i = 0;
			for(BannerDto x: list) {
				x.setTotalCount(totalCount);
				x.setSeq(totalCount - bannerParam.getPageStart() - i);
				i++;
			}
		}
		return list;
	}

	@Override
	public BannerDto getById(long id) {
		
		return bannerRepository.findById(id).map(BannerDto::of).orElse(null);
	}

	@Override
	public boolean del(String idList) {
		
		if (idList != null && idList.length() > 0) {
			
			String[] ids = idList.split(",");
			for(String x: ids) {
				long id = 0L;
				try {
					id = Long.parseLong(x);
				} catch (Exception e) {

				}
				
				if (id > 0) {
					bannerRepository.deleteById(id);
				}
			}
		}
		return true;
	}

	@Override
	public List<BannerDto> listAll() {
		
		List<Banner> bannerList = bannerRepository.findAll();
		
		return BannerDto.of(bannerList);
	}
}
