import java.awt.EventQueue;
import javax.swing.JFrame;

import org.jfugue.pattern.Pattern;
import org.jfugue.player.Player;

public class Syntext {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Syntext window = new Syntext();
					window.frame.setVisible(true);
					Player player = new Player();
					Pattern pattern = new Pattern("V0 I[Piano] Eq Ch. | Eq Ch. | Dq Eq Dq Cq   V1 I[Flute] Rw | Rw | GmajQQQ CmajQ");
					player.play(pattern);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Syntext() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
