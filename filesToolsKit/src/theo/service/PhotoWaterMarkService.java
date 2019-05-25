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
						//TODO��һ��ˮӡʵ���࣬��������
						addWaterMark(sourceFiles[i], f,getWaterMark(waterMark),getClarity(clarity),getPosition(position),getFontSize(fontSize));
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

	public static void addWaterMark(File source, File dest, String waterMarkStr,
			int clarity,  String position, int fontSize) throws IOException {
		FileInputStream fileIs = new FileInputStream(source);
		// ��ȡͼƬ
		BufferedInputStream in = new BufferedInputStream(fileIs);
		// �ֽ���תͼƬ����
		Image bi = ImageIO.read(in);
		// ��ȡͼ��ĸ߶ȣ����
		int height = bi.getHeight(null);
		int width = bi.getWidth(null);

		// Ŀ��ͼ��ĸ߶ȣ����
		// ����ͼƬ��,���û�����С
		BufferedImage tag = new BufferedImage(width, height, getBufferedImageType(source));

		Graphics g = tag.getGraphics();
		// ��ͼ
		g.drawImage(bi, 0, 0, null);
		// ����ˮӡ
		// ����ˮӡ���壬��С����ʽ
		Font font = new Font("����", 0, fontSize);
		g.setFont(font);
		// ����������ɫ
		g.setColor(new Color(192, 192, 192, clarity));

		// ��ָ��λ�û���ˮӡ
		painWaterMark(g, font, width, height, waterMarkStr, position);

		// �����
		BufferedOutputStream out = new BufferedOutputStream(
				new FileOutputStream(dest));

		ImageIO.write(tag, FileUtil.getTypeSuffix(source), out);
		in.close();
		out.close();
	}


	
	//����ˮӡ
	private static void painWaterMark(Graphics g, Font font, int width,
			int height, String waterMarkStr, String position) {
		int x, y;// ���ֵ�λ��
		FontMetrics metrics = g.getFontMetrics(font);
		switch (position) {
		case "up":
		case "��":
			x = (width - metrics.stringWidth(waterMarkStr)) / 2;
			y = metrics.getAscent() + metrics.getDescent();
			break;
		case "down":
		case "��":
			x = (width - metrics.stringWidth(waterMarkStr)) / 2;
			y = height - metrics.getDescent() * 5;
			break;
		case "left":
		case "��":
			x = 0;
			y = (height + metrics.getAscent()) / 2;
			break;
		case "right":
		case "��":
			x = width - metrics.stringWidth(waterMarkStr);
			y = (height + metrics.getAscent()) / 2;
			break;
		case "center":
		case "��":
		case "����":
			x = (width - metrics.stringWidth(waterMarkStr)) / 2;
			y = (height + metrics.getAscent()) / 2;
			break;
		// Ĭ��Ϊ����
		default:
			x = width - metrics.stringWidth(waterMarkStr);
			y = height - metrics.getDescent() * 5;
			break;
		}
		// ����ˮӡ
		g.drawString(waterMarkStr, x, y);

	}
	
	// ���ر��ʣ�Ĭ��Ϊ120
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

	// ��ȡ�ֺ�
	private int getFontSize(JTextField fontSize) {
		String fontSizeStr = fontSize.getText();
		if (Pattern.matches("[1-9][0-9]{0,2}", fontSizeStr)) {
			return Integer.parseInt(fontSizeStr);
		}
		return 20;
	}

	// Ĭ������
	private String getPosition(JTextField position) {
		String positionStr = position.getText();
		if (positionStr != null && !"".equals(positionStr.trim())) {
			if (Pattern.matches("[^��������0-9\\p{Punct}]{1,}", positionStr)) {
				return positionStr.toLowerCase();
			}
		}
		return "����";
	}

	// ���ر��ʣ�Ĭ��Ϊwater mark
	private String getWaterMark(JTextField waterMark) {
		String waterMarkStr = waterMark.getText();
		if (waterMarkStr != null && !"".equals(waterMarkStr.trim())) {
			return waterMarkStr;
		} else {
			return "Water Mark";
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
