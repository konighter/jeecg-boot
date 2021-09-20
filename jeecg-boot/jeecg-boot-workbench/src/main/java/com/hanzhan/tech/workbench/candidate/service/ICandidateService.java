package com.hanzhan.tech.workbench.candidate.service;

import com.hanzhan.tech.workbench.candidate.entity.CandidateCompanys;
import com.hanzhan.tech.workbench.candidate.entity.CandidateVisithistory;
import com.hanzhan.tech.workbench.candidate.entity.CandidateResume;
import com.hanzhan.tech.workbench.candidate.entity.CandidateEdu;
import com.hanzhan.tech.workbench.candidate.entity.Candidate;
import com.baomidou.mybatisplus.extension.service.IService;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @Description: 候选人表
 * @Author: jeecg-boot
 * @Date:   2021-09-20
 * @Version: V1.0
 */
public interface ICandidateService extends IService<Candidate> {

	/**
	 * 添加一对多
	 * 
	 */
	public void saveMain(Candidate candidate,List<CandidateCompanys> candidateCompanysList,List<CandidateVisithistory> candidateVisithistoryList,List<CandidateResume> candidateResumeList,List<CandidateEdu> candidateEduList) ;
	
	/**
	 * 修改一对多
	 * 
	 */
	public void updateMain(Candidate candidate,List<CandidateCompanys> candidateCompanysList,List<CandidateVisithistory> candidateVisithistoryList,List<CandidateResume> candidateResumeList,List<CandidateEdu> candidateEduList);
	
	/**
	 * 删除一对多
	 */
	public void delMain (String id);
	
	/**
	 * 批量删除一对多
	 */
	public void delBatchMain (Collection<? extends Serializable> idList);
	
}
