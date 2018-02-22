package zeno;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 * 将.txt格式的小说中无意义的回车换行去掉
 * @author Light
 *
 */
public class TxtImprover {

	public static void main(String[] args) {
		
		JFrame frame = new JFrame("TxtImprover");
		JTextField textField = new JTextField();
		JLabel label = new JLabel("---请输入小说文件的路径地址：---");
		textField.setPreferredSize(new Dimension(380, 30));
		JButton button = new JButton("OK");
		frame.setLayout(new FlowLayout());
		frame.add(label);
		frame.add(textField);
		frame.add(button);
		frame.setVisible(true);
		frame.setBounds(400, 300, 400, 140);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String pathname = textField.getText();
				File file = new File(pathname);
				if (file.isFile() && file.exists()) {
					try {
						FileInputStream fileInputStream = new FileInputStream(file);
						InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
						BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
						StringBuffer sb = new StringBuffer();
						String text = "", temp = null;
						while ((temp = bufferedReader.readLine()) != null) {
							if (temp.length() <= 1) {
								sb.append("\n\r");
								continue;
							}
							switch (temp.substring(temp.length()-1, temp.length())) {
							case "。": ;
							case "？": ;
							case "！": ;
							case "……":
								text += temp;
								sb.append(text+"\n\r");
								text = "";
								break;

							default:
								text += temp;
								break;
							}
						}
						File outFile = new File(pathname.substring(0, pathname.length()-4) + "2.txt");
						if (!outFile.exists()) {
							outFile.createNewFile();
						}
						FileWriter fw = new FileWriter(outFile);
						BufferedWriter bw = new BufferedWriter(fw);
						bw.write(sb.toString());
						bufferedReader.close();
						bw.close();
						fw.close();
						System.out.println("Mission Done");
						
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (UnsupportedEncodingException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		

	}

}
