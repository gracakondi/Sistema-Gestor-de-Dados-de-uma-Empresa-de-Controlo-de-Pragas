/*------------------------------------
Tema: Gestão de uma Empresa Controlo de Pragas
Nome: Gildo Kondi
ID: 33049
Ficheiro: AgendamentoFile.java
Data: 31.05.2024
--------------------------------------*/

import javax.swing.*;
import SwingComponents.*;
import Calendario.*;
import java.io.*;

public class AgendamentoFile extends ObjectsFile
{
	
	public AgendamentoFile()
	{
		super("AgendamentoFile.dat", new AgendamentoModelo() );
	}
	 
	public void salvarDados(AgendamentoModelo modelo)
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
			JOptionPane.showMessageDialog(null, "Falha ao Salvar Agendamento");
		}
	}
	
	public static void listarAgendamento() //listar agendamento
	{
		AgendamentoFile ficheiro = new AgendamentoFile();
		AgendamentoModelo modelo = new AgendamentoModelo();
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
	
	public static StringVector getAllDates()
	{
		AgendamentoFile ficheiro = new AgendamentoFile();
		AgendamentoModelo modelo = new AgendamentoModelo();
		StringVector vector = new StringVector();
		
		try
		{
			ficheiro.stream.seek(4);
			
			for (int i = 0; i < ficheiro.getNregistos(); ++i)
			{
				modelo.read( ficheiro.stream );
				
				if (modelo.getStatus() == true)
					vector.add( modelo.getDataAgenda() );
			}
						
			vector.sort();
			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}	
	
		return vector;
	}
	
	public void alterarDados(AgendamentoModelo modelo_novo)
	{
		AgendamentoModelo modelo_antigo = new AgendamentoModelo();
		
		try
		{
			stream.seek(4);
			
			for(int i = 0; i < getNregistos(); ++i)
			{
				modelo_antigo.read( stream );
				
				if (i == 0 && modelo_antigo.getCodAg() == modelo_novo.getCodAg())
				{
					stream.seek(4); 
					modelo_novo.write( stream );
					JOptionPane.showMessageDialog(null, "Dados alterados com sucesso!");
					return;
				}	
				else
				{
					if (modelo_antigo.getCodAg() + 1 == modelo_novo.getCodAg())
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
	
	public void eliminarDados(AgendamentoModelo modelo_novo)
	{
		AgendamentoModelo modelo_antigo = new AgendamentoModelo();
		
		try
		{
			stream.seek(4);
			
			for(int i = 0; i < getNregistos(); ++i)
			{
				modelo_antigo.read( stream );
				
				if (i == 0 && modelo_antigo.getCodAg() == modelo_novo.getCodAg())
				{
					stream.seek(4); 
					modelo_novo.write( stream );
					JOptionPane.showMessageDialog(null, "Dados eliminados com sucesso!");
					return;
				}	
				else
				{
					if (modelo_antigo.getCodAg() + 1 == modelo_novo.getCodAg())
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
	public static AgendamentoModelo getAgendamentoPorData(String dataProcurada)
	{
		AgendamentoFile ficheiro = new AgendamentoFile();
		AgendamentoModelo modelo = new AgendamentoModelo();
		
		try
		{
			ficheiro.stream.seek(4);
			
			for (int i = 0; i < ficheiro.getNregistos(); ++i)
			{
				modelo.read( ficheiro.stream );
				
				if (modelo.getDataAgenda().equalsIgnoreCase( dataProcurada ) && modelo.getStatus() == true)
					return modelo;
			}					
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}	
		return modelo;	
	}
		public static AgendamentoModelo getAgendamentoPorID(int idPrc)
	{
		AgendamentoFile ficheiro = new AgendamentoFile();
		AgendamentoModelo modelo = new AgendamentoModelo();
		
		try
		{
			ficheiro.stream.seek(4);
			
			for (int i = 0; i < ficheiro.getNregistos(); ++i)
			{
				modelo.read( ficheiro.stream );
				
				if (modelo.getCodAg() == idPrc && modelo.getStatus() == true)
					return modelo;
			}					
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}	
		return modelo;	
	}

	public static void pesquisarAgendamentoPorData(String dataProcurada)
	{
		AgendamentoFile ficheiro = new AgendamentoFile();
		AgendamentoModelo modelo = new AgendamentoModelo();
		
		try
		{
			ficheiro.stream.seek(4);
			
			for (int i = 0; i < ficheiro.getNregistos(); ++i)
			{
				modelo.read( ficheiro.stream );
				
				if (modelo.getDataAgenda().equalsIgnoreCase( dataProcurada ) && modelo.getStatus() == true)
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
	public static void pesquisarAgendamentoPorID(int idProcurado)
	{
		AgendamentoFile ficheiro = new AgendamentoFile();
		AgendamentoModelo modelo = new AgendamentoModelo();
		
		try
		{
			ficheiro.stream.seek(4);
			
			for (int i = 0; i < ficheiro.getNregistos(); ++i)
			{
				modelo.read( ficheiro.stream );
				
				if (modelo.getCodAg() == idProcurado  &&
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
