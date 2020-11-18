package com.handalcargo.core;

import javax.swing.SwingUtilities;

import com.handalcargo.ui.Login;

public class Main {
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(
			new Runnable() {
				@Override
				public void run() {
					Login.getInstance();
				}
			}
		);
	}
}
