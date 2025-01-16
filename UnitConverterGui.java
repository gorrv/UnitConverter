import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.*;

/**
 * The UnitConverterGui class provides a graphical user interface (GUI) 
 * for a unit conversion application. It allows users to:
 * - Select a category of units (e.g., Length, Weight, Time).
 * - Choose the source and target units for conversion.
 * - Input a value to convert and perform the calculation.
 * 
 * The GUI dynamically updates the unit dropdowns based on the selected category.
 * Conversion is performed using predefined conversion factors for each unit.
 * The result is displayed in a label.
 * 
 * @version 2025.1.09
 * @author Gor Vardanyan
 */

public class UnitConverterGui {
    private JFrame frame;
    private JPanel panel;
    private JComboBox<String> unitCategoryDropdown;
    private JComboBox<String> fromUnitDropdown;
    private JComboBox<String> toUnitDropdown;
    private JTextField inputField;
    private JButton convertButton;
    private JLabel resultLabel;
    private List<UnitCategory> categories;

    public UnitConverterGui() {
        categories = UnitCategory.createCategories();

        // Frame setup
        frame = new JFrame("Unit Converter");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 500);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        // Panel setup
        panel = new JPanel();
        panel.setLayout(new GridLayout(6, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setBackground(new Color(245, 245, 245));

        // Components
        JLabel titleLabel = new JLabel("Unit Converter", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(new Color(51, 102, 255));

        unitCategoryDropdown = new JComboBox<>();
        fromUnitDropdown = new JComboBox<>();
        toUnitDropdown = new JComboBox<>();
        inputField = new JTextField();
        convertButton = new JButton("Convert");
        resultLabel = new JLabel("Result: ", JLabel.CENTER);
        resultLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        resultLabel.setForeground(Color.DARK_GRAY);

        // Populates category dropdown
        for (UnitCategory category : categories) {
            unitCategoryDropdown.addItem(category.getName());
        }

        // Adds default items to unit dropdowns
        fromUnitDropdown.addItem("Select a unit");
        toUnitDropdown.addItem("Select a unit");

        // Adds action listeners
        unitCategoryDropdown.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateUnitDropdowns();
            }
        });

        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performConversion();
            }
        });

        // Adds components to the panel
        panel.add(titleLabel);
        panel.add(createLabeledPanel("Category:", unitCategoryDropdown));
        panel.add(createLabeledPanel("From Unit:", fromUnitDropdown));
        panel.add(createLabeledPanel("To Unit:", toUnitDropdown));
        panel.add(createLabeledPanel("Value:", inputField));
        panel.add(createButtonPanel());

        frame.add(panel);
        frame.setVisible(true);
    }

    private JPanel createLabeledPanel(String labelText, JComponent component) {
        JPanel labeledPanel = new JPanel(new BorderLayout(10, 10));
        JLabel label = new JLabel(labelText);
        labeledPanel.add(label, BorderLayout.WEST);
        labeledPanel.add(component, BorderLayout.CENTER);
        return labeledPanel;
    }

    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel(new BorderLayout(10, 10));
        buttonPanel.add(convertButton, BorderLayout.NORTH);
        buttonPanel.add(resultLabel, BorderLayout.CENTER);
        return buttonPanel;
    }

    private void updateUnitDropdowns() {
        // Clears existing items in unit dropdowns
        fromUnitDropdown.removeAllItems();
        toUnitDropdown.removeAllItems();

        // Gets selected category
        String selectedCategory = (String) unitCategoryDropdown.getSelectedItem();

        if (selectedCategory != null) {
            for (UnitCategory category : categories) {
                if (category.getName().equals(selectedCategory)) {
                    for (Unit unit : category.getUnits()) {
                        fromUnitDropdown.addItem(unit.getName());
                        toUnitDropdown.addItem(unit.getName());
                    }
                    break;
                }
            }
        }

        // Adds default option if no units are found
        if (fromUnitDropdown.getItemCount() == 0) {
            fromUnitDropdown.addItem("Select a unit");
            toUnitDropdown.addItem("Select a unit");
        }
    }

    private void performConversion() {
        String fromUnitName = (String) fromUnitDropdown.getSelectedItem();
        String toUnitName = (String) toUnitDropdown.getSelectedItem();
        String inputValue = inputField.getText();

        // Validates input
        if (fromUnitName == null || toUnitName == null || inputValue.isEmpty()) {
            resultLabel.setText("Result: Invalid input");
            return;
        }

        try {
            double value = Double.parseDouble(inputValue);
            double fromUnitRate = 0.0;
            double toUnitRate = 0.0;

            // Gets rates for the selected units
            for (UnitCategory category : categories) {
                for (Unit unit : category.getUnits()) {
                    if (unit.getName().equals(fromUnitName)) {
                        fromUnitRate = unit.getConversionFactor();
                    }
                    if (unit.getName().equals(toUnitName)) {
                        toUnitRate = unit.getConversionFactor();
                    }
                }
            }

            // Performs the conversion
            if (fromUnitRate > 0 && toUnitRate > 0) {
                double result = value * (toUnitRate / fromUnitRate);
                resultLabel.setText(String.format("Result: %.8f %s", result, toUnitName));
            } else {
                resultLabel.setText("Result: Conversion error");
            }
        } catch (NumberFormatException e) {
            resultLabel.setText("Result: Invalid number format");
        }
    }
}