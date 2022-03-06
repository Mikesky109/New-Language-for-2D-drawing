package plotgraph;

import java.io.*;
import java.util.*;
import java.awt.*; 
import java.awt.geom.*; 
import javax.swing.*; 
public class demo extends DrawEngine {
	public demo() { 
		super("Group XYZ: Drawing Demo"); 
	}
	public void drawObjects(Graphics2D g2d) {
		double xcur,ycur,xnext,ynext;
		double angle_rad = 0;
		g2d.setColor(Color.RED);
		g2d.drawOval(50,50,450,450);
		g2d.setColor(Color.BLUE);
		xcur=200;
		ycur=200;
		for (int i=0;i<3;i++){
			angle_rad+=2*Math.PI/3;
			xnext=xcur+100*Math.cos(angle_rad);
			ynext=ycur+100*Math.sin(angle_rad);
			g2d.drawLine((int)xcur,(int)ycur,(int)xnext,(int)ynext);
			xcur=xnext;
			ycur=ynext;
		}

}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
		@Override
		public void run() {new demo().setVisible(true);}}
		);
	}
}
