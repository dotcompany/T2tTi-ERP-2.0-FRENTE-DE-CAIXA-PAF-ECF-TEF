/**
* ACBrFramework DefExporter
* Este arquivo foi gerado automaticamente
**/



#ifndef ACBr
#define ACBr

typedef void* INTPTR;
typedef char* PCHAR;
typedef char BOOL;

#endif


#ifndef _ACBrSintegra_H_
#define _ACBrSintegra_H_

#ifdef __cplusplus
#define DllImport extern "C"
#else
#define DllImport extern
#endif


// Tipos de dados

typedef struct
{
	char CNPJ[15];
	char Inscricao[15];
	char RazaoSocial[36];
	char Cidade[31];
	char Estado[3];
	char Telefone[11];
	double DataInicial;
	double DataFinal;
	int CodigoConvenio;
	int NaturezaInformacoes;
	int FinalidadeArquivo;
} Registro10Rec;

typedef struct
{
	char Responsavel[29];
	char Bairro[14];
	char Cep[9];
	char Numero[6];
	char Complemento[23];
	char Endereco[35];
	char Telefone[13];
} Registro11Rec;

typedef struct
{
	char CPFCNPJ[15];
	char Inscricao[15];
	char UF[3];
	char Situacao[2];
	double Aliquota;
	double Isentas;
	double Icms;
	double ValorContabil;
	double BasedeCalculo;
	double Outras;
	char EmissorDocumento[2];
	char Cfop[5];
	char Serie[4];
	char Modelo[3];
	char Numero[7];
	double DataDocumento;
} Registro50Rec;

typedef struct
{
	char CPFCNPJ[15];
	char Inscricao[15];
	char Estado[3];
	double ValorIpi;
	double ValorContabil;
	char Serie[4];
	double DataDocumento;
	char Cfop[5];
	char Numero[7];
	char Situacao[2];
	double ValorIsentas;
	double ValorOutras;
} Registro51Rec;

typedef struct
{
	char CPFCNPJ[15];
	char Inscricao[15];
	char Estado[3];
	char Serie[4];
	double DataDocumento;
	char Cfop[5];
	char Numero[7];
	char Situacao[4];
	char CodigoAntecipacao[2];
	double BaseST;
	char Modelo[3];
	char Emitente[2];
	double Despesas;
	double IcmsRetido;
} Registro53Rec;

typedef struct
{
	double Aliquota;
	double BaseST;
	double BasedeCalculo;
	double Quantidade;
	double ValorDescontoDespesa;
	double ValorIpi;
	double Valor;
	int NumeroItem;
	char CST[4];
	char Codigo[15];
	char CFOP[5];
	char Descricao[54];
	char CPFCNPJ[15];
	char Numero[7];
	char Modelo[3];
	char Serie[4];
} Registro54Rec;

typedef struct
{
	double Valor;
	int Agencia;
	int Banco;
	char NumeroConvenio[31];
	char Inscricao[15];
	char MesAno[7];
	char CNPJ[15];
	char UF[3];
	char Numero[15];
	double DataPagamento;
	double Vencimento;
} Registro55Rec;

typedef struct
{
	char Cnpj[15];
	char Modelo[3];
	char Serie[4];
	char Numero[7];
	char Cfop[5];
	char Cst[4];
	int NumeroItem;
	char Codigo[15];
	char TipoOperacao[2];
	char CnpjConcessionaria[15];
	double Ipi;
	char Chassi[18];
} Registro56Rec;

typedef struct
{
	char NumSerie[21];
	char Aliquota[5];
	double Emissao;
	double Valor;
} Registro60ARec;

typedef struct
{
	char NumSerie[21];
	char StAliquota[5];
	double Emissao;
	double Valor;
	char Codigo[15];
	double ValorIcms;
	double Quantidade;
	double BaseDeCalculo;
} Registro60DRec;

