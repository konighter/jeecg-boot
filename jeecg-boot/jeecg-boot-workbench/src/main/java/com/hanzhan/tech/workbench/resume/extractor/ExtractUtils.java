package com.hanzhan.tech.workbench.resume.extractor;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.classification.tokenizers.HanLPTokenizer;
import com.hankcs.hanlp.corpus.tag.Nature;
import com.hankcs.hanlp.seg.Segment;
import com.hankcs.hanlp.seg.common.Term;
import org.apache.commons.compress.utils.Lists;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.LineIterator;
import org.apache.commons.lang3.StringUtils;

import java.io.StringReader;
import java.util.List;

public class ExtractUtils {

    /**
     * 提取人命
     * @param doc
     * @return
     */
    public static String extractChName(String doc) {
        Segment segment = HanLP.newSegment().enableNameRecognize(true);
        Term term = segment.seg(doc).stream().filter(t -> Nature.nr.equals(t.nature)).findFirst().get();
        if (term != null) {
            return term.word;
        } else {
            return "";
        }
    }

    public static String extractPhoneNo(String doc) {
        return "";
    }

    public static List<PhraseContent> extractPhrase(String content) {
        LineIterator iterator = IOUtils.lineIterator(new StringReader(content));
        List<PhraseContent> phraseContents = Lists.newArrayList();
        PhraseContent currentPhraseContent = PhraseContent.createPhraseContent(PhraseClassifier.BASIC);
        phraseContents.add(currentPhraseContent);

        while (iterator.hasNext()) {
            String lineContent = iterator.next();
            if (StringUtils.isBlank(lineContent.trim())) {
                continue;
            }
            List<Term> terms = NLPUtils.getDefaultSegment().seg(StringUtils.remove(lineContent.trim(), " "));
            Term term = terms.stream().filter(t -> PhraseClassifier.isPhraseClassifier(t.nature.toString())).findFirst().orElse(null);
            if (term != null && !phraseContents.get(phraseContents.size() - 1).getClassifier().getName().equalsIgnoreCase(term.nature.toString())) {
                currentPhraseContent = PhraseContent.createPhraseContent(PhraseClassifier.getClassifier(term.nature.toString()));
//                currentPhraseContent.addContents(lineContent, terms);
                phraseContents.add(currentPhraseContent);
            } else {
                currentPhraseContent.addContents(lineContent, terms);
            }
        }

        return phraseContents;
    }



}
