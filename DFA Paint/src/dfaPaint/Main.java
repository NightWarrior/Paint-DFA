package dfaPaint;

import java.awt.EventQueue;
import java.util.HashMap;
import java.util.HashSet;

public class Main{

	public static void main(String[] args)  {
		JAuto dfa;
		HashSet<String> state =new HashSet<String>();
		HashSet<String> Sigma =new HashSet<String>();
		HashSet<String> acc =new HashSet<String>();
		HashMap<String, String> map =new HashMap<String, String>();
		String init = new String("q0");
		state.add("q0");state.add("q1");state.add("q2");state.add("q3");
		Sigma.add("a");Sigma.add("b");
		acc.add("q2");acc.add("q1");
		map.put("q0a", "q1");
		map.put("q0b", "q0");
		map.put("q1a", "q2");
		map.put("q1b", "q0");
		map.put("q2a", "q2");
		map.put("q2b", "q1");
		map.put("q3a", "q0");
		map.put("q3b", "q0");
		
	
		dfa = new JAuto(state, Sigma, init, acc, map);
//		dfa.show();
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DFAPainter frame = new DFAPainter(dfa);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
//		Pattern p = Pattern.compile("[a-zA-Z]+[0-9]+");
//		Matcher m = p.matcher("ab0a");
//		if(m.find()){
//			System.out.println("Found: "+m.start()+" "+m.end());
//		}
		

	}

}
