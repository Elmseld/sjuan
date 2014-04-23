package sjuan;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ClientGUI extends JPanel implements ActionListener {
	private ClientController controller;
	private JTextField tfRequest = new JTextField();
	private JTextArea taResponse = new JTextArea();
	private JButton btnExit = new JButton("Avsluta");

	public ClientGUI(ClientController controller) {
		JPanel pnlRequest = new JPanel(new BorderLayout());
		this.controller = controller;
		setPreferredSize(new Dimension(300,400));
		setLayout(new BorderLayout());
		pnlRequest.add(new JLabel("Request: "),BorderLayout.WEST);
		pnlRequest.add(tfRequest,BorderLayout.CENTER);
		tfRequest.addActionListener(this);
		btnExit.addActionListener(this);
		add(pnlRequest,BorderLayout.NORTH);
		add(new JScrollPane(taResponse),BorderLayout.CENTER);
		add(btnExit,BorderLayout.SOUTH);
		showWindow();
	}

	private void showWindow() {
		JFrame frame = new JFrame("Client UI");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(this);
		frame.pack();
		frame.setVisible(true);
	}

	public void setResponse(String message) {
		taResponse.setText(message);
	}

	public void setError(String error) {
		taResponse.setText(error);
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==tfRequest) {
			controller.newRequest(tfRequest.getText());
		} else if (e.getSource()==btnExit) {
			controller.exit();
		}
	}
}
