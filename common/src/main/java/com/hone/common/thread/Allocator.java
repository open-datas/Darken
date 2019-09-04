package com.hone.common.thread;

import java.util.List;

public class Allocator {

	private List<Object> als;
	// 申请资源
	synchronized void apply(Object from, Object to) {
		while (als.contains(from)) {
			try {
				wait();
			} catch (Exception e) {
				// TODO: handle exception
			}
			
		}
		als.add(from);
		als.add(to);
	}
	// 归还资源
	synchronized void free(Object from, Object to) {
		als.remove(from);
		als.remove(to);
		notifyAll();
	}
}
