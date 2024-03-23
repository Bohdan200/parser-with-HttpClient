package web.parsers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.URISyntaxException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import web.data.Comment;
import web.data.Post;
import web.data.Task;
import web.methods.UserMethods;
import web.paths.Paths;
import web.methods.DataMethods;

public class DataParser {
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private static final DataMethods methods = new DataMethods();

    public static void getCommentsToLastPost(int userId) throws IOException, URISyntaxException, InterruptedException {
        String posts = methods.get(Paths.getUserPosts(userId));
        List<Post> allPosts = gson.fromJson(posts, DataMethods.getType(Post.class));

        int lastPostIdx = allPosts.get(1).getId();
        for (Post post : allPosts) {
            if (post.getId() > lastPostIdx)
                lastPostIdx = post.getId();
        }

        String comments = methods.get(Paths.getComments(lastPostIdx));
        List<Comment> allComments = gson.fromJson(comments, DataMethods.getType(Comment.class));

        FileWriter fileWriter = new FileWriter("user-" + userId + "-posts-" + lastPostIdx + "-comments.json");
        List<Comment> modifiedComments = new ArrayList<>(allComments.size());

        for (Comment comment : allComments) {
            String parseBody = comment.getBody().lines().reduce("", (acc, p) -> acc + p);
            comment.setBody(parseBody);

            System.out.println(comment);
            modifiedComments.add(comment);
        }

        Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
        prettyGson.toJson(modifiedComments, fileWriter);

        fileWriter.close();
    }
 public static void getUncompletedTasks(int userId) throws IOException, URISyntaxException, InterruptedException {
        String tasks = methods.get(Paths.getTasks(userId));
        List<Task> allTasks = gson.fromJson(tasks, DataMethods.getType(Task.class));

        for (Task task : allTasks) {
            if (!task.isCompleted()) {
                System.out.println(task);
            }
        }
    }
}
