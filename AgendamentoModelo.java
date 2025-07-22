/*------------------------------------
Tema: Gestão de uma Empresa Controlo de Pragas
Nome: Gildo Kondi
ID: 33049
Ficheiro: AgendamentoModelo.java
Data: 27.05.2024
--------------------------------------*/

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import SwingComponents.*;
import Calendario.*;
import java.io.*;

public class AgendamentoModelo implements RegistGeneric
{
	
	private int codAg, horaAgenda;
	private StringBufferModelo endereco, tipoimovel, 
	provincia, municipio, comuna, servico, profissional, cliente;
	private DataModelo dataAgenda;
	private boolean estado;

	public AgendamentoModelo()
	{
		codAg = 0;
		horaAgenda = 0;
		endereco = new StringBufferModelo("", 20); 
		tipoimovel = new StringBufferModelo("", 50);
		provincia = new StringBufferModelo("", 20);
		municipio = new StringBufferModelo("", 20);
		comuna = new StringBufferModelo("", 20);
        dataAgenda = new DataModelo();
		servico = new StringBufferModelo("", 50);
		profissional = new StringBufferModelo("", 50);
		cliente = new StringBufferModelo("", 50);
		estado = false;
	}

	//CONSTRUTOR
	public AgendamentoModelo(int codAg, int horaAgenda,
	String  endereco, String tipoimovel, String dataAgenda, 
	String provincia, String municipio, String comuna, 
	ServicoModelo servico, String profissional, String cliente,boolean estado)
	{
		this.codAg = codAg;
		this.horaAgenda = horaAgenda;
		this.endereco = new StringBufferModelo(endereco, 20); 
		this.tipoimovel = new StringBufferModelo(tipoimovel, 50);
		this.provincia = new StringBufferModelo(provincia, 20);
		this.municipio = new StringBufferModelo(municipio, 20);
		this.comuna = new StringBufferModelo(comuna, 20);
        this.dataAgenda = new DataModelo(dataAgenda);
		this.servico = new StringBufferModelo(servico.getNomeServico(), 50);
		this.profissional = new StringBufferModelo(profissional, 50);
		this.cliente = new StringBufferModelo(cliente, 50);
		this.estado = estado;
	}
	
	//--- metodos get
	public int getCodAg()
	{
		return codAg;
	}
	public int getHoraAgenda()
	{
		return horaAgenda;
	}
	public String getEndereco()
	{
		return endereco.toStringEliminatingSpaces();
	}
	public String getTipoimovel()
	{
		return tipoimovel.toStringEliminatingSpaces();
	}
    public String getDataAgenda()
    {
        return dataAgenda.toString();
    }
		public String getProvincia()
	{
		return provincia.toStringEliminatingSpaces();
	}
	public String getMunicipio()
	{
		return municipio.toStringEliminatingSpaces();
	}
	public String getComuna()
	{
		return comuna.toStringEliminatingSpaces();
	}
	public String getServico()
	{
		return servico.toStringEliminatingSpaces();
	}
	public String getProfissional()
	{
		return profissional.toStringEliminatingSpaces();
	}
	public String getCliente()
	{
		return cliente.toStringEliminatingSpaces();
	}
	public boolean getStatus()
	{
		return estado;
	}
	
	//--- metodos set
	public void setCodAg(int novocodAg)
	{
		codAg = novocodAg;
	}
	public void setHoraAgenda(int novoHoraAgenda)
	{
		horaAgenda = novoHoraAgenda;
	}
	public void setEndereco(String novoEndereco)
	{
		endereco = new StringBufferModelo(novoEndereco, 20);
	}
	public void setTipoimovel(String novoTipoimovel)
	{
		tipoimovel = new StringBufferModelo(novoTipoimovel, 50);
	}
	public void setDataAgenda(String novaData)
	{
		dataAgenda = new DataModelo (novaData);
	}
		public void setProvincia(String novaProvincia)
	{
		provincia = new StringBufferModelo(novaProvincia, 20);
	}
	public void setMunicipio(String novoMunicipio)
	{
		municipio = new StringBufferModelo(novoMunicipio, 20);
	}
	public void setComuna(String novaComuna)
	{
		comuna = new StringBufferModelo(novaComuna, 20);
	}
	public void setServico(String novoServico)
	{
		servico = new StringBufferModelo(novoServico, 50);
	}
	public void setProfissional(String novoProfissional)
	{
		profissional = new StringBufferModelo(novoProfissional, 50);
	}
	public void setCliente(String novoCliente)
	{
		cliente = new StringBufferModelo(novoCliente, 50);
	}
	public void setStatus(boolean new_status)
	{
		this.estado = new_status;
	}


	//--- toString
	public String toString ()
	{
		String str = "Dados do Agendamento\n\n";

		str += "Codigo do Agendamento: " + getCodAg() + "\n";
		str += "Cliente: " + getCliente() + "\n";
		str += "Servico Escolhido:" + getServico() + "\n";
        str += "Data Agendando: " + getDataAgenda() + "\n";
		str += "Hora do Servico: " + getHoraAgenda()+ "horas\n";
		str += "Profissional Responsavel: " + getProfissional() + "\n";
		str += "Tipo do Imovel: " + getTipoimovel() + "\n";
		str += "Dados Endereco Completo:" + getProvincia()+", "+ 
		getMunicipio()+", "+ getComuna()+", "+ getEndereco() + "\n";
		str += "Estado: " + getStatus() + "\n";
		return str;
	}
	
	// calcula o tamanho geral de cada registo/modelo
	public long sizeof()
	{		
		try
		{
			return 280 * 2 + 4 + 4 + 12 + 1; // 581
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
			stream.writeInt(codAg);
			stream.writeInt(horaAgenda);
			endereco.write(stream); 
            dataAgenda.write(stream);
			tipoimovel.write(stream);
			provincia.write(stream);
			municipio.write(stream);
			comuna.write(stream);
			servico.write(stream);
			profissional.write(stream);
			cliente.write(stream);
			stream.writeBoolean(estado);
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
			codAg = stream.readInt();
			horaAgenda = stream.readInt();
			endereco.read(stream);
			dataAgenda.read(stream);
			tipoimovel.read(stream); 
			provincia.read(stream);
			municipio.read(stream);
			comuna.read(stream);
			servico.read(stream); 
			profissional.read(stream); 
			cliente.read(stream); 
			estado = stream.readBoolean();
		}
		catch (IOException ex)
		{
			ex.printStackTrace();
			JOptionPane.showMessageDialog(null, "Falha ao tentar Ler no Ficheiro");
		}
	}
	
	public void salvar()
	{
		AgendamentoFile file = new AgendamentoFile();
		file.salvarDados(this);
	}
	
	public void salvarDados()
	{
		AgendamentoFile file = new AgendamentoFile();
		file.alterarDados(this);
	}
	
}