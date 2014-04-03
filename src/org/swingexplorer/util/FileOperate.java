package org.swingexplorer.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.List;
import java.util.ListIterator;
import java.util.StringTokenizer;

/**
 *   文件操作工具类
 *   1. 返回文件夹中所有文件列表
 *   2. 读取文本文件内容
 *   3. 新建目录
 *   4. 新建多级目录
 *   5. 新建文件
 *   6. 有编码方式的创建文件
 *   7. 删除文件
 *   8. 删除指定文件夹下所有文件
 *   9. 复制单个文件
 *   10. 复制整个文件夹的内容
 *   11. 移动文件
 *   12. 移动目录
 *   13. 建立一个可以追加的bufferedwriter
 *   14. 得到一个bufferedreader
 * @author lilz-2686
 *
 */
public class FileOperate {

	/**
	 * 遍历文件夹中文件
	 * 
	 * @param filepath
	 * @return 返回file［］数组
	 */
	public static File[] getFileList(String filepath) {
		File d = null;
		File list[] = null;
		// 建立当前目录中文件的File对象
		try {
			d = new File(filepath);
			if (d.exists()) {
				list = d.listFiles();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		// 取得代表目录中所有文件的File对象数组

		return list;
	}

	/**
	 * 读取文本文件内容
	 * 
	 * @param filePathAndName
	 *            带有完整绝对路径的文件名
	 * @param encoding
	 *            文本文件打开的编码方式
	 * @return 返回文本文件的内容
	 */
	public static String readTxt(String filePathAndName, String encoding)
			throws IOException {
		encoding = encoding.trim();
		StringBuffer str = new StringBuffer("");
		String st = "";
		try {
			FileInputStream fs = new FileInputStream(filePathAndName);
			InputStreamReader isr;
			if (encoding.equals("")) {
				isr = new InputStreamReader(fs);
			} else {
				isr = new InputStreamReader(fs, encoding);
			}
			BufferedReader br = new BufferedReader(isr);
			try {
				String data = "";
				while ((data = br.readLine()) != null) {
					str.append(data);
				}
			} catch (Exception e) {
				str.append(e.toString());
			}
			st = str.toString();
			if (st != null && st.length() > 1)
				st = st.substring(0, st.length() - 1);
		} catch (IOException es) {
			st = "";
		}
		return st;
	}

	/**
	 * 新建目录
	 * 
	 * @param folderPath
	 *            目录
	 * @return 返回目录创建后的路径
	 */
	public static String createFolder(String folderPath) {
		String txt = folderPath;
		try {
			File myFilePath = new File(txt);
			txt = folderPath;
			if (!myFilePath.exists()) {
				myFilePath.mkdir();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return txt;
	}

	/**
	 * 多级目录创建
	 * 
	 * @param folderPath
	 *            准备要在本级目录下创建新目录的目录路径例如 c:myf
	 * @param paths
	 *            无限级目录参数，各级目录以单数线区分 例如 a|b|c
	 * @return 返回创建文件后的路径
	 */
	public static String createFolders(String folderPath, String paths) {
		String txts = folderPath;
		try {
			String txt;
			txts = folderPath;
			StringTokenizer st = new StringTokenizer(paths, "|");
			for (int i = 0; st.hasMoreTokens(); i++) {
				txt = st.nextToken().trim();
				if (txts.lastIndexOf("/") != -1) {
					txts = createFolder(txts + txt);
				} else {
					txts = createFolder(txts + txt + "/");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return txts;
	}

	/**
	 * 新建文件
	 * 
	 * @param filePathAndName
	 *            文本文件完整绝对路径及文件名
	 * @param fileContent
	 *            文本文件内容
	 * @return
	 */
	public static void createFile(String filePathAndName, String fileContent) {

		try {
			String filePath = filePathAndName;
			filePath = filePath.toString();
			File myFilePath = new File(filePath);
			if (!myFilePath.exists()) {
				myFilePath.createNewFile();
			}
			FileWriter resultFile = new FileWriter(myFilePath);
			PrintWriter myFile = new PrintWriter(resultFile);
			String strContent = fileContent;
			myFile.println(strContent);
			myFile.close();
			resultFile.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 有编码方式的文件创建
	 * 
	 * @param filePathAndName
	 *            文本文件完整绝对路径及文件名
	 * @param fileContent
	 *            文本文件内容
	 * @param encoding
	 *            编码方式 例如 GBK 或者 UTF-8
	 * @return
	 */
	public static void createFile(String filePathAndName, String fileContent,
			String encoding) {

		try {
			String filePath = filePathAndName;
			filePath = filePath.toString();
			File myFilePath = new File(filePath);
			if (!myFilePath.exists()) {
				myFilePath.createNewFile();
			}
			PrintWriter myFile = new PrintWriter(myFilePath, encoding);
			String strContent = fileContent;
			myFile.println(strContent);
			myFile.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 删除文件
	 * 
	 * @param filePathAndName
	 *            文本文件完整绝对路径及文件名
	 * @return Boolean 成功删除返回true遭遇异常返回false
	 */
	public static void delFile(String filePathAndName) {
		try {	
			File file = new File(filePathAndName);
			File dirFile = file.getParentFile();
			if (!file.getParentFile().exists()) {
				dirFile.mkdirs();
				file.delete();
			}
		} catch (Exception e) {
				// TODO: handle exception
	      }
	}

	/**
	 * 删除文件
	 * 
	 * @param folderPath
	 *            文件夹完整绝对路径
	 * @return
	 */
	public static void  delFolder(String folderPath) {
		try {
			delAllFile(folderPath); // 删除完里面所有内容
			String filePath = folderPath;
			filePath = filePath.toString();
			File myFilePath = new File(filePath);
			myFilePath.delete(); // 删除空文件夹
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 删除指定文件夹下所有文件
	 * 
	 * @param path
	 *            文件夹完整绝对路径
	 * @return
	 * @return
	 */
	public static boolean delAllFile(String path) {
		boolean bea = false;
		File file = new File(path);
		if (!file.exists()) {
			return bea;
		}
		if (!file.isDirectory()) {
			return bea;
		}
		String[] tempList = file.list();
		File temp = null;
		for (int i = 0; i < tempList.length; i++) {
			if (path.endsWith(File.separator)) {
				temp = new File(path + tempList[i]);
			} else {
				temp = new File(path + File.separator + tempList[i]);
			}
			if (temp.isFile()) {
				temp.delete();
			}
			if (temp.isDirectory()) {
				delAllFile(path + "/" + tempList[i]);// 先删除文件夹里面的文件
				delFolder(path + "/" + tempList[i]);// 再删除空文件
				bea = true;
			}
		}
		return bea;
	}

	/**
	 * 复制单个文件
	 * 
	 * @param oldPathFile
	 *            准备复制的文件源
	 * @param newPathFile
	 *            拷贝到新绝对路径带文件名
	 * @return
	 */
	public static void copyFile(String oldPathFile, String newPathFile) {
		try {
			int bytesum = 0;
			int byteread = 0;
			File oldfile = new File(oldPathFile);
			if (oldfile.exists()) { // 文件存在
				InputStream inStream = new FileInputStream(oldPathFile); // 读入源文件
				FileOutputStream fs = new FileOutputStream(newPathFile);
				byte[] buffer = new byte[1444];
				while ((byteread = inStream.read(buffer)) != -1) {
					bytesum += byteread; // 字节 文件大小
//					System.out.println(bytesum);
					fs.write(buffer, 0, byteread);
				}
				inStream.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 复制整个文件夹的内容
	 * 
	 * @param oldPath
	 *            准备拷贝的目录
	 * @param newPath
	 *            指定绝对路径的新目录
	 * @return
	 */
	public static void copyFolder(String oldPath, String newPath) {
		try {
			new File(newPath).mkdirs(); // 如果文件夹不存在 则建立新文件
			File a = new File(oldPath);
			String[] file = a.list();
			File temp = null;
			for (int i = 0; i < file.length; i++) {
				if (oldPath.endsWith(File.separator)) {
					temp = new File(oldPath + file[i]);
				} else {
					temp = new File(oldPath + File.separator + file[i]);
				}
				if (temp.isFile()) {
					FileInputStream input = new FileInputStream(temp);
					FileOutputStream output = new FileOutputStream(newPath
							+ "/" + (temp.getName()).toString());
					byte[] b = new byte[1024 * 5];
					int len;
					while ((len = input.read(b)) != -1) {
						output.write(b, 0, len);
					}
					output.flush();
					output.close();
					input.close();
				}
				if (temp.isDirectory()) {// 如果是子文件
					copyFolder(oldPath + "/" + file[i], newPath + "/" + file[i]);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 移动文件
	 * 
	 * @param oldPath
	 * @param newPath
	 * @return
	 */
	public static void moveFile(String oldPath, String newPath) {
		copyFile(oldPath, newPath);
		delFile(oldPath);
	}

	/**
	 * 移动目录
	 * 
	 * @param oldPath
	 * @param newPath
	 * @return
	 */
	public static void moveFolder(String oldPath, String newPath) {
		copyFolder(oldPath, newPath);
		delFolder(oldPath);
	}

	/**
	 * 建立一个可以追加的bufferedwriter
	 * 
	 * @param fileDir
	 * @param fileName
	 * @return
	 */
	public static BufferedWriter getWriter(String fileDir, String fileName) {
		try {
			File f1 = new File(fileDir);
			if (!f1.exists()) {
				f1.mkdirs();
			}
			f1 = new File(fileDir, fileName);
			if (!f1.exists()) {
				f1.createNewFile();
			}
			BufferedWriter bw = new BufferedWriter(new FileWriter(f1.getPath(),
					true));
			return bw;
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
			return null;
		}
	}

	/**
	 * 得到一个bufferedreader
	 * 
	 * @param fileDir
	 * @param fileName
	 * @param encoding
	 * @return
	 */
	public static BufferedReader getReader(String fileDir, String fileName,
			String encoding) {
		try {
			File file = new File(fileDir, fileName);
			InputStreamReader read = new InputStreamReader(new FileInputStream(
					file), encoding);
			BufferedReader br = new BufferedReader(read);
			return br;

		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

	}
	
	/**
	 * 将list中内容写入文件
	 * @param fileName
	 * @param listStrs
	 * @param flag 是否追加
	 */
	public static void writeFile (String fileName, List<String> listStrs, boolean flag) {
		File file = null;
		try {	
			file = new File(fileName);
			File dirFile = file.getParentFile();
			if (!file.getParentFile().exists()) {
				dirFile.mkdirs();
			}
			file.createNewFile();
			
//			FileWriter fileWriter = new FileWriter(file, flag);
			OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(fileName, true), "UTF-8");
			BufferedWriter buffWriter = new BufferedWriter(osw);
			

			
			ListIterator<String> iterListStrs = listStrs.listIterator();
			while (iterListStrs.hasNext()) {
				String nextMapPath = iterListStrs.next();
				buffWriter.write(nextMapPath);
				buffWriter.newLine();
			}
			
			buffWriter.close();
			osw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

    /**
     * 设置指定的文件为隐藏
     * @param fileName
     */
    public static void setFileHide(String fileName) {
        try {
            File xmlfile = new File(fileName);
            if (xmlfile.exists()) {
                String setFileHideSets = "attrib +H \"" + fileName + "\"";
                Runtime.getRuntime().exec(setFileHideSets);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
}


















