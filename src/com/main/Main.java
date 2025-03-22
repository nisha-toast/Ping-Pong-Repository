package com.main;

import java.awt.Color;

import java.awt.Dimension;


import javax.swing.JFrame;



/* Class where the canvas is formed*/


public class Main {

	static JFrame frame = new JFrame("Simple Ping Pong");

	

	/*Height and width for aspect ratio of the window */

	//constant name is capitalized

	public static final int WIDTH = 1000;

	public static int getWidth() {

		return WIDTH;

	}



	public static int getHeight() {

		return HEIGHT;

	}



	public static final int HEIGHT = WIDTH * 9 / 16;

	private static Dimension dim = new Dimension(WIDTH, HEIGHT);

	

	

	public static void main(String[] args) {

		// TODO Auto-generated method stub

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.setResizable(false); //ensure window cannot be resized

		

		//Make the ping pong game

		Game game = new Game();

		

		//Use the game

		frame.pack(); //Causes this Window to be sized to fit the preferred size and layouts of its subcomponents. 

		frame.setLocationRelativeTo(null); //null: window is placed in the center of the screen

		frame.setPreferredSize(dim);

		frame.setMaximumSize(dim);

		frame.setMinimumSize(dim);

		frame.setLocationRelativeTo(null);

		frame.add(game); //game has to inherit from component

		frame.setVisible(true); //Shows this Window 

		

//		game.timerStart(game);

		

		

	}


}