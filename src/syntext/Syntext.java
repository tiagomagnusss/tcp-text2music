package syntext;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.LookAndFeel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.LineBorder;

import org.jfugue.pattern.Pattern;
import org.jfugue.player.Player;

@SuppressWarnings ( "serial" )
public class Syntext extends JFrame
{

   private JFrame frame;

   private JLabel lblPlayer;

   /**
    * Launch the application.
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
               window.frame.setVisible( true );
            }
            catch ( Exception e )
            {
               e.printStackTrace();
            }
         }
      } );
   }

   /**
    * Create the application.
    */
   public Syntext()
   {
      initialize();
   }

   public static void setLookAndFeel( LookAndFeel newLookAndFeel )
      throws UnsupportedLookAndFeelException
   {}

   /**
    * Initialize the contents of the frame.
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

      frame = new JFrame();
      frame.setBounds( 100, 100, 450, 300 );
      frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
      frame.getContentPane().setLayout( null );

      TextArea txtInput = new TextArea();
      txtInput.setBounds( 0, 174, 434, 87 );
      frame.getContentPane().add( txtInput );

      JLabel lblInput = new JLabel( "Digite aqui seu texto" );
      lblInput.setBounds( 10, 161, 160, 14 );
      frame.getContentPane().add( lblInput );

      JButton btnLoad = new JButton( "Carregar arquivo..." );
      btnLoad.setBounds( 281, 63, 143, 23 );
      frame.getContentPane().add( btnLoad );

      JButton btnDownload = new JButton( "Download" );
      btnDownload.setBounds( 281, 29, 143, 23 );
      frame.getContentPane().add( btnDownload );

      JPanel panel = new JPanel();
      panel.setBorder( new LineBorder( new Color( 0, 0, 0 ) ) );
      panel.setBounds( 10, 29, 261, 113 );
      frame.getContentPane().add( panel );
      panel.setLayout( null );

      JButton btnPlay = new JButton( "Play" );
      btnPlay.setBounds( 10, 11, 100, 23 );
      btnPlay.addActionListener( new ActionListener()
      {
         @Override
         public void actionPerformed( ActionEvent e )
         {
            Player player = new Player();
            Pattern pattern = new Pattern( "V0 I[Piano] Eq Ch. | Dq Eq Dq Cq   V1 I[Flute] Rw | Rw | GmajQQQ CmajQ" );
            player.play( pattern );
         }
      } );
      panel.add( btnPlay );

      JButton btnPause = new JButton( "Pause" );
      btnPause.setBounds( 10, 45, 100, 23 );
      panel.add( btnPause );

      JButton btnStop = new JButton( "Stop" );
      btnStop.setBounds( 10, 79, 100, 23 );
      panel.add( btnStop );

      JLabel lblStatus = new JLabel( "Parado" );
      lblStatus.setBounds( 177, 49, 46, 14 );
      panel.add( lblStatus );

      lblPlayer = new JLabel( "Player" );
      lblPlayer.setBounds( 10, 11, 46, 14 );
      frame.getContentPane().add( lblPlayer );

      JButton btnExit = new JButton( "Sair" );
      btnExit.addActionListener( new ActionListener()
      {
         public void actionPerformed( ActionEvent e )
         {
            System.exit( 0 );
         }
      } );
      btnExit.setBounds( 281, 119, 143, 23 );
      frame.getContentPane().add( btnExit );
   }

}
