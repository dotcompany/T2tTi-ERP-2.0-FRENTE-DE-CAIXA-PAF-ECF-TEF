/**
 * <p>Title: T2TiPDV</p>
 *
 * <p>Description: Controller do cliente</p>
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
import com.t2ti.pafecf.vo.ClienteVO;
import com.t2ti.pafecf.vo.SituacaoClienteVO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ClienteController {

    String consultaSQL;
    Statement stm;
    PreparedStatement pstm;
    ResultSet rs;
    AcessoBanco bd = new AcessoBanco();

    public ClienteVO consultaCpfCnpj(String pCpfCnpj) {
        ClienteVO cliente = new ClienteVO();
        consultaSQL =
                "select ID, NOME, CPF_CNPJ from CLIENTE where CPF_CNPJ=" + pCpfCnpj;
        try {
            stm = bd.conectar().createStatement();
            rs = stm.executeQuery(consultaSQL);
            rs.beforeFirst();
            if (rs.next()) {
                cliente.setId(rs.getInt("ID"));
                cliente.setNome(rs.getString("NOME"));
                cliente.setCpfOuCnpj(rs.getString("CPF_CNPJ"));
                return cliente;
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

    public List<ClienteVO> clienteFiltro(String filtroNome) {
        String procurePor = "%" + filtroNome + "%";
        consultaSQL =
                "SELECT COUNT(*) as TOTAL "
                + "FROM CLIENTE C JOIN SITUACAO_CLIENTE S ON C.ID_SITUACAO_CLIENTE = S.ID "
                + "WHERE C.NOME LIKE '" + procurePor + "'";

        try {
            stm = bd.conectar().createStatement();
            rs = stm.executeQuery(consultaSQL);
            rs.first();
            Integer totalRegistros = rs.getInt("TOTAL");

            if (totalRegistros > 0) {
                List<ClienteVO> listaCliente = new ArrayList<ClienteVO>();
                consultaSQL =
                        "SELECT C.ID, C.ID_SITUACAO_CLIENTE, C.NOME, S.ID, S.NOME as SITUACAO, C.CPF_CNPJ, "
                        + "C.RG, C.ORGAO_RG, C.INSCRICAO_ESTADUAL, C.INSCRICAO_MUNICIPAL, C.DATA_CADASTRO, C.UF "
                        + "FROM CLIENTE C JOIN SITUACAO_CLIENTE S ON C.ID_SITUACAO_CLIENTE = S.ID "
                        + "WHERE C.NOME LIKE '" + procurePor + "'";

                stm = bd.conectar().createStatement();
                rs = stm.executeQuery(consultaSQL);
                rs.beforeFirst();
                while (rs.next()) {
                    ClienteVO cliente = new ClienteVO();
                    cliente.setSituacaoClienteVO(new SituacaoClienteVO());
                    cliente.setNome(rs.getString("NOME"));
                    cliente.getSituacaoClienteVO().setDescricao(rs.getString("SITUACAO"));
                    cliente.setCpfOuCnpj(rs.getString("CPF_CNPJ"));
                    cliente.setRg(rs.getString("RG"));
                    cliente.setOrgaoRg(rs.getString("ORGAO_RG"));
                    cliente.setInscricaoMunicipal(rs.getString("INSCRICAO_MUNICIPAL"));
                    cliente.setInscricaoEstadual(rs.getString("INSCRICAO_ESTADUAL"));
                    cliente.setDataCadastro(rs.getDate("DATA_CADASTRO"));
                    cliente.setUf(rs.getString("UF"));
                    cliente.setId(rs.getInt("C.ID"));
                    listaCliente.add(cliente);
                }
                return listaCliente;
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

    public List<ClienteVO> consultaClienteSped(String pDataInicio, String pDataFim) {
        // TODO: -Implemente a busca por NF-e na sua retaguarda
        consultaSQL =
                "select count(*) as total from NFE_CABECALHO where DATA_EMISSAO "
                + "between '" + pDataInicio + "' and '" + pDataFim + "'";

        try {
            stm = bd.conectar().createStatement();
            rs = stm.executeQuery(consultaSQL);
            rs.first();
            Integer totalRegistros = rs.getInt("TOTAL");

            if (totalRegistros > 0) {
                List<ClienteVO> listaCliente = new ArrayList<ClienteVO>();
                consultaSQL =
                        " select "
                        + " c.nome, "
                        + " c.rg, "
                        + " c.orgao_rg, "
                        + " c.cpf_cnpj, "
                        + " c.tipo, "
                        + " c.inscricao_estadual, "
                        + " c.inscricao_municipal, "
                        + " c.data_cadastro, "
                        + " p.codigo_ibge_cidade, "
                        + " p.logradouro, "
                        + " p.numero, "
                        + " p.complemento, "
                        + " p.bairro, "
                        + " p.uf "
                        + " from "
                        + " pessoa c, pessoa_endereco p "
                        + " where "
                        + " c.id in (select ID  from NFE_CABECALHO "
                        + "where DATA_EMISSAO between '" + pDataInicio + "' and '" + pDataFim + "')";

                stm = bd.conectar().createStatement();
                rs = stm.executeQuery(consultaSQL);
                rs.beforeFirst();
                while (rs.next()) {
                    ClienteVO cliente = new ClienteVO();
                    cliente.setId(rs.getInt("PID"));
                    cliente.setTipoPessoa(rs.getString("TIPO_PESSOA"));
                    cliente.setNome(rs.getString("NOME"));
                    cliente.setCpfOuCnpj(rs.getString("CPF_CNPJ"));
                    cliente.setRg(rs.getString("RG"));
                    cliente.setOrgaoRg(rs.getString("ORGAO_RG"));
                    cliente.setInscricaoEstadual(rs.getString("INSCRICAO_ESTADUAL"));
                    cliente.setInscricaoMunicipal(rs.getString("INSCRICAO_MUNICIPAL"));
                    cliente.setDataCadastro(rs.getDate("DATA_CADASTRO"));
                    cliente.setLogradouro(rs.getString("LOGRADOURO"));
                    cliente.setNumero(rs.getString("NUMERO"));
                    cliente.setComplemento(rs.getString("COMPLEMENTO"));
                    cliente.setCep(rs.getString("CEP"));
                    cliente.setBairro(rs.getString("BAIRRO"));
                    cliente.setCidade(rs.getString("CIDADE"));
                    cliente.setUf(rs.getString("UF"));
                    cliente.setCodigoIbgeCidade(rs.getInt("CODIGO_IBGE_CIDADE"));
                    listaCliente.add(cliente);
                }
                return listaCliente;
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

    public boolean consultaIdCliente(Integer pId) {
        consultaSQL =
                "select "
                + " ID "
                + "from "
                + " CLIENTE "
                + "where "
                + " ID = "+pId;

        try {
            pstm = bd.conectar().prepareStatement(consultaSQL);
            rs = pstm.executeQuery(consultaSQL);
            rs.beforeFirst();
            if (rs.next()) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            bd.desconectar();
        }
    }

    public Boolean gravaCargaCliente(String pTupla) {
        String[] tupla = pTupla.split("\\|");
        Integer id;
        try {
            id = Integer.valueOf(tupla[1]);   //ID  INTEGER NOT NULL,

            if (!consultaIdCliente(id)) {
                consultaSQL =
                        "insert into"
                        + " CLIENTE "
                        + "(ID, "
                        + "ID_SITUACAO_CLIENTE, "//2
                        + "NOME, "//3
                        + "FANTASIA, "//4
                        + "EMAIL, "//5
                        + "CPF_CNPJ, "//6
                        + "RG, "//7
                        + "ORGAO_RG, "//8
                        + "DATA_EMISSAO_RG, "//9
                        + "SEXO, "//10
                        + "INSCRICAO_ESTADUAL, "//11
                        + "INSCRICAO_MUNICIPAL, "//12
                        + "TIPO_PESSOA, "//13
                        + "DATA_CADASTRO, "//14
                        + "LOGRADOURO, "//15
                        + "NUMERO, "//16
                        + "COMPLEMENTO, "//17
                        + "CEP, "//18
                        + "BAIRRO, "//19
                        + "CIDADE, "//20
                        + "UF, "//21
                        + "FONE1, "//22
                        + "FONE2, "//23
                        + "CELULAR, "//24
                        + "CONTATO, "//25
                        + "CODIGO_IBGE_CIDADE, "//26
                        + "CODIGO_IBGE_UF) "//27
                        + "values ("
                        + id + ", " //    ID      INTEGER NOT NULL,
                        + tupla[2] + ", " //   ID_SITUACAO_CLIENTE  INTEGER NOT NULL,
                        + tupla[3] + ", " //   NOME                 VARCHAR(150),
                        + tupla[4] + ", " //   FANTASIA             VARCHAR(150),
                        + tupla[5] + ", " //   EMAIL                VARCHAR(250),
                        + tupla[6] + ", " //   CPF_CNPJ             VARCHAR(14),
                        + tupla[7] + ", " //   RG                   VARCHAR(20),
                        + tupla[8] + ", " //   ORGAO_RG             VARCHAR(20),
                        + tupla[9] + ", " //   DATA_EMISSAO_RG      DATE,
                        + tupla[10] + ", " //   SEXO                 CHAR(1),
                        + tupla[11] + ", " //   INSCRICAO_ESTADUAL   VARCHAR(30),
                        + tupla[12] + ", " //   INSCRICAO_MUNICIPAL  VARCHAR(30),
                        + tupla[13] + ", " //   TIPO_PESSOA          CHAR(1),
                        + tupla[14] + ", " //   DATA_CADASTRO        DATE,
                        + tupla[15] + ", " //   LOGRADOURO           VARCHAR(250),
                        + tupla[16] + ", " //   NUMERO               VARCHAR(6),
                        + tupla[17] + ", " //   COMPLEMENTO          VARCHAR(50),
                        + tupla[18] + ", " //   CEP                  VARCHAR(8),
                        + tupla[19] + ", " //   BAIRRO               VARCHAR(100),
                        + tupla[20] + ", " //   CIDADE               VARCHAR(100),
                        + tupla[21] + ", " //   UF                   CHAR(2),
                        + tupla[22] + ", " //   FONE1                VARCHAR(10),
                        + tupla[23] + ", " //   FONE2                VARCHAR(10),
                        + tupla[24] + ", " //   CELULAR              VARCHAR(10),
                        + tupla[25] + ", " //   CONTATO              VARCHAR(50),
                        + tupla[26] + ", " //   CODIGO_IBGE_CIDADE   INTEGER,
                        + tupla[27] + ")";     //   CODIGO_IBGE_UF       INTEGER
            } else {
                consultaSQL =
                        "update "
                        + " CLIENTE "
                        + "set "
                        + "ID_SITUACAO_CLIENTE =" + tupla[2] + ", " //   ID_SITUACAO_CLIENTE  INTEGER NOT NULL,
                        + "NOME =" + tupla[3] + ", " //   NOME                 VARCHAR(150),
                        + "FANTASIA =" + tupla[4] + ", " //   FANTASIA             VARCHAR(150),
                        + "EMAIL =" + tupla[5] + ", " //   EMAIL                VARCHAR(250),
                        + "CPF_CNPJ =" + tupla[6] + ", " //   CPF_CNPJ             VARCHAR(14),
                        + "RG =" + tupla[7] + ", " //   RG                   VARCHAR(20),
                        + "ORGAO_RG =" + tupla[8] + ", " //   ORGAO_RG             VARCHAR(20),
                        + "DATA_EMISSAO_RG =" + tupla[9] + ", " //   DATA_EMISSAO_RG      DATE,
                        + "SEXO =" + tupla[10] + ", " //   SEXO                 CHAR(1),
                        + "INSCRICAO_ESTADUAL =" + tupla[11] + ", " //   INSCRICAO_ESTADUAL   VARCHAR(30),
                        + "INSCRICAO_MUNICIPAL =" + tupla[12] + ", " //   INSCRICAO_MUNICIPAL  VARCHAR(30),
                        + "TIPO_PESSOA =" + tupla[13] + ", " //   TIPO_PESSOA          CHAR(1),
                        + "DATA_CADASTRO =" + tupla[14] + ", " //   DATA_CADASTRO        DATE,
                        + "LOGRADOURO =" + tupla[15] + ", " //   LOGRADOURO           VARCHAR(250),
                        + "NUMERO =" + tupla[16] + ", " //   NUMERO               VARCHAR(6),
                        + "COMPLEMENTO =" + tupla[17] + ", " //   COMPLEMENTO          VARCHAR(50),
                        + "CEP =" + tupla[18] + ", " //   CEP                  VARCHAR(8),
                        + "BAIRRO =" + tupla[19] + ", " //   BAIRRO               VARCHAR(100),
                        + "CIDADE =" + tupla[20] + ", " //   CIDADE               VARCHAR(100),
                        + "UF =" + tupla[21] + ", " //   UF                   CHAR(2),
                        + "FONE1 =" + tupla[22] + ", " //   FONE1                VARCHAR(10),
                        + "FONE2 =" + tupla[23] + ", " //   FONE2                VARCHAR(10),
                        + "CELULAR =" + tupla[24] + ", " //   CELULAR              VARCHAR(10),
                        + "CONTATO =" + tupla[25] + ", " //   CONTATO              VARCHAR(50),
                        + "CODIGO_IBGE_CIDADE =" + tupla[26] + ", " //   CODIGO_IBGE_CIDADE   INTEGER,
                        + "CODIGO_IBGE_UF =" + tupla[27] //   CODIGO_IBGE_UF       INTEGER
                        + " where "
                        + " ID =" + id;
            }
            pstm = bd.conectar().prepareStatement(consultaSQL);
            pstm.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            bd.desconectar();
        }
    }
}