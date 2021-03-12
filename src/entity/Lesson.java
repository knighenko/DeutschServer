package entity;

public class Lesson {
    private int id;
    private String title;
    private String youTubeUrl;
    private String serverUrl;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYouTubeUrl() {
        return youTubeUrl;
    }

    public void setYouTubeUrl(String youTubeUrl) {
        this.youTubeUrl = youTubeUrl;
    }

    public String getServerUrl() {
        return serverUrl;
    }

    public void setServerUrl(String serverUrl) {
        this.serverUrl = serverUrl;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder=new StringBuilder("{");

        stringBuilder.append("\"id\":\"").append(id).append('\"').append(", \"title\":\"").append(title).append('\"').append(", \"youTubeUrl\":\"").append(youTubeUrl).append('\"').
                append(", \"serverUrl\":\"").append(serverUrl).append('\"').append("}");

        return stringBuilder.toString();
    }
}
