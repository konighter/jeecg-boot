package com.hanzhan.tech.workbench.candidate.service.impl;

import com.hanzhan.tech.workbench.candidate.entity.CandidateVisithistory;
import com.hanzhan.tech.workbench.candidate.mapper.CandidateVisithistoryMapper;
import com.hanzhan.tech.workbench.candidate.service.ICandidateVisithistoryService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 候选人拜访历史
 * @Author: jeecg-boot
 * @Date:   2021-09-20
 * @Version: V1.0
 */
@Service
public class CandidateVisithistoryServiceImpl extends ServiceImpl<CandidateVisithistoryMapper, CandidateVisithistory> implements ICandidateVisithistoryService {
	
	@Autowired
	private CandidateVisithistoryMapper candidateVisithistoryMapper;
	
	@Override
	public List<CandidateVisithistory> selectByMainId(String mainId) {
		return candidateVisithistoryMapper.selectByMainId(mainId);
	}
}
