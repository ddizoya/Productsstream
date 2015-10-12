package productsstream;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.w3c.dom.css.DocumentCSS;

public class Producsstream {
	private String ruta = "C:\\Users\\David\\Desktop\\productos.txt";
	private File file = new File(ruta);
	private DataOutputStream dos;
	private DataInputStream dis;

	/**
	 * >>Nota: Preguntar en clase si realmente esto era lo que había que hacer.
	 * 
	 */
	public void guardarEnFichero(Product pro) {
		try {
			if (!file.exists())
				file.createNewFile();

			// Grabamos simplemente uno a uno los atributos.
			dos = new DataOutputStream(new FileOutputStream(file, true)); // Importante
																			// el
																			// True
			dos.writeUTF(pro.getCodigo());
			dos.writeUTF(pro.getDescripcion());
			dos.writeByte(pro.getPrecio());

			// No cerramos aquí el flujo del output

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				dos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void asignarDesdeFichero(Product obj) {
		try {
			dis = new DataInputStream(new FileInputStream(file));

			byte i = 1;
			while (dis.available() > 0) {
				obj.setCodigo(dis.readUTF());
				obj.setDescripcion(dis.readUTF());
				obj.setPrecio(dis.readByte());

				System.out.println("Valores del objeto po3 con los valores del objeto po" + i + ":" + "\n Código: "
						+ obj.getCodigo() + "\n Descripción: " + obj.getDescripcion() + "\n Precio: "
						+ obj.getPrecio());
				i++;
			}
			i = 0;

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				dos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] belénEsteban) {
		// Creación de dos objetos con atributos, y uno con nulos.
		Product po1 = new Product("cod1", "parafusos", 3);
		Product po2 = new Product("cod2", "arandelas", 4);
		Product po3 = new Product();

		// Objeto auxiliar para emplear los métodos.
		Producsstream ps = new Producsstream();

		ps.guardarEnFichero(po1);
		ps.guardarEnFichero(po2);

		ps.asignarDesdeFichero(po3);
	}

}
