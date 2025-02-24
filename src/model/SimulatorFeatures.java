package model;

import java.time.LocalDate;

public class SimulatorFeatures {

    public static final int FIRST_LIMIT = 52483;
    public static final int SECOND_LIMIT = 118033;
    public static final double FIRST_PERCENTAGE = 1.5;
    public static final double SECOND_PERCENTAGE = 2.5;
    public static final double THIRD_PERCENTAGE = 3.5;
    public static final double DATE_DISCOUNT_PERCENTAGE = 10;
    public static final double BOYACA_DISCOUNT = 15;
    public static final double HYBRID_OR_ELECTRIC_DISCOUNT = 25;
    public static final LocalDate DISCOUNT_DATE = LocalDate.of(2025, 3, 1);

    private SimulatorFeatures() {
        throw new UnsupportedOperationException("Utility class");
    }
}