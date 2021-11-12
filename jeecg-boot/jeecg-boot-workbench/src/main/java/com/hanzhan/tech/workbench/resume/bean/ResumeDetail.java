package com.hanzhan.tech.workbench.resume.bean;

import java.util.List;

/**
 * Auto-generated: 2021-10-25 19:20:30
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class ResumeDetail {

    private List<ProjectInfo> projectInfos;
    private OtherInfo otherInfo;
    private BasicInfo basicInfo;
    private List<EducationInfo> educationInfos;
    private List<WorkInfo> workInfos;
    public void setProjectInfos(List<ProjectInfo> projectInfos) {
         this.projectInfos = projectInfos;
     }
     public List<ProjectInfo> getProjectInfos() {
         return projectInfos;
     }

    public void setOtherInfo(OtherInfo otherInfo) {
         this.otherInfo = otherInfo;
     }
     public OtherInfo getOtherInfo() {
         return otherInfo;
     }

    public void setBasicInfo(BasicInfo basicInfo) {
         this.basicInfo = basicInfo;
     }
     public BasicInfo getBasicInfo() {
         return basicInfo;
     }

    public void setEducationInfos(List<EducationInfo> educationInfos) {
         this.educationInfos = educationInfos;
     }
     public List<EducationInfo> getEducationInfos() {
         return educationInfos;
     }

    public void setWorkInfos(List<WorkInfo> workInfos) {
         this.workInfos = workInfos;
     }
     public List<WorkInfo> getWorkInfos() {
         return workInfos;
     }

}
