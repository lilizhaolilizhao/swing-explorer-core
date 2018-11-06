package org.swingexplorer.ui.popupmenu;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.table.TableModel;

import org.swingexplorer.Icons;
import org.swingexplorer.util.ClipBoardUtil;
import org.swingexplorer.util.FileOperate;

/**
 * 属性表单右键弹出菜单
 * @author lilz-2686
 *
 */
public class PNLTablePopupmenu extends JPopupMenu {

	private static final long serialVersionUID = 2713008397451691983L;
	
	private JTable tblProperties;
	
	private JMenuItem exportSelected;
	private JMenuItem exportAll;
	private JMenuItem clipToBoard;

	public PNLTablePopupmenu(JTable tblProperties) {
		this.tblProperties = tblProperties;
		
		initMenuItem();
		initListener();
	}

	/**
	 * 初始化弹出菜单项
	 */
	private void initMenuItem() {
		exportSelected = new JMenuItem("ExportSelected");
		exportAll = new JMenuItem("ExportAll");
		clipToBoard = new JMenuItem("ClipToBoard");
		
		Image popupImage = Icons.appPopupMenuImage();
		if (popupImage != null) {
			exportSelected.setIcon(new ImageIcon(popupImage));
			exportAll.setIcon(new ImageIcon(popupImage));
			clipToBoard.setIcon(new ImageIcon(popupImage));
		}
		
		add(exportSelected);
		add(exportAll);
		add(clipToBoard);
	}
	
	/**
	 * 初始化菜单项响应事件
	 */
	private void initListener() {
		exportSelected.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<String> selectedList = new ArrayList<String>();
				
				TableModel model = tblProperties.getModel();
				int[] selectedRows = tblProperties.getSelectedRows();
//				int[] selectedColumns = tblProperties.getSelectedColumns(); //列数固定，可移除
				
				for (int i = 0; i < selectedRows.length; i++) {
					String name = model.getValueAt(selectedRows[i], 0).toString();
					String value = model.getValueAt(selectedRows[i], 1).toString();
					selectedList.add(name + "=" + value);
				}
				
				FileOperate.writeFile("d:/exportSelected.txt", selectedList, true);
			}
		});
		
		exportAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<String> allList = new ArrayList<String>();
				
				TableModel model = tblProperties.getModel();
				int rowCount = model.getRowCount();
//				int columnCount = model.getColumnCount();
				
				for (int i = 0; i < rowCount; i++) {
					String name = model.getValueAt(i, 0).toString();
					String value = model.getValueAt(i, 1).toString();
					allList.add(name + "=" + value);
				}
				
				FileOperate.writeFile("d:/exportAll.txt", allList, true);
			}
		});
		
		clipToBoard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StringBuilder builder = new StringBuilder();
				
				TableModel model = tblProperties.getModel();
				int[] selectedRows = tblProperties.getSelectedRows();
				
				for (int i = 0; i < selectedRows.length; i++) {
					String name = model.getValueAt(selectedRows[i], 0).toString();
					String value = model.getValueAt(selectedRows[i], 1).toString();
					builder.append(name + "=" + value);
					builder.append("\n");
				}
				
				ClipBoardUtil.setStringClipboard(builder.toString());
			}
		});
	}

}
