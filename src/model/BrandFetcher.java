package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import libraries.*;

public class BrandFetcher {

    private SimpleList<Brand> brands = new SimpleList<>();

    
    public SimpleList<Brand> readCSV() {
        try (BufferedReader br = new BufferedReader(new FileReader("resources\\Guia_CSV_336.csv"))) {
            String line;
            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");

                String brandName = data[1].replace("\"", "");
                String type = data[2].replace("\"", "");
                String lineName = data[5].replace("\"", "");
                List<String> list = Arrays.asList(data);
                List<String> models = list.subList(11, 67);
                String[] modelsArray = models.toArray(new String[0]);
                String fuel = data[79];

                if (type.equals("CAMPERO") || type.equals("AUTOMOVIL") || type.equals("CAMIONETA PASAJ.")) {
                    Brand brand = searchOrCreateBrand(brandName);
                    createLine(brand, lineName, modelsArray, fuel);
                }
            }

        

        } catch (IOException e) {
            e.printStackTrace();
        }

        return brands;
    }

    private Brand searchOrCreateBrand(String brandName){
        for (Brand b : brands) {
            if (b.getName().equals(brandName)) {
                return b;
            }
        }
            Brand newBrand = new Brand(brandName);
            brands.add(newBrand);
            return newBrand;
    }

    private void createLine(Brand brand, String lineName, String[] models, String fuel){
        for (Line l : brand.getLines()) {
            if (l.getName().equals(lineName)) {
                return;
            }
        }
            Line newLine = new Line(lineName, fuel);
            addModels(newLine, models);
            brand.getLines().add(newLine);
    }

    public void addModels(Line line, String[] models){
        for (int index = 0; index < models.length; index++) {
            if (!models[index].equals("0")) {
                line.getModels().add(new Model((index + 1970), Integer.valueOf(models[index])));
            }
        }
    }
}
