import java.util.*;

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
        Set<String> likeLists=new LinkedHashSet<>();
        for (String key : likes.keySet()) {
            for (String str:likes.get(key)) {
                likeLists.add(str);
            }
    }
        LinkedList<String> linkedList=new LinkedList<>();
        for (String str:likeLists) {
            linkedList.add(str);
        }
        for (String str:likesList) {
            for (String str1:likeLists) {
                if(str.equals(str1)){
                    linkedList.remove(str);
                }
            }
        }
          return linkedList;
        }

}
