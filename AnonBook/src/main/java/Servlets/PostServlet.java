package Servlets;

import Database.Database;
import Records.Post;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public class PostServlet extends HttpServlet
{
	private final static PostServlet singleton = new PostServlet();

	private final ObjectMapper objectMapper = new ObjectMapper();
	private final Database database = Database.getInstance();

	private PostServlet(){}

	public static PostServlet getInstance()
	{
		return singleton;
	}

	private void logPost(Post post)
	{
		System.out.println("A NEW POST:");
		System.out.println("Post postUUID: " + post.postUUID);
		System.out.println("Post date: " + post.postDate);
		System.out.println("Post text: " + post.text);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException
	{
		if (req.getPathInfo() != null)
		{
			String postUUID = req.getPathInfo().substring(1);
			System.out.println(postUUID);

			Post post = database.getPost(postUUID);
			objectMapper.writeValue(resp.getWriter(), post);

			return;
		}

		List<Post> posts = database.getPosts();

		objectMapper.writeValue(resp.getWriter(), posts);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException
	{
		Post post = objectMapper.readValue(req.getReader(), Post.class);

		if (post.postDate.isBlank() || post.text.isBlank())
		{
			resp.setStatus(403);
			return;
		}

		post.postUUID = UUID.randomUUID().toString();

		database.addPost(post);

		logPost(post);
	}
}