package web.parsers;

import com.google.gson.Gson;
import java.io.IOException;
import java.net.URISyntaxException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import web.paths.Paths;
import web.user.User;
import web.user.UserAddress;
import web.user.UserGeolocaton;
import web.methods.UserMethods;

public class UsersParser {
    private static final Gson gson = new Gson();

    public static void getUsers() throws IOException, URISyntaxException, InterruptedException {
        String jsonAllUsers =  UserMethods.get(Paths.getRoot());

        Type typeToken = UserMethods.getType();

        List<User> users = gson.fromJson(jsonAllUsers, typeToken);
        users.forEach(System.out::println);
    }

    public static void getUserById(int userId) throws IOException, URISyntaxException, InterruptedException {
        String jsonUserById =  UserMethods.get(Paths.getUserById(userId));

        User user = gson.fromJson(jsonUserById, User.class);

        System.out.println(user.toString());
    }
    public static void getUserByName() throws IOException, URISyntaxException, InterruptedException {
        String jsonUserByName =  UserMethods.get(Paths.getUserByName("Samantha"));
        Type typeToken = UserMethods.getType();

        List<User> usersByName = gson.fromJson(jsonUserByName, typeToken);
        usersByName.forEach(System.out::println);
    }

    public static void postUser() throws IOException, InterruptedException {
        UserGeolocaton geolocaton = new UserGeolocaton("30", "50");
        UserAddress address = new UserAddress("48th Terrace", "112", "Miami", "58804-1099", geolocaton);
        User newUser = new User(11, "Henry Sonvar", "Halk", "sonvar@billy.biz", address, "210.066.5836", "sonvar.io");

        String postResponse = UserMethods.post(Paths.getRoot(), gson.toJson(newUser));
        System.out.println(gson.fromJson(postResponse, User.class));
    }

    public static void updateUser(int userId) throws IOException, URISyntaxException, InterruptedException {
        String customUserUpdates = gson.toJson(Map.of("name", "Tom Berton", "username", "Tommy", "email", "tommy@gmail.com", "website", "https://www.tommy-profile.com.ua", "phone", "240-734-68"));

        String updateResponse = UserMethods.put(Paths.getUserById(userId), customUserUpdates);
        System.out.println(gson.fromJson(updateResponse, User.class));
    }

    public static void removeUser() throws IOException, URISyntaxException, InterruptedException {
        String removeResponse = UserMethods.remove(Paths.getUserById(8));
        System.out.println(removeResponse);
    }
}
