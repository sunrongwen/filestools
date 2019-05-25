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
	// 缩放比率
	JLabel ratioLabel;
	JTextField ratio;
	// 源
	File[] files;
	String filePath;
	//目标
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

		// 底部输入部件
		ratioLabel = new JLabel();
		ratioLabel.setText("\u8F6C\u6362\u540E\uFF0C\u76F8\u7247\u5927\u5C0F\u4E3A\u539F\u6765\u7684");

		// 比率
		ratio = new JTextField(5);
		ratio.setBackground(Color.WHITE);
		ratio.setFont(new Font("宋体", Font.PLAIN, 20));

		JPanel inputPanel = new JPanel();
		inputPanel.setBackground(Color.WHITE);

		inputPanel.add(ratioLabel);
		inputPanel.add(ratio);

		// Add the buttons and the log to this panel.
		add(buttonPanel, BorderLayout.PAGE_START);
		add(logScrollPane, BorderLayout.CENTER);
		add(inputPanel, BorderLayout.SOUTH);

		log.append("注意：\n请确保文件夹中只有一种类型的文件，不可含有文件夹 。" + newline);
		log.append("该操作不会影响原始文件。" + newline);
		log.append("提示：\n比率（默认为0.5，即转换后边长均变为原来的0.5倍）" + newline);
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
