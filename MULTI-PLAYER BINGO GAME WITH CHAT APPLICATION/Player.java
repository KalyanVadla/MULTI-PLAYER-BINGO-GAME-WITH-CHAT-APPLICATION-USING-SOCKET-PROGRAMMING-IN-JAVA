import java.io.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.net.*;


class PlayerGUI extends JFrame implements ActionListener 
{

	JFrame f = new JFrame("B I N G O");
	
	/***********************************************************************/
	JPanel pa = new JPanel();
	JPanel pa1 = new JPanel();
	JPanel pa2 = new JPanel();
	JButton create = new JButton("CREATE");
	JButton join = new JButton("JOIN");
	JLabel mydet1 = new JLabel("Developed by");
	JLabel mydet2 = new JLabel("Vadla Kalyan");
	JLabel mydet3 = new JLabel("124003128");
	JLabel mydet4 = new JLabel("Sem 5, CSE");

	
	JLabel userc = new JLabel("PLAYER  NAME");
	JTextField userctf= new JTextField();
	JLabel codec = new JLabel("CODE");
	JTextField codectf = new JTextField();
	JButton bcreate = new JButton("CREATE->");
	JLabel waitc = new JLabel();

	JLabel userj = new JLabel("PLAYER  NAME");
	JTextField userjtf= new JTextField();
	JLabel codej = new JLabel("CODE ( enter your opponent code )");
	JTextField codejtf = new JTextField();
	JButton bjoin = new JButton("JOIN->");
	JLabel errorj = new JLabel();

	
	/*************************************************************************/


	/* ******************************************************************** */
	JPanel pb = new JPanel();
	JPanel pb1 = new JPanel();
	JPanel pb2 = new JPanel();

	JPanel gpan1 = new JPanel();
	JPanel setP = new JPanel();
	JButton setB = new JButton("SET");
	JTextField tf[] = new JTextField[25];
	JLabel note = new JLabel();
	String notes = "Numbers:(1 to 25), with no repetition";
	JTextField poptf = new JTextField();
	JButton pop = new JButton("POP");
	JLabel poplab = new JLabel("Select a number from grid to POP");
	JLabel turn = new JLabel();

	JLabel lc = new JLabel("CHAT HERE");
	JButton bse = new JButton("SEND");
	JTextArea tc1 = new JTextArea();
	JTextArea tc2 = new JTextArea();
	JScrollPane js1 = new JScrollPane(tc1);
	JScrollPane js2 = new JScrollPane(tc2);
	/* ********************************************************************** */


	Socket s;
	PrintWriter pwr;
	BufferedReader br;
	int ack=0;
	int crtflag;
	String pps;
	String name,code;
	
	int row[] = new int[5],cr=0,wr=0;
	int column[] = new int[5],cc=0,wc=0;
	int diagonal[] = new int[2],cd=0,wd=0;
	int r,c;
	UIManager um = new UIManager();
	int setme=0,setop=0;

