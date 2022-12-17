package com.example.fastfooddelivery2025.Model;

public class HelpCenter {
    private String Id,Title,EmailOrPhone,Content,IdUser;

    public HelpCenter(String id,String title, String emailOrPhone, String content, String idUser) {
        Id = id;
        Title = title;
        EmailOrPhone = emailOrPhone;
        Content = content;
        IdUser = idUser;
    }

    public HelpCenter(String title) {
        Title = title;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getEmailOrPhone() {
        return EmailOrPhone;
    }

    public void setEmailOrPhone(String emailOrPhone) {
        EmailOrPhone = emailOrPhone;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getIdUser() {
        return IdUser;
    }

    public void setIdUser(String idUser) {
        IdUser = idUser;
    }
}
