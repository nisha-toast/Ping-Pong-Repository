package com.main;

import java.awt.Color;
import java.awt.Graphics;

public class Ball {
	
	//instance variables: x, y, xVelocity, yVelocity, size, speed
	// x & y : location of ball's upper left corner
	// cx & cy : Change in x or y per frame
	// size : width & height of the ball
	// speed : related to xVelocity& yVelocity, speed if always positive
	
	private int x, y, xVelocity, yVelocity, speed, size;
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getxVelocity() {
		return xVelocity;
	}

	public void setxVelocity(int xVelocity) {
		this.xVelocity = xVelocity;
	}

	public int getyVelocity() {
		return yVelocity;
	}

	public void setyVelocity(int yVelocity) {
		this.yVelocity = yVelocity;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	private Color color;
	
	//Constructor
	public Ball(int x, int y, int xVelocity, int yVelocity, int speed, int size, Color color) {
		super();
		this.x = x;
		this.y = y;
		this.xVelocity = xVelocity;
		this.yVelocity = yVelocity;
		this.speed = speed;
		this.size = size;
		this.color = color;
	}
	
	//Paint method for the ball
	public void paint(Graphics g) {
		g.setColor(color);
		
		//paint the ball at x, y with width & height of the ball
		g.fillOval(x, y, size, size);
	}
	
	public void ballMovement() {
		x += xVelocity; //move 3 pixels right each frame
		y += yVelocity; // move 3 pixels down each frame
	}
	
	//Collision detection & reverses direction
	//Method takes in the top & bottom y values of the edges it bounces off 
	public void bounce(int top, int bottom) {
	
		//y value at/beyond the bottom//hard corded here
		if (y > 535 - size ) {
			reverseY();
		}
		//y value at/beyond the top
		else if(y < top - size ) {
			reverseY();
		}
		
		//x value is at the left or right side
		if (x <= 0 - size) { //left
			reverseX();
		}
		
		//right
		else if(x > Main.WIDTH - size) {
			reverseX();
		}
	}
	
	//Reverse the ball's change in x value
	public void reverseX() {
		xVelocity *= -1;
	}
	
	//Reverse the ball's change in y value
	public void reverseY() {
		yVelocity *= -1;
	}
	
}
