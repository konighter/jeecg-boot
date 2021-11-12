package com.hanzhan.tech.workbench.resume.extractor;

import com.google.common.collect.Lists;
import com.hankcs.hanlp.seg.common.Term;

import java.util.List;

public enum PhraseClassifier {
    BASIC("pct"), EDU("pcte"), WORKS("pctw"), PROJ("pctp"), SKILLS("pctk"), SUMMERY("pctm"), INTENT("pctIn");
    String name;

    PhraseClassifier(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public static boolean isPhraseClassifier(String name) {
        return name.toLowerCase().startsWith("pct");
    }

    public static PhraseClassifier getClassifier(String name) {
        for (PhraseClassifier classifier : PhraseClassifier.values()) {
            if (classifier.name.equalsIgnoreCase(name)) {
                return classifier;
            }
        }
        return BASIC;
    }
}


