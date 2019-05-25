package theo.util;

import javax.swing.JFileChooser;
import javax.swing.JTextArea;

public class ChooseFolderUtil {

	// ����Դ�ļ���Ŀ¼��ַ
	public static String getChoseSourcePath(JFileChooser fc, JTextArea log) {
		String folderPath = "";
		int status = fc.showOpenDialog(null);
		if (status == JFileChooser.APPROVE_OPTION) {
			// ��ȡĿ¼��ַ
			folderPath = fc.getSelectedFile().getAbsolutePath();
			log.append(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> \n");
			log.append("��Դ�ļ���: " + folderPath + ".\n");
		} else {
			log.append("�����´�Դ�ļ��С�\n");
		}
		return folderPath;
	}

	// ����Ŀ��Ŀ¼��ַ
	public static String getChoseDestinationPath(JFileChooser fc,
			JTextArea log) {
		String folderPath = "";
		int status = fc.showOpenDialog(null);
		if (status == JFileChooser.APPROVE_OPTION) {
			folderPath = fc.getSelectedFile().getAbsolutePath();
		}else{
			log.append("�����´�Ŀ���ļ��С�\n");
		}
		return folderPath;
	}

}
