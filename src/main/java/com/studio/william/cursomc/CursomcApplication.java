package com.studio.william.cursomc;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.studio.william.cursomc.domain.Categoria;
import com.studio.william.cursomc.domain.Ciudad;
import com.studio.william.cursomc.domain.Cliente;
import com.studio.william.cursomc.domain.Direccion;
import com.studio.william.cursomc.domain.Estado;
import com.studio.william.cursomc.domain.Producto;
import com.studio.william.cursomc.domain.enums.TipoCliente;
import com.studio.william.cursomc.repositories.CategoriaRepository;
import com.studio.william.cursomc.repositories.ClienteRepository;
import com.studio.william.cursomc.repositories.CuidadRepository;
import com.studio.william.cursomc.repositories.DireccionRepository;
import com.studio.william.cursomc.repositories.EstadoRepository;
import com.studio.william.cursomc.repositories.ProductoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProductoRepository productoRepository;

	@Autowired
	private EstadoRepository estadoRepository;

	@Autowired
	private CuidadRepository cuidadRepositroy;
	
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private DireccionRepository direccionrepository; 
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritorio");
		
		Producto p1 = new Producto(null, "Computador", 2000.00);
		Producto p2 = new Producto(null, "Impresora", 800.00);
		Producto p3 = new Producto(null, "Mouse", 80.00);
		

		
		cat1.getProductos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProductos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1,cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		categoriaRepository.saveAll(Arrays.asList(cat1,cat2));
		productoRepository.saveAll(Arrays.asList(p1,p2,p3));
		
		Estado est1 = new Estado(null, "Cundinamarca");
		Estado est2 = new Estado(null, "Medellin");
		
		Ciudad c1 = new Ciudad(null, "Cundinamarca", est1);
		Ciudad c2 = new Ciudad(null, "Medellin", est2);
		Ciudad c3 = new Ciudad(null, "Yondó", est2);
		
		est1.getCuidades().addAll(Arrays.asList(c1));
		est2.getCuidades().addAll(Arrays.asList(c2,c3));
		
		estadoRepository.saveAll(Arrays.asList(est1, est2)); //primero los estados para mantener las relaciones...
		cuidadRepositroy.saveAll(Arrays.asList(c1, c2,c3));
		
		
		Cliente cli1 = new Cliente(null, "Maria Silva", "Maria@gmail.com", "36378912377", TipoCliente.PERSONANATURAL);
		cli1.getTelefonos().addAll(Arrays.asList("27363323","93838393"));
		
		Direccion e1 = new Direccion(null, "Calle", "26", "Apto 303", "Jardim", "38220834",cli1, c1);
		Direccion e2 = new Direccion(null, "Avenida", "26", "Sala 800", "Centro", "38220834",cli1, c2);
		
		cli1.getDirecciones().addAll(Arrays.asList(e1, e2));
		
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		direccionrepository.saveAll(Arrays.asList(e1, e2));
	}

	
}
