
package com.modelo;

/**
 *
 * @author Gerardo Tax
 */
public class Trafico {
    int horaInicio;
    int horaFinal;
    int ProbabilidadTrafico;

    public Trafico( int horaInicio, int horaFinal, int ProbabilidadTrafico) {
        this.horaInicio = horaInicio;
        this.horaFinal = horaFinal;
        this.ProbabilidadTrafico = ProbabilidadTrafico;
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
