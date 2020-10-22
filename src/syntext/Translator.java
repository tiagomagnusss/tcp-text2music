package syntext;

import org.jfugue.pattern.Pattern;

public class Translator
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
      PAUSE( "R " );

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

   private static final int DEFAULT_VOLUME = 60;

   private static final int MAX_INSTRUMENT = 127;

   private static final int MAX_VOLUME = 127;

   private static final int MAX_OCTAVE = 10;

   // variáveis de controle
   private int octave;

   private int instrument;

   private int volume;

   // última nota tocada
   private String lastNote;

   private boolean wasLastNote = false;

   public Translator()
   {
      octave = DEFAULT_OCTAVE;
      instrument = DEFAULT_INSTRUMENT;
      volume = DEFAULT_VOLUME;
      lastNote = Notes.PAUSE.getNota();
   }

   /**
    * Constrói a string do JFugue
    * 
    * @param txt
    *           Texto para ser traduzido em pattern do JFugue.
    * @return Pattern de acordo com o texto de entrada.
    */
   public Pattern translate( String txt )
   {
      StringBuilder output = new StringBuilder();
      for ( int i = 0; i < txt.length(); i++ )
      {
         char ch = txt.charAt( i );

         // se é um numeral
         if ( ch >= 48 && ch <= 57 )
         {
            output.append( setInstrument( instrument + Integer.valueOf( String.valueOf( ch ) ) ) );
         }
         else
         {
            switch ( ch )
            {
               case 'A':
                  output.append( setNote( Notes.LA ) );
                  break;
               case 'B':
                  output.append( setNote( Notes.SI ) );
                  break;
               case 'C':
                  output.append( setNote( Notes.DO ) );
                  break;
               case 'D':
                  output.append( setNote( Notes.RE ) );
                  break;
               case 'E':
                  output.append( setNote( Notes.MI ) );
                  break;
               case 'F':
                  output.append( setNote( Notes.FA ) );
                  break;
               case 'G':
                  output.append( setNote( Notes.SOL ) );
                  break;
               case 'a':
               case 'b':
               case 'c':
               case 'd':
               case 'e':
               case 'f':
               case 'g':
                  if ( wasLastNote )
                  {
                     output.append( lastNote );
                  }
                  else
                  {
                     output.append( Notes.PAUSE.getNota() );
                  }
                  break;
               case ' ':
                  output.append( setVolume( volume * 2 ) );
                  break;
               case 'I':
               case 'O':
               case 'U':
               case 'i':
               case 'o':
               case 'u':
                  // trocar para MIDI #7(Harpsichord)
                  output.append( setInstrument( 7 ) );
                  break;
               case '!':
                  // trocar para MIDI #114 (Agogo)
                  output.append( setInstrument( 114 ) );
                  break;
               case '?':
                  output.append( setOctave( octave * 2 ) );
                  break;
               case '\n':
                  // trocar para MIDI #15 (Tubular Bells)
                  output.append( setInstrument( 15 ) );
                  break;
               case ';':
                  // trocar para MIDI #76 (Pan Flute)
                  output.append( setInstrument( 76 ) );
                  break;
               case ',':
                  // trocar para MIDI #20 (Church Organ)
                  output.append( setInstrument( 20 ) );
                  break;
               default:
                  if ( wasLastNote )
                  {
                     output.append( lastNote );
                  }
                  else
                  {
                     output.append( Notes.PAUSE.getNota() );
                  }
            }
         }
      }

      return new Pattern( output.toString() );
   }

   /**
    * Define o instrumento.
    * 
    * @param val
    *           Valor do instrumento.
    * @return String com a string correspondente do JFugue
    */
   private String setInstrument( int val )
   {
      wasLastNote = false;
      if ( val <= MAX_INSTRUMENT )
         instrument = val;
      else
         instrument = DEFAULT_INSTRUMENT;
      return "I" + instrument + " ";
   }

   /**
    * Define o volume.
    * 
    * @param val
    *           Valor do volume.
    * @return String com a string correspondente do JFugue
    */
   private String setVolume( int val )
   {
      wasLastNote = false;
      if ( val <= MAX_VOLUME )
         volume = val;
      else
         volume = DEFAULT_VOLUME;
      return ":CON(7," + volume + ") ";
   }

   /**
    * Define a oitava.
    * 
    * @param val
    *           Valor da oitava.
    * @return String com a string correspondente do JFugue
    */
   private String setOctave( int val )
   {
      wasLastNote = false;
      if ( val < MAX_OCTAVE )
         octave = val;
      else
         octave = DEFAULT_OCTAVE;
      return String.valueOf( octave );
   }

   /**
    * Define a nota a ser tocada.
    * 
    * @param note
    *           Item de nota.
    * @return String com a string correspondente do JFugue
    */
   private String setNote( Notes note )
   {
      wasLastNote = true;
      lastNote = note.getNota() + octave + " ";
      return lastNote;
   }

}
