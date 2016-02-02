package tbrachet.my.texts.bean;

//@Entity
//@Table(name="texts")
public class Text {

//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column(name = "ID")
	private Integer id;

//	@Column(name = "TITLE")
	private String title;

//	@Column(name = "TEXT")
	private String text;

//	@Column(name = "SCORE")
	private String score;

	public Text() {
		super();
	}

	public Text(Integer id, String title, String text, String score) {
		super();
		this.id = id;
		this.title = title;
		this.text = text;
		this.score = score;
	}
	
	public Text(String title, String text, String score) {
		super();
		this.title = title;
		this.text = text;
		this.score = score;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}
	
	@Override
    public String toString() {
        return "User [id=" + id + ", title=" + title + ", text=" + text
                 + "]";
    }

}
