package etl.pentaho.generator.model.builder;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import etl.pentaho.generator.model.FieldDistributionModel;
import etl.pentaho.generator.model.MultiScalarVariableRowGenerator;
import etl.pentaho.generator.model.RowGenerator;

public class SampleAnalyzer {

	private static MultiScalarVariableRowGenerator multiScalarVariableRowGenerator;
	
	public static RowGenerator analyze(String pathToFile) throws IOException {

		if (multiScalarVariableRowGenerator == null){
			List<FieldDistributionModelBuilderDirector> builderList = new ArrayList<>();
			FieldDistributionModelBuilderDirector[] builderArray = null;
	
			FileInputStream fis = null;
			BufferedReader reader = null;
			int j = 0;
			try {
				fis = new FileInputStream(pathToFile);
				reader = new BufferedReader(new InputStreamReader(fis));
	
				// Read line by line
				String line = reader.readLine();
			
	
				// First line, calculate directors
				builderArray = firstLine(builderList, line);
	
				// Process the rest of lines
				line = reader.readLine();
				j++;
				while (line != null) {
					line = processLine(builderArray, j, line);
	
					// Next line
					line = reader.readLine();
					j++;
				}
	
				FieldDistributionModel[] fieldDistributions = new FieldDistributionModel[builderArray.length];
				for (int i = 0; i < builderArray.length; i++) {
					fieldDistributions[i] = builderArray[i] == null ? null
							: builderArray[i].getDistributionFieldModel();
				}
				
				multiScalarVariableRowGenerator = new MultiScalarVariableRowGenerator(fieldDistributions);
				
				return multiScalarVariableRowGenerator;
			
			} finally {
				if (fis != null) {
					fis.close();
				}
				if (reader != null) {
					reader.close();
				}
			}
		
		}else{
			return multiScalarVariableRowGenerator;
		}
	}

	private static FieldDistributionModelBuilderDirector[] firstLine(
			List<FieldDistributionModelBuilderDirector> builderList, String line) {
		FieldDistributionModelBuilderDirector[] builderArray;
		line = line + ",1";
		
		String[] fields = line.split(",");

		for (int i = 0; i < fields.length - 1; i++) {
			FieldDistributionModelBuilderDirector director = null;
			if (fields[i] != null && !fields[i].equals("")) {
				director = new FieldDistributionModelBuilderDirector(fields[i]);
			}
			builderList.add(director);
		}

		builderArray = builderList
				.toArray(new FieldDistributionModelBuilderDirector[0]);
		return builderArray;
	}

	private static String processLine(
			FieldDistributionModelBuilderDirector[] builderArray, int j,
			String line) {
		int columnCount = 0;
		String[] fields;
		line = line + ",1";
		fields = line.split(",");
		if (fields.length - 1 != builderArray.length) {
			System.out.println("incorrect column number (" + (fields.length - 1) 
					+ ") at line: " + j);
		} else {
			for (int i = 0; i < fields.length - 1; i++) {
				if (fields[i] != null && !fields[i].equals("")) {
					FieldDistributionModelBuilderDirector builder = builderArray[i];
					if (builder == null) {
						builder = new FieldDistributionModelBuilderDirector(
								fields[i]);
						builderArray[i] = builder;
					}
					builder.processFieldValue(fields[i], columnCount);
				}
				columnCount++;
			}
		}
		return line;
	}

}
