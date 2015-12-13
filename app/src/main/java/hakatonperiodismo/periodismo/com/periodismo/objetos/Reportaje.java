package hakatonperiodismo.periodismo.com.periodismo.objetos;

/**
 * Created by debbie on 12-12-15.
 */
public class Reportaje {

    private String id;
    private String titulo;
    private String lead;
    private String nota;
    private String tags;
    private String fecha;
    private String estado;
    private String reportero_id;
    private String categoria_id;
    private String estado_sinc;



    public Reportaje(String id, String titulo, String lead, String nota,
                     String tags, String fecha, String estado, String reportero_id,
                     String categoria_id)
    {
        this.id=id;
        this.titulo=titulo;
        this.lead=lead;
        this.nota=nota;
        this.tags=tags;
        this.fecha=fecha;
        this.estado=estado;
        this.reportero_id=reportero_id;
        this.categoria_id=categoria_id;
        this.estado_sinc=estado_sinc;

    }
    public String getId()
    {
        return  id;
    }
    public String getTitulo()
    {
        return  titulo;
    }
    public String getLead()
    {
     return lead;
    }
    public String getNota()
    {
        return  nota;
    }
    public String getTags()
    {
        return  tags;
    }
    public String getFecha()
    {
        return  fecha;
    }

    public String getEstado()
    {
        return estado;
    }
    public String getReportero_id()
    {
        return  reportero_id;
    }
    public String getCategoria_id()
    {
        return  categoria_id;
    }
    public String getEstado_sinc()
    {
        return  estado_sinc;
    }


}