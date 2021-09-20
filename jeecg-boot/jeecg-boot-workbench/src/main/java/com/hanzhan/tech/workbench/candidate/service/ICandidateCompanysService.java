package com.hanzhan.tech.workbench.candidate.service;

import com.hanzhan.tech.workbench.candidate.entity.CandidateCompanys;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: 工作单位
 * @Author: jeecg-boot
 * @Date:   2021-09-20
 * @Version: V1.0
 */
public interface ICandidateCompanysService extends IService<CandidateCompanys> {

	public List<CandidateCompanys> selectByMainId(String mainId);
}
