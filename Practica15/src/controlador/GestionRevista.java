package controlador;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

import modelo.revista.Articulo;
import modelo.revista.Autor;
import modelo.revista.Revista;

public class GestionRevista {
	private List<Revista> revistas;
	private List<Articulo> articulos;
	private List<Autor> autores;
    private  int c,c2;
    
	protected String pathRevistas = "src/archivos/Revistas.txt";
	private String pathArticulos = "src/archivos/Articulos.txt";
	private String pathAutores = "src/archivos/Autores.txt";

	public GestionRevista() {
		revistas = new ArrayList<Revista>();
		articulos = new ArrayList<Articulo>();
		autores = new ArrayList<Autor>();
	}
	public void agregarRevista(String nombreR, String editorial, int codigo) throws IOException {
	
			
			Revista re = new Revista();
			re.setNombre(nombreR);
			re.setEditorial(editorial);
			re.setCodigo(codigo);
		try {
			
			  FileOutputStream file =  new FileOutputStream (pathRevistas);
			  ObjectOutputStream escritura = new ObjectOutputStream (file);
			  escritura.writeObject(re);
			  escritura.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<Revista> leerRevista() throws IOException{
		FileInputStream archivoLectura=null;
		ObjectInputStream entrada=null;
		try{
		    archivoLectura=new FileInputStream(pathRevistas);
		    entrada = new ObjectInputStream(archivoLectura);
		    Revista revista = (Revista)entrada.readObject();
		    revistas.add(revista);
		}catch(Exception e){
		    e.printStackTrace();
		}finally{
		    entrada.close();
		}

		return revistas;
	}
	

	public void agregarArticulo(String tema, String pagina, int codigo, int codigoR) {
		c=codigoR;
		
		Articulo ar = new Articulo();
		ar.setCodigo(codigo);
		ar.setTema(tema);
		ar.setPagina(pagina);
		   for(Revista r:revistas){
			   if(revistas.get(0).getCodigo()==codigoR){
				   ar.setRevistas(revistas.get(0));
			   }
		   }
		
		try {  
			  FileOutputStream file =  new FileOutputStream (pathArticulos);
			  ObjectOutputStream escritura = new ObjectOutputStream (file);
			  escritura.writeObject(ar);
			  escritura.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public List<Articulo> leerArticulo() throws IOException{
		FileInputStream archivoLectura=null;
		ObjectInputStream entrada=null;
		try{
		    archivoLectura=new FileInputStream(pathArticulos);
		    entrada = new ObjectInputStream(archivoLectura);

		    Articulo articulo = (Articulo)entrada.readObject();
		    articulos.add(articulo);
		}catch(Exception e){
		    e.printStackTrace();
		}finally{
		    entrada.close();
		}

		return articulos;
	}

	

	public void agregarAutor(String nombre, String apellido, String nacionalidad, int codigoAr) {
		
		Autor au = new Autor();
		au.setNombre(nombre);
		au.setNacionalidad(nacionalidad);
		au.setApellido(apellido);
		   for(Articulo ar:articulos){
			   if(articulos.get(0).getCodigo()==codigoAr){
				   au.setArticulo(articulos.get(0));
				  
			   }
		   }
		try {
			  FileOutputStream file =  new FileOutputStream (pathAutores);
			  ObjectOutputStream escritura = new ObjectOutputStream (file);
			  escritura.writeObject(au);
			  escritura.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<Autor> leerAutor() throws IOException{
		FileInputStream archivoLectura=null;
		ObjectInputStream entrada=null;
		try{
		    archivoLectura=new FileInputStream(pathAutores);
		    entrada = new ObjectInputStream(archivoLectura);

		    Autor autor = (Autor)entrada.readObject();
		    autores.add(autor);
		}catch(Exception e){
		    e.printStackTrace();
		}finally{
		    entrada.close();
		}

		return autores;
	}
	

	// validar autor
	public boolean validarAutor(String nombre, String apellido, String nacionalidad) throws Exception {
		int n = 1;
		if (autores.size() > 0) {
			for (int i = 0; i < autores.size(); i++) {
				if (nombre.equals(autores.get(i).getNombre()) && apellido.equals(autores.get(i).getApellido())
						&& nacionalidad.equals(autores.get(i).getNacionalidad())) {
					n++;
				}
			}
			if (n > 1) {
				throw new Exception("El autor ya se encuentra inscrito");
			}
		}
		return true;
	}

	// metodo de validar articulo
	public boolean validarArticulo(String nombre, int codigo) throws Exception {
		int n = 1;
		if (articulos.size() > 0) {
			for (Articulo ar : articulos) {
				if (ar.getTema().equals(nombre) && ar.getCodigo() == codigo) {
					n++;
				}
			}
			if (n > 1) {
				throw new Exception("El articulo ya se encuentra inscrito");
			}
		}
		return true;
	}

	// metodo para validar la revista
	public boolean validarRevista(String nombre, String editorial, int codigo) throws Exception {
		int n = 1;
		if (revistas.size() > 0) {
			for (Revista r : revistas) {
				if (r.getNombre().equals(nombre) && r.getEditorial().equals(editorial) && codigo == r.getCodigo()) {
					n++;
				}
			}
			if (n > 1) {
				throw new Exception("La revista ya se encuentra inscrita");
			}
		}
		return true;
	}

	// validar codigo articulo
	public boolean validarCodigoArticulo(int codigo) throws Exception {
		int n = 1;
		if (articulos.size() > 0) {
			for (Articulo ar : articulos) {
				if (ar.getCodigo() == codigo) {
					n++;
				}
			}
			if (n == 1) {
				throw new Exception("El Articulo no existe");
			}
		}
		return true;
	}

	// validar codigo revista
	public boolean validarCodigoRevista(int codigo) throws Exception {
		int n = 1;
		if (revistas.size() > 0) {
			for (Revista re : revistas) {
				if (re.getCodigo() == codigo) {
					n++;
				}
			}
			if (n == 1) {
				throw new Exception("La revista no existe");
			}
		}
		return true;
	}
	// metodo para validar los espacion en blanco

	public boolean validarEspacio(String nombre, String nombre2, String nombre3, String nombre4, String nombre5)
			throws Exception {
		try {

		} catch (Exception e) {
			throw new Exception("Formato incorrecto, contiene caracteres");
		}
		if (nombre.equals("") || nombre2.equals("") || nombre3.equals("") || nombre4.equals("") || nombre5.equals("")) {
			throw new Exception("ERROR UN COMPONENTE SE ENCUENTRA VACIO");
		}
		return true;
	}
	// validar choose

	public boolean validarChoose(Articulo articulo) throws Exception {
		try {

		} catch (Exception e) {
			throw new Exception("Formato incorrecto, contiene caracteres");
		}
		if (articulo == null)
			throw new Exception("no ha escojidoun articulo");
		return true;
	}

	// validar
	public boolean validarEspacio1(String nombre, String nombre2) throws Exception {
		try {

		} catch (Exception e) {
			throw new Exception("Formato incorrecto, contiene caracteres");
		}
		if (nombre.equals("") || nombre2.equals("")) {
			throw new Exception("ERROR UN COMPONENTE SE ENCUENTRA VACIO");
		}
		return true;
	}

	public List<Revista> getRevistas() {
		return revistas;
	}

	public List<Articulo> getArticulos() {
		return articulos;
	}

	public List<Autor> getAutores() {
		return autores;
	}

}
