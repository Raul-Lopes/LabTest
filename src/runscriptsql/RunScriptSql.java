/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package runscriptsql;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author raul_
 */
public class RunScriptSql {

    
    Control controle;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        if(args == null || args.length == 0)
        {
            Visual tela = new Visual(null, true);
            tela.setVisible(true);
        } else
        {
            java.util.ArrayList sentencaSql = lerArquivo();
            Control controle = new Control();
            controle.inserirSql(sentencaSql);
            System.out.println("Ok!");
        }        
    }
    


    private void run()
    {
        ArrayList sentencaSql = lerArquivo();
        controle = new Control();
        boolean resultado = controle.inserirSql(sentencaSql);
    }

    public static ArrayList lerArquivo()
    {
        ArrayList sql = new ArrayList();
        String arquivo;
        File sai;
        arquivo = "";
        sai = new File((new StringBuilder()).append(System.getProperty("user.dir")).append(System.getProperty("file.separator")).append("sql.txt").toString());
        if(!sai.exists() || !sai.canRead() || sai.length() <= 0L)
            return sql;
        BufferedReader entrada;
        FileReader leitura;
        Throwable throwable;
        Exception exception;
        leitura = new FileReader(sai);
        throwable = null;
        try
        {
            entrada = new BufferedReader(leitura);
            do
            {
                String linhaArquivo;
                if((linhaArquivo = entrada.readLine()) == null)
                    break;
                if(!linhaArquivo.isEmpty())
                    arquivo = (new StringBuilder()).append(arquivo).append(linhaArquivo).append(" ").toString();
            } while(true);
        }
        catch(Throwable throwable2)
        {
            throwable = throwable2;
            throw throwable2;
        }
        finally
        {
            if(leitura == null) goto _L0; else goto _L0
        }
        if(leitura != null)
            if(throwable != null)
                try
                {
                    leitura.close();
                }
                catch(Throwable throwable1)
                {
                    throwable.addSuppressed(throwable1);
                }
            else
                leitura.close();
        break MISSING_BLOCK_LABEL_235;
        if(throwable != null)
            try
            {
                leitura.close();
            }
            catch(Throwable throwable3)
            {
                throwable.addSuppressed(throwable3);
            }
        else
            leitura.close();
        throw exception;
        entrada.close();
        arquivo = arquivo.replace("\n", "");
        arquivo = arquivo.replace("\t", "");
        StringTokenizer tokenizer = new StringTokenizer(arquivo, ";");
        do
        {
            if(!tokenizer.hasMoreTokens())
                break;
            String sentenca = tokenizer.nextToken().trim();
            if(sentenca != null && !sentenca.isEmpty())
                sql.add(sentenca);
        } while(true);
        break MISSING_BLOCK_LABEL_373;
        FileNotFoundException ex;
        ex;
        JOptionPane.showMessageDialog(null, (new StringBuilder()).append("Erro: ").append(ex.getMessage()).toString(), "Aviso do sistema", 0);
        break MISSING_BLOCK_LABEL_373;
        ex;
        JOptionPane.showMessageDialog(null, (new StringBuilder()).append("Erro: ").append(ex.getMessage()).toString(), "Aviso do sistema", 0);
        return sql;
    }



}