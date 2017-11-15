/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hospitalSanJose.HorarioDoctores.rest;

import com.google.gson.Gson;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.hospitalSanJose.HorarioDoctores.bd.ConsultaBD;
import org.hospitalSanJose.HorarioDoctores.pojos.Consulta;

/**
 * REST Web Service
 *
 * @author Migueli Ramos
 */
@Path("generic")
public class ConsultaResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of EspecialidadResource
     */
    public ConsultaResource() {
    }

    /**
     * Retrieves representation of an instance of paciente.EspecialidadResource
     * @return an instance of java.lang.String
     */
    @GET
    @Path("prueba")
    @Produces(MediaType.TEXT_PLAIN)
    public String getPrueba() {
        Date fecha = new Date();
        System.out.println(fecha.toString() + ": Prueba");

        return "Funciona!!!!! ";
    }

    // Devuelve todos los Registros
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllConsulta() {
        try {
            List<Consulta> consultas = ConsultaBD.getAllConsulta();
            Date fecha = new Date();
            System.out.println(fecha.toString() + ": Se ha consultado - getAllCiclo");
            String json = new Gson().toJson(consultas);
            return Response.ok(json, MediaType.APPLICATION_JSON).build();
        } catch (Exception ex) {
            //ex.printStackTrace();
            return Response.status(Response.Status.SEE_OTHER).entity("No se pudo Consultar los ciclos ").build();
        }
    }
     // Añadir Registro. 
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addConsulta(Consulta consulta) {
        try {
            ConsultaBD.addConsulta(consulta);
            Date fecha = new Date();
            System.out.println(fecha.toString() + ": Se ha añadido - addConsulta " + consulta.getPK_ID());
            String json = "{ \"id\": \"" + String.valueOf(consulta.getPK_ID()) + "\" }";
            return Response.ok(json, MediaType.APPLICATION_JSON).build();
        } catch (SQLException e) {
            //e.printStackTrace();
            return Response.status(Response.Status.SEE_OTHER).entity("No se pudo Insertar Registro: " + consulta.getPK_ID()).build();
        }
    }

    // Actualizar un registro. 
    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateCiclo(Consulta consulta, @PathParam("id") int id) {
        System.out.println("Llegó a la actualización");
        try {
            ConsultaBD.updateConsulta(consulta);
            Date fecha = new Date();
            System.out.println(fecha.toString() + ": Se ha actualizado - updateConsulta " + consulta.getPK_ID());
            String json = "{ \"id\": \"" + String.valueOf(consulta.getPK_ID()) + "\" }";
            return Response.ok(json, MediaType.APPLICATION_JSON).build();
        } catch (SQLException e) {
            //e.printStackTrace();
            return Response.status(Response.Status.SEE_OTHER).entity("No se pudo Actualizar Registro: " + consulta.getPK_ID()).build();
        }
    }

    // Borrar un registro. 
    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response laConsulta(@PathParam("id") int id) {

        try {
            ConsultaBD.laConsulta(id);
            Date fecha = new Date();
            System.out.println(fecha.toString() + ": Se ha borrado - delCiclo " + id);
            String json = "{ \"id\": \"" + id + "\" }";
            return Response.ok(json, MediaType.APPLICATION_JSON).build();
        } catch (SQLException e) {
            //e.printStackTrace();
            return Response.status(Response.Status.SEE_OTHER).entity("No se pudo Borrar Registro: " + id).build();
        }
    }
}
