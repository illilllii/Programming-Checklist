package sms;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import org.json.simple.JSONObject;

import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;

public class SendMessage extends JFrame{

	private String phoneNumber, message;
	private JLabel laPhoneNumber, laMessage;
	private JTextField tfPhoneNumber, tfMessage;
	private JButton btnSend, btnReset;
	private Container c;
	private GridLayout grid;
	
	private void ��������(String to, String text) {
		String api_key = "NCSOWPSKB6PRM3T2";
	    String api_secret = "FZ3GOWBA2NV9MW5KIHB7TOBFVWKSTYGN";
	    Message coolsms = new Message(api_key, api_secret);

	    // 4 params(to, from, type, text) are mandatory. must be filled
	    HashMap<String, String> params = new HashMap<String, String>();
	    params.put("to", to);
	    params.put("from", "01088929749");
	    params.put("type", "SMS");
	    params.put("text", text);
	    params.put("app_version", "test app 1.2"); // application name and version

	    try {
	      JSONObject obj = (JSONObject) coolsms.send(params);
	      System.out.println("�޽����� ���۵Ǿ����ϴ�.");
	      System.out.println(obj.toString());
	      // 1�� obj(���ڿ�) -> �ڹ� ������Ʈ
	      // 2�� error�� �ִ��� Ȯ��
	      // 3�� DB insert
	    } catch (CoolsmsException e) {
	      System.out.println(e.getMessage());
	      System.out.println(e.getCode());
	    }
	}
		
	private void initObject() {
		grid = new GridLayout(3, 2);
		laPhoneNumber = new JLabel("������");
		laMessage = new JLabel("����");
		tfPhoneNumber = new JTextField("");
		tfMessage = new JTextField("");
		btnSend = new JButton("����");
		btnReset = new JButton("�ʱ�ȭ");
		c = getContentPane();
	}
	
	private void initSetting() {
		setTitle("���� ������");
		setSize(300, 200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		c.setLayout(grid);
	}
	
	private void initBatch() {
		c.add(laPhoneNumber);
		c.add(tfPhoneNumber);
		c.add(laMessage);
		c.add(tfMessage);
		c.add(btnSend);
		c.add(btnReset);		
	}
	
	public SendMessage() {
		initObject();
		initSetting();
		initBatch();
		
		btnReset.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tfPhoneNumber.setText("");
				tfMessage.setText("");				
			}
		});
		btnSend.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				phoneNumber = tfPhoneNumber.getText();
				message = tfMessage.getText();
				��������(phoneNumber,message);
			}		
		});
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new SendMessage();
		
	}

}
