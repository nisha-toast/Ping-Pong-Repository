package com.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.Timer;

public class Game extends JPanel {
	private static final long serialVersionUID = 1L;

	private Ball pongBall;
	private Paddle userPaddle, pcPaddle;
	private int userScore = 0;
	private int pcScore = 0;
	private boolean gameOver = false;

	private Timer timer;
	private JButton restartButton;

	public Game() {
		// Initialize ball and paddles
		pongBall = new Ball(300, 200, 3, 3, 3, 10, Color.BLACK); // Ball at the center
		userPaddle = new Paddle(95, 15, 10, 200, 10, Color.BLUE); // Left paddle
		pcPaddle = new Paddle(95, 15, 959, 200, 3, Color.RED); // Right paddle

		setFocusable(true);
		setLayout(null); // Use null layout for manual button positioning
		addRestartButton();
		setupKeyBindings();
		startGame();
	}

	private void startGame() {
		// Timer to handle game logic
		int delay = 10; // 10ms per frame
		timer = new Timer(delay, e -> {
			if (!gameOver) {
				gameLogic();
				repaint();
			}
		});
		timer.start();
	}

	private void addRestartButton() {
		restartButton = new JButton("Restart");
		restartButton.setBounds(450, 250, 100, 40); // Center the button
		restartButton.setVisible(false); // Hidden until game over
		restartButton.addActionListener(e -> resetGame()); //Listens for when the user clicks it.
		add(restartButton);
	}

	private void setupKeyBindings() {
	    // Timer for moving the paddle up
		
		//timer that fires every 10 milliseconds 
	    Timer moveUpTimer = new Timer(10, e -> {
	        userPaddle.setY(userPaddle.getY() - userPaddle.getSpeed());
	        userPaddle.constrainMovement(0, this.getHeight());
	    });

	    // Timer for moving the paddle down
	    Timer moveDownTimer = new Timer(10, e -> {
	        userPaddle.setY(userPaddle.getY() + userPaddle.getSpeed());
	        userPaddle.constrainMovement(0, this.getHeight());
	    });

	    // Key Binding for "W" pressed
	    getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("pressed W"), "moveUpStart");
	    getActionMap().put("moveUpStart", new AbstractAction() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            moveUpTimer.start(); // Start moving up
	        }
	    });

	    // Key Binding for "W" released
	    getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("released W"), "moveUpStop");
	    getActionMap().put("moveUpStop", new AbstractAction() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            moveUpTimer.stop(); // Stop moving up
	        }
	    });

	    // Key Binding for "S" pressed
	    getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("pressed S"), "moveDownStart");
	    getActionMap().put("moveDownStart", new AbstractAction() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            moveDownTimer.start(); // Start moving down
	        }
	    });

	    // Key Binding for "S" released
	    getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("released S"), "moveDownStop");
	    getActionMap().put("moveDownStop", new AbstractAction() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            moveDownTimer.stop(); // Stop moving down
	        }
	    });
	}


	private void resetGame() {
		userScore = 0;
		pcScore = 0;
		pongBall = new Ball(300, 200, 3, 3, 3, 10, Color.BLACK); // Reset ball
		gameOver = false;
		restartButton.setVisible(false); // Hide restart button
		repaint();
		startGame(); // Restart the timer
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		//Color the window
		g.setColor(Color.PINK);
		g.fillRect(0, 0, Main.WIDTH, Main.HEIGHT);

		// Draw ball and paddles
		pongBall.paint(g);
		userPaddle.paint(g);
		pcPaddle.paint(g);

		// Draw scores
		g.setColor(Color.BLACK);
		g.setFont(new Font("Arial", Font.BOLD, 24));
		g.drawString("Player: " + userScore, 50, 30);
		g.drawString("PC: " + pcScore, Main.WIDTH - 150, 30);

		// Draw game over text
		if (gameOver) {
			g.setFont(new Font("Arial", Font.BOLD, 36));
			g.drawString("Game Over", Main.WIDTH / 2 - 100, Main.HEIGHT / 2 - 50);
		}
	}

	public void gameLogic() {
		// Ball movement
		pongBall.ballMovement();

		// Ball collision with top and bottom walls
		pongBall.bounce(0, Main.HEIGHT);

		// Ball collision with user paddle
		
		if (pongBall.getX() <= userPaddle.getX() + userPaddle.getWidth() //Check if the left edge of the ball is to the left or at the right edge of the paddle 
				&& pongBall.getY() + pongBall.getSize() >= userPaddle.getY() // Check if the bottom edge of the ball is below or at the top edge of the paddle
				&& pongBall.getY() <= userPaddle.getY() + userPaddle.getHeight()) { //Check if the top edge of the ball is above or at the bottom edge of the paddle
			pongBall.reverseX();
			// Bounce back
			userScore++;
		}

		// Ball collision with PC paddle
		if (pongBall.getX() + pongBall.getSize() >= pcPaddle.getX()
				&& pongBall.getY() + pongBall.getSize() >= pcPaddle.getY()
				&& pongBall.getY() <= pcPaddle.getY() + pcPaddle.getHeight()) {
			pongBall.reverseX();
			pcScore++;
		}

		// Ball misses user paddle
		if (pongBall.getX() < 0) {
			gameOver = true;
			timer.stop();
			restartButton.setVisible(true); // Show restart button
		}

		// Ball misses PC paddle
		if (pongBall.getX() > Main.WIDTH) {
			gameOver = true;
			timer.stop();
			restartButton.setVisible(true); // Show restart button
		}

		// PC paddle movement (automated)
		pcPaddle.paddleMovement(pongBall.getY());
		pcPaddle.constrainMovement(0, this.getHeight());
	}
}