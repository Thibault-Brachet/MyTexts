package tbrachet.my.texts.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import tbrachet.my.texts.bean.Text;
import tbrachet.my.texts.services.TextServices;
import tbrachet.my.texts.utils.TextUtils;

@RestController
public class TextController {

	@Autowired
	TextServices textService; // Service which will do all data
								// retrieval/manipulation work

	/**
	 * Retrieves all texts
	 * 
	 * @return
	 */
	@RequestMapping(value = "/text/", method = RequestMethod.GET)
	public ResponseEntity<List<Text>> listAllTexts() {
		// Call the Service to retrieve all texts from database
		List<Text> Texts = textService.getAllTexts();
		if (Texts.isEmpty()) {
			return new ResponseEntity<List<Text>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Text>>(Texts, HttpStatus.OK);
	}

	// // -------------------Retrieve Single
	// // Text--------------------------------------------------------
	//
	// @RequestMapping(value = "/text/{id}", method = RequestMethod.GET,
	// produces = MediaType.APPLICATION_JSON_VALUE)
	// public ResponseEntity<Text> getText(@PathVariable("id") long id) {
	// System.out.println("Fetching Text with id " + id);
	// Text Text = textService.getById(id);
	// if (Text == null) {
	// System.out.println("Text with id " + id + " not found");
	// return new ResponseEntity<Text>(HttpStatus.NOT_FOUND);
	// }
	// return new ResponseEntity<Text>(Text, HttpStatus.OK);
	// }

	/**
	 * Create a new Text
	 * 
	 * @param Text
	 * @param ucBuilder
	 * @return
	 */
	@RequestMapping(value = "/text/", method = RequestMethod.POST)
	public ResponseEntity<Void> createText(@RequestBody Text Text, UriComponentsBuilder ucBuilder) {
		System.out.println("Creating Text " + Text.getTitle());

		Text.setScore(TextUtils.calculateScore(Text.getText()));

		// Call the service to save the text
		textService.createText(Text);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/text/{id}").buildAndExpand(Text.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	/**
	 * Update a text
	 * 
	 * @param id
	 * @param Text
	 * @return
	 */
	@RequestMapping(value = "/text/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Text> updateText(@PathVariable("id") long id, @RequestBody Text Text) {
		System.out.println("Updating Text " + id);

		// Check if the specified text exists
		Text currentText = textService.getById(id);

		if (currentText == null) {
			// if not, we return an exception
			System.out.println("Text with id " + id + " not found");
			return new ResponseEntity<Text>(HttpStatus.NOT_FOUND);
		}

		currentText.setId(Text.getId());
		currentText.setTitle(Text.getTitle());
		currentText.setText(Text.getText());
		currentText.setScore(TextUtils.calculateScore(Text.getText()));

		// Call the Service to update
		textService.updateText(currentText);

		return new ResponseEntity<Text>(currentText, HttpStatus.OK);
	}

}
