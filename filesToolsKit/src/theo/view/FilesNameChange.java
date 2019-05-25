package theo.view;

import java.io.*;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import theo.constant.YesOrNo;
import theo.dispatch.ChangeNameWorkflow;

public class FilesNameChange extends JPanel implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JButton openButton, saveButton;
	JTextArea log;
	JFileChooser fc;
	// 高级功能,起始数，前缀
	JLabel startNumLabel;
	JLabel prefixLabel;
	JTextField startNum;
	JTextField prefix;
	// 源
	File[] sourceFiles;
	String sourcePath;
	// 目标
	String destinationPath;

	private JLabel isContainSourceNameLabel;

	// 是否保留原文件名用
	private JComboBox<YesOrNo> isContainSourceName;

	public FilesNameChange() {
		super(new BorderLayout());


		log = new JTextArea(10, 40);
		log.setMargin(new Insets(5, 5, 5, 5));
		log.setEditable(false);
		JScrollPane logScrollPane = new JScrollPane(log);

		// Create a file chooser
		fc = new JFileChooser();

		openButton = new JButton("\u9009\u62E9\u6E90\u6587\u4EF6\u5939", null);
		openButton.addActionListener(this);

		saveButton = new JButton("\u9009\u62E9\u76EE\u6807\u6587\u4EF6\u5939",
				null);
		saveButton.addActionListener(this);

		// For layout purposes, put the buttons in a separate panel
		JPanel buttonPanel = new JPanel(); // use FlowLayout
		buttonPanel.setBackground(Color.WHITE);
		buttonPanel.add(openButton);
		buttonPanel.add(saveButton);

		// 底部输入部件
		startNumLabel = new JLabel();
		startNumLabel.setText("\u8D77\u59CB\u6570");
		prefixLabel = new JLabel();
		prefixLabel.setText("\u524D\u7F00");

		// 起始数
		startNum = new JTextField(5);
		startNum.setBackground(Color.WHITE);
		startNum.setFont(new Font("宋体", Font.PLAIN, 20));

		// 前缀
		prefix = new JTextField(15);
		prefix.setBackground(Color.WHITE);
		prefix.setFont(new Font("宋体", Font.PLAIN, 20));

		JPanel inputPanel = new JPanel();
		inputPanel.setBackground(Color.WHITE);
		inputPanel.add(prefixLabel);
		inputPanel.add(prefix);
		inputPanel.add(startNumLabel);
		inputPanel.add(startNum);

		// Add the buttons and the log to this panel.
		add(buttonPanel, BorderLayout.PAGE_START);
		add(logScrollPane, BorderLayout.CENTER);
		add(inputPanel, BorderLayout.SOUTH);

		isContainSourceNameLabel = new JLabel(
				"\u662F\u5426\u542B\u539F\u6587\u4EF6\u540D");
		inputPanel.add(isContainSourceNameLabel);

		isContainSourceName = new JComboBox<YesOrNo>();

		isContainSourceName.setFont(new Font("宋体", Font.PLAIN, 15));
		isContainSourceName.addItem(YesOrNo.YES);
		isContainSourceName.addItem(YesOrNo.NO);

		inputPanel.add(isContainSourceName);

		log.append("注意：\n请确保文件夹中只有一种类型的文件，不可含有文件夹 。\n该操作不会影响原始文件。\n");
		log.append("参数：\n前缀（默认为无前缀）\n数字起始值（默认为1）\n选择是否保留原文件名（默认为保留）\n");

	}

	// 按钮触发
	public void actionPerformed(ActionEvent e) {
		ChangeNameWorkflow.changeName(e, fc, log, prefix, startNum,
				isContainSourceName, openButton, saveButton);
	}

	/**
	 * Create the GUI and show it. For thread safety, this method should be
	 * invoked from the event dispatch thread.
	 */
	public static void createAndShowGUI() {
		// Create and set up the window.
		JFrame frame = new JFrame("\u6587\u4EF6\u540D\u6279\u91CF\u4FEE\u6539");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		// Add content to the window.
		frame.getContentPane().add(new FilesNameChange());

		// Display the window.
		frame.pack();
		frame.setVisible(true);
	}


}
