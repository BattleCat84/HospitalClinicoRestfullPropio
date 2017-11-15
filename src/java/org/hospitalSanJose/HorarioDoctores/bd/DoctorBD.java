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
import org.hospitalSanJose.HorarioDoctores.pojos.Doctor;

/**
 *
 * @author Migueli Ramos
 */
public class DoctorBD {
    static public List<Doctor> getAllDoctor() throws Exception {
        List<Doctor> consultas = new ArrayList<Doctor>();
        
        Connection conexion = ConexionBD.getConexion();
        PreparedStatement ps = null;
        ResultSet rs = null;
       
        try {
            ps = conexion.prepareStatement("SELECT * FROM Doctor ORDER BY apellidos ASC, nombre ASC");
            rs = ps.executeQuery();
            
            while (rs.next()) {
                Doctor consulta = new Doctor();

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
    
    static public Doctor getDoctor(int id) throws SQLException {
        
        Connection conexion = ConexionBD.getConexion();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            ps = conexion.prepareStatement("SELECT * FROM Doctor WHERE PK_ID = " + id);
            rs = ps.executeQuery();
            
             while (rs.next()) {
                Doctor consulta = new Doctor();

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
    
     static public void addDoctor(Doctor consulta) throws SQLException {

        Connection conexion = ConexionBD.getConexion();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String str;
            if (consulta.getPK_ID() != -1) {
                str = "INSERT INTO Alumno (PK_ID, Nombre, Apellidos) VALUES ("
                        + consulta.getPK_ID() + ",'" + consulta.getNombre() + "','" + consulta.getApellidos() + "')";
            } else {
                str = "INSERT INTO Alumno (Nombre, Apellidos) VALUES ("
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

    static public void delDoctor(int id) throws SQLException {
        Connection conexion = ConexionBD.getConexion();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conexion.prepareStatement("DELETE FROM Doctor WHERE PK_ID = " + id + "");

            ps.executeUpdate();

        } catch (SQLException e) {
            throw e;
        } finally {
            if(rs != null) rs.close();
            if(ps != null) ps.close();
            if(conexion != null) conexion.close();
        }
    }

    static public void updateDoctor(Doctor consulta) throws SQLException {
        Connection conexion = ConexionBD.getConexion();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String str = "UPDATE Doctor SET Nombre = '" + consulta.getNombre() + "'"
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

