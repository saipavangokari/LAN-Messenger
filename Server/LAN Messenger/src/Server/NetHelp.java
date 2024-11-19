package Server;

import java.net.InetAddress;

public class NetHelp{

   public String ip;
    public NetHelp(){
        
         try{
                 InetAddress myIP = InetAddress.getLocalHost();
                 ip = myIP.getHostAddress();
            }
         catch(Exception ex){
                     System.out.println(ex.getMessage());
            }
}
}