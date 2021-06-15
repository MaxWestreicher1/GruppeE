package A12_Zustandsautomat;

import java.util.ArrayList;

public class CSVParser_1 {



	/**
	 * Implementierung des Automaten mit einem switch()-Statement
	 * für jeden Status des Automaten.
	 * @param str Zu parsende Eingabe
	 * @return Entweder Fehler-Objekt oder Zahl-Objekt
	 */
	public static CSVResult parse(String str) {
		
		int state = 0;
		CSVResult csvResult = new CSVResult();
		
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);


			switch(state) {
				case 0:
					if(c == '"') {
						state = 1;
						break;
					}
					if (c == '\n' || c=='\t'){
						state = 0;
						break;
					}
					if(isTextData(c) == true){
						csvResult.appendChar(c);
						state = 3;
						break;
					}

				case 1:
					if (str.charAt(i+1) == '"'){
						state = 2;
						break;
					}
					if (csvResult.hasError()){
						csvResult.appendChar(c);
						state = 1;
						break;
					}
				case 2:
					if (c == '\r'){
						state = 1;
						break;
					}
					if (c  == ','){
						state = 0;
						break;
					}
				case 3:
					if (c == ','){
						state = 0;
						break;
					}
					if (c == '\n'){
						state = 0;
						break;
					}

			} // switch end
		}
		
		return csvResult;
	}
	
	private static boolean isTextData(char c) {
		// aus RFC TEXTDATA =  %x20-21 / %x23-2B / %x2D-7E
		// in Abweichung zum RFC werden alle Zeichen >=0x80 als legal betrachtet
		return !(c < 0x20 || c == 0x22 || c == 0x2c || c == 0x7f);
	}
}
