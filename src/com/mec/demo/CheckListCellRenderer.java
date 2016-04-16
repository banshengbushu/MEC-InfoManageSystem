package com.mec.demo;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

public class CheckListCellRenderer extends JCheckBox implements ListCellRenderer<Object>
{
	private static final long serialVersionUID = 1L;
	protected static Border m_nFocusBorder = new EmptyBorder(1,1,1,1);
	
	public CheckListCellRenderer()
	{
		super();
		setOpaque(true);
		setBorder(m_nFocusBorder);
	}
	public CheckListCellRenderer(String string, boolean b) {
		// TODO Auto-generated constructor stub
	}
	public Component getListCellRendererComponent(JList<? extends Object> list,
			Object value, int index, boolean isSelected, boolean cellHasFocus)
	{
		setText(value.toString());
		BaseData data = (BaseData) value;
		setSelected(data.isSelected());
		setToolTipText(data.getHelpString());
		
		setBackground(isSelected ? list.getSelectionBackground():data.getBackground());
	//	setBackground(isSelected ? Color.LIGHT_GRAY:new Color(238,238,238));
		if(list.isEnabled())
			setForeground(isSelected ? list.getForeground():data.getBackground());
		else
			setForeground(Color.LIGHT_GRAY);
		setFont(list.getFont());
		setBorder((cellHasFocus) ? UIManager.getBorder("List.focusCellHighlightBorder"):m_nFocusBorder);
		return this;
	}
	

}
