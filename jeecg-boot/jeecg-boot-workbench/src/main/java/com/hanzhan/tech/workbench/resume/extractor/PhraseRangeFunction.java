package com.hanzhan.tech.workbench.resume.extractor;

import com.hankcs.hanlp.seg.common.Term;

import java.util.List;

@FunctionalInterface
public interface PhraseRangeFunction {
    void range(String line, List<Term> terms);
}