	PlayerGUI()throws Exception
	{
		
		f.setVisible(true);
		f.setLayout(null);
		f.setSize(1082,690);      
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


		entryPanel();		

		playingPanel();

		
		
		
	
	}

	
	public void entryPanel()
	{
		pa.setSize(1075,655);
		pa.setLayout(null);
		pa.setVisible(true);
		pa.setBackground(new Color(0,153,0));

		create.setBounds(100,30,100,50);
		create.addActionListener(this);
		create.setFont(new Font("Areal",Font.BOLD,15));
		create.setForeground(Color.orange);
		pa.add(create);

		join.setBounds(250,30,100,50);
		join.addActionListener(this);
		join.setFont(new Font("Areal",Font.BOLD,15));
		join.setForeground(Color.orange);
		pa.add(join);

		mydet1.setBounds(850,480,150,30);
		mydet1.setForeground(Color.blue);
		mydet1.setFont(new Font("Times New Roman",Font.BOLD,18));
		pa.add(mydet1);
		
		mydet2.setBounds(850,520,150,25);
		mydet2.setForeground(Color.blue);
		mydet2.setFont(new Font("Times New Roman",Font.BOLD,18));
		pa.add(mydet2);
		
		mydet3.setBounds(850,548,150,25);
		mydet3.setForeground(Color.blue);
		mydet3.setFont(new Font("Times New Roman",Font.BOLD,18));
		pa.add(mydet3);
		
		mydet4.setBounds(850,576,150,25);
		mydet4.setForeground(Color.blue);
		mydet4.setFont(new Font("Times New Roman",Font.BOLD,18));
		pa.add(mydet4);
		
		/******************************************************************/
		pa1.setBounds(70,120,650,470);
		pa1.setLayout(null);
		pa1.setVisible(false);
		pa1.setBackground(Color.white);
		
			
			userc.setBounds(20,30,150,30);
			userc.setFont(new Font("Areal",Font.BOLD | Font.ITALIC,20));
			pa1.add(userc);
			
			userctf.setBounds(20,80,400,30);
			userctf.setForeground(Color.blue);
			userctf.setFont(new Font("Areal",Font.BOLD,20));
			pa1.add(userctf);

			codec.setBounds(20,160,400,30);
			codec.setFont(new Font("Areal",Font.BOLD | Font.ITALIC,20));
			pa1.add(codec);
			
			codectf.setBounds(20,210,400,30);
			codectf.setForeground(Color.blue);
			codectf.setFont(new Font("Areal",Font.BOLD,20));
			pa1.add(codectf);

			bcreate.setBounds(100,290,200,30);
			bcreate.setForeground(Color.black);
			bcreate.addActionListener(this);
			bcreate.setFont(new Font("Areal",Font.BOLD,20));
			pa1.add(bcreate);

			waitc.setBounds(20,350,500,30);
			waitc.setForeground(Color.blue);
			waitc.setFont(new Font("Areal",Font.BOLD | Font.ITALIC,20));
			pa1.add(waitc);
			waitc.setText("Share details and Wait for opponent....");
		
		pa.add(pa1);

		/**********************************************************************************/
		pa2.setBounds(70,120,650,470);
		pa2.setLayout(null);
		pa2.setVisible(false);
		pa2.setBackground(Color.white);
			
			userj.setBounds(20,30,150,30);
			userj.setFont(new Font("Areal",Font.BOLD | Font.ITALIC,20));
			pa2.add(userj);
			
			userjtf.setBounds(20,80,400,30);
			userjtf.setForeground(Color.blue);
			userjtf.setFont(new Font("Areal",Font.BOLD,20));
			pa2.add(userjtf);

			codej.setBounds(20,160,400,30);
			codej.setFont(new Font("Areal",Font.BOLD | Font.ITALIC,20));
			pa2.add(codej);
			
			codejtf.setBounds(20,210,400,30);
			codejtf.setForeground(Color.blue);
			codejtf.setFont(new Font("Areal",Font.BOLD,20));
			pa2.add(codejtf);

			bjoin.setBounds(100,290,200,30);
			bjoin.setForeground(Color.black);
			bjoin.addActionListener(this);
			bjoin.setFont(new Font("Areal",Font.BOLD,20));
			pa2.add(bjoin);
		
			errorj.setBounds(20,350,500,30);
			errorj.setForeground(Color.red);
			errorj.setFont(new Font("Areal",Font.BOLD | Font.ITALIC,20));
			pa2.add(errorj);

		/************************************************************************/
		pa.add(pa2);
		
		f.add(pa);
	}
	


