/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hospitalSanJose.HorarioDoctores.bd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.hospitalSanJose.HorarioDoctores.pojos.Paciente;

/**
 *
 * @author Migueli Ramos
 */
public class PacienteBD {
    static public List<Paciente> getAllPaciente() throws Exception {
        List<Paciente> consultas = new ArrayList<Paciente>();
        
        Connection conexion = ConexionBD.getConexion();
        PreparedStatement ps = null;
        ResultSet rs = null;
       
        try {
            ps = conexion.prepareStatement("SELECT * FROM paciente ORDER BY apellidos ASC, nombre ASC");
            rs = ps.executeQuery();
            
            while (rs.next()) {
                Paciente consulta = new Paciente();

                consulta.setPK_ID(rs.getInt("PK_ID"));
                consulta.setNombre(rs.getString("Nombre"));
                consulta.setApellidos(rs.getString("Apellidos"));

                consultas.add(consulta);
            }
            return consultas;
        } catch (Exception e) {
            throw e;
        } finally {
            if(rs != null) rs.close();
            if(ps != null) ps.close();
            if(conexion != null) conexion.close();
        }   
    }
    
    static public Paciente getPaciente(int id) throws SQLException {
        
        Connection conexion = ConexionBD.getConexion();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            ps = conexion.prepareStatement("SELECT * FROM Paciente WHERE PK_ID = " + id);
            rs = ps.executeQuery();
            
             while (rs.next()) {
                Paciente consulta = new Paciente();

                consulta.setPK_ID(rs.getInt("PK_ID"));
                consulta.setNombre(rs.getString("Nombre"));
                consulta.setApellidos(rs.getString("Apellidos"));

                return consulta;
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            if(rs != null) rs.close();
            if(ps != null) ps.close();
            if(conexion != null) conexion.close();
        }
        return null;
    }
    
     static public void addPaciente(Paciente consulta) throws SQLException {

        Connection conexion = ConexionBD.getConexion();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String str;
            if (consulta.getPK_ID() != -1) {
                str = "INSERT INTO Paciente (PK_ID, Nombre, Apellidos) VALUES ("
                        + consulta.getPK_ID() + ",'" + consulta.getNombre() + "','" + consulta.getApellidos() + "')";
            } else {
                str = "INSERT INTO Paciente (Nombre, Apellidos) VALUES ("
                        + "'" + consulta.getNombre() + "','" + consulta.getApellidos() + "')";
            }

            ps = conexion.prepareStatement(str);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw e;
        } finally {
            if(rs != null) rs.close();
            if(ps != null) ps.close();
            if(conexion != null) conexion.close();
        }
    }

    static public void delPaciente(int id) throws SQLException {
        Connection conexion = ConexionBD.getConexion();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conexion.prepareStatement("DELETE FROM Alumno WHERE PK_ID = " + id + "");

            ps.executeUpdate();

        } catch (SQLException e) {
            throw e;
        } finally {
            if(rs != null) rs.close();
            if(ps != null) ps.close();
            if(conexion != null) conexion.close();
        }
    }

    static public void updatePaciente(Paciente consulta) throws SQLException {
        Connection conexion = ConexionBD.getConexion();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String str = "UPDATE Paciente SET Nombre = '" + consulta.getNombre() + "'"
                    + ", Apellidos = '" + consulta.getApellidos() + "'"
                    + " WHERE PK_ID = " + consulta.getPK_ID();
            System.out.println(str);
            ps = conexion.prepareStatement(str);

            ps.executeUpdate();

        } catch (SQLException e) {
            throw e;
        } finally {
            if(rs != null) rs.close();
            if(ps != null) ps.close();
            if(conexion != null) conexion.close();
        }
    }
    
}
