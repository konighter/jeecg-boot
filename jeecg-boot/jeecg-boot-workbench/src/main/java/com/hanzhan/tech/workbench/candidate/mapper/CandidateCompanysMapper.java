package com.hanzhan.tech.workbench.candidate.mapper;

import java.util.List;
import com.hanzhan.tech.workbench.candidate.entity.CandidateCompanys;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Description: 工作单位
 * @Author: jeecg-boot
 * @Date:   2021-09-20
 * @Version: V1.0
 */
public interface CandidateCompanysMapper extends BaseMapper<CandidateCompanys> {

	public boolean deleteByMainId(@Param("mainId") String mainId);
    
	public List<CandidateCompanys> selectByMainId(@Param("mainId") String mainId);
}
