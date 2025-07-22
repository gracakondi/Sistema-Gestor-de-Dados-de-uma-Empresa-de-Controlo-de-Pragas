/*------------------------------------
Tema: Gestão de uma Empresa Controlo de Pragas
nomeServico: Gildo Kondi
Numero: 33049
Ficheiro: ServicoVisao.java
Data: 24.06.2024
--------------------------------------*/

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import SwingComponents.*;
import Calendario.*;
import java.io.*;

public class ServicoModelo implements RegistGeneric
{
	
	private int codServico, preco, duracao;
	private StringBufferModelo nomeServico, produtoUsado;
	private boolean status;
	public ServicoModelo()
	{
		codServico = 0;
		preco = 0;
		duracao = 0;

		nomeServico = new StringBufferModelo("", 50); 
		produtoUsado = new StringBufferModelo("", 50);
	}

	public ServicoModelo(int codServico, int preco, int duracao,
	String  nomeServico, String produtoUsado, boolean status)
	{
		this.codServico = codServico;
		this.preco = preco;
		this.duracao = duracao;
		this.nomeServico = new StringBufferModelo(nomeServico, 50); 
		this.produtoUsado = new StringBufferModelo(produtoUsado, 50);
		this.status = status;
	}
	
	// Métodos GET
	public int getcodServico()
	{
		return codServico;
	}
	public int getPreco()
	{
		return preco;
	}
	public int getDuracao()
	{
		return duracao;
	}
	public String getNomeServico()
	{
		return nomeServico.toStringEliminatingSpaces();
	}
	public String getProdutoUsado()
	{
		return produtoUsado.toStringEliminatingSpaces();
	}
	public boolean getStatus()
	{
		return status;
	}

	
	//--- metodos set
	public void setcodServico(int novocodServico)
	{
		codServico = novocodServico;
	}
	public void setPreco(int novoPreco)
	{
		preco = novoPreco;
	}
	public void setDuracao(int novaDuracao)
	{
		duracao = novaDuracao;
	}
	public void setnomeServico(String novonomeServico)
	{
		nomeServico = new StringBufferModelo(novonomeServico, 50);
	}
	public void setprodutoUsado(String novoprodutoUsado)
	{
		produtoUsado = new StringBufferModelo(novoprodutoUsado, 50);
	}
		public void setStatus(boolean novoStatus) 
	{
    	status = novoStatus;
	}

	//--- toString
	public String toString ()
	{
		String str = "Dados do Servico\n\n";

		str += "Codigo do Servico: " + getcodServico() + "\n";
		str += "Nome do Servico: " + getNomeServico() + "\n";
		str += "Preco: " + getPreco()+",00 Kz\n";
		str += "Duracao: " + getDuracao()+"horas \n";
		str += "Produto Usado: " + getProdutoUsado() + "\n";
		str += "Status: " + getStatus() + "\n";
		
		return str;
	}
	
	// calcula o tamanho geral de cada registo/modelo
	public long sizeof()
	{		
		try
		{
			return 100 * 2 + 4 + 4 + 4 + 1; // 213
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
			stream.writeInt(codServico);
			stream.writeInt(preco);
			stream.writeInt(duracao);
			nomeServico.write(stream); 
			produtoUsado.write(stream);
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
			codServico = stream.readInt();
			preco = stream.readInt();
			duracao = stream.readInt();
			nomeServico.read(stream); 
			produtoUsado.read(stream);
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
		ServicoFile file = new ServicoFile();
		file.salvarDados(this);
	}
	
	public void salvarDados()
	{
		ServicoFile file = new ServicoFile();
		file.alterarDados(this);
	}
	
}
