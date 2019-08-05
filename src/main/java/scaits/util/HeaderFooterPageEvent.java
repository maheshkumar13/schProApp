package scaits.util;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;
public class HeaderFooterPageEvent extends PdfPageEventHelper {
    public void onEndPage(PdfWriter writer,Document document) {
    	PdfContentByte cb = writer.getDirectContent();
		Phrase footer2 = new Phrase("Principal", new Font(Font.FontFamily.TIMES_ROMAN, 12f, Font.BOLD, BaseColor.BLACK));
		ColumnText.showTextAligned(cb, Element.ALIGN_RIGHT, footer2, document.leftMargin() + 450, document.bottom(), 0);
    }
} 