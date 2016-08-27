/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tads3.agenda;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fernando.jpaula2
 */
public class Agenda extends ConexaoBD {

    private static Scanner entrada = new Scanner(System.in);

    public static void incluir() {

        System.out.print("Digite o nome completo do usuário: ");
        String nome = entrada.nextLine();

        System.out.print("Digite a data de nascimento: ");
        String strDataNasc = entrada.nextLine();

        System.out.print("Digite seu e-mail: ");
        String email = entrada.nextLine();

        System.out.print("Digite seu telefone: ");
        String telefone = entrada.nextLine();

        //1) Abrir conexão
        PreparedStatement stmt = null;
        Connection conn = null;

        String sql = "INSERT INTO TB_CONTATO (NM CONTATO, DT_NASCIMENTO, VL_TELEFONE, VL_EMAIL, DT_CADASTRO)"
                + "VALUES(?,?,?,?,?)";
        try {
            conn = obterConexao();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, sql);

            DateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
            Date dataNasc = null;
            try {
                dataNasc = formatador.parse(strDataNasc);

            } catch (ParseException ex) {
                System.out.println("Data de nascimento inválida! ");
            }
            stmt.setDate(2, new java.sql.Date(dataNasc.getTime()));
            stmt.setString(3, telefone);
            stmt.setString(4, email);
            stmt.setDate(5, new java.sql.Date(System.currentTimeMillis()));

            //2) Executar SQL
            stmt.executeUpdate();
            System.out.println("");

        } catch (SQLException e) {
            System.out.println("Não foi possível executar. Erro: "+e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Classe não encontrada, tente novamente amanhã. Erro: "+e.getMessage());
        }
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                System.out.println("Erro ao fechar stmt. Erro: "+e.getMessage());
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                System.out.println("Erro ao fechar conn. Erro: "+e.getMessage());
            }

        }

        //3) Fechar conexão
    }

    public static void main(String[] args) {
        Agenda instancia = new Agenda();
        Scanner entrada = new Scanner(System.in);
        do {
            System.out.println("**** DIGITE UMA OPÇÃO ****");
            System.out.println("(1) Listar contatos");
            System.out.println("(2) Incluir novo contato");
            System.out.println("(9) Sair");
            System.out.println("Opção");

            String strOpcao = entrada.nextLine();
            int opcao = Integer.parseInt(strOpcao);
            switch (opcao) {
                case 1:
                    break;
                case 2:
                    incluir();
                    break;
                case 9:
                    System.exit(0);
                    break;
            }
        } while (true);
    }
}
