package com.hanzhan.tech.workbench.candidate.entity;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.UnsupportedEncodingException;

/**
 * @Description: 工作单位
 * @Author: jeecg-boot
 * @Date:   2021-09-20
 * @Version: V1.0
 */
@ApiModel(value="hz_candidate_companys对象", description="工作单位")
@Data
@TableName("hz_candidate_companys")
public class CandidateCompanys implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键")
    private String id;
	/**创建人*/
    @ApiModelProperty(value = "创建人")
    private String createBy;
	/**创建日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建日期")
    private Date createTime;
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
	/**候选人ID*/
    @ApiModelProperty(value = "候选人ID")
    private String candidateId;
	/**工作单位*/
	@Excel(name = "工作单位", width = 15)
    @ApiModelProperty(value = "工作单位")
    private String company;
	/**工作*/
	@Excel(name = "工作", width = 15)
    @ApiModelProperty(value = "工作")
    private String job;
	/**开始时间*/
	@Excel(name = "开始时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "开始时间")
    private Date startTime;
	/**结束时间*/
	@Excel(name = "结束时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "结束时间")
    private Date endTime;
	/**汇报对象*/
	@Excel(name = "汇报对象", width = 15)
    @ApiModelProperty(value = "汇报对象")
    private String response;
	/**团队规模*/
	@Excel(name = "团队规模", width = 15)
    @ApiModelProperty(value = "团队规模")
    private Integer teams;
	/**下属人数*/
	@Excel(name = "下属人数", width = 15)
    @ApiModelProperty(value = "下属人数")
    private Integer subordinate;
	/**工作内容*/
	@Excel(name = "工作内容", width = 15)
    @ApiModelProperty(value = "工作内容")
    private String intros;
}
