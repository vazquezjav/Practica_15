package controlador;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;

import modelo.departamento.Departamento;
import modelo.departamento.Empleado;
import modelo.departamento.Empresa;
import modelo.revista.Revista;

public class GestionDepartamento {
	private List<Empresa> empresas;
	private List<Departamento> departamentos;
	private List<Empleado> empleados;
	private String pathEmpresa = "Practica15/src/archivos/Empresa.txt";
	private String pathDepartamento = "Practica15/src/archivos/Departamento.txt";

	public GestionDepartamento() {
		empresas = new ArrayList<Empresa>();
		departamentos = new ArrayList<Departamento>();
		empleados = new ArrayList<Empleado>();
	}
//metodo para agragar datos del departamento y de el trabajador
	public void agregarDepartamento(String nombreEm, String apellidoEm, String cedula, String nombreDepa, String codigo) {
	
			Empleado em = new Empleado();
			em.setNombreEm(nombreEm);
			em.setApellidoEm(apellidoEm);
			em.setCedula(cedula);
			empleados.add(em);

			Departamento depa = new Departamento();
			depa.setNombredepa(nombreDepa);
			depa.setCodigo(codigo);
			depa.setEmpleados(em);
			departamentos.add(depa);
			try {
				  FileOutputStream file =  new FileOutputStream (pathDepartamento);
				  ObjectOutputStream escritura = new ObjectOutputStream (file);
				  escritura.writeObject(depa);
				  escritura.close();

		} catch (Exception e) {
		}
	}
//metodo para agragar empresa
	public void agregarEmpresa(String nombre, String ruc, String direccion, Departamento departamento) {
		
			Empresa emp = new Empresa();
			emp.setNombre(nombre);
			emp.setRuc(ruc);
			emp.setDireccion(direccion);
			emp.setDepartamentos(departamento);
			empresas.add(emp);
			try{
			 FileOutputStream file =  new FileOutputStream (pathEmpresa);
			  ObjectOutputStream escritura = new ObjectOutputStream (file);
			  escritura.writeObject(emp);
			  escritura.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//metodo para validar error del choose
	public boolean isChoose(Departamento departamento) throws Exception{
		try {
			
		}catch(Exception e){
			throw new Exception("Formato incorrecto, contiene caracteres");
		}
		if(departamento==null )
			throw new Exception("NO A LLENADO EL DEPARTAMENTO");
		return true;
	}
	//metodo de validacion de espacios en blanco
	public boolean isEsenci(String nombre,String ruc,String direccion) throws Exception{
		try {
			
		}catch(Exception e){
			throw new Exception("Formato incorrecto, contiene caracteres");
		}
		if(nombre.equals("")|| ruc.equals("") || direccion.equals(""))
			throw new Exception("ERROR UN COMPONENTE SE ENCUENTRA VACIO");
		return true;
	}
	//validacion de cedula
	public boolean isCedulaValida(String cedula) throws Exception{
		try {
			int a = Integer.parseInt(cedula);
		}catch(NumberFormatException e){
			throw new Exception("Formato incorrecto, contiene caracteres");
		}
		if(cedula.length()!=10)
			throw new Exception("Debe ser de 10 dígitos");
		
		return true;
	}
	//validacion de espacios vacios
	public boolean isEsenci2(String nombreEm, String apellidoEm, String cedula, String nombreDepa, String codigo) throws Exception{
		try {
			
		}catch(Exception e){
			throw new Exception("Formato incorrecto, contiene caracteres");
		}
		if(nombreEm.equals("")|| apellidoEm.equals("") || cedula.equals("")|| nombreDepa.equals("")|| codigo.equals(""))
			throw new Exception("ERROR UN COMPONENTE SE ENCUENTRA VACIO");
		return true;
	}
	

	
	public List<Empresa> getEmpresas() {
		return empresas;
	}
	public List<Departamento> getDepartamentos() {
		return departamentos;
	}
	public List<Empleado> getEmpleados() {
		return empleados;
	}
	
public List<Departamento> leerDepartamento() throws IOException {
	FileInputStream archivoLectura=null;
	ObjectInputStream entrada=null;
	try{
	    archivoLectura=new FileInputStream(pathDepartamento);
	    entrada = new ObjectInputStream(archivoLectura);
	    Departamento departamento = (Departamento)entrada.readObject();
	    departamentos.add(departamento);
	}catch(Exception e){
	    e.printStackTrace();
	}finally{
	    entrada.close();
	}
		return departamentos;
	}
	
public List<Empresa> leerEmpresa() throws IOException {
	FileInputStream archivoLectura=null;
	ObjectInputStream entrada=null;
	try{
	    archivoLectura=new FileInputStream(pathEmpresa);
	    entrada = new ObjectInputStream(archivoLectura);
	    Empresa empresa = (Empresa)entrada.readObject();
	    empresas.add(empresa);
	}catch(Exception e){
	    e.printStackTrace();
	}finally{
	    entrada.close();
	}
	return empresas;
}
	
}
