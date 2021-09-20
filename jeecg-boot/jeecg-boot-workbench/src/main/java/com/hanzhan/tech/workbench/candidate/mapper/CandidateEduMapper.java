package com.hanzhan.tech.workbench.candidate.mapper;

import java.util.List;
import com.hanzhan.tech.workbench.candidate.entity.CandidateEdu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Description: 教育经历
 * @Author: jeecg-boot
 * @Date:   2021-09-20
 * @Version: V1.0
 */
public interface CandidateEduMapper extends BaseMapper<CandidateEdu> {

	public boolean deleteByMainId(@Param("mainId") String mainId);
    
	public List<CandidateEdu> selectByMainId(@Param("mainId") String mainId);
}
