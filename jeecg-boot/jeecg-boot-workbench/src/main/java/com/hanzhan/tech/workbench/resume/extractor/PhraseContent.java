package com.hanzhan.tech.workbench.resume.extractor;

import com.google.common.collect.Lists;
import com.hankcs.hanlp.seg.common.Term;

import java.util.List;

public class PhraseContent {
    private PhraseClassifier classifier;
    private List<String> contents;
    private List<List<Term>> contentTerms;

    private PhraseContent(){}

    public static PhraseContent createPhraseContent(PhraseClassifier classifier) {
        PhraseContent phraseContent = new PhraseContent();
        phraseContent.classifier  = classifier;
        phraseContent.contents = Lists.newArrayList();
        phraseContent.contentTerms = Lists.newArrayList();
        return phraseContent;
    }

    public PhraseContent addContents(String line, List<Term> terms) {
        this.contents.add(line);
        this.contentTerms.add(terms);
        return this;
    }

    public PhraseClassifier getClassifier() {
        return this.classifier;
    }

    public void range(PhraseRangeFunction range) {
        int size = this.contents.size();
        for (int i = 0; i < size; i++) {
            range.range(this.contents.get(i), this.contentTerms.get(i));
        }
    }
}
