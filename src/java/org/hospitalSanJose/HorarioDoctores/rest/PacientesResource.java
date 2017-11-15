/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hospitalSanJose.HorarioDoctores.rest;

import java.sql.SQLException;
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
import org.hospitalSanJose.HorarioDoctores.bd.PacienteBD;
import org.hospitalSanJose.HorarioDoctores.pojos.Paciente;

/**
 * REST Web Service
 *
 * @author Migueli Ramos
 */
@Path("generic")
public class PacientesResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of GenericResource
     */
    public PacientesResource() {
    }

    /**
     * Retrieves representation of an instance of paciente.GenericResource
     * @return an instance of java.lang.String
     */
    @GET
    @Path("prueba")
    @Produces(MediaType.TEXT_PLAIN)
    public String getPrueba() {

        return "Funciona!!!!!";
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Paciente> getAllPaciente() {
        try {
            List<Paciente> consultas = PacienteBD.getAllPaciente();
            return consultas;
        } catch (Exception ex) {
            //ex.printStackTrace();
            return null;
        }
    }
    
    // Use data from the client source to create a new Person object, returned in JSON format. 
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String addPaciente(Paciente consulta) {

        try {
            PacienteBD.addPaciente(consulta);
            return Integer.toString(consulta.getPK_ID());
        } catch (SQLException e) {
            //e.printStackTrace();
            return "-1";
        }
    }
    
    /**
     * PUT method for updating or creating an instance of GenericResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
       
    // Actualizar un registro. 
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String updatePaciente(Paciente consulta) {

        try {
            PacienteBD.updatePaciente(consulta);
            return Integer.toString(consulta.getPK_ID());
        } catch (SQLException e) {
            //e.printStackTrace();
            return "-1";
        }
    }

    // Borrar un registro. 
    @DELETE
    @Path("{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public String delAPaciente(@PathParam("id") int id) {

        try {
            PacienteBD.delPaciente(id);
            return Integer.toString(id);
        } catch (SQLException e) {
            //e.printStackTrace();
            return "-1";
        }
    }
}
