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
package org.roiderh.dnc.toolbar;

import java.util.ArrayList;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import org.roiderh.dnc.Properties;

/**
 *
 * @author Herbert Roider <herbert.roider@utanet.at>
 */
public class ConfigPropertiesListModel extends DefaultComboBoxModel<Properties> {

        private ArrayList<Properties> properties = new ArrayList<Properties>();
        public void setProperties(ArrayList<Properties> lp){
                this.properties = lp;
                int last_index = this.properties.size()-1;
                if(last_index < 0){
                        last_index = 0;
                }
                this.fireContentsChanged(this, 0, last_index);
        }
        
//        public int add_row(Properties p) {
//                int id = 0;
//                this.properties.add(p);
//        //fill_table_data( m_day);
//                this.fireIntervalAdded(this, this.properties.size()-1, this.properties.size()-1);
//                return this.properties.size();
//        }
//
//        public boolean delete_row(int index) {
//                this.properties.remove(index);
//        //fill_table_data( m_day);
//                this.fireIntervalRemoved(this, index, index);
//                return true;
//
//        }
//        public boolean update_row(int index, Properties p){
//                this.properties.set(index, p);
//                this.fireContentsChanged(this, index, index);
//                return true;
//        }
        public Properties get_row(int index){
                return this.properties.get(index);
        }       
        
        
        
//        @Override
//        public org.roiderh.dnc.Properties getSelectedItem() {
//                
//                org.roiderh.dnc.Properties p = (Properties)super.getSelectedItem();              
//                return p;
//
//        }
//
//        @Override
//        public void setSelectedItem(Object _p) {
//                
//                for(int i=0;i<this.properties.size();i++){
//                        if(((org.roiderh.dnc.Properties)_p).uid == this.properties.get(i).uid){
//                              break;  
//                        }
//                }
//                
//                
//                org.roiderh.dnc.Properties p = (org.roiderh.dnc.Properties) _p;
//
//        }

        @Override
        public int getSize() {
                return this.properties.size();
        }

        @Override
        public Properties getElementAt(int i) {
                if (this.properties.size() <= 0) {
                        return null;
                }
                Properties p = this.properties.get(i);
                return p;
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

}
