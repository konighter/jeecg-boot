package com.hanzhan.tech.workbench.candidate.service;

import com.hanzhan.tech.workbench.candidate.entity.CandidateVisithistory;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: 候选人拜访历史
 * @Author: jeecg-boot
 * @Date:   2021-09-20
 * @Version: V1.0
 */
public interface ICandidateVisithistoryService extends IService<CandidateVisithistory> {

	public List<CandidateVisithistory> selectByMainId(String mainId);
}
