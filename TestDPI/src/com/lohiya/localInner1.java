package com.lohiya;

public class localInner1 {
	private int data = 30;// instance variable

	void display() {
		class Local {
			void msg() {
				System.out.println("display ==> "+data);
			}
		}
		Local l = new Local();
		l.msg();
	}
	
	void display2() {
		class MyLocal {
			void msg() {
				System.out.println("display2 ==> "+data);
			}
		}
		MyLocal l = new MyLocal();
		l.msg();
	}

	public static void main(String args[]) {
		localInner1 obj = new localInner1();
		obj.display();
		obj.display2();
	}
}