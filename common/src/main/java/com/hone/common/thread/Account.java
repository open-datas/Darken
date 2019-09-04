package com.hone.common.thread;

import java.util.Vector;

public class Account {

	private int balance;
	void transfer(Account target, int amt) {
		if (this.balance > amt) {
			this.balance -= amt;
			target.balance += amt;
		}
	}
	
	void addIfNotExit(Vector<Object> v, Object o) {
		System.out.println(o);
		synchronized (v) {
			System.out.println(o);
			if(!v.contains(o)) {
				System.out.println(o);
				v.add(o);
			}
		}
	}
	public static void main(String[] args) {
		Account account = new Account();
		Vector<Object> vector = new Vector<Object>();
		account.addIfNotExit(vector, "12");
		account.addIfNotExit(vector, "12");
	}
}
