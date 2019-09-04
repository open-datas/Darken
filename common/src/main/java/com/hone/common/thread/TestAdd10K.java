package com.hone.common.thread;

public class TestAdd10K {

	private long count = 0;
	private void add10k() {
		int idx = 0;
		while (idx++<1000) {
			// count += 1;
			set(get() + 1);
		}
	}
	
	synchronized long get() {
		return count;
	}
	
	synchronized void set(long v) {
		count = v;
	}
	public static void main(String[] args) {
		TestAdd10K add10k = new TestAdd10K();
		add10k.add10k();
	}
}
