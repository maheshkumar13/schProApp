package scaits.reportsView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import scaits.bo.employee.EmployeeBO;
import scaits.util.StringUtil;

public class PdfView  {
	static DecimalFormat  myFormatter = new DecimalFormat("##,##,##,##,###");
      public static ByteArrayOutputStream  buildPdfDocument( List<Object[]> totalResults,Set<String> colNames, String reportName, String dateStr, EmployeeBO employee) throws Exception {
    	  Document document;
    	  if(colNames.size()>20){
    	  document = new Document(PageSize.A4_LANDSCAPE.rotate(), 3, 3, 10, 10);
    	  }else{
    		 document = new Document(PageSize.LETTER.rotate(), 3, 3, 10, 10);
    	  }
         ByteArrayOutputStream out = new ByteArrayOutputStream();
          
         try {
        	 String respath = "/static/assets/logo_scil1.png";
        	 Image image = Image.getInstance(PdfView.class.getResource(respath));
        		PdfPTable imgtable = new PdfPTable(3);
				float[] columnWidths = { 0.7f, 2.10f, 1.10f };

				imgtable.setWidths(columnWidths);
				imgtable.setWidthPercentage(100f);
				PdfPCell imgcell;
				imgcell = new PdfPCell(image, false);
				imgcell.setRowspan(4);
				imgcell.setTop(0);
				imgcell.setBorder(Rectangle.NO_BORDER);
				imgcell.setLeft(0);
				imgcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				imgcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				imgtable.addCell(imgcell);

				imgcell = new PdfPCell(new Phrase("Sri Chaitanya Educational Institutions", new Font(Font.FontFamily.TIMES_ROMAN,14f,Font.BOLD)));
				imgcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				imgcell.setBorder(Rectangle.NO_BORDER);
				imgtable.addCell(imgcell);

				
				imgcell = new PdfPCell(new Phrase("	  ", new Font(Font.FontFamily.TIMES_ROMAN, 10f, Font.BOLD)));
				imgcell.setBorder(Rectangle.NO_BORDER);
				imgtable.addCell(imgcell);

				imgcell = new PdfPCell(new Phrase("(Admin. by Sri Kalyana Chakravarti Memorial Educational Trust)", new Font(Font.FontFamily.TIMES_ROMAN, 10f,
						Font.BOLD)));
				imgcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				imgcell.setNoWrap(false);
				imgcell.setBorder(Rectangle.NO_BORDER);
				imgtable.addCell(imgcell);
				String campus=String.valueOf(employee.getCampus());
				String campustype=String.valueOf(employee.getCampus());
				String city=String.valueOf(employee.getCampus());;
				imgcell = new PdfPCell(new Phrase("  " + (campus != null ? campus : "") + "," + (campustype != null ? campustype : "") + "("
						+ (city != null ? city : "") + ")", new Font(Font.FontFamily.TIMES_ROMAN, 10f, Font.BOLD)));
				imgcell.setBorder(Rectangle.NO_BORDER);
				imgtable.addCell(imgcell);
				
				imgcell = new PdfPCell(new Phrase("" + reportName, new Font(Font.FontFamily.TIMES_ROMAN, 12f, Font.BOLD)));
				imgcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				imgcell.setNoWrap(false);
				imgcell.setBorder(Rectangle.NO_BORDER);
				imgtable.addCell(imgcell);
				
				imgcell = new PdfPCell(new Phrase("	" + (dateStr), new Font(Font.FontFamily.TIMES_ROMAN, 10f, Font.BOLD)));
				imgcell.setBorder(Rectangle.NO_BORDER);
				imgtable.addCell(imgcell);
             PdfPTable table = new PdfPTable(colNames.size());
             table.setWidthPercentage(100f);
             if(colNames.size()>20){
            	 table.setTotalWidth(1000);
             }else{
            	 table.setTotalWidth(790);
             }
             
             Font headFont = FontFactory.getFont(FontFactory.TIMES_ROMAN);
             Font cellFont = FontFactory.getFont(FontFactory.TIMES_ROMAN);
             headFont.setColor(BaseColor.WHITE);;
             
             if(colNames.size()>20){
            	 headFont.setSize(7f);
            	 cellFont.setSize(6f);
             }else{
            	 headFont.setSize(8f);
            	 cellFont.setSize(7f);
             }
             PdfPCell hcell;
             for (String col : colNames) {
            	 hcell = new PdfPCell(new Phrase(col, headFont));
                 hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
                 hcell.setBackgroundColor(BaseColor.DARK_GRAY);
                 table.addCell(hcell);
			}
            
             
             PdfPCell cell;
          for (int i=0;i<totalResults.size();i++) {
        	  for (Object  ob : totalResults.get(i)) {
        	   cell = new PdfPCell(new Phrase(ob!=null?ob.toString():"",cellFont));
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               cell.setHorizontalAlignment(Element.ALIGN_CENTER);
               table.addCell(cell);
               
           }
                
          }

             PdfWriter.getInstance(document, out);
             document.open();
             document.add(imgtable);
             document.add(table);
             
             document.close();
             
         } catch (DocumentException ex) {
         
             Logger.getLogger(PdfView.class.getName()).log(Level.SEVERE, null, ex);
         }

         return out;
     }
      
