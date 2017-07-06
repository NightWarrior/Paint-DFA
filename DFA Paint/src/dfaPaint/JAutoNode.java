package dfaPaint;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

public class JAutoNode {
	int x, y, r, fontSize;
	String label, acceptedStr, initialStr;
	Boolean accepted, initial;

	JAutoNode(int x, int y, String label) {
		this.x = x;
		this.y = y;
		this.r = 50;
		this.label = label;
		this.acceptedStr = "+";
		this.initialStr = "-";
		this.accepted = false;
		this.initial = false;

		fontSize = 23;
		
		
	}

	public Boolean collides(JAutoNode n){
		Boolean b = (Math.sqrt(Math.pow(Math.abs(x - n.x), 2)  +  Math.pow(Math.abs(y - n.y), 2))<r);
				
		return b;
	}
	public Boolean collides(MouseEvent m){
		Boolean b = (Math.sqrt(Math.pow(Math.abs(x - m.getX()), 2)  +  Math.pow(Math.abs(y - m.getY()), 2))<r);
				
		return b;
	}

	public void paint(Graphics g) {
		g.setFont(new Font("Lucida Console", Font.BOLD, fontSize));

		String s = label;
		if (isAcceptedState())
			s += acceptedStr;
		if (isInitialState())
			s += initialStr;

		g.drawOval(x - r, y - r, r * 2, r * 2);
		g.drawString(s, x - fontSize / 2, y + 5);
	}

	public boolean isInitialState() {
		return initial;
	}

	public boolean isAcceptedState() {
		return accepted;
	}

	public void setAcceptedState(Boolean b) {
		accepted = b;
	}

	public void setInitialState(Boolean b) {
		initial = b;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public void setPos(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

}
