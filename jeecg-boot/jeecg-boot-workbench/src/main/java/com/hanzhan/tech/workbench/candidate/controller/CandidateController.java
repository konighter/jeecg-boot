package com.hanzhan.tech.workbench.candidate.controller;

import java.io.UnsupportedEncodingException;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.jeecg.common.system.vo.LoginUser;
import org.apache.shiro.SecurityUtils;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;
import com.hanzhan.tech.workbench.candidate.entity.CandidateCompanys;
import com.hanzhan.tech.workbench.candidate.entity.CandidateVisithistory;
import com.hanzhan.tech.workbench.candidate.entity.CandidateResume;
import com.hanzhan.tech.workbench.candidate.entity.CandidateEdu;
import com.hanzhan.tech.workbench.candidate.entity.Candidate;
import com.hanzhan.tech.workbench.candidate.vo.CandidatePage;
import com.hanzhan.tech.workbench.candidate.service.ICandidateService;
import com.hanzhan.tech.workbench.candidate.service.ICandidateCompanysService;
import com.hanzhan.tech.workbench.candidate.service.ICandidateVisithistoryService;
import com.hanzhan.tech.workbench.candidate.service.ICandidateResumeService;
import com.hanzhan.tech.workbench.candidate.service.ICandidateEduService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.aspect.annotation.AutoLog;

 /**
 * @Description: 候选人表
 * @Author: jeecg-boot
 * @Date:   2021-09-20
 * @Version: V1.0
 */
@Api(tags="候选人表")
@RestController
@RequestMapping("/candidate/candidate")
@Slf4j
public class CandidateController {
	@Autowired
	private ICandidateService candidateService;
	@Autowired
	private ICandidateCompanysService candidateCompanysService;
	@Autowired
	private ICandidateVisithistoryService candidateVisithistoryService;
	@Autowired
	private ICandidateResumeService candidateResumeService;
	@Autowired
	private ICandidateEduService candidateEduService;
	
