package org.swingexplorer.util;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

/**
 * ���а幤��
 * 
 * @author lilz-2686
 * 
 */
public class ClipBoardUtil {

	/* ��ȡ�������е��ַ���,��������ڣ��򷵻ؿ� */
	public static String getStringClipboard()
			throws UnsupportedFlavorException, IOException {
		Transferable t = Toolkit.getDefaultToolkit().getSystemClipboard().getContents(null);
		if (t != null && t.isDataFlavorSupported(DataFlavor.stringFlavor)) {
			String text = (String) t.getTransferData(DataFlavor.stringFlavor);
			return text;
		}
		return null;
	}

	/* ���ַ���д�������� */
	public static void setStringClipboard(String str) {
		StringSelection ss = new StringSelection(str);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
	}

	/* ��ȡ�������е�ͼƬ */
	public static Image getImageClipboard() throws UnsupportedFlavorException,
			IOException {
		Transferable t = Toolkit.getDefaultToolkit().getSystemClipboard()
				.getContents(null);
		if (t != null && t.isDataFlavorSupported(DataFlavor.imageFlavor)) {
			Image image = (Image) t.getTransferData(DataFlavor.imageFlavor);
			return image;
		}
		return null;
	}

	/* ��ͼƬд���������� */
	public static void setImageClipboard(Image image) {
		ImageSelection imgSel = new ImageSelection(image);
		Toolkit.getDefaultToolkit().getSystemClipboard()
				.setContents(imgSel, null);
	}

	public static class ImageSelection implements Transferable {
		private Image image;

		public ImageSelection(Image image) {
			this.image = image;
		}

		// Returns supported flavors
		public DataFlavor[] getTransferDataFlavors() {
			return new DataFlavor[] { DataFlavor.imageFlavor };
		}

		// Returns true if flavor is supported
		public boolean isDataFlavorSupported(DataFlavor flavor) {
			return DataFlavor.imageFlavor.equals(flavor);
		}

		// Returns image
		public Object getTransferData(DataFlavor flavor)
				throws UnsupportedFlavorException, IOException {
			if (!DataFlavor.imageFlavor.equals(flavor)) {
				throw new UnsupportedFlavorException(flavor);
			}
			return image;
		}
	}

}
