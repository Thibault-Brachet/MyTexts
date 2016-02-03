package tbrachet.my.texts.utils.test;

import org.junit.Assert;
import org.junit.Test;

import tbrachet.my.texts.utils.TextUtils;

public class TextUtilsTest {

	@Test
	public void caculatorScoreEasytest() {
		String s = "EDIA is a nice place to work in";

		Assert.assertEquals("1", TextUtils.calculateScore(s));

	}

	@Test
	public void caculatorScoreHardtest() {
		String s = "The Australian platypus is seemingly a hybrid of a mammal and reptilian creature";
		// This sentence is known to be a 10

		Assert.assertEquals("10", TextUtils.calculateScore(s));

	}

	@Test
	public void caculatorScoreMediumtest() {
		String s = "In 1976 the US Navy modified the Reading Ease formula to produce a grade-level score by applying the Flesch Grade-Scale formula, or the Kincaid formula. John P. Kincaid was assisted by Fishburne, Rogers, and Chissom, in his research. ";

		Assert.assertEquals("7", TextUtils.calculateScore(s));

	}

	

}
