package Records;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
@Table(name = "posts")

public class Post
{
	@Id
	@Column(name = "post_uuid", nullable = false)
	@JsonProperty("post_uuid")
	public String postUUID;

	@Column(name = "post_date", nullable = false)
	@JsonProperty("post_date")
	public String postDate;

	@Column(name = "text", nullable = false)
	@JsonProperty("text")
	public String text;
}