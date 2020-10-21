package syntext;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
   {
      Pattern pt = uut.translate( "ABCuDEFaaG bcdefAB,CDE5FG A9BCD;EFG" );
      String expect = "A5 B5 C5 I7 D5 E5 F5 F5 F5 G5 :CON(7,120) I7 R R R R R A5 B5 I20 R C5 D5 E5 I25 F5 G5 :CON(7,60) I7 A5 I16 B5 C5 D5 I76 I20 R E5 F5 G5 ";
      assertEquals( expect, pt.toString() );
   }

}
