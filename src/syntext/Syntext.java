package syntext;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LookAndFeel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import org.jfugue.pattern.Pattern;
import java.awt.Font;
import java.awt.Toolkit;

@SuppressWarnings ( "serial" )
public class Syntext extends JFrame
{
	private final String RESUME = "Resumir";

	private final String PAUSE = "Pausar";
	
	private final String STOP = "Parar";
	
	private final String PLAY = "Reproduzir";

	private JFrame frmSyntext;

	private JLabel lblPlayer;

	private JTable tbMap;

	private FileHandler fileHandler = new FileHandler();

	private Translator translator = new Translator();

	private Play play = new Play();

	/**
	 * Lança a aplicação
	 */
	public static void main( String[] args )
	{
		EventQueue.invokeLater( new Runnable()
		{
			public void run()
			{
				try
				{
					Syntext window = new Syntext();
					window.frmSyntext.setVisible( true );
				}
				catch ( Exception e )
				{
					e.printStackTrace();
				}
			}
		} );
	}

	/**
	 * Cria a aplicação
	 */
	public Syntext()
	{
		initialize();
	}

	public static void setLookAndFeel( LookAndFeel newLookAndFeel )
			throws UnsupportedLookAndFeelException
	{}

	/**
	 * Inicializa os conteúdos da aplicação
	 */
	private void initialize()
	{
		String LAF = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";

		try
		{
			UIManager.setLookAndFeel( LAF );
			SwingUtilities.updateComponentTreeUI( this );
		}
		catch ( Exception e )
		{
			System.out.println( "LAF error: " + e );
		}

		// Configura趥s da janela
		frmSyntext = new JFrame();
		frmSyntext.setIconImage(Toolkit.getDefaultToolkit().getImage(Syntext.class.getResource("/icons/icons8_treble_clef_50px.png")));
		frmSyntext.setResizable(false);
		frmSyntext.setFont(new Font("Arial", Font.PLAIN, 18));
		frmSyntext.setTitle("Syntext");
		frmSyntext.setBounds( 100, 100, 608, 442 );
		frmSyntext.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		frmSyntext.getContentPane().setLayout( null );

		// Caixa de texto - entrada do usuário
		TextArea txtInput = new TextArea();
		txtInput.setBounds( 10, 213, 582, 190 );
		frmSyntext.getContentPane().add( txtInput );

		// Painel
		JPanel panel = new JPanel();
		panel.setOpaque(false);
		panel.setBorder( new LineBorder( new Color( 0, 0, 0 ) ) );
		panel.setBounds( 10, 29, 407, 124 );
		frmSyntext.getContentPane().add( panel );
		panel.setLayout( null );


		////////////////// LABELS
		JLabel lblIndicator = new JLabel("Status:");
		lblIndicator.setBounds(242, 193, 35, 14);
		frmSyntext.getContentPane().add(lblIndicator);

		JLabel lblStatus = new JLabel( "Pausado" );
		lblStatus.setBounds(278, 193, 105, 14);
		frmSyntext.getContentPane().add(lblStatus);

		JLabel lblInput = new JLabel( "Digite aqui seu texto" );
		lblInput.setBounds( 10, 193, 160, 14 );
		frmSyntext.getContentPane().add( lblInput );

		lblPlayer = new JLabel( "Player" );
		lblPlayer.setBounds( 10, 11, 46, 14 );
		frmSyntext.getContentPane().add( lblPlayer );

		JButton btnExit = new JButton( "Sair" );
		btnExit.addActionListener( new ActionListener()
		{
			public void actionPerformed( ActionEvent e )
			{
				System.exit( 0 );
			}
		} );
		btnExit.setBounds( 427, 130, 143, 23 );
		frmSyntext.getContentPane().add( btnExit );

		// Configuração do menu Mapa de char
		JButton btnMap = new JButton( "Mapa de Caracteres" );
		btnMap.addActionListener( new ActionListener()
		{
			public void actionPerformed( ActionEvent e )
			{
				JDialog dialog = new JDialog();
				dialog.setAlwaysOnTop( true );
				dialog.setSize( 900, 350 );
				dialog.setModal( false );
				dialog.setTitle( "Mapa de Caracteres" );
				JScrollPane panel = new JScrollPane( tbMap );
				panel.setSize( 900, 350 );
				dialog.getContentPane().add( panel );
				dialog.setDefaultCloseOperation( JDialog.DISPOSE_ON_CLOSE );
				dialog.setVisible( true );
				dialog.pack();
			}
		} );
		btnMap.setBounds( 427, 29, 143, 23 );
		frmSyntext.getContentPane().add( btnMap );

		tbMap = new JTable( new DefaultTableModel(
				new Object[][] { { "A", "Nota L\u00E1" }, { "B", "Nota Si" }, { "C", "Nota D\u00F3" }, { "D", "Nota R\u00E9" },
					{ "E", "Nota Mi" }, { "F", "Nota F\u00E1" }, { "G", "Nota Sol" },
					{ "Espa\u00E7o", "Dobra o volume. Se n\u00E3o for poss\u00EDvel, volta ao default (60/127)" },
					{ "!", "Troca para o instrumento Agogo" },
					{ "?", "Aumenta uma oitava. Se n\u00E3o for poss\u00EDvel, volta ao default (5)" },
					{ "D\u00EDgito 0-9", "Troca para o instrumento MIDI com o valor atual + d\u00EDgito" },
					{ "Nova linha \n", "Troca para o instrumento Tubular Bells" }, { ";", "Troca para o instrumento Pan Flute" },
					{ ",", "Troca para o instrumento Church Organ" },
					{ "Letras min\u00FAsculas, demais vogais e consoantes e outros caracteres",
					"Se caractere anterior era nota (A a G), repete. Se n\u00E3o, pausa por um instante" }, },
				new String[] { "Caractere", "A\u00E7\u00E3o" } ) );
		tbMap.setBounds( 180, 161, 1, 1 );
		tbMap.setEnabled( false );
		tbMap.setAutoResizeMode( WIDTH );

		JLabel lblTitle = new JLabel("");
		lblTitle.setBounds(128, 11, 181, 91);
		panel.add(lblTitle);
		lblTitle.setOpaque(true);
		lblTitle.setIcon(new ImageIcon(Syntext.class.getResource("/icons/font3.png")));

		JLabel lblIcon = new JLabel("");
		lblIcon.setIcon(new ImageIcon(Syntext.class.getResource("/icons/icons8_treble_clef_50px.png")));
		lblIcon.setBounds(319, 34, 64, 50);
		panel.add(lblIcon);


		////////////////// BUTTONS
		// Inicializando botões
		JButton btnLoad = new JButton( "Carregar arquivo..." );
		JButton btnDownload = new JButton( "Salvar em formato Midi" );   
		JButton btnPlay = new JButton( PLAY );      
		JButton btnPause = new JButton( PAUSE );
		JButton btnStop = new JButton( STOP );


		// Configuração do botão Carregar Arquivo
		btnLoad.addActionListener( new ActionListener()
		{
			public void actionPerformed( ActionEvent e )
			{
				try
				{
					String fileText = fileHandler.importFile();
					txtInput.setText( fileText );
				}
				catch ( IOException ex )
				{
					JOptionPane.showMessageDialog(frmSyntext, "Ocorreu um erro ao ler o arquivo:\n" + ex);
				}
			}
		} );
		btnLoad.setBounds( 427, 96, 143, 23 );
		frmSyntext.getContentPane().add( btnLoad );


		// Configura��o do botão Download
		btnDownload.setBounds( 427, 62, 143, 23 );
		frmSyntext.getContentPane().add( btnDownload );
		btnDownload.addActionListener( new ActionListener()
		{
			@Override
			public void actionPerformed( ActionEvent e )
			{
				try
				{
					// Chama a Translator para construir o Pattern do JFugue
					Pattern result = translator.translate( txtInput.getText() );
					
					// Abre a caixa de diálogo para converter o pattern em arquivo
					fileHandler.saveFile(result);
				}
				catch ( IOException ex )
				{
					JOptionPane.showMessageDialog(frmSyntext, "Ocorreu um erro ao salvar o arquivo:\n" + ex);
				}
			}
		} );

		// Configuração do botao Play
		btnPlay.setBounds( 10, 11, 100, 23 );
		btnPlay.addActionListener( new ActionListener()
		{
			@Override
			public void actionPerformed( ActionEvent e )
			{
				// se desabilita até ser habilitado externamente pelo controlador da música
				btnPlay.setEnabled( false );

				// Chama a Translator para construir o Pattern do JFugue
				Pattern result = translator.translate( txtInput.getText() );

				// Chama o player para tocar o Pattern
				play.plays(result);

				// Habilita os botões de Pause e Stop
				btnPause.setEnabled( true );
				btnStop.setEnabled( true );

				// Atualiza status
				lblStatus.setText( play.status() );

			}
		} );
		panel.add( btnPlay );

		// Configuração do botão Pause
		btnPause.addActionListener( new ActionListener()
		{
			public void actionPerformed( ActionEvent e )
			{
				// se está como pause, desabilita o play e muda de nome
				if ( btnPause.getText().equals( PAUSE ) )
				{
					btnPause.setText( RESUME );
					// pausar a musica
					play.pause();

					// Atualiza status
					lblStatus.setText( play.status() );
				}
				else
				{
					btnPause.setText( PAUSE );
					// continua a musica
					play.resume();

					// Atualiza status
					lblStatus.setText( play.status() );
				}           

			}
		} );
		btnPause.setEnabled( false );
		btnPause.setBounds( 10, 45, 100, 23 );
		panel.add( btnPause );

		// Configuração do botão Stop
		btnStop.addActionListener( new ActionListener()
		{
			public void actionPerformed( ActionEvent e )
			{
				// para a musica
				play.stop();

				// Desabilita os botões de Pause e Stop
				btnPause.setEnabled( false );
				btnStop.setEnabled( false );           

				// Habilita botão Play
				btnPlay.setEnabled( true );

				// Atualiza status
				lblStatus.setText( play.status() );

				// se está como resume, muda para pause
				if ( btnPause.getText().equals( RESUME ) )
				{
					btnPause.setText( PAUSE );
				}

			}
		} );
		btnStop.setEnabled( false );
		btnStop.setBounds( 10, 79, 100, 23 );
		panel.add( btnStop );

	}
}
