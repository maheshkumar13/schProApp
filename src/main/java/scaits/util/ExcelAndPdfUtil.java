package scaits.util;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;

import scaits.bo.employee.EmployeeBO;
import scaits.bo.student.StudentTCDetailsBO;
import scaits.reportsView.ExcelView;
import scaits.reportsView.PdfView;



@Component
public class ExcelAndPdfUtil {
	
	@Autowired
	private EmployeeAction employeeAction;
	
	
	// excel and pdf creation
	
		public ResponseEntity<byte[]> getExcelOrPdf(List<List<String>> rts, HttpServletResponse servletResponse,String reportType, Set<String> columnNames) throws Exception{
			
			HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
					.getRequest();
		
			Set<String> colNameList=new HashSet<>();
			for (String string : columnNames) {
				colNameList.add(string.replace("[", "").replace("]", ""));
			}
		if (reportType != null && reportType.equalsIgnoreCase("xls")) {
			
			
			
			Set<String> colNames = colNameList;
			String dateStr = new SimpleDateFormat("dd-MMM-yyyy").format(new Date());
			String reportName ="DynamicReport_.xls";
			SXSSFWorkbook wb = new SXSSFWorkbook(100);
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			
			ExcelView.buildExcelDocument1(rts, colNames, wb, request, servletResponse, reportName, null, dateStr);
			
			try {
				wb.write(bos);
			} finally {
				bos.close();
				
			}

			byte[] bytes = bos.toByteArray();

			HttpHeaders headers = new HttpHeaders();
	       
			headers.set("Content-Type", "application/vnd.ms-excel;");
			headers.set("content-length", Integer.toString(bytes.length));
			headers.set("Content-Disposition", "attachment; filename=DynamicReport_"+ new Date().getTime() + ".xlsx");

			return new ResponseEntity<byte[]>(bytes, headers, HttpStatus.CREATED);
			
			
		}
		if (reportType != null && reportType.equalsIgnoreCase("pdf")) {
			
			
			ByteArrayOutputStream bis =	PdfView.buildPdfDocument1(rts, colNameList, "DynamicReport_",DU.format(new Date(),"dd/MM/yyyy"), employeeAction.getCurrentUser());
			HttpHeaders headers = new HttpHeaders();
			headers.set("Content-Type", "application/pdf;");
			headers.add("Content-Disposition", "attachment; filename=DynamicReport_"+ new Date().getTime() + ".pdf");

			return new ResponseEntity<byte[]>(bis.toByteArray(), headers, HttpStatus.CREATED);
		}
		return null;
		}
		
		
		
