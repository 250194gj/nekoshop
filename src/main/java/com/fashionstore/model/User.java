package com.fashionstore.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "users")
public class User {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

        @NotBlank(message = "名前は必須です")
        @Size(max = 100, message = "名前は100文字以下にしてください。")
        @Column(name = "name", nullable = false, length = 100)
    private String name;

        @NotBlank(message = "メールは必須です")
        @Email(message = "メールアドレスが正しくありません。")
        @Size(max = 150, message = "メール長すぎる")
        @Column(
                name = "email",
                nullable = false,
                unique = true,
                length = 150
        )
    private String email;

        @NotBlank(message = "パスワードは必須です")
        @Size(min = 6, message = "パスワードは6文字以上で入力してください。")
        @Column(name = "password", nullable = false)
    private String password;

        @NotNull(message = "ロールは必須です")
        @Enumerated(EnumType.STRING)
        @Column(name = "role", nullable = false, length = 20)
    private Role role = Role.USER;

    public User() {
    }

    public User(
            String name,
            String email,
            String password,
            Role role
    ) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}