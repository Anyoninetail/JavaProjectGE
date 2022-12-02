package frames;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JRadioButton;
import javax.swing.JToolBar;

import constants.GEConstants;
import constants.GEConstants.EToolBarButtons;
import shapes.GEEllipse;
import shapes.GELine;
import shapes.GEPolygon;
import shapes.GERectangle;

public class GEToolBar extends JToolBar {
	private ButtonGroup buttonGroup;
	
	private GEDrawingPanel drawingPanel;
	private GEToolBarHandler shapeToolBarHandler;
	
	public void init(GEDrawingPanel drawingPanel) {
		this.drawingPanel = drawingPanel;
		this.clickDefaultButton();
	}
	
	public GEToolBar(String label) {
		super(label);
		
		shapeToolBarHandler = new GEToolBarHandler();
		buttonGroup = new ButtonGroup();
		JRadioButton rButton = null;
		
		for(EToolBarButtons btn : EToolBarButtons.values()) {
			rButton = new JRadioButton();
			rButton.setIcon(new ImageIcon(GEConstants.IMG_URL + btn.toString() + GEConstants.SUFFIX_TOOLBAR_BTN));
			rButton.setSelectedIcon(new ImageIcon(GEConstants.IMG_URL + btn.toString() + GEConstants.SUFFIX_TOOLBAR_BTN_SLT));
			rButton.addActionListener(shapeToolBarHandler);
			rButton.setActionCommand(btn.toString());
			this.add(rButton);
			buttonGroup.add(rButton);
		}
	}
	
	private void clickDefaultButton(){
		JRadioButton rButton = (JRadioButton)this.getComponent(EToolBarButtons.rectangle.ordinal());
	    rButton.doClick();
	}
	
	public class GEToolBarHandler implements ActionListener{
		@Override //버튼을 클릭했을 때 실행되는 메소드
		public void actionPerformed(ActionEvent e) {
			JRadioButton button = (JRadioButton)e.getSource();
			if(button.getActionCommand().equals(EToolBarButtons.rectangle.name())) {
				drawingPanel.setCurrentShape(new GERectangle());
			} else if(button.getActionCommand().equals(EToolBarButtons.ellipse.name())) {
				drawingPanel.setCurrentShape(new GEEllipse());
			} else if(button.getActionCommand().equals(EToolBarButtons.line.name())) {
				drawingPanel.setCurrentShape(new GELine());
			} else if(button.getActionCommand().equals(EToolBarButtons.polygon.name())) {
				drawingPanel.setCurrentShape(new GEPolygon());
			} else if(button.getActionCommand().equals(EToolBarButtons.select.name())) {
				drawingPanel.setCurrentShape(null);
			}
		}
	}
}
