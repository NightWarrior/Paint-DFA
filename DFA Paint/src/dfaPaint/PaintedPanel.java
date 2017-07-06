package dfaPaint;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;
import javax.swing.Timer;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;

@SuppressWarnings("serial")
public class PaintedPanel extends JPanel implements MouseListener {
//	HashSet<String> state;
//	HashSet<String> Sigma;
//	String initial;
//	HashSet<String> accept;
//	HashMap<String, String> table;
	Boolean drag;
	MouseEvent mt;
	String dragNode;

	JAutoPainter jap;

	Timer t;

	public PaintedPanel(JAuto dfa) {
		super();
		setBackground(Color.WHITE);
//		state = dfa.getStates();
//		Sigma = dfa.getSigma();
//		initial = dfa.getInitial();
//		accept = dfa.getAccepted();
//		table = dfa.getTable();
		drag = false;

		setBounds(new Rectangle(400, 100, 800, 600));

		t = new Timer(16, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				repaint();

			}
		});

		this.addMouseListener(this);
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("DFA Painter");
		lblNewLabel.setBounds(334, 5, 131, 27);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 22));
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Drag nodes to reposition them");
		lblNewLabel_1.setBounds(601, 541, 175, 14);
		add(lblNewLabel_1);
		jap = new JAutoPainter(dfa);

	}

	@Override
	public void paintComponent(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, 800, 600);
		g.setColor(Color.BLACK);
		// g.fillOval(100-25, 100-25, 50, 50);
		// g.drawRect(100-100, 100-100, 200, 200); // Subtracting half of the
		// total width or the whole radius from the x,y coordinates, it draws
		// with center mode
		// JAutoNode n = new JAutoNode(400, 300, "q0");
		// n.setAcceptedState(true);
		// JAutoNode n1 = new JAutoNode(400, 150, "q1");
		// n1.setInitialState(true);
		// JAutoNode n2 = new JAutoNode(250, 450, "q2");
		// JAutoNode n3 = new JAutoNode(550, 450, "q3");
		// JAutoEdge e = new JAutoEdge(n1, n, "a");
		// JAutoEdge e2 = new JAutoEdge(n2, n, "b");
		// JAutoEdge e3 = new JAutoEdge(n3, n, "b");
		// n.paint(g);
		// n1.paint(g);
		// n2.paint(g);
		// n3.paint(g);
		// e.paint(g);
		// e2.paint(g);
		// e3.paint(g);

		jap.paint(g);

		if (mt != null && dragNode != null) {
//			System.out.println("hit");
			jap.drag(mt, dragNode);
		}

		t.start();
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent m) {
//		System.out.println(m.getY());

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent m) {
		// System.out.println(m.getY());
		mt = m;
		if ((dragNode = jap.checkMouseCollision(mt)) != null) {
			drag = true;
		}

	}

	@Override
	public void mouseReleased(MouseEvent m) {
		mt = m;
		drag = false;
		// TODO Auto-generated method stub

	}
}
