package theo.service;

import java.awt.event.ActionEvent;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFileChooser;
import javax.swing.JTextArea;

import theo.constant.Const;
import theo.util.ChooseFolderUtil;

public class OpenFolderService {

	
	public Map<String,Object> openSources(ActionEvent e, JFileChooser fc,JTextArea log) {
		Map<String, Object> result=new HashMap<String,Object>();
		fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		// 打开要转换的文件目录
		String sourceFolderPath = ChooseFolderUtil.getChoseSourcePath(fc, log);
		// 文件夹不为空时
		File[] sourceFiles = null;
		if (!sourceFolderPath.equals("")) {
			// 创建文件夹对象
			File sourceFolder = new File(sourceFolderPath);
			// 获取所有源文件
			sourceFiles = sourceFolder.listFiles();
		}		
		result.put(Const.SOURCE_PATH, sourceFolderPath);
		result.put(Const.SOURCE_FILES, sourceFiles);
		return result;
	}
}
