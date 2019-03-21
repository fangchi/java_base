package com.fc.thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 在同一时刻，只能有两个线程能够并行访问，超过限制的其他线程进入阻塞状态
 * @author chi.fang
 *
 */
public class TwinsLock implements Lock {

	private final Sync sync = new Sync(2);

	private static final class Sync extends AbstractQueuedSynchronizer {
		private static final long serialVersionUID = -7889272986162341211L;

		/**
		 * 最大容量
		 * @param maxCount
		 */
		Sync(int maxCount) {
			if (maxCount <= 0) {
				throw new IllegalArgumentException("count must large than zero.");
			}
			setState(maxCount);
		}

		@Override
		public int tryAcquireShared(int reduceCount) {
			for (;;) {
				int current = getState();
				System.out.println("尝试获取锁,线程:"+Thread.currentThread()+",当前State:"+current);
				int newCount = current - reduceCount;
				//小于0  则代表获取失败
				//0 成功  但无剩余资源
				//大于0 成功
				if (newCount < 0 || compareAndSetState(current, newCount)) {
					return newCount;
				}
			}
		}

		@Override
		public boolean tryReleaseShared(int returnCount) {
			for (;;) {
				int current = getState();
				int newCount = current + returnCount;
				if (compareAndSetState(current, newCount)) {
					return true;
				}
			}
		}
		
	}

	@Override
	public void lock() {
		sync.acquireShared(1);
	}

	@Override
	public void lockInterruptibly() throws InterruptedException {
		sync.acquireSharedInterruptibly(1);
	}

	@Override
	public boolean tryLock() {
		return sync.tryAcquireShared(1) >= 0;
	}

	@Override
	public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
		return sync.tryAcquireSharedNanos(1, unit.toNanos(time));
	}

	@Override
	public void unlock() {
		sync.releaseShared(1);
	}

	@Override
	public Condition newCondition() {
		return null;
	}
	
	public static void main(String[] args) {
		final TwinsLock lock = new TwinsLock();
		for (int i = 0; i < 10; i++) {
			Runnable w = new Runnable() {
				@Override
				public void run() {
					while (true) {
						lock.lock();
						try {
							Thread.sleep(1000L);
							System.out.println("获得锁并执行:"+Thread.currentThread());
							Thread.sleep(1000L);
						} catch (Exception ex) {
							ex.printStackTrace();
						} finally {
							lock.unlock();
						}
					}
					
				}
			};
			Thread thread  = new Thread(w);
			thread.start();
		}

		try {
			Thread.sleep(20000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
}
