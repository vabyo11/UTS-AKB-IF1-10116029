package droidmentor.bnv_with_viewpager;

/**
 * TANGGAL PENGERJAAN: 20 MEI 2019
         * NIM: 10116029
         * NAMA: VEBY VABYO
         * KELAS: IF-1
         */

public class SmsData {

    private String _header;
    private String _content;
    private Integer _type;

    public SmsData(String header, String content, Integer type) {
        this._header = header;
        this._content = content;
        this._type = type;
    }

    public String getHeader() {
        return _header;
    }

    public void setHeader(String header) {
        this._header = header;
    }

    public String getContent() {
        return _content;
    }

    public void setContent(String content) {
        this._content = content;
    }

    public Integer getType() {
        return _type;
    }

    public void setType(Integer _type) {
        this._type = _type;
    }
}
