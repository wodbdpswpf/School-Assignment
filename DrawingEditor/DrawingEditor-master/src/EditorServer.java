import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

public class EditorServer {

	private HashMap<String, ObjectOutputStream> clients;
	private ServerSocket serverSocket;

	public static void main(String[] args) {

		new EditorServer().start();
	}

	public EditorServer() {
		clients = new HashMap<String, ObjectOutputStream>();

		// 여러 스레드에서 접근할 것이므로 동기화
		Collections.synchronizedMap(clients);
	}

	public void start() {
		try {
			Socket socket;

			// 리스너 소켓 생성
			serverSocket = new ServerSocket(7777);
			System.out.println("서버가 시작되었습니다.");

			// 클라이언트와 연결되면
			while (true) {
				// 통신 소켓을 생성하고 스레드 생성(소켓은 1:1로만 연결된다)
				socket = serverSocket.accept();
				ServerReceiver receiver = new ServerReceiver(socket);
				receiver.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	class ServerReceiver extends Thread {
		Socket socket;
		ObjectInputStream input;
		ObjectOutputStream output;

		public ServerReceiver(Socket socket) {
			this.socket = socket;
			try {
				input = new ObjectInputStream(socket.getInputStream());
				output = new ObjectOutputStream(socket.getOutputStream());

			} catch (IOException e) {

			}
		}

		public void run() {
			String name = "";
			try {
				System.out.println("DFSDFSADFSDFA");
				name = input.readObject().toString();
				sendToAll("#" + name + "[" + socket.getInetAddress() + ":" + socket.getPort() + "]" + "enter");

				clients.put(name, output);
				System.out.println("#" + name + "[" + socket.getInetAddress() + ":" + socket.getPort() + "]" + "enter");

				System.out.println(clients.size() + "exist");

				while (input != null) {
					sendToAll(input.readObject());
				}
			
			} catch (IOException | ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {

				clients.remove(name);
				sendToAll("#" + name + "[" + socket.getInetAddress() + ":" + socket.getPort() + "]" + "Exit");

				System.out.println("#" + name + "[" + socket.getInetAddress() + ":" + socket.getPort() + "]" + "Exit");

				System.out.println(clients.size() + "exist");
			}
		}

		public void sendToAll(Object obj) {
			Iterator<String> it = clients.keySet().iterator();
			while (it.hasNext()) {
				try {
					ObjectOutputStream dos = clients.get(it.next());
					dos.writeObject(obj);
				} catch (Exception e) {

				}
			}
		}

	}

}
