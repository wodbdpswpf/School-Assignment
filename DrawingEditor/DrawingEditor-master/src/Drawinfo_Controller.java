import java.awt.AWTException;
import java.awt.FileDialog;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;

public class Drawinfo_Controller implements MouseListener, MouseMotionListener, ActionListener {

	private Editor_View view;
	ShapeFactory shapeFactory;
	ArrayList<Integer> sel_index;
	ArrayList<ArrayList<Integer>> group_list;
	ObjectOutputStream out;
	ObjectInputStream in;
	Socket socket;
	ObjectOutputStream output;
	private String name;
	private String message = "";
	Vector<MyShape> vc;
	private String chat = "";
	private String serverIp = "10.20.43.10";

	Drawinfo_Controller(String name) {
		this.name = name;
		vc =  new Vector<MyShape>();
		sel_index = new ArrayList<Integer>();
		group_list = new ArrayList<ArrayList<Integer>>();
		shapeFactory = new ShapeFactory();

	}

	public void attachView(Editor_View view) {
		this.view = view;
	}

	public void startUp() {

		try {
			socket = new Socket(serverIp, 7777);
			ClientSender clientSender = new ClientSender(socket);
			output = new ObjectOutputStream(socket.getOutputStream());
			ClientReceiver clientReceiver = new ClientReceiver(socket);
			clientReceiver.start();
			// out = new ObjectOutputStream(socket.getOutputStream());
			// clientSender.start();

			// Thread remote = new Thread(new RemoteReader());
			System.out.println("connected");
			// remote.start();
		} catch (Exception ex) {

			System.out.println("couldn't connet - you'll have to play alone");
		}

	}

	public void get_Group(int index) {
		for (int i = 0; i < group_list.size(); i++) {
			for (int j = 0; j < group_list.get(i).size(); j++) {
				if (index == group_list.get(i).get(j)) {
					for (int k = 0; k < group_list.get(i).size(); k++) {
						sel_index.add(group_list.get(i).get(k));
					}
				}
			}

		}

	}
	
