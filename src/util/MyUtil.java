package util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyUtil {
	
	public static String getCurrentTime(){  //��ȡ��ǰ��ʱ��
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss EEEE");  // MM�����д��HHΪ24Сʱ�ƣ�hhΪ12Сʱ��
		Date date = new Date();
		return sdf.format(date);
	}
	
	private static boolean isMatch(String regex, String orginal) {
		if (orginal == null || orginal.trim().equals("")) {
			return false;
		}
		Pattern pattern = Pattern.compile(regex);
		Matcher isNum = pattern.matcher(orginal);
		return isNum.matches();
	}

	public static boolean isPositiveInteger(String orginal) {
		return isMatch("^\\+{0,1}[1-9]\\d*", orginal);
	}

	public static boolean isNegativeInteger(String orginal) {
		return isMatch("^-[1-9]\\d*", orginal);
	}

	public static boolean isWholeNumber(String orginal) {
		return isMatch("[+-]{0,1}0", orginal) || isPositiveInteger(orginal) || isNegativeInteger(orginal);
	}

	public static boolean isPositiveDecimal(String orginal) {
		return isMatch("\\+{0,1}[0]\\.[1-9]*|\\+{0,1}[1-9]\\d*\\.\\d*", orginal);
	}

	public static boolean isNegativeDecimal(String orginal) {
		return isMatch("^-[0]\\.[1-9]*|^-[1-9]\\d*\\.\\d*", orginal);
	}

	public static boolean isDecimal(String orginal) {
		return isMatch("[-+]{0,1}\\d+\\.\\d*|[-+]{0,1}\\d*\\.\\d+", orginal);
	}

	public static boolean isRealNumber(String orginal) {
		return isWholeNumber(orginal) || isDecimal(orginal);
	}
	
	public static boolean validateTime(String dateString){
		//ʹ��������ʽ ���� �ַ� ���� yyyy-MM-dd �ĸ�ʽ(d��ʾ����)
		Pattern p = Pattern.compile("\\d{4}+[-]\\d{1,2}+[-]\\d{1,2}+");
		Matcher m = p.matcher(dateString);
		if(!m.matches()){	return false;} 
		
		//�õ�������
		String[] array = dateString.split("-");
		int year = Integer.valueOf(array[0]);
		int month = Integer.valueOf(array[1]);
		int day = Integer.valueOf(array[2]);
		
		if(month<1 || month>12){	return false;}
		int[] monthLengths = new int[]{0, 31, -1, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
		if(isLeapYear(year)){
			monthLengths[2] = 29;
		}else{
			monthLengths[2] = 28;
		}
		int monthLength = monthLengths[month];
		if(day<1 || day>monthLength){
			return false;	
		}
		return true;
	}
	
	/** �Ƿ������� */
	private static boolean isLeapYear(int year){
		return ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) ;
	}
}
