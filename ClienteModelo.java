/*------------------------------------
Tema: Gestão de uma Empresa Controlo de Pragas
Nome: Gildo Kondi
ID: 33049
Ficheiro: ClienteModelo.java
Data: 27.05.2025
--------------------------------------*/

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import SwingComponents.*;
import Calendario.*;
import java.io.*;

public class ClienteModelo implements RegistGeneric 
{
	//Declaracao dos atributos
	private int codCliente, telefone;
	private StringBufferModelo nome, genero, email;
	private DataModelo dataNascimento;
	private boolean status;
	
	public ClienteModelo()
	{
		codCliente = 0;
		telefone = 0;
		nome = new StringBufferModelo ("", 50);
		genero = new StringBufferModelo ("", 20);
		email = new StringBufferModelo ("", 30);
		dataNascimento = new DataModelo();
		status = false;
	}
	
	//CONSTRUTOR
	public ClienteModelo(int codCliente, int telefone, String nome,
	String genero, String email, String dataNascimento,  boolean estado)
	{
		this.codCliente = codCliente;
		this.telefone = telefone;
		this.nome = new StringBufferModelo(nome, 50);
		this.genero = new StringBufferModelo(genero, 20);
		this.email = new StringBufferModelo(email, 30);
		this.dataNascimento = new DataModelo(dataNascimento);
		this.status = estado;
	}

	// Métodos GET // Já está certo
	public int getCodCliente() 
	{
    	return codCliente;
	}
	public int getTelefone() 
	{
    	return telefone;
	}
	public String getNome()
	{
    	return nome.toStringEliminatingSpaces();
	}
	public String getGenero() 
	{
    	return genero.toStringEliminatingSpaces();
	}
	public String getEmail() 
	{
    	return email.toStringEliminatingSpaces();
	}
	public String getDataNascimento() 
	{
 		return dataNascimento.toString();
	}
	public boolean getStatus() 
	{
    	return status;
	}

	//Metodo SET // Já está certo
	public void setCodCliente(int novoCodCliente) 
	{
    	codCliente = novoCodCliente;
	}
	public void setTelefone(int novoTelefone) 
	{
    	telefone = novoTelefone;
	}
	public void setNome(String novoNome) 
	{
    	nome = new StringBufferModelo(novoNome, 50);
	}
	public void setGenero(String novoGenero) 
	{
    	genero = new StringBufferModelo(novoGenero, 20);
	}
	public void setEmail(String novoEmail) 
	{
    	email = new StringBufferModelo(novoEmail, 30);
	}
	public void setDataNascimento(DataModelo novaDataNascimento) 
	{
    	dataNascimento = novaDataNascimento;
	}
	public void setStatus(boolean novoStatus) 
	{
    	status = novoStatus;
	}

	//TOSTRING // Já está certo
	public String toString() {
		String str = "Dados do Cliente\n\n";

		str += "Codigo do Cliente: " + getCodCliente() + "\n";
		str += "Nome: " + getNome() + "\n";
		str += "Data de Nascimento: " + getDataNascimento() + "\n";
		str += "Genero: " + getGenero() + "\n";
		str += "Telefone: " + getTelefone() + "\n";
		str += "E-mail: " + getEmail() + "\n";
		str += "Status: " + getStatus() + "\n";

    	return str;
	}

	// calcula o tamanho geral de cada registo/modelo
	public long sizeof()
	{		
		try
		{
			return 100 * 2 + 4 + 4 + 12 + 1; // 221
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
			stream.writeInt(codCliente);
			nome.write(stream); 
			stream.writeInt(telefone);
			dataNascimento.write(stream);
			genero.write(stream);
			email.write(stream);
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
			codCliente = stream.readInt();
			nome.read(stream); 
			telefone = stream.readInt();
			dataNascimento.read(stream);
			genero.read(stream);
			email.read(stream);
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
		ClienteFile file = new ClienteFile();
		file.salvarDados(this);
	}
	
	public void salvarDados()
	{
		ClienteFile file = new ClienteFile();
		file.alterarDados(this);
	}

}