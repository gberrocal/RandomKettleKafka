package etl.pentaho.generator.model;

import org.apache.commons.math3.random.RandomDataGenerator;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;


public class DateFieldDistributionModel implements FieldDistributionModel {

	private static final long serialVersionUID = -4940738134573342030L;
	
	private FrequencyFieldDistributionModel frequencyFieldDistributionModel;
	
	public DateFieldDistributionModel(FrequencyFieldDistributionModel frequencyFieldDistributionModel){
		this.frequencyFieldDistributionModel = frequencyFieldDistributionModel;
	}
	
	public DateFieldDistributionModel(){
		getFrequencyFieldDistributionModel();
	}
	
	@Override
	public String generateRandomField() {
		
		
		StringBuffer sb = new StringBuffer();
		String res = null;
		RandomDataGenerator rdg = new RandomDataGenerator();
		res = frequencyFieldDistributionModel.generateRandomField();
		
		try{
			long randomDay = 10;
			String fullDate = res + Long.toString(randomDay); 
			
			// Para evitar que el dia generado salga fuera del rango del mes (ej. 31 de Abril) 
			// sumo 1 al mes calculado. Resto los dias que hay entre entre la fecha y la fecha
			// con un mes mas. El numero de dias que obtengo se los sumo a la fecha original.
			
			DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyyMMdd");
			DateTime startDate = formatter.parseDateTime(fullDate);
			
			int maxDayOfMonth = startDate.dayOfMonth().getMaximumValue();
			DateTime startDate2 = formatter.parseDateTime(res + Long.toString(rdg.nextLong(1, maxDayOfMonth)));
			
			int y = startDate2.getYear();
			int m = startDate2.getMonthOfYear();
			int d = startDate2.getDayOfMonth();
		
			sb.append(Integer.toString(y));
			sb.append(m < 10 ? "0" +m : m);
			sb.append(d < 10 ? "0" +d : d);
		
		}catch(Exception ex){
			sb = new StringBuffer("20100101");
		}
		
		return sb.toString();
	}

	public FrequencyFieldDistributionModel getFrequencyFieldDistributionModel() {
		return frequencyFieldDistributionModel;
	}

	public void setFrequencyFieldDistributionModel(
			FrequencyFieldDistributionModel frequencyFieldDistributionModel) {
		this.frequencyFieldDistributionModel = frequencyFieldDistributionModel;
	}

}
