package com.hanzhan.tech.workbench.candidate.service;

import com.hanzhan.tech.workbench.candidate.entity.CandidateResume;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: 候选人简历
 * @Author: jeecg-boot
 * @Date:   2021-09-20
 * @Version: V1.0
 */
public interface ICandidateResumeService extends IService<CandidateResume> {

	public List<CandidateResume> selectByMainId(String mainId);
}
