async function createPost()
{
    const url = "http://localhost:8080/post";

    const div = document.getElementById("create_post");
    const inputs = div.getElementsByTagName("input")

    const postDate = new Date();
    const postText = inputs.text.value;

    if (postText === '')
    {
        alert("A post cannot be empty");
        return null;
    }

    const post =
    {
        post_date: postDate,
        text: postText,
    };

    const response = await fetch(url,
    {
        method: "POST",
        body: JSON.stringify(post)
    });

    inputs.text.value = '';
    alert("Successfully made a new post.");
}

async function viewPost(uuid)
{
    const url = "http://localhost:8080/post"
}

async function getPosts()
{
    const div = document.getElementById("posts");
    const url = "http://localhost:8080/post";

    const response = await fetch(url, {method:"GET"});

    const json = response.json();

    json.then(data =>
    {
        for (let i = 0; i < data.length; i++)
        {
            div.innerHTML +=

            `<div class="post" id="${data[i].post_uuid}">
             <p>${data[i].text}</p>
             <img src="" alt="Could not load the image.">
             <input type="button" onclick="viewPost('${data[i].post_uuid}')" value="View Post">
             </div>`;
        }
    });
}