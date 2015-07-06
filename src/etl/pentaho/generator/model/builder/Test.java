package etl.pentaho.generator.model.builder;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import etl.pentaho.generator.model.DateFieldDistributionModel;
import etl.pentaho.generator.model.DateTimeFieldDistributionModel;
import etl.pentaho.generator.model.FieldDistributionModel;
import etl.pentaho.generator.model.FrequencyFieldDistributionModel;

public class Test {

	public static void main(String[] args) throws IOException {

		List<FieldDistributionModelBuilderDirector> builderList = new ArrayList<>();
		FieldDistributionModelBuilderDirector[] builderArray = null;

		FileInputStream fis = null;
		BufferedReader reader = null;
		int j = 0;
		try {
			fis = new FileInputStream(
					"C:/Users/JRRZ/Desktop/Kettle-Kaftka/prueba.csv");
			reader = new BufferedReader(new InputStreamReader(fis));

			// Read line by line
			String line = reader.readLine();

			// First line, calculate directors
			builderArray = firstLine(builderList, line);
			processLine(builderArray, j, line);
			
//			line = processLine(builderArray, j, line);
			
			// Process the rest of lines
			line = reader.readLine();
			j++;
			while (line != null) {
				line = processLine(builderArray, j, line);

				// Next line
				line = reader.readLine();
				j++;
			}

			//
			printDistribution(builderArray);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (fis != null) {
				fis.close();
			}
			if (reader != null) {
				reader.close();
			}
		}
	}

	private static void printDistribution(
			FieldDistributionModelBuilderDirector[] builderArray) {
		for (int i = 0; i < builderArray.length; i++) {
			FieldDistributionModelBuilderDirector builder = builderArray[i];
			if (builder != null) {
				FieldDistributionModel distributionFieldModel = builder
						.getDistributionFieldModel();
				if (distributionFieldModel instanceof FrequencyFieldDistributionModel
					|| distributionFieldModel instanceof DateFieldDistributionModel
					|| distributionFieldModel instanceof DateTimeFieldDistributionModel) {
						Map<String, Long> results = new HashMap<>();
						for (int q = 0; q < 1000; q++) {
							String generateRandomField = (String) distributionFieldModel
									.generateRandomField();
							Long frequency = results.get(generateRandomField);
							if (frequency == null) {
								frequency = 1L;
							} else {
								frequency++;
							}
							results.put(generateRandomField, frequency);
						}
					System.out.println(results.toString());
				}
			}
		}
	}

	private static FieldDistributionModelBuilderDirector[] firstLine(
			List<FieldDistributionModelBuilderDirector> builderList, String line) {
		FieldDistributionModelBuilderDirector[] builderArray;
		line = line + ",1";
		String[] fields = line.split(",");

		for (int i = 0; i < fields.length; i++) {
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
		//TODO ,1 esta puesto para que el split no tome los campos finales como un campo cuando vienen varias ,,,,
		line = line + ",1";
		fields = line.split(",");
		if (fields.length != builderArray.length) {
			System.out.println("incorrect column number (" + fields.length
					+ ") at line: " + j);
		} else {
			for (int i = 0; i < fields.length; i++) {
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
