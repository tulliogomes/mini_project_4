package br.edu.ifpb.pd.rest;

import java.util.HashMap;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import br.edu.ifpb.pd.Noticia;

@ApplicationPath("/noticias")
public class ResourceNoticias extends Application {
	
	@Context
    private UriInfo context;
	private HashMap<String,Noticia> noticias = new HashMap<String,Noticia>();
	
	public ResourceNoticias() {
        noticias.put("1",new Noticia("1","Bia", "sem titulo", "05/05/05", "o jardineiro eh jesus e as arvores somos noses"));
        noticias.put("2",new Noticia("2","Tullio", "amor sem fim", "05/05/05", "eh melhor ser dois do que um"));
        //alunos.put("3",new Aluno("3","Bruno"));
    }

	@POST
    public Response cadastrar(@FormParam("id")String id,
    						  @FormParam("autor")String autor,
    						  @FormParam("titulo")String titulo,
    						  @FormParam("data")String data,
    						  @FormParam("conteudo")String conteudo){
        noticias.put(id, new Noticia(id, autor, titulo, data, conteudo));
        System.out.println("Noticia Cadastrada");
        //OU
        //return Response.status(Response.Status.CREATED).build();
        return Response.ok(MediaType.APPLICATION_JSON_TYPE).build();
    }
	
	@GET
    @Path("{id}")
    public Response getNoticia(@PathParam("id") String id,
            @QueryParam("formato")String formato) {
        if (noticias.containsKey(id)){
            if (formato.equals("xml"))
                return Response.ok(
                    noticias.get(id),
                        MediaType.APPLICATION_XML).build();
            else if (formato.equals("html")){
                String html = "<html><body>"+
                        noticias.get(id).getAutor()+
                        noticias.get(id).getTitulo()+
                        noticias.get(id).getData()+
                        noticias.get(id).getConteudo()
                        +"</body></html>";
                return Response.ok(
                    html,
                        MediaType.TEXT_HTML).build();
            }
            else return Response.status(Response.Status.UNSUPPORTED_MEDIA_TYPE).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
	
	@PUT
    @Path("{id}/{titulo}")
    public Response atualizar (@PathParam("id")String id, @PathParam("titulo")String titulo){
        if (!noticias.containsKey(id))
            return Response.status(Response.Status.NOT_FOUND).build();
        else{
            Noticia nova = noticias.get(id);
            nova.setTitulo(titulo);
            return Response.ok(nova, MediaType.APPLICATION_ATOM_XML).build();
        }
    }
	
	@DELETE
    @Path("{id}")
    public Response remover(@PathParam("id")String id){
        if (!noticias.containsKey(id))
            return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok(noticias.remove(id),
                MediaType.APPLICATION_XML).build();
    }

}
