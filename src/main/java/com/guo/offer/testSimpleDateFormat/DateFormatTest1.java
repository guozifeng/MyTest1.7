package com.guo.offer.testSimpleDateFormat;

import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * 问题解决第一种方式
 * 
 * @author Administrator
 *
 */
public class DateFormatTest1 {
	private static String date[] = { "01-Jan-1999", "01-Jan-2000", "01-Jan-2001" };

	public static void main(String[] args) {
		for (int i = 0; i < date.length; i++) {
			final int temp = i;
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy", Locale.US);
						while (true) {
							String str1 = date[temp];
							String str2 = sdf.format(sdf.parse(str1));
							// System.out.println(Thread.currentThread().getName()
							// + ", " + str1 + "," + str2);
							if (!str1.equals(str2)) {
								throw new RuntimeException(
										Thread.currentThread().getName() + ", Expected " + str1 + " but got " + str2);
							}
						}
					} catch (Exception e) {
						throw new RuntimeException("parse failed", e);
					}
				}
			}).start();
		}
	}
}