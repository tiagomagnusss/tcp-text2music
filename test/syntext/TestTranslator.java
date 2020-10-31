package syntext;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;

import org.jfugue.pattern.Pattern;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestTranslator
{

   private static Translator uut;

   @BeforeEach
   void setUp()
      throws Exception
   {
      uut = new Translator();
   }

   @Test
   void testParse()
      throws InvalidMidiDataException,
      MidiUnavailableException
   {
      Pattern pt = uut.translate( "ABCuDEFaaG bcdefAB,CDE5FG A9BCD;EFG" );
      String expect = "A5 B5 C5 I7 D5 E5 F5 F5 F5 G5 :CON(7,120) R R R R R A5 B5 I20 C5 D5 E5 I25 F5 G5 :CON(7,60) A5 I34 B5 C5 D5 I76 E5 F5 G5 ";

      assertEquals( expect, pt.toString() );
   }

}
