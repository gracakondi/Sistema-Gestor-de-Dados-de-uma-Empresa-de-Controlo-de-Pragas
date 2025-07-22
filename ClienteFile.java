/*------------------------------------
Tema: Gestão de uma Empresa Controle de Pragas
Nome: Gildo Kondi
ID: 33049
Ficheiro: ClienteFile.java
Data:21.05.2025
--------------------------------------*/

import javax.swing.*;
import SwingComponents.*;
import Calendario.*;
import java.io.*;

public class ClienteFile extends ObjectsFile
{
	
	public ClienteFile()
	{
		super("ClienteFile.dat", new ClienteModelo() );
	}
	
	public void salvarDados(ClienteModelo modelo)
	{
		try
		{
			//colocar o File Pointer no final do ficheiro
			stream.seek( stream.length() );
			
			//escrever os dados no ficheiro
			modelo.write(stream);
			
			incrementarProximoCodigo();
			
			JOptionPane.showMessageDialog(null, "Dados Salvos com Sucesso!");
		}
		catch (IOException ex)
		{
			ex.printStackTrace();
			JOptionPane.showMessageDialog(null, "Falha ao Salvar um Novo Cliente");
		}
	}
	 
	public static void listarCliente()
	{
		ClienteFile ficheiro = new ClienteFile();
		ClienteModelo modelo = new ClienteModelo();
		String output = "Listagem de Dados do Ficheiro Cliente\n\n";
		
		try
		{
			ficheiro.stream.seek(4);
			
			for (int i = 0; i < ficheiro.getNregistos(); ++i)
			{
				modelo.read( ficheiro.stream );
				
				if (modelo.getStatus() == true)
				{
					output += "---------------------------------\n";
					output += modelo.toString() + "\n";
				}
			}
						
			JTextArea area = new JTextArea(40, 60);
			area.setText( output );
			area.setFocusable(false);
			JOptionPane.showMessageDialog(null, new JScrollPane( area ), 
					"Gestao de Controlo de Pragas", JOptionPane.INFORMATION_MESSAGE);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}		
	}
	
	public static StringVector getAllNames()
	{
		ClienteFile ficheiro = new ClienteFile();
		ClienteModelo modelo = new ClienteModelo();
		StringVector vector = new StringVector();
		
		try
		{
			ficheiro.stream.seek(4);
			
			for (int i = 0; i < ficheiro.getNregistos(); ++i)
			{
				modelo.read( ficheiro.stream );
				
				if (modelo.getStatus() == true)
					vector.add( modelo.getNome() );
			}
						
			vector.sort();
			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}	
	
		return vector;
	}
	
	public void alterarDados(ClienteModelo modelo_novo)
	{
		ClienteModelo modelo_antigo = new ClienteModelo();
		
		try
		{
			stream.seek(4);
			
			for(int i = 0; i < getNregistos(); ++i)
			{
				modelo_antigo.read( stream );
				
				if (i == 0 && modelo_antigo.getCodCliente() == modelo_novo.getCodCliente())
				{
					stream.seek(4); 
					modelo_novo.write( stream );
					JOptionPane.showMessageDialog(null, "Dados alterados com sucesso!");
					return;
				}	
				else
				{
					if (modelo_antigo.getCodCliente() + 1 == modelo_novo.getCodCliente())
					{
						modelo_novo.write(stream);
						return;
					}
							
				}			
			}			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}

	public void eliminarDados(ClienteModelo modelo_novo)
	{
		ClienteModelo modelo_antigo = new ClienteModelo();
		
		try
		{
			stream.seek(4);
			
			for(int i = 0; i < getNregistos(); ++i)
			{
				modelo_antigo.read(stream);
				
				if (i == 0 && modelo_antigo.getCodCliente() == modelo_novo.getCodCliente())
				{
					stream.seek(4); 
					modelo_novo.write( stream );
					JOptionPane.showMessageDialog(null, "Dados eliminados com sucesso!");
					return;
				}	
				else
				{
					if (modelo_antigo.getCodCliente() + 1 == modelo_novo.getCodCliente())
					{
						modelo_novo.write(stream);
						return;
					}
							
				}			
			}			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	public static ClienteModelo getClientePorNome(String nomeProcurado)
	{
		ClienteFile ficheiro = new ClienteFile();
		ClienteModelo modelo = new ClienteModelo();
		
		try
		{
			ficheiro.stream.seek(4);
			
			for (int i = 0; i < ficheiro.getNregistos(); ++i)
			{
				modelo.read( ficheiro.stream );
				
				if (modelo.getNome().equalsIgnoreCase( nomeProcurado ) && modelo.getStatus() == true)
					return modelo;
			}					
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}	
		return modelo;	
	}
		public static ClienteModelo getClientePorID(int idprocurado)
	{
		ClienteFile ficheiro = new ClienteFile();
		ClienteModelo modelo = new ClienteModelo();
		
		try
		{
			ficheiro.stream.seek(4);
			
			for (int i = 0; i < ficheiro.getNregistos(); ++i)
			{
				modelo.read( ficheiro.stream );
				
				if (modelo.getCodCliente() == idprocurado  && modelo.getStatus() == true)
					return modelo;
			}					
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}	
		return modelo;	
	}
	public static void pesquisarClientePorNome(String nomeProcurado)
	{
		ClienteFile ficheiro = new ClienteFile();
		ClienteModelo modelo = new ClienteModelo();
		
		try
		{
			ficheiro.stream.seek(4);
			
			for (int i = 0; i < ficheiro.getNregistos(); ++i)
			{
				modelo.read( ficheiro.stream );
				
				if (modelo.getNome().equalsIgnoreCase( nomeProcurado ) && modelo.getStatus() == true)
				{
					JOptionPane.showMessageDialog(null, modelo.toString(), 
					"Gestao de Contole De Pragas", JOptionPane.INFORMATION_MESSAGE);
					break;					
				}
			}					
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}		
	}
	public static void pesquisarClientePorID(int idProcurado)
	{
		ClienteFile ficheiro = new ClienteFile();
		ClienteModelo modelo = new ClienteModelo();
		
		try
		{
			ficheiro.stream.seek(4);
			
			for (int i = 0; i < ficheiro.getNregistos(); ++i)
			{
				modelo.read( ficheiro.stream );
				
				if (modelo.getCodCliente() == idProcurado && modelo.getStatus() == true)
				{
					JOptionPane.showMessageDialog(null, modelo.toString(), 
					"Gestao de Controle de Pragas", JOptionPane.INFORMATION_MESSAGE);
					break;					
				}
			}					
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}		
	} 
		
}
