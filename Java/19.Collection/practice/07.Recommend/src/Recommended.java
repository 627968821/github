import java.util.LinkedList;

public interface Recommended {
    void addLike(String name, LinkedList<String> list);

    boolean likesBoth(String name, String name1, String name2) throws IllegalArgumentException;

    LinkedList<String> recommendByPerson(String name);

    LinkedList<String> recommendByProject(LinkedList<String> names);
}
