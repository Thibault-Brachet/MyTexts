package tbrachet.my.texts.services.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import tbrachet.my.texts.bean.Text;
import tbrachet.my.texts.services.TextServices;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:test-context.xml")
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class TextServicesTest  {

	@Autowired
	private TextServices textServices;
	
	
	@Test
	public void getAllTextsTest() {
		List<Text> texts = textServices.getAllTexts();

		Assert.assertEquals(3, texts.size());
		return;
	}

	@Test
	public void findTextByIdTest() {
		Text text = textServices.getById(1l);

		Assert.assertEquals("EDIA", text.getTitle());
		Assert.assertEquals(
				"We specialise in education technology because we are passionate about education. We believe that access to education improves the quality of life for many. We think that learning and teaching on a web scale can be improved by using the right technologies. EDIA offers research, consultancy, development and maintenance of web-based education technology. We serve various types of organisations, such as universities, schools, publishers, libraries and corporations.",
				text.getText());
		Assert.assertEquals("", text.getScore());
		return;
	}

	@Test
	public void saveNewTextTest() {
		Text text = new Text();
		text.setTitle("New Title");
		text.setText("New Text");
		text.setScore("5");

		textServices.createText(text);
		List<Text> allTexts = textServices.getAllTexts();
		Assert.assertEquals(4, allTexts.size());
		
		Text newText = textServices.getById(4);
		
		Assert.assertNotNull(newText);
		
		if(newText != null){
			long id = newText.getId();
			Assert.assertNotNull(id);
			Assert.assertEquals(4, id);					
			Assert.assertEquals("New Title", newText.getTitle());
			Assert.assertEquals("New Text", newText.getText());
			Assert.assertEquals("5", newText.getScore());			
		} 
		
	}
	
	@Test
	public void saveExistingTextTest() {
		Assert.assertEquals(3, textServices.getAllTexts().size());
		Text text = textServices.getById(1l);
		
		text.setTitle("Updated title");
		text.setText("Updated text");
		text.setScore("9");
		
		textServices.updateText(text);
		
		Assert.assertEquals(3, textServices.getAllTexts().size());
		Text updatedText = textServices.getById(1l);		

		Assert.assertEquals("Updated title", updatedText.getTitle());
		Assert.assertEquals("Updated text", updatedText.getText());
		Assert.assertEquals("9", updatedText.getScore());
	}


}
