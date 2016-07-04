package com.company.restaurant.controllers;

import com.company.restaurant.dao.TableDao;
import com.company.restaurant.model.Table;

import java.util.List;

public class TableControllerImpl implements TableController {
    private TableDao tableDao;

    public void setTableDao(TableDao tableDao) {
        this.tableDao = tableDao;
    }

    @Override
    public Table addTable(Table table) {
        return tableDao.addTable(table);
    }

    @Override
    public void delTable(Table table) {
        tableDao.delTable(table);
    }

    @Override
    public Table findTableById(int tableId) {
        return tableDao.findTableById(tableId);
    }

    @Override
    public Table findTableByNumber(int number) {
        return tableDao.findTableByNumber(number);
    }

    @Override
    public List<Table> findAllTables() {
        return tableDao.findAllTables();
    }
}