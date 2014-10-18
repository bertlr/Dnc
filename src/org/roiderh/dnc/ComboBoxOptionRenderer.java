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

import javax.swing.JLabel;
import javax.swing.ListCellRenderer;
import javax.swing.*;
import java.awt.Component;

/**
 *
 * @author Herbert Roider <herbert.roider@utanet.at>
 */
public class ComboBoxOptionRenderer extends JLabel implements ListCellRenderer {

        public Component getListCellRendererComponent(
                JList list,
                Object value,
                int index,
                boolean isSelected,
                boolean cellHasFocus) {
     
                setText(((Option) value).name);

                setFont(list.getFont());

                return this;
        }
}
