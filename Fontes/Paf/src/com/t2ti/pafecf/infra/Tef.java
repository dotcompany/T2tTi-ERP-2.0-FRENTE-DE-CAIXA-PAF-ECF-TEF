/**
 * <p>Title: T2TiPDV</p>
 *
 * <p>Description: Classe de controle do TEF</p>
 *
 * <p>The MIT License</p>
 *
 * <p>Copyright: Copyright (C) 2013 T2Ti.COM</p>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 * The author may be contacted at: alberteije@gmail.com</p>
 *
 * @author T2Ti.COM
 * @version 1.0
 */
package com.t2ti.pafecf.infra;

import com.t2ti.pafecf.view.Caixa;
import com.t2ti.pafecf.view.Mensagem;
import jACBr.ACBrException;
import java.io.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import nlink.win32.DllClass;
import nlink.win32.DllMethod;
import nlink.win32.NLink;

public class Tef {

    @DllClass
    public interface User32 {

        @DllMethod
        int BlockInput(boolean fBlock);
    }
    //Variáveis de controle
    public static String NSU, nomeRede, data, hora, identificacao, valor;
    public static boolean relatorioGerencial;
    private static boolean transacaoAprovada, retornoInsPosSts = false;
    //private static Integer contaLinhaEOF;
    private static String conteudoArquivo, linhaArquivo, campoArquivo, linha;
    //Arquivos de controle
    public static File arquivoIntPos001 = new File(System.getProperty("user.dir") + "\\intpos.001");
    public static File arquivoImprimeTxt = new File(System.getProperty("user.dir") + "\\imprime.txt");
    public static File arquivoTefTxt = new File(System.getProperty("user.dir") + "\\tef.txt");
    public static File arquivoPendenteTxt = new File(System.getProperty("user.dir") + "\\pendente.txt");
    //Arquivos para comunicação com o GP
    public static File reqIntPos001 = new File("c:\\tef_dial\\req\\intpos.001");
    public static File respAtivo001 = new File("c:\\tef_dial\\resp\\ativo.001");
    public static File respIntPosSts = new File("c:\\tef_dial\\resp\\intpos.sts");
    public static File respIntPos001 = new File("c:\\tef_dial\\resp\\intpos.001");
    static NumberFormat formatter = new DecimalFormat("#,###,##0.00");
    static NumberFormat formataTef = new DecimalFormat("0.00");

