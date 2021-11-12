package com.hanzhan.tech.workbench.poi.parsers;

import com.hanzhan.tech.workbench.poi.FileSuffix;
import com.hanzhan.tech.workbench.poi.SupportedSuffix;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hwpf.HWPFDocument;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Slf4j
@SupportedSuffix({FileSuffix.DOC})
@Component
public class WordFileContentParser extends AbstractFileContentParser {
    @Override
    public String doParseFileContent(InputStream is) throws IOException  {
        HWPFDocument document = new HWPFDocument(is);
        String content = document.getDocumentText();
        return content;
    }
}
