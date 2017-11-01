import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.KeyEvent;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

public class Editor_View {
	JFrame frame;
	int x1;
	int y1;
	int x2;
	int y2;
	Drawinfo_Controller controller;

	DrawPanel drawPanel;
	//Vector<MyShape> vc = new Vector<MyShape>();
	int dist;
	boolean multi_selec;
	JButton btn_line;
	JButton btn_rec;
	JButton btn_cir;
	JButton btn_del;
	JButton btn_group;
	JButton btn_copy;
	//JButton btn_paste_undo;
	JButton messagesend;
	JButton drawsend;
	MenuItem fnew;
	MenuItem fopen;
	MenuItem fsave;
	MenuItem fexit;
	JTextArea textarea;
	JTextField textfield;
	
	Editor_View(Drawinfo_Controller pController) {
		MenuBar mb = new MenuBar();
		Menu file = new Menu("FILE");
		fnew = new MenuItem("NEW");
		fopen = new MenuItem("OPEN");
		fsave = new MenuItem("SAVE");
		fexit = new MenuItem("EXIT");
		controller = pController;

		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(2048, 1024);
		frame.setVisible(true);
		frame.setMenuBar(mb);

		mb.add(file);
		file.add(fnew);
		file.addSeparator();
		file.add(fopen);
		file.add(fsave);
		file.addSeparator();
		file.add(fexit);

		JPanel panel = new JPanel();
		JPanel buttonPanel = new JPanel();
		JPanel sendPanel = new JPanel();
		JPanel messagePanel = new JPanel();
		JPanel textPanel = new JPanel();

		btn_line = new JButton("Line");
		btn_rec = new JButton("Rectangle");
		btn_cir = new JButton("Circle");
		btn_del = new JButton("Delete");
		btn_copy = new JButton("Copy");
		btn_group = new JButton("Group");
		//btn_paste_undo = new JButton("Paste_Undo");
		messagesend = new JButton("Send");
		drawsend = new JButton("Drawing Send");

		fnew.addActionListener(controller);
		fopen.addActionListener(controller);
		fsave.addActionListener(controller);
		fexit.addActionListener(controller);
		btn_line.addActionListener(controller);
		btn_rec.addActionListener(controller);
		btn_cir.addActionListener(controller);
		btn_del.addActionListener(controller);
		btn_group.addActionListener(controller);
		btn_copy.addActionListener(controller);
		//btn_paste_undo.addActionListener(controller);
		messagesend.addActionListener(controller);
		drawsend.addActionListener(controller);
		buttonPanel.add(btn_line);
		buttonPanel.add(btn_rec);
		buttonPanel.add(btn_cir);
		buttonPanel.add(btn_del);
		buttonPanel.add(btn_group);
		buttonPanel.add(btn_copy);
		// buttonPanel.add(btn_paste_undo);

		drawPanel = new DrawPanel();
		buttonPanel.setLayout(new GridLayout(7, 1, 5, 5));
		JScrollPane js = new JScrollPane();

		textarea = new JTextArea();
		textarea.setColumns(20);
		textarea.setRows(30);

		messagePanel.add(js);
		js.setViewportView(textarea);

		textfield = new JTextField();
		sendPanel.add(textfield, BorderLayout.SOUTH);
		textfield.setColumns(15);
		textPanel.add(textfield);
		textPanel.add(messagesend);

		sendPanel.setLayout(new BorderLayout());
		sendPanel.add(textPanel, BorderLayout.SOUTH);
		sendPanel.add(messagePanel, BorderLayout.CENTER);
		sendPanel.add(drawsend, BorderLayout.NORTH);

		drawPanel.addMouseListener(controller);
		drawPanel.addMouseMotionListener(controller);
		panel.setBackground(Color.WHITE);
		panel.setLayout(new BorderLayout()); // borderlayout으로 설정
		panel.add(drawPanel, BorderLayout.CENTER); // 가운데에
		panel.add(buttonPanel, BorderLayout.LINE_START); // 앞에
		panel.add(sendPanel, BorderLayout.LINE_END);
		frame.add(panel);

	}

	class DrawPanel extends JPanel {

		public void paint(Graphics g) {
			Graphics2D g2 = (Graphics2D) g;
			// 일시적인 그려짐
			if (dist == 1) {
				g.drawLine(x1, y1, x2, y2);
			} else if (dist == 2) {
				int w = (x2 > x1) ? x2 - x1 : x1 - x2;
				int h = (y2 > y1) ? y2 - y1 : y1 - y2;
				g2.drawRect((x2 > x1) ? x1 : x2, (y2 > y1) ? y1 : y2, w, h);
			} else if (dist == 3) {
				int w = (x2 > x1) ? x2 - x1 : x1 - x2;
				int h = (y2 > y1) ? y2 - y1 : y1 - y2;
				g.drawOval((x2 > x1) ? x1 : x2, (y2 > y1) ? y1 : y2, w, w);
			} else if (multi_selec) {
				int w = (x2 > x1) ? x2 - x1 : x1 - x2;
				int h = (y2 > y1) ? y2 - y1 : y1 - y2;
				g2.drawRect((x2 > x1) ? x1 : x2, (y2 > y1) ? y1 : y2, w, h);
			}
			for (int i = 0; i < controller.vc.size(); i++)

			{
				MyShape shape = controller.vc.elementAt(i);
				shape.draw(g2);
				shape.make_selectbox(g2);

			}

		}
	}
}