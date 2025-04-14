package Servlets;

import Database.Database;
import Records.Comment;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class CommentServlet extends HttpServlet
{
	private final static CommentServlet singleton = new CommentServlet();

	private final ObjectMapper objectMapper = new ObjectMapper();
	private final Database database = Database.getInstance();

	private CommentServlet(){}

	public static CommentServlet getInstance()
	{
		return singleton;
	}

	private void logComment(Comment comment)
	{
		System.out.println("A NEW COMMENT:");
		System.out.println("Post ID: " + comment.postUUID);
		System.out.println("Comment date: " + comment.commentDate);
		System.out.println("Text: " + comment.text);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException
	{
		Comment comment = objectMapper.readValue(req.getReader(), Comment.class);

		if (comment.postUUID.isBlank() || comment.commentDate.isBlank() || comment.text.isBlank())
		{
			resp.setStatus(403);
			return;
		}

		database.addComment(comment);
		logComment(comment);
	}
}