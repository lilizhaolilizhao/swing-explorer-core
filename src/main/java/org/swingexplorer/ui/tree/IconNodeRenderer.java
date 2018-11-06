package org.swingexplorer.ui.tree;

import java.awt.Component;

import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

import org.swingexplorer.FRMSwingExplorer;
import org.swingexplorer.Icons;
import org.swingexplorer.model.TreeNodeObject;

/**
 * 对树叶节点进行渲染
 * @author lilz-2686
 *
 */
public class IconNodeRenderer extends DefaultTreeCellRenderer {

	private static final long serialVersionUID = 5147384171816062975L;

	public Component getTreeCellRendererComponent(JTree tree, Object value,
			boolean sel, boolean expanded, boolean leaf, int row,
			boolean hasFocus) {

		super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf,
				row, hasFocus);

		if (leaf) {
			setIcon(Icons.getBrownNullFolder());
		} else {
			if (value != null && !("null").equals(value) && value instanceof DefaultMutableTreeNode) {
				
				Component comp = ((TreeNodeObject) ((DefaultMutableTreeNode)value).getUserObject()).getComponent();
				
				if ((comp != null && !("null").equals(comp)) && 
						(comp instanceof FRMSwingExplorer || SwingUtilities.getWindowAncestor(comp) instanceof FRMSwingExplorer)) {
					setIcon(Icons.getRedFolder());
				} else {
					setIcon(Icons.getBrownFolder());
				}
			} else {
				setIcon(Icons.getBrownFolder());
			}
		}

		return this;
	}
}