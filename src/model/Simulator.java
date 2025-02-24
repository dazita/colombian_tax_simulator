package model;

import java.time.LocalDate;

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
    public double calculateTaxFinal(String brand, String line, String model, boolean departmentCondition) {
        try {
            Brand foundBrand = findBrand(brand);
            System.out.println(foundBrand.getName());
            Line foundLine = findLine(foundBrand, line);
            System.out.println(foundLine.getFuel());
            System.out.println(foundLine.getName());
            int price = findModelPrice(foundLine, model);
            System.out.println(price);
            double tax = calculateTaxByPrice(Double.valueOf(price));
            System.out.println(tax);
            double finalTax = validateDiscounts(tax, foundLine, departmentCondition);
            System.out.println(finalTax);
            return finalTax;
        } catch (Exception e) {
            System.out.println("notFound");
            presenter.carNotFound();
            return 0;
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

    public double validateDiscounts(double price, Line foundLine, boolean departmentCondition){
        double finalTax = price;
        if (validateFuelDiscount(foundLine, price)) {
            finalTax -= (finalTax/100)*SimulatorFeatures.HYBRID_OR_ELECTRIC_DISCOUNT;
            System.out.println((finalTax/100)*SimulatorFeatures.HYBRID_OR_ELECTRIC_DISCOUNT);
        } else if (departmentCondition) {
            finalTax -= (finalTax/100)*SimulatorFeatures.BOYACA_DISCOUNT;
        } else if (validateDateDiscount()){
            finalTax -= (finalTax/100)*SimulatorFeatures.DATE_DISCOUNT_PERCENTAGE;
        }
        return finalTax;
    }

    public boolean validateDateDiscount(){
        return LocalDate.now().isBefore(SimulatorFeatures.DISCOUNT_DATE);
    }

    public boolean validateFuelDiscount(Line line, double price) {
        return (line.getFuel().equals("HBD") || line.getFuel().equals("ELT"));
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
            if ((String.valueOf(m.getYear())).equals(model)) {
                price = m.getPrize();
            }
        }
        return price;
    }

}
