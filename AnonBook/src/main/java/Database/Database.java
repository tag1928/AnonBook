package Database;

import Records.Comment;
import Records.Post;
import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.util.List;

public class Database
{
	private final static Database singleton = new Database();

	private final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("main");
	private final EntityManager entityManager = entityManagerFactory.createEntityManager();

	private final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
	private final CriteriaQuery<Post> criteriaQuery = criteriaBuilder.createQuery(Post.class);
	private final Root<Post> root = criteriaQuery.from(Post.class);

	private Database()
	{
		entityManager.setFlushMode(FlushModeType.AUTO);
	}

	public static Database getInstance()
	{
		return singleton;
	}

	public void addPost(Post post)
	{
		entityManager.getTransaction().begin();
		entityManager.persist(post);
		entityManager.getTransaction().commit();
	}

	public void addComment(Comment comment)
	{
		entityManager.getTransaction().begin();
		entityManager.persist(comment);
		entityManager.getTransaction().commit();
	}

	public List<Post> getPosts()
	{
		List<Post> posts;

		criteriaQuery.select(root);

		TypedQuery<Post> query = entityManager.createQuery(criteriaQuery);
		posts = query.getResultList();

		return posts;
	}

	public Post getPost(String postUUID)
	{
		criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("post_uuid"), postUUID));

		TypedQuery<Post> typedQuery = entityManager.createQuery(criteriaQuery);

		return typedQuery.getSingleResult();
	}
}