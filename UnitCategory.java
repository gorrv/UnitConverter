import java.util.ArrayList;
import java.util.List;

/**
 * The UnitCategory class represents a category of units used for unit conversion.
 * Each category has a name and a list of units associated with it.
 * 
 * @version 2025.1.09
 * @author Gor Vardanyan
 */

public class UnitCategory {
    private String name;
    private List<Unit> units;

    public UnitCategory(String name) {
        this.name = name;
        this.units = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void addUnit(Unit unit) {
        units.add(unit);
    }

    public List<Unit> getUnits() {
        return units;
    }

    public static List<UnitCategory> createCategories() {
        List<UnitCategory> categories = new ArrayList<>();

        // Length category
        UnitCategory length = new UnitCategory("Length");
        length.addUnit(new Unit("Meter", 1.0, "m"));
        length.addUnit(new Unit("Kilometer", 0.001, "km"));
        length.addUnit(new Unit("Cantimeter", 100.0, "cm"));
        categories.add(length);

        // Weight category
        UnitCategory weight = new UnitCategory("Weight");
        weight.addUnit(new Unit("Gram", 1.0, "g"));
        weight.addUnit(new Unit("Kilogram", 0.001, "kg"));
        weight.addUnit(new Unit("Miligram", 1000, "mg"));
        weight.addUnit(new Unit("Pound", 0.0022, "lbs"));
        categories.add(weight);

        // Time Category
        UnitCategory time = new UnitCategory("Time");
        time.addUnit(new Unit("Second", 1.0, "s"));
        time.addUnit(new Unit("Minute", 1.0 / 60, "min"));
        time.addUnit(new Unit("Hour", 1.0/3600, "h"));
        time.addUnit(new Unit("Milisecond", 1000, "ms"));
        categories.add(time);

        // Temperature Category
        UnitCategory temperature = new UnitCategory("Temperature");
        temperature.addUnit(new Unit("Celsius", 1.0, "C"));
        temperature.addUnit(new Unit("Fahrenheit", 1.8, "F"));
        categories.add(temperature);

        // Speed Category
        UnitCategory speed = new UnitCategory("Speed");
        speed.addUnit(new Unit("Meter per Second", 1.0, "m/s"));
        speed.addUnit(new Unit("Kilometer per Hour", 1.0/3.6, "km/h"));
        speed.addUnit(new Unit("Miles per Hour", 1/2.24, "mph"));
        categories.add(speed);

        // Energy Category
        UnitCategory energy = new UnitCategory("Energy");
        energy.addUnit(new Unit("Joule", 1.0, "J"));
        energy.addUnit(new Unit("Kilojoule", 1/1000.0, "kJ"));
        energy.addUnit(new Unit("Calorie", 1/4.184, "cal"));
        energy.addUnit(new Unit("Kilocalorie", 1/4184.0, "kcal"));
        energy.addUnit(new Unit("Watt-hour", 1/3600.0, "Wh"));
        categories.add(energy);

        // Volume Category
        UnitCategory volume = new UnitCategory("Volume");
        volume.addUnit(new Unit("Cubic meter", 1.0, "m³"));
        volume.addUnit(new Unit("Cubic cantimeter", 1/1000000, "cm³"));
        volume.addUnit(new Unit("Liter", 1/1000, "l"));
        volume.addUnit(new Unit("Mililiter", 1/1000000, "ml"));
        volume.addUnit(new Unit("Fluid Ounce", 0.0000295735, "fl oz"));
        categories.add(volume);


        return categories;
    }
}

