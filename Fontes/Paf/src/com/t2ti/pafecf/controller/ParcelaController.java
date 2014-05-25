/**
 * <p>Title: T2TiPDV</p>
 *
 * <p>Description: Controller da parcela</p>
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
package com.t2ti.pafecf.controller;

import com.t2ti.pafecf.bd.AcessoBanco;
import com.t2ti.pafecf.infra.Biblioteca;
import com.t2ti.pafecf.infra.Paf;
import com.t2ti.pafecf.view.Caixa;
import com.t2ti.pafecf.vo.ContasPagarReceberVO;
import com.t2ti.pafecf.vo.ContasParcelasVO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ParcelaController {

    String consultaSQL;
    Statement stm;
    PreparedStatement pstm;
    ResultSet rs;
    AcessoBanco bd = new AcessoBanco();

    public ContasPagarReceberVO inserirCabecalho(ContasPagarReceberVO pParcelaCabecalho) {
        try {
            consultaSQL = "insert into CONTAS_PAGAR_RECEBER ("
                    + "ID_ECF_VENDA_CABECALHO, "
                    + "ID_PLANO_CONTAS, "
                    + "ID_TIPO_DOCUMENTO, "
                    + "ID_PESSOA, "
                    + "TIPO, "
                    + "NUMERO_DOCUMENTO, "
                    + "VALOR, "
                    + "DATA_LANCAMENTO, "
                    + "PRIMEIRO_VENCIMENTO, "
                    + "NATUREZA_LANCAMENTO, "
                    + "QUANTIDADE_PARCELA) "
                    + "values (?,?,?,?,?,?,?,?,?,?,?)";

            pstm = bd.conectar().prepareStatement(consultaSQL);
            pstm.setInt(1, pParcelaCabecalho.getIdEcfVendaCabecalho());
            pstm.setInt(2, pParcelaCabecalho.getIdPlanoContas());
            pstm.setInt(3, pParcelaCabecalho.getIdTipoDocumento());
            pstm.setInt(4, pParcelaCabecalho.getIdPessoa());
            pstm.setString(5, pParcelaCabecalho.getTipo());
            pstm.setString(6, pParcelaCabecalho.getNumeroDocumento());
            pstm.setDouble(7, pParcelaCabecalho.getValor());
            pstm.setDate(8, new java.sql.Date(pParcelaCabecalho.getDataLancamento().getTime()));
            pstm.setDate(9, new java.sql.Date(pParcelaCabecalho.getPrimeiroVencimento().getTime()));
            pstm.setString(10, pParcelaCabecalho.getNaturezaLancamento());
            pstm.setInt(11, pParcelaCabecalho.getQuantidadeParcela());

            pstm.executeUpdate();

            consultaSQL = "select max(ID) as ID from CONTAS_PAGAR_RECEBER";
            pstm = bd.conectar().prepareStatement(consultaSQL);
            rs = pstm.executeQuery();
            rs.first();
            pParcelaCabecalho.setId(rs.getInt("ID"));

            return pParcelaCabecalho;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            bd.desconectar();
        }
    }

    public void inserirDetalhe(List<ContasParcelasVO> pListaParcelaDetalhe) {
        try {
            consultaSQL = "insert into CONTAS_PARCELAS ("
                    + "ID_CONTAS_PAGAR_RECEBER, "
                    + "ID_MEIOS_PAGAMENTO, "
                    + "ID_CHEQUE_EMITIDO, "
                    + "ID_CONTA_CAIXA, "
                    + "DATA_EMISSAO, "
                    + "DATA_VENCIMENTO, "
                    + "NUMERO_PARCELA, "
                    + "VALOR, "
                    + "TAXA_JUROS, "
                    + "TAXA_MULTA, "
                    + "TAXA_DESCONTO, "
                    + "VALOR_JUROS, "
                    + "VALOR_MULTA, "
                    + "VALOR_DESCONTO, "
                    + "TOTAL_PARCELA, "
                    + "HISTORICO, "
                    + "SITUACAO) "
                    + "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

            ContasParcelasVO parcelaDetalhe;
            for (int i = 0; i < pListaParcelaDetalhe.size(); i++) {
                parcelaDetalhe = pListaParcelaDetalhe.get(i);

                pstm = bd.conectar().prepareStatement(consultaSQL);
                pstm.setInt(1, parcelaDetalhe.getIdContasPagarReceber());
                pstm.setInt(2, parcelaDetalhe.getIdMeiosPagamento());
                pstm.setInt(3, parcelaDetalhe.getIdChequeEmitido());
                pstm.setInt(4, parcelaDetalhe.getIdContaCaixa());
                pstm.setDate(5, new java.sql.Date(parcelaDetalhe.getDataEmissao().getTime()));
                pstm.setDate(6, new java.sql.Date(parcelaDetalhe.getDataVencimento().getTime()));
                pstm.setInt(7, parcelaDetalhe.getNumeroParcela());
                pstm.setDouble(8, parcelaDetalhe.getValor());
                pstm.setDouble(9, parcelaDetalhe.getTaxaJuros());
                pstm.setDouble(10, parcelaDetalhe.getTaxaMulta());
                pstm.setDouble(11, parcelaDetalhe.getTaxaDesconto());
                pstm.setDouble(12, parcelaDetalhe.getValorJuros());
                pstm.setDouble(13, parcelaDetalhe.getValorMulta());
                pstm.setDouble(14, parcelaDetalhe.getValorDesconto());
                pstm.setDouble(15, parcelaDetalhe.getTotalParcela());
                pstm.setString(16, parcelaDetalhe.getHistorico());
                pstm.setString(17, parcelaDetalhe.getSituacao());

                pstm.executeUpdate();
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            bd.desconectar();
        }
    }

    public ContasPagarReceberVO retornaCabecalhoDaParcela(Integer pIdVenda) {
        try {
            pstm = bd.conectar().prepareStatement(consultaSQL);
            rs = pstm.executeQuery(consultaSQL);
            rs.beforeFirst();
            if (rs.next()) {
                ContasPagarReceberVO parcelaCabecalho = new ContasPagarReceberVO();

                parcelaCabecalho.setId(rs.getInt("ID"));
                parcelaCabecalho.setIdEcfVendaCabecalho(rs.getInt("ID_ECF_VENDA_CABECALHO"));
                parcelaCabecalho.setIdPlanoContas(rs.getInt("ID_PLANO_CONTAS"));
                parcelaCabecalho.setIdTipoDocumento(rs.getInt("ID_TIPO_DOCUMENTO"));
                parcelaCabecalho.setIdPessoa(rs.getInt("ID_PESSOA"));
                parcelaCabecalho.setTipo(rs.getString("TIPO"));
                parcelaCabecalho.setNumeroDocumento(rs.getString("NUMERO_DOCUMENTO"));
                parcelaCabecalho.setValor(rs.getDouble("VALOR"));
                parcelaCabecalho.setDataLancamento(rs.getDate("DATA_LANCAMENTO"));
                parcelaCabecalho.setPrimeiroVencimento(rs.getDate("PRIMEIRO_VENCIMENTO"));
                parcelaCabecalho.setNaturezaLancamento(rs.getString("NATUREZA_LANCAMENTO"));
                parcelaCabecalho.setQuantidadeParcela(rs.getInt("QUANTIDADE_PARCELA"));
                return parcelaCabecalho;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            bd.desconectar();
        }
    }

    public List<ContasParcelasVO> retornaDetalheDaParcela(Integer pIdCabecalho) {
        try {
            stm = bd.conectar().createStatement();
            rs = stm.executeQuery("select count(*) as TOTAL from CONTAS_PARCELAS where ID_CONTAS_PAGAR_RECEBER=" + pIdCabecalho);
            rs.first();
            Integer totalRegistros = rs.getInt("TOTAL");

            if (totalRegistros > 0) {
                List<ContasParcelasVO> listaParcelas = new ArrayList<ContasParcelasVO>();
                consultaSQL = "select count(*) as TOTAL from CONTAS_PARCELAS where ID_CONTAS_PAGAR_RECEBER=" + pIdCabecalho;

                stm = bd.conectar().createStatement();
                rs = stm.executeQuery(consultaSQL);
                rs.beforeFirst();
                while (rs.next()) {
                    ContasParcelasVO parcelaDetalhe = new ContasParcelasVO();

                    parcelaDetalhe.setId(rs.getInt("ID"));
                    parcelaDetalhe.setIdContasPagarReceber(rs.getInt("ID_CONTAS_PAGAR_RECEBER"));
                    parcelaDetalhe.setIdMeiosPagamento(rs.getInt("ID_MEIOS_PAGAMENTO"));
                    parcelaDetalhe.setIdChequeEmitido(rs.getInt("ID_CHEQUE_EMITIDO"));
                    parcelaDetalhe.setIdContaCaixa(rs.getInt("ID_CONTA_CAIXA"));
                    parcelaDetalhe.setDataEmissao(rs.getDate("DATA_EMISSAO"));
                    parcelaDetalhe.setDataVencimento(rs.getDate("DATA_VENCIMENTO"));
                    parcelaDetalhe.setNumeroParcela(rs.getInt("NUMERO_PARCELA"));
                    parcelaDetalhe.setValor(rs.getDouble("VALOR"));
                    parcelaDetalhe.setTaxaJuros(rs.getDouble("TAXA_JUROS"));
                    parcelaDetalhe.setTaxaMulta(rs.getDouble("TAXA_MULTA"));
                    parcelaDetalhe.setTaxaDesconto(rs.getDouble("TAXA_DESCONTO"));
                    parcelaDetalhe.setValorJuros(rs.getDouble("VALOR_JUROS"));
                    parcelaDetalhe.setValorMulta(rs.getDouble("VALOR_MULTA"));
                    parcelaDetalhe.setValorDesconto(rs.getDouble("VALOR_DESCONTO"));
                    parcelaDetalhe.setTotalParcela(rs.getDouble("TOTAL_PARCELA"));
                    parcelaDetalhe.setHistorico(rs.getString("HISTORICO"));
                    parcelaDetalhe.setSituacao(rs.getString("SITUACAO"));

                    listaParcelas.add(parcelaDetalhe);
                }
                return listaParcelas;
            } else {
                //caso nao existam registros retorna nulo
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            bd.desconectar();
        }
    }

    public void imprimeParcelas(String pNome, String pCpf, String pCoo, Double pValorTotal, List<ContasParcelasVO> pListaParcelaDetalhe) {
        try {
            SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");

            String contrato =
                    "Pelo presente instrumento particular de Confissão e Assunção de "
                    + "Dívida que entre si fazem, de um lado, <NomeCliente>, inscrito no "
                    + "CPF sob o nº <CPFCliente>, aqui designada simplesmente DEVEDORA e, de outro"
                    + "lado, <QualificaEmpresa>, doravante denominada simplesmente CREDORA, "
                    + "pactuam a CONFISSÃO E ASSUNÇÃO DE DÍVIDA, segundo as cláusulas e condições abaixo enumeradas:"
                    + "01- A CREDORA ajustou com a DEVEDORA venda de mercadoria de acordo com Cupom Fiscal nº <COO>, "
                    + "em data de <DataVenda>, no qual esta assumiu débito no valor de <ValorTotalVenda>; "
                    + "02- Reconhecendo seu débito - em sua certeza, liquidez e exigibilidade -, a DEVEDORA se "
                    + "compromete a pagar a quantia da seguinte forma:";

            contrato = contrato.replace("<NomeCliente>", pNome);
            contrato = contrato.replace("<CPFCliente>", pCpf);
            contrato = contrato.replace("<QualificaEmpresa>", Caixa.configuracao.getTituloTelaCaixa());
            contrato = contrato.replace("<COO>", pCoo);
            contrato = contrato.replace("<DataVenda>", formatoData.format(Caixa.aCBrECF.getDataHora()));
            contrato = contrato.replace("<ValorTotalVenda>", pValorTotal.toString());

            //Caixa.aCBrECF.abreRelatorioGerencial(Caixa.configuracao.getGerencialIdentificacaoPaf());
            Caixa.aCBrECF.linhaRelatorioGerencial(Biblioteca.repete("=", 48), 0);
            Caixa.aCBrECF.linhaRelatorioGerencial("        TERMO DE COMPROMISSO CONTRATUAL         ", 0);
            Caixa.aCBrECF.linhaRelatorioGerencial("", 0);


            Integer elementos = contrato.length(); // Quantas letras tem o contrato
            Integer linhas = elementos / 47;   // divide pelo numero de colunas, no caso 48
            Integer adicional = elementos % 47;   // Caso sobre algo da divisao, indica que ha mais uma linha a ser impressa.

            if (adicional > 0) {
                linhas++;
            }

            elementos = 0;

            for (int i = 1; i < linhas; i++) {
                Caixa.aCBrECF.linhaRelatorioGerencial(contrato.substring(elementos, 47), 0);
                elementos = elementos + 48;
            }
            Caixa.aCBrECF.linhaRelatorioGerencial("", 0);
            Caixa.aCBrECF.linhaRelatorioGerencial("", 0);

            Caixa.aCBrECF.linhaRelatorioGerencial(Biblioteca.repete("_", 48), 0);
            Caixa.aCBrECF.linhaRelatorioGerencial("            VALOR       PARCELA    VENCIMENTO   ", 0);
            Caixa.aCBrECF.linhaRelatorioGerencial(Biblioteca.repete("_", 48), 0);

            ContasParcelasVO parcelas;
            for (int i = 0; i < pListaParcelaDetalhe.size() - 1; i++) {
                parcelas = pListaParcelaDetalhe.get(i);
                Caixa.aCBrECF.linhaRelatorioGerencial(parcelas.getValor() + "" + parcelas.getNumeroParcela() + parcelas.getDataVencimento(), 0);
            }

            Caixa.aCBrECF.linhaRelatorioGerencial("", 0);
            Caixa.aCBrECF.linhaRelatorioGerencial("", 0);
            Caixa.aCBrECF.linhaRelatorioGerencial("", 0);
            Caixa.aCBrECF.linhaRelatorioGerencial("", 0);

            Caixa.aCBrECF.linhaRelatorioGerencial("    ________________________________________    ", 0);
            Caixa.aCBrECF.linhaRelatorioGerencial(pNome, 0);
            Caixa.aCBrECF.linhaRelatorioGerencial(pCpf, 0);

            Caixa.aCBrECF.linhaRelatorioGerencial("", 0);
            Caixa.aCBrECF.linhaRelatorioGerencial("", 0);
            Caixa.aCBrECF.linhaRelatorioGerencial(Biblioteca.repete("=", 48), 0);
            Caixa.aCBrECF.fechaRelatorio();
            
            Paf.gravaR06("RG");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
