/*
 *   Swing Explorer. Tool for developers exploring Java/Swing-based application internals. 
 * 	 Copyright (C) 2012, Maxim Zakharenkov
 *
 *   This library is free software; you can redistribute it and/or
 *   modify it under the terms of the GNU Lesser General Public
 *   License as published by the Free Software Foundation; either
 *   version 2.1 of the License, or (at your option) any later version.
 *
 *   This library is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *   Lesser General Public License for more details.
 *
 *   You should have received a copy of the GNU Lesser General Public
 *   License along with this library; if not, write to the Free Software
 *   Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 *   
 */
package org.swingexplorer.properties;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;

import org.swingexplorer.plaf.CustomTableHeaderUI;
import org.swingexplorer.ui.popupmenu.PNLTablePopupmenu;
import org.swingexplorer.util.ClipBoardUtil;

/**
 *
 * @author  Maxim Zakharenkov
 */
public class PNLPropertySheet extends JPanel {

	private static final long serialVersionUID = -6392704419402057841L;
	
	MdlProperties mdlProperties;
	JTable tblProperties;
	JScrollPane scpTable;
	
	public PNLPropertySheet() {
		
		tblProperties = new JTable();
		mdlProperties = new MdlProperties();
		tblProperties.setModel(mdlProperties);
		
		scpTable = new JScrollPane();
		scpTable.setViewportView(tblProperties);
		
		this.setLayout(new BorderLayout());
		this.add(scpTable, BorderLayout.CENTER);
		tblProperties.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tblProperties.getColumnModel().getColumn(0).setPreferredWidth(80);
		tblProperties.getColumnModel().getColumn(1).setPreferredWidth(200);
		
		JTableHeader header = tblProperties.getTableHeader();
		header.setUI(new CustomTableHeaderUI());
		
		tblProperties.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent me) {
				// 鼠标双击两次选中的单行value拷贝到剪切板
				if(me.getButton() == MouseEvent.BUTTON1 && me.getClickCount() >= 2){
					TableModel model = tblProperties.getModel();
					int[] selectedRows = tblProperties.getSelectedRows();
					if (tblProperties.getSelectedColumnCount() <= 1) {
						String value = model.getValueAt(selectedRows[0], 1).toString();
						
						if (value.startsWith("class") && value.contains(" ")) {
							value = value.substring(value.indexOf(" ") + 1);
						}
						ClipBoardUtil.setStringClipboard(value);
					}
				} 
				
				// 添加了鼠标右键弹出菜单功能
				if(me.getButton() == MouseEvent.BUTTON3){
					PNLTablePopupmenu pnlPopupmenu = new PNLTablePopupmenu(tblProperties);
					
					pnlPopupmenu.show(me.getComponent(), me.getX(), me.getY());
				} 
			}
		});
	}
	
	@Override
	public void setName(String name) {
		super.setName(name);
		tblProperties.setName(name + ".tblProperties");
	}
    
    @Override
    public void setToolTipText(String text) {
        tblProperties.setToolTipText(text);
    }
    
    @Override
    public String getToolTipText() {
        return tblProperties.getToolTipText();
    }
    
    public void setColumnSize(int column, int size) {
        tblProperties.getColumnModel().getColumn(column).setPreferredWidth(size);
    }
	
	public void setBean(Object _bean) {
		mdlProperties.setBean(_bean);
	}
	
	public Object getBean() {
		return mdlProperties.getBean();
	}
}
