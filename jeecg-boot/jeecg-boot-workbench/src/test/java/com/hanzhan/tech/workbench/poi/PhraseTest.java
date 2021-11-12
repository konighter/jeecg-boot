package com.hanzhan.tech.workbench.poi;

import com.hanzhan.tech.workbench.resume.extractor.NLPUtils;
import com.hanzhan.tech.workbench.resume.extractor.PhraseClassifier;
import com.hanzhan.tech.workbench.resume.extractor.PhraseContent;
import org.junit.Test;

public class PhraseTest {

    @Test
    public void TestPhrase() {
        PhraseContent content = PhraseContent.createPhraseContent(PhraseClassifier.EDU);
        String line = "中国人民万岁";
        content.addContents(line, NLPUtils.getDefaultSegment().seg(line));
        content.range((l, terms) -> {
            System.out.println(l);
            System.out.println(terms);
        });



    }
}
