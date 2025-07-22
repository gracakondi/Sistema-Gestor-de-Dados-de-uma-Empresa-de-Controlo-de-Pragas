/*------------------------------------
Tema: Gestão de uma Empresa Controlo de Pragas
Nome: Gildo Kondi
Numero: 33049
Ficheiro: ServicoVisao.java
Data: 24.06.2024
--------------------------------------*/

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import SwingComponents.*;
import Calendario.*;
import javax.swing.UIManager.*;

public class ServicoVisao extends JFrame
{
	PainelCentro centro;
	PainelSul sul;
	boolean editar;
	
	public ServicoVisao(boolean alterar, ServicoModelo modelo)
	{
		super("Cadastro de Novo Servico");
		
		editar = alterar;
		
		definirTema();
		
		JPanel painelNorte = new JPanel();
		JLabel lbImagem = new JLabel(new ImageIcon("image/sistema32.png"));
		painelNorte.add(lbImagem);
		
		getContentPane().add(painelNorte, BorderLayout.NORTH);
		
		if (!alterar)
			getContentPane().add(centro = new PainelCentro(), BorderLayout.CENTER);
		else
			getContentPane().add(centro = new PainelCentro(modelo), BorderLayout.CENTER);
		
		getContentPane().add(sul = new PainelSul(), BorderLayout.SOUTH);
		
		
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	
	class PainelCentro extends JPanel
	{
		private JTextField codServicoJTF, nomeJTF, precoJTF, duracaoJTF, produtoUsadoJTF;
		private ServicoFile ServicoFile;
		
		public PainelCentro()
		{
			setLayout( new GridLayout(3, 4, 10, 5) );
			
			ServicoFile = new ServicoFile();
			
			//linha1
			add( new JLabel("Código do Serviço") );
			add( codServicoJTF = new JTextField() );
			codServicoJTF.setText( "000" + ServicoFile.getProximoCodigo() );
			codServicoJTF.setFocusable(false);
			
			add( new JLabel("Nome do Serviço") );
			add( nomeJTF = new JTextField() );
			
			//linha2
			add( new JLabel("Preco") );
			add( precoJTF = new JTextField() );

			add( new JLabel("Duração") );
			add( duracaoJTF = new JTextField() );
			
			//linha3
			add( new JLabel("Produtos Usados") );
			add( produtoUsadoJTF = new JTextField() );
			
		}

		public PainelCentro(ServicoModelo modelo)
		{
			setLayout( new GridLayout(3, 4, 10, 5) );
			
			ServicoFile = new ServicoFile();
			
			//linha1
			add( new JLabel("Código do Serviço") );
			add( codServicoJTF = new JTextField() );
			codServicoJTF.setText( "000"+ modelo.getcodServico() );
			codServicoJTF.setFocusable(false);
			
			add( new JLabel("Nome do Serviço") );
			add( nomeJTF = new JTextField() );
			nomeJTF.setText( modelo.getNomeServico() );
			
			//linha2
			add( new JLabel("Preço") );
			add( precoJTF = new JTextField() );
			precoJTF.setText(  modelo.getPreco() + "" );

			add( new JLabel("Duração") );
			add( duracaoJTF = new JTextField() );
			duracaoJTF.setText(  modelo.getDuracao() + "" );
			
			//linha3
			add( new JLabel("Produtos Usados") );
			add( produtoUsadoJTF = new JTextField() );
			produtoUsadoJTF.setText( modelo.getProdutoUsado() );

		}
		
		//--- metodos get
		public int getcodServico()
		{
			return Integer.parseInt( codServicoJTF.getText().trim());
		}
		public String getNomeServico()
		{
			return nomeJTF.getText().trim();
		}
		public int getPreco()
		{
			return Integer.parseInt( precoJTF.getText().trim());
		}
		public int getDuracao()
		{
			return Integer.parseInt( duracaoJTF.getText().trim());
		}
		public String getProdutoUsado()
		{
			return produtoUsadoJTF.getText().trim();
		}
		
		//--- metodos set
		public void setcodServico(int codServico)
		{
			codServicoJTF.setText("" + codServico);
		}
		public void setPreco(String preco)
		{
			precoJTF.setText( "" + preco );
		}
			public void setDuraco(String duracao)
		{
			duracaoJTF.setText( "" + duracao );
		}
		public void setnomeServico(String nome)
		{
			nomeJTF.setText(nome);
		}
			public void setprodutoUsado(String produtoUsado)
		{
			produtoUsadoJTF.setText( produtoUsado );
		}
		
		//--- salvar
		public void salvar()
		{			
			ServicoModelo modelo = new ServicoModelo(getcodServico(), getPreco(), getDuracao(),
			getNomeServico(), getProdutoUsado(), true);
			
					
			JOptionPane.showMessageDialog(null, modelo.toString() );
			
			modelo.salvar();			
			dispose();
		}
		
		//--- metodo principal para alterar
		public void alterar()
		{			
			ServicoModelo modelo = new ServicoModelo(getcodServico(), getPreco(), getDuracao(),
			getNomeServico(), getProdutoUsado(), true);
					
			JOptionPane.showMessageDialog(null, modelo.toString() );
			
			modelo.salvarDados();			
			dispose();
		}
		
	}
	
	class PainelSul extends JPanel implements ActionListener
	{
		JButton salvarJB, cancelarJB;
		
		public PainelSul()
		{
			add(salvarJB = new JButton("Salvar", new ImageIcon("image/save24.png") ) );
			add(cancelarJB = new JButton("Cancelar", new ImageIcon("image/cancel24.png")) );
			
			salvarJB.addActionListener(this);
			cancelarJB.addActionListener(this);
		}
		
		public void actionPerformed(ActionEvent evt)
		{
			if (evt.getSource() == salvarJB)
				if (editar)
					centro.alterar();
				else
					centro.salvar();
			else
				dispose();
		}
	}
	
	 public void definirTema() 
	 {
        try {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
        }
    }

	public static void main(String[] args) {
		new ServicoVisao(false, new ServicoModelo());
	}
}
