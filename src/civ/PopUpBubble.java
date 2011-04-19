import javax.swing.*;
import java.awt.*;
import javax.imageio.*;
import java.io.*;

public class PopUpBubble extends JPanel{
	
	private Image i; //Flytta till globalt scope

//Anropas vid avslutad strid, ourloss �r den egna soldatens f�rluster, theirloss �r motst�ndarens f�rluster
//skall ritas ut med v�nster h�rn p� X / Y
	PopUpBubble(int x, int y, int ourloss, int theirloss){
		if (ourloss>theirloss){
			try{
				i = ImageIO.read(new File("data/img/bubbla3.png"));
				}
				catch(IOException e){
					return;
				}
	
		}
		else if(ourloss<theirloss){
			try{
				i = ImageIO.read(new File("data/img/bubbla4.png"));
				}
				catch(IOException e){
					return;
				}

		}
		else{
			try{
				i = ImageIO.read(new File("data/img/bubbla5.png"));
			}
			catch(IOException e){
				return;
			}
		}
		setLayout(null);
		setBounds(x, y-(i.getHeight(null)), i.getWidth(null)+1, i.getHeight(null)+1);//b�r ber�knas efter bilden		
		setOpaque(false);		
			
		JLabel losslabel = new JLabel(""+ourloss);
		Font curFont = losslabel.getFont();
		losslabel.setFont(new Font(curFont.getFontName(), curFont.getStyle(), 24));
		losslabel.setBounds(170, 62, 300, 50);
		add(losslabel);	

		JLabel winlabel = new JLabel(""+theirloss);
		curFont = winlabel.getFont();
		winlabel.setFont(new Font(curFont.getFontName(), curFont.getStyle(), 24));			
		winlabel.setBounds(170, 88, 300, 50);
		add(winlabel);	
		

	}

//Anropas n�r kommando ges till enhet/stad, skall svara med att rita ut simpel feedback med v�nster h�rn(typ) p� X / Y
	//bilden �r 300 x 226 y stor
	PopUpBubble(int x, int y){
		try{
			i = ImageIO.read(new File("data/img/bubbla2.png"));
			}
			catch(IOException e){
				return;
			}
		
		setLayout(null);
		//setBounds(x, y-226, 301, 227);//b�r ber�knas efter bilden
		setBounds(x, y-(i.getHeight(null)), i.getWidth(null), i.getHeight(null));
		setOpaque(false);		
		
		//JLabel feedbacklabel = new JLabel("w/e");
		
		//feedbacklabel.setBounds(100, 100, 300, 50);
		//add(feedbacklabel);
		
	}
	
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(/*pratbubbla h�r*/ i, 0, 0, this);
	}
	
}