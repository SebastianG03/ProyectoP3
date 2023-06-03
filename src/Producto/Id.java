package Producto;

public class Id {
    private int especie;
    private int desplazamiento;
    public Id(int identificadorEspecie, int desplazamiento){
        this.especie = identificadorEspecie;
        this.desplazamiento = desplazamiento;
    }
    public Id(String identificador) throws Exception{
        char[] digitos = identificador.toCharArray();
        if(digitos.length==4){ //Controla que la ID del producto cuente con solo 4 dígitos
            //Se asigna el primer dígito de la ID como el índice de la especie
            this.especie = Integer.parseInt(String.valueOf(digitos[0]));

            StringBuilder acumulador = new StringBuilder();//Acumulador

            for(int i=1; i<digitos.length; i++){
                acumulador.append(digitos[i]);//Se añade los últimos tres dígitos en el acumulador
            }
            
            this.desplazamiento = Integer.parseInt(String.valueOf(acumulador));
        }else {
            throw new Exception("La id del producto debe contar con 4 dígitos");
        }
    }
    public int obtenerIdentificadorEspecie(){
        return this.especie;
    }
    public int obtenerDesplazamiento(){
        return this.desplazamiento;
    }
    public void establecerDesplazamiento(int desplazamiento){
        this.desplazamiento = desplazamiento;
    }
    private String generarIdentificador(){
        String porcionDesplazamiento = "";
        if(desplazamiento / 10 < 1){
            porcionDesplazamiento += "00" + desplazamiento;
        }else if(desplazamiento  / 100 < 1){
            porcionDesplazamiento += "0" + desplazamiento;
        }else{
            porcionDesplazamiento += desplazamiento;
        }
        return this.especie + porcionDesplazamiento;
    }
    public int obtenerIdentificador(){
        return Integer.parseInt(generarIdentificador());
    }

    @Override
    public String toString(){
        return generarIdentificador();
    }
}
