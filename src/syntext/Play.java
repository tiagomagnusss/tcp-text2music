package syntext;

import org.jfugue.pattern.Pattern;
import org.jfugue.player.Player;
import org.jfugue.player.ManagedPlayer;
import javax.sound.midi.Sequence;

/* Esta classe é responsável por reproduzir o pattern gerado pelo translator*/
public class Play{

// Chama a Translator para construir o Pattern do JFugue
    private String pattern = Translator.translate(txt);

    
    Player player;
    
//  Inicializa o player
	public Play(){
        player = new Player();
    }

   /**
    * Toca o Pattern e mantém atualizado o status
    * 
    * @param pattern
    *           Texto traduzido em pattern do JFugue.
    * @return void
    */
    public void Plays(String pattern){

        player.play( pattern );
        Sequence sq = player.getSequence( pattern );
        ManagedPlayer mp = player.getManagedPlayer();
        mp.start( sq );
        while ( mp.isPlaying() )
        {
        System.out.println("Reproduzindo");
        }

        System.out.println("Parado");
    }
}
