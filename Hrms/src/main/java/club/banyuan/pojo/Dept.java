package club.banyuan.pojo;

public class Dept {

  private int id;
  private String name;
  private String description;

  public Dept() {
  }

  public Dept(int id, String name, String description) {
    this.id = id;
    this.name = name;
    this.description = description;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @Override
  public String toString() {
    return "{" +
<<<<<<< HEAD
            "id=" + id +
            ", name='" + name + '\'' +
=======
            "name=" + name +
            ", id='" + id + '\'' +
>>>>>>> fc5f787... 1
            ", description='" + description + '\'' +
            '}';
  }
}