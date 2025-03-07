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

import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionRegistration;
import org.openide.util.NbBundle.Messages;
import javax.swing.AbstractAction;
import org.openide.util.actions.Presenter;
import java.awt.Component;

@ActionID(
        category = "File",
        id = "org.roiderh.dnc.toolbar.DncAction"
)
@org.openide.awt.ActionRegistration(
        lazy = false,
        displayName = "NOT-USED"
)
@ActionReference(path = "Toolbars/File", position = 0)
@Messages("CTL_DncAction=Dnc")
public final class DncAction extends AbstractAction implements Presenter.Toolbar {
        /**
         * Set the DnctoolbarPanel in the toolbar
         * @return 
         */
        @Override
        public Component getToolbarPresenter() {
                return new org.roiderh.dnc.toolbar.DnctoolbarPanel();
        }

        @Override
        public void actionPerformed(ActionEvent e) {
                // TODO implement action body
                //System.out.println("toolbar action performed");
        }
}
