package dfaPaint;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JAutoPainter {
	ArrayList<JAutoNode> nodes;
	ArrayList<JAutoEdge> edges;

	public JAutoPainter(JAuto dfa) { // get elements of the dfa and make
										// respective nodes and edges of them.
		Random rand = new Random();
		nodes = new ArrayList<JAutoNode>();
		edges = new ArrayList<JAutoEdge>();

		ArrayList<String> states = new ArrayList<String>(dfa.getStates());
		ArrayList<String> accepted = new ArrayList<String>(dfa.getAccepted());
		@SuppressWarnings("unused")
		ArrayList<String> sigma = new ArrayList<String>(dfa.getSigma());
		List<String> tableKeys = new ArrayList<String>(dfa.getTable().keySet());
		List<String> tableValues = new ArrayList<String>(dfa.getTable().values());
		String initial = dfa.getInitial();

		// System.out.println("states: " + states);
		// System.out.println("accepted: " + accepted);
		// System.out.println("sigma: " + sigma);
		// System.out.println("tableKeys: " + tableKeys);
		// System.out.println("tableValues: " + tableValues);
		// System.out.println("initial: " + initial);

		for (String n : states) {
			nodes.add(new JAutoNode(50 + rand.nextInt(700), 50 + rand
					.nextInt(500), n));
		}


		for (String n : accepted) { // marking final states
			for (JAutoNode node : nodes)
				if (n.equals(node.getLabel()))
					node.setAcceptedState(true);
		}

		for (JAutoNode node : nodes) { // marking initial state
			if (initial.equals(node.getLabel()))
				node.setInitialState(true);
		}

		// try {
		for (int i = 0; i < tableKeys.size(); i++) { // making edges between
														// appropriate nodes
			Pattern p = Pattern.compile("[a-zA-Z]+[0-9]+");
			Matcher m = p.matcher(tableKeys.get(i));
			m.find();
			// System.out.println(m.start()+" "+m.end()+"
			// "+tableKeys.get(i).substring(m.start(), m.end()));
			for (JAutoNode n1 : nodes) {
				for (JAutoNode n2 : nodes) {
					if (tableKeys.get(i).substring(m.start(), m.end())
							.equals(n1.getLabel())) {
						if (tableValues.get(i).equals(n2.getLabel())) {
							Boolean exists = false;
							for (JAutoEdge e : edges) {
								if(e.exists(n1, n2)){
									exists=true;
									e.addTransition(tableKeys.get(i).substring(m.end(), tableKeys.get(i).length()));
								}
									
							}
							if (!exists) {
								edges.add(new JAutoEdge(n1, n2, tableKeys
										.get(i).substring(m.end(),
												tableKeys.get(i).length())));
							}

						}
					}

				}
			}
		}
		// } catch (Exception e) {
		// e.printStackTrace();
		// }

	}

	public String checkMouseCollision(MouseEvent m) {
		for (JAutoNode n : nodes) {
			if (n.collides(m))
				return n.getLabel();
		}
		return null;
	}

	public void drag(MouseEvent m, String nodeLabel) {
		for (JAutoNode n : nodes) {
			if (n.getLabel().equals(nodeLabel)) {
				n.setPos(m.getX(), m.getY());
				for (JAutoEdge e : edges) {
					e.refresh();
				}
			}
		}
	}

	public void paint(Graphics g) { // iterate through all nodes and edges and
									// call paint
		// function
		for (JAutoNode n : nodes) {
			n.paint(g);
		}
		for (JAutoEdge e : edges) {
			e.paint(g);
		}
	}

}
