package theo.dispatch;

import java.awt.event.ActionEvent;
import java.io.File;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import theo.constant.Const;
import theo.service.OpenFolderService;
import theo.service.PhotoResizeService;

public class PhotoResizeWorkflow {
	private static String sourcePath;
	private static File[] sourceFiles;
	private static OpenFolderService openService=new OpenFolderService();
	private static PhotoResizeService service=new PhotoResizeService();
	
	public static void reSize(ActionEvent e, JFileChooser fc,JTextArea log, JTextField ratio,
			JButton openButton,JButton saveButton){		
		//获取选择的文件夹地址和文件夹中的文件
		if(e.getSource()==openButton){
			Map<String, Object> result=openService.openSources(e, fc, log);
			sourcePath=(String) result.get(Const.SOURCE_PATH);
			sourceFiles=(File[]) result.get(Const.SOURCE_FILES);
		//存储处理后的文件到目的地
		}else if(e.getSource()==saveButton){
			service.reSizeSaveSources(e, fc, log, sourcePath, sourceFiles, ratio);
		}	
	}

}
