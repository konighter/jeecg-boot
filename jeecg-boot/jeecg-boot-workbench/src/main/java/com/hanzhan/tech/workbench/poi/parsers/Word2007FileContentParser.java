package com.hanzhan.tech.workbench.poi.parsers;

import com.hanzhan.tech.workbench.poi.FileSuffix;
import com.hanzhan.tech.workbench.poi.SupportedSuffix;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Slf4j
@SupportedSuffix({FileSuffix.DOCX})
@Component
public class Word2007FileContentParser extends AbstractFileContentParser {
    @Override
    public String doParseFileContent(InputStream is) throws IOException {
        XWPFDocument document = new XWPFDocument(is);
        XWPFWordExtractor extractor = new XWPFWordExtractor(document);
        String content = extractor.getText();
        return content;
    }
}
