package syntext;

import javax.swing.JLabel;

import javax.sound.midi.Sequence;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;

import org.jfugue.pattern.Pattern;
import org.jfugue.player.Player;
import org.jfugue.player.ManagedPlayer;

@SuppressWarnings("unused")

/* Esta classe Ã© responsÃ¡vel por reproduzir o pattern gerado pelo translator*/

public class Play{
   
    Player player;
    ManagedPlayer mp;
    Sequence sq;
    JLabel status;

//  Inicializa o player
	public Play(){
        player = new Player();

        // Define mp como controlador do player e inicia reprodução
        mp = player.getManagedPlayer();       
        
    }

   /**
    * Toca o Pattern
    * 
    * @param pattern
    *           Texto traduzido em pattern do JFugue
    * @return void
    */
    public void plays(Pattern pattern){

        // Gera sequencia a ser usada pelo Managed Player
        sq = player.getSequence( pattern );

        // Cuida das exceções do ManagedPlayer
        try {
			mp.start(sq);
		} catch (InvalidMidiDataException e) {
			e.printStackTrace();
		} catch (MidiUnavailableException e) {
			e.printStackTrace();
		}
    }

    /**
    * Atualiza o label Status para Reproduzindo/Pausado
    */
    public JLabel status(){
        if ( mp.isPlaying() )
        {
            return status = new JLabel("Reproduzindo");
        }
        else {
            return status = new JLabel("Parado");
        }
    }

    /**
    * Pausa o pattern sendo tocado
    */
    public void pause(){
        mp.pause();
    }

    /**
    * Retorna a reproduzir a partir do ponto onde foi pausado
    */
    public void resume(){
        mp.resume();
    }
    
    /**
    * Volta a reproduzir o pattern desde o inÃ­cio
    */
    public void stop(){
        mp.reset();
    }
}