	public void playingPanel()
	{
		
		pb.setSize(1075,655);
		pb.setLayout(null);
		pb.setVisible(false);
		pb.setBackground(Color.green);

		/* ******************************************************************* */

		pb1.setBounds(2,2,668,646);
		pb1.setLayout(null);
		pb1.setBackground(Color.cyan);

			gpan1.setBounds(167,167,334,334);
			gpan1.setBackground(Color.orange);
			gpan1.setBorder(BorderFactory.createLineBorder(Color.orange,6));
			gpan1.setLayout(new GridLayout(5,5,4,4));
				for(int i=0;i<25;i++)
				{
					tf[i] = new JTextField();
					tf[i].setHorizontalAlignment(JTextField.CENTER);
					tf[i].setBackground(Color.white);
					tf[i].setForeground(Color.black);
					tf[i].setFont(new Font("Areal",Font.BOLD,20));
					gpan1.add(tf[i]);
				}
			pb1.add(gpan1);
			
			setP.setBounds(521,315,100,40);
			setP.setLayout(null);
			setP.add(setB);
			setB.setBounds(0,0,100,40);
			setB.setForeground(Color.blue);
			setB.setFont(new Font("Areal",Font.BOLD,17));
			setB.addActionListener(this);
			setP.setVisible(true);
			pb1.add(setP);
			
			note.setBounds(167,115,350,40);
			note.setForeground(Color.red);
			note.setFont(new Font("Areal",Font.BOLD | Font.ITALIC,17));
			pb1.add(note);

			poplab.setBounds(167,510,350,40);
			poplab.setForeground(Color.red);
			poplab.setBackground(Color.white);
			poplab.setFont(new Font("Areal",Font.BOLD | Font.ITALIC,17));
			pb1.add(poplab);

			turn.setBounds(70,565,70,40);
			turn.setFont(new Font("Areal",Font.BOLD | Font.ITALIC,17));
			turn.setText("Wait");
			turn.setForeground(Color.red);
			pb1.add(turn);
			
			poptf.setBounds(167,565,203,40);
			poptf.setForeground(Color.black);
			poptf.setBackground(Color.white);
			poptf.setFont(new Font("Areal",Font.BOLD,20));
			poptf.setHorizontalAlignment(JTextField.CENTER);
			poptf.setEditable(false);
			pb1.add(poptf);

			pop.setBounds(381,565,120,40);
			pop.setForeground(Color.blue);
			pop.setFont(new Font("Areal",Font.BOLD,17));
			pop.addActionListener(this);
			pop.setEnabled(false);
			pb1.add(pop);

		pb.add(pb1);

		/* *********************************************************** */
	
		/* *********************************************************** */

		pb2.setBounds(672,2,396,646);
		pb2.setLayout(null);
		pb2.setBackground(Color.orange);

			lc.setBounds(116,2,160,30);
			lc.setFont(new Font("Areal",Font.BOLD | Font.ITALIC,20));
			lc.setForeground(Color.blue);
			pb2.add(lc);
			
			tc1.setFont(new Font("Lucida Console",Font.BOLD,15));
			tc1.setEditable(false);
			tc1.setLineWrap(true);
			tc1.setBackground(Color.white);
			js1.setBounds(2,34,392,548);
			pb2.add(js1);
			
			tc2.setFont(new Font("Lucida Console",Font.BOLD,18));
			tc2.setLineWrap(true);
			js2.setBounds(2,584,262,60);
			pb2.add(js2);

			bse.setBounds(266,584,120,60);
			bse.addActionListener(this);
			bse.setFont(new Font("Areal",Font.BOLD,20));
			bse.setForeground(Color.blue);
			pb2.add(bse);

		pb.add(pb2);

		/****************************************************************/

		f.add(pb);
		
	}


	public void listenMsg()
	{	
		new Thread(new Runnable()
		{
			@Override
			public void run()
			{
				String sms;
				
				try{
				while(true)
				{
					int i=1;
					while(!(sms=br.readLine()).equals("NULL"))
					{
						if(i==1)
						{
							if(sms.charAt(0)=='_')
							{
								i++;
								tc1.append("\n"+sms+"\n");
							}
							else if(sms.equals("$Win"))
							{
								um.put("OptionPane.messageForeground",Color.red);
								JOptionPane.showConfirmDialog(f,"YOU LOST","BINGO",JOptionPane.DEFAULT_OPTION);
								pa.setVisible(true);
								pb.setVisible(false);
								Defaulties();
								s.close();
							}
							else if(sms.equals("$Set"))
							{
								setop=1;
								if(pps.equals("create"))
								{
									if(setme==1)
									{
										turn.setText("---play---");
										turn.setForeground(Color.blue);
										pop.setEnabled(true);
										poptf.setEditable(true);
									}
									else
									{
										turn.setText("set grid");
										turn.setForeground(Color.red);
										pop.setEnabled(false);
										poptf.setEditable(false);
									}
								}
								else if(pps.equals("join"))
								{
									
									if(setme==1)
									{
										turn.setText("---wait---");
										turn.setForeground(Color.red);
										pop.setEnabled(false);
										poptf.setEditable(false);
									}
									else
									{
										turn.setText("set grid");
										turn.setForeground(Color.red);
										pop.setEnabled(false);
										poptf.setEditable(false);
									}
									
								}
							}
							else
							{
								searchGrid(sms);
								int winR = popFun();
								if(winR>=5)
								{
									pwr.println("$Win\nNULL");
									pwr.flush();
									um.put("OptionPane.messageForeground",Color.blue);
							JOptionPane.showConfirmDialog(f,"BINGO!!!\nYOU WON","BINGO",JOptionPane.DEFAULT_OPTION);
									pa.setVisible(true);
									pb.setVisible(false);
									Defaulties();
									try{
									s.close();}catch(Exception e){}
								}
								else
								{
									poptf.setEditable(true);
									pop.setEnabled(true);
									turn.setText("---play---");
									turn.setForeground(Color.blue);
								}
							}
						}
						else
							tc1.append(""+sms+"\n");
					}
				}}catch(Exception e){}
			}
		}).start();
		
	}

