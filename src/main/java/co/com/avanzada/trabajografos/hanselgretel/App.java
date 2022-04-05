package co.com.avanzada.trabajografos.hanselgretel;

import static guru.nidi.graphviz.model.Factory.graph;
import static guru.nidi.graphviz.model.Factory.node;
import static guru.nidi.graphviz.model.Factory.to;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

import guru.nidi.graphviz.attribute.Label;
import guru.nidi.graphviz.attribute.Style;
import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.Graph;
import ij.IJ;
import ij.ImagePlus;

public class App {

	public static void main(String[] args) throws IOException {
		while (true) {
			program();
		}
	}
	
	public static void program() throws IOException {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		Graph g = graph("hanselGretel").directed()
				.linkAttr().with("class", "link-class");
		int origen, destino;
		ArrayList<Vertice> vertices;

			int numVertices = sc.nextInt();
			int numEdges = sc.nextInt();

			if (numEdges == 0) {
				imprimirSalida(false, 0, null);
			}

			vertices = new ArrayList<Vertice>(numVertices + 1);

			for(int i = 0; i < numVertices; i++) {
				vertices.add(new Vertice(i));
			}
			
			for (int i = 0; i < numEdges; i++) {
				int u, v, w;
				u = sc.nextInt();
				v = sc.nextInt();
				w = sc.nextInt();
				vertices.get(u).listaAdj.add(new Arista(v, w));
			}
			
			origen = sc.nextInt();
			destino = sc.nextInt();

			PriorityQueue<Vertice> queue = new PriorityQueue<Vertice>();
			vertices.get(origen).distancia = 0;
			queue.add(vertices.get(origen));

			Vertice u, v;
			while (!queue.isEmpty()){
				u = queue.poll();
				u.visitado = true;
				if (u.id == destino) break;
				for (Arista e: u.listaAdj) {
					v = vertices.get(e.v);
					if (u.distancia + e.w < v.distancia) {
						v.distancia = u.distancia + e.w;
						e.drawn = true;
						queue.add(v);
					}
				}
			}
			for (Vertice vertex: vertices) {
				for (Arista edge: vertex.listaAdj) {
					if (edge.w > vertices.get(destino).distancia) {
						g = g.with(node(Integer.toString(vertex.id)).link(
								to(node(Integer.toString(edge.v)))
								.with(Label.of(Integer.toString(edge.w)))
						));
						continue;
					}
					if (edge.drawn) {
						g = g.with(node(Integer.toString(vertex.id)).link(
								to(node(Integer.toString(edge.v)))
								.with(Style.BOLD, Label.of(Integer.toString(edge.w)))
						));
					} else {
						g = g.with(node(Integer.toString(vertex.id)).link(
								to(node(Integer.toString(edge.v)))
								.with(Label.of(Integer.toString(edge.w)))
						));
					}
				}
			}
			imprimirSalida(vertices.get(destino).visitado, vertices.get(destino).distancia, g);
	}
	
	private static void imprimirSalida(boolean esAlcanzable, int dist, Graph g) throws IOException {
		if (esAlcanzable) {
			System.out.println("La distancia es de "+dist+" para llegar a casa. :)");
			try {
				Graphviz.fromGraph(g).width(900).render(Format.PNG).toFile(new File("src/resources/grafico_grafo_HG.png"));
				System.out.println("Grafico generado");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			System.out.println("Hansel y Gretel han muerto. :(");
			Imagen i = new Imagen();
			i.abrirImagen();
		}
	}
}
