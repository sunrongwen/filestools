package theo.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.LayoutStyle.ComponentPlacement;

import theo.dispatch.ViewDisplay;
import javax.swing.SwingConstants;
import java.awt.Component;

public class FileUtilKit extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FileUtilKit frame = new FileUtilKit();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FileUtilKit() {
		setTitle("\u6587\u4EF6\u6279\u91CF\u5904\u7406");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		//按钮点击后，显示《文件批量改名》页面
		JButton btnOpenFileNameChange = new JButton("\u6587\u4EF6\u540D\u6279\u91CF\u4FEE\u6539");
		btnOpenFileNameChange.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewDisplay.showWin(ViewDisplay.FILES_NAME_CHANGE);
			}
		});
		//按钮点击后，显示《图片批量修改尺寸》页面
		JButton btnChangeSize = new JButton("\u56FE\u7247\u5C3A\u5BF8\u6279\u91CF\u4FEE\u6539");
		btnChangeSize.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewDisplay.showWin(ViewDisplay.PHOTOS_SIZE_CHANGE);
			}
		});
		//按钮点击后，显示《图片批量添加水印》页面
		JButton btnAddWater = new JButton("\u56FE\u7247\u6279\u91CF\u6DFB\u52A0\u6C34\u5370");
		btnAddWater.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewDisplay.showWin(ViewDisplay.PHOTOS_WATERMARK_ADD);
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(22, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnOpenFileNameChange, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE)
							.addGap(203)
							.addComponent(btnChangeSize, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE))
						.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
							.addGap(167)
							.addComponent(btnAddWater, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 150, GroupLayout.PREFERRED_SIZE)))
					.addGap(15))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btnAddWater)
						.addComponent(btnOpenFileNameChange)
						.addComponent(btnChangeSize))
					.addContainerGap(318, Short.MAX_VALUE))
		);
		gl_contentPane.linkSize(SwingConstants.VERTICAL, new Component[] {btnOpenFileNameChange, btnChangeSize, btnAddWater});
		gl_contentPane.linkSize(SwingConstants.HORIZONTAL, new Component[] {btnOpenFileNameChange, btnChangeSize, btnAddWater});
		contentPane.setLayout(gl_contentPane);
	}


}
