package jislap1.lenovo.com.jplay.Objetos;

public class Pelicula {
    private String nombre;
    private String descripción;
    private String idcategoria;
    private String url;
    private String durabilidad;
    private String restricción;

    public Pelicula() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripción() {
        return descripción;
    }

    public void setDescripción(String descripción) {
        this.descripción = descripción;
    }

    public String getIdcategoria() {
        return idcategoria;
    }

    public void setIdcategoria(String idcategoria) {
        this.idcategoria = idcategoria;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDurabilidad() {
        return durabilidad;
    }

    public void setDurabilidad(String durabilidad) {
        this.durabilidad = durabilidad;
    }

    public String getRestricción() {
        return restricción;
    }

    public void setRestricción(String restricción) {
        this.restricción = restricción;
    }
}
