package com.loris.validator;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.Errors;

	public class CuitValidator {
	
	public static void validateCuit(Errors errors, String cuit){
		if(StringUtils.isNotBlank(cuit)){
			String theValue = StringUtils.deleteWhitespace(StringUtils.remove(cuit, '-'));
			long cuitNumber = 0;
			try {
				cuitNumber = Long.parseLong(theValue);
				if (!validateCuit(cuitNumber)) {
					errors.rejectValue("cuit", MessageConstants.CUIT_INVALID);
				}
			} catch (Exception e) {
				errors.rejectValue("cuit", MessageConstants.CUIT_INVALID);				
			}					
		}
	}
	
	private static boolean validateCuit(long cuit) {
		//divide the cuit-cuil number and makes the calculation of the
		// validator digit
		long verificationCode = cuit % 10;
		long C = (cuit / Long.parseLong("10000000000")) * 5;
		long D = ((cuit / 1000000000) % 10) * 4;
		long E = ((cuit / 100000000) % 10) * 3;
		long F = ((cuit / 10000000) % 10) * 2;
		long G = ((cuit / 1000000) % 10) * 7;
		long H = ((cuit / 100000) % 10) * 6;
		long I = ((cuit / 10000) % 10) * 5;
		long J = ((cuit / 1000) % 10) * 4;
		long K = ((cuit / 100) % 10) * 3;
		long L = ((cuit / 10) % 10) * 2;
		long x = (C + D + E + F + G + H + I + J + K + L) % 11;
		if (x == 0)
			x = 11;
		//compare the validator digit to the calculated
		if (11 - x != verificationCode)
			return false;
		return true;
	}
}
