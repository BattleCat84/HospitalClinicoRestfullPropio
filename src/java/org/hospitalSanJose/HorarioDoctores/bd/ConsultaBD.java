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
import org.hospitalSanJose.HorarioDoctores.pojos.Consulta;
import org.hospitalSanJose.HorarioDoctores.pojos.Doctor;

/**
 *
 * @author Migueli Ramos
 */
public class ConsultaBD {
    static public List<Consulta> getAllConsulta() throws Exception {
        
        List<Consulta> consultas = new ArrayList<Consulta>();
        
        Connection conexion = ConexionBD.getConexion();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            ps = conexion.prepareStatement("SELECT * FROM ciclo ORDER BY nombre ASC, abreviatura ASC");
            rs = ps.executeQuery();
            
            while (rs.next()) {
                Consulta consulta = new Consulta();

                consulta.setPK_ID(rs.getInt("PK_ID"));
                consulta.setNombre(rs.getString("Nombre"));
                consulta.setAbreviatura(rs.getString("Abreviatura"));

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
          static public Consulta getConsulta(int id) throws SQLException {
       
        Connection conexion = ConexionBD.getConexion();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            ps = conexion.prepareStatement("SELECT * FROM Consulta WHERE PK_ID = " + id);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                Consulta consulta = new Consulta();

                consulta.setPK_ID(rs.getInt("PK_ID"));
                consulta.setNombre(rs.getString("Nombre"));
                consulta.setAbreviatura(rs.getString("Abreviatura"));

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

    static public void addConsulta(Consulta consulta) throws SQLException {

        Connection conexion = ConexionBD.getConexion();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String str;
            if (consulta.getPK_ID() != -1) {
                str = "INSERT INTO Consulta (PK_ID, Nombre, Abreviatura) VALUES ("
                        + consulta.getPK_ID() + ",'" + consulta.getNombre() + "','" + consulta.getAbreviatura()+ "')";
            } else {
                str = "INSERT INTO Consulta (Nombre, Abreviatura) VALUES ("
                        + "'" + consulta.getNombre() + "','" + consulta.getAbreviatura()+ "')";
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

    static public void laConsulta(int id) throws SQLException {
        Connection conexion = ConexionBD.getConexion();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conexion.prepareStatement("DELETE FROM Consulta WHERE PK_ID = " + id + "");

            ps.executeUpdate();

        } catch (SQLException e) {
            throw e;
        } finally {
            if(rs != null) rs.close();
            if(ps != null) ps.close();
            if(conexion != null) conexion.close();
        }
    }

    static public void updateConsulta(Consulta consulta) throws SQLException {
        Connection conexion = ConexionBD.getConexion();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String str = "UPDATE Consulta SET Nombre = '" + consulta.getNombre() + "'"
                    + ", Abreviatura = '" + consulta.getAbreviatura()+ "'"
                    + " WHERE PK_ID = " + consulta.getPK_ID();
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
    