    /**
     * Realiza a transação TEF.
     *
     * @param pIdentificacao identificação da transação
     * @param pNumeroCupom número do cupom fiscal - COO
     * @param pValorPago valor da transação
     * @param pNumeroTransacao número da transação
     * @return 0 = GP INATIVO / 1 = OK / -1 = FAIL
     */
    public static Integer realizaTransacao(String pIdentificacao, String pNumeroCupom, String pValorPago, String pNumeroTransacao) {

        Integer result;
        pValorPago = ajustaValorCartao(pValorPago);
        FileWriter gravar;

        if (respIntPosSts.exists()) {
            respIntPosSts.delete();
        }
        if (respIntPos001.exists()) {
            respIntPos001.delete();
        }
        result = 0;

        if (verificaGerenciadorPadrao(true)) {
            try {
                //Formata arquivo INTPOS.001 para solicitar a transação TEF
                conteudoArquivo = "";
                conteudoArquivo = "000-000 = CRT" + (char) 13 + (char) 10
                        + "001-000 = " + pIdentificacao + (char) 13 + (char) 10
                        + "002-000 = " + pNumeroCupom + (char) 13 + (char) 10
                        + "003-000 = " + pValorPago + (char) 13 + (char) 10
                        + "999-999 = 0";

                gravar = new FileWriter(arquivoIntPos001);
                PrintWriter saida = new PrintWriter(gravar);
                saida.println(conteudoArquivo);
                saida.close();
                gravar.close();

                copyFile(arquivoIntPos001, reqIntPos001);
                arquivoIntPos001.delete();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (new File(System.getProperty("user.dir") + "\\imprime" + pNumeroTransacao + ".txt").exists()) {
                new File(System.getProperty("user.dir") + "\\imprime" + pNumeroTransacao + ".txt").delete();
            }

            result = -2;

            try {
                Caixa.setTextoMensagem("Aguardando resposta da operadora de cartão");
                for (int tentativa = 1; tentativa <= 7; tentativa++) {
                    // Verifica se o Gerenciador Padrão recebeu o arquivo INTPOS.001
                    if (respIntPosSts.exists()) {
                        linhaArquivo = "";
                        linha = "";
                        retornoInsPosSts = true;
                        break;
                    } else {
                        Thread.sleep(1000);
                        //Caso o arquivo INTPOS.sts não retorne em 7 segundos exibe mensagem para o operador
                        if (tentativa == 7) {
                            retornoInsPosSts = false;
                            if (reqIntPos001.exists()) {
                                reqIntPos001.delete();
                                JOptionPane.showMessageDialog(null, "Gerenciador Padrão não está ATIVO!", "Mensagem para o operador", 2);
                                result = 0;
                                break;
                            }
                        }
                    }
                } //Fim do for tentativa
                Caixa.setTextoMensagem("");

                if (retornoInsPosSts) {
                    while (result == -2) {
                        if (respIntPos001.exists()) {

                            result = lerRespIntPos001(pIdentificacao);
                        } //Fim File exists
                        Thread.sleep(1000);
                    } //Fim while result = -2
                }// fim exiteintpossts


            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }


            //Se tudo ocorrer bem cria o arquivo Pendente.txt
            if (result == 1) {
                result = 1;
                // gera um arquivo pendente, pois falta a confirmação da transação(CNC);
                try {
                    gravar = new FileWriter(arquivoPendenteTxt);
                    PrintWriter saida = new PrintWriter(gravar);
                    saida.println(Tef.NSU.toString());
                    saida.close();
                    gravar.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
        return result;
    }

    /**
     * Ajusta o valor para enviar para o GP.
     *
     * @param pValor valor do pagamento
     * @return valor formatado (Exemplo: 10000 para 100,00
     */
    public static String ajustaValorCartao(String pValor) {
        String cValorCartao = pValor.substring(0, pValor.indexOf(","))
                + pValor.substring(pValor.indexOf(",") + 1, pValor.length());

        return cValorCartao;
    }

    /**
     * Cópia de arquivos por Stream.
     *
     * @param pOrigem arquivo de origem.
     * @param pDestino arquivo de destino.
     * @return nenhum.
     */
    public static void copyFile(File pOrigem, File pDestino) throws IOException {
        InputStream entradaCopy = new FileInputStream(pOrigem);
        OutputStream saidaCopy = new FileOutputStream(pDestino);

        // Transfere bytes da entrada para saida
        byte[] buf = new byte[1024];
        int len;
        while ((len = entradaCopy.read(buf)) > 0) {
            saidaCopy.write(buf, 0, len);
        }
        entradaCopy.close();
        saidaCopy.close();
    }

    /**
     * Cria uma identificação inicial para a transação.
     *
     * @return String com o numero da identificação no formato (DDMMHHMMSS)
     */
    public static String novaIdentificacao() {
        try {
            Thread.sleep(1000);
            SimpleDateFormat formHora = new SimpleDateFormat("ddMMHHmmss");
            String identificacao = formHora.format(new Date());
            return identificacao;
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        return "000";
    }

    /**
     * Abre o ADM do TEF
     *
     * @return true = Ativo | false = Inativo.
     */
    public static Integer abrirADM() {

        Integer result, numeroLinhas;
        String pIdentificacao = "";
        pIdentificacao = novaIdentificacao();
        Tef.NSU = "";
        if (respIntPosSts.exists()) {
            respIntPosSts.delete();
        }
        if (arquivoIntPos001.exists()) {
            arquivoIntPos001.delete();
        }
        result = 0;
        //if (verificaGerenciadorPadrao()) {
        try {
            conteudoArquivo = "000-000 = ADM" + (char) 13 + (char) 10
                    + "001-000 = " + pIdentificacao + (char) 13 + (char) 10
                    + "999-999 = 0";

            FileWriter gravar = new FileWriter(arquivoIntPos001);
            PrintWriter saida = new PrintWriter(gravar);
            saida.println(conteudoArquivo);
            saida.close();
            gravar.close();

            copyFile(arquivoIntPos001, reqIntPos001);
            arquivoIntPos001.delete();
        } catch (IOException e) {
            e.printStackTrace();
        }

        result = -2;
        try {
            for (int tentativa = 1; tentativa <= 7; tentativa++) {

                // Verifica se o Gerenciador Padrão recebeu o arquivo INTPOS.001
                if (respIntPosSts.exists()) {
                    linhaArquivo = "";
                    linha = "";
                    retornoInsPosSts = true;
                    break;
                } else {
                    Thread.sleep(1000);
                    //Caso o arquivo INTPOS.sts não retorne em 7 segundos exibe mensagem para o operador
                    if (tentativa == 7) {
                        retornoInsPosSts = false;
                        if (reqIntPos001.exists()) {
                            reqIntPos001.delete();
                            JOptionPane.showMessageDialog(null, "Gerenciador Padrão não está ATIVO!", "Mensagem para o operador", 2);
                            result = 0;
                            break;
                        }
                    }
                }

            } //Fim do for tentativa
            if (retornoInsPosSts) {
                while (result == -2) {
                    if (respIntPos001.exists()) {
                        result = lerRespIntPos001(pIdentificacao);
                    } else {
                        Thread.sleep(1000);
                    }
                    //Fim File exists
                } //Fim while result = -2
            } //retornouintpos

        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

        //Se tudo ocorrer bem cria o arquivo PENDENTE.txt
        if (result == 1) {
            result = 1;
            try {
                FileWriter gravar = new FileWriter(arquivoPendenteTxt);
                PrintWriter saida = new PrintWriter(gravar);
                saida.println(Tef.NSU.toString());
                saida.close();
                gravar.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //}
        return result;
    }

    /**
     * Verifica se o GP está ativo.
     *
     * @return true = Ativo | false = Inativo.
     */
    public static boolean verificaGerenciadorPadrao(boolean emiteMensagem) {
        boolean result = false;

        if (arquivoIntPos001.exists()) {
            arquivoIntPos001.delete();
        }
        if (respIntPosSts.exists()) {
            respIntPosSts.delete();
        }
        try {
            conteudoArquivo = "000-000 = ATV" + (char) 13 + (char) 10
                    + "001-000 = " + novaIdentificacao() + (char) 13 + (char) 10
                    + "999-999 = 0";

            FileWriter gravar = new FileWriter(arquivoIntPos001);
            PrintWriter saida = new PrintWriter(gravar);
            saida.println(conteudoArquivo);
            saida.close();
            gravar.close();

            copyFile(arquivoIntPos001, reqIntPos001);
            arquivoIntPos001.delete();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //tenta sete vezes
        for (int tentativa = 1; tentativa <= 7; tentativa++) {
            try {
                if ((respAtivo001.exists()) || (respIntPosSts.exists())) {
                    //GP ativo
                    respAtivo001.delete();
                    respIntPosSts.delete();
                    result = true;
                    break;
                }
                Thread.sleep(1000);
                if (tentativa == 7) {
                    reqIntPos001.delete();
                    if (emiteMensagem) {
                        JOptionPane.showMessageDialog(null, "Gerenciador Padrão não está ATIVO!", "Mensagem para o operador", 2);
                    }
                    result = false;
                    break;
                }
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        reqIntPos001.delete();
        return result;
    }

    /**
     * Realiza a impressão da Transação TEF.
     *
     * @param pFormaPagamento forma de pagamento
     * @param pValorPago valor da forma de pagamento
     * @param pNumeroCupom número do cupom fiscal - COO
     * @param pIdentificacao identificação da transação
     * @param pNumeroTransacao número da transação
     * @return true ou false
     */
    public static boolean imprimeTransacao(String pFormaPagamento, String pValorPago, String pNumeroCupom, String pIdentificacao, String pNumeroTransacao) {
        boolean result = false;
        //boolean semPapel = false;
        User32 user32 = NLink.create(User32.class);

        try {
            //result = true;
            /*
             * Criação do arquivo imprimieXXXXXXXX.TXT. Vamos utilizar esse arquivo caso ocorra
             * queda de energia para cancelar a transação.
             */
            if (new File(System.getProperty("user.dir") + "\\imprime" + pNumeroTransacao + ".txt").exists()) {
                user32.BlockInput(true);

                try {
                    //if (Caixa.aCBrECF.getPoucoPapel()) {
                    //semPapel = true;
                    //} else {
                    if (!relatorioGerencial) {
                        Caixa.aCBrECF.abreCupomVinculado(pNumeroCupom, pFormaPagamento, Double.valueOf(pValorPago));
                    } else {
                        Caixa.aCBrECF.abreRelatorioGerencial(1);
                    }
                    //}
                } catch (Exception e) {
                    //if (Caixa.aCBrECF.getPoucoPapel()) {
                    //semPapel = true;
                    //}
                }

                FileReader ler = new FileReader(new File(System.getProperty("user.dir") + "\\imprime" + pNumeroTransacao + ".txt"));
                BufferedReader entrada = new BufferedReader(ler);
                linha = "";
                boolean estado;
                int totalLinhas = 0;
                while ((!result) && ((linha = entrada.readLine()) != null)) {
                    estado = false;
                    totalLinhas += 1;
                    if (totalLinhas % 200 == 0) {
                        try {
                            if (Caixa.aCBrECF.getEstado() == Caixa.RELATORIO) {
                                Caixa.aCBrECF.fechaRelatorio();
                            }
                            Caixa.aCBrECF.abreRelatorioGerencial(1);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    while (!estado) {
                        try {
                            //if (semPapel) {
                            //estado = false;
                            //} else {
                            if (!relatorioGerencial) {
                                Caixa.aCBrECF.linhaCupomVinculado(linha + (char) 13 + (char) 10);
                            } else {
                                Caixa.aCBrECF.linhaRelatorioGerencial(linha + (char) 13 + (char) 10, 0);
                            }
                            estado = true;
                            //}
                        } catch (Exception e) {
                            estado = false;
                        }
                        if (!estado) {
                            user32.BlockInput(false);

                            String[] opcoes = {"Sim", "Não"};
                            String mensagem = "Impressora não responde. Tentar imprimir novamente?";
                            //if (semPapel) {
                            //mensagem = "Impressora com pouco ou sem papel. Tentar imprimir novamente?";
                            //}
                            int escolha = JOptionPane.showOptionDialog(null, mensagem, "Aviso do Sistema", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, opcoes, null);
                            if (escolha == JOptionPane.YES_OPTION) {
                                user32.BlockInput(true); //bloquear teclado;
                                if (impressoraLigada()) {
                                    entrada.close();
                                    ler.close();
                                    if (Caixa.aCBrECF.getEstado() == Caixa.RELATORIO) {// && (!Caixa.aCBrECF.getPoucoPapel())) {
                                        Caixa.aCBrECF.fechaRelatorio();
                                    }
                                    //Caixa.aCBrECF.leituraX();
                                    relatorioGerencial = true;
                                    result = imprimeTransacao(pFormaPagamento, pValorPago, pNumeroCupom, pIdentificacao, pNumeroTransacao);
                                    relatorioGerencial = false;
                                    break;
                                }
                            } else {
                                user32.BlockInput(false);//desbloqueando mouse e teclado para fechar o sistema
                                return false;
                            }
                        }
                    }
                }
                entrada.close();
                ler.close();
                result = true;
                if (Caixa.aCBrECF.getEstado() == Caixa.RELATORIO) {
                    Caixa.aCBrECF.fechaRelatorio();
                }
            } else {
                result = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } catch (Throwable t) {
            t.printStackTrace();
            JOptionPane.showMessageDialog(null, t.getMessage(), "Erro do Sistema", JOptionPane.ERROR_MESSAGE);
            return false;
        } finally {
            user32.BlockInput(false);//desbloqueando teclado e mouse
        }
        return result;
    }

    public static boolean impressoraLigada() {
        try {
            Caixa.aCBrECF.getEstado();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Confirma a Transação TEF.
     *
     * @param pNumeroTransacao número da transação
     * @return NSU
     */
    public static String confirmaTransacao(String pNumeroTransacao) {

        while (!verificaGerenciadorPadrao(true)) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }

        linhaArquivo = "";
        conteudoArquivo = "";
        File arquivo;
        if (respIntPosSts.exists()) {
            respIntPosSts.delete();
        }

        if (!pNumeroTransacao.equals("")) {
            arquivo = new File(System.getProperty("user.dir") + "\\intpos" + pNumeroTransacao + ".001");
        } else {
            arquivo = respIntPos001;
        }

        if (arquivo.exists()) {

            try {

                FileReader ler = new FileReader(arquivo);
                BufferedReader entrada = new BufferedReader(ler);

                while ((linhaArquivo = entrada.readLine()) != null) {
                    if ((linhaArquivo.substring(0, 3).equals("001"))
                            || (linhaArquivo.substring(0, 3).equals("002"))
                            || (linhaArquivo.substring(0, 3).equals("010"))
                            || (linhaArquivo.substring(0, 3).equals("012"))
                            || (linhaArquivo.substring(0, 3).equals("027"))) {
                        conteudoArquivo = conteudoArquivo + linhaArquivo + (char) 13 + (char) 10;
                    }
                    if (linhaArquivo.substring(0, 3).equals("999")) {
                        conteudoArquivo = conteudoArquivo + linhaArquivo;
                    }
                    if (linhaArquivo.substring(0, 3).equals("003")) {
                        valor = linhaArquivo.substring(10, linhaArquivo.length());
                    }
                    if (linhaArquivo.substring(0, 3).equals("012")) {
                        Tef.NSU = linhaArquivo.substring(10, linhaArquivo.length());
                    }


                } //Fim do while not EOF

                entrada.close();
                ler.close();

                // Cria o novo INTPOS.001 da Confirmacão
                conteudoArquivo = "000-000 = CNF" + (char) 13 + (char) 10 + conteudoArquivo;
                FileWriter gravar = new FileWriter(arquivoIntPos001);
                PrintWriter saida = new PrintWriter(gravar);
                saida.println(conteudoArquivo);
                saida.close();
                gravar.close();
                copyFile(arquivoIntPos001, reqIntPos001);

                Thread.sleep(1000);
                int cont = 0;
                while (!respIntPosSts.exists()) {
                    Thread.sleep(1000);
                    cont++;
                    if (cont == 7) {
                        JOptionPane.showMessageDialog(null, "Gerenciador Padrão não está Ativo e será ativado automaticamente!", "Mensagem para o operador", JOptionPane.INFORMATION_MESSAGE);
                        cont = 0;
                        Runtime.getRuntime().exec("c:\\tef_dial\\tef_dial.exe");
                        Thread.sleep(8000);
                        if (!reqIntPos001.exists()) {
                            copyFile(arquivoIntPos001, reqIntPos001);
                        }
                    }
                }
                arquivoIntPos001.delete();
                respIntPosSts.delete();

                //Gera um arquivo TEF informando que foi confirmado a impressão do arquivo!
                // Informações necessarias para cancelamento automatico da transação
                String linhaTef = pNumeroTransacao + ";"
                        + nomeRede + ";"
                        + data + ";"
                        + hora + ";"
                        + valor + ";";


                if (!arquivoTefTxt.exists()) {
                    gravar = new FileWriter(arquivoTefTxt);
                    saida = new PrintWriter(gravar);
                    saida.println(linhaTef);
                    saida.close();
                    gravar.close();
                } else {
                    Biblioteca.addLineFromFile(arquivoTefTxt.getAbsolutePath(), linhaTef);
                }


                if (respIntPosSts.exists()) {
                    respIntPosSts.delete();
                }
                if (respIntPos001.exists()) {
                    respIntPos001.delete();
                }
                if (arquivoPendenteTxt.exists()) {
                    arquivoPendenteTxt.delete();
                }

                return Tef.NSU;
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /*
     * Não Confirma a Transação ou a impressao TEF.
     * @param pNumeroTransacao número da transação
     * @return NSU
     */
    public static String naoConfirmaTransacao(String pNumeroTransacao) {
        while (!verificaGerenciadorPadrao(true)) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        linhaArquivo = "";
        conteudoArquivo = "";
        File arquivo;
        String rede = "", cNSU = "", valor = "";
        if (respIntPosSts.exists()) {
            respIntPosSts.delete();
        }

        if (!pNumeroTransacao.equals("")) {
            arquivo = new File(System.getProperty("user.dir") + "\\intpos" + pNumeroTransacao + ".001");
        } else {
            arquivo = respIntPos001;
        }
        if (arquivo.exists()) {

            try {
                FileReader ler = new FileReader(arquivo);
                BufferedReader entrada = new BufferedReader(ler);

                while ((linhaArquivo = entrada.readLine()) != null) {
                    if ((linhaArquivo.substring(0, 3).equals("001"))
                            || (linhaArquivo.substring(0, 3).equals("002"))
                            || (linhaArquivo.substring(0, 3).equals("010"))
                            || (linhaArquivo.substring(0, 3).equals("012"))
                            || (linhaArquivo.substring(0, 3).equals("027"))) {
                        conteudoArquivo = conteudoArquivo + linhaArquivo + (char) 13 + (char) 10;
                    }
                    if (linhaArquivo.substring(0, 3).equals("003")) {
                        valor = linhaArquivo.substring(10, linhaArquivo.length());
                    }
                    ;
                    if (linhaArquivo.substring(0, 3).equals("012")) {
                        cNSU = linhaArquivo.substring(10, linhaArquivo.length());
                    }
                    ;
                    if (linhaArquivo.substring(0, 3).equals("010")) {
                        rede = linhaArquivo.substring(10, linhaArquivo.length());
                    }
                    ;
                    if (linhaArquivo.substring(0, 3).equals("999")) {
                        conteudoArquivo = conteudoArquivo + linhaArquivo;
                    }
                } //Fim do while not EOF

                entrada.close();
                ler.close();

                // Cria o novo INTPOS.001 da Confirmacão
                conteudoArquivo = "000-000 = NCN" + (char) 13 + (char) 10 + conteudoArquivo;
                FileWriter gravar = new FileWriter(arquivoIntPos001);
                PrintWriter saida = new PrintWriter(gravar);
                saida.println(conteudoArquivo);
                saida.close();
                gravar.close();

                copyFile(arquivoIntPos001, reqIntPos001);

                int cont = 0;
                while (!respIntPosSts.exists()) {
                    Thread.sleep(1000);
                    cont++;
                    if (cont == 7) {
                        if (!reqIntPos001.exists()) {
                            copyFile(arquivoIntPos001, reqIntPos001);
                        } else {
                            JOptionPane.showMessageDialog(null, "Gerenciador Padrão não está Ativo e será ativado automaticamente!", "Mensagem para o operador", JOptionPane.INFORMATION_MESSAGE);
                            Runtime.getRuntime().exec("c:\\tef_dial\\tef_dial.exe");
                            Thread.sleep(8000);
                        }
                        cont = 0;
                    }
                }
                arquivoIntPos001.delete();
                arquivo.delete();
                respIntPosSts.delete();
                //Exclui o arquivo pedente.txt
                if (arquivoPendenteTxt.exists()) {
                    arquivoPendenteTxt.delete();
                }
                if (!valor.isEmpty()) {
                    Double val = Double.parseDouble(valor) / 100;
                    valor = formatter.format(val);
                }

                String textomsg = "";
                if (!rede.toString().equals("")) {
                    textomsg += "Rede: " + rede + (char) 13 + (char) 10;
                }
                if (!cNSU.toString().equals("")) {
                    textomsg += "NSU: " + cNSU + (char) 13 + (char) 10;
                }
                if (!valor.toString().equals("0,00")) {
                    textomsg += "Valor: " + valor;
                }

                JOptionPane.showMessageDialog(null, "Última Transação TEF foi cancelada" + (char) 13 + (char) 10 + (char) 13 + (char) 10
                        + textomsg, "Encerrar venda.", JOptionPane.INFORMATION_MESSAGE);

                return cNSU;

            } catch (InterruptedException ex) {
                ex.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

////////////////////////////////////////////////////////////////////////////////
// Função: CancelaTransacaoTEF
// Objetivo: Cancelar uma transação já confirmada
// Parâmetros: String com o número de identificação (NSU)
//             String com o valor da transação
//             String com o valor da transação
//             String com o nome e bandeira (REDE)
//             String com o número do documento
//             String com a data da transação no formato DDMMAAAA
//             String com a hora da transação no formato HHSMMSS
// Retorno: True para OK ou False para não OK
////////////////////////////////////////////////////////////////////////////////
    public static int CancelaTransacaoTEF(String cNSU, String cValor, String cNomeRede, String pIdentificacao, String cData, String cHora) {

        Integer result;
        cValor = ajustaValorCartao(cValor);
        conteudoArquivo = "";
        if (respIntPosSts.exists()) {
            respIntPosSts.delete();
        }
        if (respIntPos001.exists());
        respIntPos001.delete();

        conteudoArquivo = "000-000 = CNC" + (char) 13 + (char) 10
                + "001-000 = " + pIdentificacao + (char) 13 + (char) 10
                + "003-000 = " + cValor + (char) 13 + (char) 10
                + "010-000 = " + cNomeRede + (char) 13 + (char) 10
                + "012-000 = " + cNSU + (char) 13 + (char) 10
                + "022-000 = " + cData + (char) 13 + (char) 10
                + "023-000 = " + cHora + (char) 13 + (char) 10
                + "999-999 = 0";


        try {
            FileWriter gravar;

            gravar = new FileWriter(arquivoIntPos001);
            PrintWriter saida = new PrintWriter(gravar);
            saida.println(conteudoArquivo);
            saida.close();
            gravar.close();
            copyFile(arquivoIntPos001, reqIntPos001);
            arquivoIntPos001.delete();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        result = -2;

        try {
            for (int tentativa = 1; tentativa <= 7; tentativa++) {
                // Verifica se o Gerenciador Padrão recebeu o arquivo INTPOS.001
                if (respIntPosSts.exists()) {
                    linha = "";
                    retornoInsPosSts = true;
                    break;
                } else {
                    Thread.sleep(1000);
                    //Caso o arquivo INTPOS.sts não retorne em 7 segundos exibe mensagem para o operador
                    if (tentativa == 7) {
                        retornoInsPosSts = false;
                        if (reqIntPos001.exists()) {
                            reqIntPos001.delete();
                            JOptionPane.showMessageDialog(null, "Gerenciador Padrão não está ATIVO!", "Mensagem para o operador", 2);
                            result = 0;
                            break;
                        }
                    }
                }
            } //Fim do for tentativa

            if (retornoInsPosSts) {
                while (result == -2) {
                    if (respIntPos001.exists()) {
                        result = lerRespIntPos001(pIdentificacao);
                    } //Fim File exists
                } //Fim while result = -2
            }
            //Cria o arquivo temporário IMPRIME[transacao].txt com a imagem do comprovante.
            if (!linha.equals("")) {
                try {
                    linha = linha + linha;
                    FileWriter gravar = new FileWriter(new File(System.getProperty("user.dir") + "\\imprime" + Tef.NSU.toString() + ".txt"));
                    PrintWriter saida = new PrintWriter(gravar);
                    saida.println(linha);
                    saida.close();
                    gravar.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }


        //Se tudo ocorrer bem cria o arquivo TEF.txt
        if (result == 1) {
            result = 1;

            try {
                FileWriter gravar = new FileWriter(arquivoPendenteTxt);
                PrintWriter saida = new PrintWriter(gravar);
                saida.println(Tef.NSU.toString());
                saida.close();
                gravar.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return result;

    }
////////////////////////////////////////////////////////////////////////////////
// Função: limpaBuffer
// Objetivo: Limpar os arquivos responsaveis pela transacao TEF
// Parâmetros: String com o número de identificação (NSU)
////////////////////////////////////////////////////////////////////////////////

    public static void limpaBuffer(String pNumeroTransacao) {

        if (respIntPosSts.exists()) {
            respIntPosSts.delete();
        }
        if (respIntPos001.exists()) {
            respIntPos001.delete();
        }
        if (new File(System.getProperty("user.dir") + "\\intpos" + pNumeroTransacao + ".001").exists()) {
            new File(System.getProperty("user.dir") + "\\intpos" + pNumeroTransacao + ".001").delete();
        }
        if (new File(System.getProperty("user.dir") + "\\imprime" + pNumeroTransacao + ".txt").exists()) {
            new File(System.getProperty("user.dir") + "\\imprime" + pNumeroTransacao + ".txt").delete();
        }
    }
////////////////////////////////////////////////////////////////////////////////
// Função: limpaArquivosTempos
// Objetivo: Limpar os arquivos temporarios depois de imprimir e confirmar a transação
// Parâmetros: 
////////////////////////////////////////////////////////////////////////////////

    public static void limpaArquivosTemps() {

        if (arquivoTefTxt.exists()) {
            arquivoTefTxt.delete();
        }

        if (arquivoPendenteTxt.exists()) {
            arquivoPendenteTxt.delete();
        }
    }
////////////////////////////////////////////////////////////////////////////////
// Função: cancelaTefPendentes
// Objetivo: cancelar todas as transacoes TEF pendentes
// Parâmetros:
// Retorno: True se tudo ok e false se tiver algum erro
////////////////////////////////////////////////////////////////////////////////

    public static boolean cancelaTefPendentes() throws ACBrException {
        boolean result = false;
        //não confirmando a ultima transação pendente
        String lin = "";

        try {
            if (arquivoPendenteTxt.exists()) {
                FileReader leitura = new FileReader(arquivoPendenteTxt);
                BufferedReader entrada = new BufferedReader(leitura);
                while ((linhaArquivo = entrada.readLine()) != null) {
                    Tef.NSU = linhaArquivo;
                    naoConfirmaTransacao(Tef.NSU);
                }
                entrada.close();
                leitura.close();
                limpaBuffer(Tef.NSU);
                result = true;
            }
            limpaArquivosTemps();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return result;
    }

////////////////////////////////////////////////////////////////////////////////
// Função: verificaTefPendentes
// Objetivo: verifica se a transaçoes nao impressas ou nao confirmadas
// Parâmetros:
// Retorno: True se a pendentek e false se não tiver pendente
////////////////////////////////////////////////////////////////////////////////
    public static boolean verificaTefPendentes() {
        boolean result = false;
        try {
            FileWriter gravar;
            if (arquivoTefTxt.exists()) {
                result = true;
            }

            if (arquivoPendenteTxt.exists()) {
                result = true;
            }

            if (respIntPos001.exists() && (!arquivoPendenteTxt.exists())) {
                NSU = lerIdentificacao();
                gravar = new FileWriter(arquivoPendenteTxt);
                PrintWriter saida = new PrintWriter(gravar);
                saida.println(Tef.NSU.toString());
                saida.close();
                gravar.close();
                result = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    ////////////////////////////////////////////////////////////////////////////////
// Função: executaADM()
// Objetivo: executar a função ADM do TEF.
// Parâmetros:
// Retorno
////////////////////////////////////////////////////////////////////////////////
    public static void executaADM() {

        if (verificaGerenciadorPadrao(true)) {
            switch (Tef.abrirADM()) {
                case 1: // OK
                    Tef.relatorioGerencial = true;
                    if (!Tef.imprimeTransacao("", "", "", "", Tef.NSU)) {
                        try {
                            cancelaTefPendentes();
                        } catch (ACBrException ex) {
                            ex.printStackTrace();
                        }
                        //JOptionPane.showMessageDialog(null, "Devido a um erro no ECF o sistema será finalizado, ", "Aviso do Sistema", JOptionPane.ERROR_MESSAGE);
                        //+ "a venda e as transações TEF serão canceladas " + (char) 13 + (char) 10
                        //+ "quando o sistema reiniciar.", "Aviso do Sistema", JOptionPane.ERROR_MESSAGE);
                        //Tef.NaoConfirmaTransacao(this.ultimoNSU);
                        //System.exit(0);
                    }
                    Tef.relatorioGerencial = false;
                    if (!Tef.NSU.toString().equals("")) {
                        Tef.confirmaTransacao(Tef.NSU);
                    }
                    Tef.limpaBuffer(Tef.NSU);
                    Tef.limpaArquivosTemps();

                    break;
                case -1: //Transação não realizada
                    //JOptionPane.showMessageDialog(rootPane, "Erro na transação com cartão.", "Aviso do Sistema", JOptionPane.ERROR_MESSAGE);
                    break;
                case 0: // GP Inativo
                    JOptionPane.showMessageDialog(null, "Gerenciador padrão inativo.", "Aviso do Sistema", JOptionPane.ERROR_MESSAGE);
                    break;
            }
        }
    }
////////////////////////////////////////////////////////////////////////////////
// Função: lerRespIntPos001
// Objetivo: ler o arquivo respintpos001 e gerar o arquivo para impressão se tiver;
// Parâmetros: pIdentificacao
// Retorno: -1: Transação não Realizada; 0: GP não Ativo; 1: Transação Realizada;
////////////////////////////////////////////////////////////////////////////////

    public static int lerRespIntPos001(String pIdentificacao) {
        int result = -2, numeroLinhas;
        User32 user32 = NLink.create(User32.class);
        try {
            FileReader leitura = new FileReader(respIntPos001);
            BufferedReader entrada = new BufferedReader(leitura);

            while ((linhaArquivo = entrada.readLine()) != null) {
                campoArquivo = linhaArquivo.substring(0, 3);
                switch (Integer.parseInt(campoArquivo)) {
                    case 1: //verifica se o campo de identificação é o mesmo do solicitado
                        if (!linhaArquivo.substring(10, linhaArquivo.length()).equals(pIdentificacao)) {
                            entrada.close();
                            leitura.close();
                            respIntPos001.delete();
                            break;
                        }
                        break;
                    case 9: //verifica se a transação foi aprovada
                        if (linhaArquivo.substring(10, linhaArquivo.length()).equals("0")) {
                            transacaoAprovada = true;
                            result = 1;
                        }
                        if (!linhaArquivo.substring(10, linhaArquivo.length()).equals("0")) {
                            transacaoAprovada = false;
                            result = -1;
                        }
                        break;
                    case 12: {
                        Tef.NSU = linhaArquivo.substring(10, linhaArquivo.length());
                    }
                    break;
                    case 10: {
                        Tef.nomeRede = linhaArquivo.substring(10, linhaArquivo.length());
                    }
                    break;
                    case 22: {
                        Tef.data = linhaArquivo.substring(10, linhaArquivo.length());
                    }
                    break;
                    case 23: {
                        Tef.hora = linhaArquivo.substring(10, linhaArquivo.length());
                    }
                    break;

                    case 28: //verifica se existem linhas para serem impressas
                        if ((Integer.parseInt(linhaArquivo.substring(10, linhaArquivo.length())) != 0) && (transacaoAprovada == true)) {

                            /*
                             O arquivo INTPOS.001 é copiado temporariamente. Isso ocorre para cadas transação.
                             Caso a transação necessite ser cancelada, as informações estarão neste arquivo.
                             */
                            copyFile(respIntPos001, new File(System.getProperty("user.dir") + "\\intpos" + Tef.NSU + ".001"));
                            result = 1;

                            //Armazena o número de linhas para a impressão da via única
                            numeroLinhas = Integer.parseInt(linhaArquivo.substring(10, linhaArquivo.length()));

                            //Formata o arquivo para impressão da via única
                            linha = linha + (char) 13 + (char) 10;

                            String linhas2Via = "" + (char) 13 + (char) 10;
                            ;

                            for (int i = 1; i <= numeroLinhas; i++) {
                                linhaArquivo = entrada.readLine();
                                try {
                                    if (linhaArquivo.substring(0, 3).equals("029")) {
                                        linha = linha + linhaArquivo.substring(11, linhaArquivo.length() - 1) + (char) 13 + (char) 10;
                                        linhas2Via += linhaArquivo.substring(11, linhaArquivo.length() - 1) + (char) 13 + (char) 10;
                                    } else {
                                        if (linhaArquivo.substring(0, 3).equals("028")) {
                                            i = i - 1;
                                        }
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                            }
                            linha = linha + (char) 13 + (char) 10 + (char) 13 + (char) 10
                                    //+ " . . . . . . . . . . . . . . . . . . . . . . . . "
                                    + (char) 13 + (char) 10 + (char) 13 + (char) 10;

                            linha += linhas2Via;
                        }
                        break;
                    case 30: //Se o campo for 030 exibe uma mensagem para o operador
                        user32.BlockInput(true);
                        Mensagem msg = new Mensagem(null, true, true, 1000);
                        //verifica se tem alguma linha para ser impressa
                        //se tiver exibe mensagem se ok, no minimo 5 segundos
                        if ((!linha.equals("")) && (result == 1)) {
                            Caixa.setTextoMensagem(linhaArquivo.substring(10, linhaArquivo.length()));
                            msg.setMensagem(linhaArquivo.substring(10, linhaArquivo.length()));
                            msg.setLocationRelativeTo(null);
                            msg.setVisible(true);
                        } else {
                            //exibe mensagem com OK
                            user32.BlockInput(false);
                            JOptionPane.showMessageDialog(null, linhaArquivo.substring(10, linhaArquivo.length()), "Mensagem para o operador", 2);
                            result = -1;
                        }
                        user32.BlockInput(false);
                        break;
                    /*
                     case 712: //verifica se existem linhas para sereom impressas na via do cliente
                     if ((Integer.parseInt(linhaArquivo.substring(10, linhaArquivo.length())) != 0) && (transacaoAprovada == true)) {

                     //Armazena o número de linhas para a impressão da via do cliente
                     numeroLinhas = Integer.parseInt(linhaArquivo.substring(10, linhaArquivo.length()));

                     //Formata o arquivo para impressão da via do cliente
                     linha = linha + (char) 13 + (char) 10;

                     for (int i = 1; i <= numeroLinhas; i++) {
                     linhaArquivo = entrada.readLine();
                     if (linhaArquivo.substring(0, 3).equals("713")) {
                     linha = linha + linhaArquivo.substring(11, linhaArquivo.length() - 1) + (char) 13 + (char) 10;
                     }
                     }
                     linha = linha + (char) 13 + (char) 10 + (char) 13 + (char) 10
                     + " . . . . . . . . . . . . . . . . . . . . . . . . "
                     + (char) 13 + (char) 10 + (char) 13 + (char) 10;
                     }
                     break;
                     case 714: //Verifica se existem linhas para serem impressas na via do estabelecimento
                     if ((Integer.parseInt(linhaArquivo.substring(10, linhaArquivo.length())) != 0) && (transacaoAprovada == true)) {

                     //Armazena o número de linhas para a impressão da via do estabelecimento
                     numeroLinhas = Integer.parseInt(linhaArquivo.substring(10, linhaArquivo.length()));

                     //Formata o arquivo para impressão da via do estabelecimento
                     linha = linha + (char) 13 + (char) 10;

                     for (int i = 1; i <= numeroLinhas; i++) {
                     linhaArquivo = entrada.readLine();
                     if (linhaArquivo.substring(0, 3).equals("715")) {
                     linha = linha + linhaArquivo.substring(11, linhaArquivo.length() - 1) + (char) 13 + (char) 10;
                     }
                     }
                     linha = linha + (char) 13 + (char) 10 + (char) 13 + (char) 10
                     + " . . . . . . . . . . . . . . . . . . . . . . . . "
                     + (char) 13 + (char) 10 + (char) 13 + (char) 10;
                     }
                     break;*/
                }
                if (linhaArquivo == null) { //Chegou no fim do arquivo
                    break;
                }
            } //Fim while not EOF
            entrada.close();
            leitura.close();
        } catch (IOException e) {
            user32.BlockInput(false);
            //e.printStackTrace();
        }
        //Cria o arquivo temporário IMPRIME[transacao].txt com a imagem do comprovante.
        if (!linha.equals("")) {
            try {
                FileWriter gravar = new FileWriter(new File(System.getProperty("user.dir") + "\\imprime" + Tef.NSU.toString() + ".txt"));
                PrintWriter saida = new PrintWriter(gravar);
                saida.println(linha);
                saida.close();
                gravar.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * Consulta de cheque
     *
     * @param pIdentificacao identificação da transação
     * @param pValorCheque valor do cheque
     * @return 0 = GP INATIVO / 1 = OK / -1 = FAIL
     */
    public static Integer consultaCheqhe(String pIdentificacao, String pValorCheque) {

        Integer result;
        pValorCheque = ajustaValorCartao(pValorCheque);
        FileWriter gravar;

        if (respIntPosSts.exists()) {
            respIntPosSts.delete();
        }
        if (respIntPos001.exists()) {
            respIntPos001.delete();
        }

        result = 0;
        if (verificaGerenciadorPadrao(true)) {
            try {
                //Formata arquivo INTPOS.001 para solicitar a transação consulta cheque
                conteudoArquivo = "";
                conteudoArquivo = "000-000 = CHQ" + (char) 13 + (char) 10
                        + "001-000 = " + pIdentificacao + (char) 13 + (char) 10
                        + "003-000 = " + pValorCheque + (char) 13 + (char) 10
                        + "999-999 = 0";

                gravar = new FileWriter(arquivoIntPos001);
                PrintWriter saida = new PrintWriter(gravar);
                saida.println(conteudoArquivo);
                saida.close();
                gravar.close();

                copyFile(arquivoIntPos001, reqIntPos001);
                arquivoIntPos001.delete();
            } catch (IOException e) {
                e.printStackTrace();
            }

            /*
             if (new File(System.getProperty("user.dir") + "\\imprime" + pNumeroTransacao + ".txt").exists()) {
             new File(System.getProperty("user.dir") + "\\imprime" + pNumeroTransacao + ".txt").delete();
             }*/

            result = -2;

            try {
                for (int tentativa = 1; tentativa <= 7; tentativa++) {
                    // Verifica se o Gerenciador Padrão recebeu o arquivo INTPOS.001
                    if (respIntPosSts.exists()) {
                        linhaArquivo = "";
                        linha = "";
                        retornoInsPosSts = true;
                        break;
                    } else {
                        Thread.sleep(1000);
                        //Caso o arquivo INTPOS.sts não retorne em 7 segundos exibe mensagem para o operador
                        if (tentativa == 7) {
                            retornoInsPosSts = false;
                            if (reqIntPos001.exists()) {
                                reqIntPos001.delete();
                                JOptionPane.showMessageDialog(null, "Gerenciador Padrão não está ATIVO!", "Mensagem para o operador", 2);
                                result = 0;
                                break;
                            }
                        }
                    }
                } //Fim do for tentativa

                if (retornoInsPosSts) {
                    while (result == -2) {
                        Thread.sleep(1000);
                        if (respIntPos001.exists()) {
                            result = lerRespIntPos001(pIdentificacao);
                        } //Fim File exists
                    } //Fim while result = -2
                }// fim exiteintpossts

            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }

            //Se tudo ocorrer bem cria o arquivo Pendente.txt
            if (result == 1) {
                result = 1;
                // gera um arquivo pendente, pois falta a confirmação da transação(CNC);
                try {
                    gravar = new FileWriter(arquivoPendenteTxt);
                    PrintWriter saida = new PrintWriter(gravar);
                    saida.println(Tef.NSU.toString());
                    saida.close();
                    gravar.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    private static String lerIdentificacao() {
        String result = "";
        try {
            FileReader leitura = new FileReader(respIntPos001);
            BufferedReader entrada = new BufferedReader(leitura);

            while ((linhaArquivo = entrada.readLine()) != null) {
                campoArquivo = linhaArquivo.substring(0, 3);
                switch (Integer.parseInt(campoArquivo)) {
                    case 1: //verifica se o campo de identificação é o mesmo do solicitado
                        result = linhaArquivo.substring(10, linhaArquivo.length());
                        respIntPos001.delete();
                        break;
                }
            }
            entrada.close();
            leitura.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
