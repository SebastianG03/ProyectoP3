package SistemaDeCompras.ClasesDelSistema;

public class Usuario {
    private String cedula;
    private String nombre;
    private String correo;


    public Usuario(String cedula, String nombre, String correo) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.correo = correo;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }


    public String imprimirDatos() {
        return "Cedula: " + cedula +
                "\nNombre: " + nombre +
                "\nCorreo: " + correo;
    }
}
