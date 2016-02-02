package tbrachet.my.texts.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
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
		Map<String, Object> params = new HashMap<String, Object>();

		String sql = "SELECT * FROM texts";

		List<Text> result = namedParameterJdbcTemplate.query(sql, params, new TextMapper());

		return result;
	}

	public void saveNewText(Text createdText) {
		String query = "INSERT INTO texts (TITLE, TEXT, SCORE) VALUES (:title,:text,:score)";
		Map<String, Object> namedParameters = new HashMap<String, Object>();
		namedParameters.put("title", createdText.getTitle());
		namedParameters.put("text", createdText.getText());
		namedParameters.put("score", createdText.getScore());
		namedParameterJdbcTemplate.update(query, namedParameters);

	}

	public void saveExistingText(Text updatedText) {
		String SQL = "UPDATE texts SET TITLE = :title, TEXT=:text, SCORE=:score WHERE ID = :id";
		Map<String, Object> namedParameters = new HashMap<String, Object>();
		namedParameters.put("title", updatedText.getTitle());
		namedParameters.put("text", updatedText.getText());
		namedParameters.put("score", updatedText.getScore());
		namedParameters.put("id", updatedText.getId());
		namedParameterJdbcTemplate.update(SQL, namedParameters);

	}

	public Text findById(long id) {
		String SQL = "SELECT * FROM texts WHERE ID = :id";  
		SqlParameterSource namedParameters = new MapSqlParameterSource("id", Long.valueOf(id));  
		Text text = (Text) namedParameterJdbcTemplate.queryForObject(SQL, namedParameters, new TextMapper());  
		return text;
	}
	
	private static final class TextMapper implements RowMapper<Text> {

		public Text mapRow(ResultSet rs, int rowNum) throws SQLException {
			Text text = new Text();
			text.setId(rs.getInt("id"));
			text.setTitle(rs.getString("title"));
			text.setText(rs.getString("text"));
			text.setScore(rs.getString("score"));
			return text;
		}
	}


}
