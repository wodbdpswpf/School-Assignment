
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import javax.swing.JPanel;
import java.awt.Frame;
import java.awt.BorderLayout;
import javax.swing.JDialog;
import javax.swing.JButton;
import java.awt.Rectangle;
import java.io.UnsupportedEncodingException;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.KeySpec;
import java.util.prefs.Preferences;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JLabel;

public class LoginDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;

	private JButton okBtn = null;

	private JButton cancelBtn = null;

	private JTextField idTxtFld = null;

	private JLabel idLabel = null;


	private final String ENCRYPT_KEYWORD = "LoginDialog"; // @jve:decl-index=0:

	/**
	 * This method initializes okBtn
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getOkBtn() {
		if (okBtn == null) {
			okBtn = new JButton();
			okBtn.setBounds(new Rectangle(126, 128, 73, 23));
			okBtn.setText("Ok");
			okBtn.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {

					LoginDialog.this.setVisible(false);
					LoginDialog.this.dispose();
					Drawinfo_Controller controller = new Drawinfo_Controller(idTxtFld.getText());
					Editor_View view = new Editor_View(controller);
					controller.attachView(view);
					controller.startUp();
				}
			});
		}
		return okBtn;
	}

	/**
	 * This method initializes cancelBtn
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getCancelBtn() {
		if (cancelBtn == null) {
			cancelBtn = new JButton();
			cancelBtn.setBounds(new Rectangle(207, 128, 73, 23));
			cancelBtn.setText("Cancel");
			cancelBtn.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {

					LoginDialog.this.setVisible(false);
					LoginDialog.this.dispose();
				}
			});
		}
		return cancelBtn;
	}

	/**
	 * This method initializes idTxtFld
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getIdTxtFld() {
		if (idTxtFld == null) {
			idTxtFld = new JTextField();
			idTxtFld.setBounds(new Rectangle(126, 27, 155, 22));
		}
		return idTxtFld;
	}

	/**
	 * This method initializes pwTxtFld
	 * 
	 * @return javax.swing.JPasswordField
	 */
	
	/**
	 * @param args
	 */

	/**
	 * @param owner
	 */
	public LoginDialog(Frame owner) {
		super(owner);
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {

		this.setSize(300, 200);
		this.setContentPane(getJContentPane());

	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			idLabel = new JLabel();
			idLabel.setBounds(new Rectangle(22, 30, 90, 17));
			idLabel.setText("ID");

			

			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getOkBtn(), null);
			jContentPane.add(getCancelBtn(), null);
			jContentPane.add(getIdTxtFld(), null);
		
			jContentPane.add(idLabel, null);
		
		}
		return jContentPane;
	}

}