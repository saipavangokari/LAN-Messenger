package Server;

import java.awt.*;
import javax.swing.*;
import java.net.*;
import java.util.*;
import java.io.*;

public class Server extends JFrame implements Runnable{

	Socket socket;
	DataInputStream diss;
	DataOutputStream doss;
	public static Vector<DataOutputStream> client = new Vector<DataOutputStream>();
	
	public Server() {
		
		Image img = new ImageIcon(this.getClass().getResource("/logo.png")).getImage();
		Image newimg = img.getScaledInstance(75,75,java.awt.Image.SCALE_SMOOTH);
        ImageIcon im = new ImageIcon(newimg);
		NetHelp nh = new NetHelp();

		this.setResizable(false);
		this.setBounds(100, 100, 600, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(null);
		this.setLocationRelativeTo(null);
		this.setTitle("LAN Messenger - SERVER");
		this.setIconImage(img);
		this.setVisible(true);
		
		JPanel logopanel = new JPanel();
		logopanel.setBounds(10, 10, 566, 100);
		this.getContentPane().add(logopanel);
		logopanel.setLayout(new BorderLayout(0, 0));
		
		JLabel logolabel = new JLabel("LAN Messenger");
		logolabel.setFont(new Font("Arial", Font.BOLD, 20));
		logolabel.setHorizontalAlignment(SwingConstants.CENTER);
		logolabel.setIcon(im);
		logopanel.add(logolabel, BorderLayout.CENTER);
		
		JPanel ippanel = new JPanel();
		ippanel.setBounds(10, 108, 566, 145);
		this.getContentPane().add(ippanel);
		ippanel.setLayout(new BorderLayout(0, 0));
		
		JLabel ip = new JLabel();
		ip.setFont(new Font("Arial", Font.BOLD, 16));
		ip.setHorizontalAlignment(SwingConstants.CENTER);
		ip.setText("IP: "+nh.ip+" Port: 9999");
		ippanel.add(ip, BorderLayout.CENTER);
		
		JLabel serverlabel = new JLabel("SERVER STARTED");
		serverlabel.setFont(new Font("Arial", Font.PLAIN, 14));
		serverlabel.setHorizontalAlignment(SwingConstants.CENTER);
		ippanel.add(serverlabel, BorderLayout.NORTH);
		
		JLabel msglabel = new JLabel("Connect to the Server with the above IP and Port Number to start communication");
		msglabel.setHorizontalAlignment(SwingConstants.CENTER);
		msglabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		ippanel.add(msglabel, BorderLayout.SOUTH);
		
		this.revalidate();
		this.repaint();
	}
	
	public Server(Socket socket) {
		try {
			
			this.socket = socket;
			this.diss = new DataInputStream(socket.getInputStream());
			this.doss = new DataOutputStream(socket.getOutputStream());
			
		} catch (Exception e) {}
	}
	

	public void run() {
		
		try {
			
			DataInputStream dis = new DataInputStream(socket.getInputStream());
			DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
			client.add(dos);
			
			
			while(true) {
				String dataString = dis.readUTF().trim();
				
				System.out.println("Recieved "+dataString);
				for(int i=0; i<client.size();i++) {
					try {
						DataOutputStream cdos = client.get(i);
						if(cdos!=dos) {
													
							cdos.writeUTF(dataString);
						}
					
					} catch (Exception e) {}
					
				}
			}
			
		}
		
		catch (Exception e) {}
	}
	
	
	public static void main(String[] args) throws Exception {
		Server sgui = new Server();
		ServerSocket ss = new ServerSocket(9999);
		
		while(true) {
			Socket socket = ss.accept();
			Server server = new Server(socket);
			Thread thread = new Thread(server);
			thread.start();
		}
	}
}
