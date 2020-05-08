package TechnicalInspection;

import java.time.LocalDate;
import Enum.RoleType;

public class User {
    private int id;
    private String name;
    private String surname;
    private String jmbg;
    private LocalDate birthDate;
    private String address;
    private String postalNumber;
    private String mail;
    private String phoneNumber;
    private String userName;
    private String password;
    private RoleType role;

    public User(String name, String surname, String jmbg, LocalDate birthDate, String address, String postalNumber, String mail, String phoneNumber, String userName, String password, RoleType role) {
        this.name = name;
        this.surname = surname;
        this.jmbg = jmbg;
        this.birthDate = birthDate;
        this.address = address;
        this.postalNumber = postalNumber;
        this.mail = mail;
        this.phoneNumber = phoneNumber;
        this.userName = userName;
        this.password = password;
        this.role = role;
    }

    public User(int id, String name, String surname, String jmbg, LocalDate birthDate, String address, String postalNumber, String mail, String phoneNumber, String userName, String password, RoleType role) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.jmbg = jmbg;
        this.birthDate = birthDate;
        this.address = address;
        this.postalNumber = postalNumber;
        this.mail = mail;
        this.phoneNumber = phoneNumber;
        this.userName = userName;
        this.password = password;
        this.role = role;
    }


    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getJmbg() {
        return jmbg;
    }

    public void setJmbg(String jmbg) {
        this.jmbg = jmbg;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostalNumber() {
        return postalNumber;
    }

    public void setPostalNumber(String postalNumber) {
        this.postalNumber = postalNumber;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public RoleType getRole() {
        return role;
    }

    public void setRole(RoleType role) {
        this.role = role;
    }
}
