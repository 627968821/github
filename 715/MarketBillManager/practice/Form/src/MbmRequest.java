public class MbmRequest {
    private String method;
    private String path;
    private String host;
    private int contentLength;

    public int getContentLength() {
        return contentLength;
    }

    public void setContentLength(int contentLength) {
        this.contentLength = contentLength;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "MbmRequest{" +
                "method='" + method + '\'' +
                ", path='" + path + '\'' +
                ", host='" + host + '\'' +
                ", contentLength='" + contentLength + '\'' +
                '}';
    }
}
