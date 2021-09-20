package com.hanzhan.tech.workbench.candidate.mapper;

import java.util.List;
import com.hanzhan.tech.workbench.candidate.entity.CandidateVisithistory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Description: 候选人拜访历史
 * @Author: jeecg-boot
 * @Date:   2021-09-20
 * @Version: V1.0
 */
public interface CandidateVisithistoryMapper extends BaseMapper<CandidateVisithistory> {

	public boolean deleteByMainId(@Param("mainId") String mainId);
    
	public List<CandidateVisithistory> selectByMainId(@Param("mainId") String mainId);
}
