package alliance;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;
import java.time.Duration;
import java.time.Instant;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

import javax.swing.JComboBox;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

public class Game extends JFrame {

	JPanel contentPane;
	JLabel lblLogo;
	JLabel lblStartGame;
	JLabel lblBg;
	JLabel board;
	JLabel lblPlayerTurn;
	JComboBox comboBox;
	JButton startButton;
	JButton rollButton;
	Dice lblDice;
	Clip bgClip;
	Player players[];
	int playerTurn=-1;
	int PlayerCount=2;
	int[] boardOrder[]={{15,505},{80,505},{145,505},{210,505},{275,505},{340,505},{405,505},{470,505},{535,505},{600,505},{600,450},{535,450},{470,450},{405,450},{340,450},{275,450},{210,450},{145,450},{80,450},{15,450},{15,395},{80,395},{145,395},{210,395},{275,395},{340,395},{405,395},{470,395},{535,395},{600,395},{600,340},{535,340},{470,340},{405,340},{340,340},{275,340},{210,340},{145,340},{80,340},{15,340},{15,285},{80,285},{145,285},{210,285},{275,285},{340,285},{405,285},{470,285},{535,285},{600,285},{600,230},{535,230},{470,230},{405,230},{340,230},{275,230},{210,230},{145,230},{80,230},{15,230},{15,175},{80,175},{145,175},{210,175},{275,175},{340,175},{405,175},{470,175},{535,175},{600,175},{600,120},{535,120},{470,120},{405,120},{340,120},{275,120},{210,120},{145,120},{80,120},{15,120},{15,65},{80,65},{145,65},{210,65},{275,65},{340,65},{405,65},{470,65},{535,65},{600,65},{600,10},{535,10},{470,10},{405,10},{340,10},{275,10},{210,10},{145,10},{80,10},{15,10}};
	int[] snakes[]={{40,20},{50,16},{81,78},{92,52},{95,36}};
	int[] ladders[]={{4,22},{10,29},{14,77},{33,51},{64,82},{74,90}};
	Instant startTime = Instant.now();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Game frame = new Game();
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
	public Game() {
		try{
			AudioInputStream aIS = AudioSystem.getAudioInputStream(new File("src/mp3/gamebg.wav").getAbsoluteFile());
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
		
		
		
		lblLogo = new JLabel();
		lblLogo.setIcon(ResizeImage("/images/logo.png",400,150));
		lblLogo.setBounds(300, 50, 400, 150);
		contentPane.add(lblLogo);
		
		lblStartGame = new JLabel("Select Number of Players");
		lblStartGame.setForeground(Color.WHITE);
		lblStartGame.setHorizontalAlignment(SwingConstants.CENTER);
		lblStartGame.setFont(new Font("Tahoma", Font.BOLD, 36));
		lblStartGame.setBounds(250, 250, 500, 50);
		contentPane.add(lblStartGame);
		
		lblPlayerTurn = new JLabel();
		lblPlayerTurn.setText("Player 1 Turn");
		lblPlayerTurn.setForeground(Color.WHITE);
		lblPlayerTurn.setHorizontalAlignment(SwingConstants.CENTER);
		lblPlayerTurn.setFont(new Font("Tahoma", Font.BOLD, 36));
		lblPlayerTurn.setBounds(680, 30, 300, 50);
		contentPane.add(lblPlayerTurn);
		lblPlayerTurn.setVisible(false);
		
		board=new JLabel();
		board.setIcon(ResizeImage("/images/board.jpg",650,550));
		board.setBounds(5, 5, 650, 550);
		contentPane.add(board);
		board.setVisible(false);
		
		lblDice = new Dice();
		
		
		
		rollButton = new JButton("Roll Dice");
		rollButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				lblDice.roll();
			}
		});
		rollButton.setFont(new Font("Tahoma", Font.PLAIN, 24));
		rollButton.setBounds(690, 240, 250, 70);
		contentPane.add(rollButton);	
		rollButton.setVisible(false);
		
		lblBg = new JLabel();
		lblBg.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblBg.setIcon(ResizeImage("/images/bg.jpg",1000,600));
		lblBg.setBounds(0, 0, 1000, 600);
		contentPane.add(lblBg);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"2", "3", "4", "5", "6"}));
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 32));
		comboBox.setBounds(455, 330, 90, 50);
		contentPane.add(comboBox);
		
		startButton = new JButton("Start Game");
		startButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				PlayerCount = Integer.parseInt(comboBox.getSelectedItem().toString());
				lblLogo.setVisible(false);
				lblStartGame.setVisible(false);
				comboBox.setVisible(false);
				startButton.setVisible(false);
				board.setVisible(true);
				lblDice.display();
				lblPlayerTurn.setVisible(true);
				rollButton.setVisible(true);
				players=new Player[PlayerCount];
				for(int i=0;i<PlayerCount;i++){
					players[i]=new Player(i+1);
				}
			}
		});
		startButton.setFont(new Font("Tahoma", Font.PLAIN, 24));
		startButton.setBounds(350, 420, 300, 70);
		contentPane.add(startButton);	
		
		
	}
	public ImageIcon ResizeImage(String path,int width,int height){
		return (new ImageIcon(new ImageIcon(Game.class.getResource(path)).getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT)));
	}
	class Dice{
		private JLabel dice;
		Dice(){
			dice = new JLabel();
			dice.setIcon(ResizeImage("/images/dice1.png",100,100));
			dice.setBounds(760, 100, 100, 100);
			contentPane.add(dice);
			dice.setVisible(false);
		}
		public void display(){
			dice.setVisible(true);
		}
		public void roll(){
			Random randObj=new Random();
			int rollNum=randObj.nextInt(5)+1;
			dice.setIcon(ResizeImage("/images/dice"+rollNum+".png",100,100));
			playerTurn=(playerTurn+1)%PlayerCount;
			players[playerTurn].move(rollNum);
			if(playerTurn+2<=PlayerCount){
				lblPlayerTurn.setText("Player "+(playerTurn+2)+" Turn");
			}
			else{
				lblPlayerTurn.setText("Player 1 Turn");
			}
		}
	}
	class Player{
		private JLabel player;
		private int playerNo;
		private int playerPos=-1;
		Player(int tmp){
			playerNo=tmp;
			player = new JLabel();
			player.setIcon(ResizeImage("/images/player"+tmp+".png",50,50));
			player.setBounds(700+80*((tmp-1)%3),350+80*((tmp-1)/3),50,50);
			contentPane.remove(lblBg);
			contentPane.remove(board);
			contentPane.add(player);
			contentPane.add(board);
			contentPane.add(lblBg);
		}
		public void move(int x){
			if(playerPos+x<100){
				playerPos+=x;
				SnakesNLadders();
				player.setBounds(boardOrder[playerPos][0], boardOrder[playerPos][1], 50, 50);
			}
			if(playerPos==99){
				setVisible(false);
				bgClip.stop();
				Duration totalTime = Duration.between(startTime, Instant.now());
		        new ScoreBoard(playerNo,totalTime).setVisible(true);
			}
		}
		public void SnakesNLadders(){
			for(int x=0;x<snakes.length;x++){
				if(snakes[x][0]==(playerPos+1)){
					PlayMusic("snake");
					playerPos=snakes[x][1]-1;
				}
			}
			for(int y=0;y<ladders.length;y++){
				if(ladders[y][0]==(playerPos+1)){
					PlayMusic("ladder");
					playerPos=ladders[y][1]-1;
				}
			}
		}
	}
	public void PlayMusic(String sound){
		try {
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("src/mp3/"+sound+".wav").getAbsoluteFile());
			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
