package learningsys.model;

import org.springframework.web.multipart.MultipartFile;

public class GetPath {

    private MultipartFile multfile;
    private String name;
    private String detail;
    private int price;

    public MultipartFile getMultfile() {
        return multfile;
    }

    public void setMultfile(MultipartFile multfile) {
        this.multfile = multfile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
