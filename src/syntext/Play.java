package syntext;

import javax.swing.JLabel;

import javax.sound.midi.Sequence;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;

import org.jfugue.pattern.Pattern;
import org.jfugue.player.Player;
import org.jfugue.player.ManagedPlayer;

/* Esta classe é responsável por reproduzir o pattern gerado pelo translator*/
public class Play{
   
    Player player;
    ManagedPlayer mp;
    Sequence sq;
    JLabel status;

//  Inicializa o player
	public Play(){
        player = new Player();
    }

   /**
    * Toca o Pattern e mantém atualizado o status
    * 
    * @param pattern
    *           Texto traduzido em pattern do JFugue
    * @return String Parado/Reproduzindo
    */
    public void plays(Pattern pattern){

        // Inicia reprodução
        player.play( pattern );

        // Gera sequencia a ser usada pelo Managed Player
        sq = player.getSequence( pattern );

        // Define mp como controlador do player
        mp = player.getManagedPlayer();
        mp.start( sq );

    }

    public JLabel status(){
        while ( mp.isPlaying() )
        {
            return status = new JLabel("Reproduzindo");
        }
            return status = new JLabel("Parado");  
    }

    /**
    * Pausa o pattern sendo tocado
    * 
    * @param void
    * @return void
    */
    public void pause(){
        mp.pause();
    }

    /**
    * Retorna a reproduzir a partir do ponto onde foi pausado
    * 
    * @param void
    * @return void
    */
    public void resume(){
        mp.resume();
    }
    
    /**
    * Volta a reproduzir o pattern desde o início
    * 
    * @param void
    * @return void 
    */
    public void stop(){
        mp.reset();
    }
}
