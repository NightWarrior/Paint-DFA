package dfaPaint;

import java.util.HashMap;
import java.util.HashSet;


public class JAuto {

	HashSet<String> state;
	HashSet<String> Sigma;
	String initial;
	HashSet<String> accept;

	HashMap<String, String> table;

	JAuto(HashSet<String> state, HashSet<String> Sigma, String initial,HashSet<String> accept, HashMap<String, String> table){
		this.state = state;
		this.Sigma = Sigma;
		this.initial = initial;
		this.accept = accept;
		this.table = table;
	}

	public String delta(String state, String letter) {
		return(table.get(state+"."+letter));
	}

	public String deltaStar(String state, String string) {

		if(string.length()> 0)
			state = delta(deltaStar(state, string.substring(0, string.length()-1)), string.substring(string.length()-1, string.length()));
		return state;
		}



	public boolean isAccepted(String str) {
		return(accept.contains(deltaStar(initial,str))? true:false);
	}

	public void show() {
		System.out.println("States:   "+state);
		System.out.println("Sigma:    "+Sigma);
		System.out.println("Initial:  "+initial);
		System.out.println("Table:    "+table);
		System.out.println("Accepted: "+accept);
		System.out.println();
	}

	public HashSet<String> getStates(){
		return state;
	}
	public HashSet<String> getSigma(){
		return Sigma;
	}
	public HashSet<String> getAccepted(){
		return accept;
	}
	public String getInitial(){
		return initial;
	}
	public HashMap<String,String> getTable(){
		return table;
	}

	public void setStates(HashSet<String> state){
		this.state = state;
	}
	public void setSigma(HashSet<String> Sigma){
		this.Sigma = Sigma;
	}
	public void setInitial(String initial){
		this.initial = initial;
	}
	public void setTable(HashMap<String,String> table){
		this.table= table;
	}
	public void setAccepted(HashSet<String> accept){
		this.accept = accept;
	}
}
