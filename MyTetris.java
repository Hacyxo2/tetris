package project2;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.border.EmptyBorder;
import javax.swing.*;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.SystemColor;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.Point;

public class MyTetris extends JFrame {

	private JPanel contentPane;
	private TetrisCanvas tetrisCanvas = null;
	private TetrisData data;
	static JTextArea score = new JTextArea();
	static JTextArea removeLine = new JTextArea();
	static JTextArea level = new JTextArea();
	static JTextArea gauge = new JTextArea();
	
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
		setTitle("Tetris");
		JMenuBar jb = new JMenuBar();
		JMenu menu = new JMenu("GAME");
		JMenuItem start, exit;
		tetrisCanvas = new TetrisCanvas();
		
		data = new TetrisData();
		start = new JMenuItem("START");
		start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tetrisCanvas.start();
				new Manuel(getLocation());
				
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
		setBounds(1, 1, 350, 650);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		contentPane.add(tetrisCanvas, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new GridLayout(2, 4, 0, 0));
		score.setFocusable(false);
		score.setBackground(SystemColor.menu);
		score.setText("SCORE: ");
		score.setRows(1);
		panel.add(score);
		level.setFocusable(false);
		
		
		level.setBackground(SystemColor.menu);
		level.setRows(1);
		level.setText("LEVEL: ");
		panel.add(level);
		removeLine.setFocusable(false);
		
		
		removeLine.setBackground(SystemColor.menu);
		removeLine.setRows(1);
		removeLine.setText("LINES: ");
		panel.add(removeLine);
		gauge.setFocusable(false);
		gauge.setBackground(SystemColor.menu);
		gauge.setText("GAUGE: ");
		gauge.setRows(1);
		panel.add(gauge);
		
		
	}

	public TetrisData TetrisData() {
		return data;
	}
	public TetrisCanvas TetrisCanvas() {
		return tetrisCanvas;
	}
	static void getGauge(int Gauge) {
		gauge.setText("GAUGE: " + Gauge);
	}
	static void getScore(int Score) {
		score.setText("SCORE: " + Score);
	}
	static void getLevel(int Level) {
		level.setText("LEVEL: " + Level);
	}
	static void getRemoveLine(int Line) {
		removeLine.setText("LINE: " + Line);
	}
}
class Manuel extends JFrame {
	
	Manuel(Point point) {
		
		setTitle("게임 조작 방법");
		JPanel UserManuel = new JPanel();
		setLocation(point);
		setContentPane(UserManuel);
		JLabel NewLabel = new JLabel("<html>조작법"
								   + "<br>위쪽 방향키는 조각을 시계방향으로 회전시킵니다."
								   + "<br>왼쪽, 오른쪽 방향키로 조각을 좌우로 움직일 수 있습니다."
								   + "<br>아래 방향키를 눌러 조각을 한칸 아래로 움직일 수 있습니다."
								   + "<br>Space 바를 누르면 조각을 바로 아래로 내릴 수 있습니다."
								   + "<br>GAUGE가 1 이상일 때 Enter키를 누르면 조각들이 재정렬됩니다.  </html>");
		UserManuel.add(NewLabel);
		setSize(400, 150);
		setResizable(false);
		setVisible(true);
	}
}