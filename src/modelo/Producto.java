package modelo;
import java.sql.*;
import java.util.UUID;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Producto {
    
    //1- Parametros 
    String UUID_product;
    String nombre;
    Double Precio;
    String Categoria;
    
    //2- Creacion de getters y setters

    public String getUUID_product() {
        return UUID_product;
    }

    public void setUUID_product(String UUID_product) {
        this.UUID_product = UUID_product;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getPrecio() {
        return Precio;
    }

    public void setPrecio(Double Precio) {
        this.Precio = Precio;
    }

    public String getCategoria() {
        return Categoria;
    }

    public void setCategoria(String Categoria) {
        this.Categoria = Categoria;
    }
    
    public void Guardar() {
        //Creamos una variable igual a ejecutar el método de la clase de conexión
        Connection conexion = ClaseConexion.getConexion();
        try {
            System.out.println("estoy ene l modelo");
            //Creamos el PreparedStatement que ejecutará la Query
            PreparedStatement addProducto = conexion.prepareStatement("INSERT INTO tbProducto(UUID_producto, Nombre, precio, categoria) VALUES (?, ?, ?, ?)");
            //Establecer valores de la consulta SQL
            addProducto.setString(1, UUID.randomUUID().toString());
            addProducto.setString(2, getNombre());
            addProducto.setDouble(3, getPrecio());
            addProducto.setString(4, getCategoria());
 
            //Ejecutar la consulta
            addProducto.executeUpdate();
            System.out.println("si ves esto es por que se ejecuta el de guardar");
 
        } catch (SQLException ex) {
            System.out.println("este es el error en el modelo:metodo guardar " + ex);
        }
    }
    
    public void Mostrar(JTable tabla) {
        //Creamos una variable de la clase de conexion
        Connection conexion = ClaseConexion.getConexion();
        //Definimos el modelo de la tabla
        DefaultTableModel modeloPinulito = new DefaultTableModel();
        modeloPinulito.setColumnIdentifiers(new Object[]{"UUID_producto", "Nombre", "Precio", "Categoria"});
        try {
            //Creamos un Statement
            Statement statement = conexion.createStatement();
            //Ejecutamos el Statement con la consulta y lo asignamos a una variable de tipo ResultSet
            ResultSet rs = statement.executeQuery("SELECT * FROM tbProducto");
            //Recorremos el ResultSet
            while (rs.next()) {
                //Llenamos el modelo por cada vez que recorremos el resultSet
                modeloPinulito.addRow(new Object[]{rs.getString("UUID_producto"), 
                    rs.getString("nombre"), 
                    rs.getInt("precio"), 
                    rs.getString("categoria")});
            }
            //Asignamos el nuevo modelo lleno a la tabla
            tabla.setModel(modeloPinulito);
        } catch (Exception e) {
            System.out.println("Este es el error en el modelo, metodo mostrar " + e);
        }
    }
    
}
