package co.com.avanzada.trabajografos.impeldown;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;


public class Bfs {
	int N;
	Vertice[] G; // Actual graph

	public Bfs() {
		N = 0;
		G = null;
	}
	
	public Bfs(Vertice[] G1, int N1) {
		this.G = G1;
		this.N = N1;
	}
	
	

	ArrayList <Integer> bfs(int s,int dot) {
		ArrayList<Integer> Camino= new ArrayList<Integer>();
		int[] dist = new int[N];
		boolean[] visited = new boolean[N];
		int[] p = new int[N];
		Queue< Integer> Q = new LinkedList< Integer>();
		Arrays.fill(dist, -1);
		Arrays.fill(p, -1);
		visited[s] = true;
		dist[s] = 0;
		Q.offer(s);
		while (!Q.isEmpty()) {
			int u = Q.poll();
			for (Arista e : G[u].adj) {
				int v = e.to;
				if (!visited[v]) {
					visited[v] = true;
					dist[v] = dist[u] + 1;
					p[v] = u;
					Q.offer(v);
				}
			}
		}
		for(int i=dot;p[i]!=-1;i=p[i]) {
			Camino.add(i);
		}
			Camino.add(s);
		return Camino;
	}
	 public static void main(String args[]) {
        //Scanner linea = new Scanner(System.in);
		 Vertice[] G = new Vertice[4]; //Al crear el grafo especificamos que es de 4 pociciones

		 Arista k = new Arista(1, 1);
		 Arista k1 = new Arista(2, 1);
		 Arista k2 = new Arista(3, 1);
        ArrayList<Arista> Nodo1=new ArrayList();
        Nodo1.add(k);
        Nodo1.add(k1);
        Nodo1.add(k2);
        Arista k3 = new Arista(0, 1);
        Arista k4 = new Arista(2, 1);
        ArrayList<Arista> Nodo2=new ArrayList();
        Nodo2.add(k3);
        Nodo2.add(k4);
        Arista k6 = new Arista(0, 1);
        Arista k7 = new Arista(1, 1);
        ArrayList<Arista> Nodo3=new ArrayList();
        Nodo3.add(k6);
        Nodo3.add(k7);
        Arista k5 = new Arista(0, 1);
        ArrayList<Arista> Nodo4=new ArrayList();
        Nodo4.add(k5);
        G[0]=new Vertice(Nodo1);
        G[1]=new Vertice(Nodo2);
        G[2]=new Vertice(Nodo3);
        G[3]=new Vertice(Nodo4);
        Bfs l=new Bfs(G,4);
       // System.out.println(Arrays.toString(l.bfs(2)));
    }
}
