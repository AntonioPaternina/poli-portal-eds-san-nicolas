package co.com.estacionsannicolas.beans;

import java.io.Serializable;
import java.util.List;

public class PageBean<T> implements Serializable {
    private long totalItems;
    private List<T> content;

    public long getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(long totalItems) {
        this.totalItems = totalItems;
    }

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }
}
