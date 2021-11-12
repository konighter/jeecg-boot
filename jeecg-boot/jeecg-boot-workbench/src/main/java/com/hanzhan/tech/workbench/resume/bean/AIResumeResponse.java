/**
  * Copyright 2021 bejson.com
  */
package com.hanzhan.tech.workbench.resume.bean;
import java.util.List;

/**
 * Auto-generated: 2021-10-25 19:20:30
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class AIResumeResponse {

    private long logId;
    private String errorMsg;
    private int errorCode;
    private List<Results> results;
    public void setLogId(long logId) {
         this.logId = logId;
     }
     public long getLogId() {
         return logId;
     }

    public void setErrorMsg(String errorMsg) {
         this.errorMsg = errorMsg;
     }
     public String getErrorMsg() {
         return errorMsg;
     }

    public void setErrorCode(int errorCode) {
         this.errorCode = errorCode;
     }
     public int getErrorCode() {
         return errorCode;
     }

    public void setResults(List<Results> results) {
         this.results = results;
     }
     public List<Results> getResults() {
         return results;
     }

}
