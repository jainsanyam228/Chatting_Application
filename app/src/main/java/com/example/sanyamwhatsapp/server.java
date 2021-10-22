import java.io.*;
import java.net.*;
class server{
     public static void main(String[] args){
         
        ServerSocket s1  = new ServerSocket(1302);
        Socket s = s1.accept();
        DataInputStream din = new DataInputStream(s.getInputStream());
        DataOutputStream dout = new DataOutputStream(s.getOutputStream());
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String msgin="" , msgout="";
        while(!msgin.equals("end")){
            msgin = din.readUTF();
            System.out.println(msgin); // printing client message
            msgout = br.readLine();
            dout.writeUTF(msgout);
            dout.flush();
        }
        s.close();
    }
}