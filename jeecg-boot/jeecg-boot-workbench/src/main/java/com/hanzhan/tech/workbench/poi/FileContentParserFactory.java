package com.hanzhan.tech.workbench.poi;

import com.google.common.collect.HashMultimap;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class FileContentParserFactory implements BeanPostProcessor {

    private static HashMultimap<FileSuffix ,FileContentParser> parserFactory = HashMultimap.<FileSuffix ,FileContentParser>create();
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (! (bean instanceof FileContentParser)) {
            return bean;
        }
        FileContentParser parser = (FileContentParser) bean;
        SupportedSuffix[] suffixParsers = bean.getClass().getAnnotationsByType(SupportedSuffix.class);
        if (suffixParsers != null && suffixParsers.length > 0) {
            for (SupportedSuffix suffixes : suffixParsers) {
                for (FileSuffix suffix : suffixes.value()) {
                    parserFactory.put(suffix, parser);
                }
            }
        }
        return bean;
    }

    public static FileContentParser getParser(FileSuffix suffix) {
        return parserFactory.get(suffix).stream().findFirst().get();
    }

}
