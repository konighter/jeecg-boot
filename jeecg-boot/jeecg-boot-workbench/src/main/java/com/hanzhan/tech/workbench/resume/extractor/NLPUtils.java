package com.hanzhan.tech.workbench.resume.extractor;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.dictionary.CustomDictionary;
import com.hankcs.hanlp.seg.Segment;

public class NLPUtils {


    static {

        CustomDictionary.add("教育经历", PhraseClassifier.EDU.getName());
        CustomDictionary.add("教育背景", PhraseClassifier.EDU.getName());
        CustomDictionary.add("工作经历", PhraseClassifier.WORKS.getName());
        CustomDictionary.add("工作经验", PhraseClassifier.WORKS.getName());
        CustomDictionary.add("项目经验", PhraseClassifier.PROJ.getName());
        CustomDictionary.add("项目经历", PhraseClassifier.PROJ.getName());
        CustomDictionary.add("自我评价", PhraseClassifier.SUMMERY.getName());
        CustomDictionary.add("求职意向", PhraseClassifier.BASIC.getName());
        CustomDictionary.add("专业技能", PhraseClassifier.SKILLS.getName());
        CustomDictionary.add("个人技能", PhraseClassifier.SKILLS.getName());
        CustomDictionary.add("自我描述", PhraseClassifier.SUMMERY.getName());
        CustomDictionary.add("个人总结", PhraseClassifier.SUMMERY.getName());

    }

    public static Segment getDefaultSegment() {
        return HanLP.newSegment().enableAllNamedEntityRecognize(true);
    }


}
