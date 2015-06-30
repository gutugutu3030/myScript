package gutu.lexerRunner;

import java.io.IOException;
import java.io.Reader;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class CodeDialog extends Reader {
	private String buffer = null;
	private int pos = 0;

	@Override
	public int read(char[] cbuf, int off, int len) throws IOException {
		// TODO 自動生成されたメソッド・スタブ
		if (buffer == null) {
			String in = showDialog();
			if (in == null) {
				return -1;
			}
			print(in);
			buffer = in + "\n";
			pos = 0;
		}
		int size = 0;
		int length = buffer.length();
		while (pos < length && size < len) {
			cbuf[off + size++] = buffer.charAt(pos++);
		}
		if (pos == length) {
			buffer = null;
		}

		return size;
	}

	protected void print(String in) {
		// TODO 自動生成されたメソッド・スタブ
		System.out.println(in);
	}

	protected String showDialog() {
		// TODO 自動生成されたメソッド・スタブ
		JTextArea area = new JTextArea(20, 40);
		JScrollPane pane = new JScrollPane(area);
		int result = JOptionPane.showOptionDialog(null, pane, "inout", JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.PLAIN_MESSAGE, null, null, null);
		if(result==JOptionPane.OK_OPTION){
			return area.getText();
		}
		return null;
	}

	@Override
	public void close() throws IOException {
		// TODO 自動生成されたメソッド・スタブ

	}

}
