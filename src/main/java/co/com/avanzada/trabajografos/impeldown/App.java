package co.com.avanzada.trabajografos.impeldown;

import static guru.nidi.graphviz.model.Factory.graph;
import static guru.nidi.graphviz.model.Factory.node;
import static guru.nidi.graphviz.model.Factory.to;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.Graph;

public class App {
	
	public static ArrayList<String> ciudades = new ArrayList<String>();
	public static Graph g = graph("ImpelDown").directed()
			.linkAttr().with("class", "link-class");

	public App() {}
	
	public static void main(String[] args) {

		
		Scanner input = new Scanner(System.in);
		int n =input.nextInt();
		ArrayList<Arista> Nodo1=new ArrayList();
		input.nextLine();
		Vertice[] G = new Vertice[n];

		String conYCons =input.nextLine();
		String[] partes = conYCons.split(" ");
		int dato1=(int)Double.parseDouble(partes[0]);//Conexiones
		int dato2=(int)Double.parseDouble(partes[1]);//Consultas
		String [] conexiones = new String[dato1];
		String [] consultas = new String[dato2];
		for (int i = 0; i <dato1;i++) {
			conexiones[i] = input.nextLine();
			String[] separacion = conexiones[i].split(" ");

			// Listado ciudades sin repetirse
			if(!ciudades.contains(separacion[0])) {
				ciudades.add(separacion[0]);
			}
			if(!ciudades.contains(separacion[1])) {
				ciudades.add(separacion[1]);
			}
		}
		Arrays.sort(conexiones);
		
		

		for(int i=0;i<dato2;i++) {
			String info=input.nextLine();
			consultas[i]=info;
		}
		
		for (String string : conexiones) {
			String[] separacion=string.split(" ");

			int fromCiudad=hallarNumCiudad(separacion[0]);
			int toCiudad=hallarNumCiudad(separacion[1]);

			Arista k=new Arista(toCiudad,1);
			Vertice v=G[fromCiudad];
			if(v==null) {
				v=new Vertice();
			}
			v.add(k);
			G[fromCiudad]=v;

			toCiudad=hallarNumCiudad(separacion[0]);
			fromCiudad=hallarNumCiudad(separacion[1]);

			k=new Arista(toCiudad,1);
			v=G[fromCiudad];
			if(v==null) {
				v=new Vertice();
			}
			v.add(k);
			G[fromCiudad]=v;
		}


		Bfs l = new Bfs(G,n);
		for (int j=0;j<dato2;j++) {

			String[] separacion=consultas[j].split(" ");
			int pos=hallarNumCiudad(separacion[0]);
			int meta=hallarNumCiudad(separacion[1]);
			ArrayList<Integer> recorrido = l.bfs(pos,meta);
			for(int i=recorrido.size();i>0;i--) {
				System.out.print(ciudades.get(recorrido.get(i-1))+" ");
			}
			System.out.println();
		}
		drawGraph(conexiones, ciudades);
	}
	
	public static void drawGraph(String[] conexiones, 
			ArrayList<String> ciudades) {
		for (int i = 0; i < conexiones.length; i++) {
			String[] separacion = conexiones[i].split(" ");
			boolean flag = false;
			
			g = g.with(node(separacion[0]).link(
					to(node(separacion[1]))
			));
		}
		try {
			Graphviz.fromGraph(g).width(900).render(Format.PNG).toFile(new File("src/resources/grafico_grafo_ID.png"));
			System.out.println("Grafico generado");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static int hallarNumCiudad(String ciudad) {

		for(int i=0;i<ciudades.size();i++) {
			if(ciudades.get(i).equals(ciudad)) {
				return i;
			}
		}
		return -1;
	}

}
