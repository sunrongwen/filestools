package theo.dispatch;

import java.awt.event.ActionEvent;
import java.io.File;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import theo.constant.Const;
import theo.service.ChangeNameService;
import theo.service.OpenFolderService;

public class ChangeNameWorkflow {
	private static String sourcePath;
	private static File[] sourceFiles;
	private static OpenFolderService openService=new OpenFolderService();
	private static ChangeNameService service=new ChangeNameService();
	
	public static void changeName(ActionEvent e, JFileChooser fc,JTextArea log, JTextField prefix, JTextField base,JComboBox<?> comboBox,
			JButton openButton,JButton saveButton){		
		//��ȡѡ����ļ��е�ַ���ļ����е��ļ�
		if(e.getSource()==openButton){
			Map<String, Object> result=openService.openSources(e, fc, log);
			sourcePath=(String) result.get(Const.SOURCE_PATH);
			sourceFiles=(File[]) result.get(Const.SOURCE_FILES);
		//�洢�������ļ���Ŀ�ĵ�
		}else if(e.getSource()==saveButton){
			service.changeNameSaveSources(e, fc, log, sourcePath, sourceFiles, prefix, base, comboBox);
		}	
	}

}
