public class Id {
    private int categoria;
    private int especieMascota;
    private int desplazamiento;
    private char[] digitos;
    public Id(String indicesSegmentos){
        this.digitos = indicesSegmentos.toCharArray();
        this.categoria = Integer.parseInt(String.valueOf(digitos[0]));
        this.especieMascota = Integer.parseInt(String.valueOf(digitos[1]));
        if(digitos.length == 5){
            StringBuilder sb = new StringBuilder();
            for(int i = 2; i < 5; i++){
                sb.append(digitos[i]);
            }
            this.desplazamiento = Integer.parseInt(sb.toString());
        }
    }

    public int obtenerCategoria(){
        return this.categoria;
    }
    public int obtenerEspecieMascota(){
        return this.especieMascota;
    }
    public int obtenerDesplazamiento(){
        return  this.desplazamiento;
    }
    public int obtenerDigitos(){
        return Integer.parseInt(toString());
    }
    public void establecerDesplazamiento(int desplazamiento){
        this.desplazamiento = desplazamiento;
    }

    private String mostrarDesplazamiento(){
        String porcion = "";
        if(desplazamiento / 10 < 1){
            porcion += "00" + desplazamiento;
        }else if(desplazamiento  / 100 < 1){
            porcion += "0" + desplazamiento;
        }else{
            porcion += desplazamiento;
        }
        return porcion;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(this.categoria); sb.append(this.especieMascota); sb.append(mostrarDesplazamiento());
        return sb.toString();
    }
}