typedef struct
{
	char NumSerie[21];
	char StAliquota[5];
	double Emissao;
	double Valor;
	char Codigo[15];
	double ValorIcms;
	double Quantidade;
	double BaseDeCalculo;
	int Item;
	char Cupom[7];
	char ModeloDoc[3];
} Registro60IRec;

typedef struct
{
	int CRO;
	int NumOrdem;
	double VendaBruta;
	char ModeloDoc[3];
	double ValorGT;
	int CRZ;
	int CooFinal;
	int CooInicial;
	char NumSerie[21];
	double Emissao;
} Registro60MRec;

typedef struct
{
	double BaseDeCalculo;
	double Valor;
	double Qtd;
	char MesAno[7];
	char Codigo[15];
	char Aliquota[5];
} Registro60RRec;

typedef struct
{
	double Emissao;
	double Valor;
	double ValorIcms;
	double Outras;
	double BaseDeCalculo;
	double Isentas;
	int NumOrdemInicial;
	int NumOrdemFinal;
	char Modelo[3];
	char SubSerie[3];
	char Serie[4];
	double Aliquota;
} Registro61Rec;

typedef struct
{
	double Aliquota;
	double Valor;
	double Qtd;
	char MesAno[7];
	char Codigo[15];
	double BaseDeCalculo;
} Registro61RRec;

typedef struct
{
	double Icms;
	double ValorContabil;
	char UF[3];
	double Isentas;
	char SubSerie[3];
	char Serie[2];
	double DataDocumento;
	char Modelo[3];
	char CPFCNPJ[15];
	char Cfop[5];
	char Numero[7];
	char Inscricao[15];
	char Situacao[15];
	double Outras;
	double BasedeCalculo;
	char CifFobOutros[2];
} Registro70Rec;

typedef struct
{
	char CPFCNPJ[15];
	char Inscricao[15];
	double DataDocumento;
	char Modelo[3];
	char Serie[2];
	char SubSerie[3];
	char Numero[7];
	char UF[3];
	char UFNF[3];
	char CPFCNPJNF[15];
	char InscricaoNF[15];
	double DataNF;
	char ModeloNF[3];
	char SerieNF[4];
	char NumeroNF[7];
	double ValorNF;
} Registro71Rec;

typedef struct
{
	double ValorProduto;
	char CodigoPosse[2];
	char InscricaoPossuidor[15];
	char Codigo[15];
	char CNPJPossuidor[15];
	char UFPossuidor[3];
	double Data;
	double Quantidade;
} Registro74Rec;

typedef struct
{
	char Codigo[15];
	char Descricao[54];
	double Reducao;
	double BaseST;
	double AliquotaIpi;
	char NCM[9];
	char Unidade[7];
	double AliquotaICMS;
	double DataFinal;
	double DataInicial;
} Registro75Rec;

typedef struct
{
	double Isentas;
	double ValorTotal;
	double Icms;
	double BasedeCalculo;
	double Outras;
	int Modelo;
	int Numero;
	char Situacao[2];
	char Inscricao[15];
	char SubSerie[3];
	char Uf[3];
	char Serie[3];
	char Cfop[5];
	char CPFCNPJ[15];
	double DataDocumento;
	int TipoReceita;
	int Aliquota;
} Registro76Rec;

typedef struct
{
	double Quantidade;
	double ValorServico;
	double ValorDesconto;
	double BaseDeCalculo;
	int Modelo;
	int Numero;
	int NumeroTerminal;
	int NumeroItem;
	int Aliquota;
	char CNPJMF[15];
	char Cfop[5];
	char Codigo[12];
	char SubSerie[3];
	char CPFCNPJ[15];
	char Serie[3];
	int TipoReceita;
} Registro77Rec;

typedef struct
{
	char Declaracao[12];
	double DataDeclaracao;
	char NaturezaExportacao[2];
	char RegistroExportacao[13];
	double DataRegistro;
	char Conhecimento[17];
	double DataConhecimento;
	char TipoConhecimento[3];
	char Pais[5];
	double DataAverbacao;
	char NumeroNotaFiscal[7];
	double DataNotaFiscal;
	char Modelo[3];
	char Serie[4];
} Registro85Rec;

