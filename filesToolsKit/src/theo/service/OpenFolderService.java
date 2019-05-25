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
		// ��Ҫת�����ļ�Ŀ¼
		String sourceFolderPath = ChooseFolderUtil.getChoseSourcePath(fc, log);
		// �ļ��в�Ϊ��ʱ
		File[] sourceFiles = null;
		if (!sourceFolderPath.equals("")) {
			// �����ļ��ж���
			File sourceFolder = new File(sourceFolderPath);
			// ��ȡ����Դ�ļ�
			sourceFiles = sourceFolder.listFiles();
		}		
		result.put(Const.SOURCE_PATH, sourceFolderPath);
		result.put(Const.SOURCE_FILES, sourceFiles);
		return result;
	}
}
