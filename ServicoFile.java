/*------------------------------------
Tema: Gestão de uma Empresa Controlo de Pragas
nomeServico: Gildo Kondi
Numero: 33049
Ficheiro: ServicoFile.java
Data: 31.05.2024
--------------------------------------*/

import javax.swing.*;
import SwingComponents.*;
import Calendario.*;
import java.io.*;

public class ServicoFile extends ObjectsFile
{
	
	public ServicoFile()
	{
		super("ServicoFile.dat", new ServicoModelo() );
	}
	
	public void salvarDados(ServicoModelo modelo)
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
			JOptionPane.showMessageDialog(null, "Falha ao Salvar um Novo Servico");
		}
	}
	
	public static void listarServicos()
	{
		ServicoFile ficheiro = new ServicoFile();
		ServicoModelo modelo = new ServicoModelo();
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
	
	public static StringVector getAllServices()
	{
		ServicoFile ficheiro = new ServicoFile();
		ServicoModelo modelo = new ServicoModelo();
		StringVector vector = new StringVector();
		
		try
		{
			ficheiro.stream.seek(4);
			
			for (int i = 0; i < ficheiro.getNregistos(); ++i)
			{
				modelo.read( ficheiro.stream );
				
				if (modelo.getStatus() == true)
					vector.add( modelo.getNomeServico() );
			}
						
			vector.sort();
			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}	
	
		return vector;
	}
	
		public static ServicoModelo buscarPorNome(String nome) {
    ServicoFile file = new ServicoFile();
    ServicoModelo modelo = new ServicoModelo();

    try {
        file.stream.seek(4); // pula o cabeçalho, se tiver
        for (int i = 0; i < file.getNregistos(); i++) {
            modelo.read(file.stream);
            
            // ⚠️ Compara o nome e verifica se está ativo
            if (modelo.getStatus() && modelo.getNomeServico().trim().equalsIgnoreCase(nome.trim())) {
                return modelo;
            }
        }
    } catch (IOException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(null, "Erro ao buscar serviço por nome.");
    }

    return null; // se não encontrar
}
	
	
	public void alterarDados(ServicoModelo modelo_novo)
	{
		ServicoModelo modelo_antigo = new ServicoModelo();
		
		try
		{
			stream.seek(4);
			
			for(int i = 0; i < getNregistos(); ++i)
			{
				modelo_antigo.read( stream );
				
				if (i == 0 && modelo_antigo.getcodServico() == modelo_novo.getcodServico())
				{
					stream.seek(4); 
					modelo_novo.write( stream );
					JOptionPane.showMessageDialog(null, "Dados alterados com sucesso!");
					return;
				}	
				else
				{
					if (modelo_antigo.getcodServico() + 1 == modelo_novo.getcodServico())
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
	
	public void eliminarDados(ServicoModelo modelo_novo)
	{
		ServicoModelo modelo_antigo = new ServicoModelo();
		
		try
		{
			stream.seek(4);
			
			for(int i = 0; i < getNregistos(); ++i)
			{
				modelo_antigo.read( stream );
				
				if (i == 0 && modelo_antigo.getcodServico() == modelo_novo.getcodServico())
				{
					stream.seek(4); 
					modelo_novo.write( stream );
					JOptionPane.showMessageDialog(null, "Dados eliminados com sucesso!");
					return;
				}	
				else
				{
					if (modelo_antigo.getcodServico() + 1 == modelo_novo.getcodServico())
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
	public static ServicoModelo getServicoPorNome(String nomeProcurado)
	{
		ServicoFile ficheiro = new ServicoFile();
		ServicoModelo modelo = new ServicoModelo();
		
		try
		{
			ficheiro.stream.seek(4);
			
			for (int i = 0; i < ficheiro.getNregistos(); ++i)
			{
				modelo.read( ficheiro.stream );
				
				if (modelo.getNomeServico().equalsIgnoreCase( nomeProcurado ) && modelo.getStatus() == true)
					return modelo;
			}					
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}	
		return modelo;	
	}
	public static void pesquisarServicoPorNome(String nomeProcurado)
	{
		ServicoFile ficheiro = new ServicoFile();
		ServicoModelo modelo = new ServicoModelo();
		
		try
		{
			ficheiro.stream.seek(4);
			
			for (int i = 0; i < ficheiro.getNregistos(); ++i)
			{
				modelo.read( ficheiro.stream );
				
				if (modelo.getNomeServico().equalsIgnoreCase( nomeProcurado ) && modelo.getStatus() == true)
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
