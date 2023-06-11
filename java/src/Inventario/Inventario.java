package Inventario;

import Inventario.Categoria.*;
import Producto.*;

public class Inventario {
    private Categoria comida;
    private Categoria accesorio;
    private Categoria insumo_medico;

    //CONSTRUCTOR
    public Inventario(){
        this.comida = new Comida();
        this.accesorio = new Accesorio();
        this.insumo_medico = new InsMedico();
    }

    //METODOS OBTENER
    public Comida obtener_categoria_comida(){
        return (Comida) this.comida;
    }
    public Accesorio obtener_categoria_accesorio(){
        return (Accesorio) this.accesorio;
    }
    public  InsMedico obtener_insumo_medico(){
        return (InsMedico) this.insumo_medico;
    }

    //OPERACIONES BÁSICAS
        //AGREGAR
    public boolean agregar_producto_comida(Producto nuevo_producto){
        comida.vectorizar_producto(nuevo_producto);
        return comida.agregar_producto(nuevo_producto);
    }
    public boolean agregar_producto_accesorio(Producto nuevo_producto){
        accesorio.vectorizar_producto(nuevo_producto);
        return accesorio.agregar_producto(nuevo_producto);
    }
    public boolean agregar_producto_insumo_medico(Producto nuevo_producto){
        insumo_medico.vectorizar_producto(nuevo_producto);
        return insumo_medico.agregar_producto(nuevo_producto);
    }
        //BUSCAR
    public Producto buscar_producto_comida(Id identificador) throws Exception{
        return comida.buscar_producto(identificador);
    }
        //ELIMINAR
    public boolean eliminar_producto_comida(Id identificador){
        return comida.eliminar_producto(identificador);
    }
    public boolean eliminar_producto_accesorio(Id identificador){
        return accesorio.eliminar_producto(identificador);
    }
    public boolean eliminar_producto_insumo_medico(Id identificador){
        return insumo_medico.eliminar_producto(identificador);
    }
        //MODIFICAR
    public boolean modificar_producto_comida(Id identificador, String nombre, double precio, double descuento, int stock,
                                             String descripcion, String marca, String fabricante, String raza,
                                             String sabor, String contenedor, String etapaDeVida, String tipo){
        return comida.modificar_producto(identificador, nombre, precio, descuento, stock, descripcion) &&
                ((Comida)comida).modificar_producto_comida(identificador, marca, fabricante, raza, sabor, contenedor, etapaDeVida, tipo);
    }

    public void solicitudCompra(Producto nuevoProducto, int cantidad) {
        if (nuevoProducto instanceof ProductoComida) {
            comida.restar_stock(nuevoProducto.obtener_id(), cantidad);
            comida.aumentar_unidades_vendidas(nuevoProducto.obtener_id(), cantidad);
        } else if (nuevoProducto instanceof ProductoAccesorio) {
            accesorio.restar_stock(nuevoProducto.obtener_id(), cantidad);
            accesorio.aumentar_unidades_vendidas(nuevoProducto.obtener_id(), cantidad);
        } else if (nuevoProducto instanceof ProductoInsMedico) {
            insumo_medico.restar_stock(nuevoProducto.obtener_id(), cantidad);
            insumo_medico.aumentar_unidades_vendidas(nuevoProducto.obtener_id(), cantidad);
        }
    }

    @Override
    public String toString(){
        return comida.toString() + accesorio.toString() + insumo_medico.toString();
    }
}


