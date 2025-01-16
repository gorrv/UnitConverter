/**
 * The Unit class represents a unit of measurement within a specific category.
 * Each unit has a name, a conversion factor relative to a base unit, and a symbol.
 * 
 * @version 2025.1.09
 * @author Gor Vardanyan
 */

public class Unit {
    private String name;
    private double conversionFactor;
    private String symbol;

    public Unit(String name, double conversionFactor, String symbol) {
        this.name = name;
        this.conversionFactor = conversionFactor;
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public double getConversionFactor() {
        return conversionFactor;
    }

    public String getSymbol() {
        return symbol;
    }
}
