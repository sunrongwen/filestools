package theo.util;

import javax.swing.JFileChooser;
import javax.swing.JTextArea;

public class ChooseFolderUtil {

	// 返回源文件的目录地址
	public static String getChoseSourcePath(JFileChooser fc, JTextArea log) {
		String folderPath = "";
		int status = fc.showOpenDialog(null);
		if (status == JFileChooser.APPROVE_OPTION) {
			// 获取目录地址
			folderPath = fc.getSelectedFile().getAbsolutePath();
			log.append(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> \n");
			log.append("打开源文件夹: " + folderPath + ".\n");
		} else {
			log.append("请重新打开源文件夹。\n");
		}
		return folderPath;
	}

	// 返回目标目录地址
	public static String getChoseDestinationPath(JFileChooser fc,
			JTextArea log) {
		String folderPath = "";
		int status = fc.showOpenDialog(null);
		if (status == JFileChooser.APPROVE_OPTION) {
			folderPath = fc.getSelectedFile().getAbsolutePath();
		}else{
			log.append("请重新打开目标文件夹。\n");
		}
		return folderPath;
	}

}
