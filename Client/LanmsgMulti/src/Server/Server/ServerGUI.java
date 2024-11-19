package Server;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.*;
 


public class ServerGUI extends JFrame implements ActionListener, FocusListener{
        
       //Declaring Objects
        JPanel logopanel;
        JPanel chatpanel;
        JPanel msgpanel;
        JPanel sendpanel;
        JLabel l1;//Title logo label
        JLabel l2;//IP and Port display label  
        JTextArea textArea;
        JTextArea msgArea;
        JScrollPane sta;//Scroll For Chat Area
        JScrollPane smf;//Scroll For Message Area
        JButton sendb;
        
        //Socket objects
        ServerSocket serverSocket;
        Socket socket;
        DataInputStream dis;
        DataOutputStream dos;
        String msgin = "";
        String msgout = "";
        public static String clientip = "";
        static Logger logger = Logger.getLogger("Clientconnectlog");
        static FileHandler fh;  

               
       public ServerGUI() throws IOException{
           
        //Initializing the Objects
        logopanel = new JPanel();
        chatpanel = new JPanel();
        msgpanel = new JPanel();
        sendpanel = new JPanel();
        l1 = new JLabel();//Title logo label
        l2 = new JLabel();//IP and Port display label  
        textArea = new JTextArea();
        msgArea = new JTextArea();
        sta = new JScrollPane(textArea,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);//Applying Scroll to chat text area
        smf = new JScrollPane(msgArea,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);//Applying Scroll to message text area
        sendb = new JButton();
        
        //Logo Import and resize
        ImageIcon imageicon = new ImageIcon("res//logo.png");
        Image image = imageicon.getImage();
        Image newimg = image.getScaledInstance(80,80,java.awt.Image.SCALE_SMOOTH);
        ImageIcon im = new ImageIcon(newimg);
        
        //Creating A Frame and setting attributes
        this.setTitle("LAN Messenger - SERVER");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
        this.setSize(550,520);
        this.setVisible(true);
        this.setResizable(false);
        this.setIconImage(imageicon.getImage());
        this.setLocationRelativeTo(null);//opens frame in the center of the screen
        this.setBackground(new Color(32,32,32));
        
        //logopanel settings
        //logopanel.setBackground(Color.green);
        logopanel.setPreferredSize(new Dimension(540,110));
        logopanel.setLayout(new BoxLayout(logopanel,BoxLayout.Y_AXIS));
        //Logo label settings
        l1.setText("LAN Messenger");
        l1.setIcon(im);
        l1.setFont(new Font("Arial",Font.BOLD,18));
        l1.setAlignmentX(Component.CENTER_ALIGNMENT);
        //IP label Settings
        NetHelp nh = new NetHelp();
        l2.setText("IP: "+nh.ip+" Port: 5597"+" SERVER");
        l2.setFont(new Font("Arial",Font.BOLD,14));
        l2.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        //Chat chatpanel settings
        chatpanel.setPreferredSize(new Dimension(540,250));
        //chatpanel.setBackground(Color.red);
        //Chat textArea(sta) settings
        sta.setPreferredSize(new Dimension(525,240));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false);
        textArea.addFocusListener(this);
        
        //Message msgpanel Settings
        //msgpanel.setBackground(Color.blue);
        msgpanel.setPreferredSize(new Dimension(540,85));
        //Message msgArea(smf) settings
        smf.setPreferredSize(new Dimension(525,75));
        msgArea.setLineWrap(true);
        msgArea.setWrapStyleWord(true);
        //msgArea.setForeground(Color.BLACK);
        msgArea.setFont(new Font("SansSerif",Font.PLAIN,16));
        
        
        //Send sendpanel Setting
        //sendpanel.setBackground(Color.ORANGE);
        sendpanel.setPreferredSize(new Dimension(540,40));
        //Send sendb settings
        sendb.setPreferredSize(new Dimension(70,30));
        sendb.setText("Send");
        sendb.setFocusable(false);
        sendb.addActionListener(this);
        //sendb.addFocusListener(this);
        
        //Panel add
        logopanel.add(l1);
        logopanel.add(l2);
        chatpanel.add(sta);
        msgpanel.add(smf);
        sendpanel.add(sendb);
        
        
        
        //Frame add
        this.add(logopanel);
        this.add(chatpanel);
        this.add(msgpanel);
        this.add(sendpanel);
        
        //To avoid JFrame errors
        this.revalidate();
        this.repaint();

            serverSocket = new ServerSocket(5597);
            while(true){
            socket = serverSocket.accept();
            textArea.append("Connected to Client: "+socket.getInetAddress());
            clientip = "Connected to Client: "+socket.getInetAddress();
              fh = new FileHandler("C://MyLogFile.log"); 
                logger.addHandler(fh);
      SimpleFormatter formatter = new SimpleFormatter();  
      fh.setFormatter(formatter);
      logger.info("hello");
            dis = new DataInputStream(socket.getInputStream());
            dos = new DataOutputStream(socket.getOutputStream());
            
            while(!msgin.equals("exit")){
                msgin = dis.readUTF();
                if(!msgin.isEmpty()){
                textArea.append("\nClient: "+msgin.trim());
                }
            }
        }
            
       }

   @Override
    public void focusGained(FocusEvent e) {
        if(msgArea.getText().equals("")){
            msgArea.setText("Enter Your Message Here");
            msgArea.setForeground(new Color(153,153,153));
            msgArea.setFont(new Font("Consolas",Font.BOLD,20));
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
       if(msgArea.getText().equals("Enter Your Message Here")){
            msgArea.setText("");
            msgArea.setForeground(Color.BLACK);
            msgArea.setFont(new Font("SansSerif",Font.PLAIN,16));
        }
    }

    @Override
    public void actionPerformed(ActionEvent e){
        
        try{
             msgout = msgArea.getText();
             if(!msgout.isEmpty()){
             dos.writeUTF(msgout);
             textArea.append("\nYou: "+msgout.trim());
             }
             msgArea.setText("");
        
        }
        catch(Exception ex){
            System.out.println(ex);
        }
               
        
    }

    public static void main(String args[]) throws IOException
    {
      new ServerGUI();
    
      
    }
}


