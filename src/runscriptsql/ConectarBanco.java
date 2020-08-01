/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package runscriptsql;

/**
 *
 * @author raul_
 */
// Decompiled by DJ v3.5.5.77 Copyright 2003 Atanas Neshkov  Date: 01/08/2020 18:56:42
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   ConectarBanco.java
import java.io.*;
import java.sql.*;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class ConectarBanco {

    public ConectarBanco() {
        try {
            lerDadosAcesso();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public Connection conectar() {
        String url = (new StringBuilder()).append("jdbc:firebirdsql:").append(realIp).append("/").append(realPorta).append(":").append(realNomeBanco).append("?encoding=ISO8859_1").toString();
        try {
            Class.forName("org.firebirdsql.jdbc.FBDriver").newInstance();
            conn = DriverManager.getConnection(url, realUsuario, realSenha);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        } catch (InstantiationException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public boolean desconectar() {
        boolean isConnected = false;
        try {
            String url = (new StringBuilder()).append("jdbc:firebirdsql:").append(realIp).append("/").append(realPorta).append(":").append(realNomeBanco).toString();
            Class.forName("org.firebirdsql.jdbc.FBDriver").newInstance();
            conn = DriverManager.getConnection(url, realUsuario, realSenha);
            conn.close();
            isConnected = true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            isConnected = false;
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
            isConnected = false;
        } catch (InstantiationException e) {
            System.out.println(e.getMessage());
            isConnected = false;
        } catch (IllegalAccessException e) {
            System.out.println(e.getMessage());
            isConnected = false;
        }
        return isConnected;
    }

    public ResultSet executar(String query) throws SQLException {
        ResultSet rs;
        Statement st = conn.createStatement();
        rs = st.executeQuery(query);
        return rs;
    }

    public int inserir(String query) {
        int result = -1;
        try {
            Statement st = conn.createStatement();
            result = st.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    private void lerDadosAcesso()
            throws Exception {
        File f = new File((new StringBuilder()).append(System.getProperty("user.dir")).append(System.getProperty("file.separator")).append("Lib").append(System.getProperty("file.separator")).append("acesso.properties").toString());
        if (f.exists()) {
            Properties arquivoAuxiliar = new Properties();
            arquivoAuxiliar.load(new FileInputStream(new File((new StringBuilder()).append(System.getProperty("user.dir")).append(System.getProperty("file.separator")).append("Lib").append(System.getProperty("file.separator")).append("acesso.properties").toString())));
            realIp = arquivoAuxiliar.getProperty("RealIp");
            realNomeBanco = arquivoAuxiliar.getProperty("RealNomeBanco");
            realPorta = arquivoAuxiliar.getProperty("RealPorta");
            realUsuario = arquivoAuxiliar.getProperty("RealUsuario");
            realSenha = arquivoAuxiliar.getProperty("RealSenha");
        } else {
            JOptionPane.showMessageDialog(null, (new StringBuilder()).append("Erro, arquivo n\343o encontrado: ").append(f.getPath()).toString(), "Aviso do sistema", 1);
            System.exit(0);
        }
    }

    private Connection conn;
    private String realIp;
    private String realPorta;
    private String realNomeBanco;
    private String realUsuario;
    private String realSenha;
}
