package com.hanzhan.tech.workbench.candidate.vo;

import java.util.List;
import com.hanzhan.tech.workbench.candidate.entity.Candidate;
import com.hanzhan.tech.workbench.candidate.entity.CandidateCompanys;
import com.hanzhan.tech.workbench.candidate.entity.CandidateVisithistory;
import com.hanzhan.tech.workbench.candidate.entity.CandidateResume;
import com.hanzhan.tech.workbench.candidate.entity.CandidateEdu;
import lombok.Data;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelEntity;
import org.jeecgframework.poi.excel.annotation.ExcelCollection;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;
import org.jeecg.common.aspect.annotation.Dict;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Description: 候选人表
 * @Author: jeecg-boot
 * @Date:   2021-09-20
 * @Version: V1.0
 */
@Data
@ApiModel(value="hz_candidatePage对象", description="候选人表")
public class CandidatePage {

	/**主键*/
	@ApiModelProperty(value = "主键")
    private String id;
	/**姓名*/
	@Excel(name = "姓名", width = 15)
	@ApiModelProperty(value = "姓名")
    private String name;
	/**工作单位*/
	@Excel(name = "工作单位", width = 15)
	@ApiModelProperty(value = "工作单位")
    private String company;
	/**职位*/
	@Excel(name = "职位", width = 15)
	@ApiModelProperty(value = "职位")
    private String job;
	/**电话号码*/
	@Excel(name = "电话号码", width = 15)
	@ApiModelProperty(value = "电话号码")
    private String phoneNum;
	/**邮箱*/
	@Excel(name = "邮箱", width = 15)
	@ApiModelProperty(value = "邮箱")
    private String email;
	/**标签*/
	@Excel(name = "标签", width = 15)
	@ApiModelProperty(value = "标签")
    private String tags;
	/**上次拜访时间*/
	@Excel(name = "上次拜访时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "上次拜访时间")
    private Date lastVisitTime;
	/**下次拜访时间*/
	@Excel(name = "下次拜访时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "下次拜访时间")
    private Date nextVisitTime;
	/**自我介绍*/
	@Excel(name = "自我介绍", width = 15)
	@ApiModelProperty(value = "自我介绍")
    private String introduction;
	/**技能*/
	@Excel(name = "技能", width = 15)
	@ApiModelProperty(value = "技能")
    private String skills;
	/**状态*/
	@Excel(name = "状态", width = 15)
	@ApiModelProperty(value = "状态")
    private Integer status;
	/**创建日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "创建日期")
    private Date createTime;
	/**创建人*/
	@ApiModelProperty(value = "创建人")
    private String createBy;
	/**更新人*/
	@ApiModelProperty(value = "更新人")
    private String updateBy;
	/**更新日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "更新日期")
    private Date updateTime;
	/**所属部门*/
	@ApiModelProperty(value = "所属部门")
    private String sysOrgCode;
	/**租户*/
	@Excel(name = "租户", width = 15)
	@ApiModelProperty(value = "租户")
    private Integer tenantId;

	@ExcelCollection(name="工作单位")
	@ApiModelProperty(value = "工作单位")
	private List<CandidateCompanys> candidateCompanysList;
	@ExcelCollection(name="候选人拜访历史")
	@ApiModelProperty(value = "候选人拜访历史")
	private List<CandidateVisithistory> candidateVisithistoryList;
	@ExcelCollection(name="候选人简历")
	@ApiModelProperty(value = "候选人简历")
	private List<CandidateResume> candidateResumeList;
	@ExcelCollection(name="教育经历")
	@ApiModelProperty(value = "教育经历")
	private List<CandidateEdu> candidateEduList;

}
