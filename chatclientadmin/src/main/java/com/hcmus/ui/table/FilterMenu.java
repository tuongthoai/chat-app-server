package com.hcmus.ui.table;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class FilterMenu extends JMenu {
    private JMenuItem filterItem;
    private JMenuItem clearFilterItem;
    private JComponent[] components;
    private JLabel[] labels;

    private TableRowSorter<DefaultTableModel> sorter;
    private DefaultTableModel model;

    public FilterMenu(TableRowSorter<DefaultTableModel> sorter, DefaultTableModel model) {
        super("Filter");
        this.model = model;
        this.sorter = sorter;

        filterItem = new JMenuItem("Filter");
        clearFilterItem = new JMenuItem("Clear Filter");
        add(filterItem);
        add(clearFilterItem);

        filterItem.addActionListener(e -> {
            showFilterDialog();
        });
    }

    public void setFilterComponents(JComponent... components) {
        this.components = components;
    }

    public void setFilterLabels(JLabel... labels) {
        this.labels = labels;
    }

    private void showFilterDialog() {
        if (components == null || labels == null) throw new IllegalStateException("Filter components and labels must be set");
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        for (int i = 0; i < components.length; i++) {
            JPanel row = new JPanel();
            row.add(labels[i]);
            row.add(components[i]);
            panel.add(row);
        }
        int result = JOptionPane.showConfirmDialog(null, panel, "Filter", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) applyFilter();
    }

    private void applyFilter() {
        for (JComponent component: this.components){
            if (!(component instanceof JTextField)) {
                continue;
            }
            JTextField textField = (JTextField) component;
            String text = textField.getText();
            String columnName = textField.getName();
            if (text.trim().isEmpty()) sorter.setRowFilter(null);
            else {
                // get column index
                int columnIndex = -1;
                for (int i = 0; i < model.getColumnCount(); i++) {
                    if (model.getColumnName(i).equalsIgnoreCase(columnName)) {
                        columnIndex = i;
                        break;
                    }
                }
                if (columnIndex == -1) throw new IllegalStateException("Column name not found");
                System.out.println(columnIndex);
                sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text, columnIndex));
            }
        }
    }


    public JMenuItem getFilterItem() {
        return filterItem;
    }

    public JMenuItem getClearFilterItem() {
        return clearFilterItem;
    }
}
