import java.awt.Color;
import java.awt.Component;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


import javax.swing.JFrame;

public class MyMouseAdapter extends MouseAdapter {
	
	Color newColor = null;
	Color lastColor = null;
	SweeperBehavior sweeper = new SweeperBehavior();
	
	public void mousePressed(MouseEvent e) {
		switch (e.getButton()) {
		case 1: // Left mouse button
			Component c = e.getComponent();
			while (!(c instanceof JFrame)) {
				c = c.getParent();
				if (c == null) {
					return;
				}
			}
			JFrame myFrame = (JFrame) c;
			MyPanel myPanel = (MyPanel) myFrame.getContentPane().getComponent(0);
			Insets myInsets = myFrame.getInsets();
			int x1 = myInsets.left;
			int y1 = myInsets.top;
			e.translatePoint(-x1, -y1);
			int x = e.getX();
			int y = e.getY();
			myPanel.x = x;
			myPanel.y = y;
			myPanel.mouseDownGridX = myPanel.getGridX(x, y);
			myPanel.mouseDownGridY = myPanel.getGridY(x, y);
			myPanel.repaint();
			break;
		case 3: // Right mouse button
			c = e.getComponent();
			while (!(c instanceof JFrame)) {
				c = c.getParent();
				if (c == null) {
					return;
				}
			}

			myFrame = (JFrame) c;
			myPanel = (MyPanel) myFrame.getContentPane().getComponent(0);
			myInsets = myFrame.getInsets();

			x1 = myInsets.left;
			y1 = myInsets.top;
			e.translatePoint(-x1, -y1);

			x = e.getX();
			y = e.getY();
			myPanel.x = x;
			myPanel.y = y;
			myPanel.mouseDownGridX = myPanel.getGridX(x, y);
			myPanel.mouseDownGridY = myPanel.getGridY(x, y);
			myPanel.repaint();
			break;

		default: // Some other button (2 = Middle mouse button, etc.)
			// Do nothing
			break;
		}
	
	}

	public void mouseReleased(MouseEvent e) {
		switch (e.getButton()) {
		case 1: // Left mouse button
			Component c = e.getComponent();
			while (!(c instanceof JFrame)) {
				c = c.getParent();
				if (c == null) {
					return;
				}
			}
			JFrame myFrame = (JFrame) c;
			MyPanel myPanel = (MyPanel) myFrame.getContentPane().getComponent(0); // Can also loop among components to find MyPanel
																					
			Insets myInsets = myFrame.getInsets();
			int x1 = myInsets.left;
			int y1 = myInsets.top;
			e.translatePoint(-x1, -y1);
			int x = e.getX();
			int y = e.getY();
			myPanel.x = x;
			myPanel.y = y;
			int gridX = myPanel.getGridX(x, y);
			int gridY = myPanel.getGridY(x, y);
			if ((myPanel.mouseDownGridX == -1) || (myPanel.mouseDownGridY == -1)) {
				// Had pressed outside
				// Do nothing
			} else {
				if ((gridX == -1) || (gridY == -1)) {
					// Is releasing outside
					// Do nothing
				} else {
					if(sweeper.hasMine(myPanel.mouseDownGridX, myPanel.mouseDownGridY) && (sweeper.hasFlag(myPanel.mouseDownGridX,myPanel.mouseDownGridY)==false)){
						SweeperBehavior.gameOver(myPanel.mouseDownGridX,myPanel.mouseDownGridY);
						myPanel.repaint();
						SweeperBehavior.displayLosingStatement();
				
				} else {
					if(sweeper.hasFlag(myPanel.mouseDownGridX,myPanel.mouseDownGridY)==false){
					sweeper.chainReaction(myPanel.mouseDownGridX, myPanel.mouseDownGridY);
					
						} else {
							//do nothing on flagged cell
					}
				}
			}	
		
	}
			myPanel.repaint();
			//sweeper.winnerwinnerchickendinner
			break;

		case 3: // Right mouse button
			c = e.getComponent();
			while (!(c instanceof JFrame)) {
			
				c = c.getParent();
				if (c == null) {
				
					return;
				}
			}

			myFrame = (JFrame)c;
			myPanel = (MyPanel) myFrame.getContentPane().getComponent(0);  //Can also loop among components to find MyPanel
		
			x = e.getX();
			y = e.getY();
		
			
			if ((myPanel.mouseDownGridX == -1) && (myPanel.mouseDownGridY == -1)){
				//clicking outside grid				
				} else {
					if(sweeper.hasFlag(myPanel.mouseDownGridX,myPanel.mouseDownGridY)==false){
						sweeper.flagCell(myPanel.mouseDownGridX, myPanel.mouseDownGridY);
					} else {
							sweeper.unFlagCell(myPanel.mouseDownGridX, myPanel.mouseDownGridY);
							
					}
				 myPanel.repaint();
			}
			sweeper.gameWon();
		break;
		
		default: // Some other button (2 = Middle mouse button, etc.)
			// Do nothing
			break;
		}
	}
}