	public int popFun()
	{
		wr=0;wc=0;wd=0;
		cr=0;cc=0;cd=0;
		
		for(int i=0;i<25;i++)
		{
			if(!tf[i].getText().equals("X"))
			{
				r = i/5;
				c = i%5;
				insertInRow(r);
				insertInColumn(c);
				if(r==c)
					insertInDiagonal(0);
				if(r-c==4 || r-c==-4)
					insertInDiagonal(1);
			}	
		}
		
		for(int j=0;j<5;j++)
		{
			if(!findInArray(row,j,cr))
				wr++;
			if(!findInArray(column,j,cc))
				wc++;
			if(j<2)
				if(!findInArray(diagonal,j,cd))
					wd++;
		}
		return wd+wr+wc;

	}
	public boolean findInArray(int arr[],int element,int count)
	{
		for(int i=0;i<count;i++)
		{
			if(arr[i]==element)
				return true;
		}
		return false;
	}
	public void insertInRow(int insNum)
	{
		for(int i=0;i<cr;i++)
		{
			if(row[i]==insNum)
				return;
		}
		row[cr]=insNum;
		cr++;
	}
	public void insertInColumn(int insNum)
	{
		for(int i=0;i<cc;i++)
		{
			if(column[i]==insNum)
				return;
		}
		column[cc]=insNum;
		cc++;
	}
	public void insertInDiagonal(int insNum)
	{
		for(int i=0;i<cd;i++)
		{
			if(diagonal[i]==insNum)
				return;
		}
		diagonal[cd]=insNum;
		cd++;
	}

	public void wrongNum(int i)
	{
		tf[i].setForeground(Color.red);
		tf[i].setText("X");
		crtflag=0;
	}


	public boolean findDot(String sd,int i)
	{
		for(int j=0;j<sd.length();j++)
		{
			if(sd.charAt(j)=='.')
			{
				return true;
			}
		}
		return false;
	}


	public boolean findSpace(String sd,int i)
	{
		for(int j=0;j<sd.length();j++)
		{
			if(sd.charAt(j)==' ')
			{
				return true;
			}
		}
		return false;
	}


	public boolean repetition(String stf,int i)
	{
		for(int j=0;j<i;j++)
		{
			if(tf[j].getText().equals(stf))
				return true;
		}
		return false;
	}
	
	public boolean searchGrid(String popNumS)
	{
		for(int i=0;i<25;i++)
		{
			if(tf[i].getText().equalsIgnoreCase(popNumS))
			{
				tf[i].setText("X");
				tf[i].setForeground(Color.blue);
				return	true;
			}
		}
		return false;
	}

