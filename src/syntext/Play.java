package syntext;

import Translator;
import org.jfugue.pattern.Pattern;

/* Esta classe é responsável por reproduzir o pattern gerado pelo translator*/
public class Play{

// Chama a Translator para construir o Pattern do JFugue
    private String pattern = Translator();

//  Inicializa o player
	Public Play(){
        Player pl = new Player();
    }

   /**
    * Toca o Pattern e mantém atualizado o status
    * 
    * @param pattern
    *           Texto traduzido em pattern do JFugue.
    * @return void
    */
    public void Plays(String pattern){

        pl.play( pattern );
        Sequence sq = pl.getSequence( pattern );
        ManagedPlayer mp = pl.getManagedPlayer();
        mp.start( sq );
        while ( mp.isPlaying() )
        {
        System.out.println("Reproduzindo");
        }

        System.out.println("Parado");
    }
}
