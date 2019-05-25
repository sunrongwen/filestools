package theo.service;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.regex.Pattern;

import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import theo.constant.YesOrNo;
import theo.util.ChooseFolderUtil;
import theo.util.FileUtil;

public class ChangeNameService {

	public void changeNameSaveSources(ActionEvent e, JFileChooser fc,JTextArea log, String sourceFolderPath,
			File[] sourceFiles, JTextField prefix, JTextField startNum,JComboBox<?> comboBox) {
		// Դ�ļ���ַ����Ϊ�գ��ұ������ļ�
		if (sourceFolderPath.equals("") || sourceFiles.length == 0) {
			log.append("Դ�ļ�����û���ļ���\n");
		} else {
			// ѡ��洢��ƬĿ¼
			String destinationFolderPath = ChooseFolderUtil.getChoseDestinationPath(
					fc, log);
			// Ŀ���ļ�����Դ�ļ��в�����ͬ
			if (sourceFolderPath.equals(destinationFolderPath)) {
				log.append("����Ŀ���ļ�����Դ�ļ��в�����ͬ��\n");
			} else {
				for (int i = 0; i < sourceFiles.length; i++) {
					// Ŀ���ļ���ַ ǰ׺ ���� ��׺
					File f = FileUtil.createNewFile(destinationFolderPath,
							getPrefix(prefix), getStartNum(startNum), i, sourceFiles[i],
							isContainFormerName(comboBox));
					try {
						// ����
						Files.copy(sourceFiles[i].toPath(), f.toPath());
					} catch (IOException e1) {
						throw new RuntimeException("�����洢�׶�ʧ��");
					}
				}
				log.append(">>>>>>�ļ����޸ĳɹ���<<<<<<\n ");
				log.append("Ŀ���ļ��е�ַ: " + destinationFolderPath + "\n");
				log.append("=========================== \n");
			}
		}
	}

	
	
	
	//�Ƿ����ԭͼƬ��
	private boolean isContainFormerName(JComboBox<?> comboBox){
		return ((YesOrNo)comboBox.getSelectedItem()).getCmd();
	}
	
	
	//ͼƬ������ǰ׺��Ĭ��Ϊ""
	private String getPrefix(JTextField prefix) {
		String command = prefix.getText();
		if (command != null && !"".equals(command.trim())) {
			return command;
		} else {
			return "";
		}
	}

	// ͼƬ��������ʼ����Ĭ��Ϊ-1,���ù��ܲ���Ч
	private int getStartNum(JTextField startNum) {
		String command = startNum.getText();
		if (Pattern.matches("\\d+", command)) {
			return Integer.parseInt(command);
		} else {
			return -1;
		}

	}
}