	public void Defaulties()
	{
		for(int i=0;i<25;i++)
		{
			tf[i].setEditable(true);
			tf[i].setText("");
			tf[i].setForeground(Color.black);
		}
		setP.setVisible(true);
		turn.setText("wait");
		turn.setForeground(Color.red);
		pop.setEnabled(true);
		poptf.setEditable(false);
		tc1.setText("");
		tc2.setText("");
	}
	public void actionPerformed(ActionEvent ae)
	{
		String bb = ae.getActionCommand();

		if(bb.equals("SEND"))
		{
			String Smsg;
			if(!(Smsg=tc2.getText()).equals(""))
			{
				try{
				pwr.println("___[   "+name+"   ]___\n"+Smsg+"\nNULL");
				pwr.flush();
				tc2.setText("");
				}catch(Exception e){}

				tc1.append("\n_____ME_____\n"+Smsg+"\n");				
			}
		}


		if(bb.equals("CREATE"))
		{
			pa2.setVisible(false);
			pa1.setVisible(true);
		}

		if(bb.equals("JOIN"))
		{
			pa1.setVisible(false);
			pa2.setVisible(true);
		}
		
		if(bb.equals("SET"))
		{
			crtflag=1;
			for(int i=0;i<25;i++)
			{
				String stf=tf[i].getText();
				if( !stf.equals(""))
				{
					if(repetition(stf,i))
						wrongNum(i);
					else if(findSpace(stf,i))
						wrongNum(i);
					else if(findDot(stf,i))
						wrongNum(i);
					else if(stf.charAt(0)>='1' && stf.charAt(0)<='9')
					{
						int x = Integer.valueOf(stf);
						if(x<1||x>25)
							wrongNum(i);
						else
						{
							tf[i].setForeground(Color.black);
						}
					}
					else
						wrongNum(i);
				}
				else
					wrongNum(i);
			}
			if(crtflag==1)
			{
				for(int i=0;i<25;i++)
				{
					tf[i].setEditable(false); 
				}
				setP.setVisible(false);
				setme=1;
				pwr.println("$Set\nNULL");
				pwr.flush();
				if(pps.equals("create"))
				{
					if(setop==1)
					{
						turn.setText("---play---");
						turn.setForeground(Color.blue);
						pop.setEnabled(true);
						poptf.setEditable(true);
					}
					else
					{
						turn.setText("wait");
						turn.setForeground(Color.red);
						pop.setEnabled(false);
						poptf.setEditable(false);
					}
				}
				if(pps.equals("join"))
				{
					if(setop==1)
					{
						turn.setText("---wait---");
						turn.setForeground(Color.red);
						pop.setEnabled(false);
						poptf.setEditable(false);
					}
					else
					{
						turn.setText("wait");
						turn.setForeground(Color.red);
						pop.setEnabled(false);
						poptf.setEditable(false);
					}
				}
			}
		}

		if(bb.equals("POP"))
		{
			String pops = poptf.getText();
			if(!pops.equals("X"))
			{
				if(searchGrid(pops))
				{
					poptf.setText("");
					poptf.setForeground(Color.black);
					poptf.setEditable(false);
					pop.setEnabled(false);
					int winR = popFun();
					if(winR<5)
					{
						pwr.println(pops+"\nNULL");
						pwr.flush();
					}
					else
					{
						pwr.println("$Win\nNULL");
						pwr.flush();
						um.put("OptionPane.messageForeground",Color.blue);
						JOptionPane.showConfirmDialog(f,"BINGO!!!\nYOU WON","BINGO",JOptionPane.DEFAULT_OPTION);
						pa.setVisible(true);
						pb.setVisible(false);
						Defaulties();
						try{s.close();}catch(Exception e){}
					}
					turn.setText("---wait---");
					turn.setForeground(Color.red);
				}
				else
				{
					poptf.setText("X");
					poptf.setForeground(Color.red);
				}	
			}
			
		}

		if(bb.equals("CREATE->"))
		{
			try
			{
				s =  new Socket("localhost",9122);
				
				br = new BufferedReader(new InputStreamReader(s.getInputStream()));
				pwr = new PrintWriter(s.getOutputStream());
				
				pps = "create";
				create.setEnabled(false);
				join.setEnabled(false);
				userctf.setEditable(false);
				codectf.setEditable(false);
				bcreate.setEnabled(false); 

				String sn = userctf.getText();
				String sc = codectf.getText();
				name=sn;
				code=sc;

				pwr.println("create->");
				pwr.flush();
				pwr.println(sn);
				pwr.flush();
				pwr.println(sc);
				pwr.flush();
				
				ack = Integer.valueOf(br.readLine());
				if(ack==1)
				{
					turn.setText("wait");
					turn.setForeground(Color.red);
					pa.setVisible(false);
					pb.setVisible(true);
					note.setText(notes);
					create.setEnabled(true);
					join.setEnabled(true);
					userctf.setEditable(true);
					codectf.setEditable(true);
					bcreate.setEnabled(true);
					userctf.setText("");
					codectf.setText("");
					listenMsg();
				}		
			}
			catch(Exception e)
			{	}
		}


		if(bb.equals("JOIN->"))
		{
			try
			{
				s =  new Socket("localhost",9122);
				
				br = new BufferedReader(new InputStreamReader(s.getInputStream()));
				pwr = new PrintWriter(s.getOutputStream());
				
				pps="join";
				create.setEnabled(false);
				join.setEnabled(false);
				userjtf.setEditable(false);
				codejtf.setEditable(false);

				String sn = userjtf.getText();
				String sc = codejtf.getText();
				name=sn;
				code=sc;

				pwr.println("join->");
				pwr.flush();
				pwr.println(sn);
				pwr.flush();
				pwr.println(sc);
				pwr.flush();
				
				ack = Integer.valueOf(br.readLine());
				
				if(ack==1)
				{
					turn.setText("wait");
					turn.setForeground(Color.red);
					pa.setVisible(false);
					pb.setVisible(true);
					note.setText(notes);
					create.setEnabled(true);
					join.setEnabled(true);
					userjtf.setEditable(true);
					codejtf.setEditable(true);
					bjoin.setEnabled(true);
					userjtf.setText("");
					codejtf.setText("");
					errorj.setText("");
					listenMsg();
				}
				else
				{
					create.setEnabled(true);
					join.setEnabled(true);
					userjtf.setEditable(true);
					codejtf.setEditable(true);
					bjoin.setEnabled(true);
					codejtf.setText("");
					errorj.setText("No room exists");
					s.close();
				}	
				
			}
			catch(Exception e)
			{	}
		}




	}

}


class Player
{
	public static void main(String args[])throws Exception
	{
		PlayerGUI obj = new PlayerGUI();
	}
}