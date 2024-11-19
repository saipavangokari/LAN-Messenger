package Client;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;


public class ClientGUI extends JFrame implements ActionListener, FocusListener{

       //Declaring andObjects
       JPanel logopanel;//= new JPanel();//logopanel
       JPanel chatpanel;//chatpanel
       JPanel msgpanel;//Message Panel
       JPanel sendpanel;//Message Panel
       JLabel l1;//Title logo label
       JLabel l2;//IP and Port display label  
       JTextArea textArea;//Chat text area
       JTextArea msgArea;//Message Area
       JScrollPane sta;// Scroll For Chat Area
       JScrollPane smf;// Scroll For Message Area
       JButton sendb;
       
       
       Socket socket;
       DataInputStream dis;
       DataOutputStream dos;
       String msgin = "";
       String msgout = "";
       public String myname = "";
       public String ipno = "";
       public int portno = 0;

        public void gui() throws IOException{
        //Initializing the Objects
        logopanel = new JPanel();//logopanel
        chatpanel = new JPanel();
        msgpanel = new JPanel();
        sendpanel = new JPanel();
        l1 = new JLabel();//Title logo label
        l2 = new JLabel();//IP and Port display label  
        textArea = new JTextArea();//Chat text area
        msgArea = new JTextArea();//Message Text Area
        sta = new JScrollPane(textArea,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);//Applying Scroll to chat text area
        smf = new JScrollPane(msgArea,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);//Applying Scroll to message text area
        sendb = new JButton();
        
        //Logo Import and resize
        ImageIcon imageicon = new ImageIcon("res//logo.png");
        Image image = imageicon.getImage();
        Image newimg = image.getScaledInstance(80,80,java.awt.Image.SCALE_SMOOTH);
        ImageIcon im = new ImageIcon(newimg);
        
       // ip = JOptionPane.showInputDialog("Please Enter server IP");
        
        //Creating A Frame and setting attributes
        if(!ipno.isEmpty()){
        this.setTitle("LAN Messenger - CLIENT - " + myname);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
        this.setSize(550,520);
        this.setVisible(true);
        this.setResizable(false);
        this.setIconImage(imageicon.getImage());
        this.setLocationRelativeTo(null);
        }
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
        //l2.setText("Not Connected to any server !!");
       
        l2.setText("Connected to IP:"+ipno+" Port: "+portno+" CLIENT");
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
        msgArea.setForeground(Color.BLACK);
        msgArea.setFont(new Font("SansSerif",Font.PLAIN,16));
        
        
        //Send sendpanel Setting
        //sendpanel.setBackground(Color.ORANGE);
        sendpanel.setPreferredSize(new Dimension(540,40));
        //Send sendb settings
        sendb.setPreferredSize(new Dimension(70,30));
        sendb.setText("Send");
        sendb.setFocusable(false);
        sendb.addActionListener(this);
        
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
        
        socket = new Socket(ipno,portno);
        dis = new DataInputStream(socket.getInputStream());
        dos = new DataOutputStream(socket.getOutputStream());
        
        while(!msgin.equals("exit")){
            msgin = dis.readUTF();
            if(!msgin.isEmpty()){
            textArea.append("\n"+msgin.trim());
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
    public void actionPerformed(ActionEvent e) {
           
        try{
             msgout = msgArea.getText();
             if(!msgout.isEmpty()){
             dos.writeUTF(myname+": "+msgout);
             textArea.append("\nYou: "+msgout.trim());
             }
             msgArea.setText("");
        
        }
        catch(Exception ex){
            System.out.println(ex);
        }
    }
}
    

 
