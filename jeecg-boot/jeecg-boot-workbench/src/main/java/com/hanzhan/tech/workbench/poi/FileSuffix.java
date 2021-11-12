package com.hanzhan.tech.workbench.poi;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

import java.util.List;
import java.util.stream.Collectors;

public enum FileSuffix {
    TXT(Lists.newArrayList("txt", "csv")),
    DOC(Lists.newArrayList("doc")),
    DOCX(Lists.newArrayList("docx")),
    EXCEL(Lists.newArrayList("xlsx", "xls")),
    PDF(Lists.newArrayList("pdf")),
    HTML(Lists.newArrayList("html", "htm")),
    UNKNOWN(Lists.newArrayList());
    List<String> suffixes;
    FileSuffix(List<String> suffixes) {
        this.suffixes = ImmutableList.copyOf(suffixes.stream().map( s -> s.toUpperCase()).collect(Collectors.toList()));
    }

    public static FileSuffix suffix(String suffix) {
        for (FileSuffix s: FileSuffix.values() ) {
            if (s.suffixes.contains(suffix.toUpperCase())) {
                return s;
            }
        }
        return UNKNOWN;
    }

}
