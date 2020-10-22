package syntext;

import org.jfugue.pattern.Pattern;

/* Esta classe é responsável  por manter o controle do que será reproduzido pela biblioteca JFugue,
como volume, nota, BPM, etc. Possui getters e setters para redefinir os valores que devem ser tocados em seguida. */
public class Control{

// define default para status 0: parado, 1: reproduzindo
   private static final int STATUS = 0;

// método construtor da classe, inicializa o controle com valores padrão
	Public Control(){
        status = STATUS;
    }

   /**
    * Atualiza o status da reprodução da string
    * 
    * @param algo_do_managed_player
    *           Texto para ser traduzido em pattern do JFugue.
    * @return Pattern de acordo com o texto de entrada.
    */
	Public void getStatus(int status){
        status = resposta_do_managed_player;

        if (status)
            print "Parado";
        else
            print "Reproduzindo"
    }

}
