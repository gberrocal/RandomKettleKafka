package etl.pentaho.generator.model.builder;

import etl.pentaho.generator.model.FieldDistributionModel;
import etl.pentaho.generator.model.FrequencyFieldDistributionModel;
import etl.pentaho.generator.model.builder.FieldDistributionModelBuilderDirector;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TestComparator {
    private static HashMap<String, String> mapFirstFile = new HashMap();

    public static void main(String[] args) throws IOException {
        ArrayList<FieldDistributionModelBuilderDirector> builderList = new ArrayList<FieldDistributionModelBuilderDirector>();
        FieldDistributionModelBuilderDirector[] builderArray = null;
        FileInputStream fis = null;
        BufferedReader reader = null;
        FileInputStream fis2 = null;
        BufferedReader reader2 = null;
        int j = 0;
        boolean columnCounter = false;
        try {
            try {
                fis = new FileInputStream("C:/Users/JRRZ/Desktop/Kettle-Kaftka/SUMMIT_Trades_15_03_27.csv");
                reader = new BufferedReader(new InputStreamReader(fis));
                fis2 = new FileInputStream("C:/Users/JRRZ/Desktop/Kettle-Kaftka/salida.txt");
                reader2 = new BufferedReader(new InputStreamReader(fis2));
                String line = reader.readLine();
                builderArray = TestComparator.firstLine(builderList, line);
                TestComparator.processLine(builderArray, j, line);
                line = reader.readLine();
                ++j;
                while (line != null) {
                    line = TestComparator.processLine(builderArray, j, line);
                    line = reader.readLine();
                    ++j;
                }
                FieldDistributionModel[] fieldDistributions = new FieldDistributionModel[builderArray.length];
                for (int i = 0; i < builderArray.length; ++i) {
                    fieldDistributions[i] = builderArray[i] == null ? null : builderArray[i].getDistributionFieldModel();
                }
                TestComparator.readFirstFile(fieldDistributions);
                j = 0;
                ArrayList<FieldDistributionModelBuilderDirector> builderList2 = new ArrayList<FieldDistributionModelBuilderDirector>();
                FieldDistributionModelBuilderDirector[] builderArray2 = null;
                String line2 = reader2.readLine();
                builderArray2 = TestComparator.firstLine(builderList2, line2);
                TestComparator.processLine(builderArray2, j, line2);
                line2 = reader2.readLine();
                ++j;
                while (line2 != null) {
                    line2 = TestComparator.processLine(builderArray2, j, line2);
                    line2 = reader2.readLine();
                    ++j;
                }
                FieldDistributionModel[] fieldDistributions2 = new FieldDistributionModel[builderArray2.length];
                for (int i2 = 0; i2 < builderArray2.length; ++i2) {
                    fieldDistributions2[i2] = builderArray2[i2] == null ? null : builderArray2[i2].getDistributionFieldModel();
                }
                TestComparator.compareFiles(fieldDistributions2);
            }
            catch (FileNotFoundException e) {
                e.printStackTrace();
                if (fis != null) {
                    fis.close();
                }
                if (reader != null) {
                    reader.close();
                }
            }
        }
        finally {
            if (fis != null) {
                fis.close();
            }
            if (reader != null) {
                reader.close();
            }
        }
    }

    private static void readFirstFile(FieldDistributionModel[] fieldDistributions) {
        for (int i = 0; i < fieldDistributions.length; ++i) {
            if (!(fieldDistributions[i] instanceof FrequencyFieldDistributionModel)) continue;
            String[] values = ((FrequencyFieldDistributionModel)fieldDistributions[i]).getValues();
            Long[] dist = ((FrequencyFieldDistributionModel)fieldDistributions[i]).getFrecuencyDistribution();
            Long count = ((FrequencyFieldDistributionModel)fieldDistributions[i]).getCount();
            for (int z = 0; z < values.length; ++z) {
                Double total;
                if (z == 0) {
                    total = new Double(dist[z].longValue()) * new Double(100.0) / new Double(count.longValue());
                    mapFirstFile.put(values[z].toString(), String.valueOf(total));
                    continue;
                }
                total = (new Double(dist[z].longValue()) - new Double(dist[z - 1].longValue())) * new Double(100.0) / new Double(count.longValue());
                mapFirstFile.put(values[z].toString(), String.valueOf(total));
            }
        }
    }

    private static void compareFiles(FieldDistributionModel[] fieldDistributions) {
        FileWriter fichero = null;
        PrintWriter pw = null;
        try {
            try {
                fichero = new FileWriter("C:/Users/JRRZ/Desktop/Kettle-Kaftka/compara2.txt");
                pw = new PrintWriter(fichero);
                for (int i = 0; i < fieldDistributions.length; ++i) {
                    if (!(fieldDistributions[i] instanceof FrequencyFieldDistributionModel)) continue;
                    String[] values = ((FrequencyFieldDistributionModel)fieldDistributions[i]).getValues();
                    Long[] dist = ((FrequencyFieldDistributionModel)fieldDistributions[i]).getFrecuencyDistribution();
                    Long count = ((FrequencyFieldDistributionModel)fieldDistributions[i]).getCount();
                    for (int z = 0; z < values.length; ++z) {
                        Double total;
                        StringBuffer sb = new StringBuffer();
                        if (z == 0) {
                            total = new Double(dist[z].longValue()) / new Double(count.longValue());
                            sb.append(values[z]).append(";").append(String.valueOf(total)).append(";").append(mapFirstFile.get(values[z]) != null ? mapFirstFile.get(values[z]) : "0");
                        } else {
                            total = (new Double(dist[z].longValue()) - new Double(dist[z - 1].longValue())) * new Double(100.0) / new Double(count.longValue());
                            sb.append(values[z]).append(";").append(String.valueOf(total)).append(";").append(mapFirstFile.get(values[z]) != null ? mapFirstFile.get(values[z]) : "0");
                        }
                        pw.println(sb.toString());
                    }
                }
            }
            catch (IOException e) {
                e.printStackTrace();
                try {
                    if (fichero != null) {
                        fichero.close();
                    }
                }
                catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }
        finally {
            try {
                if (fichero != null) {
                    fichero.close();
                }
            }
            catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    private static FieldDistributionModelBuilderDirector[] firstLine(List<FieldDistributionModelBuilderDirector> builderList, String line) {
        line = String.valueOf(line) + ",1";
        String[] fields = line.split(",");
        for (int i = 0; i < fields.length; ++i) {
            FieldDistributionModelBuilderDirector director = null;
            if (!(fields[i] == null || fields[i].equals(""))) {
                director = new FieldDistributionModelBuilderDirector(fields[i]);
            }
            builderList.add(director);
        }
//        FieldDistributionModelBuilderDirector[] builderArray = builderList.toArray((T[])new FieldDistributionModelBuilderDirector[0]);
//        return builderArray;
        return null;
    }

    private static String processLine(FieldDistributionModelBuilderDirector[] builderArray, int j, String line) {
        int columnCount = 0;
        String[] fields = (line = String.valueOf(line) + ",1").split(",");
        if (fields.length != builderArray.length) {
            System.out.println("incorrect column number (" + fields.length + ") at line: " + j);
        } else {
            for (int i = 0; i < fields.length; ++i) {
                if (!(fields[i] == null || fields[i].equals(""))) {
                    FieldDistributionModelBuilderDirector builder = builderArray[i];
                    if (builder == null) {
                        builderArray[i] = builder = new FieldDistributionModelBuilderDirector(fields[i]);
                    }
                    if (fields[i].equals("IRFundingMMFloat")) {
                        System.out.println(fields.toString());
                    }
                    builder.processFieldValue(fields[i], columnCount);
                }
                ++columnCount;
            }
        }
        return line;
    }
}
