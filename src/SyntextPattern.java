import org.jfugue.pattern.*;
import java.util.Random;

public class SyntextPattern
{
   // Enumerável com as notas possíveis e a letra que cada uma representa
   public enum Notes
   {
      LA( "A" ),
      SI( "B" ),
      DO( "C" ),
      RE( "D" ),
      MI( "E" ),
      FA( "F" ),
      SOL( "G" ),
      PAUSE( "P" );

      private String nota;

      Notes( String nota )
      {
         this.nota = nota;
      }

      public String getNota()
      {
         return this.nota;
      }
   }

   // define variáveis default
   private static final int DEFAULT_OCTAVE = 5;

   private static final int DEFAULT_INSTRUMENT = 0;

   private static final int DEFAULT_BPM = 120;

   private static final int DEFAULT_VOLUME = 70;

   private static final int VOLUME_STEP = 10;

   private static final int MAX_LIMIT = 127;

   // variáveis de controle
   private int octave;

   private int instrument;

   private int bpm;

   private int volume;

   // texto de entrada
   private String text;

   // última nota tocada
   private String lastNote;

   public SyntextPattern()
   {
      // text = txt;
      octave = DEFAULT_OCTAVE;
      instrument = DEFAULT_INSTRUMENT;
      bpm = DEFAULT_BPM;
      volume = DEFAULT_VOLUME;
      lastNote = Notes.PAUSE.getNota();
   }

   /**
    * Constrói a string do JFugue
    * 
    * @param txt Texto para ser parseado.
    * @return Pattern de acordo com o texto de entrada.
    */
   public Pattern parse( String txt )
   {
      return new Pattern( txt.toString() );
   }

   /**
    * Define o BPM.
    * 
    * @param val Valor do BPM.
    * @return String com a string correspondente do JFugue
    */
   private String setBPM( int val )
   {
      if ( val > 0 )
         bpm = val;
      return "T" + bpm + " ";
   }

   /**
    * Define o instrumento.
    * 
    * @param val Valor do instrumento.
    * @return String com a string correspondente do JFugue
    */
   private String setInstrument( int val )
   {
      if ( val < MAX_LIMIT )
         instrument = val;
      return "I" + instrument + " ";
   }

   /**
    * Define o volume.
    * 
    * @param val Valor do volume.
    * @return String com a string correspondente do JFugue
    */
   private String setVolumeTo( int val )
   {
      if ( val < MAX_LIMIT )
         volume = val;
      return ":CON(7," + volume + ") ";
   }

}