		public static  ByteArrayOutputStream createStudentTransferCertificate(StudentTCDetailsBO studentTCDetails, EmployeeBO currentUser, String daysheetstring) {
			
			  Document document;
			  document = new Document(PageSize.LEGAL, 20, 20, 20, 50);
	         ByteArrayOutputStream out = new ByteArrayOutputStream();
	         PdfPTable MainBdyTable = new PdfPTable(4);
	         Rectangle layout = new Rectangle(10, 20, 605, 1000);
	         PdfPTable HeaderTable = new PdfPTable(1);
	         PdfPTable mainHeaderTable = new PdfPTable(2);
	         PdfPTable BodyTable = new PdfPTable(6);
	         PdfPTable BodyFooterTable = new PdfPTable(6);
			try {

				SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
				Date date = new Date();

				LineSeparator ls = new LineSeparator();

				PdfPCell collODcell;
				PdfPCell collNcell;
				PdfPCell collAcell;
				PdfPCell collRCNcell;

				

				//PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(file));
				//TransFooter event = new TransFooter(currentUser);
				//writer.setPageEvent(event);

				
					// Create layout and set background & borders
					if (studentTCDetails != null) {
						
						layout.setBorderColor(BaseColor.BLACK);
						layout.setBorderWidthLeft(2f);
						layout.setBorderWidthRight(2f);
						layout.setBorderWidthTop(2f);
						layout.setBorderWidthBottom(2f);
						layout.setBorder(Rectangle.BOX);
					//	document.add(layout);

						PdfPCell spaceCell = new PdfPCell(new Phrase(" ", new Font(Font.FontFamily.TIMES_ROMAN, 10f, Font.BOLD, BaseColor.BLACK)));
						spaceCell.setBorder(Rectangle.NO_BORDER);
						
						
						
						String respath = "/static/assets/logo_scil1.png";
						InputStream logo = ExcelView.class.getResourceAsStream(respath);
						if (logo == null)
							throw new Exception("resource not found: " + respath);
						/* Read the input image into InputStream */
						/* Convert Image to byte array */
						byte[] bytes = IOUtils.toByteArray(logo);
						/* Add Picture to workbook and get a index for the picture */
						/* Close Input Stream */
						logo.close();
						
						
						Image image = Image.getInstance(bytes);
						
						image.scaleAbsolute(50, 50);

						
						mainHeaderTable.setWidthPercentage(100);
						float[] hColWidths1 = { 50f, 250f };
						mainHeaderTable.setWidths(hColWidths1);
						
						PdfPCell imgcell;
						
						
						
						
						
						
						

						imgcell = new PdfPCell(image, false);
						imgcell.setRowspan(3);
						imgcell.setPaddingLeft(1f);
						imgcell.setBorderWidthLeft(1f);
						imgcell.setBorderColorLeft(BaseColor.WHITE);
						imgcell.setPaddingRight(0f);
						imgcell.setBorderWidthRight(0f);
						imgcell.setBorderColorRight(null);
						imgcell.setPaddingTop(1f);
						imgcell.setBorderWidthTop(1f);
						imgcell.setBorderColorTop(BaseColor.WHITE);
						imgcell.setPaddingBottom(1f);
						imgcell.setBorderWidthBottom(1f);
						imgcell.setBorderColorBottom(BaseColor.WHITE);
						imgcell.setHorizontalAlignment(Element.ALIGN_CENTER);
						mainHeaderTable.addCell(imgcell);

						collNcell = new PdfPCell(
								new Phrase("SRI CHAITANYA EDUCATIONAL INSTITUTIONS", new Font(Font.FontFamily.TIMES_ROMAN, 20f, Font.BOLD, BaseColor.BLACK)));
						collNcell.setUseBorderPadding(true);
						collNcell.setPaddingLeft(0f);
						collNcell.setBorderWidthLeft(0f);
						collNcell.setBorderColorLeft(null);
						collNcell.setPaddingRight(1f);
						collNcell.setBorderWidthRight(1f);
						collNcell.setBorderColorRight(BaseColor.WHITE);
						collNcell.setPaddingTop(1f);
						collNcell.setBorderWidthTop(1f);
						collNcell.setBorderColorTop(BaseColor.WHITE);
						collNcell.setPaddingBottom(0f);
						collNcell.setBorderWidthBottom(0f);
						collNcell.setBorderColorBottom(null);
						collNcell.setHorizontalAlignment(Element.ALIGN_CENTER);
						mainHeaderTable.addCell(collNcell);


						mainHeaderTable.completeRow();
						

						
						HeaderTable.setWidthPercentage(100);
						HeaderTable.setSpacingAfter(10);
						float[] hColWidths = { 250f };
						HeaderTable.setWidths(hColWidths);

						/*collODcell = new PdfPCell(new Phrase("ORIGINAL", new Font(Font.FontFamily.TIMES_ROMAN, 10f, Font.BOLD, BaseColor.BLACK)));
						collODcell.setUseBorderPadding(true);
						collODcell.setPaddingLeft(0f);
						collODcell.setBorderWidthLeft(0f);
						collODcell.setBorderColorLeft(null);
						collODcell.setPaddingRight(0f);
						collODcell.setBorderWidthRight(0f);
						collODcell.setBorderColorRight(BaseColor.BLACK);
						collODcell.setPaddingTop(0f);
						collODcell.setBorderWidthTop(0f);
						collODcell.setBorderColorTop(BaseColor.BLACK);
						collODcell.setPaddingBottom(0f);
						collODcell.setBorderWidthBottom(0f);
						collODcell.setBorderColorBottom(null);
						collODcell.setHorizontalAlignment(Element.ALIGN_RIGHT);
						HeaderTable.addCell(collODcell);*/

						collNcell = new PdfPCell(new Phrase("TRANSFER CERTIFICATE",
								new Font(Font.FontFamily.TIMES_ROMAN, 20f, Font.BOLD, BaseColor.BLACK)));
						collNcell.setUseBorderPadding(true);
						collNcell.setPaddingLeft(0f);
						collNcell.setBorderWidthLeft(0f);
						collNcell.setBorderColorLeft(null);
						collNcell.setPaddingRight(0f);
						collNcell.setBorderWidthRight(0f);
						collNcell.setBorderColorRight(BaseColor.BLACK);
						collNcell.setPaddingTop(0f);
						collNcell.setBorderWidthTop(0f);
						collNcell.setBorderColorTop(BaseColor.BLACK);
						collNcell.setPaddingBottom(0f);
						collNcell.setBorderWidthBottom(0f);
						collNcell.setBorderColorBottom(null);
						collNcell.setHorizontalAlignment(Element.ALIGN_CENTER);
						HeaderTable.addCell(collNcell);

						HeaderTable.completeRow();
						

						// document.add(new Paragraph(" "));

						

						// BodyTable.setSpacingAfter(0);
						// BodyTable.setSpacingBefore(0);
						BodyTable.setWidthPercentage(100);
						float[] ayColWidths = { 250f, 200f, 180f, 180f, 300f, 100f };
						BodyTable.setWidths(ayColWidths);
						
						
						PdfPCell dateCell = new PdfPCell(new Phrase("TC Number", new Font(Font.FontFamily.TIMES_ROMAN, 12f)));
						dateCell.setBorder(Rectangle.NO_BORDER);
						dateCell.setLeading(5, 1);
						BodyTable.addCell(dateCell);
						dateCell = new PdfPCell(new Phrase(":" + " " + studentTCDetails.getTcNo(),
								new Font(Font.FontFamily.TIMES_ROMAN, 12f, Font.BOLD, BaseColor.BLACK)));
						dateCell.setBorder(Rectangle.NO_BORDER);
						dateCell.setLeading(5, 1);
						BodyTable.addCell(dateCell);

						PdfPCell admNoCell = new PdfPCell(new Phrase("Category ", new Font(Font.FontFamily.TIMES_ROMAN, 12f)));
						admNoCell.setBorder(Rectangle.NO_BORDER);
						admNoCell.setLeading(5, 1);
						BodyTable.addCell(admNoCell);
						admNoCell = new PdfPCell(new Phrase(":" + " " + studentTCDetails.getCategory(),
								new Font(Font.FontFamily.TIMES_ROMAN, 12f, Font.BOLD, BaseColor.BLACK)));
						admNoCell.setBorder(Rectangle.NO_BORDER);
						admNoCell.setHorizontalAlignment(Element.ALIGN_LEFT);
						admNoCell.setLeading(5, 1);
						BodyTable.addCell(admNoCell);

						PdfPCell aYearCell = new PdfPCell(new Phrase("Admission No ", new Font(Font.FontFamily.TIMES_ROMAN, 12f)));
						aYearCell.setBorder(Rectangle.NO_BORDER);
						aYearCell.setLeading(5, 1);
						BodyTable.addCell(aYearCell);
						aYearCell = new PdfPCell(new Phrase(studentTCDetails.getAdmissionNo(),
								new Font(Font.FontFamily.TIMES_ROMAN, 12f, Font.BOLD, BaseColor.BLACK)));
						aYearCell.setBorder(Rectangle.NO_BORDER);
						aYearCell.setLeading(5, 1);
						BodyTable.addCell(aYearCell);

						

						BodyTable.completeRow();
						

						

						// MainBdyTable.setSpacingAfter(0);
						// MainBdyTable.setSpacingBefore(0);
						MainBdyTable.setWidthPercentage(100);
						float[] mtColWidths = { 40f, 600f, 30f, 500f };
						MainBdyTable.setWidths(mtColWidths);

						PdfPCell collName = new PdfPCell(new Phrase("1.", new Font(Font.FontFamily.TIMES_ROMAN, 12f)));
						collName.setBorder(Rectangle.NO_BORDER);
						collName.setLeading(5, 1);
						MainBdyTable.addCell(collName);
						collName = new PdfPCell(new Phrase("Student Name ", new Font(Font.FontFamily.TIMES_ROMAN, 12f)));
						collName.setBorder(Rectangle.NO_BORDER);
						collName.setNoWrap(false);
						collName.setLeading(5, 1);
						MainBdyTable.addCell(collName);
						collName = new PdfPCell(new Phrase(":", new Font(Font.FontFamily.TIMES_ROMAN, 10f, Font.BOLD, BaseColor.BLACK)));
						collName.setBorder(Rectangle.NO_BORDER);
						collName.setLeading(5, 1);
						MainBdyTable.addCell(collName);
						collName = new PdfPCell(
								new Phrase(studentTCDetails.getName(), new Font(Font.FontFamily.TIMES_ROMAN, 10f, Font.BOLD, BaseColor.BLACK)));
						collName.setBorder(Rectangle.NO_BORDER);
						collName.setNoWrap(false);
						collName.setLeading(5, 1);
						MainBdyTable.addCell(collName);


						PdfPCell fatherName = new PdfPCell(new Phrase('\n' + "2.", new Font(Font.FontFamily.TIMES_ROMAN, 12f)));
						fatherName.setBorder(Rectangle.NO_BORDER);
						MainBdyTable.addCell(fatherName);
						fatherName = new PdfPCell(new Phrase('\n' + "Father Name/Guardian Name ", new Font(Font.FontFamily.TIMES_ROMAN, 12f)));
						fatherName.setBorder(Rectangle.NO_BORDER);
						fatherName.setNoWrap(false);
						MainBdyTable.addCell(fatherName);
						fatherName = new PdfPCell(new Phrase('\n' + ":", new Font(Font.FontFamily.TIMES_ROMAN, 10f, Font.BOLD, BaseColor.BLACK)));
						fatherName.setBorder(Rectangle.NO_BORDER);
						MainBdyTable.addCell(fatherName);
						fatherName = new PdfPCell(new Phrase('\n' + studentTCDetails.getFatherName(),
								new Font(Font.FontFamily.TIMES_ROMAN, 10f, Font.BOLD, BaseColor.BLACK)));
						fatherName.setBorder(Rectangle.NO_BORDER);
						fatherName.setNoWrap(false);
						MainBdyTable.addCell(fatherName);

						PdfPCell motherName = new PdfPCell(new Phrase('\n' + "3.", new Font(Font.FontFamily.TIMES_ROMAN, 12f)));
						motherName.setBorder(Rectangle.NO_BORDER);
						MainBdyTable.addCell(motherName);
						motherName = new PdfPCell(new Phrase('\n' + "Mother Name ", new Font(Font.FontFamily.TIMES_ROMAN, 12f)));
						motherName.setBorder(Rectangle.NO_BORDER);
						motherName.setNoWrap(false);
						MainBdyTable.addCell(motherName);
						motherName = new PdfPCell(new Phrase('\n' + ":", new Font(Font.FontFamily.TIMES_ROMAN, 10f, Font.BOLD, BaseColor.BLACK)));
						motherName.setBorder(Rectangle.NO_BORDER);
						MainBdyTable.addCell(motherName);
						motherName = new PdfPCell(new Phrase('\n' + studentTCDetails.getMotherName(),
								new Font(Font.FontFamily.TIMES_ROMAN, 10f, Font.BOLD, BaseColor.BLACK)));
						motherName.setBorder(Rectangle.NO_BORDER);
						motherName.setNoWrap(false);
						MainBdyTable.addCell(motherName);

						PdfPCell stNatRel = new PdfPCell(new Phrase('\n' + "4.", new Font(Font.FontFamily.TIMES_ROMAN, 12f)));
						stNatRel.setBorder(Rectangle.NO_BORDER);
						MainBdyTable.addCell(stNatRel);
						stNatRel = new PdfPCell(new Phrase('\n' + "Nationality and Religion ", new Font(Font.FontFamily.TIMES_ROMAN, 12f)));
						stNatRel.setBorder(Rectangle.NO_BORDER);
						stNatRel.setNoWrap(false);
						MainBdyTable.addCell(stNatRel);
						stNatRel = new PdfPCell(new Phrase('\n' + ":", new Font(Font.FontFamily.TIMES_ROMAN, 10f, Font.BOLD, BaseColor.BLACK)));
						stNatRel.setBorder(Rectangle.NO_BORDER);
						MainBdyTable.addCell(stNatRel);
						stNatRel = new PdfPCell(new Phrase('\n' + studentTCDetails.getNationalityAndReligion(),
								new Font(Font.FontFamily.TIMES_ROMAN, 10f, Font.BOLD, BaseColor.BLACK)));
						stNatRel.setBorder(Rectangle.NO_BORDER);
						stNatRel.setNoWrap(false);
						MainBdyTable.addCell(stNatRel);

						PdfPCell candBelongs = new PdfPCell(new Phrase('\n' + "5.", new Font(Font.FontFamily.TIMES_ROMAN, 12f)));
						candBelongs.setBorder(Rectangle.NO_BORDER);
						MainBdyTable.addCell(candBelongs);
						candBelongs = new PdfPCell(
								new Phrase('\n' + "Whether Candidate Belongs to SC/ST/OBC ",
										new Font(Font.FontFamily.TIMES_ROMAN, 12f)));
						candBelongs.setBorder(Rectangle.NO_BORDER);
						candBelongs.setNoWrap(false);
						MainBdyTable.addCell(candBelongs);
						candBelongs = new PdfPCell(new Phrase('\n' + ":", new Font(Font.FontFamily.TIMES_ROMAN, 10f, Font.BOLD, BaseColor.BLACK)));
						candBelongs.setBorder(Rectangle.NO_BORDER);
						MainBdyTable.addCell(candBelongs);
						candBelongs = new PdfPCell(new Phrase('\n' + studentTCDetails.getStudentCast(),
								new Font(Font.FontFamily.TIMES_ROMAN, 10f, Font.BOLD, BaseColor.BLACK)));
						candBelongs.setBorder(Rectangle.NO_BORDER);
						candBelongs.setNoWrap(false);
						MainBdyTable.addCell(candBelongs);

						PdfPCell stDateOfAdmission = new PdfPCell(new Phrase('\n' + "6.", new Font(Font.FontFamily.TIMES_ROMAN, 10f)));
						stDateOfAdmission.setBorder(Rectangle.NO_BORDER);
						MainBdyTable.addCell(stDateOfAdmission);
						stDateOfAdmission = new PdfPCell(
								new Phrase('\n' + "Date of Admission in the school with class ", new Font(Font.FontFamily.TIMES_ROMAN, 10f)));
						stDateOfAdmission.setBorder(Rectangle.NO_BORDER);
						stDateOfAdmission.setNoWrap(false);
						MainBdyTable.addCell(stDateOfAdmission);
						stDateOfAdmission = new PdfPCell(new Phrase('\n' + ":", new Font(Font.FontFamily.TIMES_ROMAN, 8f, Font.BOLD, BaseColor.BLACK)));
						stDateOfAdmission.setBorder(Rectangle.NO_BORDER);
						MainBdyTable.addCell(stDateOfAdmission);
						stDateOfAdmission = new PdfPCell(new Phrase(
								'\n' + formatter.format(studentTCDetails.getDateOfAdmission()) + " " + "("
										+ DU.DateToWords(DU.format(studentTCDetails.getStudentDob(), "dd-MM-yyyy")) + ")",
								new Font(Font.FontFamily.TIMES_ROMAN, 8f, Font.BOLD, BaseColor.BLACK)));
						stDateOfAdmission.setBorder(Rectangle.NO_BORDER);
						stDateOfAdmission.setNoWrap(false);
						MainBdyTable.addCell(stDateOfAdmission);
						
						PdfPCell stDOB = new PdfPCell(new Phrase('\n' + "7.", new Font(Font.FontFamily.TIMES_ROMAN, 10f)));
						stDOB.setBorder(Rectangle.NO_BORDER);
						MainBdyTable.addCell(stDOB);
						stDOB = new PdfPCell(
								new Phrase('\n' + "Date of Birth(In Christian Era) according to Admission Register ", new Font(Font.FontFamily.TIMES_ROMAN, 10f)));
						stDOB.setBorder(Rectangle.NO_BORDER);
						stDOB.setNoWrap(false);
						MainBdyTable.addCell(stDOB);
						stDOB = new PdfPCell(new Phrase('\n' + ":", new Font(Font.FontFamily.TIMES_ROMAN, 8f, Font.BOLD, BaseColor.BLACK)));
						stDOB.setBorder(Rectangle.NO_BORDER);
						MainBdyTable.addCell(stDOB);
						stDOB = new PdfPCell(new Phrase(
								'\n' + formatter.format(studentTCDetails.getStudentDob()) + " " + "("
										+ DU.DateToWords(DU.format(studentTCDetails.getStudentDob(), "dd-MM-yyyy")) + ")",
										new Font(Font.FontFamily.TIMES_ROMAN, 8f, Font.BOLD, BaseColor.BLACK)));
						stDOB.setBorder(Rectangle.NO_BORDER);
						stDOB.setNoWrap(false);
						MainBdyTable.addCell(stDOB);

						PdfPCell stCurClass = new PdfPCell(new Phrase('\n' + "8.", new Font(Font.FontFamily.TIMES_ROMAN, 12f)));
						stCurClass.setBorder(Rectangle.NO_BORDER);
						MainBdyTable.addCell(stCurClass);
						stCurClass = new PdfPCell(new Phrase('\n' + "a) Class in which the student last studied/is studying ",
								new Font(Font.FontFamily.TIMES_ROMAN, 12f)));
						stCurClass.setBorder(Rectangle.NO_BORDER);
						stCurClass.setNoWrap(false);
						MainBdyTable.addCell(stCurClass);
						stDOB = new PdfPCell(new Phrase('\n' + ":", new Font(Font.FontFamily.TIMES_ROMAN, 10f, Font.BOLD, BaseColor.BLACK)));
						stDOB.setBorder(Rectangle.NO_BORDER);
						MainBdyTable.addCell(stDOB);

						String classLeaving = studentTCDetails.getClassOfLeaving();
						stCurClass = new PdfPCell(
								new Phrase('\n' + classLeaving, new Font(Font.FontFamily.TIMES_ROMAN, 10f, Font.BOLD, BaseColor.BLACK)));
						stCurClass.setBorder(Rectangle.NO_BORDER);
						stCurClass.setNoWrap(false);
						MainBdyTable.addCell(stCurClass);

						PdfPCell stFLangP1 = new PdfPCell(new Phrase('\n' + "9.", new Font(Font.FontFamily.TIMES_ROMAN, 12f)));
						stFLangP1.setBorder(Rectangle.NO_BORDER);
						MainBdyTable.addCell(stFLangP1);
						stFLangP1 = new PdfPCell(new Phrase('\n' + "School/Board Annual Exams last taken with result ", new Font(Font.FontFamily.TIMES_ROMAN, 12f)));
						stFLangP1.setBorder(Rectangle.NO_BORDER);
						stFLangP1.setNoWrap(false);
						MainBdyTable.addCell(stFLangP1);
						stFLangP1 = new PdfPCell(new Phrase('\n' + ":", new Font(Font.FontFamily.TIMES_ROMAN, 10f, Font.BOLD, BaseColor.BLACK)));
						stFLangP1.setBorder(Rectangle.NO_BORDER);
						MainBdyTable.addCell(stFLangP1);
						stFLangP1 = new PdfPCell(new Phrase('\n' + studentTCDetails.getAnnualExamsLastTaken(),
								new Font(Font.FontFamily.TIMES_ROMAN, 10f, Font.BOLD, BaseColor.BLACK)));
						stFLangP1.setBorder(Rectangle.NO_BORDER);
						stFLangP1.setNoWrap(false);
						MainBdyTable.addCell(stFLangP1);

						PdfPCell stSLangP2 = new PdfPCell(new Phrase('\n' + "10.", new Font(Font.FontFamily.TIMES_ROMAN, 12f)));
						stSLangP2.setBorder(Rectangle.NO_BORDER);
						MainBdyTable.addCell(stSLangP2);
						stSLangP2 = new PdfPCell(new Phrase('\n' + "Whether failed.if so once/twice in same class ", new Font(Font.FontFamily.TIMES_ROMAN, 12f)));
						stSLangP2.setBorder(Rectangle.NO_BORDER);
						stSLangP2.setNoWrap(false);
						MainBdyTable.addCell(stSLangP2);
						stSLangP2 = new PdfPCell(new Phrase('\n' + ":", new Font(Font.FontFamily.TIMES_ROMAN, 10f, Font.BOLD, BaseColor.BLACK)));
						stSLangP2.setBorder(Rectangle.NO_BORDER);
						MainBdyTable.addCell(stSLangP2);
						stSLangP2 = new PdfPCell(new Phrase('\n' + studentTCDetails.getOnceOrtwiceinSameClass(),
								new Font(Font.FontFamily.TIMES_ROMAN, 10f, Font.BOLD, BaseColor.BLACK)));
						stSLangP2.setBorder(Rectangle.NO_BORDER);
						stSLangP2.setNoWrap(false);
						MainBdyTable.addCell(stSLangP2);

						PdfPCell stOptP3 = new PdfPCell(new Phrase('\n' + "11.", new Font(Font.FontFamily.TIMES_ROMAN, 12f)));
						stOptP3.setBorder(Rectangle.NO_BORDER);
						MainBdyTable.addCell(stOptP3);
						stOptP3 = new PdfPCell(new Phrase('\n' + "Subjects Studied : ", new Font(Font.FontFamily.TIMES_ROMAN, 12f)));
						stOptP3.setBorder(Rectangle.NO_BORDER);
						stOptP3.setNoWrap(false);
						MainBdyTable.addCell(stOptP3);
						
						stOptP3 = new PdfPCell(new Phrase("", new Font(Font.FontFamily.TIMES_ROMAN, 12f)));
						stOptP3.setBorder(Rectangle.NO_BORDER);
						stOptP3.setNoWrap(false);
						MainBdyTable.addCell(stOptP3);
						
						
						PdfPTable table = new PdfPTable(3); // 3 columns.

			            PdfPCell cell1 = new PdfPCell(new Paragraph(studentTCDetails.getSubject1()));
			            PdfPCell cell2 = new PdfPCell(new Paragraph(studentTCDetails.getSubject2()));
			            PdfPCell cell3 = new PdfPCell(new Paragraph(studentTCDetails.getSubject3()));
			            
			            PdfPCell cell4 = new PdfPCell(new Paragraph(studentTCDetails.getSubject4()));
			            PdfPCell cell5 = new PdfPCell(new Paragraph(studentTCDetails.getSubject5()));
			            PdfPCell cell6 = new PdfPCell(new Paragraph());

			            table.addCell(cell1);
			            table.addCell(cell2);
			            table.addCell(cell3); 
			            table.addCell(cell4);
			            table.addCell(cell5);
			            table.addCell(cell6); 
			            
			            stOptP3 = new PdfPCell(table);
						stOptP3.setBorder(Rectangle.NO_BORDER);
						stOptP3.setNoWrap(false);
						MainBdyTable.addCell(stOptP3);
						

						PdfPCell stMTMedium = new PdfPCell(new Phrase('\n' + "12.", new Font(Font.FontFamily.TIMES_ROMAN, 12f)));
						stMTMedium.setBorder(Rectangle.NO_BORDER);
						MainBdyTable.addCell(stMTMedium);
						stMTMedium = new PdfPCell(
								new Phrase('\n' + "Whether qualifier for promotion to higher class if so,which class ", new Font(Font.FontFamily.TIMES_ROMAN, 12f)));
						stMTMedium.setBorder(Rectangle.NO_BORDER);
						stMTMedium.setNoWrap(false);
						MainBdyTable.addCell(stMTMedium);
						stMTMedium = new PdfPCell(new Phrase('\n' + ":", new Font(Font.FontFamily.TIMES_ROMAN, 10f, Font.BOLD, BaseColor.BLACK)));
						stMTMedium.setBorder(Rectangle.NO_BORDER);
						MainBdyTable.addCell(stMTMedium);
						stMTMedium = new PdfPCell(new Phrase('\n' + studentTCDetails.getQualifierforPromotiontoHigherClass(),
								new Font(Font.FontFamily.TIMES_ROMAN, 10f, Font.BOLD, BaseColor.BLACK)));
						stMTMedium.setBorder(Rectangle.NO_BORDER);
						stMTMedium.setNoWrap(false);
						MainBdyTable.addCell(stMTMedium);

						PdfPCell joinDate = new PdfPCell(new Phrase('\n' + "13.", new Font(Font.FontFamily.TIMES_ROMAN, 12f)));
						joinDate.setBorder(Rectangle.NO_BORDER);
						MainBdyTable.addCell(joinDate);
						joinDate = new PdfPCell(new Phrase('\n' + "Month upto which the student has paid school dues;paid upto ",
								new Font(Font.FontFamily.TIMES_ROMAN, 12f)));
						joinDate.setBorder(Rectangle.NO_BORDER);
						joinDate.setNoWrap(false);
						MainBdyTable.addCell(joinDate);
						joinDate = new PdfPCell(new Phrase('\n' + ":", new Font(Font.FontFamily.TIMES_ROMAN, 10f, Font.BOLD, BaseColor.BLACK)));
						joinDate.setBorder(Rectangle.NO_BORDER);
						MainBdyTable.addCell(joinDate);
						joinDate = new PdfPCell(new Phrase('\n' + studentTCDetails.getPaidSchoolDues(),
								new Font(Font.FontFamily.TIMES_ROMAN, 10f, Font.BOLD, BaseColor.BLACK)));
						joinDate.setBorder(Rectangle.NO_BORDER);
						joinDate.setNoWrap(false);
						MainBdyTable.addCell(joinDate);

						PdfPCell joinClassYear = new PdfPCell(new Phrase('\n' + "14.", new Font(Font.FontFamily.TIMES_ROMAN, 12f)));
						joinClassYear.setBorder(Rectangle.NO_BORDER);
						MainBdyTable.addCell(joinClassYear);
						joinClassYear = new PdfPCell(new Phrase(
								'\n' + "Any Fee Concession availed of; if so,the nature of such concession ",
								new Font(Font.FontFamily.TIMES_ROMAN, 12f)));
						joinClassYear.setBorder(Rectangle.NO_BORDER);
						joinClassYear.setNoWrap(false);
						MainBdyTable.addCell(joinClassYear);
						joinClassYear = new PdfPCell(new Phrase('\n' + ":", new Font(Font.FontFamily.TIMES_ROMAN, 10f, Font.BOLD, BaseColor.BLACK)));
						joinClassYear.setBorder(Rectangle.NO_BORDER);
						MainBdyTable.addCell(joinClassYear);

						String classAdmission = studentTCDetails.getConcessionAvailed();


						joinClassYear = new PdfPCell(
								new Phrase('\n' + classAdmission, new Font(Font.FontFamily.TIMES_ROMAN, 10f, Font.BOLD, BaseColor.BLACK)));
						joinClassYear.setBorder(Rectangle.NO_BORDER);
						joinClassYear.setNoWrap(false);
						MainBdyTable.addCell(joinClassYear);

						PdfPCell stQuaNextYear = new PdfPCell(new Phrase('\n' + "15.", new Font(Font.FontFamily.TIMES_ROMAN, 12f)));
						stQuaNextYear.setBorder(Rectangle.NO_BORDER);
						MainBdyTable.addCell(stQuaNextYear);
						stQuaNextYear = new PdfPCell(new Phrase(
								'\n' + "Total No.of Working Days ",
								new Font(Font.FontFamily.TIMES_ROMAN, 12f)));
						stQuaNextYear.setBorder(Rectangle.NO_BORDER);
						stQuaNextYear.setNoWrap(false);
						MainBdyTable.addCell(stQuaNextYear);
						stQuaNextYear = new PdfPCell(new Phrase('\n' + ":", new Font(Font.FontFamily.TIMES_ROMAN, 10f, Font.BOLD, BaseColor.BLACK)));
						stQuaNextYear.setBorder(Rectangle.NO_BORDER);
						MainBdyTable.addCell(stQuaNextYear);
						stQuaNextYear = new PdfPCell(new Phrase('\n' + studentTCDetails.getNoOfWorkingDays(),
								new Font(Font.FontFamily.TIMES_ROMAN, 10f, Font.BOLD, BaseColor.BLACK)));
						stQuaNextYear.setBorder(Rectangle.NO_BORDER);
						stQuaNextYear.setNoWrap(false);
						MainBdyTable.addCell(stQuaNextYear);

						PdfPCell stEligble = new PdfPCell(new Phrase('\n' + "16.", new Font(Font.FontFamily.TIMES_ROMAN, 12f)));
						stEligble.setBorder(Rectangle.NO_BORDER);
						MainBdyTable.addCell(stEligble);
						stEligble = new PdfPCell(new Phrase(
								'\n' + "Total No.of Working Days Present ",
								new Font(Font.FontFamily.TIMES_ROMAN, 12f)));
						stEligble.setBorder(Rectangle.NO_BORDER);
						stEligble.setNoWrap(false);
						MainBdyTable.addCell(stEligble);
						stEligble = new PdfPCell(new Phrase('\n' + ":", new Font(Font.FontFamily.TIMES_ROMAN, 10f, Font.BOLD, BaseColor.BLACK)));
						stEligble.setBorder(Rectangle.NO_BORDER);
						MainBdyTable.addCell(stEligble);
						stEligble = new PdfPCell(new Phrase('\n' + studentTCDetails.getNoOfWorkingDaysPresent(),
								new Font(Font.FontFamily.TIMES_ROMAN, 10f, Font.BOLD, BaseColor.BLACK)));
						stEligble.setBorder(Rectangle.NO_BORDER);
						stEligble.setNoWrap(false);
						MainBdyTable.addCell(stEligble);

						PdfPCell stSchCon = new PdfPCell(new Phrase('\n' + "17.", new Font(Font.FontFamily.TIMES_ROMAN, 12f)));
						stSchCon.setBorder(Rectangle.NO_BORDER);
						MainBdyTable.addCell(stSchCon);
						stSchCon = new PdfPCell(
								new Phrase('\n' + "Co-curricular activities in which the student usually took part(mention achievements level there in) ",
										new Font(Font.FontFamily.TIMES_ROMAN, 12f)));
						stSchCon.setBorder(Rectangle.NO_BORDER);
						stSchCon.setNoWrap(false);
						MainBdyTable.addCell(stSchCon);
						stSchCon = new PdfPCell(new Phrase('\n' + ":", new Font(Font.FontFamily.TIMES_ROMAN, 10f, Font.BOLD, BaseColor.BLACK)));
						stSchCon.setBorder(Rectangle.NO_BORDER);
						MainBdyTable.addCell(stSchCon);
						stSchCon = new PdfPCell(new Phrase(
								'\n' + studentTCDetails.getCoCcurricularActivities(),
								new Font(Font.FontFamily.TIMES_ROMAN, 10f, Font.BOLD, BaseColor.BLACK)));
						stSchCon.setBorder(Rectangle.NO_BORDER);
						stSchCon.setNoWrap(false);
						MainBdyTable.addCell(stSchCon);


						PdfPCell stPerMarks = new PdfPCell(new Phrase('\n' + "18.", new Font(Font.FontFamily.TIMES_ROMAN, 12f)));
						stPerMarks.setBorder(Rectangle.NO_BORDER);
						MainBdyTable.addCell(stPerMarks);
						stPerMarks = new PdfPCell(new Phrase('\n' + "General Conduct ", new Font(Font.FontFamily.TIMES_ROMAN, 12f)));
						stPerMarks.setBorder(Rectangle.NO_BORDER);
						stPerMarks.setNoWrap(false);
						MainBdyTable.addCell(stPerMarks);
						stPerMarks = new PdfPCell(new Phrase('\n' + ":", new Font(Font.FontFamily.TIMES_ROMAN, 12f)));
						stPerMarks.setBorder(Rectangle.NO_BORDER);
						MainBdyTable.addCell(stPerMarks);
						stPerMarks = new PdfPCell(new Phrase('\n' + "(1)" + "  " + studentTCDetails.getConduct(),
								new Font(Font.FontFamily.TIMES_ROMAN, 10f, Font.BOLD, BaseColor.BLACK)));
						stPerMarks.setBorder(Rectangle.NO_BORDER);
						stPerMarks.setNoWrap(false);
						MainBdyTable.addCell(stPerMarks);

						PdfPCell stLeftDate = new PdfPCell(new Phrase('\n' + "19", new Font(Font.FontFamily.TIMES_ROMAN, 12f)));
						stLeftDate.setBorder(Rectangle.NO_BORDER);
						MainBdyTable.addCell(stLeftDate);
						stLeftDate = new PdfPCell(
								new Phrase('\n' + "Date of Applied of Transfer Certificate ", new Font(Font.FontFamily.TIMES_ROMAN, 12f)));
						stLeftDate.setBorder(Rectangle.NO_BORDER);
						stLeftDate.setNoWrap(false);
						MainBdyTable.addCell(stLeftDate);
						stLeftDate = new PdfPCell(new Phrase('\n' + ":", new Font(Font.FontFamily.TIMES_ROMAN, 12f)));
						stLeftDate.setBorder(Rectangle.NO_BORDER);
						MainBdyTable.addCell(stLeftDate);
						stLeftDate = new PdfPCell(new Phrase('\n' + formatter.format(studentTCDetails.getDateOfAppliedTransferCertificate()),
								new Font(Font.FontFamily.TIMES_ROMAN, 10f, Font.BOLD, BaseColor.BLACK)));
						stLeftDate.setBorder(Rectangle.NO_BORDER);
						stLeftDate.setNoWrap(false);
						MainBdyTable.addCell(stLeftDate);

						PdfPCell stTransDate = new PdfPCell(new Phrase('\n' + "20.", new Font(Font.FontFamily.TIMES_ROMAN, 12f)));
						stTransDate.setBorder(Rectangle.NO_BORDER);
						MainBdyTable.addCell(stTransDate);
						stTransDate = new PdfPCell(new Phrase('\n' + "Date of Issue of Transfer Certificate ", new Font(Font.FontFamily.TIMES_ROMAN, 12f)));
						stTransDate.setBorder(Rectangle.NO_BORDER);
						stTransDate.setNoWrap(false);
						MainBdyTable.addCell(stTransDate);
						stTransDate = new PdfPCell(new Phrase('\n' + ":", new Font(Font.FontFamily.TIMES_ROMAN, 12f)));
						stTransDate.setBorder(Rectangle.NO_BORDER);
						MainBdyTable.addCell(stTransDate);
						stTransDate = new PdfPCell(new Phrase('\n' + formatter.format(studentTCDetails.getDateOfIssuTransferCertificate()),
								new Font(Font.FontFamily.TIMES_ROMAN, 10f, Font.BOLD, BaseColor.BLACK)));
						stTransDate.setBorder(Rectangle.NO_BORDER);
						stTransDate.setNoWrap(false);
						MainBdyTable.addCell(stTransDate);

						PdfPCell stConduct = new PdfPCell(new Phrase('\n' + "21.", new Font(Font.FontFamily.TIMES_ROMAN, 12f)));
						stConduct.setBorder(Rectangle.NO_BORDER);
						MainBdyTable.addCell(stConduct);
						stConduct = new PdfPCell(new Phrase('\n' + "Reason For Leaving the school ", new Font(Font.FontFamily.TIMES_ROMAN, 12f)));
						stConduct.setBorder(Rectangle.NO_BORDER);
						stConduct.setNoWrap(false);
						MainBdyTable.addCell(stConduct);
						stConduct = new PdfPCell(new Phrase('\n' + ":", new Font(Font.FontFamily.TIMES_ROMAN, 12f)));
						stConduct.setBorder(Rectangle.NO_BORDER);
						MainBdyTable.addCell(stConduct);
						stConduct = new PdfPCell(
								new Phrase('\n' + studentTCDetails.getReasonForLeaving(), new Font(Font.FontFamily.TIMES_ROMAN, 10f, Font.BOLD, BaseColor.BLACK)));
						stConduct.setBorder(Rectangle.NO_BORDER);
						stConduct.setNoWrap(false);
						MainBdyTable.addCell(stConduct);
						
						
						PdfPCell anyOtherReason = new PdfPCell(new Phrase('\n' + "22.", new Font(Font.FontFamily.TIMES_ROMAN, 12f)));
						anyOtherReason.setBorder(Rectangle.NO_BORDER);
						MainBdyTable.addCell(anyOtherReason);
						anyOtherReason = new PdfPCell(new Phrase('\n' + "Any Other Remarks ", new Font(Font.FontFamily.TIMES_ROMAN, 12f)));
						anyOtherReason.setBorder(Rectangle.NO_BORDER);
						anyOtherReason.setNoWrap(false);
						MainBdyTable.addCell(anyOtherReason);
						anyOtherReason = new PdfPCell(new Phrase('\n' + ":", new Font(Font.FontFamily.TIMES_ROMAN, 12f)));
						anyOtherReason.setBorder(Rectangle.NO_BORDER);
						MainBdyTable.addCell(anyOtherReason);
						anyOtherReason = new PdfPCell(
								new Phrase('\n' + studentTCDetails.getAnyOtherRemarks(), new Font(Font.FontFamily.TIMES_ROMAN, 10f, Font.BOLD, BaseColor.BLACK)));
						anyOtherReason.setBorder(Rectangle.NO_BORDER);
						anyOtherReason.setNoWrap(false);
						MainBdyTable.addCell(anyOtherReason);

						MainBdyTable.completeRow();
					}
				
					
					float[] ayColWidths =  { 100f, 100f, 100f, 100f, 100f, 100f };
					BodyFooterTable.setWidths(ayColWidths);
					

					PdfPCell admNoCell = new PdfPCell(new Phrase("Signature Of Class Teacher", new Font(Font.FontFamily.TIMES_ROMAN, 12f, Font.BOLD, BaseColor.BLACK)));
					admNoCell.setBorder(Rectangle.NO_BORDER);
					admNoCell.setNoWrap(true);
					admNoCell.setLeading(5, 1);
					BodyFooterTable.addCell(admNoCell);
					
					admNoCell = new PdfPCell(new Phrase("", new Font(Font.FontFamily.TIMES_ROMAN, 12f, Font.BOLD, BaseColor.BLACK)));
					admNoCell.setBorder(Rectangle.NO_BORDER);
					admNoCell.setLeading(5, 1);
					BodyFooterTable.addCell(admNoCell);
					

					PdfPCell aYearCell = new PdfPCell(new Phrase("", new Font(Font.FontFamily.TIMES_ROMAN, 12f, Font.BOLD, BaseColor.BLACK)));
					aYearCell.setBorder(Rectangle.NO_BORDER);
					aYearCell.setLeading(5, 1);
					BodyFooterTable.addCell(aYearCell);
					
					aYearCell = new PdfPCell(new Phrase("Checked By", new Font(Font.FontFamily.TIMES_ROMAN, 12f, Font.BOLD, BaseColor.BLACK)));
					aYearCell.setBorder(Rectangle.NO_BORDER);
					aYearCell.setLeading(5, 1);
					BodyFooterTable.addCell(aYearCell);

					PdfPCell dateCell = new PdfPCell(new Phrase("", new Font(Font.FontFamily.TIMES_ROMAN, 12f, Font.BOLD, BaseColor.BLACK)));
					dateCell.setBorder(Rectangle.NO_BORDER);
					dateCell.setLeading(4, 1);
					BodyFooterTable.addCell(dateCell);
					
					dateCell = new PdfPCell(new Phrase("Principal Signature", new Font(Font.FontFamily.TIMES_ROMAN, 12f, Font.BOLD, BaseColor.BLACK)));
					dateCell.setBorder(Rectangle.NO_BORDER);
					dateCell.setLeading(4, 1);
					dateCell.setNoWrap(true);
					BodyFooterTable.addCell(dateCell);

					BodyFooterTable.completeRow();
	                
					
				PdfWriter.getInstance(document, out);
				document.open();
				document.add(layout);
				document.add(mainHeaderTable);
				document.add(HeaderTable);
				document.add(BodyTable);

				document.add(new Paragraph('\n'));

				document.add(new Chunk(ls));

				document.add(new Paragraph('\n'));
				document.add(MainBdyTable);
				 document.add( Chunk.NEWLINE );
				 document.add( Chunk.NEWLINE );
				 document.add( Chunk.NEWLINE );
				document.add(BodyFooterTable);
				
				document.close();
				
			} catch (DocumentException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return out;
		}
		
		

}
