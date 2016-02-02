package tbrachet.my.texts.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tbrachet.my.texts.bean.Text;
import tbrachet.my.texts.dao.ITextDao;

@Service("textService")
public class TextServices implements ITextServices {
	
	@Autowired
	private ITextDao dao;

	public List<Text> getAllTexts() {
		return dao.findAllTexts();
	}

	public Text getById(long id) {
		return dao.findById(id);
	}

	public void createText(Text createdText) {
		dao.saveNewText(createdText);

	}

	public void updateText(Text updatedText) {
		dao.saveExistingText(updatedText);

	}

}
