package springmvcdemo;

import java.util.Objects;

public class ResponseMessage {
  private Integer userId;
  private Integer id;
  private String title;
  private Boolean completed;

  public ResponseMessage(Integer userId, Integer id, String title, Boolean completed) {
    this.userId = userId;
    this.id = id;
    this.title = title;
    this.completed = completed;
  }

  public ResponseMessage() {}

  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Boolean getCompleted() {
    return completed;
  }

  public void setCompleted(Boolean completed) {
    this.completed = completed;
  }

  @Override
  public String toString() {
    return "ResponseMessage{"
        + "userId="
        + userId
        + ", id="
        + id
        + ", title='"
        + title
        + '\''
        + ", completed="
        + completed
        + '}';
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof ResponseMessage that)) return false;
    return Objects.equals(userId, that.userId)
        && Objects.equals(id, that.id)
        && Objects.equals(title, that.title)
        && Objects.equals(completed, that.completed);
  }

  @Override
  public int hashCode() {
    return Objects.hash(userId, id, title, completed);
  }
}
