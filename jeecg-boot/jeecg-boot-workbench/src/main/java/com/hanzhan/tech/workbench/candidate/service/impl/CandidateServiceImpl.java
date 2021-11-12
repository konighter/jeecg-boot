package com.hanzhan.tech.workbench.candidate.service.impl;

import com.hanzhan.tech.workbench.candidate.entity.Candidate;
import com.hanzhan.tech.workbench.candidate.entity.CandidateCompanys;
import com.hanzhan.tech.workbench.candidate.entity.CandidateVisithistory;
import com.hanzhan.tech.workbench.candidate.entity.CandidateResume;
import com.hanzhan.tech.workbench.candidate.entity.CandidateEdu;
import com.hanzhan.tech.workbench.candidate.mapper.CandidateCompanysMapper;
import com.hanzhan.tech.workbench.candidate.mapper.CandidateVisithistoryMapper;
import com.hanzhan.tech.workbench.candidate.mapper.CandidateResumeMapper;
import com.hanzhan.tech.workbench.candidate.mapper.CandidateEduMapper;
import com.hanzhan.tech.workbench.candidate.mapper.CandidateMapper;
import com.hanzhan.tech.workbench.candidate.service.ICandidateService;
import com.hanzhan.tech.workbench.common.BizRunException;
import org.apache.shiro.SecurityUtils;
import org.jeecg.common.system.vo.LoginUser;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Collection;

/**
 * @Description: 候选人表
 * @Author: jeecg-boot
 * @Date:   2021-09-20
 * @Version: V1.0
 */
@Service
public class CandidateServiceImpl extends ServiceImpl<CandidateMapper, Candidate> implements ICandidateService {

	@Autowired
	private CandidateMapper candidateMapper;
	@Autowired
	private CandidateCompanysMapper candidateCompanysMapper;
	@Autowired
	private CandidateVisithistoryMapper candidateVisithistoryMapper;
	@Autowired
	private CandidateResumeMapper candidateResumeMapper;
	@Autowired
	private CandidateEduMapper candidateEduMapper;

	@Override
	@Transactional
	public void saveMain(Candidate candidate, List<CandidateCompanys> candidateCompanysList,List<CandidateVisithistory> candidateVisithistoryList,List<CandidateResume> candidateResumeList,List<CandidateEdu> candidateEduList) {
		if (candidate == null) {
			throw new BizRunException("非法数据");
		}

		initCandidateInfo(candidate);
		candidateMapper.insert(candidate);
		if(!CollectionUtils.isEmpty(candidateCompanysList)) {
			for(CandidateCompanys entity:candidateCompanysList) {
				//外键设置
				entity.setCandidateId(candidate.getId());
				candidateCompanysMapper.insert(entity);
			}
		}
		if(!CollectionUtils.isEmpty(candidateVisithistoryList)) {
			for(CandidateVisithistory entity:candidateVisithistoryList) {
				//外键设置
				entity.setCandidateId(candidate.getId());
				candidateVisithistoryMapper.insert(entity);
			}
		}
		if(!CollectionUtils.isEmpty(candidateResumeList)) {
			for(CandidateResume entity:candidateResumeList) {
				//外键设置
				entity.setCandidateId(candidate.getId());
				candidateResumeMapper.insert(entity);
			}
		}
		if(!CollectionUtils.isEmpty(candidateEduList)) {
			for(CandidateEdu entity:candidateEduList) {
				//外键设置
				entity.setCandidateId(candidate.getId());
				candidateEduMapper.insert(entity);
			}
		}
	}


	@Override
	public Candidate getById(Serializable id) {
		Candidate candidate = super.getById(id);
		return candidate;
	}

	@Override
	@Transactional
	public void updateMain(Candidate candidate,List<CandidateCompanys> candidateCompanysList,List<CandidateVisithistory> candidateVisithistoryList,List<CandidateResume> candidateResumeList,List<CandidateEdu> candidateEduList) {
		candidateMapper.updateById(candidate);

		//1.先删除子表数据
//		candidateCompanysMapper.deleteByMainId(candidate.getId());
//		candidateVisithistoryMapper.deleteByMainId(candidate.getId());
//		candidateResumeMapper.deleteByMainId(candidate.getId());
//		candidateEduMapper.deleteByMainId(candidate.getId());

		//2.子表数据重新插入
		if(candidateCompanysList!=null && candidateCompanysList.size()>0) {
			for(CandidateCompanys entity:candidateCompanysList) {
				//外键设置
				entity.setCandidateId(candidate.getId());
				candidateCompanysMapper.insert(entity);
			}
		}
		if(candidateVisithistoryList!=null && candidateVisithistoryList.size()>0) {
			for(CandidateVisithistory entity:candidateVisithistoryList) {
				//外键设置
				entity.setCandidateId(candidate.getId());
				candidateVisithistoryMapper.insert(entity);
			}
		}
		if(candidateResumeList!=null && candidateResumeList.size()>0) {
			for(CandidateResume entity:candidateResumeList) {
				//外键设置
				entity.setCandidateId(candidate.getId());
				candidateResumeMapper.insert(entity);
			}
		}
		if(candidateEduList!=null && candidateEduList.size()>0) {
			for(CandidateEdu entity:candidateEduList) {
				//外键设置
				entity.setCandidateId(candidate.getId());
				candidateEduMapper.insert(entity);
			}
		}
	}

	@Override
	@Transactional
	public void delMain(String id) {
		candidateCompanysMapper.deleteByMainId(id);
		candidateVisithistoryMapper.deleteByMainId(id);
		candidateResumeMapper.deleteByMainId(id);
		candidateEduMapper.deleteByMainId(id);
		candidateMapper.deleteById(id);
	}

	@Override
	@Transactional
	public void delBatchMain(Collection<? extends Serializable> idList) {
		for(Serializable id:idList) {
			candidateCompanysMapper.deleteByMainId(id.toString());
			candidateVisithistoryMapper.deleteByMainId(id.toString());
			candidateResumeMapper.deleteByMainId(id.toString());
			candidateEduMapper.deleteByMainId(id.toString());
			candidateMapper.deleteById(id);
		}
	}

	private void initCandidateInfo(Candidate candidate) {
		Date now = new Date();
		LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		if (candidate.getCreateTime() == null) {
			candidate.setCreateTime(now);
		}

		if (candidate.getUpdateTime() == null) {
			candidate.setUpdateTime(now);
		}
		if (sysUser != null) {
			if (StringUtils.isEmpty(candidate.getUpdateBy())) {
				candidate.setUpdateBy(sysUser.getId());
			}

			if (StringUtils.isEmpty(candidate.getCreateBy())) {
				candidate.setCreateBy(sysUser.getId());
			}
		}
	}

}
