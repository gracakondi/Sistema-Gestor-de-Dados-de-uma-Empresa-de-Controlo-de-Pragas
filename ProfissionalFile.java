/*------------------------------------
Tema: Gestão de uma Morgue
Nome: Osvaldo Ramos
Numero: 2817
Ficheiro: ProfissionalFile.java
Data: 31.05.2024
--------------------------------------*/

import javax.swing.*;
import SwingComponents.*;
import Calendario.*;
import java.io.*;

public class ProfissionalFile extends ObjectsFile
{
	
	public ProfissionalFile()
	{
		super("ProfissionalFile.dat", new ProfissionalModelo() );
	}
	 
	public void salvarDados(ProfissionalModelo modelo)
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
			JOptionPane.showMessageDialog(null, "Falha ao Salvar um Novo Cadaver");
		}
	}
	
	public static void listarProfissional()
	{
		ProfissionalFile ficheiro = new ProfissionalFile();
		ProfissionalModelo modelo = new ProfissionalModelo();
		String output = "Listagem de Dados do Ficheiro\n\n";
		
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
		ProfissionalFile ficheiro = new ProfissionalFile();
		ProfissionalModelo modelo = new ProfissionalModelo();
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
	
	public static StringVector getAllEspecialidade()
	{
		ProfissionalFile ficheiro = new ProfissionalFile();
		ProfissionalModelo modelo = new ProfissionalModelo();
		StringVector v = new StringVector();
		
		try
		{
			ficheiro.stream.seek(4);
			
			for (int i = 0; i < ficheiro.getNregistos(); ++i)
			{
				modelo.read( ficheiro.stream );
				
				if (modelo.getStatus() == true)
					v.add( modelo.getEspecialidade() );
			}
			
			v.sort();
			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}	
	
		return v;
	}

	public void alterarDados(ProfissionalModelo modelo_novo)
	{
		ProfissionalModelo modelo_antigo = new ProfissionalModelo();
		
		try
		{
			stream.seek(4);
			
			for(int i = 0; i < getNregistos(); ++i)
			{
				modelo_antigo.read( stream );
				
				if (i == 0 && modelo_antigo.getCodProfissional() == modelo_novo.getCodProfissional())
				{
					stream.seek(4); 
					modelo_novo.write( stream );
					JOptionPane.showMessageDialog(null, "Dados alterados com sucesso!");
					return;
				}	
				else
				{
					if (modelo_antigo.getCodProfissional() + 1 == modelo_novo.getCodProfissional())
					{
						modelo_novo.write( stream);
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
	
	public void eliminarDados(ProfissionalModelo modelo_novo)
	{
		ProfissionalModelo modelo_antigo = new ProfissionalModelo();
		
		try
		{
			stream.seek(4);
			
			for(int i = 0; i < getNregistos(); ++i)
			{
				modelo_antigo.read( stream );
				
				if (i == 0 && modelo_antigo.getCodProfissional() == modelo_novo.getCodProfissional())
				{
					stream.seek(4); 
					modelo_novo.write( stream );
					JOptionPane.showMessageDialog(null, "Dados eliminados com sucesso!");
					return;
				}	
				else
				{
					if (modelo_antigo.getCodProfissional() + 1 == modelo_novo.getCodProfissional())
					{
						modelo_novo.write( stream);
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
	public static ProfissionalModelo getProfPorNome(String nomeProcurado)
	{
		ProfissionalFile ficheiro = new ProfissionalFile();
		ProfissionalModelo modelo = new ProfissionalModelo();
		
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
	public static void pesquisarProfPorNome(String nomeProcurado)
	{
		ProfissionalFile ficheiro = new ProfissionalFile();
		ProfissionalModelo modelo = new ProfissionalModelo();
		
		try
		{
			ficheiro.stream.seek(4);
			
			for (int i = 0; i < ficheiro.getNregistos(); ++i)
			{
				modelo.read( ficheiro.stream );
				
				if (modelo.getNome().equalsIgnoreCase( nomeProcurado ) && modelo.getStatus() == true)
				{
					JOptionPane.showMessageDialog(null, modelo.toString(), 
					"Gestao de Controlo de Pragas", JOptionPane.INFORMATION_MESSAGE);
					break;					
				}
			}					
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}		
	}
	public static void pesquisarProfPorEspecialidade(String espProcurado)
	{
		ProfissionalFile ficheiro = new ProfissionalFile();
		ProfissionalModelo modelo = new ProfissionalModelo();
		
		try
		{
			ficheiro.stream.seek(4);
			
			for (int i = 0; i < ficheiro.getNregistos(); ++i)
			{
				modelo.read( ficheiro.stream );
				
				if (modelo.getEspecialidade().equalsIgnoreCase( espProcurado ) &&
						modelo.getStatus() == true)
				{
					JOptionPane.showMessageDialog(null, modelo.toString(), 
					"Gestao de Controlo de Pragas", JOptionPane.INFORMATION_MESSAGE);
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
