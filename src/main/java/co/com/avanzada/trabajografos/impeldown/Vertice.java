package co.com.avanzada.trabajografos.impeldown;

import java.util.ArrayList;
import java.util.List;


public class Vertice {
	
	List<Arista> adj;

	public Vertice() {
		adj = new ArrayList<Arista>();
	}
	public Vertice(ArrayList<Arista> k) {
		adj = k;
	}
	public void add(Arista e) {
		adj.add(e);
	}
}
