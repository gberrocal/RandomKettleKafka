package etl.pentaho.generator.model;

import org.apache.commons.math3.random.RandomDataGenerator;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class DateTimeFieldDistributionModel extends DateFieldDistributionModel {

	private static final long serialVersionUID = 2228897766035659296L;

	public DateTimeFieldDistributionModel(FrequencyFieldDistributionModel frequencyFieldDistributionModel) {
		super(frequencyFieldDistributionModel);
	}

	@Override
	public String generateRandomField() {
		
		StringBuffer sb = new StringBuffer();
		String res = null;
		RandomDataGenerator rdg = new RandomDataGenerator();
		res = getFrequencyFieldDistributionModel().generateRandomField();
		
		// Puede ser cualquier dia < 28 que es el dia maximo que podria tomar febrero.
		long randomDay = 10;
		String fullDate = res + Long.toString(randomDay); 

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
		
		long hour = rdg.nextLong(0, 24);
		long min = rdg.nextLong(0, 60);
		long sec = rdg.nextLong(0, 60);
		
		sb.append(" ");
		sb.append(hour < 10L ? "0"+ hour : hour);
		sb.append(":");
		sb.append(min < 10L ? "0"+ min : min);
		sb.append(":");
		sb.append(sec < 10L ? "0"+ sec : sec);

		
		return sb.toString();
	}
}
