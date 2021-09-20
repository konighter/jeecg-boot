package com.hanzhan.tech.workbench.candidate.service.impl;

import com.hanzhan.tech.workbench.candidate.entity.CandidateEdu;
import com.hanzhan.tech.workbench.candidate.mapper.CandidateEduMapper;
import com.hanzhan.tech.workbench.candidate.service.ICandidateEduService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 教育经历
 * @Author: jeecg-boot
 * @Date:   2021-09-20
 * @Version: V1.0
 */
@Service
public class CandidateEduServiceImpl extends ServiceImpl<CandidateEduMapper, CandidateEdu> implements ICandidateEduService {
	
	@Autowired
	private CandidateEduMapper candidateEduMapper;
	
	@Override
	public List<CandidateEdu> selectByMainId(String mainId) {
		return candidateEduMapper.selectByMainId(mainId);
	}
}
