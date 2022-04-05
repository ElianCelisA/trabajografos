package co.com.avanzada.trabajografos.hanselgretel;

import java.util.ArrayList;
import java.util.List;

public class Vertice implements Comparable<Vertice>{
	
	public int id;
	public List<Arista> listaAdj;
	public int distancia;
	public boolean visitado;

	public Vertice(int id) {
		this.id = id;
		listaAdj = new ArrayList<Arista>();
		distancia = Integer.MAX_VALUE;
		visitado = false;
	}

	public int compareTo(Vertice otro) {
		return this.distancia - otro.distancia;
	}
}