      public static ByteArrayOutputStream  buildPdfDocument1( List<List<String>> totalResults,Set<String> colNames, String reportName, String dateStr, EmployeeBO employee) throws Exception {
    	  Document document;
    	  if(colNames.size()>20){
    	  document = new Document(PageSize.A4_LANDSCAPE.rotate(), 3, 3, 10, 10);
    	  }else{
    		 document = new Document(PageSize.LETTER.rotate(), 3, 3, 10, 10);
    	  }
         ByteArrayOutputStream out = new ByteArrayOutputStream();
          
         try {
        	 String respath = "/static/assets/logo_scil1.png";
        	 Image image = Image.getInstance(PdfView.class.getResource(respath));
        		PdfPTable imgtable = new PdfPTable(3);
				float[] columnWidths = { 0.7f, 2.10f, 1.10f };

				imgtable.setWidths(columnWidths);
				imgtable.setWidthPercentage(100f);
				PdfPCell imgcell;
				imgcell = new PdfPCell(image, false);
				imgcell.setRowspan(4);
				imgcell.setTop(0);
				imgcell.setBorder(Rectangle.NO_BORDER);
				imgcell.setLeft(0);
				imgcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				imgcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				imgtable.addCell(imgcell);

				imgcell = new PdfPCell(new Phrase("Sri Chaitanya Educational Institutions", new Font(Font.FontFamily.TIMES_ROMAN,14f,Font.BOLD)));
				imgcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				imgcell.setBorder(Rectangle.NO_BORDER);
				imgtable.addCell(imgcell);

				
				imgcell = new PdfPCell(new Phrase("	  ", new Font(Font.FontFamily.TIMES_ROMAN, 10f, Font.BOLD)));
				imgcell.setBorder(Rectangle.NO_BORDER);
				imgtable.addCell(imgcell);

				imgcell = new PdfPCell(new Phrase("(Admin. by Sri Kalyana Chakravarti Memorial Educational Trust)", new Font(Font.FontFamily.TIMES_ROMAN, 10f,
						Font.BOLD)));
				imgcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				imgcell.setNoWrap(false);
				imgcell.setBorder(Rectangle.NO_BORDER);
				imgtable.addCell(imgcell);
				String campus=String.valueOf(employee.getCampus());
				String campustype=String.valueOf(employee.getCampus());
				String city=String.valueOf(employee.getCampus());;
				imgcell = new PdfPCell(new Phrase("  " + (campus != null ? campus : "") + "," + (campustype != null ? campustype : "") + "("
						+ (city != null ? city : "") + ")", new Font(Font.FontFamily.TIMES_ROMAN, 10f, Font.BOLD)));
				imgcell.setBorder(Rectangle.NO_BORDER);
				imgtable.addCell(imgcell);
				
				imgcell = new PdfPCell(new Phrase("" + reportName, new Font(Font.FontFamily.TIMES_ROMAN, 12f, Font.BOLD)));
				imgcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				imgcell.setNoWrap(false);
				imgcell.setBorder(Rectangle.NO_BORDER);
				imgtable.addCell(imgcell);
				
				imgcell = new PdfPCell(new Phrase("	" + (dateStr), new Font(Font.FontFamily.TIMES_ROMAN, 10f, Font.BOLD)));
				imgcell.setBorder(Rectangle.NO_BORDER);
				imgtable.addCell(imgcell);
             PdfPTable table = new PdfPTable(colNames.size());
             table.setWidthPercentage(100f);
             if(colNames.size()>20){
            	 table.setTotalWidth(1000);
             }else{
            	 table.setTotalWidth(790);
             }
             
             Font headFont = FontFactory.getFont(FontFactory.TIMES_ROMAN);
             Font cellFont = FontFactory.getFont(FontFactory.TIMES_ROMAN);
             headFont.setColor(BaseColor.WHITE);;
             
             if(colNames.size()>20){
            	 headFont.setSize(7f);
            	 cellFont.setSize(6f);
             }else{
            	 headFont.setSize(8f);
            	 cellFont.setSize(7f);
             }
             PdfPCell hcell;
             for (String col : colNames) {
            	 hcell = new PdfPCell(new Phrase(col, headFont));
                 hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
                 hcell.setBackgroundColor(BaseColor.DARK_GRAY);
                 table.addCell(hcell);
			}
            
             
             PdfPCell cell;
          for (int i=0;i<totalResults.size();i++) {
        	  for (Object  ob : totalResults.get(i)) {
        	   cell = new PdfPCell(new Phrase(ob!=null?ob.toString():"",cellFont));
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               cell.setHorizontalAlignment(Element.ALIGN_CENTER);
               table.addCell(cell);
               
           }
                
          }

             PdfWriter.getInstance(document, out);
             document.open();
             document.add(imgtable);
             document.add(table);
             
             document.close();
             
         } catch (DocumentException ex) {
         
             Logger.getLogger(PdfView.class.getName()).log(Level.SEVERE, null, ex);
         }

         return out;
     }

}
