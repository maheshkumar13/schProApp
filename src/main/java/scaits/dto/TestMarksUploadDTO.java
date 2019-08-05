package scaits.dto;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import scaits.bo.common.TestBO;
import scaits.bo.student.StudyClassBO;
import scaits.util.DU;
import scaits.util.Constants.FormatDates;

public class TestMarksUploadDTO {
	
	//information feilds
	
	private Date testDate;
	private String testDateStr;
	
	private String testTypeVal;
	
	private TestBO testVal;
	private StudyClassBO studyClass;
	private MultipartFile uploadFile;

	public Date getTestDate() {
		return testDate;
	}

	public void setTestDate(Date testDate) {
		this.testDate = testDate;
	}

	public String getTestDateStr() {
		return testDateStr;
	}

	public void setTestDateStr(String testDateStr) {
		
		this.testDateStr = testDateStr;
		if(testDateStr==null || testDateStr=="" || testDateStr.length()==0) {
			this.testDate = new Date();
		}else {
			this.testDate = DU.parse(testDateStr, FormatDates.DATEFORMAT);
		}
		
	}

	public TestBO getTestVal() {
		return testVal;
	}

	public void setTestVal(TestBO testVal) {
		this.testVal = testVal;
	}

	public MultipartFile getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(MultipartFile uploadFile) {
		this.uploadFile = uploadFile;
	}

	public StudyClassBO getStudyClass() {
		return studyClass;
	}

	public void setStudyClass(StudyClassBO studyClass) {
		this.studyClass = studyClass;
	}

	public String getTestTypeVal() {
		return testTypeVal;
	}

	public void setTestTypeVal(String testTypeVal) {
		this.testTypeVal = testTypeVal;
	}
	
	
	
	
	
	
}
