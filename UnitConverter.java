import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The UnitConverter class handles unit conversion operations and management of unit categories.
 * It allows adding categories of units, retrieving units for a specific category, 
 * and performing conversions between units within the same category.
 * 
 * @version 2025.1.09
 * @author Gor Vardanyan
 */

public class UnitConverter {
    private Map<String, List<Unit>> categories;

    public UnitConverter() {
        categories = new HashMap<>();
    }

    // Add a new category with its units
    public void addCategory(String categoryName, List<Unit> units) {
        categories.put(categoryName, units);
    }

    // Get units for a specific category
    public List<Unit> getUnits(String categoryName) {
        return categories.get(categoryName);
    }

    // Conversion logic
    public double convert(String category, String fromUnitName, String toUnitName, double value) {
        List<Unit> units = categories.get(category);

        if (units == null) {
            throw new IllegalArgumentException("Category not found: " + category);
        }

        Unit fromUnit = null, toUnit = null;

        // Find the from and to units in the list
        for (Unit unit : units) {
            if (unit.getName().equalsIgnoreCase(fromUnitName)) {
                fromUnit = unit;
            }
            if (unit.getName().equalsIgnoreCase(toUnitName)) {
                toUnit = unit;
            }
        }

        if (fromUnit == null || toUnit == null) {
            throw new IllegalArgumentException("Invalid unit names: " + fromUnitName + " or " + toUnitName);
        }

        // Conversion:
        return value * fromUnit.getConversionFactor() / toUnit.getConversionFactor();
    }
}
