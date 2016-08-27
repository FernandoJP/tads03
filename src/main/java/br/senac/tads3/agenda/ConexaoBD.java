/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tads3.agenda;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author fernando.jpaula2
 */
public class ConexaoBD {

    protected static Connection obterConexao() throws SQLException, ClassNotFoundException {
        Connection conn = null;

        //Passo 1: registrar o driver JDBC
        Class.forName("org.apache.derby.jdbc.ClientDataSource");

        //Passo 2: abrir conexão
        conn = DriverManager.getConnection(
                "jdbc:derby://localhost:1527/agendabd;SecurityMechanism=3",
                "app", //usuário BD
                "app"); //senha BD
        return conn;
    }
}
