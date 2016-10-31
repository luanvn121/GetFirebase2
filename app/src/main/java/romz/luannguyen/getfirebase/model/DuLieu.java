package romz.luannguyen.getfirebase.model;

/**
 * Created by Admin on 10/28/2016.
 */
public class DuLieu {
    private String content;
    private String title;
    private String url;

    public DuLieu(String content, String title, String url) {
        this.content = content;
        this.title = title;
        this.url = url;
    }

    public DuLieu() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
