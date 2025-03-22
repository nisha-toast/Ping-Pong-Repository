package com.main;
import java.awt.Color;

import java.awt.Graphics;


public class Paddle {


	// Paddle: rectangle block that moves up & down

	// instance variables height, x, y, speed, width, color


	// x & y are the position of the paddle in coordinates

	// height: paddle height in pixels

	// speed: distance the paddle may move up or down per frame

	// width: width of the paddle if you want to change it in the future


	private int height, width, x, y, speed;

	private Color color;


	public Paddle(int height, int width, int x, int y, int speed, Color color) {

		this.height = height;

		this.width = width;

		this.x = x;

		this.y = y;

		this.speed = speed;

		this.color = color;

	}


	// Getters and setters for paddle properties

	public int getX() {

		return x;

	}


	public int getY() {

		return y;

	}


	public void setY(int y) {

		this.y = y;

	}


	public int getHeight() {

		return height;

	}


	public int getWidth() {

		return width;

	}


	public int getSpeed() {

		return speed;

	}


	// Draw the paddle

	public void paint(Graphics g) {

		g.setColor(color);

		g.fillRect(x, y, width, height);

	}


	// Move paddle up or down based on moveY (used for PC paddle automation)

	public void paddleMovement(int targetY) {

		int centerY = y + height / 2;


		if (Math.abs(centerY - targetY) > speed) {

			if (centerY > targetY) {

				y -= speed; // Move up

			} else if (centerY < targetY) {

				y += speed; // Move down

			}

		}

	}


	// Constrain paddle movement within the screen boundaries

	public void constrainMovement(int top, int bottom) {


		if (y < top) {

			y = top; // Prevent moving above the top boundary

		}

		if (y > bottom - height) {

			y = bottom - height; // Prevent moving below the bottom boundary

		}

	}


}