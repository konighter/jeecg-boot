package com.hanzhan.tech.workbench.candidate.service;

import com.hanzhan.tech.workbench.candidate.entity.CandidateEdu;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: 教育经历
 * @Author: jeecg-boot
 * @Date:   2021-09-20
 * @Version: V1.0
 */
public interface ICandidateEduService extends IService<CandidateEdu> {

	public List<CandidateEdu> selectByMainId(String mainId);
}
