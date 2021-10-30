import java.io.Serializable;

public class  Carro implements Serializable {

    private String modelo;
    private String fabricante;
    private String ano;
    private String potencia;

    @Override
    public String toString() {
        return "Carro{" +
                "modelo='" + modelo + '\'' +
                ", fabricante='" + fabricante + '\'' +
                ", ano='" + ano + '\'' +
                ", potencia='" + potencia + '\'' +
                '}';
    }

    public Carro(String modelo, String fabricante, String ano, String potencia) {
        this.modelo = modelo;
        this.fabricante = fabricante;
        this.ano = ano;
        this.potencia = potencia;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public String getPotencia() {
        return potencia;
    }

    public void setPotencia(String potencia) {
        this.potencia = potencia;
    }
}
