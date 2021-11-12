package com.hanzhan.tech.workbench.poi;

import java.io.File;

public interface FileContentParser {
    String parseFileContent(File file);
    String parseFileContent(String filepath);
}
