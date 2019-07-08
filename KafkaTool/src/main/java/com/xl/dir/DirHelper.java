package com.xl.dir;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.UUID;

public class DirHelper {

	private static final Runtime sys = Runtime.getRuntime();

	/**
	 * s
	 */
	public static File createTempDir(String root, String namePrefix) throws IOException {
		final File dir = createDirectory(root, namePrefix);
		sys.addShutdownHook(new Thread() {
			@Override
			public void run() {
				try {
					deleteRecursively(dir);
				} catch (Exception e) {
					//
				}
			}
		});
		return dir;
	}

	/**
	 *
	 */
	public static File createTempDir() throws IOException {
		return createTempDir(System.getProperty("java.io.tmpdir"), "dir");
	}

	/**
	 *
	 */
	public static File createTempDir(String namePrefix) throws IOException {
		return createTempDir(System.getProperty("java.io.tmpdir"), namePrefix);
	}

	/**
	 *
	 */
	public static int getRandomPort() {
		int port = 0;
		try {
			ServerSocket s = new ServerSocket(0);
			port = s.getLocalPort();
			s.close();
		} catch (IOException e) {
		}
		return port;
	}

	private static File createDirectory(String root, String namePrefix) throws IOException {
		int attempts = 0;
		int maxAttempts = 10;
		File dir = null;
		while (dir == null) {
			attempts++;
			if (attempts > maxAttempts) {
				throw new IOException("Failed to create a temp directory under "
					+ root + " after " + maxAttempts + " attemps!");
			}
			try {
				dir = new File(root, namePrefix + "-" + UUID.randomUUID().toString());
				if (dir.exists() || !dir.mkdirs()) {
					dir = null;
				}
			} catch (SecurityException e) {
				dir = null;
			}

		}
		return dir.getCanonicalFile();
	}

	private static void deleteRecursively(File file) throws IOException {
		if (file != null) {
			try {
				if (file.isDirectory()) {
					IOException exception = null;
					for (File child : file.listFiles()) {
						try {
							deleteRecursively(child);
						} catch (IOException e) {
							exception = e;
						}
					}
					if (exception != null) {
						throw exception;
					}
				}
			} finally {
				if (!file.delete()) {
					if (file.exists()) {
						throw new IOException("Failed to delete: " + file.getAbsolutePath());
					}
				}
			}

		}
	}

}
