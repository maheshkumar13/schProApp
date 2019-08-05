package scaits.util;

import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.SessionAttributes;

import scaits.bo.employee.EmployeeBO;
import scaits.repository.EmployeeRepository;

@Component
@SessionAttributes(value = "employee")
public class EmployeeAction {

	@Autowired
	EmployeeRepository employeeRepository;

	public EmployeeBO currentUser = null;
	
	public EmployeeBO getCurrentUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (currentUser != null) {
			// System.out.println("StudentAction :" +auth.getName());
			currentUser = employeeRepository.findByPayRollId(auth.getName());
		}
		return currentUser;
	}

	public void setCurrentUser(EmployeeBO currentUser) {
		this.currentUser = currentUser;
	}
	
	
	public Map<Long, String> getAttendancesTime() {
		Map<Long, String> AttendanceTime = new TreeMap<Long, String>();
		AttendanceTime.put(15l, "15 Minutes");
		AttendanceTime.put(30l, "30 Minutes");
		AttendanceTime.put(45l, "45 Minutes");

		AttendanceTime.put(60l, "1 Hr");
		AttendanceTime.put(75l, "1 Hr 15 Minutes");
		AttendanceTime.put(90l, "1 Hr 30 Minutes");
		AttendanceTime.put(105l, "1 Hr 45 Minutes");

		AttendanceTime.put(120l, "2 Hrs");
		AttendanceTime.put(135l, "2 Hrs 15 Minutes");
		AttendanceTime.put(150l, "2 Hrs 30 Minutes");
		AttendanceTime.put(165l, "2 Hrs 45 Minutes");
		AttendanceTime.put(180l, "3 Hrs");

		AttendanceTime.put(195l, "3 Hrs 15 Minutes");
		AttendanceTime.put(210l, "3 Hrs 30 Minutes");
		AttendanceTime.put(225l, "3 Hrs 45 Minutes");
		AttendanceTime.put(240l, "4 Hrs");

		AttendanceTime.put(255l, "4 Hrs 15 Minutes");
		AttendanceTime.put(270l, "4 Hrs 30 Minutes");
		AttendanceTime.put(285l, "4 Hrs 45 Minutes");
		AttendanceTime.put(300l, "5 Hrs");

		AttendanceTime.put(315l, "5 Hrs 15 Minutes");
		AttendanceTime.put(330l, "5 Hrs 30 Minutes");
		AttendanceTime.put(345l, "5 Hrs 45 Minutes");
		AttendanceTime.put(360l, "6 Hrs");

		AttendanceTime.put(375l, "6 Hrs 15 Minutes");
		AttendanceTime.put(390l, "6 Hrs 30 Minutes");
		AttendanceTime.put(405l, "6 Hrs 45 Minutes");
		AttendanceTime.put(420l, "7 Hrs");

		AttendanceTime.put(435l, "7 Hrs 15 Minutes");
		AttendanceTime.put(450l, "7 Hrs 30 Minutes");
		AttendanceTime.put(465l, "7 Hrs 45 Minutes");
		AttendanceTime.put(480l, "8 Hrs");

		AttendanceTime.put(495l, "8 Hrs 15 Minutes");
		AttendanceTime.put(510l, "8 Hrs 30 Minutes");
		AttendanceTime.put(525l, "8 Hrs 45 Minutes");
		AttendanceTime.put(540l, "9 Hrs");
		return AttendanceTime;
	}

	public Map<String, String> getAttendanceTimeData() {
		Map<String, String> AttendanceTimeData = new TreeMap<String, String>();
		AttendanceTimeData.put("00:00", "00:00");
		AttendanceTimeData.put("00:15", "00:15");
		AttendanceTimeData.put("00:30", "00:30");
		AttendanceTimeData.put("00:45", "00:45");
		AttendanceTimeData.put("01:00", "01:00");
		AttendanceTimeData.put("01:15", "01:15");
		AttendanceTimeData.put("01:30", "01:30");
		AttendanceTimeData.put("01:45", "01:45");
		AttendanceTimeData.put("02:00", "02:00");
		AttendanceTimeData.put("02:15", "02:15");
		AttendanceTimeData.put("02:30", "02:30");
		AttendanceTimeData.put("02:45", "02:45");
		AttendanceTimeData.put("03:00", "03:00");
		AttendanceTimeData.put("03:15", "03:15");
		AttendanceTimeData.put("03:30", "03:30");
		AttendanceTimeData.put("03:45", "03:45");
		AttendanceTimeData.put("04:00", "04:00");
		AttendanceTimeData.put("04:15", "04:15");
		AttendanceTimeData.put("04:30", "04:30");
		AttendanceTimeData.put("04:45", "04:45");
		AttendanceTimeData.put("05:00", "05:00");
		AttendanceTimeData.put("05:15", "05:15");
		AttendanceTimeData.put("05:30", "05:30");
		AttendanceTimeData.put("05:45", "05:45");
		AttendanceTimeData.put("06:00", "06:00");
		AttendanceTimeData.put("06:15", "06:15");
		AttendanceTimeData.put("06:30", "06:30");
		AttendanceTimeData.put("06:45", "06:45");
		AttendanceTimeData.put("07:00", "07:00");
		AttendanceTimeData.put("07:15", "07:15");
		AttendanceTimeData.put("07:30", "07:30");
		AttendanceTimeData.put("07:45", "07:45");
		AttendanceTimeData.put("08:00", "08:00");
		AttendanceTimeData.put("08:15", "08:15");
		AttendanceTimeData.put("08:30", "08:30");
		AttendanceTimeData.put("08:45", "08:45");
		AttendanceTimeData.put("09:00", "09:00");
		AttendanceTimeData.put("09:15", "09:15");
		AttendanceTimeData.put("09:30", "09:30");
		AttendanceTimeData.put("09:45", "09:45");
		AttendanceTimeData.put("10:00", "10:00");
		AttendanceTimeData.put("10:15", "10:15");
		AttendanceTimeData.put("10:30", "10:30");
		AttendanceTimeData.put("10:45", "10:45");
		AttendanceTimeData.put("11:00", "11:00");
		AttendanceTimeData.put("11:15", "11:15");
		AttendanceTimeData.put("11:30", "11:30");
		AttendanceTimeData.put("11:45", "11:45");
		AttendanceTimeData.put("12:00", "12:00");
		AttendanceTimeData.put("12:15", "12:15");
		AttendanceTimeData.put("12:30", "12:30");
		AttendanceTimeData.put("12:45", "12:45");
		AttendanceTimeData.put("13:00", "13:00");
		AttendanceTimeData.put("13:15", "13:15");
		AttendanceTimeData.put("13:30", "13:30");
		AttendanceTimeData.put("13:45", "13:45");
		AttendanceTimeData.put("14:00", "14:00");
		AttendanceTimeData.put("14:15", "14:15");
		AttendanceTimeData.put("14:30", "14:30");
		AttendanceTimeData.put("14:45", "14:45");
		AttendanceTimeData.put("15:00", "15:00");
		AttendanceTimeData.put("15:15", "15:15");
		AttendanceTimeData.put("15:30", "15:30");
		AttendanceTimeData.put("15:45", "15:45");
		AttendanceTimeData.put("16:00", "16:00");
		AttendanceTimeData.put("16:15", "16:15");
		AttendanceTimeData.put("16:30", "16:30");
		AttendanceTimeData.put("16:45", "16:45");
		AttendanceTimeData.put("17:00", "17:00");
		AttendanceTimeData.put("17:15", "17:15");
		AttendanceTimeData.put("17:30", "17:30");
		AttendanceTimeData.put("17:45", "17:45");
		AttendanceTimeData.put("18:00", "18:00");
		AttendanceTimeData.put("18:15", "18:15");
		AttendanceTimeData.put("18:30", "18:30");
		AttendanceTimeData.put("18:45", "18:45");
		AttendanceTimeData.put("19:00", "19:00");
		AttendanceTimeData.put("19:15", "19:15");
		AttendanceTimeData.put("19:30", "19:30");
		AttendanceTimeData.put("19:45", "19:45");
		AttendanceTimeData.put("20:00", "20:00");
		AttendanceTimeData.put("20:15", "20:15");
		AttendanceTimeData.put("20:30", "20:30");
		AttendanceTimeData.put("20:45", "20:45");
		AttendanceTimeData.put("21:00", "21:00");
		AttendanceTimeData.put("21:15", "21:15");
		AttendanceTimeData.put("21:30", "21:30");
		AttendanceTimeData.put("21:45", "21:45");
		AttendanceTimeData.put("22:00", "22:00");
		AttendanceTimeData.put("22:15", "22:15");
		AttendanceTimeData.put("22:30", "22:30");
		AttendanceTimeData.put("22:45", "22:45");
		AttendanceTimeData.put("23:00", "23:00");
		AttendanceTimeData.put("23:15", "23:15");
		AttendanceTimeData.put("23:30", "23:30");
		AttendanceTimeData.put("23:45", "23:45");

		return AttendanceTimeData;
	}

}
