package etl.pentaho.generator.model;

public class MultiScalarVariableRowGenerator implements RowGenerator {

	private static final long serialVersionUID = -1075686963834398039L;

	private FieldDistributionModel[] fieldDistributions;

	public MultiScalarVariableRowGenerator(
			FieldDistributionModel[] fieldDistributions) {
		this.fieldDistributions = new FieldDistributionModel[fieldDistributions.length];
		for (int i = 0; i < fieldDistributions.length; i++) {
			this.fieldDistributions[i] = fieldDistributions[i];
		}
	}

	@Override
	public String generateRow(String separator) {
		StringBuffer rowBuffer = new StringBuffer("");

		rowBuffer.append(fieldDistributions[0].generateRandomField());

		for (int i = 1; i < fieldDistributions.length; i++) {
			if (fieldDistributions[i] != null) {
				rowBuffer.append(separator).append(
						fieldDistributions[i].generateRandomField());
			}
		}
		return rowBuffer.toString();
	}

}
