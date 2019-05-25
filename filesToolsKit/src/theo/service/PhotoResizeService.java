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
					// 目标文件地址
					File f = FileUtil.createNewFile(destinationFolderPath, sourceFiles[i]);
					try {
						reSize(sourceFiles[i], f, getRatio(ratio));
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				
				}
				log.append(">>>>>>图片修改尺寸成功！<<<<<<\n ");
				log.append("目标文件夹地址: " + destinationFolderPath + "\n");
				log.append("=========================== \n");
			}
		}
	}

	public static void reSize(File source, File dest, double ratio) throws IOException {
		FileInputStream fileIs = new FileInputStream(source);
		// 读取图片
		BufferedInputStream in = new BufferedInputStream(fileIs);
		// 字节流转图片对象
		Image bi = ImageIO.read(in);
		// 获取图像的高度，宽度
		int height = (int) (bi.getHeight(null) * ratio);
		int width = (int) (bi.getWidth(null) * ratio);		
		//平滑处理
		bi=bi.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		// 目标图象的高度，宽度
		// 构建图片流,设置画布大小
		BufferedImage tag = new BufferedImage(width, height, getBufferedImageType(source));
		// 绘制改变尺寸后的图
		tag.getGraphics().drawImage(bi, 0, 0, width, height, null);
		// 输出流
		BufferedOutputStream out = new BufferedOutputStream(
				new FileOutputStream(dest));

		ImageIO.write(tag, FileUtil.getTypeSuffix(source), out);
		in.close();
		out.close();
	}

	
	
		// 返回比率，默认为0.5
		private double getRatio(JTextField ratio) {
			String ratioStr = ratio.getText();
			if (Pattern.matches("\\d\\.\\d{1,}", ratioStr)) {
				return Double.parseDouble(ratioStr);
			} else {
				return 0.5;
			}
		}

		// 目标图象会尽可能使用原始图像类型方式输出
		private static int getBufferedImageType(File f) {
			String suffix = FileUtil.getTypeSuffix(f);
			if (suffix.equals("png")) {
				return BufferedImage.TYPE_4BYTE_ABGR;// alpha透明
			} else {
				return BufferedImage.TYPE_INT_RGB;
			}
		}


}
