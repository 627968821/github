import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class PersonalRecommended implements Recommended {
    Map<String, LinkedList<String>> likes = new HashMap<>();

    @Override
    public void addLike(String name, LinkedList<String> list) {
        likes.put(name, list);
    }

    @Override
    public boolean likesBoth(String name, String name1, String name2) throws IllegalArgumentException {
        for (String key : likes.keySet()) {
            if (name.equals(key)) {
                return likes.get(key).contains(name1) && likes.get(key).contains(name2);
            } else
                throw new IllegalArgumentException("UnknownPersonException");
        }
        return false;
    }

    @Override
    public LinkedList<String> recommendByPerson(String name) {
        for (String key : likes.keySet()) {
            if (name.equals(key)) {
                return likes.get(key);
            }
        }
        return null;
    }
    @Override
    public LinkedList<String> recommendByProject(LinkedList<String> likesList){
        
    }
}
