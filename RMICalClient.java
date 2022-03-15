import javax.swing.*;
import java.awt.*;
import java.rmi.*;
import java.awt.event.*;
import java.io.*;
public class RMICalClient extends JFrame implements ActionListener
{
          double n1 = 0.0;
          double  d1;
double n2 = 0.0;
          JButton jb[] = new JButton[21];
          JTextField tf;
          Container con;
          int button,i;
          String str;
          String num="";
          JPanel tp,bp;                           
          public RMICalClient()
{
                   setTitle("calculator");
                   tp = new JPanel();
                   bp = new JPanel();
                   tf = new JTextField(22);
                   tf.setEditable(false);
                   con = getContentPane();
                   bp.setLayout(new GridLayout(5,4));
                   tp.add(tf);
                   con.add(tp,"North");                  
                   for(int i=0;i<10;i++)                 
{
                        jb[i] = new JButton(""+i);
}
                   jb[10] = new JButton("+");
                   jb[11] = new JButton("-");
                   jb[12] = new JButton("*");
                   jb[13] = new JButton("/");
                   jb[14] = new JButton("clear");
                   jb[15] = new JButton(".");
                   jb[16] = new JButton("=");
                   for(int i = 0;i<17;i++)
{
                             jb[i].addActionListener(this);
                             bp.add(jb[i]);
                             }
                   con.add(bp,"Center"); 
                   setDefaultCloseOperation(EXIT_ON_CLOSE);
                   }     
public void actionPerformed(ActionEvent ae)
{ 
String num1=" "; 
str = ae.getActionCommand();             
                   System.out.println(str);
                   for(int i=0;i<10;i++)
{
                          if(ae.getSource()==jb[i])
{
num = num+str;     
                                      tf.setText(num);     
                             }
                   }
                   if((ae.getSource())==jb[15])              
{
num = num+str;                     
                             tf.setText(num);
                             }
                   for(int i=10;i<14;i++)
{
                             if(ae.getSource()==jb[i])            
{
button = i-9;         
                                      if(num!="")             
{
                                      tf.setText("");
                                      n1 = Double.parseDouble(num);    
                                       num="";                           
                                      }
Else:
{
                                                          tf.setText("");
                                                }
}
                             }
                             if(ae.getSource()==jb[16])          
{
                                      if(!(tf.getText().equals("")))
{
                                                tf.setText("");
n2 = Double.parseDouble(num);
                                                num = "";
                                                          try
{
                                                          String url = "rmi://localhost/calserver";
                                                          RMICalIntf a =(RMICalIntf) Naming.lookup(url);
                                                          switch(button)
{
                                                                   case 1:
                                                                                      d1 = a.add(n1,n2);
String s=String.valueOf(d1);
tf.setText(s);
                                                                                      num1=num1+d1;
System.out.println(d1);

                                                                                      break;  
                                                                             case 2:
                                                                                      d1 = a.sub(n1,n2);
                                                                                      break;
                                                                   case 3:
                                                                                      d1 = a.mul(n1,n2);
                                                                                      break;
                                                                   case 4:
                                                                                      d1 = a.div(n1,n2);
                                                                                      break;
                                                                             default:
                                                                                      d1 = 0.0;
                                                                   }
str = String.valueOf(d1);        
                                                          tf.setText(str);   
                                                                   }
catch(Exception e){}
                                                          }
Else:
{
                                      tf.setText("");
                                                }
                             }
                             if(ae.getSource()==jb[14]) 
{
                                                tf.setText("");
                                                num = "";
                                                n1=0.0;
                                                n2=0.0;
                                                button=0;
                                      }
                             }
public static void main(String args[])
{
                   JFrame f = new RMICalClient();
                   f.setSize(200,200);
                   f.setVisible(true);
                   }
}