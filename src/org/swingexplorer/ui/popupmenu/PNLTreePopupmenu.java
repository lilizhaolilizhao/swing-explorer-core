package org.swingexplorer.ui.popupmenu;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTree;

import org.swingexplorer.Icons;
import org.swingexplorer.util.SysUtils;

/**
 * �����δѡ��treepathʱ���Ҽ������˵�
 * @author lilz-2686
 *
 */
public class PNLTreePopupmenu extends JPopupMenu {

	private static final long serialVersionUID = 2713008397451691983L;
	
	private JTree componentTree;
	private JTree treDisplayed;
	
	private JMenuItem expandTree;
	private JMenuItem collapseTree;

	public PNLTreePopupmenu(JTree componentTree, JTree treDisplayed) {
		this.componentTree = componentTree;
		this.treDisplayed = treDisplayed;
		
		initMenuItem();
		initAction();
	}

	/**
	 * ��ʼ�������˵���
	 */
	private void initMenuItem() {
		expandTree = new JMenuItem("ExpandTree");
		collapseTree = new JMenuItem("CollapseTree");
		
		Image popupImage = Icons.appPopupMenuImage();
		if (popupImage != null) {
			expandTree.setIcon(new ImageIcon(popupImage));
			collapseTree.setIcon(new ImageIcon(popupImage));
		}
		
		add(expandTree);
		add(collapseTree);
	}
	
	/**
	 * ��ʼ���˵�����Ӧ�¼�
	 */
	private void initAction() {
		expandTree.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SysUtils.expandTree(componentTree);
				SysUtils.expandTree(treDisplayed);
			}
		});
		
		collapseTree.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SysUtils.collapseTree(componentTree, false);
				SysUtils.collapseTree(treDisplayed, true);
			}
		});
	}
}
