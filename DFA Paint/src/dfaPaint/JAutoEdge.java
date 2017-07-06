package dfaPaint;

import java.awt.*;
import java.awt.geom.*;

public class JAutoEdge {
	int x1, x2, y1, y2, cx, cy, lx, ly, r, fontSize;
	String label;
	double angle1, angle2;
	QuadCurve2D.Double curve;
	JAutoNode n1, n2;

	public JAutoEdge(JAutoNode n1, JAutoNode n2, String label) {
		this.n1 = n1;
		this.n2 = n2;
		x1 = n1.getX();
		x2 = n2.getX();
		y1 = n1.getY();
		y2 = n2.getY();
		this.label = label;
		r = 50;
		fontSize = 20;

		angle1 = Math.atan2(y2 - y1, x2 - x1);
		angle2 = Math.atan2(y1 - y2, x1 - x2);

		x1 += r * Math.cos(angle1);
		y1 += r * Math.sin(angle1);
		x2 += r * Math.cos(angle2);
		y2 += r * Math.sin(angle2);

		if (n1 == n2) {
			x2 += (int) (r * Math.cos(angle2 - Math.toRadians(90 + 30)));
			y2 += (int) (r * Math.sin(angle2 - Math.toRadians(90 + 30)));
		}

		if (n1 != n2) {
			cx = (x1 + x2) / 2 + (int) (25 * Math.cos(angle2 - Math.toRadians(90)));
			cy = (y1 + y2) / 2 + (int) (25 * Math.sin(angle2 - Math.toRadians(90)));
			lx = (x1 + x2) / 2 + (int) (20 * Math.cos(angle2 - Math.toRadians(90)));
			ly = (y1 + y2) / 2 + (int) (20 * Math.sin(angle2 - Math.toRadians(90)));
		} else {
			cx = (x1 + x2) / 2 + (int) (70 * Math.cos(angle2 - Math.toRadians(90-45)));
			cy = (y1 + y2) / 2 + (int) (70 * Math.sin(angle2 - Math.toRadians(90-45)));
			lx = (x1 + x2) / 2 + (int) (40 * Math.cos(angle2 - Math.toRadians(90-45)));
			ly = (y1 + y2) / 2 + (int) (40 * Math.sin(angle2 - Math.toRadians(90-45)));
		}

		curve = new QuadCurve2D.Double(x1, y1, cx, cy, x2, y2);
		// Math.toRadians(90+45)

	}

	public void refresh() {
		x1 = n1.getX();
		x2 = n2.getX();
		y1 = n1.getY();
		y2 = n2.getY();

		angle1 = Math.atan2(y2 - y1, x2 - x1);
		angle2 = Math.atan2(y1 - y2, x1 - x2);

		x1 += r * Math.cos(angle1);
		y1 += r * Math.sin(angle1);
		x2 += r * Math.cos(angle2);
		y2 += r * Math.sin(angle2);
		
		
		if (n1 == n2) {
			x2 += (int) (r * Math.cos(angle2 - Math.toRadians(90 + 30)));
			y2 += (int) (r * Math.sin(angle2 - Math.toRadians(90 + 30)));
		}

		if (n1 != n2) {
			cx = (x1 + x2) / 2 + (int) (25 * Math.cos(angle2 - Math.toRadians(90)));
			cy = (y1 + y2) / 2 + (int) (25 * Math.sin(angle2 - Math.toRadians(90)));
			lx = (x1 + x2) / 2 + (int) (20 * Math.cos(angle2 - Math.toRadians(90)));
			ly = (y1 + y2) / 2 + (int) (20 * Math.sin(angle2 - Math.toRadians(90)));
		} else {
			cx = (x1 + x2) / 2 + (int) (70 * Math.cos(angle2 - Math.toRadians(90-45)));
			cy = (y1 + y2) / 2 + (int) (70 * Math.sin(angle2 - Math.toRadians(90-45)));
			lx = (x1 + x2) / 2 + (int) (40 * Math.cos(angle2 - Math.toRadians(90-45)));
			ly = (y1 + y2) / 2 + (int) (40 * Math.sin(angle2 - Math.toRadians(90-45)));
		}

		curve = new QuadCurve2D.Double(x1, y1, cx, cy, x2, y2);

	}

	public void paint(Graphics g) {
		// g.drawLine(x1, y1, x2, y2);
		((Graphics2D) g).draw(curve);
		g.drawLine(x2, y2, x2 - (int) (20 * Math.cos(angle2 - Math.toRadians(90 + 60))),
				y2 - (int) (20 * Math.sin(angle2 - Math.toRadians(90 + 60))));
		g.drawLine(x2, y2, x2 - (int) (20 * Math.cos(angle2 - Math.toRadians(90 + 60 + 90))),
				y2 - (int) (20 * Math.sin(angle2 - Math.toRadians(90 + 60 + 90))));
		g.setFont(new Font("Lucida Console", Font.BOLD, fontSize));
		g.drawString(label, lx, ly);
		// g.drawString(label,
		// (x1+x2)/2-(int)(20*Math.cos(angle2-Math.toRadians(90+45))),
		// (y1+y2)/2-(int)(20*Math.sin(angle2-Math.toRadians(90+45))));
		// System.out.println(x1 + " " + y1 + " " + x2 + " " + y2);
	}

	public void setLine(int x1, int y1, int x2, int y2) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}
	
	public Boolean exists(JAutoNode n1, JAutoNode n2){
		if(this.n1 == n1 && this.n2 == n2){
			return true;
		}
		return false;
	}
	
	public void addTransition(String label){
		this.label+=",";
		this.label+=label;
		
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

}
