package menus;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import constants.GEConstants.EEditMenuItems;

public class GEMenuEdit extends JMenu {
	public GEMenuEdit(String titleEditMenu) {
		super(titleEditMenu);
		
		for(EEditMenuItems btn: EEditMenuItems.values()) {
			JMenuItem menuItem = new JMenuItem(btn.toString());
			this.add(menuItem);
		}
	}
}
