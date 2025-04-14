package Records;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
@Table(name = "comments")

public class Comment
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "comment_id", nullable = false)
	@JsonIgnore
	private Long id;

//	@ManyToOne
//	@Transient
//	public Post post;

	@Column(name = "post_uuid", nullable = false)
	@JsonProperty("post_uuid")
	public String postUUID;

	@Column(name = "comment_date", nullable = false)
	@JsonProperty("comment_date")
	public String commentDate;

	@Column(name = "text", nullable = false)
	@JsonProperty("text")
	public String text;
}