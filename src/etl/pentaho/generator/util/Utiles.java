package etl.pentaho.generator.util;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class Utiles {

//	public static boolean isDate(String chanceDate){
//		try{
//			// Formato de Fecha DDMMAAAA
//
//			// 1- Si no es una fecha no tendra un tamanio de 8 digitos
//			if (chanceDate.length() != 8){
//				return false;
//			}
//			
//			// 2- Si no es una fecha la conversion lanzara excepcion
//			int posibleFecha = Integer.parseInt(chanceDate.substring(0, 8));
//			
//			
//			// 3- El mes no puede ser mayor de 12
//			if (Integer.parseInt(chanceDate.substring(4, 6)) > 12){
//				return false;
//			}
//			
//			// 4- El dia no puede ser mayor de 31
//			if (Integer.parseInt(chanceDate.substring(6, 8)) > 31){
//				return false;
//			}
//			
//			return true;
//		}catch (NumberFormatException e){
//			return false;
//		}
//		
//	}
//	
//	public static boolean isDateTime(String chanceDateTime){
//		try{
//			// Formato de Fecha DDMMAAAA
//
//			// 1- Si no es una fecha no tendra un tamanio de 8 digitos
//			if (chanceDateTime.length() != 17){
//				return false;
//			}
//			
//			// 2- Si no es una fecha la conversion lanzara excepcion
//			int posibleFecha = Integer.parseInt(chanceDateTime.substring(0, 8));
//			
//			
//			// 3- El mes no puede ser mayor de 12
//			if (Integer.parseInt(chanceDateTime.substring(4, 6)) > 12){
//				return false;
//			}
//			
//			// 4- El dia no puede ser mayor de 31
//			if (Integer.parseInt(chanceDateTime.substring(6, 8)) > 31){
//				return false;
//			}
//			
//			// 5- La horas, minutos y segundos estan separados por :
//			if (!chanceDateTime.substring(11, 12).equals(":") || !chanceDateTime.substring(14, 15).equals(":")){
//				return false;
//			}
//			
//			return true;
//		}catch (NumberFormatException e){
//			return false;
//		}
//		
//	}
	
	public static boolean isDate(String chanceDate){
		try{
			DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyyMMdd");
			DateTime startDate = formatter.parseDateTime(chanceDate);
			
			return true;
		}catch (Exception e){
			return false;
		}
		
	}
	
	public static boolean isDateTime(String chanceDateTime){
		try{
			DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyyMMdd HH:mm:ss");
			DateTime startDate = formatter.parseDateTime(chanceDateTime);
			
			return true;
		}catch (Exception e){
			return false;
		}
	}
}
