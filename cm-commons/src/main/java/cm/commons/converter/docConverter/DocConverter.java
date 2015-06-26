package cm.commons.converter.docConverter;

import cm.commons.converter.pdfConverter.PDFConverter;
import cm.commons.converter.swfConverter.SWFConverter;
import cm.commons.util.FileUtils;


public class DocConverter {

    private PDFConverter pdfConverter;
    private SWFConverter swfConverter;


    public DocConverter(PDFConverter pdfConverter, SWFConverter swfConverter) {
        super();
        this.pdfConverter = pdfConverter;
        this.swfConverter = swfConverter;
    }

    public void convert(String inputFile, String swfFile) {
        if (!FileUtils.exist(swfFile)) {
            String pdfFile = FileUtils.getFilePrefix(inputFile) + ".pdf";
            if (!FileUtils.getFileSufix(inputFile).equals(".pdf"))
                if (!FileUtils.exist(pdfFile))
                    this.pdfConverter.convert2PDF(inputFile);
            this.swfConverter.convert2SWF(pdfFile, swfFile);
        }

    }

    public void convert(String inputFile) {
        this.pdfConverter.convert2PDF(inputFile);
        String pdfFile = FileUtils.getFilePrefix(inputFile) + ".pdf";
        this.swfConverter.convert2SWF(pdfFile);

    }

}
