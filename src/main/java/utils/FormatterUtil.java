package utils;

public class FormatterUtil {

	public static String convertMobileFormat(String mobile) {
		return String.valueOf(mobile).replaceFirst("(\\d{3})(\\d{3})(\\d+)", "($1)-$2-$3");
	}
	
	public static void main(String[] args) {
		System.out.println("HI");
	}
}
