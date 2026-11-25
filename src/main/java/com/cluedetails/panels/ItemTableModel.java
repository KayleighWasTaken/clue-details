package com.cluedetails.panels;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

class ItemTableModel extends AbstractTableModel
{
    private final List<ItemDetails> items = new ArrayList<>();
    private List<ItemDetails> visibleItems = new ArrayList<>();
    static final int DEFAULT_COLUMN_COUNT = 3;

    private final int columnCount;

    public ItemTableModel()
    {
        super();
        columnCount = DEFAULT_COLUMN_COUNT;
    }

    public ItemTableModel(int cols)
    {
        super();
        columnCount = cols;
    }

    public void setItems(Collection<ItemDetails> newItems)
    {
        items.clear();
        items.addAll(newItems);
        visibleItems = items;
        fireTableDataChanged();
    }

    public void filterItems(String searchText)
    {
        final String[] searchTerms = searchText.toLowerCase().split("\\s+");

        visibleItems = items.stream().filter(item ->
            Arrays.stream(searchTerms)
                .allMatch(keyword ->
                    item.getName().toLowerCase().contains(keyword)
                    || Integer.toString(item.getId()).contains(keyword)
                )
        ).collect(Collectors.toCollection(ArrayList::new));
        fireTableDataChanged();
    }

    @Override
    public int getRowCount()
    {
        int itemCount = visibleItems.size();
        return itemCount % columnCount == 0 ? itemCount / columnCount : itemCount / columnCount + 1;
    }

    @Override
    public int getColumnCount()
    {
        return columnCount;
    }

    @Override
    public ItemDetails getValueAt(int rowIndex, int columnIndex)
    {
        int itemIndex = rowIndex * columnCount + columnIndex;
        return itemIndex >= 0 && itemIndex < visibleItems.size() ? visibleItems.get(itemIndex) : null;
    }
}
