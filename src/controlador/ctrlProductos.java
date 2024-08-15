package controlador;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashSet;
import java.util.Set;
import modelo.Producto;
import vista.frmProductos;

public class ctrlProductos implements MouseListener {
    
   //1- Llamar a las otras capas (modelo, vista)
    private frmProductos Vista;
    private Producto modelo;
    
    //2- Crear el constructor de la clase
    public ctrlProductos(frmProductos Vista, Producto Modelo){
        this.Vista = Vista;
        this.modelo = Modelo;
        
        Vista.btnInsertar.addMouseListener(this);
        
        //Para mostrar los datos
        Vista.jtbProductos.addMouseListener(this);
        modelo.Mostrar(Vista.jtbProductos);
        
       
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        
        
        if(e.getSource() == Vista.btnInsertar){
            System.err.println("le di clic al boton");
                    
            modelo.setNombre(Vista.txtNombre.getText());
            modelo.setPrecio(Double.parseDouble(Vista.txtPrecio.getText()));
            modelo.setCategoria(Vista.txtCategoria.getText());
            
            modelo.Guardar();
            modelo.Mostrar(Vista.jtbProductos);
            
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }
    
}
