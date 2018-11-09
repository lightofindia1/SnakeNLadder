package alliance;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class Credits extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Credits frame = new Credits();
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
	public Credits() {
		Timer timer = new Timer();
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
		
		JLabel lblExit = new JLabel("Back to Menu");
		lblExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				dispose();
		        new Menu().setVisible(true);
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
		lblExit.setFont(new Font("Tahoma", Font.BOLD, 32));
		lblExit.setBounds(5, 500, 300, 50);
		contentPane.add(lblExit);

		
		JLabel lblDevelopedBy = new JLabel("Developed by");
		lblDevelopedBy.setForeground(Color.WHITE);
		lblDevelopedBy.setHorizontalAlignment(SwingConstants.CENTER);
		lblDevelopedBy.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblDevelopedBy.setBounds(300, 222, 300, 30);
		contentPane.add(lblDevelopedBy);
		
		JLabel lblManikiran = new JLabel("Manikiran P");
		lblManikiran.setForeground(Color.WHITE);
		lblManikiran.setHorizontalAlignment(SwingConstants.CENTER);
		lblManikiran.setFont(new Font("Tahoma", Font.BOLD, 45));
		lblManikiran.setBounds(400, 259, 300, 50);
		contentPane.add(lblManikiran);
		
		JLabel lblRoll = new JLabel("16030141IT024");
		lblRoll.setForeground(Color.WHITE);
		lblRoll.setHorizontalAlignment(SwingConstants.CENTER);
		lblRoll.setFont(new Font("Tahoma", Font.BOLD, 64));
		lblRoll.setBounds(420, 320, 550, 55);
		contentPane.add(lblRoll);
		
		JLabel lblAU = new JLabel("Alliance University");
		lblAU.setForeground(Color.WHITE);
		lblAU.setHorizontalAlignment(SwingConstants.CENTER);
		lblAU.setFont(new Font("Tahoma", Font.BOLD, 32));
		lblAU.setBounds(383, 386, 400, 50);
		contentPane.add(lblAU);
		
		JLabel lblBitmoji = new JLabel();
		lblBitmoji.setIcon(ResizeImage("/images/manikiran1.png",300,300));
		lblBitmoji.setBounds(100, 200, 300, 300);
		contentPane.add(lblBitmoji);
		timer.schedule(new UpdateBitmoji(lblBitmoji), 0, 3000);
		
		JLabel lblBg = new JLabel();
		lblBg.setIcon(ResizeImage("/images/bg.jpg",1000,600));
		lblBg.setBounds(15, 0, 1000, 600);
		contentPane.add(lblBg);
	}
	public ImageIcon ResizeImage(String path,int width,int height){
		return (new ImageIcon(new ImageIcon(Credits.class.getResource(path)).getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT)));
	}
	class UpdateBitmoji extends TimerTask {
		JLabel bitmoji;
		public UpdateBitmoji(JLabel btmoji){
			this.bitmoji=btmoji;
		}
	    public void run() {
	    	Random obj=new Random();
			int x=obj.nextInt(4)+1;
			bitmoji.setIcon(ResizeImage("/images/manikiran"+x+".png",300,300));
	    }
	}

}
