package roid.com.gyeongbokgung;


public class PersonalData {
    private String member_id;
    private String member_name;
    private String member_password;

    public String getMember_id() {
        return member_id;
    }

    public String getMember_name() {
        return member_name;
    }

    public String getMember_password() {
        return member_password;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }

    public void setMember_name(String member_name) {
        this.member_name = member_name;
    }

    public void setMember_password(String member_address) {
        this.member_password = member_address;
    }
}