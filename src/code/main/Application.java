package code.main;

import gui.login.LoginPage;

import java.awt.*;

public class Application {
	public static void main(String[] args) {
		LoginPage page = new LoginPage();
		page.run(new Point(0, 0));
	}
}
