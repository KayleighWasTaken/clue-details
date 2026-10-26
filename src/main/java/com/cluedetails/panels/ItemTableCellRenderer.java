package com.cluedetails.panels;

import net.runelite.client.ui.ColorScheme;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

class ItemTableCellRenderer extends JPanel implements TableCellRenderer
{
    private final JLabel iconLabel = new JLabel();
    private final JTextArea nameTextArea = JGenerator.makeJTextArea();
    private final static EmptyBorder UNSELECTED_BORDER = new EmptyBorder(5, 5, 5, 5);
    private final static CompoundBorder SELECTED_BORDER = new CompoundBorder(
            UNSELECTED_BORDER,
            new LineBorder(ColorScheme.BRAND_ORANGE, 1)
    );

    public ItemTableCellRenderer()
    {
        setOpaque(true);

        // Definitely a better way to do this
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.gridx = 0;
        c.gridy = 0;
        c.gridheight = 3;
        c.gridwidth = 1;
        add(iconLabel, c);

        c.gridx = 1;
        c.gridy = 2;
        c.gridheight = 1;
        c.gridwidth = 5;
        add(nameTextArea, c);

        setBorder(UNSELECTED_BORDER);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
    {
        if (value != null)
        {
            ItemDetails details = (ItemDetails) value;
            nameTextArea.setText(details.getName());
            iconLabel.setIcon(details.getIcon());
        } else
        {
            nameTextArea.setText("");
            iconLabel.setIcon(null);
        }

        if (isSelected)
        {
            setBorder(SELECTED_BORDER);
        } else
        {
            setBorder(UNSELECTED_BORDER);
        }

        return this;
    }
}
