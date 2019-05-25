package theo.view;

import java.io.*;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import theo.dispatch.PhotoAddWaterMarkWorkflow;

public class PhotosWaterMarkAdd extends JPanel implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static private final String newline = "\n";
	JButton openButton, saveButton;
	JTextArea log;
	JFileChooser fc;

	// ˮӡ������
	JLabel clarityLabel;
	JTextField clarity;

	// ˮӡ����
	JLabel waterMarkLabel;
	JTextField waterMark;

	// ˮӡλ��
	JTextField position;
	JLabel positionLabel;
	// ˮӡ��С
	private JLabel fontSizeLabel;
	private JTextField fontSize;

	// Դ
	File[] files;
	String filePath;
	// Ŀ��
	String filePath2;

	public PhotosWaterMarkAdd() {
		super(new BorderLayout());

		// Create the log first, because the action listeners
		// need to refer to it.
		log = new JTextArea(10, 40);
		log.setMargin(new Insets(5, 5, 5, 5));
		log.setEditable(false);
		JScrollPane logScrollPane = new JScrollPane(log);

		// Create a file chooser
		fc = new JFileChooser();

		openButton = new JButton("\u9009\u62E9\u6E90\u6587\u4EF6\u5939", null);
		openButton.addActionListener(this);

		// Create the save button. We use the image from the JLF
		// Graphics Repository (but we extracted it from the jar).
		saveButton = new JButton("\u9009\u62E9\u76EE\u6807\u6587\u4EF6\u5939",
				null);
		saveButton.addActionListener(this);

		// For layout purposes, put the buttons in a separate panel
		JPanel buttonPanel = new JPanel(); // use FlowLayout
		buttonPanel.setBackground(Color.WHITE);
		buttonPanel.add(openButton);
		buttonPanel.add(saveButton);

		// �ײ����벿��

		// ˮӡinput
		waterMarkLabel = new JLabel();
		waterMarkLabel.setText("\u6C34\u5370\u5185\u5BB9");

		waterMark = new JTextField(15);
		waterMark.setBackground(Color.WHITE);
		waterMark.setFont(new Font("����", Font.PLAIN, 20));

		// ������input
		clarityLabel = new JLabel();
		clarityLabel.setText("\u6E05\u6670\u5EA6");

		clarity = new JTextField(2);
		clarity.setBackground(Color.WHITE);
		clarity.setFont(new Font("����", Font.PLAIN, 20));

		// λ��
		positionLabel = new JLabel();
		positionLabel.setText("\u4F4D\u7F6E");

		position = new JTextField(5);
		position.setFont(new Font("����", Font.PLAIN, 20));
		position.setBackground(Color.WHITE);

		JPanel inputPanel = new JPanel();
		inputPanel.setBackground(Color.WHITE);

		inputPanel.add(waterMarkLabel);
		inputPanel.add(waterMark);
		inputPanel.add(clarityLabel);
		inputPanel.add(clarity);
		inputPanel.add(positionLabel);
		inputPanel.add(position);

		// Add the buttons and the log to this panel.
		add(buttonPanel, BorderLayout.PAGE_START);
		add(logScrollPane, BorderLayout.CENTER);
		add(inputPanel, BorderLayout.SOUTH);

		fontSizeLabel = new JLabel();
		fontSizeLabel.setText("\u5B57\u53F7");
		inputPanel.add(fontSizeLabel);

		fontSize = new JTextField(3);
		fontSize.setFont(new Font("����", Font.PLAIN, 20));
		fontSize.setBackground(Color.WHITE);
		inputPanel.add(fontSize);

		log.append("ע�⣺\n��ȷ���ļ�����ֻ��һ�����͵��ļ������ɺ����ļ��� ��" + newline);
		log.append("�ò�������Ӱ��ԭʼ�ļ� ��" + newline);
		log.append("��ʾ��\n" + newline);
		log.append("ˮӡ����Ĭ��Ϊwater mark" + newline + "�����Ȳ���Ϊ0-9��Ĭ�ϵ�������Ϊ5"
				+ newline + "λ�ò���Ϊ���ϡ��¡����ҡ��У�Ĭ��Ϊ����" + newline
				+ "�ֺŲ���Ϊ����10��������Ĭ��Ϊ18��" + newline);
	}

	public void actionPerformed(ActionEvent e) {
		PhotoAddWaterMarkWorkflow.addWaterMark(e, fc, log, waterMark, clarity, position, fontSize, openButton, saveButton);
	}

	/**
	 * Create the GUI and show it. For thread safety, this method should be
	 * invoked from the event dispatch thread.
	 */
	public static void createAndShowGUI() {
		// Create and set up the window.
		JFrame frame = new JFrame(
				"\u56FE\u7247\u6C34\u5370\u6279\u91CF\u6DFB\u52A0");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		// Add content to the window.
		frame.getContentPane().add(new PhotosWaterMarkAdd());

		// Display the window.
		frame.pack();
		frame.setVisible(true);
	}



}
