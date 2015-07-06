package etl.pentaho.generator.model.builder;

import etl.pentaho.generator.model.DateFieldDistributionModel;
import etl.pentaho.generator.model.FieldDistributionModel;
import etl.pentaho.generator.model.FrequencyFieldDistributionModel;

public class DateFieldDistributionModelBuilder implements
		FieldDistributionModelBuilder {

	private FrequencyFieldDistributionModelBuilder frequencyFieldDistributionModelBuilder;
	
	public DateFieldDistributionModelBuilder(){
		this.frequencyFieldDistributionModelBuilder = new FrequencyFieldDistributionModelBuilder();
	}
	
	@Override
	public FieldDistributionModel getDistributionFieldModel() {
		FrequencyFieldDistributionModel frequencyFieldDistributionModel = (FrequencyFieldDistributionModel)frequencyFieldDistributionModelBuilder.getDistributionFieldModel();
		DateFieldDistributionModel dateFieldDistributionModel =	new DateFieldDistributionModel(frequencyFieldDistributionModel);
			
		return dateFieldDistributionModel;
	}
	
	@Override
	public void processFieldValue(String fieldValue) {
		frequencyFieldDistributionModelBuilder.setCount(frequencyFieldDistributionModelBuilder.getCount() + 1);
		
		// Si es una fecha, para decidir el valor que se repite se toma el anio y el mes.
		String frequencyDate = fieldValue.substring(0, 6);
		fieldValue = frequencyDate;

		Long frequency = frequencyFieldDistributionModelBuilder.getValueFrequencyMap().get(fieldValue);
		if (frequency == null) {
			frequency = 1L;
		} else {
			frequency++;
		}
		
		frequencyFieldDistributionModelBuilder.getValueFrequencyMap().put(fieldValue, frequency);
	}

	public FrequencyFieldDistributionModelBuilder getFrequencyFieldDistributionModelBuilder() {
		return frequencyFieldDistributionModelBuilder;
	}

	public void setFrequencyFieldDistributionModelBuilder(
			FrequencyFieldDistributionModelBuilder frequencyFieldDistributionModelBuilder) {
		this.frequencyFieldDistributionModelBuilder = frequencyFieldDistributionModelBuilder;
	}

}
