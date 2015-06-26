import cm.commons.converter.docConverter.DocConverter;
import cm.commons.converter.pdfConverter.OpenOfficePDFConverter;
import cm.commons.converter.pdfConverter.PDFConverter;
import cm.commons.converter.swfConverter.SWFConverter;
import cm.commons.converter.swfConverter.SWFToolsSWFConverter;


public class TestJacob {
    public static void main(String[] args) throws Exception {
        PDFConverter pdfConverter = new OpenOfficePDFConverter();
        //PDFConverter pdfConverter = new JacobPDFConverter();
        //PDFConverter pdfConverter = new JComPDFConverter();
        SWFConverter swfConverter = new SWFToolsSWFConverter();
        DocConverter converter = new DocConverter(pdfConverter, swfConverter);
        String txtFile = "D:/test/txtTest.txt";

        String docFile = "D:\\test\\docTest.doc";
        //String xlsFile = "D:\\test\\xlsTest.xlsx";
        //String pptFile = "D:\\test\\pptTest.pptx";
        converter.convert(txtFile);
        //converter.convert(docFile);
//      converter.convert(xlsFile);
//      converter.convert(pptFile);
    }
}
