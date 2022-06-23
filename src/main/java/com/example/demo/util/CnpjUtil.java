package com.example.demo.util;

public class CnpjUtil {

	public static boolean isValid(String cnpj) {
		int sum = 0, digit;
		
		cnpj = StringUtil.removeNonNumericChars(cnpj);
		
		try {
			if (cnpj.length() != 14)
				return false;
			
			String calculateCnpj = cnpj.substring(0, 12);
			
			char[] charsCnpj = cnpj.toCharArray();
	
			for (int i = 0; i < 4; i++)
				if (charsCnpj[i] - 48 >= 0 && charsCnpj[i] - 48 <= 9)
					sum += (charsCnpj[i] - 48) * (6 - (i + 1));
			for (int i = 0; i < 8; i++)
				if (charsCnpj[i + 4] - 48 >= 0 && charsCnpj[i + 4] - 48 <= 9)
					sum += (charsCnpj[i + 4] - 48) * (10 - (i + 1));
			digit = 11 - (sum % 11);
	
			calculateCnpj += (digit == 10 || digit == 11) ? "0" : Integer.toString(digit);
	
			sum = 0;
			for (int i = 0; i < 5; i++)
				if (charsCnpj[i] - 48 >= 0 && charsCnpj[i] - 48 <= 9)
					sum += (charsCnpj[i] - 48) * (7 - (i + 1));
			for (int i = 0; i < 8; i++)
				if (charsCnpj[i + 5] - 48 >= 0 && charsCnpj[i + 5] - 48 <= 9)
					sum += (charsCnpj[i + 5] - 48) * (10 - (i + 1));
			digit = 11 - (sum % 11);
			calculateCnpj += (digit == 10 || digit == 11) ? "0" : Integer.toString(digit);
	
			return cnpj.equals(calculateCnpj);
		} catch (Exception e) {
			return false;
		}
	}
	
	public static String format(String cnpj) {
		return (cnpj.substring(0, 2) + "." + 
				cnpj.substring(2, 5) + "." + 
				cnpj.substring(5, 8) + "/" + 
				cnpj.substring(8, 12) + "-" + 
				cnpj.substring(12));
	}
}