	/**
	 * 分页列表查询
	 *
	 * @param candidate
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "候选人表-分页列表查询")
	@ApiOperation(value="候选人表-分页列表查询", notes="候选人表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Candidate candidate,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Candidate> queryWrapper = QueryGenerator.initQueryWrapper(candidate, req.getParameterMap());
		Page<Candidate> page = new Page<Candidate>(pageNo, pageSize);
		IPage<Candidate> pageList = candidateService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param candidatePage
	 * @return
	 */
	@AutoLog(value = "候选人表-添加")
	@ApiOperation(value="候选人表-添加", notes="候选人表-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody CandidatePage candidatePage) {
		Candidate candidate = new Candidate();
		BeanUtils.copyProperties(candidatePage, candidate);
		candidateService.saveMain(candidate, candidatePage.getCandidateCompanysList(),candidatePage.getCandidateVisithistoryList(),candidatePage.getCandidateResumeList(),candidatePage.getCandidateEduList());
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param candidatePage
	 * @return
	 */
	@AutoLog(value = "候选人表-编辑")
	@ApiOperation(value="候选人表-编辑", notes="候选人表-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody CandidatePage candidatePage) {
		Candidate candidate = new Candidate();
		BeanUtils.copyProperties(candidatePage, candidate);
		Candidate candidateEntity = candidateService.getById(candidate.getId());
		if(candidateEntity==null) {
			return Result.error("未找到对应数据");
		}
		candidateService.updateMain(candidate, candidatePage.getCandidateCompanysList(),candidatePage.getCandidateVisithistoryList(),candidatePage.getCandidateResumeList(),candidatePage.getCandidateEduList());
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "候选人表-通过id删除")
	@ApiOperation(value="候选人表-通过id删除", notes="候选人表-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		candidateService.delMain(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "候选人表-批量删除")
	@ApiOperation(value="候选人表-批量删除", notes="候选人表-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.candidateService.delBatchMain(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "候选人表-通过id查询")
	@ApiOperation(value="候选人表-通过id查询", notes="候选人表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Candidate candidate = candidateService.getById(id);
		if(candidate==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(candidate);

	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "工作单位通过主表ID查询")
	@ApiOperation(value="工作单位主表ID查询", notes="工作单位-通主表ID查询")
	@GetMapping(value = "/queryCandidateCompanysByMainId")
	public Result<?> queryCandidateCompanysListByMainId(@RequestParam(name="id",required=true) String id) {
		List<CandidateCompanys> candidateCompanysList = candidateCompanysService.selectByMainId(id);
		return Result.OK(candidateCompanysList);
	}
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "候选人拜访历史通过主表ID查询")
	@ApiOperation(value="候选人拜访历史主表ID查询", notes="候选人拜访历史-通主表ID查询")
	@GetMapping(value = "/queryCandidateVisithistoryByMainId")
	public Result<?> queryCandidateVisithistoryListByMainId(@RequestParam(name="id",required=true) String id) {
		List<CandidateVisithistory> candidateVisithistoryList = candidateVisithistoryService.selectByMainId(id);
		return Result.OK(candidateVisithistoryList);
	}
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "候选人简历通过主表ID查询")
	@ApiOperation(value="候选人简历主表ID查询", notes="候选人简历-通主表ID查询")
	@GetMapping(value = "/queryCandidateResumeByMainId")
	public Result<?> queryCandidateResumeListByMainId(@RequestParam(name="id",required=true) String id) {
		List<CandidateResume> candidateResumeList = candidateResumeService.selectByMainId(id);
		return Result.OK(candidateResumeList);
	}
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "教育经历通过主表ID查询")
	@ApiOperation(value="教育经历主表ID查询", notes="教育经历-通主表ID查询")
	@GetMapping(value = "/queryCandidateEduByMainId")
	public Result<?> queryCandidateEduListByMainId(@RequestParam(name="id",required=true) String id) {
		List<CandidateEdu> candidateEduList = candidateEduService.selectByMainId(id);
		return Result.OK(candidateEduList);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param candidate
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, Candidate candidate) {
      // Step.1 组装查询条件查询数据
      QueryWrapper<Candidate> queryWrapper = QueryGenerator.initQueryWrapper(candidate, request.getParameterMap());
      LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();

      //Step.2 获取导出数据
      List<Candidate> queryList = candidateService.list(queryWrapper);
      // 过滤选中数据
      String selections = request.getParameter("selections");
      List<Candidate> candidateList = new ArrayList<Candidate>();
      if(oConvertUtils.isEmpty(selections)) {
          candidateList = queryList;
      }else {
          List<String> selectionList = Arrays.asList(selections.split(","));
          candidateList = queryList.stream().filter(item -> selectionList.contains(item.getId())).collect(Collectors.toList());
      }

      // Step.3 组装pageList
      List<CandidatePage> pageList = new ArrayList<CandidatePage>();
      for (Candidate main : candidateList) {
          CandidatePage vo = new CandidatePage();
          BeanUtils.copyProperties(main, vo);
          List<CandidateCompanys> candidateCompanysList = candidateCompanysService.selectByMainId(main.getId());
          vo.setCandidateCompanysList(candidateCompanysList);
          List<CandidateVisithistory> candidateVisithistoryList = candidateVisithistoryService.selectByMainId(main.getId());
          vo.setCandidateVisithistoryList(candidateVisithistoryList);
          List<CandidateResume> candidateResumeList = candidateResumeService.selectByMainId(main.getId());
          vo.setCandidateResumeList(candidateResumeList);
          List<CandidateEdu> candidateEduList = candidateEduService.selectByMainId(main.getId());
          vo.setCandidateEduList(candidateEduList);
          pageList.add(vo);
      }

      // Step.4 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      mv.addObject(NormalExcelConstants.FILE_NAME, "候选人表列表");
      mv.addObject(NormalExcelConstants.CLASS, CandidatePage.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("候选人表数据", "导出人:"+sysUser.getRealname(), "候选人表"));
      mv.addObject(NormalExcelConstants.DATA_LIST, pageList);
      return mv;
    }

    /**
    * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
      MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
      Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
      for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
          MultipartFile file = entity.getValue();// 获取上传文件对象
          ImportParams params = new ImportParams();
          params.setTitleRows(2);
          params.setHeadRows(1);
          params.setNeedSave(true);
          try {
              List<CandidatePage> list = ExcelImportUtil.importExcel(file.getInputStream(), CandidatePage.class, params);
              for (CandidatePage page : list) {
                  Candidate po = new Candidate();
                  BeanUtils.copyProperties(page, po);
                  candidateService.saveMain(po, page.getCandidateCompanysList(),page.getCandidateVisithistoryList(),page.getCandidateResumeList(),page.getCandidateEduList());
              }
              return Result.OK("文件导入成功！数据行数:" + list.size());
          } catch (Exception e) {
              log.error(e.getMessage(),e);
              return Result.error("文件导入失败:"+e.getMessage());
          } finally {
              try {
                  file.getInputStream().close();
              } catch (IOException e) {
                  e.printStackTrace();
              }
          }
      }
      return Result.OK("文件导入失败！");
    }

}
