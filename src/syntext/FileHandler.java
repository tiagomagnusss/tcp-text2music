package syntext;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FileHandler
{

   /**
    * Ctor default.
    */
   public FileHandler()
   {}

   /**
    * Lê o texto de um arquivo escolhido pelo usuário.
    * 
    * @throws IOException
    */
   public String importFile()
      throws IOException
   {
      File file = fileSearch();
      String fileText = "";

      // se o arquivo é válido
      if ( file != null )
      {
         StringBuilder sbText = new StringBuilder();
         BufferedReader reader = new BufferedReader( new FileReader( file ) );
         String line;

         // lê linha a linha até o fim
         while ( ( line = reader.readLine() ) != null )
         {
            sbText.append( line ).append( "\n" );
         }

         reader.close();
         fileText = sbText.toString();
      }

      return fileText;
   }

   /**
    * Salva o arquivo MIDI.
    * 
    * @TODO
    */
   public void saveFile()
   {

   }

   /**
    * Abre o prompt para buscar um arquivo.
    * 
    * @return Arquivo selecionado ou nulo se não selecionou.
    */
   private File fileSearch()
   {
      File file = null;
      JFileChooser jfc = new JFileChooser( System.getProperty( "user.home" ) + "/Desktop" );
      jfc.setDialogTitle( "Selecione um arquivo texto..." );

      jfc.setAcceptAllFileFilterUsed( false );
      jfc.addChoosableFileFilter( new FileNameExtensionFilter( ".txt", "txt" ) );

      if ( jfc.showOpenDialog( null ) == JFileChooser.APPROVE_OPTION )
         file = jfc.getSelectedFile();

      return file;
   }
}
