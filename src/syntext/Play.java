package syntext;

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
    String status;

//  Inicializa o player
	public Play(){
        player = new Player();

        // Define mp como controlador do player e inicia reprodu��o
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

        // Cuida das exce��es do ManagedPlayer
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
    public String status(){

        if ( mp.isPlaying() )
        {
            status = "Reproduzindo";
        }
        else {
            status = "Parado";
        }
        
        return status;
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
    * Volta a reproduzir o pattern desde o início
    */
    public void stop(){
        mp.reset();
    }
}
