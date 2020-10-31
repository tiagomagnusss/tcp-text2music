package syntext;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequence;
import javax.swing.JButton;

import org.jfugue.pattern.Pattern;
import org.jfugue.player.ManagedPlayer;
import org.jfugue.player.ManagedPlayerListener;
import org.jfugue.player.Player;

/* Esta classe é responsável por reproduzir o pattern gerado pelo translator */
public class Play
{
   JButton stop;

   Player player;

   ManagedPlayer mp;

   Sequence sq;

   String status;

   /**
    * Construtor do player.
    * 
    * @param btnStop
    *           Botão de stop da interface.
    */
   public Play( JButton btnStop )
   {
      stop = btnStop;
      player = new Player();

      // Define mp como controlador do player e inicia reprodução
      mp = player.getManagedPlayer();
      // adicionando listener para saber quando terminou de tocar
      mp.addManagedPlayerListener( new ManagedPlayerListener()
      {

         @Override
         public void onFinished()
         {
            stop.doClick();
         }

         @Override
         public void onPaused()
         {}

         @Override
         public void onReset()
         {}

         @Override
         public void onResumed()
         {}

         @Override
         public void onSeek( long arg0 )
         {}

         @Override
         public void onStarted( Sequence arg0 )
         {}

      } );
   }

   /**
    * Toca o Pattern
    * 
    * @param pattern
    *           Texto traduzido em pattern do JFugue
    * @return void
    */
   public void plays( Pattern pattern )
   {

      // Gera sequencia a ser usada pelo Managed Player
      sq = player.getSequence( pattern );

      // Cuida das exceções do ManagedPlayer
      try
      {
         mp.start( sq );
      }
      catch ( InvalidMidiDataException e )
      {
         e.printStackTrace();
      }
      catch ( MidiUnavailableException e )
      {
         e.printStackTrace();
      }
   }

   /**
    * Atualiza o label Status para Reproduzindo/Pausado
    */
   public String status()
   {

      if ( mp.isPlaying() )
      {
         status = "Reproduzindo";
      }
      else
      {
         status = "Parado";
      }

      return status;
   }

   /**
    * Pausa o pattern sendo tocado
    */
   public void pause()
   {
      mp.pause();
   }

   /**
    * Retorna a reproduzir a partir do ponto onde foi pausado
    */
   public void resume()
   {
      mp.resume();
   }

   /**
    * Volta a reproduzir o pattern desde o início
    */
   public void stop()
   {
      mp.reset();
   }
}
