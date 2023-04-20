package hackAssmbler;

import java.awt.*;

import javax.swing.*;     
public class Gui {
	
	 public static void main(String[] args) {
		 
		 ImageIcon image = new ImageIcon("logo.png");
		 
		 
		 JFrame win = new JFrame();           //creating Window(frame)
		 win.setSize(500, 500);               //set size
		 win.setVisible(true);                //making the window visible
		 
//		 JLabel labelInFile = new JLabel();
//		 labelInFile.setText("choose file");
//		 win.add(labelInFile);
//		 labelInFile.setIcon(image);
		 
		 JButton button = new JButton();
		 win.add(button);
		 
	 }
}    
