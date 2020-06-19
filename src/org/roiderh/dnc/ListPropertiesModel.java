/*
 * Copyright (C) 2014 by Herbert Roider <herbert.roider@utanet.at>
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
package org.roiderh.dnc;

import javax.swing.AbstractListModel;
import java.util.ArrayList;

/**
 *
 * @author Herbert Roider <herbert.roider@utanet.at>
 */
public class ListPropertiesModel extends AbstractListModel<String> {

    private ArrayList<Properties> properties = new ArrayList<>();

    public int add_row(Properties p) {
        int id = 0;
        this.properties.add(p);
        this.fireIntervalAdded(this, this.properties.size() - 1, this.properties.size() - 1);
        return this.properties.size();
    }

    public boolean delete_row(int index) {
        this.properties.remove(index);
        this.fireIntervalRemoved(this, index, index);
        return true;

    }

    public boolean update_row(int index, Properties p) {
        this.properties.set(index, p);
        this.fireContentsChanged(this, index, index);
        return true;
    }

    public Properties get_row(int index) {
        return this.properties.get(index);
    }

    @Override
    public int getSize() {
        return this.properties.size();
    }

    @Override
    public String getElementAt(int i) {
        if (this.properties.size() <= 0) {
            return null;
        }
        Properties p = this.properties.get(i);
        return p.name;
    }
}
