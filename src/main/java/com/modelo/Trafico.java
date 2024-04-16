
package com.modelo;

/**
 *
 * @author Gerardo Tax
 */
public class Trafico {
    String origin;
    String destino;
    int horaInicio;
    int horaFinal;
    int ProbabilidadTrafico;

    public Trafico(String origin, String destino, int horaInicio, int horaFinal, int ProbabilidadTrafico) {
        this.origin = origin;
        this.destino = destino;
        this.horaInicio = horaInicio;
        this.horaFinal = horaFinal;
        this.ProbabilidadTrafico = ProbabilidadTrafico;
    }
    
    

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public int getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(int horaInicio) {
        this.horaInicio = horaInicio;
    }

    public int getHoraFinal() {
        return horaFinal;
    }

    public void setHoraFinal(int horaFinal) {
        this.horaFinal = horaFinal;
    }

    public int getProbabilidadTrafico() {
        return ProbabilidadTrafico;
    }

    public void setProbabilidadTrafico(int ProbabilidadTrafico) {
        this.ProbabilidadTrafico = ProbabilidadTrafico;
    }
  
}
