package com.kademika.day14.store.Market.MarketGUI.Panels;

import com.kademika.day14.store.Market.Market;
import com.kademika.day14.store.Market.Objects.Animal;
import com.kademika.day14.store.Market.Objects.Transaction;

import javax.swing.*;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

/**
 * Created by dean on 8/16/15.
 */
public class CatalogPanel extends AbstractPanel {

    JButton exit;

    public CatalogPanel(final Market market, final JFrame frame, final JPanel mainPane) {
        Object[][] data = new Object[100][5];
        int i=1;
        for (Iterator<Animal> iter = market.getAnimals().values().iterator();iter.hasNext();) {
            Animal animal = iter.next();
            data[i-1][0] = animal.getId();
            data[i-1][1] = animal.getName();
            data[i-1][2] = animal.getType().toString();
            data[i-1][3] = animal.getAmount();
            data[i-1][4] = animal.getPrice();
            i++;
        }
        String[] columsNames = {"id", "Name ", "Type", "Amount", "Price"};
        final JTable table = new JTable(data , columsNames);
        TableColumn column = null;
        column = table.getColumnModel().getColumn(0);
        column.setPreferredWidth(3);
//        column = table.getColumnModel().getColumn(1);
//        column.setPreferredWidth(25);
//        column = table.getColumnModel().getColumn(4);
//        DoubleRenderer renderer = new DoubleRenderer();
//        column.setCellRenderer(renderer);
//        column.setPreferredWidth(25);
//        column.getCellEditor().addCellEditorListener(new CellEditorListener() {
//            @Override
//            public void editingStopped(ChangeEvent e) {
//                e.getSource().
//            }

//            @Override
//            public void editingCanceled(ChangeEvent e) {
//
//            }
//        });
        JScrollPane sp = new JScrollPane(table);
        table.setFillsViewportHeight(true);
        add(sp,BorderLayout.CENTER);
        exit = new JButton("Exit");
        add(exit,BorderLayout.NORTH);

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setContentPane(mainPane);
                frame.pack();
            }
        });

        table.getModel().addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                int row = e.getFirstRow();
                int column = e.getColumn();
                if (column == 4) {
                    Object o = table.getValueAt(row,column);
                    Animal anim = market.getAnimals().get(Integer.valueOf(table.getValueAt(row, 0).toString()));
                    if (checkString(o.toString())) {
                        market.setPrice(anim,Double.parseDouble(o.toString()));
                    }
                }
            }
        });

    }


}
