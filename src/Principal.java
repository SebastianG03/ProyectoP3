import javax.swing.*;
import java.util.Random;

public class Principal {
    public static void main(String[] args){
        Id idz = new Id("12345");
        System.out.println("ID: " + idz);
        //Generar Inventario
        Inventario inv = new Inventario();
        Random generadorInt = new Random();
        Random generadorDouble = new Random();
        Random generadorString = new Random();
        String texto;


        for(int j = 0; j < 50; j++){
            texto = "";

            String categoria = String.valueOf(generadorInt.nextInt(3));
            String espMascota = String.valueOf(generadorInt.nextInt(3));
            double precio = generadorDouble.nextDouble(9.99);
            int stock = generadorInt.nextInt(999);
            String id = categoria + espMascota;

            for(int i = 0; i < 5 ; i++){
                char c = (char)(generadorString.nextInt(5) + 'a');
                texto += c;
            }

            inv.agregarProducto(new Producto(id,texto,precio,stock,".../" + texto +"/..."));
        }

        //Indroduce productos iguales en nombre y descripción
        inv.agregarProducto(new Producto("22","REP",7.77,777,".../REP/..."));
        inv.agregarProducto(new Producto("22","REP",8.88,888,".../REP/..."));
        //Mostrar inventario
        System.out.println(inv);

        //Filtrar por categoria
        Object[] arregloFiltrado = inv.filtrarCategoria(1);
        for(Object o: arregloFiltrado){
            ((Producto)o).mostrarProducto();
        }
        System.out.println("\n");
        //Filtrar por especie
        Object[] arregloFiltrado2 = inv.filtrarEspecieMascota(2);
        for(Object o: arregloFiltrado2){
            ((Producto)o).mostrarProducto();
        }
        System.out.println("\n");
        inv.ordenarUnVendidas(arregloFiltrado2);
        for(Object o: arregloFiltrado2){
            ((Producto)o).mostrarProducto();
        }

        /*//Buscar Producto
        String buscar = JOptionPane.showInputDialog("Ingrese el id del producto que desea buscar: ");
        Id idBuscar = new Id(buscar);
        try{
            Producto encontrado = inv.buscarProducto(idBuscar);
            encontrado.mostrarProducto();
        }catch (Exception ex){
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }

        //Eliminar Producto
        String eliminar = JOptionPane.showInputDialog("Ingrese el id del producto que desea eleminar: ");
        Id idEliminar = new Id(eliminar);
        try{
            boolean eliminado = inv.eliminarProducto(idEliminar);
            System.out.println("Producto eliminado: " + eliminado);
        }catch (Exception ex){
            JOptionPane.showMessageDialog(null,ex.getMessage());
        }

        //Modificar Nombre del Producto
        String sModificarNombre = JOptionPane.showInputDialog("Ingrese el id del producto que desea modificar: ");
        String modificarNombre = JOptionPane.showInputDialog("Ingrese el nuevo nombre del producto: ");
        Id  idModificarNombre = new Id(sModificarNombre);
        boolean modificadoNombre = inv.modificarNombre(idModificarNombre, modificarNombre);
        System.out.println("Producto modificado: " + modificadoNombre);
        //Modificar Descripcion del Producto
        String sModificarDescripcion = JOptionPane.showInputDialog("Ingrese el id del producto que desea modificar: ");
        String modificarDescripcion = JOptionPane.showInputDialog("Ingrese la nueva descripcion del producto: ");
        Id  idModificarDescripcion = new Id(sModificarDescripcion);
        boolean modificadoDescripcion = inv.modificarDescripcion(idModificarDescripcion, modificarDescripcion);
        System.out.println("Producto modificado: " + modificadoDescripcion);

        //Mostrar inventario
        System.out.println(inv);*/
    }
}
