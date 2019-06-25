package  cn.javabs.demo.entity;
/**
 *  资源文件 =  资源路径 +  资源名称
 */
public class Source {
    private   int    id;
    private   String author;
    private   String sourceName;//资源名称
    private   String sourcePath;//资源路径
    private   int downCount; // 下载的次数
    private   String createTime  ;

    @Override
    public String toString() {
        return "Source{" +
                "id=" + id +
                ", author='" + author + '\'' +
                ", sourceName='" + sourceName + '\'' +
                ", sourcePath='" + sourcePath + '\'' +
                ", downCount=" + downCount +
                ", createTime='" + createTime + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public String getSourcePath() {
        return sourcePath;
    }

    public void setSourcePath(String sourcePath) {
        this.sourcePath = sourcePath;
    }

    public int getDownCount() {
        return downCount;
    }

    public void setDownCount(int downCount) {
        this.downCount = downCount;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
