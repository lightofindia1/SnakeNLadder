package alliance;

import java.awt.EventQueue;
import java.time.Duration;
import java.time.Instant;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Cursor;

public class ScoreBoard extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ScoreBoard frame = new ScoreBoard(3,Duration.ZERO);
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
	public ScoreBoard(int playerNo,Duration totalTime) {
		this.setTitle("SnakeNLadder");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(50, 50, 1000, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblLogo = new JLabel();
		lblLogo.setIcon(ResizeImage("/images/logo.png",200,75));
		lblLogo.setBounds(400, 50, 200, 75);
		contentPane.add(lblLogo);
		
		JLabel lblPlayer = new JLabel();
		lblPlayer.setIcon(ResizeImage("/images/player"+playerNo+".png",150,150));
		lblPlayer.setBounds(650,200,150,150);
		contentPane.add(lblPlayer);
		
		JLabel lblWinner = new JLabel();
		lblWinner.setText("Player "+playerNo+" Won");
		lblWinner.setFont(new Font("Tahoma", Font.BOLD, 42));
		lblWinner.setForeground(Color.white);
		lblWinner.setBounds(600, 250, 400, 250);
		contentPane.add(lblWinner);
		
		JLabel lblTime = new JLabel();
		lblTime.setText(formatTime(totalTime.getSeconds()));
		lblTime.setFont(new Font("Tahoma", Font.BOLD, 32));
		lblTime.setForeground(Color.red);
		lblTime.setHorizontalAlignment(SwingConstants.CENTER);
		lblTime.setBounds(240, 220, 200, 250);
		contentPane.add(lblTime);
		
		JLabel lblBanner1 = new JLabel();
		lblBanner1.setIcon(ResizeImage("/images/banner1.png",400,250));
		lblBanner1.setBounds(150, 150, 400, 250);
		contentPane.add(lblBanner1);
		
		JLabel lblHome = new JLabel();
		lblHome.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblHome.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				setVisible(false);
		        new Menu().setVisible(true);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				
			}
			@Override
			public void mouseExited(MouseEvent e) {
				
			}
		});
		lblHome.setIcon(ResizeImage("/images/homeBtn.png",325,200));
		lblHome.setBounds(337, 400, 325, 200);
		contentPane.add(lblHome);

		JLabel lblGrayscreen = new JLabel();
		lblGrayscreen.setIcon(ResizeImage("/images/greybg.png",1000,600));
		lblGrayscreen.setBounds(0, 0, 1000, 600);
		contentPane.add(lblGrayscreen);
		
		JLabel lblBg = new JLabel();
		lblBg.setIcon(ResizeImage("/images/bg.jpg",1000,600));
		lblBg.setBounds(0, 0, 1000, 600);
		contentPane.add(lblBg);
		
	}
	public ImageIcon ResizeImage(String path,int width,int height){
		return (new ImageIcon(new ImageIcon(ScoreBoard.class.getResource(path)).getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT)));
	}
	public String formatTime(long s){
		long min=s/60;
		long sec=s%60;
		return min+"m "+sec+"s";
	}
}