typedef struct
{
	char RegistroExportacao[13];
	double DataRegistro;
	char CPFCNPJ[15];
	char Inscricao[3];
	char UF[3];
	char NumeroNotaFiscal[7];
	double DataDocumento;
	char Modelo[3];
	char Serie[4];
	char Codigo[15];
	double Quantidade;
	double ValorUnitario;
	double ValorTotalProduto;
	char Relacionamento[2];
} Registro86Rec;


// Funções

DllImport int SIN_Create(INTPTR* sinHandle);
DllImport int SIN_Destroy(INTPTR* sinHandle);
DllImport int SIN_GeraArquivo(const INTPTR sinHandle);
DllImport int SIN_GetAtivo(const INTPTR sinHandle);
DllImport int SIN_GetFileName(const INTPTR sinHandle, PCHAR buffer, const int bufferLen);
DllImport int SIN_GetUltimoErro(const INTPTR sinHandle, PCHAR buffer, const int bufferLen);
DllImport int SIN_GetVersaoValidador(const INTPTR sinHandle);
DllImport int SIN_LimparRegistros(const INTPTR sinHandle);
DllImport int SIN_Registro10(const INTPTR sinHandle, const Registro10Rec registro10);
DllImport int SIN_Registro11(const INTPTR sinHandle, const Registro11Rec registro11);
DllImport int SIN_Registro50(const INTPTR sinHandle, const Registro50Rec registro50[], const int count);
DllImport int SIN_Registro51(const INTPTR sinHandle, const Registro51Rec registro51[], const int count);
DllImport int SIN_Registro53(const INTPTR sinHandle, const Registro53Rec registro53[], const int count);
DllImport int SIN_Registro54(const INTPTR sinHandle, const Registro54Rec registro54[], const int count);
DllImport int SIN_Registro55(const INTPTR sinHandle, const Registro55Rec registro55[], const int count);
DllImport int SIN_Registro56(const INTPTR sinHandle, const Registro56Rec registro56[], const int count);
DllImport int SIN_Registro60A(const INTPTR sinHandle, const Registro60ARec registro60A[], const int count);
DllImport int SIN_Registro60D(const INTPTR sinHandle, const Registro60DRec registro60D[], const int count);
DllImport int SIN_Registro60I(const INTPTR sinHandle, const Registro60IRec registro60I[], const int count);
DllImport int SIN_Registro60M(const INTPTR sinHandle, const Registro60MRec registro60M[], const int count);
DllImport int SIN_Registro60R(const INTPTR sinHandle, const Registro60RRec registro60R[], const int count);
DllImport int SIN_Registro61(const INTPTR sinHandle, const Registro61Rec registro61[], const int count);
DllImport int SIN_Registro61R(const INTPTR sinHandle, const Registro61RRec registro61R[], const int count);
DllImport int SIN_Registro70(const INTPTR sinHandle, const Registro70Rec registro70[], const int count);
DllImport int SIN_Registro71(const INTPTR sinHandle, const Registro71Rec registro71[], const int count);
DllImport int SIN_Registro74(const INTPTR sinHandle, const Registro74Rec registro74[], const int count);
DllImport int SIN_Registro75(const INTPTR sinHandle, const Registro75Rec registro75[], const int count);
DllImport int SIN_Registro76(const INTPTR sinHandle, const Registro76Rec registro76[], const int count);
DllImport int SIN_Registro77(const INTPTR sinHandle, const Registro77Rec registro77[], const int count);
DllImport int SIN_Registro85(const INTPTR sinHandle, const Registro85Rec registro85[], const int count);
DllImport int SIN_Registro86(const INTPTR sinHandle, const Registro86Rec registr86[], const int count);
DllImport int SIN_SetFileName(const INTPTR sinHandle, const PCHAR filename);
DllImport int SIN_SetVersaoValidador(const INTPTR sinHandle, const int Versao);


#endif
