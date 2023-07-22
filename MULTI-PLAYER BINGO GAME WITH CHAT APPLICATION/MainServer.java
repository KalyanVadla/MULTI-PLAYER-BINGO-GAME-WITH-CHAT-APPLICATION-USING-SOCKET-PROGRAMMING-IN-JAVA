import java.io.*;
import java.util.*;
import java.net.*;

/* PlayerThread objects are considered as players */ 
class PlayerThread implements Runnable
{
	Socket s;
	public static ArrayList<PlayerThread> lst = new ArrayList<PlayerThread>();   // lst : list in which all players details are stored
	BufferedReader br;  
	PrintWriter pw;
	String username;   //---------- player user name
	String code;  	 //------------ room code 
	String purpose; // if player created game purpose=create else purpose=join

	public PlayerThread(Socket s)
	{
		try
		{
			this.s = s;
			this.br = new BufferedReader(new InputStreamReader(s.getInputStream()));  
			this.pw = new PrintWriter(s.getOutputStream());
			
			/* receiving the details from socket and storing in list(lst) */
			this.purpose = this.br.readLine();
			this.username = this.br.readLine();
			this.code = this.br.readLine();
			lst.add(this);
		}	
		catch(Exception e)
		{
			removeSocket(s);
		}
	}

	/* player with purpose "join" search for room with the code */
	public void searchForC()
	{
		int flag=0;
		try{
			for(PlayerThread pt : lst)
			{
				if(pt.purpose.equals("create->") && pt.code.equals(code))    
					{
						pw.println("1");        // if room exists , value 1 will be sent to player so that he can join 
						pw.flush();
						pt.pw.println("1");
						pt.pw.flush();
						flag=1;
					}		
			}
			if(flag==0)
			{
				pw.println("0");   // if room doesn't exists, value 0 will be sent to player, he can't join 
				pw.flush();
				removeSocket(s);
			}
		}catch(Exception e){removeSocket(s);}
			
	}
	
	@Override
	public void run()
	{
		String msg;
		if(purpose.equals("join->"))
		{
			searchForC();
		}

		while(s.isConnected())
		{
			try
			{
				msg = br.readLine();   // receives data from player 
				sendMsg(msg);  // sends data to other player
			}
			catch(Exception e)
			{
				removeSocket(s);
				break;
			}
		}
	}

	public void sendMsg(String msg)
	{
		for(PlayerThread pt : lst)
		{
			if(pt.code.equals(code) && !pt.username.equals(username))   // iterate through list and sends the data 
			{
				try
				{
					pt.pw.println(msg); // sending the data
					pt.pw.flush();
				}
				catch(Exception e)
				{
					removeSocket(s);
				}
			}
		}
	}
	
	/* if any exceptional errors occurs this method is called to close the connection */
	public void removeSocket(Socket s)
	{
		try
		{
			lst.remove(this);  // removing the socket from list
			s.close();
		}
		catch(Exception e)
		{
		}
	}
}

/* Main Server class starts -----> */
public class MainServer
{
	ServerSocket ssk;   //------serversocket object
	public MainServer(ServerSocket ssk) 
	{
		this.ssk=ssk;
	}
	public void startServer()
	{
		System.out.println("\nServer started running ................\n");
		int playerCount=0;
		try
		{
			while(!ssk.isClosed())
			{
				Socket s = ssk.accept();	//waiting for a player to connect
				System.out.println("A new Client has connected "+(playerCount+1));

				
				PlayerThread ct = new PlayerThread(s)	; // creating PlayerThread object		
				Thread t = new Thread(ct);
				t.start(); // thread starts running
			}
		}
		catch(Exception e)
		{}
	}


	public void closeServer()
	{
		try
		{
			if(ssk!=null)
			{
				ssk.close();
			}	
		}
		catch(Exception e)
		{}
	}


	public static void main(String args[])
	{
		try{
		ServerSocket ss = new ServerSocket(9122);
		MainServer obj = new MainServer(ss);
		obj.startServer();
		obj.closeServer();}catch(Exception e){}
	}

}