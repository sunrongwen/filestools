package theo.service;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
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

public class PhotoWaterMarkService {

	public void addWaterMarkSaveSources(ActionEvent e, JFileChooser fc,JTextArea log, String sourceFolderPath,
			File[] sourceFiles,JTextField waterMark,JTextField position,JTextField fontSize,JTextField clarity) {
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
						//TODO建一个水印实体类，或是条件
						addWaterMark(sourceFiles[i], f,getWaterMark(waterMark),getClarity(clarity),getPosition(position),getFontSize(fontSize));
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

	public static void addWaterMark(File source, File dest, String waterMarkStr,
			int clarity,  String position, int fontSize) throws IOException {
		FileInputStream fileIs = new FileInputStream(source);
		// 读取图片
		BufferedInputStream in = new BufferedInputStream(fileIs);
		// 字节流转图片对象
		Image bi = ImageIO.read(in);
		// 获取图像的高度，宽度
		int height = bi.getHeight(null);
		int width = bi.getWidth(null);

		// 目标图象的高度，宽度
		// 构建图片流,设置画布大小
		BufferedImage tag = new BufferedImage(width, height, getBufferedImageType(source));

		Graphics g = tag.getGraphics();
		// 绘图
		g.drawImage(bi, 0, 0, null);
		// 绘制水印
		// 设置水印字体，大小，样式
		Font font = new Font("宋体", 0, fontSize);
		g.setFont(font);
		// 设置字体颜色
		g.setColor(new Color(192, 192, 192, clarity));

		// 在指定位置绘制水印
		painWaterMark(g, font, width, height, waterMarkStr, position);

		// 输出流
		BufferedOutputStream out = new BufferedOutputStream(
				new FileOutputStream(dest));

		ImageIO.write(tag, FileUtil.getTypeSuffix(source), out);
		in.close();
		out.close();
	}


	
	//绘制水印
	private static void painWaterMark(Graphics g, Font font, int width,
			int height, String waterMarkStr, String position) {
		int x, y;// 文字的位置
		FontMetrics metrics = g.getFontMetrics(font);
		switch (position) {
		case "up":
		case "上":
			x = (width - metrics.stringWidth(waterMarkStr)) / 2;
			y = metrics.getAscent() + metrics.getDescent();
			break;
		case "down":
		case "下":
			x = (width - metrics.stringWidth(waterMarkStr)) / 2;
			y = height - metrics.getDescent() * 5;
			break;
		case "left":
		case "左":
			x = 0;
			y = (height + metrics.getAscent()) / 2;
			break;
		case "right":
		case "右":
			x = width - metrics.stringWidth(waterMarkStr);
			y = (height + metrics.getAscent()) / 2;
			break;
		case "center":
		case "中":
		case "居中":
			x = (width - metrics.stringWidth(waterMarkStr)) / 2;
			y = (height + metrics.getAscent()) / 2;
			break;
		// 默认为右下
		default:
			x = width - metrics.stringWidth(waterMarkStr);
			y = height - metrics.getDescent() * 5;
			break;
		}
		// 绘制水印
		g.drawString(waterMarkStr, x, y);

	}
	
	// 返回比率，默认为120
	private int getClarity(JTextField clarity) {
		String clarityStr = clarity.getText();
		if (Pattern.matches("\\d{1,}", clarityStr)) {
			int a = Integer.parseInt(clarityStr);
			switch (a) {
			case 0:
				return 25;
			case 1:
				return 50;
			case 2:
				return 75;
			case 3:
				return 100;
			case 4:
				return 125;
			case 5:
				return 150;
			case 6:
				return 175;
			case 7:
				return 200;
			case 8:
				return 225;
			case 9:
				return 255;
			}
		}
		return 150;
	}

	// 获取字号
	private int getFontSize(JTextField fontSize) {
		String fontSizeStr = fontSize.getText();
		if (Pattern.matches("[1-9][0-9]{0,2}", fontSizeStr)) {
			return Integer.parseInt(fontSizeStr);
		}
		return 20;
	}

	// 默认右下
	private String getPosition(JTextField position) {
		String positionStr = position.getText();
		if (positionStr != null && !"".equals(positionStr.trim())) {
			if (Pattern.matches("[^。，：；0-9\\p{Punct}]{1,}", positionStr)) {
				return positionStr.toLowerCase();
			}
		}
		return "右下";
	}

	// 返回比率，默认为water mark
	private String getWaterMark(JTextField waterMark) {
		String waterMarkStr = waterMark.getText();
		if (waterMarkStr != null && !"".equals(waterMarkStr.trim())) {
			return waterMarkStr;
		} else {
			return "Water Mark";
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
