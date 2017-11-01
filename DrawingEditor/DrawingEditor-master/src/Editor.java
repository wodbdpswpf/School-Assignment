import java.awt.AWTException;

import javax.swing.JFrame;

public class Editor {

	public static void main(String[] args) throws AWTException {

		JFrame frm = new JFrame();
		frm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frm.setSize(200, 200);
		frm.setVisible(false);
		LoginDialog log = new LoginDialog(frm);
		log.setLocationRelativeTo(frm);
		log.setSize(300, 300);
		log.setVisible(true);
	}
}
