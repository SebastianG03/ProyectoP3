package Producto;

public class Id {
    private int mascota;
    private int desplazamiento;

    //CONSTRUCTORES
    public Id(int identificadorEspecie, int desplazamiento){
        this.mascota = identificadorEspecie;
        this.desplazamiento = desplazamiento;
    }
    public Id(String identificador) throws Exception{
        char[] digitos = identificador.toCharArray();
        if(digitos.length==4){ //Controla que la ID del producto cuente con solo 4 dígitos
            //Se asigna el primer dígito de la ID como el índice de la especie
            this.mascota = Integer.parseInt(String.valueOf(digitos[0]));

            StringBuilder acumulador = new StringBuilder();//Acumulador

            for(int i=1; i<digitos.length; i++){
                acumulador.append(digitos[i]);//Se añade los últimos tres dígitos en el acumulador
            }

            this.desplazamiento = Integer.parseInt(String.valueOf(acumulador));
        }else {
            throw new Exception("La id del producto debe contar con 4 dígitos");
        }
    }

    //METODOS OBTENER
    public int obtener_mascota(){
        return this.mascota;
    }
    public int obtener_desplazamiento(){
        return this.desplazamiento;
    }
    public int obtener_identificador(){
        return Integer.parseInt(generar_identificador());
    }

    //METODOS ESTABLECER
    public void establecer_mascota(int mascota){
        this.mascota = mascota;
    }
    public void establecer_desplazamiento(int desplazamiento){
        this.desplazamiento = desplazamiento;
    }

    //METODOS AUXILIARES
    private String generar_identificador(){
        String porcionDesplazamiento = "";
        if(desplazamiento / 10 < 1){
            porcionDesplazamiento += "00" + desplazamiento;
        }else if(desplazamiento  / 100 < 1){
            porcionDesplazamiento += "0" + desplazamiento;
        }else{
            porcionDesplazamiento += desplazamiento;
        }
        return this.mascota + porcionDesplazamiento;
    }

    @Override
    public String toString(){
        return generar_identificador();
    }
}
