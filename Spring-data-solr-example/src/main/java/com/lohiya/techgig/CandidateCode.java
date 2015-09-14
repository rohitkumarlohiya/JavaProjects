package com.lohiya.techgig;

import java.util.ArrayList;
import java.util.List;

class Point {

	private int x;
	private int y;
	private int pointValue;

	Point(int x, int y, int pointValue) {
		this.x = x;
		this.y = y;
		this.pointValue = pointValue;
	}

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

	public int getPointValue() {
		return pointValue;
	}

	public void setPointValue(int pointValue) {
		this.pointValue = pointValue;
	}
}

public class CandidateCode {

	static int total_no_of_path = 0;

	public static int no_of_path(int[] input1, int[] input2) {

		Point p = new Point(0, 0, getPointValue(input1, input2, 0, 0));

		List<Point> points = getListOfotherReachedPointFromPassedPoints(p,
				input1, input2);

		recursive_method(input1, input2, points);

		return total_no_of_path;
	}

	private static void recursive_method(int[] input1, int[] input2,
			List<Point> points) {
		
		
		for (Point point : points) {

			if(point.getX() == (input1[0]-1)  && point.getY() == (input1[1]-1))
			{
				total_no_of_path +=1;
			}
			else
			{
				recursive_method(input1, input2, getListOfotherReachedPointFromPassedPoints(point,input1, input2));
			}
		}
	}

	private static List<Point> getListOfotherReachedPointFromPassedPoints(
			Point p, int[] input1, int[] input2) {

		List<Point> points = new ArrayList<Point>();

		if (p.getPointValue() == 1) {
			
			if(p.getY() < input1[1]-1)
			{
			points.add(new Point(p.getX(), p.getY() + 1, getPointValue(input1,
					input2, p.getX(), p.getY() + 1)));
			}

		} else if (p.getPointValue() == 2) {
			
			if(p.getX() < input1[0]-1)
			{
			points.add(new Point(p.getX() + 1, p.getY(), getPointValue(input1,
					input2, p.getX() + 1, p.getY())));
			}

		} else if (p.getPointValue() == 3) {
			if(p.getY() < input1[1]-1 && p.getX() < input1[0]-1)
			{
			points.add(new Point(p.getX() + 1, p.getY() + 1, getPointValue(
					input1, input2, p.getX() + 1, p.getY() + 1)));
			}

		} else if (p.getPointValue() == 4) {
			if(p.getY() < input1[1]-1)
			{
			points.add(new Point(p.getX(), p.getY() + 1, getPointValue(input1,
					input2, p.getX(), p.getY() + 1)));
			}
			if(p.getX() < input1[0]-1)
			{
			points.add(new Point(p.getX() + 1, p.getY(), getPointValue(input1,
					input2, p.getX() + 1, p.getY())));
			}

		} else if (p.getPointValue() == 5) {
			if(p.getY() < input1[1]-1)
			{
			points.add(new Point(p.getX(), p.getY() + 1, getPointValue(input1,
					input2, p.getX(), p.getY() + 1)));
			}
			if(p.getY() < input1[1]-1 && p.getX() < input1[0]-1)
			{
			points.add(new Point(p.getX() + 1, p.getY() + 1, getPointValue(
					input1, input2, p.getX() + 1, p.getY() + 1)));
			}

		} else if (p.getPointValue() == 6) {
			if(p.getX() < input1[0]-1)
			{
			points.add(new Point(p.getX() + 1, p.getY(), getPointValue(input1,
					input2, p.getX() + 1, p.getY())));
			}
			if(p.getY() < input1[1]-1 && p.getX() < input1[0]-1)
			{
			points.add(new Point(p.getX() + 1, p.getY() + 1, getPointValue(
					input1, input2, p.getX() + 1, p.getY() + 1)));
			}

		} else if (p.getPointValue() == 7) {
			if(p.getX() < input1[0]-1)
			{
			points.add(new Point(p.getX() + 1, p.getY(), getPointValue(input1,
					input2, p.getX() + 1, p.getY())));
			}
			if(p.getY() < input1[1]-1)
			{
			points.add(new Point(p.getX(), p.getY() + 1, getPointValue(input1,
					input2, p.getX(), p.getY() + 1)));
			}
			if(p.getY() < input1[1]-1 && p.getX() < input1[0]-1)
			{
			points.add(new Point(p.getX() + 1, p.getY() + 1, getPointValue(
					input1, input2, p.getX() + 1, p.getY() + 1)));
			}

		}
		return points;
	}

	private static int getPointValue(int[] input1, int[] input2, int x1, int y1) {
		return input2[(x1 * input1[1]) + y1];
	}

	public static void main(String[] args) {

		int[] input1 = { 4, 6 };
		int[] input2 = { 1, 3, 0, 0, 0, 0, 0, 0, 4, 5, 1, 0, 0, 0, 0, 6, 7, 6,
				0, 0, 0, 0, 5, 0 };
		System.out.println("no_of_path = " + no_of_path(input1, input2));

	}
}
