package tbrachet.my.texts.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tbrachet.my.texts.bean.Text;

@Service("textService")
@Transactional
public class TextServices implements ITextServices {

	public List<Text> getAllTexts() {
		// TODO Connect with DAO when the database will be created 
		List<Text> results = new ArrayList<Text>();
		
		Text text0 = new Text(0, "title0", "text0", "0");
		Text text1 = new Text(1, "title1", "text1", "1");
		Text text2 = new Text(2, "title2", "text2", "2");
		Text text3 = new Text(3, "title3", "text3", "3");
		
		results.add(text0);
		results.add(text1);
		results.add(text2);
		results.add(text3);
		
		return results;
	}

	public Text getById(long id) {
		// TODO Connect with DAO when the database will be created 
		Text text0 = new Text(0, "title0", "text0", "0");
		
		
		return text0;
	}

	public void createText(Text createdText) {
		// TODO Auto-generated method stub

	}

	public void updateText(Text updatedText) {
		// TODO Auto-generated method stub

	}

	public String calculateScore(String text) {
		// TODO Implement a method calculating a score (Flesch–Kincaid readability tests?)
		return "5";
	}

}
