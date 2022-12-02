package constants;

import java.awt.Color;

public class GEConstants {
	//MainFrame
	public static final int WIDTH_MAINFRAME = 400;
	public static final int HEIGHT_MAINFRAME = 600;
	public static final String TITLE_MAINFRAME = "GE-11";
	
	//MenuBar
	public static final String TITLE_FILEMENU = "파일";
	public static final String TITLE_EDITMENU = "편집";
	public static final String TITLE_COLORMENU = "컬러";
	
	//GEMenuItem
	public static enum EFileMenuItems {새로만들기, 열기, 저장, 다른이름으로저장, 종료}
	public static enum EEditMenuItems {Undo, Redo, 삭제, 잘라내기, 복사, 붙이기, Group, Ungroup}
	public static enum EColorMenuItems {SetLineColor, ClearLineColor, SetFillColor, ClearFillColor}
	
	//GEToolBar
	public static final String TITLE_SHAPETOOLBAR = "Shape Tools";
	public static enum EToolBarButtons {select, rectangle, line, ellipse, polygon};
	public static final String SUFFIX_TOOLBAR_BTN = ".gif";
	public static final String SUFFIX_TOOLBAR_BTN_SLT = "SLT.gif";
	public static final String IMG_URL = "images/";
	
	//GEDrawingPanel
	public static final Color FOREGROUND_COLOR = Color.BLACK;
	public static final Color BACKGROUND_COLOR = Color.WHITE;
	public static enum EState {Idle, TwoPointsDrawing, NPointsDrawing, Moving}
	
	//GEAnchorList
	public static final int ANCHOR_W = 6;
	public static final int ANCHOR_H = 6;
	public static final int RR_OFFSET = 40;
	public static final Color ANCHOR_LINECOLOR = Color.BLACK;
	public static final Color ANCHOR_FILLCOLOR = Color.WHITE;
	public static enum EAnchorTypes {NW, NN, NE, WW, EE, SW, SS, SE, RR, NONE}
	
	//GEMenuColor
	public static final String FILLCOLOR_TITLE = "selected Fill Color";
	public static final String LINECOLOR_TITLE = "selected Line Color";
	
	//GETransformer
	public static final int DEFAULT_DASH_OFFSET = 4;
	public static final int DEFAULT_DASHLINE_WIDTH = 1;
}
