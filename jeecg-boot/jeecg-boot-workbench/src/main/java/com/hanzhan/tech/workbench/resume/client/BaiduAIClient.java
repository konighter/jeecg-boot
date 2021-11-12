package com.hanzhan.tech.workbench.resume.client;

import com.baidu.aip.http.AipRequest;
import com.baidu.aip.http.EBodyFormat;
import com.baidu.aip.nlp.AipNlp;
import com.google.common.collect.Maps;
import com.google.common.io.Files;
import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.Map;

public class BaiduAIClient extends AipNlp {

    public BaiduAIClient(String appId, String apiKey, String secretKey) {
        super(appId, apiKey, secretKey);
    }

    public JSONObject resumeExtract(File file) throws IOException {
        AipRequest request = new AipRequest();
        this.preOperation(request);
        Base64.Encoder encoder = Base64.getEncoder();
        Map<String, String> resume = Maps.newHashMap();
        resume.put("filename", file.getName());
        resume.put("filetype", Files.getFileExtension(file.getPath()));
        resume.put("filedata", encoder.encodeToString(IOUtils.toByteArray(new FileInputStream(file))));
        request.addBody("resume", resume);

        request.setUri("https://aip.baidubce.com/rpc/2.0/recruitment/v1/cvparser");
        request.addHeader("Content-Encoding", "GBK");
        request.addHeader("Content-Type", "application/json");
        request.setBodyFormat(EBodyFormat.RAW_JSON);
        this.postOperation(request);
        return this.requestServer(request);


    }

    public JSONObject resumeExtract(String content, String fileName) throws IOException {
        AipRequest request = new AipRequest();
        this.preOperation(request);
        Base64.Encoder encoder = Base64.getEncoder();
        Map<String, String> resume = Maps.newHashMap();
        resume.put("filename", fileName);
        resume.put("filetype", "txt");
        resume.put("filedata", encoder.encodeToString(content.getBytes("UTF-8")));
        request.addBody("resume", resume);

        request.setUri("https://aip.baidubce.com/rpc/2.0/recruitment/v1/cvparser");
        request.addHeader("Content-Encoding", "GBK");
        request.addHeader("Content-Type", "application/json");
        request.setBodyFormat(EBodyFormat.RAW_JSON);
        this.postOperation(request);
        return this.requestServer(request);
    }
}
