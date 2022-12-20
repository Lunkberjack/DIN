package random;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class Conversor extends JFrame {

	private JPanel contentPane;
	private JTextField ingresar;
	private JLabel enviar;
	private JButton bBinario;
	private JButton bOctal;
	private JButton bHexadecimal;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		Conversor frame = new Conversor();
		frame.setVisible(true);
	}

	/**
	 * Create the frame.
	 */
	public Conversor() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);

		contentPane = new JPanel();

		ingresar = new JTextField();
		ingresar.setBounds(152, 36, 117, 20);
		contentPane.add(ingresar);
		ingresar.setColumns(10);

		enviar = new JLabel();
		enviar.setBounds(152, 205, 117, 20);
		contentPane.add(enviar);

		JButton bBinario = new JButton("Binario");
		bBinario.setBounds(152, 78, 117, 23);
		bBinario.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				enviar.setText(decimalABinario(Long.valueOf(ingresar.getText())));
			}
		});
		contentPane.add(bBinario);

		JButton bOctal = new JButton("Octal");
		bOctal.setBounds(152, 122, 117, 23);
		bOctal.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				enviar.setText(decimalAOctal(Integer.valueOf(ingresar.getText())));
			}
		});
		contentPane.add(bOctal);
		
		JButton bHexadecimal = new JButton("Hexadecimal");
		bHexadecimal.setBounds(152, 167, 117, 23);
		bHexadecimal.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				enviar.setText(decimalAHexadecimal(Integer.valueOf(ingresar.getText())));
			}
		});
		contentPane.add(bHexadecimal);

		contentPane.setLayout(null);
		this.setContentPane(contentPane);
	}
	
	public String decimalABinario (long decimal) {
		if(decimal <= 0) {
			return "0";
		}
		StringBuilder binario = new StringBuilder();
		while (decimal > 0) {
			short residuo = (short) (decimal % 2);
			decimal = decimal / 2;
			binario.insert(0, String.valueOf(residuo));
		}
		return binario.toString();

	}

	public static String decimalAOctal(int decimal) {
		String octal = "";
		String caracteresOctales = "01234567";
		while (decimal > 0) {
			int residuo = decimal % 8;
			octal = (caracteresOctales.charAt(residuo) + octal);
			decimal /= 8;
		}
		return octal;
	}

    public static String decimalAHexadecimal(int decimal) {
        String hexadecimal = "";
        String caracteresHexadecimales = "0123456789abcdef";
        while (decimal > 0) {
            int residuo = decimal % 16;
            hexadecimal = caracteresHexadecimales.charAt(residuo) + hexadecimal;
            decimal /= 16;
        }
        return hexadecimal;
    }
}
