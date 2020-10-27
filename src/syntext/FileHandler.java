package syntext;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.OutputStream;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.jfugue.pattern.Pattern;
import org.jfugue.midi.MidiFileManager;

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
    * Abre um prompt para salvar um arquivo Midi a partir de um Pattern.
    * 
    * @param pattern
    *           Texto traduzido em pattern do JFugue
    * @throws IOException
    */
   public void saveFile(Pattern pattern)
   	throws IOException
   {
	   JFrame parentFrame = new JFrame();
	   JFileChooser fileChooser = new JFileChooser();
	   fileChooser.setDialogTitle("Especifique um local para salvar...");   
	   
	   fileChooser.setAcceptAllFileFilterUsed( false );
	   fileChooser.addChoosableFileFilter( new FileNameExtensionFilter( "Arquivo Midi (.mid)", "mid" ) );
	    
	   int userSelection = fileChooser.showSaveDialog(parentFrame);
	    
	   File file = new File(fileChooser.getSelectedFile()+".mid");
	   
	   if (userSelection == JFileChooser.APPROVE_OPTION) {
	       
	       OutputStream output = new FileOutputStream(file);
		   MidiFileManager.savePatternToMidi(pattern, output);
		   output.close();
	   }   	   
   }

   /**
    * Abre o prompt para buscar um arquivo para leitura.
    * 
    * @return Arquivo selecionado ou nulo se não selecionou.
    */
   private File fileSearch()
   {
      File file = null;
      JFileChooser fileChooser = new JFileChooser( System.getProperty( "user.home" ) + "/Desktop" );
      fileChooser.setDialogTitle( "Selecione um arquivo texto..." );

      fileChooser.setAcceptAllFileFilterUsed( false );
      fileChooser.addChoosableFileFilter( new FileNameExtensionFilter( "Arquivo de texto (.txt)", "txt" ) );

      if ( fileChooser.showOpenDialog( null ) == JFileChooser.APPROVE_OPTION )
         file = fileChooser.getSelectedFile();

      return file;
   }
}
