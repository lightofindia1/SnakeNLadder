package alliance;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

public class Menu extends JFrame {

	private JPanel contentPane;
	Clip bgClip;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu frame = new Menu();
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
	public Menu() {
		try{
			AudioInputStream aIS = AudioSystem.getAudioInputStream(new File("src/mp3/menubg.wav").getAbsoluteFile());
			bgClip = AudioSystem.getClip();
			bgClip.open(aIS);
			bgClip.loop(Clip.LOOP_CONTINUOUSLY);
		}catch(Exception e){}
		this.setTitle("SnakeNLadder");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(50, 50, 1000, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblLogo = new JLabel();
		lblLogo.setIcon(ResizeImage("/images/logo.png",400,150));
		lblLogo.setBounds(300, 50, 400, 150);
		contentPane.add(lblLogo);
		
		JLabel lblStartGame = new JLabel("Start Game");
		lblStartGame.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				setVisible(false);
				bgClip.stop();
		        new Game().setVisible(true);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				lblStartGame.setForeground(Color.RED);
				lblStartGame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblStartGame.setForeground(Color.WHITE);
				
			}
		});
		lblStartGame.setForeground(Color.WHITE);
		lblStartGame.setHorizontalAlignment(SwingConstants.CENTER);
		lblStartGame.setFont(new Font("Tahoma", Font.BOLD, 42));
		lblStartGame.setBounds(375, 250, 250, 50);
		contentPane.add(lblStartGame);
		
		
		
		JLabel lblCredits = new JLabel("Credits");
		lblCredits.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				setVisible(false);
		        new Credits().setVisible(true);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				lblCredits.setForeground(Color.RED);
				lblCredits.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblCredits.setForeground(Color.WHITE);
				
			}
		});
		lblCredits.setForeground(Color.WHITE);
		lblCredits.setHorizontalAlignment(SwingConstants.CENTER);
		lblCredits.setFont(new Font("Tahoma", Font.BOLD, 42));
		lblCredits.setBounds(375, 310, 250, 50);
		contentPane.add(lblCredits);
		
		JLabel lblExit = new JLabel("Exit");
		lblExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				dispose();
		        System.exit(0);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				lblExit.setForeground(Color.RED);
				lblExit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblExit.setForeground(Color.WHITE);
				
			}
		});
		lblExit.setForeground(Color.WHITE);
		lblExit.setHorizontalAlignment(SwingConstants.CENTER);
		lblExit.setFont(new Font("Tahoma", Font.BOLD, 42));
		lblExit.setBounds(375, 370, 250, 50);
		contentPane.add(lblExit);
		
		JLabel lblBg = new JLabel();
		lblBg.setIcon(ResizeImage("/images/bg.jpg",1000,600));
		lblBg.setBounds(0, 0, 1000, 600);
		contentPane.add(lblBg);
		
		
		
		
		
		
	}
	public ImageIcon ResizeImage(String path,int width,int height){
		return (new ImageIcon(new ImageIcon(Menu.class.getResource(path)).getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT)));
	}
}
