package gutu.lexerRunner;

import gutu.Lexer;
import gutu.ParseException;
import gutu.Token;

public class LexerRunner {

	public static void main(String[] args) throws ParseException {
		// TODO 自動生成されたメソッド・スタブ
		Lexer lexer=new Lexer(new CodeDialog());
		Token token;
		while((token=lexer.read())!=Token.EOF){
			System.out.println("=> "+token.getText());
		}
	}

}
