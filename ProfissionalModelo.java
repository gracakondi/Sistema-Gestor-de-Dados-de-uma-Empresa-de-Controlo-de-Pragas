/*------------------------------------
Tema: Gestão de uma Empresa Controlo de Pragas
Nome: Gildo Kondi
ID: 33049
Ficheiro: ProfissionalModelo.java
Data: 27.05.2025
--------------------------------------*/

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import SwingComponents.*;
import Calendario.*;
import java.io.*;

public class ProfissionalModelo implements RegistGeneric 
{
	private int codProfissional, telefone;
	private StringBufferModelo nome, genero, cargo, especialidade;
	private boolean status;
	
	public ProfissionalModelo()
	{
		codProfissional = 0;
		telefone = 0;
		nome = new StringBufferModelo ("", 50);
		genero = new StringBufferModelo("", 20);
		cargo = new StringBufferModelo("", 50);
		especialidade = new StringBufferModelo("", 50);
	}
	
	//CONSTRUTOR
	public ProfissionalModelo(int codProfissional, int telefone, String nome, 
	String cargo, String genero, String especialidade, boolean estado)
	{
		this.codProfissional = codProfissional;
		this.telefone = telefone;
		this.nome = new StringBufferModelo(nome, 50); 
		this.genero = new StringBufferModelo(cargo, 20);
		this.cargo = new StringBufferModelo(cargo, 50);
		this.especialidade = new StringBufferModelo(especialidade, 50);
		this.status = estado;
	}

	// Métodos GET
	public int getCodProfissional() {
    	return codProfissional;
	}

	public int getTelefone() {
    	return telefone;
	}

	public String getNome() {
    	return nome.toStringEliminatingSpaces();
	}

	public String getGenero() {
    	return genero.toStringEliminatingSpaces();
	}

	public String getCargo() {
    	return cargo.toStringEliminatingSpaces();
	}

	public String getEspecialidade()
	{
		return especialidade.toStringEliminatingSpaces();
	}

	public boolean getStatus() {
    	return status;
	}

	//Metodo SET
	public void setCodProfissional(int novoCodProfissional) {
    	codProfissional = novoCodProfissional;
	}

	public void setTelefone(int novoTelefone) {
    	telefone = novoTelefone;
	}

	public void setNome(String novoNome) {
    	nome = new StringBufferModelo(novoNome, 50);
	}

	public void setGenero(String novoGenero) {
    	genero = new StringBufferModelo(novoGenero, 10);
	}

	public void setCargo(String novoCargo) {
    	cargo = new StringBufferModelo(novoCargo, 50);
	}

	public void setEspecialidade (String novaEspecialidade)
	{
		especialidade = new StringBufferModelo(novaEspecialidade, 50);
	}

	public void setStatus(boolean novoStatus) {
    	status = novoStatus;
	}

	//TOSTRING
	public String toString() {
		String str = "Dados do Profissional\n\n";

		str += "Codigo do Profissional: " + getCodProfissional() + "\n";
		str += "Nome: " + getNome() + "\n";
		str += "Cargo: " + getCargo() + "\n";
		str += "Especialidade" + getEspecialidade() + "\n";
		str += "Genero: " + getGenero() + "\n";
		str += "Telefone: " + getTelefone() + "\n";
		str += "Status: " + getStatus() + "\n";

    	return str;
	}

	// calcula o tamanho geral de cada registo/modelo
	public long sizeof()
	{		
		try
		{
			return 170 * 2 + 4 + 4 + 1; // 349
		}
		catch(Exception ex)
		{
			return 0;
		}				
	}

	public void write(RandomAccessFile stream)
	{
		try
		{
			stream.writeInt(codProfissional);
			nome.write(stream); 
			stream.writeInt(telefone);
			genero.write(stream);
			cargo.write(stream);
			especialidade.write(stream);
			stream.writeBoolean(status);
		}
		catch (IOException ex)
		{
			ex.printStackTrace();
			JOptionPane.showMessageDialog(null, "Falha ao tentar Escrever no Ficheiro");
		}
	}

		public void read(RandomAccessFile stream)
	{
		try
		{
			codProfissional = stream.readInt();
			nome.read(stream); 
			telefone = stream.readInt();
			genero.read(stream);
			cargo.read(stream);
			especialidade.read(stream);
			status = stream.readBoolean();
		}
		catch (IOException ex)
		{
			ex.printStackTrace();
			JOptionPane.showMessageDialog(null, "Falha ao tentar Ler no Ficheiro");
		}
	}
	
	public void salvar()
	{
		ProfissionalFile file = new ProfissionalFile();
		file.salvarDados(this);
	}
	
	public void salvarDados()
	{
		ProfissionalFile file = new ProfissionalFile();
		file.alterarDados(this);
	}
}