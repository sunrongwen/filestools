package theo.view;

import java.io.*;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import theo.dispatch.PhotoResizeWorkflow;

public class PhotosSizeChange extends JPanel implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static private final String newline = "\n";
	JButton openButton, saveButton;
	JTextArea log;
	JFileChooser fc;
	// ���ű���
	JLabel ratioLabel;
	JTextField ratio;
	// Դ
	File[] files;
	String filePath;
	//Ŀ��
	String filePath2;

	public PhotosSizeChange() {
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
		ratioLabel = new JLabel();
		ratioLabel.setText("\u8F6C\u6362\u540E\uFF0C\u76F8\u7247\u5927\u5C0F\u4E3A\u539F\u6765\u7684");

		// ����
		ratio = new JTextField(5);
		ratio.setBackground(Color.WHITE);
		ratio.setFont(new Font("����", Font.PLAIN, 20));

		JPanel inputPanel = new JPanel();
		inputPanel.setBackground(Color.WHITE);

		inputPanel.add(ratioLabel);
		inputPanel.add(ratio);

		// Add the buttons and the log to this panel.
		add(buttonPanel, BorderLayout.PAGE_START);
		add(logScrollPane, BorderLayout.CENTER);
		add(inputPanel, BorderLayout.SOUTH);

		log.append("ע�⣺\n��ȷ���ļ�����ֻ��һ�����͵��ļ������ɺ����ļ��� ��" + newline);
		log.append("�ò�������Ӱ��ԭʼ�ļ���" + newline);
		log.append("��ʾ��\n���ʣ�Ĭ��Ϊ0.5����ת����߳�����Ϊԭ����0.5����" + newline);
	}

	public void actionPerformed(ActionEvent e) {
		PhotoResizeWorkflow.reSize(e, fc, log, ratio, openButton, saveButton);
	}

	/**
	 * Create the GUI and show it. For thread safety, this method should be
	 * invoked from the event dispatch thread.
	 */
	public static void createAndShowGUI() {
		// Create and set up the window.
		JFrame frame = new JFrame("\u56FE\u7247\u5C3A\u5BF8\u6279\u91CF\u4FEE\u6539");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		// Add content to the window.
		frame.getContentPane().add(new PhotosSizeChange());

		// Display the window.
		frame.pack();
		frame.setVisible(true);
	}



	

}
