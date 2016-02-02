package tbrachet.my.texts.dao;

import java.util.List;

import tbrachet.my.texts.bean.Text;

public interface ITextDao {
	
	List<Text> findAllTexts();
	Text findById(long id);
	void saveNewText(Text createdText);
	void saveExistingText(Text updatedText);

}
