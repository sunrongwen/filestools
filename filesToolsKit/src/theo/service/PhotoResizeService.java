package theo.service;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import theo.util.ChooseFolderUtil;
import theo.util.FileUtil;

public class PhotoResizeService {

	public void reSizeSaveSources(ActionEvent e, JFileChooser fc,JTextArea log, String sourceFolderPath,
			File[] sourceFiles, JTextField ratio) {
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
					// Ŀ���ļ���ַ
					File f = FileUtil.createNewFile(destinationFolderPath, sourceFiles[i]);
					try {
						reSize(sourceFiles[i], f, getRatio(ratio));
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				
				}
				log.append(">>>>>>ͼƬ�޸ĳߴ�ɹ���<<<<<<\n ");
				log.append("Ŀ���ļ��е�ַ: " + destinationFolderPath + "\n");
				log.append("=========================== \n");
			}
		}
	}

	public static void reSize(File source, File dest, double ratio) throws IOException {
		FileInputStream fileIs = new FileInputStream(source);
		// ��ȡͼƬ
		BufferedInputStream in = new BufferedInputStream(fileIs);
		// �ֽ���תͼƬ����
		Image bi = ImageIO.read(in);
		// ��ȡͼ��ĸ߶ȣ����
		int height = (int) (bi.getHeight(null) * ratio);
		int width = (int) (bi.getWidth(null) * ratio);		
		//ƽ������
		bi=bi.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		// Ŀ��ͼ��ĸ߶ȣ����
		// ����ͼƬ��,���û�����С
		BufferedImage tag = new BufferedImage(width, height, getBufferedImageType(source));
		// ���Ƹı�ߴ���ͼ
		tag.getGraphics().drawImage(bi, 0, 0, width, height, null);
		// �����
		BufferedOutputStream out = new BufferedOutputStream(
				new FileOutputStream(dest));

		ImageIO.write(tag, FileUtil.getTypeSuffix(source), out);
		in.close();
		out.close();
	}

	
	
		// ���ر��ʣ�Ĭ��Ϊ0.5
		private double getRatio(JTextField ratio) {
			String ratioStr = ratio.getText();
			if (Pattern.matches("\\d\\.\\d{1,}", ratioStr)) {
				return Double.parseDouble(ratioStr);
			} else {
				return 0.5;
			}
		}

		// Ŀ��ͼ��ᾡ����ʹ��ԭʼͼ�����ͷ�ʽ���
		private static int getBufferedImageType(File f) {
			String suffix = FileUtil.getTypeSuffix(f);
			if (suffix.equals("png")) {
				return BufferedImage.TYPE_4BYTE_ABGR;// alpha͸��
			} else {
				return BufferedImage.TYPE_INT_RGB;
			}
		}


}
