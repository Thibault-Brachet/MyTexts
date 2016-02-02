package tbrachet.my.texts.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import tbrachet.my.texts.bean.Text;

@Repository
public class TextDao implements ITextDao {
	
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	@Autowired
	public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	public List<Text> findAllTexts() {
		// TODO Auto-generated method stub
		return null;
	}

	public Text findById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	public void saveNewText(Text createdText) {
		// TODO Auto-generated method stub

	}

	public void saveExistingText(Text updatedText) {
		// TODO Auto-generated method stub

	}

}
