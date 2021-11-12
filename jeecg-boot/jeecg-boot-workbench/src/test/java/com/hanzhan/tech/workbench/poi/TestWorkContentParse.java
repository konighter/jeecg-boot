package com.hanzhan.tech.workbench.poi;

import com.baidu.aip.nlp.AipNlp;
import com.google.common.collect.Maps;
import com.google.common.io.Files;
import com.google.gson.JsonObject;
import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.corpus.tag.Nature;
import com.hankcs.hanlp.seg.Segment;
import com.hankcs.hanlp.seg.common.Term;
import com.hankcs.hanlp.tokenizer.NLPTokenizer;
import com.hanzhan.tech.workbench.poi.parsers.PdfFileContentParser;
import com.hanzhan.tech.workbench.poi.parsers.Word2007FileContentParser;
import com.hanzhan.tech.workbench.poi.parsers.WordFileContentParser;
import com.hanzhan.tech.workbench.resume.client.BaiduAIClient;
import com.hanzhan.tech.workbench.resume.extractor.ExtractUtils;
import com.hanzhan.tech.workbench.resume.extractor.NLPUtils;
import com.hanzhan.tech.workbench.resume.extractor.PhraseClassifier;
import com.hanzhan.tech.workbench.resume.extractor.PhraseContent;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.compress.utils.Lists;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.LineIterator;
import org.apache.commons.lang3.StringUtils;
import org.apache.lucene.index.Terms;
import org.json.JSONObject;
import org.junit.Test;

import java.io.*;
import java.util.List;

@Slf4j
public class TestWorkContentParse {
    private static BaiduAIClient client;

    static {
        client = new BaiduAIClient("24997982", "q7Ydhx1eraG0ugLHiKQRLcbk", "GaTGbCgtjDY59U2OokG6otQG0GOvwCGV");

        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);
    }


    @Test
    public void TestWordRead() throws IOException {
        FileContentParser parser = new WordFileContentParser();
        File resumePath = new File("/Users/hanyabin/Documents/resume/java");
        for (File f : resumePath.listFiles()) {
            if (FileSuffix.suffix(Files.getFileExtension(f.getPath())).equals(FileSuffix.DOC)) {

                String content = parser.parseFileContent(f);
                JSONObject resumeJson = client.resumeExtract(content, f.getName());
                log.info("resume result: {}", resumeJson);


//
//                List<PhraseContent> contents = ExtractUtils.extractPhrase(content);
//                for (PhraseContent c : contents) {
//                    c.range((text, terms) -> {
//                        log.info("lexer result: {}", client.lexer(text, Maps.newHashMap()));
//                    });
//                }
            }

        }
    }

    @Test
    public void TestWordXRead() throws IOException {
        FileContentParser parser = new Word2007FileContentParser();
        File resumePath = new File("/Users/hanyabin/Documents/resume/java");
        for (File f : resumePath.listFiles()) {
            if (FileSuffix.suffix(Files.getFileExtension(f.getPath())).equals(FileSuffix.DOCX)) {
                String content = parser.parseFileContent(f);
                ExtractUtils.extractPhrase(content);
            }
        }
    }

    @Test
    public void TestPdfRead() {
        FileContentParser parser = new PdfFileContentParser();
        File resumePath = new File("/Users/hanyabin/Documents/resume/java");
        for (File f : resumePath.listFiles()) {
            if (FileSuffix.suffix(Files.getFileExtension(f.getPath())).equals(FileSuffix.PDF)) {
                String content = parser.parseFileContent(f);
                ExtractUtils.extractPhrase(content);
            }

        }
    }

    @Test
    public void TestBaidu() {
        // 初始化一个AipNlp

//        client.lexer("")
    }

}
