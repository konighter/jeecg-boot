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
 * @Description: 候选人简历
 * @Author: jeecg-boot
 * @Date:   2021-09-20
 * @Version: V1.0
 */
@ApiModel(value="hz_candidate_resume对象", description="候选人简历")
@Data
@TableName("hz_candidate_resume")
public class CandidateResume implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键")
    private String id;
	/**候选人ID*/
    @ApiModelProperty(value = "候选人ID")
    private String candidateId;
	/**简历文本*/
	@Excel(name = "简历文本", width = 15)
    @ApiModelProperty(value = "简历文本")
    private String resumes;
	/**简历URL*/
	@Excel(name = "简历URL", width = 15)
    @ApiModelProperty(value = "简历URL")
    private String resumeUrl;
	/**简历来源*/
	@Excel(name = "简历来源", width = 15)
    @ApiModelProperty(value = "简历来源")
    private String source;
	/**是否最新*/
	@Excel(name = "是否最新", width = 15)
    @ApiModelProperty(value = "是否最新")
    private Integer current;
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
}
