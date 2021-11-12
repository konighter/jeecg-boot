package com.hanzhan.tech.workbench.poi.parsers;

import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.io.RandomAccessBufferedFileInputStream;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

@Slf4j
public class PdfFileContentParser extends AbstractFileContentParser {
    @Override
    public String doParseFileContent(InputStream is) throws IOException {
        PDFParser parser = new PDFParser(new RandomAccessBufferedFileInputStream(is));
        parser.parse();
        PDDocument doc = parser.getPDDocument();
        PDFTextStripper textStripper = new PDFLayoutTextStripper();
        String content = textStripper.getText(doc);
        return content;

    }
}
