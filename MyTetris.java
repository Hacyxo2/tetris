package project2;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.*;

public class MyTetris extends JFrame {

	private JPanel contentPane;
	private TetrisCanvas tetrisCanvas = null;
	private TetrisData data;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MyTetris frame = new MyTetris();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MyTetris() {
		JMenuBar jb = new JMenuBar();
		JMenu menu = new JMenu("GAME");
		JMenuItem start, pause, exit;
		JSplitPane splitPane = new JSplitPane();
		splitPane.setFocusable(false);
		tetrisCanvas = new TetrisCanvas();
		data = new TetrisData();
		start = new JMenuItem("START");
		start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tetrisCanvas.start();
			}
		});
		exit = new JMenuItem("EXIT");
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		pause = new JMenuItem("PAUSE");
		pause.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tetrisCanvas.stop();
			}
		});

		jb.add(menu);
		menu.add(start);
		menu.add(pause);
		menu.add(exit);

		setJMenuBar(jb);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(1, 1, 400, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		contentPane.add(tetrisCanvas, BorderLayout.CENTER);

		contentPane.add(splitPane, BorderLayout.NORTH);

		splitPane.setResizeWeight(0.5);
		
		JTextArea textArea_1 = new JTextArea();
		splitPane.setRightComponent(textArea_1);
		
		JTextField score = new JTextField();
		
		splitPane.setLeftComponent(score);
		score.setColumns(10);
	}

	public TetrisData TetrisData() {
		return data;
	}
	public TetrisCanvas TetrisCanvas() {
		return tetrisCanvas;
	}
	
}
