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
		// 源文件地址不能为空，且必须有文件
		if (sourceFolderPath.equals("") || sourceFiles.length == 0) {
			log.append("源文件夹中没有文件！\n");
		} else {
			// 选择存储照片目录
			String destinationFolderPath = ChooseFolderUtil.getChoseDestinationPath(
					fc, log);
			// 目标文件夹与源文件夹不能相同
			if (sourceFolderPath.equals(destinationFolderPath)) {
				log.append("错误：目标文件夹与源文件夹不能相同。\n");
			} else {
				for (int i = 0; i < sourceFiles.length; i++) {
					// 目标文件地址 前缀 数字 后缀
					File f = FileUtil.createNewFile(destinationFolderPath,
							getPrefix(prefix), getStartNum(startNum), i, sourceFiles[i],
							isContainFormerName(comboBox));
					try {
						// 拷贝
						Files.copy(sourceFiles[i].toPath(), f.toPath());
					} catch (IOException e1) {
						throw new RuntimeException("批量存储阶段失败");
					}
				}
				log.append(">>>>>>文件名修改成功！<<<<<<\n ");
				log.append("目标文件夹地址: " + destinationFolderPath + "\n");
				log.append("=========================== \n");
			}
		}
	}

	
	
	
	//是否包含原图片名
	private boolean isContainFormerName(JComboBox<?> comboBox){
		return ((YesOrNo)comboBox.getSelectedItem()).getCmd();
	}
	
	
	//图片命名的前缀，默认为""
	private String getPrefix(JTextField prefix) {
		String command = prefix.getText();
		if (command != null && !"".equals(command.trim())) {
			return command;
		} else {
			return "";
		}
	}

	// 图片命名的起始数，默认为-1,即该功能不生效
	private int getStartNum(JTextField startNum) {
		String command = startNum.getText();
		if (Pattern.matches("\\d+", command)) {
			return Integer.parseInt(command);
		} else {
			return -1;
		}

	}
}
