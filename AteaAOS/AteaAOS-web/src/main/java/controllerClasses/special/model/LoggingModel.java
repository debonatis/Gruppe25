/*
 * Copyright (C) 2014 simond
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package controllerClasses.special.model;

import controllerClasses.util.LogSorter;
import entityModels.Logging;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

/**
 *
 * @author simond
 */
public class LoggingModel extends LazyDataModel<Logging>{
    
    private List<Logging> datasource;
     
    public LoggingModel(List<Logging> datasource) {
        this.datasource = datasource;
    }
     
    @Override
    public Logging getRowData(String rowKey) {
        for(Logging log : datasource) {
            if(log.getDated().equals(rowKey))
                return log;
        }
 
        return null;
    }
 
    @Override
    public Object getRowKey(Logging car) {
        return car.getDated();
    }
 
    
    @Override
    public List<Logging> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String,String> filters) {
        List<Logging> data = new ArrayList<>();
 
        //filter
        for(Logging log : datasource) {
            boolean match = true;
 
            if (filters != null) {
                for (Iterator<String> it = filters.keySet().iterator(); it.hasNext();) {
                    try {
                        String filterProperty = it.next();
                        Object filterValue = filters.get(filterProperty);
                        String fieldValue = String.valueOf(log.getClass().getDeclaredField(filterProperty).get(log));
 
                        if(filterValue == null || fieldValue.startsWith(filterValue.toString())) {
                            match = true;
                    }
                    else {
                            match = false;
                            break;
                        }
                    } catch(Exception e) {
                        match = false;
                    }
                }
            }
 
            if(match) {
                data.add(log);
            }
        }
 
        //sort
        if(sortField != null) {
            Collections.sort(data, new LogSorter(sortField, sortOrder));
        }
 
        //rowCount
        int dataSize = data.size();
        this.setRowCount(dataSize);
 
        //paginate
        if(dataSize > pageSize) {
            try {
                return data.subList(first, first + pageSize);
            }
            catch(IndexOutOfBoundsException e) {
                return data.subList(first, first + (dataSize % pageSize));
            }
        }
        else {
            return data;
        }
    }
}
