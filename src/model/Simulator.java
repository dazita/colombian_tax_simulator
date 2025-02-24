package model;

import interfaces.iEVDContract;
import interfaces.iEVDContract.Presenter;
import libraries.*;

public class Simulator implements iEVDContract.Model {

    private SimpleList<Brand> brands;
    private BrandFetcher fetcher;
    private iEVDContract.Presenter presenter;

    public Simulator() {
        fetcher = new BrandFetcher();
        brands = fetcher.readCSV();
    }

    @Override
    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void calculateTaxFinal(String brand, String line, String model, boolean departmentCondition) {
        try {
            Brand foundBrand = findBrand(brand);
            Line foundLine = findLine(foundBrand, line);
            int price = findModelPrice(foundLine, model);
            double tax = calculateTaxByPrice(Double.valueOf(price));
            validateDiscounts(tax, foundLine);
        } catch (Exception e) {
            presenter.carNotFound();
        }
    }

    public double calculateTaxByPrice(double price) {
        if (price <= SimulatorFeatures.FIRST_LIMIT) {
            price = (price / 100) * SimulatorFeatures.FIRST_PERCENTAGE;
        } else if (price > SimulatorFeatures.FIRST_LIMIT && price <= SimulatorFeatures.SECOND_LIMIT) {
            price = (price / 100) * SimulatorFeatures.SECOND_PERCENTAGE;
        } else {
            price = (price / 100) * SimulatorFeatures.THIRD_PERCENTAGE;
        }
        return price;
    }

    public void validateDiscounts(double Price, Line foundLine){

    }

    public double validateFuelDiscount(Line line, double price) {
        if (line.getFuel().equals("HBD") || line.getFuel().equals("ELT")) {
            return price - (price / 100) * SimulatorFeatures.HYBRID_OR_ELECTRIC_DISCOUNT;
        }
        return price;
    }

    public double validateDepartmentDiscount(boolean departmentCondition, double price){
        if (departmentCondition) {
            return (price/100)*
        }
    }

    public Brand findBrand(String brandName) {
        Brand foundBrand = null;
        for (Brand b : brands) {
            if (brandName.equals(b.getName())) {
                foundBrand = b;
            }
        }
        return foundBrand;
    }

    public Line findLine(Brand brandObject, String lineName) {
        Line foundLine = null;
        for (Line l : brandObject.getLines()) {
            if (l.getName().equals(lineName)) {
                foundLine = l;
            }
        }
        return foundLine;
    }

    public int findModelPrice(Line lineObject, String model) {
        int price = 0;
        for (Model m : lineObject.getModels()) {
            if (m.getYear().equals(model)) {
                price = m.getPrize();
            }
        }
        return price;
    }

}
