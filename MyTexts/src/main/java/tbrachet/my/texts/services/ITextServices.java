package tbrachet.my.texts.services;

import java.util.List;

import tbrachet.my.texts.bean.Text;


public interface ITextServices {
	
	List<Text> getAllTexts();
	Text getById(long id);
	void createText(Text createdText);
	void updateText(Text updatedText);

}
