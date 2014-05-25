/**
 * <p>Title: T2TiPDV</p>
 *
 * <p>Description: Classe de controle do PAF</p>
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

import com.t2ti.pafecf.controller.EmpresaController;
import com.t2ti.pafecf.controller.ImpressoraController;
import com.t2ti.pafecf.controller.ProdutoController;
import com.t2ti.pafecf.controller.RegistroRController;
import com.t2ti.pafecf.controller.SintegraController;
import com.t2ti.pafecf.controller.TotalTipoPagamentoController;
import com.t2ti.pafecf.view.Caixa;
import com.t2ti.pafecf.vo.EmpresaVO;
import com.t2ti.pafecf.vo.ImpressoraVO;
import com.t2ti.pafecf.vo.MeiosPagamentoVO;
import com.t2ti.pafecf.vo.ProdutoVO;
import com.t2ti.pafecf.vo.R01VO;
import com.t2ti.pafecf.vo.R02VO;
import com.t2ti.pafecf.vo.R03VO;
import com.t2ti.pafecf.vo.R04VO;
import com.t2ti.pafecf.vo.R05VO;
import com.t2ti.pafecf.vo.R06VO;
import com.t2ti.pafecf.vo.R07VO;
import com.t2ti.pafecf.vo.Sintegra60AVO;
import com.t2ti.pafecf.vo.Sintegra60MVO;
import com.t2ti.plugins.paf.PafE;
import com.t2ti.plugins.paf.PafN;
import com.t2ti.plugins.paf.PafP;
import com.t2ti.plugins.paf.PafR;
import com.t2ti.plugins.paf.pafe.RegistroE2;
import com.t2ti.plugins.paf.pafn.RegistroN3;
import com.t2ti.plugins.paf.pafp.RegistroP2;
import com.t2ti.plugins.paf.pafr.RegistroR02;
import com.t2ti.plugins.paf.pafr.RegistroR03;
import com.t2ti.plugins.paf.pafr.RegistroR04;
import com.t2ti.plugins.paf.pafr.RegistroR05;
import com.t2ti.plugins.paf.pafr.RegistroR06;
import com.t2ti.plugins.paf.pafr.RegistroR07;
import com.t2ti.plugins.util.RegistroEAD;
import jACBr.ACBrException;
import jACBr.Aliquota;
import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import javax.swing.JOptionPane;

public class Paf {

    //TODO : -Verifique com calma se todos os itens solicitados pela especificação de requisitos se encontram no relatório. Faça os ajustes necessários.
    public static void identificacaoPafEcf() {
        try {
            RegistroRController rControl = new RegistroRController();
            R01VO r01 = rControl.tabelaR01();
            String md5;

            Caixa.aCBrECF.abreRelatorioGerencial(0);
            Caixa.aCBrECF.linhaRelatorioGerencial(Biblioteca.repete("=", 48), 0);
            Caixa.aCBrECF.linhaRelatorioGerencial("IDENTIFICACAO DO PAF-ECF", 0);
            Caixa.aCBrECF.linhaRelatorioGerencial(Biblioteca.repete("=", 48), 0);
            Caixa.aCBrECF.linhaRelatorioGerencial("NU. LAUDO.........: " + r01.getNumeroLaudoPaf(), 0);
            Caixa.aCBrECF.linhaRelatorioGerencial("C.N.P.J. .........: " + r01.getCnpjSh(), 0);
            Caixa.aCBrECF.linhaRelatorioGerencial("RAZAO SOCIAL......: " + r01.getDenominacaoSh(), 0);
            Caixa.aCBrECF.linhaRelatorioGerencial("ENDERECO..........: " + r01.getEnderecoSh(), 0);
            Caixa.aCBrECF.linhaRelatorioGerencial("NUMERO............: " + r01.getNumeroSh(), 0);
            Caixa.aCBrECF.linhaRelatorioGerencial("COMPLEMENTO.......: " + r01.getComplementoSh(), 0);
            Caixa.aCBrECF.linhaRelatorioGerencial("BAIRRO............: " + r01.getBairroSh(), 0);
            Caixa.aCBrECF.linhaRelatorioGerencial("CIDADE............: " + r01.getCidadeSh(), 0);
            Caixa.aCBrECF.linhaRelatorioGerencial("CEP...............: " + r01.getCepSh(), 0);
            Caixa.aCBrECF.linhaRelatorioGerencial("UF................: " + r01.getUfSh(), 0);
            Caixa.aCBrECF.linhaRelatorioGerencial("TELEFONE..........: " + r01.getTelefoneSh(), 0);
            Caixa.aCBrECF.linhaRelatorioGerencial("CONTATO...........: " + r01.getContatoSh(), 0);
            Caixa.aCBrECF.linhaRelatorioGerencial("************IDENTIFICACAO DO PAF-ECF************", 0);
            Caixa.aCBrECF.linhaRelatorioGerencial("NOME COMERCIAL....: " + r01.getNomePafEcf(), 0);
            Caixa.aCBrECF.linhaRelatorioGerencial("VERSAO DO PAF-ECF.: " + r01.getVersaoPafEcf(), 0);
            Caixa.aCBrECF.linhaRelatorioGerencial("**********PRINCIPAL ARQUIVO EXECUTAVEL**********", 0);
            Caixa.aCBrECF.linhaRelatorioGerencial("NOME..........: " + r01.getPrincipalExecutavel(), 0);
            md5 = Biblioteca.MD5File("T2TiPDV.jar");
            Caixa.aCBrECF.linhaRelatorioGerencial("MD5...........: " + md5, 0);
            Caixa.aCBrECF.linhaRelatorioGerencial("****************DEMAIS ARQUIVOS*****************", 0);
            Caixa.aCBrECF.linhaRelatorioGerencial("NOME..........: BALCAO.JAR", 0);
            md5 = Biblioteca.MD5File("balcao/balcao.jar");
            Caixa.aCBrECF.linhaRelatorioGerencial("MD5...........: " + md5, 0);
            Caixa.aCBrECF.linhaRelatorioGerencial("**************NOME DO ARQUIVO TEXTO*************", 0);
            Caixa.aCBrECF.linhaRelatorioGerencial("NOME..........: ArquivoMD5.txt", 0);
            md5 = Biblioteca.MD5File("ArquivoMD5.txt");
            Caixa.aCBrECF.linhaRelatorioGerencial("MD5...........: " + md5, 0);
            Caixa.aCBrECF.linhaRelatorioGerencial("VERSAO ER PAF-ECF.: " + r01.getVersaoEr(), 0);
            Caixa.aCBrECF.linhaRelatorioGerencial("**********RELACAO DOS ECF AUTORIZADOS***********", 0);
            Caixa.aCBrECF.linhaRelatorioGerencial("SERIE............: " + r01.getSerieEcf(), 0);
            Caixa.aCBrECF.linhaRelatorioGerencial(Biblioteca.repete("=", 48), 0);
            Caixa.aCBrECF.fechaRelatorio();

            gravaR06("RG");
        } catch (Throwable t) {
            JOptionPane.showMessageDialog(null, t.getMessage(), "Erro do Sistema", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static boolean ECFAutorizado() {
        try {
            Properties arquivoAuxiliar = new Properties();
            arquivoAuxiliar.load(new FileInputStream(new File(System.getProperty("user.dir")
                    + System.getProperty("file.separator")
                    + "arquivoauxiliar.properties")));
            String MD5Serie = Biblioteca.MD5String(Caixa.aCBrECF.getNumSerie());
            String serieEcf = arquivoAuxiliar.getProperty("ecf.serie");
            if (MD5Serie.equals(serieEcf)) {
                return true;
            }
        } catch (NoSuchAlgorithmException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro do Sistema", JOptionPane.ERROR_MESSAGE);
        } catch (UnsupportedEncodingException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro do Sistema", JOptionPane.ERROR_MESSAGE);
        } catch (ACBrException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro do Sistema", JOptionPane.ERROR_MESSAGE);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Não foi possível acessar o arquivo de configuração!", "Erro do Sistema", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    //TODO : -Verifique se o procedimento abaixo está correto. Faça os ajustes necessários.
    public static String geraMD5() {
        try {
            PafN pafN = new PafN();

            R01VO r01 = new RegistroRController().tabelaR01();
            EmpresaVO empresa = new EmpresaController().pegaEmpresa(Caixa.configuracao.getIdEmpresa());

            //Registro N1
            pafN.getRegistroN().getRegistroN1().setCnpj(empresa.getCnpj());
            pafN.getRegistroN().getRegistroN1().setInscricaoEstadual(empresa.getInscricaoEstadual());
            pafN.getRegistroN().getRegistroN1().setInscricaoMunicipal(empresa.getInscricaoMunicipal());
            pafN.getRegistroN().getRegistroN1().setRazaoSocial(empresa.getRazaoSocial());
            
            //Registro N2
            pafN.getRegistroN().getRegistroN2().setLaudoPaf(r01.getNumeroLaudoPaf());
            pafN.getRegistroN().getRegistroN2().setNomePaf(r01.getNomePafEcf());
            pafN.getRegistroN().getRegistroN2().setVersaoPaf(r01.getVersaoPafEcf());

            //Registro N3
            RegistroN3 registroN3 = new RegistroN3();
            registroN3.setNomeArquivo("T2TiPDV.jar");
            registroN3.setCodigoMd5(Biblioteca.MD5File(System.getProperty("user.dir") + "\\T2TiPDV.jar"));
            pafN.getRegistroN().getListaRegistroN3().add(registroN3);

            registroN3 = new RegistroN3();
            registroN3.setNomeArquivo("Balcao.jar");
            registroN3.setCodigoMd5(Biblioteca.MD5File(System.getProperty("user.dir") + "\\Balcao.jar"));
            pafN.getRegistroN().getListaRegistroN3().add(registroN3);

            //Registro E9
            pafN.getRegistroN().getRegistroN9().setCnpj(empresa.getCnpj());
            pafN.getRegistroN().getRegistroN9().setInscricaoEstadual(empresa.getInscricaoEstadual());

            try {
                // TODO: -Gere o arquivo com o nome exigido pela espeficicação
                pafN.geraArquivoTxt(new File("ArquivoMD5.txt"));
                RegistroEAD.assinarArquivo("ArquivoMD5.txt");
            } catch (Exception e) {
                e.printStackTrace();
            }
                                
            String md5 = Biblioteca.MD5File(System.getProperty("user.dir") + "\\ArquivoMD5.txt");

            Properties arquivoAuxiliar = new Properties();
            arquivoAuxiliar.load(new FileInputStream(new File(System.getProperty("user.dir")
                    + System.getProperty("file.separator")
                    + "arquivoauxiliar.properties")));

            arquivoAuxiliar.setProperty("md5.arquivos", md5);

            FileOutputStream out = new FileOutputStream(new File(System.getProperty("user.dir")
                    + System.getProperty("file.separator")
                    + "arquivoauxiliar.properties"));
            arquivoAuxiliar.store(out, "Arquivo auxiliar do PAF");
            out.close();

            JOptionPane.showMessageDialog(Caixa.getCaixa(), "Arquivo armazenado em: " + System.getProperty("user.dir") + "\\ArquivoMD5.txt", "Informação do Sistema", JOptionPane.INFORMATION_MESSAGE);

            return md5;
        } catch (Exception e) {
            return null;
        }
    }

    public static void meiosPagamento(String dataIni, String dataFim) {
        TotalTipoPagamentoController totalTipoPagamentoControl = new TotalTipoPagamentoController();
        List<MeiosPagamentoVO> listaMeiosPagamento = new ArrayList<MeiosPagamentoVO>();
        listaMeiosPagamento = totalTipoPagamentoControl.meiosPagamento(dataIni, dataFim, Caixa.configuracao.getImpressoraVO().getId());
        String meio, tipoDoc, valor, data = "";

        try {
            Caixa.aCBrECF.abreRelatorioGerencial(0);
            Caixa.aCBrECF.linhaRelatorioGerencial(Biblioteca.repete("=", 48), 0);
            Caixa.aCBrECF.linhaRelatorioGerencial("MEIOS DE PAGAMENTO", 0);
            Caixa.aCBrECF.linhaRelatorioGerencial("PERIODO: " + dataIni + " A " + dataFim, 0);
            Caixa.aCBrECF.linhaRelatorioGerencial(Biblioteca.repete("=", 48), 0);
            Caixa.aCBrECF.linhaRelatorioGerencial("MEIO DE PGTO.  TIPO DOC. VLR.ACUMUL.  DT.ACUMUL.", 0);
            Caixa.aCBrECF.linhaRelatorioGerencial(Biblioteca.repete("=", 48), 0);
            for (int i = 0; i < listaMeiosPagamento.size(); i++) {
                meio = listaMeiosPagamento.get(i).getDescricao();
                meio = meio + Biblioteca.repete(" ", 15 - meio.length());
                tipoDoc = "FISCAL ";
                NumberFormat formatter = new DecimalFormat("0.00");
                valor = formatter.format(listaMeiosPagamento.get(i).getTotal());
                valor = Biblioteca.repete(" ", 13 - valor.length()) + valor;
                data = " " + listaMeiosPagamento.get(i).getDataHora().toString().substring(0, 10);
                Caixa.aCBrECF.linhaRelatorioGerencial(meio + tipoDoc + valor + data, 0);
            }
            Caixa.aCBrECF.linhaRelatorioGerencial(Biblioteca.repete("=", 48), 0);
            Caixa.aCBrECF.fechaRelatorio();

            gravaR06("RG");
        } catch (Throwable t) {
            JOptionPane.showMessageDialog(null, t.getMessage(), "Erro do Sistema", JOptionPane.ERROR_MESSAGE);
        }
    }

    //TODO: -Verifique com calma se o procedimento abaixo está sendo realizado corretamente. Faça os devidos ajustes.
    public static void gravaR02R03() {
        try {
            List<R03VO> listaR03 = new ArrayList<R03VO>();
            RegistroRController registroRControl = new RegistroRController();

            //Dados para o registro R02
            R02VO R02 = new R02VO();
            R02.setIdCaixa(Caixa.movimento.getIdCaixa());
            R02.setIdOperador(Caixa.movimento.getIdOperador());
            R02.setIdImpressora(Caixa.movimento.getIdImpressora());
            R02.setCrz(Integer.valueOf(Caixa.aCBrECF.getNumCRZ()));
            R02.setCoo(Integer.valueOf(Caixa.aCBrECF.getNumCOO()));
            R02.setCro(Integer.valueOf(Caixa.aCBrECF.getNumCRO()));

            java.util.Date data = Ecf.getDataMovimento();
            java.sql.Date dataSQL = new java.sql.Date(data.getTime());
            SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm:ss");

            R02.setDataMovimento(dataSQL);
            R02.setDataEmissao(dataSQL);
            R02.setHoraEmissao(formatoHora.format(data));
            R02.setValorVendaBruta(Caixa.aCBrECF.getVendaBruta());
            R02.setValorGrandeTotal(Caixa.aCBrECF.getGrandeTotal());
            R02.setSerieEcf(Caixa.aCBrECF.getNumSerie());
            
            R02 = registroRControl.gravaR02(R02);

            //Dados para o registro R03
            
            //TODO: -Estude o framework jACBrFramework e implemente o procedimento DadosReducaoZClass para pegar gerar esses dados corretamente
            R03VO R03 = new R03VO();

            Aliquota[] aliquotas = Caixa.aCBrECF.getAliquotas();
            
            for (int i = 0; i < aliquotas.length; i++) {
                R03 = new R03VO();
                R03.setIdR02(R02.getId());
                R03.setSerieEcf(Caixa.aCBrECF.getNumSerie());
                R03.setTotalizadorParcial(String.valueOf(aliquotas[i].getTipo()));
                R03.setValorAcumulado(aliquotas[i].getTotal());
                R03.setCrz(Integer.valueOf(Caixa.aCBrECF.getNumCRZ()));
                listaR03.add(R03);
            }

            registroRControl.gravaR03(listaR03);
            
            grava60M60A(R02.getIdImpressora());
            
        } catch (Throwable t) {
            JOptionPane.showMessageDialog(null, t.getMessage(), "Erro do Sistema", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void grava60M60A(Integer pIdImpressora) {
        try {
            ImpressoraVO impressora = new ImpressoraController().pegaImpressora(pIdImpressora);
            SintegraController sintegraControl = new SintegraController();
            Sintegra60MVO sintegra60M = new Sintegra60MVO();
            List<Sintegra60AVO> lista60A = new ArrayList<Sintegra60AVO>();

            java.util.Date data = Ecf.getDataMovimento();
            java.sql.Date dataSQL = new java.sql.Date(data.getTime());

            sintegra60M.setDataEmissao(dataSQL);
            sintegra60M.setNumeroSerieEcf(Caixa.aCBrECF.getNumSerie());
            sintegra60M.setNumeroEquipamento(Integer.valueOf(Caixa.aCBrECF.getNumECF()));
            sintegra60M.setModeloDocumentoFiscal(impressora.getModeloDocumentoFiscal());
            sintegra60M.setCooInicial(Integer.valueOf(Caixa.aCBrECF.getNumCOOInicial()));
            sintegra60M.setCooFinal(Integer.valueOf(Caixa.aCBrECF.getNumCOO()));
            sintegra60M.setCrz(Integer.valueOf(Caixa.aCBrECF.getNumCRZ()));
            sintegra60M.setCro(Integer.valueOf(Caixa.aCBrECF.getNumCRO()));
            sintegra60M.setValorVendaBruta(Caixa.aCBrECF.getVendaBruta());
            sintegra60M.setValorGrandeTotal(Caixa.aCBrECF.getGrandeTotal());

            sintegraControl.Grava60M(sintegra60M);
            //TODO: -Estude o framework jACBrFramework e implemente o procedimento DadosReducaoZClass para gravar o 60A
        } catch (Throwable t) {
            t.printStackTrace();
            JOptionPane.showMessageDialog(null, t.getMessage(), "Erro do Sistema", JOptionPane.ERROR_MESSAGE);
        }

    }

    public static boolean confereGT() {
        try {
            Properties arquivoAuxiliar = new Properties();
            arquivoAuxiliar.load(new FileInputStream(new File(System.getProperty("user.dir")
                    + System.getProperty("file.separator")
                    + "arquivoauxiliar.properties")));

            String gtArquivo = arquivoAuxiliar.getProperty("ecf.gt");

            String gtEcf = String.valueOf(Caixa.aCBrECF.getGrandeTotal());
            gtEcf = Biblioteca.MD5String(gtEcf);

            if (gtArquivo.equals(gtEcf)) {
                return true;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public static void atualizaGT() {
        try {
            Properties arquivoAuxiliar = new Properties();
            arquivoAuxiliar.load(new FileInputStream(new File(System.getProperty("user.dir")
                    + System.getProperty("file.separator")
                    + "arquivoauxiliar.properties")));

            String gtEcf = String.valueOf(Caixa.aCBrECF.getGrandeTotal());
            gtEcf = Biblioteca.MD5String(gtEcf);

            arquivoAuxiliar.setProperty("ecf.gt", gtEcf);

            FileOutputStream out = new FileOutputStream(new File(System.getProperty("user.dir")
                    + System.getProperty("file.separator")
                    + "arquivoauxiliar.properties"));

            arquivoAuxiliar.store(out, "Arquivo auxiliar do PAF");

            out.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    //TODO : -Verifique com calma se todos os itens solicitados pela especificação de requisitos se encontram no relatório. Faça os ajustes necessários.
    public static void parametrosConfiguracao() {
        try {
            Caixa.aCBrECF.abreRelatorioGerencial(0);
            Caixa.aCBrECF.linhaRelatorioGerencial(Biblioteca.repete("=", 48), 0);
            Caixa.aCBrECF.linhaRelatorioGerencial("***********PARAMETROS DE CONFIGURACAO***********", 0);
            Caixa.aCBrECF.linhaRelatorioGerencial(Biblioteca.repete("=", 48), 0);
            Caixa.aCBrECF.linhaRelatorioGerencial("CONFIGURACAO:", 0);
            Caixa.aCBrECF.linhaRelatorioGerencial(Biblioteca.repete("=", 48), 0);

            Caixa.aCBrECF.linhaRelatorioGerencial("Funcionalidades:", 0);
            Caixa.aCBrECF.linhaRelatorioGerencial("", 0);
            Caixa.aCBrECF.linhaRelatorioGerencial("TIPO DE FUNCIONAMENTO: ......... Stand Alone", 0);
            Caixa.aCBrECF.linhaRelatorioGerencial("TIPO DE FUNCIONAMENTO: ......... Comercializavel", 0);
            Caixa.aCBrECF.linhaRelatorioGerencial("INTEGRACAO DO PAF-ECF: ......... Balcao", 0);
            Caixa.aCBrECF.linhaRelatorioGerencial(Biblioteca.repete("=", 48), 0);

            Caixa.aCBrECF.linhaRelatorioGerencial("Parâmetros Para Nao Concomitancia:", 0);
            Caixa.aCBrECF.linhaRelatorioGerencial("", 0);
            Caixa.aCBrECF.linhaRelatorioGerencial("PRE-VENDA: ................................. SIM", 0);
            Caixa.aCBrECF.linhaRelatorioGerencial("DAV POR ECF: ............................... NAO", 0);
            Caixa.aCBrECF.linhaRelatorioGerencial("DAV IMPRESSORA NAO FISCAL: ................. SIM", 0);
            Caixa.aCBrECF.linhaRelatorioGerencial("DAV-OS: .................................... NAO", 0);
            Caixa.aCBrECF.linhaRelatorioGerencial(Biblioteca.repete("=", 48), 0);

            Caixa.aCBrECF.linhaRelatorioGerencial("Aplicacoes Especiais:", 0);
            Caixa.aCBrECF.linhaRelatorioGerencial("", 0);
            Caixa.aCBrECF.linhaRelatorioGerencial("TAB. INDICE TECNICO DE PRODUCAO: ........... SIM", 0);
            Caixa.aCBrECF.linhaRelatorioGerencial("POSTO REVENDEDOR DE COMBUSTIVEIS: .......... NAO", 0);
            Caixa.aCBrECF.linhaRelatorioGerencial("Bar, Restaurante e Similar - ECF-Restaurante:NAO", 0);
            Caixa.aCBrECF.linhaRelatorioGerencial("Bar, Restaurante e Similar - ECF-Comum: .... NAO", 0);
            Caixa.aCBrECF.linhaRelatorioGerencial("FARMACIA DE MANIPULACAO: ................... NAO", 0);
            Caixa.aCBrECF.linhaRelatorioGerencial("OFICINA DE CONSERTO: ....................... NAO", 0);
            Caixa.aCBrECF.linhaRelatorioGerencial("TRANSPORTE DE PASSAGEIROS: ................. NAO", 0);
            Caixa.aCBrECF.linhaRelatorioGerencial(Biblioteca.repete("=", 48), 0);

            Caixa.aCBrECF.linhaRelatorioGerencial("Criterios por Unidade Federada:", 0);
            Caixa.aCBrECF.linhaRelatorioGerencial("", 0);
            Caixa.aCBrECF.linhaRelatorioGerencial("REQUISITO XVIII - Tela Consulta de Preco:", 0);
            Caixa.aCBrECF.linhaRelatorioGerencial("", 0);
            Caixa.aCBrECF.linhaRelatorioGerencial("TOTALIZACAO DOS VALORES DA LISTA: .......... NAO", 0);
            Caixa.aCBrECF.linhaRelatorioGerencial("TRANSFORMACAO DAS INFORMACOES EM PRE-VENDA:. NAO", 0);
            Caixa.aCBrECF.linhaRelatorioGerencial("TRANSFORMACAO DAS INFORMACOES EM DAV: ...... NAO", 0);
            Caixa.aCBrECF.linhaRelatorioGerencial(Biblioteca.repete("=", 48), 0);

            Caixa.aCBrECF.linhaRelatorioGerencial("REQUISITO XXII - PAF-ECF Integrado ao ECF:", 0);
            Caixa.aCBrECF.linhaRelatorioGerencial("", 0);
            Caixa.aCBrECF.linhaRelatorioGerencial("NAO COINCIDENCIA GT(ECF) x ARQUIVO CRIPTOGRAFADO", 0);
            Caixa.aCBrECF.linhaRelatorioGerencial("RECOMPOE VALOR DO GT ARQUIVO CRIPTOGRAFADO:  NAO", 0);
            Caixa.aCBrECF.linhaRelatorioGerencial(Biblioteca.repete("=", 48), 0);

            Caixa.aCBrECF.linhaRelatorioGerencial("REQUISITO XXXVI - A - PAF-ECF Combustivel:", 0);
            Caixa.aCBrECF.linhaRelatorioGerencial("", 0);
            Caixa.aCBrECF.linhaRelatorioGerencial("Impedir Registro de Venda com Valor Zero ou", 0);
            Caixa.aCBrECF.linhaRelatorioGerencial("Negativo: .................................. SIM", 0);
            Caixa.aCBrECF.linhaRelatorioGerencial(Biblioteca.repete("=", 48), 0);

            Caixa.aCBrECF.fechaRelatorio();

            gravaR06("RG");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(Caixa.getCaixa(), e.getMessage(), "Erro do Sistema", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void gravaR06(String simbolo) {
        try {
            R06VO r06;
            SimpleDateFormat formatohora = new SimpleDateFormat("HH:mm:ss");
            RegistroRController registroRControl = new RegistroRController();
            boolean primeiroDocumentoDia = false;

            //verifica se é o primeiro documento do dia
            r06 = registroRControl.ultimoRegistroR06();
            Calendar dataEcf = Calendar.getInstance();
            Calendar dataUltimoRegistro = Calendar.getInstance();
            dataEcf.setTime(Ecf.getDataHoraEcf());
            dataEcf.set(Calendar.HOUR_OF_DAY, 0);
            dataEcf.set(Calendar.MINUTE, 0);
            dataEcf.set(Calendar.SECOND, 0);
            if (r06.getDataEmissao() != null) {
                dataUltimoRegistro.setTime(r06.getDataEmissao());
            } else {
                primeiroDocumentoDia = true;
            }
            dataUltimoRegistro.set(Calendar.HOUR_OF_DAY, 0);
            dataUltimoRegistro.set(Calendar.MINUTE, 0);
            dataUltimoRegistro.set(Calendar.SECOND, 0);
            if (dataEcf.after(dataUltimoRegistro)) {
                primeiroDocumentoDia = true;
            }

            if (Caixa.movimento != null) {
                r06.setIdOperador(Caixa.movimento.getIdOperador());
                r06.setIdImpressora(Caixa.movimento.getIdImpressora());
                r06.setIdCaixa(Caixa.movimento.getIdCaixa());
            } else {
                r06.setIdOperador(0);
                r06.setIdImpressora(Caixa.configuracao.getImpressoraVO().getId());
                r06.setIdCaixa(0);
            }

            r06.setSerieEcf(Caixa.configuracao.getImpressoraVO().getSerie());
            r06.setCoo(Integer.valueOf(Caixa.aCBrECF.getNumCOO()));
            r06.setGnf(Integer.valueOf(Caixa.aCBrECF.getNumGNF()));
            if (simbolo.equals("RG")) {
                r06.setGrg(Integer.valueOf(Caixa.aCBrECF.getNumGRG()));
            }
            if (simbolo.equals("CC")) {
                r06.setCdc(Integer.valueOf(Caixa.aCBrECF.getNumCDC()));
            }
            r06.setDenominacao(simbolo);
            r06.setDataEmissao(new java.sql.Date(Ecf.getDataHoraEcf().getTime()));
            r06.setHoraEmissao(formatohora.format(Ecf.getDataHoraEcf()));

            registroRControl.gravaR06(r06);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void geraArquivoEstoque() {
        List<ProdutoVO> listaProduto = new ProdutoController().tabelaProduto();
        if (listaProduto != null) {
            geraEstoque(listaProduto);
        }
    }

    public static void geraArquivoEstoque(Integer pCodigoInicial, Integer pCodigoFinal) {
        List<ProdutoVO> listaProduto = new ProdutoController().tabelaProduto(pCodigoInicial, pCodigoFinal);
        if (listaProduto != null) {
            geraEstoque(listaProduto);
        }
    }

    public static void geraArquivoEstoque(String pNomeInicial, String pNomeFinal) {
        List<ProdutoVO> listaProduto = new ProdutoController().tabelaProduto(pNomeInicial, pNomeFinal);
        if (listaProduto != null) {
            geraEstoque(listaProduto);
        }
    }

    public static void geraEstoque(List<ProdutoVO> listaProduto) {
        try {
            PafE pafE = new PafE();
            EmpresaVO empresa = new EmpresaController().pegaEmpresa(Caixa.configuracao.getIdEmpresa());
            ImpressoraVO impressora = new ImpressoraController().pegaImpressora(Caixa.configuracao.getIdImpressora());
            String tripa = "";

            //Registro E1
            pafE.getRegistroE().getRegistroE1().setCnpj(empresa.getCnpj());
            pafE.getRegistroE().getRegistroE1().setInscricaoEstadual(empresa.getInscricaoEstadual());
            pafE.getRegistroE().getRegistroE1().setInscricaoMunicipal(empresa.getInscricaoMunicipal());
            pafE.getRegistroE().getRegistroE1().setRazaoSocial(empresa.getRazaoSocial());
            pafE.getRegistroE().getRegistroE1().setNumeroFabricacao(impressora.getSerie());
            pafE.getRegistroE().getRegistroE1().setMfAdicional(impressora.getMfd());
            pafE.getRegistroE().getRegistroE1().setTipoEcf(impressora.getTipo());
            pafE.getRegistroE().getRegistroE1().setMarcaEcf(impressora.getMarca());
            pafE.getRegistroE().getRegistroE1().setDataEstoque(Caixa.configuracao.getDataAtualizacaoEstoque());

            for (int i = 0; i <= listaProduto.size() - 1; i++) {
                tripa = ""
                        + listaProduto.get(i).getGtin()
                        + listaProduto.get(i).getDescricao()
                        + listaProduto.get(i).getDescricaoPDV()
                        + Biblioteca.formatoDecimal("V", listaProduto.get(i).getQuantidadeEstoqueAnterior())
                        + listaProduto.get(i).getDataEstoque()
                        + listaProduto.get(i).getCst()
                        + Biblioteca.formatoDecimal("V", listaProduto.get(i).getTaxaIcms())
                        + Biblioteca.formatoDecimal("V", listaProduto.get(i).getValorVenda())
                        + listaProduto.get(i).getHashIncremento();

                if (Biblioteca.MD5String(tripa).equals(listaProduto.get(i).getHashTripa())) {
                    pafE.getRegistroE().getRegistroE1().setModeloEcf(impressora.getModelo());
                } else {
                    pafE.getRegistroE().getRegistroE1().setModeloEcf(Biblioteca.repete("?", 20));
                }

                RegistroE2 registroE2 = new RegistroE2();
                registroE2.setCnpj(empresa.getCnpj());
                registroE2.setCodigoMercadoria(listaProduto.get(i).getGtin());
                registroE2.setDescricaoMercadoria(listaProduto.get(i).getDescricaoPDV());

                if (Biblioteca.MD5String(tripa).equals(listaProduto.get(i).getHashTripa())) {
                    registroE2.setUnidadeMedida(listaProduto.get(i).getUnidadeProdutoVO().getNome());
                } else {
                    registroE2.setUnidadeMedida(Biblioteca.repete("?", 6));
                }
                registroE2.setQuantidadeEstoque(listaProduto.get(i).getQuantidadeEstoque());

                pafE.getRegistroE().getListaRegistroE2().add(registroE2);
            }

            //Registro E9
            pafE.getRegistroE().getRegistroE9().setCnpj(empresa.getCnpj());
            pafE.getRegistroE().getRegistroE9().setInscricaoEstadual(empresa.getInscricaoEstadual());

            try {
                // TODO: -Gere o arquivo com o nome exigido pela espeficicação
                pafE.geraArquivoTxt(new File("PAF_E.txt"));
                RegistroEAD.assinarArquivo("PAF_E.txt");
            } catch (Exception e) {
                e.printStackTrace();
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void geraTabelaProdutos() {
        try {
            PafP pafP = new PafP();
            List<ProdutoVO> listaProduto = new ProdutoController().tabelaProduto();
            EmpresaVO empresa = new EmpresaController().pegaEmpresa(Caixa.configuracao.getIdEmpresa());
            ImpressoraVO impressora = new ImpressoraController().pegaImpressora(Caixa.configuracao.getIdImpressora());
            String tripa = "";

            //Registro P1
            pafP.getRegistroP().getRegistroP1().setCnpj(empresa.getCnpj());
            pafP.getRegistroP().getRegistroP1().setInscricaoEstadual(empresa.getInscricaoEstadual());
            pafP.getRegistroP().getRegistroP1().setInscricaoMunicipal(empresa.getInscricaoMunicipal());
            pafP.getRegistroP().getRegistroP1().setRazaoSocial(empresa.getRazaoSocial());

            for (int i = 0; i <= listaProduto.size() - 1; i++) {
                tripa = ""
                        + listaProduto.get(i).getGtin()
                        + listaProduto.get(i).getDescricao()
                        + listaProduto.get(i).getDescricaoPDV()
                        + Biblioteca.formatoDecimal("V", listaProduto.get(i).getQuantidadeEstoqueAnterior())
                        + listaProduto.get(i).getDataEstoque()
                        + listaProduto.get(i).getCst()
                        + Biblioteca.formatoDecimal("V", listaProduto.get(i).getTaxaIcms())
                        + Biblioteca.formatoDecimal("V", listaProduto.get(i).getValorVenda())
                        + listaProduto.get(i).getHashIncremento();

                RegistroP2 registroP2 = new RegistroP2();
                registroP2.setCnpj(empresa.getCnpj());
                registroP2.setCodigoMercadoria(listaProduto.get(i).getGtin());
                registroP2.setDescricaoMercadoria(listaProduto.get(i).getDescricaoPDV());

                if (Biblioteca.MD5String(tripa).equals(listaProduto.get(i).getHashTripa())) {
                    registroP2.setUnidadeMedida(listaProduto.get(i).getUnidadeProdutoVO().getNome());
                } else {
                    registroP2.setUnidadeMedida(Biblioteca.repete("?", 6));
                }

                registroP2.setIat(listaProduto.get(i).getIat());
                registroP2.setIppt(listaProduto.get(i).getIppt());
                registroP2.setSituacaoTributaria(listaProduto.get(i).getPafProdutoSt());
                registroP2.setAliquota(listaProduto.get(i).getTaxaIcms());
                registroP2.setValorUnitario(listaProduto.get(i).getValorVenda());

                pafP.getRegistroP().getListaRegistroP2().add(registroP2);
            }

            //Registro E9
            pafP.getRegistroP().getRegistroP9().setCnpj(empresa.getCnpj());
            pafP.getRegistroP().getRegistroP9().setInscricaoEstadual(empresa.getInscricaoEstadual());

            try {
                // TODO: -Gere o arquivo com o nome exigido pela espeficicação
                pafP.geraArquivoTxt(new File("PAF_P.txt"));
                RegistroEAD.assinarArquivo("PAF_P.txt");
            } catch (Exception e) {
                e.printStackTrace();
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //TODO: -verifique com calma o procedimento abaixo e corrija o que for necessário. Atenção às "?????" do Bloco VII
    public static void geraMovimentoECF(Date pDataInicio, Date pDataFim, Date pDataMovimento, Integer pIdImpressora) {
        try {
            PafR pafR = new PafR();
            EmpresaVO empresa = new EmpresaController().pegaEmpresa(Caixa.configuracao.getIdEmpresa());
            ImpressoraVO impressora = new ImpressoraController().pegaImpressora(pIdImpressora);
            RegistroRController registroRController = new RegistroRController();
            SimpleDateFormat formataData;
            String tripa = "";

            R01VO r01 = registroRController.tabelaR01();
            
            //Registro R1
            pafR.getRegistroR().getRegistroR01().setNumeroFabricacao(r01.getSerieEcf());
            pafR.getRegistroR().getRegistroR01().setMfAdicional(impressora.getMfd());
            pafR.getRegistroR().getRegistroR01().setTipoEcf(impressora.getTipo());
            pafR.getRegistroR().getRegistroR01().setMarcaEcf(impressora.getMarca());
            pafR.getRegistroR().getRegistroR01().setModeloEcf(impressora.getModelo());
            pafR.getRegistroR().getRegistroR01().setVersaoSb(impressora.getVersao());
            pafR.getRegistroR().getRegistroR01().setDataInstalacaoSb(impressora.getDataInstalacaoSb());
            pafR.getRegistroR().getRegistroR01().setHoraInstalacaoSb(impressora.getHoraInstalacaoSb());
            pafR.getRegistroR().getRegistroR01().setNumeroSequencialEcf(Integer.valueOf(impressora.getNumeroEcf()));
            pafR.getRegistroR().getRegistroR01().setCnpjUsuario(r01.getCnpjEmpresa());
            pafR.getRegistroR().getRegistroR01().setInscricaoEstadualUsuario(empresa.getInscricaoEstadual());
            pafR.getRegistroR().getRegistroR01().setCnpjSoftwareHouse(r01.getCnpjSh());
            pafR.getRegistroR().getRegistroR01().setInscricaoEstadualSoftwareHouse(r01.getInscricaoEstadualSh());
            pafR.getRegistroR().getRegistroR01().setInscricaoMunicipalSoftwareHouse(r01.getInscricaoMunicipalSh());
            pafR.getRegistroR().getRegistroR01().setNomeSoftwareHouse(r01.getDenominacaoSh());
            pafR.getRegistroR().getRegistroR01().setNomePaf(r01.getNomePafEcf());
            pafR.getRegistroR().getRegistroR01().setVersaoPaf(r01.getVersaoPafEcf());
            pafR.getRegistroR().getRegistroR01().setCodigoMd5(r01.getMd5PafEcf());
            pafR.getRegistroR().getRegistroR01().setDataInicial(pDataInicio);
            pafR.getRegistroR().getRegistroR01().setDataFinal(pDataFim);
            pafR.getRegistroR().getRegistroR01().setVersaoEspecificacaoRequisitos(r01.getVersaoEr());
            
            formataData = new SimpleDateFormat("yyyy-MM-dd");
            String strDataIni = formataData.format(pDataInicio);
            String strDataFim = formataData.format(pDataFim);
            
            List<R02VO> listaR02 = registroRController.tabelaR02(strDataIni, strDataFim, pIdImpressora);
            for (int i = 0; i <= listaR02.size() - 1; i++) {
                tripa = ""
                        + listaR02.get(i).getId()
                        + listaR02.get(i).getIdOperador()
                        + listaR02.get(i).getIdImpressora()
                        + listaR02.get(i).getIdCaixa()
                        + listaR02.get(i).getCrz()
                        + listaR02.get(i).getCoo()
                        + listaR02.get(i).getCro()
                        + listaR02.get(i).getDataMovimento()
                        + listaR02.get(i).getDataEmissao()
                        + listaR02.get(i).getHoraEmissao()
                        + Biblioteca.formatoDecimal("V", listaR02.get(i).getValorVendaBruta())
                        + Biblioteca.formatoDecimal("V", listaR02.get(i).getValorGrandeTotal())
                        + listaR02.get(i).getSerieEcf()
                        + listaR02.get(i).getHashIncremento();

                RegistroR02 registroR02 = new RegistroR02();
                registroR02.setNumeroFabricacao(r01.getSerieEcf());
                registroR02.setMfAdicional(impressora.getMfd());
                if (Biblioteca.MD5String(tripa).equals(listaR02.get(i).getHashTripa())) {
                    registroR02.setModeloEcf(impressora.getModelo());
                } else {
                    registroR02.setModeloEcf(Biblioteca.repete("?", 20));
                }
                registroR02.setNumeroUsuario(listaR02.get(i).getIdOperador());
                registroR02.setCrz(listaR02.get(i).getCrz());
                registroR02.setCoo(listaR02.get(i).getCoo());
                registroR02.setCro(listaR02.get(i).getCro());
                registroR02.setDataMovimento(listaR02.get(i).getDataMovimento());
                registroR02.setDataEmissao(listaR02.get(i).getDataEmissao());
                registroR02.setHoraEmissao(listaR02.get(i).getHoraEmissao());
                registroR02.setVendaBrutaDiaria(listaR02.get(i).getValorVendaBruta());
                registroR02.setParametroEcfDescontoIssqn("");

                pafR.getRegistroR().getListaRegistroR02().add(registroR02);

                List<R03VO> listaR03 = registroRController.tabelaR03(listaR02.get(i).getId());
                for (int j = 0; j <= listaR03.size() - 1; j++) {
                    tripa = ""
                            + listaR03.get(j).getTotalizadorParcial()
                            + Biblioteca.formatoDecimal("V", listaR03.get(j).getValorAcumulado())
                            + listaR03.get(j).getCrz()
                            + listaR03.get(j).getSerieEcf()
                            + listaR03.get(j).getHashIncremento();

                    RegistroR03 registroR03 = new RegistroR03();
                    registroR03.setNumeroFabricacao(r01.getSerieEcf());
                    registroR03.setMfAdicional(impressora.getMfd());
                    if (Biblioteca.MD5String(tripa).equals(listaR03.get(j).getHashTripa())) {
                        registroR03.setModeloEcf(impressora.getModelo());
                    } else {
                        registroR03.setModeloEcf(Biblioteca.repete("?", 20));
                    }
                    registroR03.setNumeroUsuario(listaR02.get(i).getIdOperador());
                    registroR03.setCrz(listaR03.get(j).getCrz());
                    registroR03.setTotalizadorParcial(listaR03.get(j).getTotalizadorParcial());
                    registroR03.setValorAcumulado(listaR03.get(j).getValorAcumulado());

                    pafR.getRegistroR().getListaRegistroR03().add(registroR03);
                }
            }

            List<R04VO> listaR04 = registroRController.tabelaR04(strDataIni, strDataFim, pIdImpressora);
            for (int i = 0; i <= listaR04.size() - 1; i++) {
                tripa = ""
                        + listaR04.get(i).getId()
                        + listaR04.get(i).getCcf()
                        + listaR04.get(i).getCoo()
                        + Biblioteca.formatoDecimal("V", listaR04.get(i).getValorLiquido())
                        + listaR04.get(i).getSerieEcf()
                        + listaR04.get(i).getStatusVenda()
                        + listaR04.get(i).getCancelado()
                        + listaR04.get(i).getHashIncremento();

                RegistroR04 registroR04 = new RegistroR04();
                registroR04.setNumeroFabricacao(r01.getSerieEcf());
                registroR04.setMfAdicional(impressora.getMfd());
                if (Biblioteca.MD5String(tripa).equals(listaR04.get(i).getHashTripa())) {
                    registroR04.setModeloEcf(impressora.getModelo());
                } else {
                    registroR04.setModeloEcf(Biblioteca.repete("?", 20));
                }
                registroR04.setNumeroUsuario(listaR04.get(i).getIdOperador());
                registroR04.setNumeroContador(listaR04.get(i).getCcf());
                registroR04.setCoo(listaR04.get(i).getCoo());
                registroR04.setDataInicio(listaR04.get(i).getDataEmissao());
                registroR04.setSubtotalDocumento(listaR04.get(i).getSubTotal());
                registroR04.setDescontoSubtotal(listaR04.get(i).getDesconto());
                registroR04.setIndicadorTipoDesconto(listaR04.get(i).getIndicadorDesconto());
                registroR04.setAcrescimoSubtotal(listaR04.get(i).getAcrescimo());
                registroR04.setIndicadorTipoAcrescimo(listaR04.get(i).getIndicadorAcrescimo());
                registroR04.setValorTotalLiquido(listaR04.get(i).getValorLiquido());
                registroR04.setIndicadorCancelamento(listaR04.get(i).getCancelado());
                registroR04.setCancelamentoSubtotal(listaR04.get(i).getCancelamentoAcrescimo());
                registroR04.setOrdemAplicacaoDescontoAcrescimo(listaR04.get(i).getOrdemDescontoAcrescimo());
                registroR04.setNomeCliente(listaR04.get(i).getCliente());
                registroR04.setCnpjCpfAdquirente(listaR04.get(i).getCpfCnpj());

                pafR.getRegistroR().getListaRegistroR04().add(registroR04);

                List<R05VO> listaR05 = registroRController.tabelaR05(listaR04.get(i).getId());
                if (listaR05 != null) {
                    for (int j = 0; j <= listaR05.size() - 1; j++) {
                        tripa = ""
                                + listaR05.get(j).getSerieEcf()
                                + listaR05.get(j).getCoo()
                                + listaR05.get(j).getCcf()
                                + listaR05.get(j).getGtin()
                                + Biblioteca.formatoDecimal("Q", listaR05.get(j).getQuantidade())
                                + Biblioteca.formatoDecimal("V", listaR05.get(j).getValorUnitario())
                                + Biblioteca.formatoDecimal("V", listaR05.get(j).getTotalItem())
                                + listaR05.get(j).getTotalizadorParcial()
                                + listaR05.get(j).getIndicadorCancelamento()
                                + listaR05.get(j).getHashIncremento();

                        RegistroR05 registroR05 = new RegistroR05();
                        registroR05.setNumeroFabricacao(r01.getSerieEcf());
                        registroR05.setMfAdicional(impressora.getMfd());
                        if (Biblioteca.MD5String(tripa).equals(listaR05.get(j).getHashTripa())) {
                            registroR05.setModeloEcf(impressora.getModelo());
                        } else {
                            registroR05.setModeloEcf(Biblioteca.repete("?", 20));
                        }
                        registroR05.setNumeroUsuario(listaR04.get(i).getIdOperador());
                        registroR05.setCoo(listaR05.get(j).getCoo());
                        registroR05.setNumeroContador(listaR05.get(j).getCcf());
                        registroR05.setNumeroItem(listaR05.get(j).getItem());
                        registroR05.setCodigoProduto(listaR05.get(j).getGtin());
                        registroR05.setDescricaoProduto(listaR05.get(j).getDescricaoPdv());
                        registroR05.setQuantidadeProduto(listaR05.get(j).getQuantidade());
                        registroR05.setUnidadeMedida(listaR05.get(j).getSiglaUnidade());
                        registroR05.setValorUnitario(listaR05.get(j).getValorUnitario());
                        registroR05.setDescontoSobreItem(listaR05.get(j).getDesconto());
                        registroR05.setAcrescimoSobreItem(listaR05.get(j).getAcrescimo());
                        registroR05.setValorTotalLiquido(listaR05.get(j).getTotalItem());
                        registroR05.setTotalizadorParcial(listaR05.get(j).getTotalizadorParcial());
                        registroR05.setIndicadorCancelamento(listaR05.get(j).getIndicadorCancelamento());
                        registroR05.setQuantidadeCancelada(listaR05.get(j).getQuantidadeCancelada());
                        registroR05.setValorCancelado(listaR05.get(j).getValorCancelado());
                        registroR05.setCancelamentoAcrescimoItem(listaR05.get(j).getCancelamentoAcrescimo());
                        registroR05.setIat(listaR05.get(j).getIat());
                        registroR05.setIppt(listaR05.get(j).getIppt());
                        registroR05.setCasasDecimaisQuantidade(listaR05.get(j).getCasasDecimaisQuantidade());
                        registroR05.setCasasDecimaisValorUnitario(listaR05.get(j).getCasasDecimaisValor());

                        pafR.getRegistroR().getListaRegistroR05().add(registroR05);
                    }
                }

                List<R07VO> listaR07 = registroRController.tabelaR07IdR04(listaR04.get(i).getId());
                if (listaR07 != null) {
                    for (int j = 0; j <= listaR07.size() - 1; j++) {
                        tripa = ""
                                + listaR07.get(j).getSerieEcf()
                                + listaR07.get(j).getCoo()
                                + listaR07.get(j).getCcf()
                                + listaR07.get(j).getGnf()
                                + listaR07.get(j).getHashIncremento();

                        RegistroR07 registroR07 = new RegistroR07();
                        registroR07.setNumeroFabricacao(r01.getSerieEcf());
                        registroR07.setMfAdicional(impressora.getMfd());
                        if (Biblioteca.MD5String(tripa).equals(listaR07.get(j).getHashTripa())) {
                            registroR07.setModeloEcf(impressora.getModelo());
                        } else {
                            registroR07.setModeloEcf(Biblioteca.repete("?", 20));
                        }
                        registroR07.setNumeroUsuario(listaR04.get(i).getIdOperador());
                        registroR07.setCoo(listaR07.get(j).getCoo());
                        registroR07.setCcf(listaR07.get(j).getCcf());
                        registroR07.setGnf(listaR07.get(j).getGnf());
                        registroR07.setMeioPagamento(listaR07.get(j).getMeioPagamento());
                        registroR07.setValorPago(listaR07.get(j).getValorPagamento());
                        registroR07.setIndicadorEstorno(listaR07.get(j).getIndicadorEstorno());
                        registroR07.setValorEstornado(listaR07.get(j).getValorEstorno());

                        pafR.getRegistroR().getListaRegistroR07().add(registroR07);
                    }
                }
            }

            List<R06VO> listaR06 = registroRController.tabelaR06(strDataIni, strDataFim, pIdImpressora);
            for (int i = 0; i <= listaR06.size() - 1; i++) {
                tripa = ""
                        + listaR06.get(i).getCoo()
                        + listaR06.get(i).getGnf()
                        + listaR06.get(i).getGrg()
                        + listaR06.get(i).getCdc()
                        + listaR06.get(i).getDenominacao()
                        + listaR06.get(i).getDataEmissao()
                        + listaR06.get(i).getHoraEmissao()
                        + listaR06.get(i).getSerieEcf()
                        + listaR06.get(i).getHashIncremento();

                RegistroR06 registroR06 = new RegistroR06();
                registroR06.setNumeroFabricacao(r01.getSerieEcf());
                registroR06.setMfAdicional(impressora.getMfd());
                if (Biblioteca.MD5String(tripa).equals(listaR06.get(i).getHashTripa())) {
                    registroR06.setModeloEcf(impressora.getModelo());
                } else {
                    registroR06.setModeloEcf(Biblioteca.repete("?", 20));
                }
                registroR06.setNumeroUsuario(listaR06.get(i).getIdOperador());
                registroR06.setCoo(listaR06.get(i).getCoo());
                registroR06.setGnf(listaR06.get(i).getGnf());
                registroR06.setGrg(listaR06.get(i).getGrg());
                registroR06.setCdc(listaR06.get(i).getCdc());
                registroR06.setDenominacao(listaR06.get(i).getDenominacao());
                registroR06.setDataFinalEmissao(listaR06.get(i).getDataEmissao());
                registroR06.setHoraFinalEmissao(listaR06.get(i).getHoraEmissao());

                pafR.getRegistroR().getListaRegistroR06().add(registroR06);
            }
            
            try {
                // TODO: -Gere o arquivo com o nome exigido pela espeficicação
                pafR.geraArquivoTxt(new File("PAF_R.txt"));
                RegistroEAD.assinarArquivo("PAF_R.txt");
            } catch (Exception e) {
                e.printStackTrace();
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}