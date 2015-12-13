package hakatonperiodismo.periodismo.com.periodismo.objetos;

/**
 * Created by debbie on 14-09-15.
 */
public class Usuario {

    private String id_usuario;
    private String usuario;
    private String password;


    public Usuario(String id_usuario, String usuario, String password)
    {
        this.id_usuario=id_usuario;
        this.usuario=usuario;
        this.password=password;
    }
    public String getId_usuario(){return id_usuario;}
    public String getUsuario (){return usuario;}
    public String getPassword (){return password;}
}
