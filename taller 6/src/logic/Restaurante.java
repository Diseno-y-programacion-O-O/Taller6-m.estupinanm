package logic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Restaurante {
	private List<Producto> menu = new ArrayList<>();
	private List<Producto> bebidas = new ArrayList<>();
	private List<Ingrediente> ingredientes = new ArrayList<>();
	private Map<String, Pedido> pedidos = new HashMap<>();
	private Pedido pedido;
	private int numeroPedidos = 0;
	
	public Restaurante(){
		cargarInformacionRestaurante(new File("data/ingredientes.txt"), new File("data/menu.txt"), new File("data/bebidas.txt"), new File("data/combos.txt"));
	}
	
	public void iniciarPedido(String nombreCliente, String direccionCliente) {
		numeroPedidos++;
		pedido = new Pedido(nombreCliente, direccionCliente, numeroPedidos, 0);  // Pasar valor total inicial como 0
	}
	
	public Pedido getPedidoEnCurso() {
		return pedido;
	}
	
	public Pedido getPedidoID(String id) {
		Pedido pedido = null;
		if(pedidos.containsKey(id)) {
			pedido = pedidos.get(id);
		}
		return pedido;
	}
	
	public int cerrarYGuardarPedido() {
		int pedidoSimilar = 0;
		for(Pedido pedidoEnPedidos : pedidos.values()) {
			if(pedido.equals(pedidoEnPedidos)) {
				pedidoSimilar++;
			}
		}
		pedidos.put(pedido.getIdPedido(), pedido);
		pedido.guardarFactura(new File("data/factura.txt"));;
		pedido = null;
		return pedidoSimilar;
	}
	
	public ArrayList<Producto> getMenuBase(){
		return (ArrayList<Producto>) menu;
	}
	
	public ArrayList<Producto> getBebidas(){
		return (ArrayList<Producto>) bebidas;
	}
	
	public ArrayList<Ingrediente> getIngredientes(){
		return (ArrayList<Ingrediente>) ingredientes;
	}
	
	public void cargarInformacionRestaurante(File archivoIngredientes, File archivoMenu, File archivoBebidas, File archivoCombos) {
		try {
			cargarIngredientes(archivoIngredientes);
			cargarMenu(archivoMenu);
			cargarBebidas(archivoBebidas);
			cargarCombos(archivoCombos);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (IngredienteRepetidoException e) {
			System.out.println("Error: Ingrediente repetido - " + e.getMessage());
		} catch (ProductoRepetidoException e) {
			System.out.println("Error: Producto repetido - " + e.getMessage());
		}
	}
	
	
	private void cargarIngredientes(File archivoIngredientes) throws FileNotFoundException, IOException, IngredienteRepetidoException {
		BufferedReader br = new BufferedReader(new FileReader(archivoIngredientes));
		String linea = br.readLine();
		while (linea != null) {
			String[] informacion = linea.split(";");
			String nombre = informacion[0];
			int costoAdicional = Integer.parseInt(informacion[1]);
			int calorias = Integer.parseInt(informacion[2]);
			Ingrediente ingrediente = new Ingrediente(nombre, costoAdicional, calorias);
			
			// Verificar si el ingrediente ya existe en la lista
			for (Ingrediente existente : ingredientes) {
				if (existente.getNombre().equals(ingrediente.getNombre())) {
					throw new IngredienteRepetidoException("Ingrediente repetido: " + ingrediente.getNombre());
				}
			}
			
			ingredientes.add(ingrediente);
			linea = br.readLine();
		}
		br.close();
	}
	
	
	private void cargarMenu(File archivoMenu) throws FileNotFoundException, IOException, ProductoRepetidoException {
		BufferedReader br = new BufferedReader(new FileReader(archivoMenu));
		String linea = br.readLine();
		while (linea != null) {
			String[] informacion = linea.split(";");
			String nombre = informacion[0];
			int precioBase = Integer.parseInt(informacion[1]);
			int calorias = Integer.parseInt(informacion[2]);
			Producto producto = new ProductoMenu(nombre, precioBase, calorias);
			if (menu.contains(producto)) {
				throw new ProductoRepetidoException("Producto repetido: " + producto.getNombre());
			}
			menu.add(producto);
			linea = br.readLine();
		}
		br.close();
	}
	

	private void cargarBebidas(File archivoBebidas) throws FileNotFoundException, IOException {
		BufferedReader br = new BufferedReader(new FileReader(archivoBebidas));
		String linea = br.readLine();
		while (linea != null) {
			String[] informacion = linea.split(";");
			String nombre = informacion[0];
			int precioBase = Integer.parseInt(informacion[1]);
			int calorias = Integer.parseInt(informacion[2]);
			Producto producto = new ProductoMenu(nombre, precioBase, calorias);
			bebidas.add(producto);
			linea = br.readLine();
		}
		br.close();
	}
	
	private void cargarCombos(File archivoCombos) throws FileNotFoundException, IOException {
		BufferedReader br = new BufferedReader(new FileReader(archivoCombos));
		String linea = br.readLine();
		while (linea != null) {
			String[] informacion = linea.split(";");
			String nombre = informacion[0];
			int descuento = Integer.parseInt(informacion[1].substring(0, informacion[1].length() - 1));
			Combo combo = new Combo(nombre, descuento);
			for(int i = 2; i <= 4; i++) {
				combo.agregarItemACombo(getProductoPorNombre(informacion[i]));
			}
			Producto producto = combo;
			menu.add(producto);
			linea = br.readLine();
		}
		br.close();
	}
	
	private Producto getProductoPorNombre(String nombre) {
		Producto producto = null;
		for(Producto productoEnMenu : menu) {
			if(nombre.equals(productoEnMenu.getNombre())) {
				producto = productoEnMenu;
			}
		}
		if(producto == null) {
			for(Producto productoEnBebidas : bebidas) {
				if(nombre.equals(productoEnBebidas.getNombre())) {
					producto = productoEnBebidas;
				}
			}
		}
		return producto;
	}
}