	public void delete(){
		for(int i = 0; i < sel_index.size(); i ++){
			vc.removeElementAt(sel_index.get(i));
		}
	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		if (e.getSource().equals(this.view.btn_line)) {
			this.view.dist = 1;
		} else if (e.getSource().equals(this.view.btn_rec)) {
			this.view.dist = 2;
		} else if (e.getSource().equals(this.view.btn_cir)) {
			this.view.dist = 3;
		} else if (e.getSource().equals(this.view.btn_del)) {
				System.out.println(vc.size());
				//vc.removeElementAt(sel_index.get(i));
				delete();
									
		} else if (e.getSource().equals(this.view.btn_group)) {
			group_list.add((ArrayList<Integer>) sel_index.clone());
			for (int i = 0; i < sel_index.size(); i++) {
				vc.get(sel_index.get(i)).set_group(true);
			}
		}else if(e.getSource().equals(this.view.btn_copy)){
			
			for(int i = 0; i < sel_index.size(); i ++){
				Shape shape = vc.get(sel_index.get(i)).getShape();
				vc.add(shapeFactory.getShape(shape));
				this.view.frame.repaint();	
			}
				
		}
		if (e.getSource() == this.view.fnew) {
			vc.clear();
			view.x1 = 0;
			view.y1 = 0;
			view.x2 = 0;
			view.y2 = 0;

			view.frame.repaint();

		} else if (e.getSource() == this.view.fopen) {
			// 열기
			FileDialog fdlg = new FileDialog(this.view.frame, "열기", FileDialog.LOAD);
			fdlg.setVisible(true);
			String dir = fdlg.getDirectory();
			String file = fdlg.getFile();

			if (dir == null || file == null)
				return;

			try {
				ObjectInputStream ois = new ObjectInputStream(
						new BufferedInputStream(new FileInputStream(new File(dir, file))));
				this.vc = (Vector) ois.readObject();
				ois.close();
			} catch (IOException ee) {
			} catch (ClassNotFoundException ee) {
			}

		} else if (e.getSource() == this.view.fsave) {
			// 저장
			FileDialog fdlg = new FileDialog(this.view.frame, "저장", FileDialog.SAVE);
			fdlg.setVisible(true);
			String dir = fdlg.getDirectory();
			String file = fdlg.getFile();

			if (dir == null || file == null)
				return;

			try {
				ObjectOutputStream oos = new ObjectOutputStream(
						new BufferedOutputStream(new FileOutputStream(new File(dir, file))));
				oos.writeObject(this.vc);
				oos.close();
			} catch (IOException ee) {
			}
		} else if (e.getSource() == this.view.fexit) {

			System.exit(0);

		} else if (e.getSource() == this.view.messagesend) {

			try {
				message = this.view.textfield.getText();
				if (message.equals("exit"))
					System.exit(0);
				output.writeObject("[" + name + "]" + message);
				output.flush();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else if (e.getSource() == this.view.drawsend) {
			try {			
				output.writeObject(this.vc.clone());
				output.flush();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
	}

	/*
	 * else if (e.getSource().equals(this.view.btn_paste)) {
	 * 
	 * for (int i = 0; i < view.vc.size(); i++) { if
	 * (sel_shape.get_select().equals(view.vc.get(i).getShape())) {
	 * paste_shape.execute(view, i); break; } this.view.frame.repaint();
	 * 
	 * } } else if (e.getSource().equals(this.view.btn_paste_undo)) {
	 * paste_shape.undo(view); this.view.frame.repaint(); }
	 */

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		this.view.x2 = e.getX();
		this.view.y2 = e.getY();

		for (int i = 0; i < sel_index.size(); i++) {
			vc.get(sel_index.get(i)).move(view.x2, view.y2);
		}

		this.view.frame.repaint();

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

		this.view.x1 = this.view.x2 = e.getX();
		this.view.y1 = this.view.y2 = e.getY();
		if (sel_index.isEmpty()) {
			view.multi_selec = true;
		}
		this.view.frame.repaint();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		this.view.x2 = e.getX();
		this.view.y2 = e.getY();

		this.view.frame.repaint();

		if (this.view.dist == 1) {

			MyShape shape = shapeFactory.getShape(new Line2D.Double(view.x1, view.y1, view.x2, view.y2));
			this.vc.add(shape);
			this.view.dist = 0;

		} else if (this.view.dist == 2) {

			MyShape shape = shapeFactory.getShape(new Rectangle2D.Double(view.x1, view.y1,
					(view.x2 > view.x1) ? view.x2 - view.x1 : view.x1 - view.x2,
					(view.y2 > view.y1) ? view.y2 - view.y1 : view.y1 - view.y2));
			this.vc.add(shape);
			this.view.dist = 0;

		} else if (this.view.dist == 3) {
			MyShape shape = shapeFactory.getShape(
					new Ellipse2D.Double(view.x1, view.y1, (view.x2 > view.x1) ? view.x2 -view.x1 : view.x1 - view.x2,
							(view.y2 > view.y1) ? view.x2 - view.x1 : view.x1 - view.x2));
			this.vc.add(shape);
			this.view.dist = 0; 
		} else if (this.view.multi_selec) {
			Rectangle2D rec = new Rectangle2D.Double(view.x1, view.y1,
					(view.x2 > view.x1) ? view.x2 - view.x1 : view.x1 - view.x2,
					(view.y2 > view.y1) ? view.y2 - view.y1 : view.y1 - view.y2);

			for (int i = 0; i < vc.size(); i++) {
				Rectangle2D is_sel = vc.get(i).getShape().getBounds2D();
				if (rec.contains(is_sel) == true) {
					vc.get(i).set_select(true);
					vc.get(i).set_offset(view.x1, view.y1);
					sel_index.add(i);
					this.view.frame.repaint();
				}
			}
		}

		view.multi_selec = false;

	}

	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated
		this.view.x1 = e.getX();
		this.view.y1 = e.getY();
		view.multi_selec = false;
		if (e.getClickCount() == 1) {
			for (int i = 0; i < sel_index.size(); i++) {
				vc.get(sel_index.get(i)).set_select(false);
			}
			sel_index.clear();
		} else if (e.getClickCount() == 2) {
			for (int i = 0; i < vc.size(); i++) {
				if (vc.get(i).is_select(view.x1, view.y1)) {
					if (vc.get(i).get_group()) {
						sel_index.clear();
						this.get_Group(i);
						System.out.println(sel_index.size());
						for (int j = 0; j < sel_index.size(); j++) {
							vc.get(sel_index.get(i)).set_select(true);
						}
						this.view.frame.repaint();
						break;
					}
					vc.get(i).set_offset(view.x1, view.y1);
					sel_index.add(i);
					this.view.frame.repaint();
					break;
				}
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	class ClientReceiver extends Thread {
		Socket socket;
		ObjectInputStream input;

		public ClientReceiver(Socket socket) {
			this.socket = socket;
			try {
				input = new ObjectInputStream(socket.getInputStream());
			} catch (IOException e) {

			}
		}

		@SuppressWarnings("unchecked")
		public void run() {
			while (input != null) {
				try {
					if (input.readObject() instanceof String) {
						chat += input.readObject() + "\n";
						view.textarea.setText(chat);
					} else {
						System.out.println("success");
						vc = (Vector<MyShape>) input.readObject();
						System.out.println(vc.size());
						view.frame.repaint();
						
					}

				} catch (IOException | ClassNotFoundException e) {

				}
			}
		}
	}

	class ClientSender extends Thread {
		Socket socket;

		public ClientSender(Socket socket) {
			this.socket = socket;
			try {
				output.writeObject(name);
				System.out.println("그림을 그리자.");
			} catch (Exception e) {

			}
		}

		public void run() {

			while (output != null) {
				try {
					if (message.equals("exit"))
						System.exit(0);
					// output.writeUTF("[" + name + "]" + message);
					output.flush();
				} catch (IOException e) {
				}

				view.textfield.setText("");
			}
		}

	}
}
