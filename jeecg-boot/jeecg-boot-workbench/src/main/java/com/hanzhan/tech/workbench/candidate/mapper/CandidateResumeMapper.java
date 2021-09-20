package com.hanzhan.tech.workbench.candidate.mapper;

import java.util.List;
import com.hanzhan.tech.workbench.candidate.entity.CandidateResume;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Description: 候选人简历
 * @Author: jeecg-boot
 * @Date:   2021-09-20
 * @Version: V1.0
 */
public interface CandidateResumeMapper extends BaseMapper<CandidateResume> {

	public boolean deleteByMainId(@Param("mainId") String mainId);
    
	public List<CandidateResume> selectByMainId(@Param("mainId") String mainId);
}
