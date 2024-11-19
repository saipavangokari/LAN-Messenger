package Client;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;


public class ServerIP extends JFrame implements ActionListener,KeyListener,Runnable{
    
        JPanel msgPanel,ipPanel,connectPanel,namePanel;
        JLabel msgLabel, ipLabel, portLabel,nameLabel;
        JTextField ipField, portField, nameField;
        JButton connectButton;
       
        public String ip = "";
        String port = "";
        String name = "";
        public int portn;
        
        public void ServerGUI(){
            
            ImageIcon imageicon = new ImageIcon("res//logo.png");
            Image image = imageicon.getImage();
            //Image newimg = image.getScaledInstance(80,80,java.awt.Image.SCALE_SMOOTH);
            //ImageIcon im = new ImageIcon(newimg);
            
            msgPanel = new JPanel();
            namePanel = new JPanel();
            ipPanel = new JPanel();
            connectPanel = new JPanel();
            msgLabel = new JLabel();
            ipLabel = new JLabel();
            nameLabel = new JLabel();
            portLabel = new JLabel();
            ipField = new JTextField();
            portField = new JTextField();
            connectButton = new JButton();
            nameField = new JTextField();
            
            this.setTitle("LAN Messenger - WELCOME CLIENT");
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
            this.setSize(550,230);
            this.setVisible(true);
            this.setResizable(false);
            this.setIconImage(imageicon.getImage());
            this.setLocationRelativeTo(null);
            
            //Message Panel Settings
            //msgPanel.setBackground(Color.red);
            msgPanel.setPreferredSize(new Dimension(540,50));
            msgPanel.setLayout(new BorderLayout());
            //Message Label Settings
            msgLabel.setText("Enter the Your Name, IP and Port number of the server to connect");
            msgLabel.setFont(new Font("Arial",Font.BOLD, 14));
            msgLabel.setVerticalAlignment(JLabel.CENTER);
            msgLabel.setHorizontalAlignment(JLabel.CENTER);
            
            namePanel.setPreferredSize(new Dimension(530,50));
            namePanel.setLayout(new FlowLayout(FlowLayout.CENTER,5,0));
            //namePanel.setBackground(Color.BLUE);
            nameLabel.setText("Name:");
            nameLabel.setFont(new Font("Arial",Font.BOLD, 14));
            nameField.setPreferredSize(new Dimension(340,30));
            nameField.setFont(new Font("Arial",Font.BOLD, 14));
           
            //IP Panel Settings
           // ipPanel.setBackground(Color.RED);
            ipPanel.setPreferredSize(new Dimension(530,45));   
            ipPanel.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
            ipLabel.setText("IP: ");
            ipLabel.setFont(new Font("Arial",Font.BOLD, 14));
            ipField.setPreferredSize(new Dimension(160,30));
            ipField.setFont(new Font("Arial",Font.BOLD, 14)); 
            ipField.addKeyListener(this);
            portLabel.setText("   Port: ");
            portLabel.setFont(new Font("Arial",Font.BOLD, 14));
            portField.setPreferredSize(new Dimension(160,30));
            portField.setFont(new Font("Arial",Font.BOLD, 14));
            portField.addKeyListener(this);
           
            //Connect Panel Settings
            connectPanel.setPreferredSize(new Dimension(530,40));
            //connectPanel.setBackground(Color.BLUE);
            connectButton.setText("Connect");
            connectButton.setPreferredSize(new Dimension(85,30));
            connectButton.setFocusable(false);
            connectButton.addActionListener(this);
            
            //Panel add
            
            msgPanel.add(msgLabel);
            namePanel.add(nameLabel);
            namePanel.add(nameField);
            ipPanel.add(ipLabel);
            ipPanel.add(ipField);
            ipPanel.add(portLabel);
            ipPanel.add(portField);
            
            
            connectPanel.add(connectButton);
            
            //Frame add
            this.add(msgPanel);
            this.add(namePanel);
            this.add(ipPanel);
            this.add(connectPanel);
            
            //To avoid Jframe errors
            this.revalidate();
            this.repaint();
        }
 
        public String getIP(){
            return ip;
        }
        public int getPort(){
            return portn;
        }
    @Override
    public void run() {
       ClientGUI ob = new ClientGUI();
                   
            ob.ipno=ip;
            ob.portno=portn;
            ob.myname=name;
            try {
                ob.gui();
                ob.setVisible(true);
            } catch (IOException ex) {}
            
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==connectButton && !(ipField.getText().trim().isEmpty()) && !(portField.getText().trim().isEmpty()) && !(nameField.getText().trim().isEmpty())){
           
            ip = ipField.getText().trim();
            port= portField.getText().trim();
            name=nameField.getText().trim();
            portn = Integer.parseInt(port);  
            Thread t1 = new Thread(this);
            t1.start();
            dispose();

        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        char c = e.getKeyChar();
        if(!(Character.isDigit(c)||c==KeyEvent.VK_BACK_SPACE|| c==KeyEvent.VK_PERIOD || c==KeyEvent.VK_DELETE)){
            e.consume();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {}
    @Override
    public void keyReleased(KeyEvent e) {}
    
    public static void main(String[] args) {
        ServerIP sip = new ServerIP();
        sip.ServerGUI();
    }    
}