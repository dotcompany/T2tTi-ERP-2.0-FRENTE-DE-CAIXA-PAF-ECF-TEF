/**
 * <p>Title: T2TiPDV</p>
 *
 * <p>Description: Controller do Sped Fiscal</p>
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
import com.t2ti.pafecf.view.Caixa;
import com.t2ti.pafecf.vo.ClienteVO;
import com.t2ti.pafecf.vo.ContadorVO;
import com.t2ti.pafecf.vo.EmpresaVO;
import com.t2ti.pafecf.vo.ImpressoraVO;
import com.t2ti.pafecf.vo.MeiosPagamentoVO;
import com.t2ti.pafecf.vo.NotaFiscalCabecalhoVO;
import com.t2ti.pafecf.vo.ProdutoVO;
import com.t2ti.pafecf.vo.R02VO;
import com.t2ti.pafecf.vo.R03VO;
import com.t2ti.pafecf.vo.R04VO;
import com.t2ti.pafecf.vo.R05VO;
import com.t2ti.pafecf.vo.SpedFiscalC321VO;
import com.t2ti.pafecf.vo.SpedFiscalC370VO;
import com.t2ti.pafecf.vo.SpedFiscalC390VO;
import com.t2ti.pafecf.vo.SpedFiscalC425VO;
import com.t2ti.pafecf.vo.SpedFiscalC490VO;
import com.t2ti.pafecf.vo.UnidadeProdutoVO;
import com.t2ti.plugins.sped.fiscal.SpedFiscal;
import com.t2ti.plugins.sped.fiscal.bloco0.Registro0150;
import com.t2ti.plugins.sped.fiscal.bloco0.Registro0190;
import com.t2ti.plugins.sped.fiscal.bloco0.Registro0200;
import com.t2ti.plugins.sped.fiscal.blococ.RegistroC100;
import com.t2ti.plugins.sped.fiscal.blococ.RegistroC114;
import com.t2ti.plugins.sped.fiscal.blococ.RegistroC190;
import com.t2ti.plugins.sped.fiscal.blococ.RegistroC300;
import com.t2ti.plugins.sped.fiscal.blococ.RegistroC310;
import com.t2ti.plugins.sped.fiscal.blococ.RegistroC320;
import com.t2ti.plugins.sped.fiscal.blococ.RegistroC321;
import com.t2ti.plugins.sped.fiscal.blococ.RegistroC350;
import com.t2ti.plugins.sped.fiscal.blococ.RegistroC370;
import com.t2ti.plugins.sped.fiscal.blococ.RegistroC390;
import com.t2ti.plugins.sped.fiscal.blococ.RegistroC400;
import com.t2ti.plugins.sped.fiscal.blococ.RegistroC405;
import com.t2ti.plugins.sped.fiscal.blococ.RegistroC420;
import com.t2ti.plugins.sped.fiscal.blococ.RegistroC425;
import com.t2ti.plugins.sped.fiscal.blococ.RegistroC460;
import com.t2ti.plugins.sped.fiscal.blococ.RegistroC470;
import com.t2ti.plugins.sped.fiscal.blococ.RegistroC490;
import com.t2ti.plugins.sped.fiscal.blocoe.RegistroE100;
import com.t2ti.plugins.sped.fiscal.blocoe.RegistroE110;
import com.t2ti.plugins.util.RegistroEAD;
import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SpedFiscalController {

    String consultaSQL;
    Statement stm;
    PreparedStatement pstm;
    ResultSet rs;
    AcessoBanco bd = new AcessoBanco();
    // Variáveis utilizadas na geração do arquivo
    SpedFiscal spedFiscal;
    String strDataIni = "";
    String strDataFim = "";
    Integer versaoLeiaute;
    Integer finalidadeArquivo;
    Integer perfilApresentacao;
    Date dataInicio;
    Date dataFim;

    // TODO: -Implemente o método abaixo
    public List<SpedFiscalC370VO> tabelaC370(Integer pId) {
        consultaSQL =
                "select * from VIEW_C370 "
                + "where ID_NF_CABECALHO = " + pId;

        return new ArrayList<SpedFiscalC370VO>();
    }

    // TODO: -Implemente o método abaixo
    public List<SpedFiscalC390VO> tabelaC390(Integer pId) {
        consultaSQL =
                "select * from VIEW_C390 "
                + "where ID_NF_CABECALHO = " + pId;

        return new ArrayList<SpedFiscalC390VO>();
    }

    // TODO: -Implemente o método abaixo 
    public List<SpedFiscalC321VO> tabelaC321(String pDataInicio, String pDataFim) {

        consultaSQL =
                "select * from VIEW_C321 "
                + "where DATA_EMISSAO between '"
                + pDataInicio + "' and '" + pDataFim + "'";

        return new ArrayList<SpedFiscalC321VO>();
    }

    // TODO: -Implemente o método abaixo 
    public List<SpedFiscalC425VO> tabelaC425(String pDataInicio, String pDataFim, String pTotalizadorParcial) {

        consultaSQL =
                "select * from VIEW_C425 "
                + "where "
                + "(DATA_VENDA between '"
                + pDataInicio + "' and '" + pDataFim
                + "') and TOTALIZADOR_PARCIAL = " + pTotalizadorParcial;

        return new ArrayList<SpedFiscalC425VO>();
    }

    // TODO: -Implemente o método abaixo 
    public List<SpedFiscalC490VO> tabelaC490(String pDataInicio, String pDataFim) {

        consultaSQL =
                "select * from VIEW_C490 "
                + "where DATA_EMISSAO between '"
                + pDataInicio + "' and '" + pDataFim + "'";

        return new ArrayList<SpedFiscalC490VO>();
    }

    // TODO: -Implemente o método abaixo 
    public List<MeiosPagamentoVO> tabelaE110(String pDataInicio, String pDataFim) {

        consultaSQL =
                "select sum(soma_icms) as soma_icms from VIEW_E110 "
                + "where "
                + "DATA_EMISSAO between '"
                + pDataInicio + "' and '" + pDataFim
                + "' group by extract(year from data_emissao), extract(month from data_emissao)";

        return new ArrayList<MeiosPagamentoVO>();
    }

    ////////////////////////////////////////////////////////////////////////////
    ///               Geração do arquivo do Sped para o PAF                  ///
    ////////////////////////////////////////////////////////////////////////////
    public void gerarArquivoSpedFiscal(Date pDataInicio, Date pDataFim, Integer pVersao, Integer pFinalidade, Integer pPerfil) {

        SimpleDateFormat formataData = null;
        formataData = new SimpleDateFormat("yyyy-MM-dd");
        this.strDataIni = formataData.format(pDataInicio);
        this.strDataFim = formataData.format(pDataFim);
        this.versaoLeiaute = pVersao;
        this.finalidadeArquivo = pFinalidade;
        this.perfilApresentacao = pPerfil;
        this.dataInicio = pDataInicio;
        this.dataFim = pDataFim;

        spedFiscal = new SpedFiscal();
        spedFiscal.getBloco0().getRegistro0000().setDtIni(pDataInicio);
        spedFiscal.getBloco0().getRegistro0000().setDtFin(pDataFim);

        gerarBloco0();
        gerarBlocoC();
        gerarBlocoE();
        gerarBlocoH();

        try {
            // TODO: -Gere o arquivo com o nome exigido pela espeficicação
            spedFiscal.geraArquivoTxt(new File("SpedFiscal.txt"));
            RegistroEAD.assinarArquivo("SpedFiscal.txt");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void gerarBloco0() {
        EmpresaVO empresa = new EmpresaController().pegaEmpresa(Caixa.configuracao.getIdEmpresa());
        ContadorVO contador = new ContadorController().pegaContador();
        List<UnidadeProdutoVO> listaUnidade = new UnidadeController().unidadeSped(strDataIni, strDataFim);
        List<ProdutoVO> listaProduto = new ProdutoController().consultaProdutoSped(strDataIni, strDataFim, perfilApresentacao);
        // TODO: -Implemente a busca por NF-e na sua retaguarda
        //List<ClienteVO> listaCliente = new ClienteController().consultaClienteSped(strDataIni, strDataFim);
        List<ClienteVO> listaCliente = new ArrayList<ClienteVO>();

        // Registro 0000
        spedFiscal.getBloco0().getRegistro0000().setCodVer(this.versaoLeiaute.toString());
        spedFiscal.getBloco0().getRegistro0000().setCodFin(this.finalidadeArquivo.toString());
        spedFiscal.getBloco0().getRegistro0000().setIndPerfil(this.perfilApresentacao.toString());
        spedFiscal.getBloco0().getRegistro0000().setNome(empresa.getRazaoSocial());
        spedFiscal.getBloco0().getRegistro0000().setCnpj(empresa.getCnpj());
        spedFiscal.getBloco0().getRegistro0000().setCpf("");
        spedFiscal.getBloco0().getRegistro0000().setUf(empresa.getUf());
        spedFiscal.getBloco0().getRegistro0000().setIe(empresa.getInscricaoEstadual());
        spedFiscal.getBloco0().getRegistro0000().setCodMun(empresa.getCodigoIbgeCidade());
        spedFiscal.getBloco0().getRegistro0000().setIm(empresa.getInscricaoMunicipal());
        spedFiscal.getBloco0().getRegistro0000().setSuframa(empresa.getSuframa());
        spedFiscal.getBloco0().getRegistro0000().setIndAtiv("1");

        // Registro 0001
        spedFiscal.getBloco0().getRegistro0001().setIndMov(0);

        // Registro 0005
        spedFiscal.getBloco0().getRegistro0001().getRegistro0005().setFantasia(empresa.getNomeFantasia());
        spedFiscal.getBloco0().getRegistro0001().getRegistro0005().setCep(empresa.getCep());
        spedFiscal.getBloco0().getRegistro0001().getRegistro0005().setEndereco(empresa.getLogradouro());
        spedFiscal.getBloco0().getRegistro0001().getRegistro0005().setNum(empresa.getNumero());
        spedFiscal.getBloco0().getRegistro0001().getRegistro0005().setCompl(empresa.getComplemento());
        spedFiscal.getBloco0().getRegistro0001().getRegistro0005().setBairro(empresa.getBairro());
        spedFiscal.getBloco0().getRegistro0001().getRegistro0005().setFone(empresa.getFone());
        spedFiscal.getBloco0().getRegistro0001().getRegistro0005().setFax(empresa.getFax());
        spedFiscal.getBloco0().getRegistro0001().getRegistro0005().setEmail(empresa.getEmail());

        // Registro 0100
        spedFiscal.getBloco0().getRegistro0001().getRegistro0100().setNome(contador.getNome());
        spedFiscal.getBloco0().getRegistro0001().getRegistro0100().setCpf(contador.getCpf());
        spedFiscal.getBloco0().getRegistro0001().getRegistro0100().setCrc(contador.getInscricaoCrc());
        spedFiscal.getBloco0().getRegistro0001().getRegistro0100().setCnpj(contador.getCnpj());
        spedFiscal.getBloco0().getRegistro0001().getRegistro0100().setCep(contador.getCep());
        spedFiscal.getBloco0().getRegistro0001().getRegistro0100().setEndereco(contador.getLogradouro());
        spedFiscal.getBloco0().getRegistro0001().getRegistro0100().setNum(contador.getNumero().toString());
        spedFiscal.getBloco0().getRegistro0001().getRegistro0100().setCompl(contador.getComplemento());
        spedFiscal.getBloco0().getRegistro0001().getRegistro0100().setBairro(contador.getBairro());
        spedFiscal.getBloco0().getRegistro0001().getRegistro0100().setFone(contador.getFone());
        spedFiscal.getBloco0().getRegistro0001().getRegistro0100().setFax(contador.getFax());
        spedFiscal.getBloco0().getRegistro0001().getRegistro0100().setEmail(contador.getEmail());
        spedFiscal.getBloco0().getRegistro0001().getRegistro0100().setCodMun(contador.getCodigoMunicipio());

        if (listaCliente != null) {
            for (int i = 0; i < listaCliente.size(); i++) {
                // Registro 0150
                Registro0150 registro0150 = new Registro0150();
                registro0150.setCodPart(listaCliente.get(i).getId().toString());
                registro0150.setNome(listaCliente.get(i).getNome());
                registro0150.setCodPais("1058");
                if (listaCliente.get(i).getTipoPessoa().equals("F")) {
                    registro0150.setCpf(listaCliente.get(i).getCpfOuCnpj());
                } else {
                    registro0150.setCnpj(listaCliente.get(i).getCpfOuCnpj());
                }
                registro0150.setIe(listaCliente.get(i).getInscricaoEstadual());
                registro0150.setCodMun(listaCliente.get(i).getCodigoIbgeCidade());
                registro0150.setSuframa("");
                registro0150.setEndereco(listaCliente.get(i).getLogradouro());
                registro0150.setCompl(listaCliente.get(i).getComplemento());
                registro0150.setBairro(listaCliente.get(i).getBairro());

                spedFiscal.getBloco0().getRegistro0001().getRegistro0150List().add(registro0150);
            }
        }

        if (listaUnidade != null) {
            for (int i = 0; i < listaUnidade.size(); i++) {
                // Registro 0190
                Registro0190 registro0190 = new Registro0190();
                registro0190.setUnid(listaUnidade.get(i).getId().toString());
                registro0190.setDescr(listaUnidade.get(i).getNome());

                spedFiscal.getBloco0().getRegistro0001().getRegistro0190List().add(registro0190);
            }
        }

        if (listaProduto != null) {
            for (int i = 0; i < listaProduto.size(); i++) {
                // Registro 0200
                Registro0200 registro0200 = new Registro0200();
                registro0200.setCodItem(listaProduto.get(i).getId().toString());
                registro0200.setDescrItem(listaProduto.get(i).getNome());
                registro0200.setCodBarra(listaProduto.get(i).getGtin());
                registro0200.setCodAntItem("");
                registro0200.setUnidInv(listaProduto.get(i).getIdUnidade().toString());
                registro0200.setTipoItem(listaProduto.get(i).getTipoItemSped());
                registro0200.setCodNcm(listaProduto.get(i).getNcm());
                registro0200.setExIpi("");
                registro0200.setCodGen(listaProduto.get(i).getNcm().substring(0, 2));
                registro0200.setCodLst("");
                registro0200.setAliqIcms(listaProduto.get(i).getTaxaIcms());

                spedFiscal.getBloco0().getRegistro0001().getRegistro0200List().add(registro0200);
            }
        }
    }

    
    //TODO: -Verifique com calma o procedimento abaixo, corrija o que for necessário
    public void gerarBlocoC() {

        // Registro C001
        spedFiscal.getBlocoC().getRegistroC001().setIndMov(0);

        List<NotaFiscalCabecalhoVO> listaNf2Cabecalho = new NotaFiscalController().consultaNotaCabecalhoSped(strDataIni, strDataFim);

        // TODO: -Implemente a busca por NF-e na sua retaguarda
        List listaNfeCabecalho = new ArrayList();

        /////////////////
        /// Perfil A ////
        /////////////////
        if (perfilApresentacao == 0) {
            if (listaNfeCabecalho != null) {
                for (int i = 0; i < listaNfeCabecalho.size() - 1; i++) {
                    // Registro C100
                    RegistroC100 registroC100 = new RegistroC100();
                    registroC100.setIndOper("1");
                    registroC100.setIndEmit("0");
                    registroC100.setCodPart("1");
                    registroC100.setCodMod("1");
                    registroC100.setCodSit("");
                    registroC100.setSer("1");
                    registroC100.setNumDoc("1");
                    registroC100.setChvNfe("1");
                    registroC100.setDtDoc(new Date());
                    registroC100.setDtES(new Date());
                    registroC100.setVlDoc(1D);
                    registroC100.setIndPgto("");
                    registroC100.setVlDesc(0D);
                    registroC100.setVlAbatNt(0D);
                    registroC100.setVlMerc(0D);
                    registroC100.setIndFrt("");
                    registroC100.setVlFrt(0D);
                    registroC100.setVlSeg(0D);
                    registroC100.setVlOutDa(0D);
                    registroC100.setVlBcIcms(0D);
                    registroC100.setVlIcms(0D);
                    registroC100.setVlBcIcmsSt(0D);
                    registroC100.setVlIcmsSt(0D);
                    registroC100.setVlIpi(0D);
                    registroC100.setVlPis(0D);
                    registroC100.setVlCofins(0D);
                    registroC100.setVlPisSt(0D);
                    registroC100.setVlCofinsSt(0D);

                    spedFiscal.getBlocoC().getRegistroC001().getRegistroC100List().add(registroC100);

                    // Registro C114
                    // TODO: -Implemente a busca por NF-e na sua retaguarda - Observe o Registro C110
                    List listaCupomVinculado = new ArrayList();
                    if (listaCupomVinculado != null) {
                        for (int j = 0; j < listaCupomVinculado.size() - 1; j++) {
                            RegistroC114 registroC114 = new RegistroC114();
                            registroC114.setCodMod("1");
                            registroC114.setEcfFab("1");
                            registroC114.setEcfCx("1");
                            registroC114.setNumDoc("1");
                            registroC114.setDtDoc(new Date());
                        }
                    }

                    // Registro C190
                    // TODO: -Implemente a busca por NF-e na sua retaguarda
                    List listaNfeAnalitico = new ArrayList();
                    if (listaNfeAnalitico != null) {
                        for (int j = 0; j < listaNfeAnalitico.size() - 1; j++) {
                            RegistroC190 registroC190 = new RegistroC190();
                            registroC190.setCstIcms("1");
                            registroC190.setCfop("1");
                            registroC190.setAliqIcms(0D);
                            registroC190.setVlOpr(1D);
                            registroC190.setVlBcIcms(0D);
                            registroC190.setVlIcms(0D);
                            registroC190.setVlBcIcmsSt(0D);
                            registroC190.setVlIcmsSt(0D);
                            registroC190.setVlRedBc(0D);
                            registroC190.setVlIpi(0D);
                            registroC190.setCodObs("");

                            spedFiscal.getBlocoC().getRegistroC001().getRegistroC100List().get(i).getRegistroC190List().add(registroC190);
                        }
                    }
                }
            } //if (listaNfeCabecalho != null)


            if (listaNf2Cabecalho != null) {
                for (int i = 0; i < listaNf2Cabecalho.size() - 1; i++) {
                    // Registro C350
                    RegistroC350 registroC350 = new RegistroC350();
                    registroC350.setSer(listaNf2Cabecalho.get(i).getSerie());
                    registroC350.setSubSer(listaNf2Cabecalho.get(i).getSubserie());
                    registroC350.setNumDoc(listaNf2Cabecalho.get(i).getNumero());
                    registroC350.setDtDoc(listaNf2Cabecalho.get(i).getDataEmissao());
                    registroC350.setCnpjCpf(listaNf2Cabecalho.get(i).getCpfCnpjCliente());
                    registroC350.setVlMerc(listaNf2Cabecalho.get(i).getTotalProdutos());
                    registroC350.setVlDoc(listaNf2Cabecalho.get(i).getTotalNf());
                    registroC350.setVlDesc(listaNf2Cabecalho.get(i).getDesconto());
                    registroC350.setVlPis(listaNf2Cabecalho.get(i).getPis());
                    registroC350.setVlCofins(listaNf2Cabecalho.get(i).getCofins());
                    registroC350.setCodCta("");

                    spedFiscal.getBlocoC().getRegistroC001().getRegistroC350List().add(registroC350);

                    // Registro C370
                    List<SpedFiscalC370VO> listaC370 = tabelaC370(listaNf2Cabecalho.get(i).getId());
                    if (listaC370 != null) {
                        for (int j = 0; j < listaC370.size() - 1; j++) {
                            RegistroC370 registroC370 = new RegistroC370();
                            registroC370.setNumItem(listaC370.get(j).getItem().toString());
                            registroC370.setCodItem(listaC370.get(j).getIdProduto().toString());
                            registroC370.setQtd(listaC370.get(j).getQuantidade());
                            registroC370.setUnid(listaC370.get(j).getIdUnidade().toString());
                            registroC370.setVlItem(listaC370.get(j).getValor());
                            registroC370.setVlDesc(listaC370.get(j).getDesconto());

                            spedFiscal.getBlocoC().getRegistroC001().getRegistroC350List().get(i).getRegistroC370List().add(registroC370);
                        }
                    }

                    // Registro C390
                    List<SpedFiscalC390VO> listaC390 = tabelaC390(listaNf2Cabecalho.get(i).getId());
                    if (listaC390 != null) {
                        for (int j = 0; j < listaC390.size() - 1; j++) {
                            RegistroC390 registroC390 = new RegistroC390();
                            registroC390.setCstIcms(listaC390.get(j).getCst());
                            registroC390.setCfop(listaC390.get(j).getCfop().toString());
                            registroC390.setAliqIcms(listaC390.get(j).getTaxaIcms());
                            registroC390.setVlOpr(listaC390.get(j).getSomaValor());
                            registroC390.setVlBcIcms(listaC390.get(j).getSomaBaseIcms());
                            registroC390.setVlIcms(listaC390.get(j).getSomaIcms());
                            registroC390.setVlRedBc(listaC390.get(j).getSomaIcmsOutras());

                            spedFiscal.getBlocoC().getRegistroC001().getRegistroC350List().get(i).getRegistroC390List().add(registroC390);
                        }
                    }
                }
            }
        } // Fim Perfil A


        /////////////////
        /// Perfil B ////
        /////////////////
        if (perfilApresentacao == 1) {
            if (listaNf2Cabecalho != null) {
                for (int i = 0; i < listaNf2Cabecalho.size() - 1; i++) {
                    // Registro C300
                    RegistroC300 registroC300 = new RegistroC300();
                    registroC300.setCodMod("02");
                    registroC300.setSer(listaNf2Cabecalho.get(i).getSerie());
                    registroC300.setSub(listaNf2Cabecalho.get(i).getSubserie());
                    registroC300.setDtDoc(listaNf2Cabecalho.get(i).getDataEmissao());
                    registroC300.setVlDoc(listaNf2Cabecalho.get(i).getTotalNf());
                    registroC300.setVlPis(listaNf2Cabecalho.get(i).getPis());
                    registroC300.setVlCofins(listaNf2Cabecalho.get(i).getCofins());
                    registroC300.setCodCta("");

                    spedFiscal.getBlocoC().getRegistroC001().getRegistroC300List().add(registroC300);

                    // Registro C310
                    List listaC310 = new ArrayList();
                    // TODO: -Implemente a busca pelas NF2 canceladas
                    if (listaC310 != null) {
                        for (int j = 0; j < listaC310.size() - 1; j++) {
                            RegistroC310 registroC310 = new RegistroC310();
                            registroC310.setNumDocCanc("1");

                            spedFiscal.getBlocoC().getRegistroC001().getRegistroC300List().get(i).getRegistroC310List().add(registroC310);
                        }
                    }

                    // Registro C320 -- igual ao C390
                    List<SpedFiscalC390VO> listaC390 = tabelaC390(listaNf2Cabecalho.get(i).getId());
                    if (listaC390 != null) {
                        for (int j = 0; j < listaC390.size() - 1; j++) {
                            RegistroC320 registroC320 = new RegistroC320();
                            registroC320.setCstIcms(listaC390.get(j).getCst());
                            registroC320.setCfop(listaC390.get(j).getCfop().toString());
                            registroC320.setAliqIcms(listaC390.get(j).getTaxaIcms());
                            registroC320.setVlOpr(listaC390.get(j).getSomaValor());
                            registroC320.setVlBcIcms(listaC390.get(j).getSomaBaseIcms());
                            registroC320.setVlIcms(listaC390.get(j).getSomaIcms());
                            registroC320.setVlRedBc(listaC390.get(j).getSomaIcmsOutras());

                            spedFiscal.getBlocoC().getRegistroC001().getRegistroC300List().get(i).getRegistroC320List().add(registroC320);

                            // Registro C321
                            List<SpedFiscalC321VO> listaC321 = tabelaC321(strDataIni, strDataFim);
                            if (listaC321 != null) {
                                for (int k = 0; k < listaC321.size() - 1; k++) {
                                    RegistroC321 registroC321 = new RegistroC321();
                                    registroC321.setCodItem(listaC321.get(k).getIdProduto().toString());
                                    registroC321.setQtd(listaC321.get(k).getSomaQuantidade());
                                    registroC321.setUnid(listaC321.get(k).getDescricaoUnidade());
                                    registroC321.setVlItem(listaC321.get(k).getSomaValor());
                                    registroC321.setVlDesc(listaC321.get(k).getSomaDesconto());
                                    registroC321.setVlBcIcms(listaC321.get(j).getSomaBaseIcms());
                                    registroC321.setVlIcms(listaC321.get(j).getSomaIcms());
                                    registroC321.setVlPis(listaC321.get(j).getSomaPis());
                                    registroC321.setVlCofins(listaC321.get(j).getSomaCofins());

                                    spedFiscal.getBlocoC().getRegistroC001().getRegistroC300List().get(i).getRegistroC320List().get(j).getRegistroC321List().add(registroC321);
                                }
                            }
                        }
                    }
                }
            }
        } // Fim Perfil B


        /////////////////
        ///  Ambos   ////
        /////////////////
        List<ImpressoraVO> listaImpressora = new ImpressoraController().tabelaImpressora();
        
        int indice400 = -1;
        
        if (listaImpressora != null) {
            for (int i = 0; i < listaImpressora.size() - 1; i++) {
                // verifica se existe movimento no periodo para aquele ECF
                List<R02VO> listaR02 = new RegistroRController().tabelaR02(strDataIni, strDataFim, listaImpressora.get(i).getId());
                
                if (listaR02 != null) {
                    // Registro C400
                    RegistroC400 registroC400 = new RegistroC400();
                    indice400++;
                    registroC400.setCodMod(listaImpressora.get(i).getModeloDocumentoFiscal());
                    registroC400.setEcfMod(listaImpressora.get(i).getModelo());
                    registroC400.setEcfFab(listaImpressora.get(i).getSerie());
                    registroC400.setEcfCx(listaImpressora.get(i).getNumeroEcf());

                    spedFiscal.getBlocoC().getRegistroC001().getRegistroC400List().add(registroC400);

                    // Registro C405
                    for (int j = 0; j < listaR02.size() - 1; j++) {
                        RegistroC405 registroC405 = new RegistroC405();
                        registroC405.setDtDoc(listaR02.get(j).getDataMovimento());
                        registroC405.setCro(listaR02.get(j).getCro());
                        registroC405.setCrz(listaR02.get(j).getCrz());
                        registroC405.setNumCooFin(listaR02.get(j).getCoo());
                        registroC405.setGtFin(listaR02.get(j).getValorGrandeTotal());
                        registroC405.setVlBrt(listaR02.get(j).getValorVendaBruta());
                        
                        spedFiscal.getBlocoC().getRegistroC001().getRegistroC400List().get(indice400).getRegistroC405List().add(registroC405);

                        // Registro C420
                        List<R03VO> listaR03 = new RegistroRController().tabelaR03(listaR02.get(j).getId());
                        for (int k = 0; k < listaR03.size() - 1; k++) {
                            RegistroC420 registroC420 = new RegistroC420();
                            registroC420.setCodTotPar(listaR03.get(k).getTotalizadorParcial());
                            registroC420.setVlrAcumTot(listaR03.get(k).getValorAcumulado());
                            registroC420.setNrTot(0);

                            spedFiscal.getBlocoC().getRegistroC001().getRegistroC400List().get(indice400).getRegistroC405List().get(j).getRegistroC420List().add(registroC420);

                            // Registro C425
                            List<SpedFiscalC425VO> listaC425 = tabelaC425(strDataIni, strDataFim, listaR03.get(k).getTotalizadorParcial());
                            for (int l = 0; l < listaC425.size() - 1; l++) {
                                RegistroC425 registroC425 = new RegistroC425();
                                registroC425.setCodItem(listaC425.get(l).getIdProduto().toString());
                                registroC425.setUnid(listaC425.get(l).getIdUnidade().toString());
                                registroC425.setQtd(listaC425.get(l).getSomaQuantidade());
                                registroC425.setVlItem(listaC425.get(l).getSomaValor());
                                registroC425.setVlPis(listaC425.get(l).getSomaPis());
                                registroC425.setVlCofins(listaC425.get(l).getSomaCofins());

                                spedFiscal.getBlocoC().getRegistroC001().getRegistroC400List().get(indice400).getRegistroC405List().get(j).getRegistroC420List().get(l).getRegistroC425List().add(registroC425);
                            }
                        }
                        
                        // se tiver o perfil A, gera o C460 com C470
                        if (perfilApresentacao == 0) {
                            // Registro C460
                            List<R04VO> listaR04 = new RegistroRController().tabelaR04(strDataIni, strDataFim, listaImpressora.get(i).getId());
                            if (listaR04 != null) {
                                for (int l = 0; l < listaR04.size() - 1; l++) {
                                    RegistroC460 registroC460 = new RegistroC460();
                                    registroC460.setCodMod("02");
                                    registroC460.setNumDoc(listaR04.get(l).getCoo().toString());

                                    if (listaR04.get(l).getStatusVenda().equals("C")) {
                                        registroC460.setCodSit("02");
                                    } else {
                                        registroC460.setCodSit("00");
                                    }
                                    
                                    if (registroC460.getCodSit().equals("00"))
                                    {
                                        registroC460.setDtDoc(listaR04.get(l).getDataEmissao());
                                        registroC460.setVlDoc(listaR04.get(l).getValorLiquido());
                                        registroC460.setVlPis(listaR04.get(l).getPis());
                                        registroC460.setVlCofins(listaR04.get(l).getCofins());
                                        registroC460.setCpfCnpj(listaR04.get(l).getCpfCnpj());
                                        registroC460.setNomAdq(listaR04.get(l).getCliente());
                                    }
                                    
                                    spedFiscal.getBlocoC().getRegistroC001().getRegistroC400List().get(indice400).getRegistroC405List().get(j).getRegistroC460List().add(registroC460);
                                    
                                    if (registroC460.getCodSit().equals("00"))
                                    {
                                        // Registro C470
                                        List<R05VO> listaR05 = new RegistroRController().tabelaR05(listaR04.get(l).getId());
                                        if (listaR05 != null) {
                                            for (int m = 0; m < listaR05.size() - 1; m++) {
                                                RegistroC470 registroC470 = new RegistroC470();
                                                registroC470.setCodItem(listaR05.get(m).getIdProduto().toString());
                                                registroC470.setQtd(listaR05.get(m).getQuantidade());
                                                registroC470.setQtdCanc(listaR05.get(m).getQuantidadeCancelada());
                                                registroC470.setUnid(listaR05.get(m).getIdUnidade().toString());
                                                registroC470.setVlItem(listaR05.get(m).getTotalItem());
                                                registroC470.setCstIcms(listaR05.get(m).getCst());
                                                registroC470.setCfop(listaR05.get(m).getCfop().toString());
                                                registroC470.setAliqIcms(listaR05.get(m).getAliquotaICMS());
                                                registroC470.setVlPis(listaR05.get(m).getPis());
                                                registroC470.setVlCofins(listaR05.get(m).getCofins());
                                                
                                                spedFiscal.getBlocoC().getRegistroC001().getRegistroC400List().get(indice400).getRegistroC405List().get(j).getRegistroC460List().get(l).getRegistroC470List().add(registroC470);
                                            }
                                        }
                                    }
                                    
                                }                                
                            }
                        }
                        
                        // Registro C490
                        List<SpedFiscalC490VO> listaC490 = tabelaC490(strDataIni, strDataFim);
                        for (int g = 0; g < listaC490.size() - 1; g++) {
                            RegistroC490 registroC490 = new RegistroC490();
                            registroC490.setCstIcms(listaC490.get(g).getCst());
                            registroC490.setCfop(listaC490.get(g).getCfop().toString());
                            registroC490.setAliqIcms(listaC490.get(g).getTaxaIcms());
                            registroC490.setVlOpr(listaC490.get(g).getSomaValor());
                            registroC490.setVlBcIcms(listaC490.get(g).getSomaBaseIcms());
                            registroC490.setVlIcms(listaC490.get(g).getSomaIcms());
                            
                            spedFiscal.getBlocoC().getRegistroC001().getRegistroC400List().get(indice400).getRegistroC405List().get(j).getRegistroC490List().add(registroC490);
                        }
                        
                    }
                }

            }
        }

    }

    // TODO: -Ajuste a geração do Bloco E
    public void gerarBlocoE() {
        // Registro E001
        spedFiscal.getBlocoE().getRegistroE001().setIndMov(0);

        // Registro E100
        RegistroE100 registroE100 = new RegistroE100();
        registroE100.setDtIni(this.dataInicio);
        registroE100.setDtIni(this.dataFim);
        spedFiscal.getBlocoE().getRegistroE001().getRegistroE100List().add(registroE100);

        List<MeiosPagamentoVO> listaE110 = tabelaE110(strDataIni, strDataFim);
        if (listaE110 != null) {
            for (int i = 0; i < listaE110.size(); i++) {
                // Registro E110
                RegistroE110 registroE110 = new RegistroE110();
                registroE110.setVlTotDebitos(listaE110.get(i).getTotal());
                registroE110.setVlAjDebitos(0D);
                registroE110.setVlTotAjDebitos(0D);
                registroE110.setVlEstornosCred(0D);
                registroE110.setVlTotCreditos(0D);
                registroE110.setVlAjCreditos(0D);
                registroE110.setVlTotAjCreditos(0D);
                registroE110.setVlEstornosDeb(0D);
                registroE110.setVlSldCredorAnt(0D);
                registroE110.setVlSldApurado(listaE110.get(i).getTotal());
                registroE110.setVlTotDed(0D);
                registroE110.setVlIcmsRecolher(listaE110.get(i).getTotal());
                registroE110.setVlSldCredorTransportar(0D);
                registroE110.setDebEsp(0D);
                
                spedFiscal.getBlocoE().getRegistroE001().getRegistroE100List().get(0).setRegistroE110(registroE110);
            }
        }
        
    }

    // TODO: -Ajuste a geração do Bloco H
    public void gerarBlocoH() {
        // Registro H001
        spedFiscal.getBlocoH().getRegistroH001().setIndMov(1);
        
    }
}
