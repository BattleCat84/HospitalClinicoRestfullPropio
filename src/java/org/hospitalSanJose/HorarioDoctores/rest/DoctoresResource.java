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
import org.hospitalSanJose.HorarioDoctores.bd.ConsultaBD;
import org.hospitalSanJose.HorarioDoctores.bd.DoctorBD;
import org.hospitalSanJose.HorarioDoctores.pojos.Doctor;

/**
 * REST Web Service
 *
 * @author Migueli Ramos
 */
@Path("generic")
public class DoctoresResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of DoctoresResource
     */
    public DoctoresResource() {
    }

    /**
     * Retrieves representation of an instance of alumnado.GenericResource
     *
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
    public List<Doctor> getAllAlumno() {
        try {
            List<Doctor> consultas = DoctorBD.getAllDoctor();
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
    public String addDoctor(Doctor consulta) {

        try {
            DoctorBD.addDoctor(consulta);
            return Integer.toString(consulta.getPK_ID());
        } catch (SQLException e) {
            //e.printStackTrace();
            return "-1";
        }
    }

    // Actualizar un registro. 
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String updateAlumno(Doctor consulta) {

        try {
            DoctorBD.updateDoctor(consulta);
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
    public String delAlumno(@PathParam("id") int id) {

        try {
            DoctorBD.delDoctor(id);
            return Integer.toString(id);
        } catch (SQLException e) {
            //e.printStackTrace();
            return "-1";
        }
    }
}
