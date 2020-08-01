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
import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JOptionPane;

// Referenced classes of package sqlexec:
//            ConectarBanco
public class Control {

    public Control() {
        bd = new ConectarBanco();
        conn = bd.conectar();
    }

    public ResultSet executarSql2(String query) throws SQLException {
        ResultSet rs;
        Statement st = conn.createStatement();
        rs = st.executeQuery(query);
        return rs;
    }

    public boolean inserirSql(ArrayList queries) {
        boolean tudoOk = false;
        try {
            Statement st = conn.createStatement();
            String query;
            for (Iterator iterator = queries.iterator(); iterator.hasNext(); st.addBatch(query)) {
                query = (String) iterator.next();
            }

            st.executeBatch();
            tudoOk = true;
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, (new StringBuilder()).append("Erro: ").append(e.getMessage()).toString(), "Aviso do sistema", 0);
            System.exit(0);
        }
        return tudoOk;
    }

    ConectarBanco bd;
    Connection conn;
    PreparedStatement pstm;
}
