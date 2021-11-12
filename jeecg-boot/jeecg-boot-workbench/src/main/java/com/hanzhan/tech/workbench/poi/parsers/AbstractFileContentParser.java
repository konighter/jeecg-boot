package com.hanzhan.tech.workbench.poi.parsers;

import com.hanzhan.tech.workbench.poi.FileContentParser;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Slf4j
public abstract class AbstractFileContentParser  implements FileContentParser {
    @Override
    public String parseFileContent(File file) {
        if (file == null || !file.exists() || !file.isFile()) {
            return "";
        }
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            String content = doParseFileContent(fis);
            if (log.isDebugEnabled()) {
                log.debug("FileContent[{}] : {}", file.getName(), content);
            }
            return content;
        } catch (IOException e) {
            log.error("ParseFileContentError", e);
        } finally {
            IOUtils.closeQuietly(fis);
        }

        return "";
    }

    @Override
    public String parseFileContent(String filepath) {
        return parseFileContent(new File(filepath));
    }

    public abstract String doParseFileContent(InputStream inputStream) throws IOException;
}
