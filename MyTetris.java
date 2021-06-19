package project2;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.*;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.SystemColor;
import java.awt.Component;
import java.awt.GridLayout;

public class MyTetris extends JFrame {

	private JPanel contentPane;
	private TetrisCanvas tetrisCanvas = null;
	private TetrisData data;
	static JTextArea score = new JTextArea();
	static JTextArea removeLine = new JTextArea();
	static JTextArea level = new JTextArea();
	
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
		JMenuItem start, exit;
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

		jb.add(menu);
		menu.add(start);
		menu.add(exit);

		setJMenuBar(jb);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(1, 1, 390, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		contentPane.add(tetrisCanvas, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		
		panel.setLayout(new GridLayout(0, 3, 0, 0));
		score.setFocusable(false);
		score.setBackground(SystemColor.menu);
		score.setText("점수: ");
		score.setLineWrap(true);
		score.setRows(1);
		panel.add(score);
		removeLine.setFocusable(false);
		
		
		removeLine.setBackground(SystemColor.menu);
		removeLine.setRows(1);
		removeLine.setText("제거한 라인: ");
		panel.add(removeLine);
		level.setFocusable(false);
		
		
		level.setBackground(SystemColor.menu);
		level.setRows(1);
		level.setText("레벨: ");
		panel.add(level);
	}

	public TetrisData TetrisData() {
		return data;
	}
	public TetrisCanvas TetrisCanvas() {
		return tetrisCanvas;
	}
	
	public static void getScore(int Score) {
		score.setText("점수: " + Score);
	}
	public static void getLevel(int Level) {
		level.setText("레벨: " + Level);
	}
	public static void getRemoveLine(int Line) {
		removeLine.setText("제거한 라인: " + Line);
	}
}
