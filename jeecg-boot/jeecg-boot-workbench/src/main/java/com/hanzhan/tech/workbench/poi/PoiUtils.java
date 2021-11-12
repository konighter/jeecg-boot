package com.hanzhan.tech.workbench.poi;

import com.google.common.collect.Maps;
import com.google.common.io.Files;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.util.Collections;
import java.util.Map;

@Slf4j
public class PoiUtils {

    public static Map<String,String> parseFile2Text(String pathName) {
        File path = new File(pathName);
        if (!path.exists()) {
            log.warn("Path[{}] not exist", pathName);
            return Collections.emptyMap();
        }

        Map<String, String> contents =  Maps.newHashMap();
        if (path.isFile()) {
            contents.put(path.getPath(), parseFile2Text(path));
        } else {
            for (File f : path.listFiles()) {
                String content = parseFile2Text(f);
                if (StringUtils.isNotEmpty(content)) {
                    contents.put(f.getPath(), content);
                }
            }
        }
        return contents;
    }

    public static String parseFile2Text(File file) {
        if (file == null || !file.exists() || !file.isFile()) {
            log.warn("invalid file: {}", file);
            return "";
        }
        FileContentParser parser = FileContentParserFactory.getParser(FileSuffix.suffix(Files.getFileExtension(file.getPath())));
        if (parser == null) {
            return "";
        } else {
            return parser.parseFileContent(file);
        }
    }



}
