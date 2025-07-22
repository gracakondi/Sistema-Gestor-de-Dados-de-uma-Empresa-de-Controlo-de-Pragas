/*------------------------------------*
Tema: Gestão de uma Empresa Controlo de Pragas
Nome: Gildo Kondi
ID: 33049
Ficheiro: Analise.java
Data: 07.06.2025
--------------------------------------*

1. Objectivo
Este projeto tem como objetivo desenvolver um sistema de gestão para 
uma empresa de controlo de pragas, permitindo o cadastro, edição, listagem e 
pesquisa de clientes, serviços, agendamentos e profissionais.

2. Visao [Interfaces Graficas]
- ApresentacaoVisao
- LoginVisao
- MenuPrincipalVisao
- ClienteVisao
- ServicosVisao
- AgendamentoVisao
- ProfissionalVisao


3. Entidades Fortes e Seus Atributos (Modelo)
- ClienteModelo
	int codCliente
	String nome
	String genero
	int telefone
	String email
	String dataNascimento
	boolean status
	
- ServicosModelo
	int codServico 
	String nomeServico
	int preco
	String produtoUsado
	int duracao
	boolean status
	
- AgendamentoModelo
	int codAg
	string cliente
	ServicosModelo servicos
	string profissional
	DataModelo dataAgenda
	String horaAgenda
	string tipoimovel
	String endereco
	string provincia
	string municipio
	String comuna

	
- ProfisionalModelo
	int codProfissional
	String nome
	String genero
	String cargo
	String especialidade
	int telefone
	boolean status 
	
4. Ficheiro
- ClienteFile.dat
- ServicosFile.dat
- AgendamentoFile.dat
- ProfissionalFile.dat

5. Tabelas de Apoio (Auxiliares) = Entidades Fracas
- Especialidade.tab
= TipoImovel.tab
- Provincias.tab
- Municipio.tab
- Comuna.tab

6. Listagens e Pesquisas

Listagem geral de Clientes
Pesquisar cliente por Nome
Pesquisar cliente por ID
Pesquisar Servico por Nome
Pesquisar Agendamento por Data
Pesquisar Agendamento por ID
Pesquisar Profissional por Nome
Pesquisar Profissional por ID

7. Detalhes Técnicos
7.1 - Linguagem: Java
7.2 - Interface: Swing (JFrame, JMenu, JTextField, etc.)
7.3 - IDE: Notepad++
*/