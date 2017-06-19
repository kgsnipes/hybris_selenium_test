package kg.hybris.dto;

/**
 * Created by kaushik on 6/18/2017.
 */
public class Address {

    private String country;
    private String line1;
    private String line2;
    private String phone;
    private String email;
    private String province;
    private String city;
    private String zip;
    private String title;
    private String firstName;
    private String lastName;


    public Address(String country, String line1, String line2, String phone, String email, String province, String city, String zip, String title, String firstName, String lastName) {
        this.country = country;
        this.line1 = line1;
        this.line2 = line2;
        this.phone = phone;
        this.email = email;
        this.province = province;
        this.city = city;
        this.zip = zip;
        this.title = title;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLine1() {
        return line1;
    }

    public void setLine1(String line1) {
        this.line1 = line1;
    }

    public String getLine2() {
        return line2;
    }

    public void setLine2(String line2) {
        this.line2 = line2;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
