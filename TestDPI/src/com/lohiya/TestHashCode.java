package com.lohiya;

class Employee{
	
}

public class TestHashCode {
	
	private int i;	
	private int data;
	private Employee emp;
	
	/*@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + data;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TestHashCode other = (TestHashCode) obj;
		if (data != other.data)
			return false;
		return true;
	}*/
	
	

	public static void main(String[] args) {
		
		
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + data;
		result = prime * result + ((emp == null) ? 0 : emp.hashCode());
		result = prime * result + i;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TestHashCode other = (TestHashCode) obj;
		if (data != other.data)
			return false;
		if (emp == null) {
			if (other.emp != null)
				return false;
		} else if (!emp.equals(other.emp))
			return false;
		if (i != other.i)
			return false;
		return true;
	}
	
	

	/*@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + data;
		result = prime * result + i;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TestHashCode other = (TestHashCode) obj;
		if (data != other.data)
			return false;
		if (i != other.i)
			return false;
		return true;
	}*/
	
	

}
