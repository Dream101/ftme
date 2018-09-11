package test.tt;

public class Ceshi {
	static int i = 0;
	public static void main(String[] args) {
		for (int n = 0; n < 10; n++) {
			Thread thread = new Thread(new Runnable() {
				public void run() {
					int j = 0;
					while (j++ <100) {
						i++;
						System.out.println(i);
					}
				}
			});
			thread.start();
		}
		System.out.println(i);
	}
	public static void Threads(int k) {
		for (int n = 0; n < k; n++) {
			Thread thread = new Thread(new Runnable() {
				public void run() {
					int j = 0;
					while (j++ <100) {
						i++;
						System.out.println(i);
					}
				}
			});
			thread.start();
		}
	}
	public static Thread[] getThreads() {
		Thread[] thread = new Thread[10];
		for (int k = 0; k < 10; k++) {
			thread[k] = new Thread(new Runnable() {
				public void run() {
					int j = 0;
					while (j++ <100) {
						i++;
						System.out.println(i);
					}
				}
			});
		}
		return thread;
	}

}
